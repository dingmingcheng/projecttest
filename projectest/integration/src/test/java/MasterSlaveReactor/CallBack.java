package MasterSlaveReactor;

        import java.nio.channels.SocketChannel;

public interface CallBack {
    void Dispatch(SocketChannel socketChannel);
}
