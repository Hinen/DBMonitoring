public class Main implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new Main());
        thread.start();
    }

    @Override
    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            shutDown();
        }));

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

    private void shutDown() {
        DBManager.get().closeConnection();
    }
}