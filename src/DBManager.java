import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<Map<String, String>> query(String sql) {
        ArrayList<Map<String, String>> resultList = new ArrayList<>();

        try {
            if (isConnectionClosed())
                connectToDB();

            // ???
            if (isConnectionClosed())
                return resultList;

            resultSet = statement.executeQuery(sql);

            if (resultSet != null) {
                while (resultSet.next()) {
                    Map<String, String> resultMap = new HashMap<>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                        resultMap.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));

                    resultList.add(resultMap);
                }
            }
        } catch (SQLException se) {
            connection = null;
            statement = null;
        }

        return resultList;
    }

    public ArrayList<String> query(String sql, String column) {
        ArrayList<Map<String, String>> resultList = query(sql);
        if (resultList == null)
            return null;

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++)
            result.add(resultList.get(i).get(column));

        return result;
    }

    public String queryGetValue(String sql, String column) {
        ArrayList<String> resultList = query(sql, column);
        if (resultList == null || resultList.isEmpty())
            return null;

        return resultList.get(0);
    }
}
