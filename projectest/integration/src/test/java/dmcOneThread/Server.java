package dmcOneThread;

import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.dmc.common.AsysWriter.doWrite;

public class Server {
    public static void main(String[] args){
        try {
            ServerSocketChannel serverSocketChannel;
            Selector selector;
            Selector selector1;
            Set<SelectionKey> selectionKeys = null;

            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            selector1 = Selector.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));

            System.out.println("server is start");
            while (true) {
                selector.select();
                selectionKeys = selector.selectedKeys();
                for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel client = ssc.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("connected...");
                    }
                    else if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(byteBuffer);
                        if (readBytes > 0) {
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            String response = new String(bytes, "UTF-8");
                            System.out.println("GET, the request is " + response + " it's from " + sc.getRemoteAddress());
                            String result = response + " 's result";
                            System.out.println("send result...");
                            doWrite(sc, result + "");
                        }
                        //链路已经关闭，释放资源
                        else if (readBytes < 0) {
                            key.cancel();
                            sc.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
