package Data;

public class Constants {
    public static class SMTPConfig {
        public static final String MAIL_USER = "hinen.monitoring@gmail.com";
        public static final String MAIL_PASSWORD = "eHhtbmhkZmtiZXNhdWFyaA==";
        public static final String SMTP_TYPE = "smtp.gmail.com";
        public static final String TLS_VERSION = "TLSv1.2";
        public static final int SMTP_PORT = 587;
    }

    public static class DBConfig {
        public static final String DB_HOST = "34.64.164.251";
        public static final String DB_PORT = "3306";
        public static final String DB_USER_NAME = "hinen";
        public static final String DB_USER_PASSWORD = "aGluZW5ANzA4NA==";
        public static final String DB_CONNECTION_OPTION = "autoReconnect=true&useSSL=false";
    }

    public static class Query {
        public static final String SHOW_VARIABLES = "SHOW VARIABLES";
        public static final String SELECT_STUDENT = "SELECT * FROM mobile_system.student";
        public static final String SET_MAX_CONNECTIONS_140 = "SET GLOBAL max_connections = 140";
        public static final String SET_MAX_CONNECTIONS_150 = "SET GLOBAL max_connections = 150";
        public static final String INSERT_STUDENT = "INSERT INTO mobile_system.student(`id`, `name`, `age`) VALUES(20192762, '박수빈', 25)";
        public static final String REMOVE_STUDENT = "DELETE FROM mobile_system.student WHERE `id` = 20192762";
    }

    public static class StatusKey {
        public static final String MAX_CONNECTIONS = "max_connections";
    }

    public static class SMTPTarget {
        // 모니터링 메일을 받을 이메일을 해당 배열에 추가해주세요.
        public static final String[] MAIL_RECEIVER = {
                "qkrqldtjqj@gmail.com",
                "kanghee.lee@ssu.ac.kr"
        };
    }

    public static class MonitoringType {
        public static final int SERVER_ERROR = 1;
        public static final int DB_EXECUTE_QUERY_FAIL = 2;
        public static final int MONITORING_EXCEPTION = 3;
        public static final int SMTP_EXCEPTION = 4;
        public static final int MAX_CONNECTION_ERROR = 5;
        public static final int MAX_CONNECTION_NORMAL = 6;
        public static final int NEW_STUDENT = 7;
        public static final int REMOVE_STUDENT = 8;

        public static String getMonitoringTypeStr(int type) {
            if (type == SERVER_ERROR)
                return "Server Status";
            else if (type == DB_EXECUTE_QUERY_FAIL)
                return "Execute Query Fail";
            else if (type == MONITORING_EXCEPTION)
                return "Monitoring Exception";
            else if (type == SMTP_EXCEPTION)
                return "SMTP Exception";
            else if (type == MAX_CONNECTION_ERROR)
                return "Max Connection Error";
            else if (type == MAX_CONNECTION_NORMAL)
                return "Max Connection Normal";
            else if (type == NEW_STUDENT)
                return "New Student";
            else if (type == REMOVE_STUDENT)
                return "Remove Student";

            return "";
        }
    }
}
