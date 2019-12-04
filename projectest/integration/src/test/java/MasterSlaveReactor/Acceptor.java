package MasterSlaveReactor;

import ThreadGroupTest.ReactorThreadGroup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable{

    private SocketChannel socketChannel;

    private CallBack callBack;

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    Acceptor(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            // TODO
            System.out.println( Thread.currentThread().getName() + " " + socketChannel.getRemoteAddress() +  " connected...");
            callBack.Dispatch(socketChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
