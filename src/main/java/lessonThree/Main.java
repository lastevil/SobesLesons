package lessonThree;

public class Main {
    final static Object monitor = new Object();

    public static void main(String[] args) {

        MyCounter counter = new MyCounter();
        Thread th1 = new Pin(monitor, "PIN", counter, 20);
        Thread th2 = new Pong(monitor, "PONG", counter, 20);
        th1.start();
        th2.start();


        MyCounter counter2 = new MyCounter();

        Thread th3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter2.increment();
            }
            System.out.println("Th3 counter: "+counter2.getCounter());
        });
        Thread th4 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                counter2.decrement();
            }
            System.out.println("Th4 counter: "+counter2.getCounter());
        });
        th3.start();
        th4.start();
        try {
            Thread.currentThread().join(th4.getId());
            Thread.currentThread().join(th3.getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main counter: "+counter2.getCounter());
    }
}
