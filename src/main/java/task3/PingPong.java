package task3;

public class PingPong {
    private final Object monitor = new Object();
    private volatile String action = "Ping";
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        Thread t1 = new Thread(pingPong::printPing);
        Thread t2 = new Thread(pingPong::printPong);

        t1.start();
        t2.start();
    }

    private void printPing() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (action.equals("Pong")) {
                        monitor.wait();
                    }
                    System.out.println("Ping");
                    action = "Pong";
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printPong() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (action.equals("Ping")) {
                        monitor.wait();
                    }
                    System.out.println("Pong");
                    action = "Ping";
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
