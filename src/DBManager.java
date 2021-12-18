import java.sql.*;

public class DBManager {
    private static DBManager singleton = new DBManager();
    public static DBManager get() { return singleton; }

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://";

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void start() {
        try {
            if (isConnectionClosed())
                connectToDB();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private boolean isConnectionClosed() throws SQLException {
        return connection == null ||
                connection.isClosed() ||
                statement == null ||
                statement.isClosed();
    }

    private void connectToDB() throws SQLException {
        connection = DriverManager.getConnection(
                DB_URL +
                    Constants.DBConfig.DB_HOST +
                    ":" +
                    Constants.DBConfig.DB_PORT +
                    "?" +
                    Constants.DBConfig.DB_CONNECTION_OPTION,
                Constants.DBConfig.DB_USER_NAME,
                Constants.DBConfig.DB_USER_PASSWORD);

        statement = connection.createStatement();
    }
}
