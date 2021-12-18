import java.sql.*;

public class DBManager {
    private static DBManager singleton = new DBManager();
    public static DBManager get() { return singleton; }

    public static final String DB_HOST = "34.64.164.251";
    public static final String DB_PORT = "3306";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://";
    private static final String DB_CONNECTION_OPTION = "?autoReconnect=true&useSSL=false";
    private static final String DB_USER_NAME = "hinen";
    private static final String DB_USER_PASSWORD = "hinen@7084";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private DBManager() {
        System.out.println("Initializing DBManager...");

        try{
            Class.forName(JDBC_DRIVER);
            connection = null;
            statement = null;
            resultSet = null;
        } catch (Exception ex) {

        }
    }

    public void start() {
        try {
            connection = DriverManager.getConnection(DB_URL + DB_HOST + ":" + DB_PORT + DB_CONNECTION_OPTION, DB_USER_NAME, DB_USER_PASSWORD);
            statement = connection.createStatement();

        } catch (SQLException se) {
            System.out.println(se);
        }
    }
}
