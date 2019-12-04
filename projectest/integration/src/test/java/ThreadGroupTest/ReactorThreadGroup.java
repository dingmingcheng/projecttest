package ThreadGroupTest;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import static com.dmc.common.AsysWriter.doWrite;

public class ReactorThreadGroup {

    private static final int DEFAULT_THREADPOOL_SIZE = 10;

    private Integer threadNum;

    private AtomicInteger currentNum = new AtomicInteger(0);

    private List<Handler> handlerWorkers = new ArrayList<Handler>();

    public ReactorThreadGroup() {
        this(DEFAULT_THREADPOOL_SIZE);
    }

    public ReactorThreadGroup(Integer threadNum) {
        this.threadNum = threadNum;
        for (int i = 0; i < threadNum; i ++) {
            Handler worker = new Handler();
            handlerWorkers.add(worker);
            worker.start();
        }
        System.out.println("threadpool create success");
    }

    public void dispatch(SocketChannel sc) {
        next().add(sc);
    }

    public Handler next() {
        return handlerWorkers.get((currentNum.getAndIncrement()) % threadNum);
    }
}
