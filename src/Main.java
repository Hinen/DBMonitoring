import Manager.DBManager;
import Manager.InputManager;
import Manager.MonitoringManager;
import Manager.SMTPManager;

public class Main implements Runnable {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DBManager.get().closeConnection();
        }));

        // Main Thread
        Thread thread = new Thread(new Main());
        thread.start();

        // Input Thread
        new Thread(() -> {
            while (true) {
                InputManager.get().update();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }

    @Override
    public void run() {
        SMTPManager.get();
        DBManager.get();
        MonitoringManager.get();

        while (true) {
            MonitoringManager.get().start();
            SMTPManager.get().start();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}