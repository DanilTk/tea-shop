package util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBPropertiesReader {

    private static final Logger LOGGER = Logger.getLogger("teashop.util.jdbc.DBPropertiesReader");
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";

    public static Properties loadDBProperties() {

        Properties properties = new Properties();

        try (InputStream input = DBPropertiesReader.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE_NAME)) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to read properties file: " + DB_PROPERTIES_FILE_NAME);
        }
        return properties;
    }
}