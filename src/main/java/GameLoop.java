import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GameLoop {
    static boolean start = false;
    static TimeUnit unit = TimeUnit.SECONDS;
    static long timeout = 1;
    static boolean tryLock;
    private final ReentrantLock lock = new ReentrantLock();

    public void start() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                Match match = new Match();
                match.start();
                break;

            }
        } finally {
            lock.unlock();
        }

    }


}
