package resources.DAO;

import util.jdbc.DBConnector;
import util.jdbc.DBPropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionManager {
    private Connection connection;

    public ConnectionManager() {
        init();
    }

    private void init() {
        Properties properties = DBPropertiesReader.loadDBProperties();

        try {
            connection = DBConnector.createDbConnection(properties);
        } catch (SQLException e) {
            //TODO manage exception
        }
    }

    protected Connection getConnection() {
        return connection;
    }
}