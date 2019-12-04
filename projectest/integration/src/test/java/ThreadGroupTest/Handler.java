package ThreadGroupTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.dmc.common.AsysWriter.doWrite;

public class Handler extends Thread{

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private volatile Boolean running = false;

    private List<SocketChannel> socketChannelList;

    private Selector selector;

    private Boolean isConnected = false;

    Handler() {
        try {
            socketChannelList = new ArrayList<>();
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(SocketChannel socketChannel) {
        lock.lock();
        try {
            selector.wakeup();
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannelList.add(socketChannel);
            condition.signal();
            isConnected = true;
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (true) {
                if (!isConnected) {
                    condition.await();
                }
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();

                for (; it.hasNext();) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(byteBuffer);
                        if (readBytes > 0) {
                            System.out.println("connected to thread " + Thread.currentThread().getName());
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            String response = new String(bytes, "UTF-8");
                            System.out.println("GET, the request is " + response + " it's from " + sc.getRemoteAddress());
                            String result = response + " 's result";
                            System.out.println("send result...");
                            doWrite(sc, result + "");
                        } else if (readBytes < 0) {
                            System.out.println(Thread.currentThread().getName() + " disconnected");
                            key.cancel();
                            sc.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
