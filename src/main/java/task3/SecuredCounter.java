package task3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecuredCounter {

    public static void main(String[] args) {
        System.out.printf("Начальное значение счетчика: %d%n", new Counter().getCounter());
        Counter counter = new Counter();

        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        Thread t3 = new Thread(counter);

        t1.start();
        t2.start();
        t3.start();
    }


}

class Counter implements Runnable {
    private volatile int counter = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                lock.lock();
                System.out.printf("Поток %s увеличил счетчик на %d%n", Thread.currentThread().getName(), i);
                counter += i;
                System.out.printf("Счетчик: %d%n", counter);
            } finally {
                lock.unlock();
            }
        }
    }

    public int getCounter() {
        return counter;
    }
}

