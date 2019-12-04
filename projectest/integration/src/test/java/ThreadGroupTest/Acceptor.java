package ThreadGroupTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Acceptor {

    public static void main(String[] args) throws IOException {
        /**
         * init
         */
        ServerSocketChannel serverSocketChannel;
        Selector selector;
        Set<SelectionKey> selectionKeys = null;

        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        System.out.println("server is start...");
        ReactorThreadGroup group = new ReactorThreadGroup(10);
        while (true) {
            selector.select();
            selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> it = selectionKeys.iterator();
            for (; it.hasNext(); ) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                    SocketChannel client = sc.accept();
                    client.configureBlocking(false);
                    System.out.println("connected...");
                    group.dispatch(client);
                }
            }
        }
    }
}
