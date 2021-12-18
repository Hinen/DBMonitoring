import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonitoringManager {
    private static MonitoringManager singleton = new MonitoringManager();
    public static MonitoringManager get() { return singleton; }

    private Map<String, String> statusMap = new HashMap<>();

    private MonitoringManager() {
        System.out.println("Initializing MonitoringManager...");

        statusMap.put(Constants.StatusKey.MAX_CONNECTIONS, "150");
    }

    public void start() {
        System.out.println("----------\nCHECK TIME : " + DateManager.get().getNowTime());

        checkVariables();
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

            SMTPManager.get().addMail(
                    "DB Max Connection에 문제 발생_" + DateManager.get().getNowTime(),
                    "Monitoring DB Host : " + Constants.DBConfig.DB_HOST + "\n" +
                            "Check Time : " + DateManager.get().getNowTime() + "\n\n" +
                            "Before MaxConnection : " + beforeValue + "\n" +
                            "After MaxConnection : " + value + "\n\n" +
                            "위와 같이 MaxConnection 값에 문제가 발생했으므로 모니터링 결과를 공유합니다.",
                    Constants.MonitoringType.MAX_CONNECTION
            );
        }

        statusMap.put(Constants.StatusKey.MAX_CONNECTIONS, valueStr);
    }
}
