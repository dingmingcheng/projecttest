package MasterSlaveReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.dmc.common.AsysWriter.doWrite;

public class SubReactor implements Runnable{

    private List<SocketChannel> socketChannels;

    private Selector selector;

    SubReactor() {
        init();
    }

    private void init() {
        try {
            selector = Selector.open();
            socketChannels = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(SocketChannel socketChannel) {
        try {
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannels.add(socketChannel);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                for (; it.hasNext();) {
                    SelectionKey key = it.next();
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
                    it.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
