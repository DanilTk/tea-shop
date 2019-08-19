package resources.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnumQueryBuilder {
    private Connection connection;

    public EnumQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement buildGetMeasureNamesQuery() throws SQLException {
        String query = "SELECT id, measure_name FROM measure_name";
        return connection.prepareStatement(query);
    }

    public PreparedStatement buildGetProductCategoriesQuery() throws SQLException {
        String query = "SELECT id, product_category FROM product_category";
        return connection.prepareStatement(query);
    }
}