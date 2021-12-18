import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoringManager {
    private static MonitoringManager singleton = new MonitoringManager();

    private SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();

    public static MonitoringManager get() {
        return singleton;
    }

    private MonitoringManager() {
        System.out.println("Initializing MonitoringManager...");
    }

    public void start() {
        System.out.println("----------\nCHECK TIME : " + getNowTime());
    }

    public String getNowTime() {
        date.setTime(System.currentTimeMillis());
        return dayTime.format(date);
    }
}
