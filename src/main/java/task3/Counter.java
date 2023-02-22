package task3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private volatile int counter = 0;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Counter count = new Counter();
        System.out.printf("Начальное значение счетчика: %d%n", new Counter().counter);

        Thread t1 = new Thread(count::increaseCounter);
        Thread t2 = new Thread(count::increaseCounter);
        Thread t3 = new Thread(count::increaseCounter);

        t1.start();
        t2.start();
        t3.start();
    }

    private void increaseCounter() {
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
}
