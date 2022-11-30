package lessonThree;

import java.util.concurrent.Callable;

public class Pin extends Thread {

    private String action;
    final private Object monitor;

    private MyCounter counter;

    private Integer countShots;

    Pin(Object monitor, String action, MyCounter counter, Integer countShots){
        this.action = action;
        this.monitor = monitor;
        this.counter=counter;
        this.countShots=countShots;
    }

    @Override
    public void run(){
        synchronized (monitor) {
            while (counter.getCounter() < countShots) {
                if (counter.getCounter() % 2 == 0) {
                    System.out.println(action);
                    counter.increment();
                    monitor.notify();
                }
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            monitor.notify();
        }
    }
}
