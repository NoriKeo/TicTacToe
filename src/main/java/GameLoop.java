import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GameLoop {
    static boolean start = false;
    static TimeUnit unit = TimeUnit.SECONDS;
    static long timeout = 1;
    static boolean tryLock;
    private final ReentrantLock lock = new ReentrantLock();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    static long pid = ProcessHandle.current().pid();
    public void start() throws InterruptedException {
        System.out.println("PID: " + pid);
        writeLock.lock();
        readLock.lock();
        try {
            while (true) {
                Match match = new Match();
                match.start();
                break;

            }
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }

    }


}
