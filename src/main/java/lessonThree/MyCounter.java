package lessonThree;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCounter {
    private int counter;
    private final Lock lock = new ReentrantLock();

    public void increment(){
        lock.lock();
        try {
            counter++;
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            counter--;
        }finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}
