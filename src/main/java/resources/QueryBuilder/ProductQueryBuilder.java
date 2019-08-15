package resources.QueryBuilder;

import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductQueryBuilder implements QueryBuilderFunctionality {
    private final String TABLE_NAME = "product";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PreparedStatement buildAddQuery(Product product) throws SQLException {

        String query = "INSERT INTO ? (name, price, measure_name_id, product_category_id) VALUES (?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, TABLE_NAME);
        preparedStatement.setString(2, product.getName());
        preparedStatement.setBigDecimal(3, product.getPrice());
        preparedStatement.setInt(4, MeasureName.getMeasureNameIdByEnum(product.getMeasureName()));
        preparedStatement.setInt(5, ProductCategory.getProductCategoryIdByEnum(product.getProductCategory()));

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildGetAllQuery() throws SQLException {

        String query = "SELECT id, name, price, measure_id, product_category_id FROM ?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, TABLE_NAME);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildUpdateQuery(Long productId, Product newProductParameters) throws SQLException {

        String query = "UPDATE ? SET name=?, price=?, measure_name_id=?, product_category_id=? WHERE id=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, TABLE_NAME);
        preparedStatement.setString(2, newProductParameters.getName());
        preparedStatement.setBigDecimal(3, newProductParameters.getPrice());
        preparedStatement.setInt(4, MeasureName.getMeasureNameIdByEnum(newProductParameters.getMeasureName()));
        preparedStatement.setInt(5, ProductCategory.getProductCategoryIdByEnum(newProductParameters.getProductCategory()));
        preparedStatement.setLong(6, productId);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildDeleteQuery(Long productId) throws SQLException {

        String query = "DELETE FROM ? WHERE id=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, TABLE_NAME);
        preparedStatement.setLong(2, productId);
        return preparedStatement;
    }
}