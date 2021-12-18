import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DBManager.get();

        new Thread(() -> {
            while (true) {
                ArrayList<Map<String, String>> result = DBManager.get().query("SHOW VARIABLES;");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
