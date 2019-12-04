package com.dmc.common;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.dmc.common.AsysWriter.doWrite;

public class Client implements Runnable{

    private SocketChannel clientChannel;

    private Selector selector;

    private volatile Boolean connected = false;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public Client() {
        try {
            clientChannel = SocketChannel.open();
            selector = Selector.open();
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
            clientChannel.connect(new InetSocketAddress("127.0.0.1", 9090));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {

            Set<SelectionKey> selectionKeys = null;
            while (true) {
                selector.select();
                selectionKeys = selector.selectedKeys();

                for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                    SelectionKey key = it.next();
                    it.remove();

                    SocketChannel sc = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        lock.lock();
                        try {
                            System.out.println("client connect...");
                            if (sc.finishConnect()) ;
                            else System.exit(1);
                            sc.register(selector, SelectionKey.OP_READ);
                            this.connected = true;
                            System.out.println("this.connect is " + this.connected);
                            condition.signal();
                        } finally {
                            lock.unlock();
                        }
                    } else if (key.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int length = sc.read(byteBuffer);
                        if (length > 0) {
                            byteBuffer.flip();
                            byte[] ans = new byte[byteBuffer.remaining()];
                            byteBuffer.get(ans);
                            String response = new String(ans, "UTF-8");
                            System.out.println("get the result from server : " + response);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String sendString) throws Exception {
        lock.lock();
        try {
            if (!this.connected) {
                condition.await();
            }
            clientChannel.register(selector, SelectionKey.OP_READ);
            doWrite(clientChannel, sendString);
        } finally {
            lock.unlock();
        }
    }
}
