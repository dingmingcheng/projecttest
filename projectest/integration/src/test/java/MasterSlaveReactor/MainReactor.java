package MasterSlaveReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainReactor implements Runnable, CallBack{
    private static final int DEFAULT_PORT = 9090;

    private static final String DEFAULT_HOST = "127.0.0.1";

    private int port;

    private String host;

    private AtomicInteger currentNum;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private ExecutorService threadPool;

    private ExecutorService subThreadPool;

    private SubReactor[] subReactors = new SubReactor[10];

    MainReactor() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    MainReactor(String host, Integer port) {
        this.port = port;
        this.host = host;
        currentNum = new AtomicInteger(0);
        init();
        initSubPool();
    }

    public void initSubPool() {
        subThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i ++) {
            subReactors[i] = new SubReactor();
            subThreadPool.execute(subReactors[i]);
        }
    }

    public void init() {
        try {
            threadPool = Executors.newFixedThreadPool(10);

            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(host, port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        while (true) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeyset = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeyset.iterator();
                for (;it.hasNext();) {
                    SelectionKey key = it.next();

                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    accept(sc);
                    it.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void accept(SocketChannel sc) {
        Acceptor acceptor = new Acceptor(sc);
        acceptor.setCallBack(this);
        threadPool.execute(acceptor);
    }

    @Override
    public void Dispatch(SocketChannel socketChannel) {
        SubReactor subReactor = next();
        subReactor.register(socketChannel);
    }

    public SubReactor next() {
        return subReactors[currentNum.getAndAdd(1) % 10];
    }
}
