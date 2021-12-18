import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MonitoringManager {
    private static MonitoringManager singleton = new MonitoringManager();
    public static MonitoringManager get() { return singleton; }

    private SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();

    private Map<String, String> statusMap = new HashMap<>();

    private MonitoringManager() {
        System.out.println("Initializing MonitoringManager...");

        statusMap.put(Constants.StatusKey.MAX_CONNECTIONS, "150");
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
            if (map.get("Variable_name").equals(Constants.StatusKey.MAX_CONNECTIONS))
                checkMaxConnections(map);
        }
    }

    private void checkMaxConnections(Map<String, String> map) {
        String valueStr = map.get("Value");
        Integer value = Integer.parseInt(valueStr);
        if (value == null)
            return;

        int beforeValue = Integer.parseInt(statusMap.get(Constants.StatusKey.MAX_CONNECTIONS));
        if (beforeValue >= 150 && value < 150) {
            System.out.println("warning : " + value);

            // TODO SEND MAIL
        }

        statusMap.put(Constants.StatusKey.MAX_CONNECTIONS, valueStr);
    }
}
