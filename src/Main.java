import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DBManager.get();

        new Thread(() -> {
            while (true) {
                String result = DBManager.get().queryGetValue("SHOW VARIABLES WHERE `Variable_name` = 'max_connections';", "Value");
                System.out.println(result);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
