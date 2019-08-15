package resources.DAO;

import util.jdbc.DBConnector;
import util.jdbc.DBPropertiesReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAO {

    private Connection connection;

    public DAO() {
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

    public Connection getConnection() {
        return connection;
    }
}