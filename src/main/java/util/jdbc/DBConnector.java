package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static final String SSL_FALSE = "?useSSL=false";
    private static final String SUBPROTOCOL = "jdbc:mysql://";

    public static Connection createDbConnection(Properties properties) throws SQLException {

        String dbName = properties.getProperty("db_name");
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String dbUrl = buildDbUrl(host, port, dbName);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(dbUrl, user, password);
    }

    private static String buildDbUrl(String host, String port, String dbName) {

        return new StringBuilder()
                .append(SUBPROTOCOL)
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(dbName)
                .append(SSL_FALSE)
                .toString();
    }
}