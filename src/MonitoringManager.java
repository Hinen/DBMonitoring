import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MonitoringManager {
    private static MonitoringManager singleton = new MonitoringManager();
    public static MonitoringManager get() { return singleton; }

    private SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();

    private MonitoringManager() {
        System.out.println("Initializing MonitoringManager...");
    }

    public void start() {
        System.out.println("----------\nCHECK TIME : " + getNowTime());

        checkVariables();
    }

    public String getNowTime() {
        date.setTime(System.currentTimeMillis());
        return dayTime.format(date);
    }

    private void checkVariables() {
        ArrayList<Map<String, String>> variables = DBManager.get().query(Constants.Query.SHOW_VARIABLES);
        if (variables == null)
            return;

        for (Map<String, String> map : variables) {
            if (map.get("Variable_name").equals("max_connections"))
                checkMaxConnections(map);
        }
    }

    private void checkMaxConnections(Map<String, String> map) {
        Integer value = Integer.parseInt(map.get("Value"));
        if (value == null)
            return;

        if (value < 150)
            System.out.println("warning : " + value);
    }
}
