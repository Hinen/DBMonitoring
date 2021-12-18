import java.util.ArrayList;
import java.util.Map;

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

        DBManager.get();

        while (true) {
            String result = DBManager.get().queryGetValue("SHOW VARIABLES WHERE `Variable_name` = 'max_connections';", "Value");
            System.out.println(result);

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