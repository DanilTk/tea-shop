package resources.QueryBuilder;

import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductQueryBuilder implements QueryBuilder {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductQueryBuilder(Connection connection) {
        this.connection = connection;
    } //TODO: Robert to advise if can workaround constructor? how to pass connection I want to use?

    @Override
    public PreparedStatement buildAddQuery(Product product) throws SQLException {

        String query = "INSERT INTO product (name, price, measure_name_id, product_category_id) VALUES (?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setBigDecimal(2, product.getPrice());
        preparedStatement.setInt(3, MeasureName.getMeasureNameIdByEnum(product.getMeasureName()));
        preparedStatement.setInt(4, ProductCategory.getProductCategoryIdByEnum(product.getProductCategory()));

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildGetAllQuery() throws SQLException {

        String query = "SELECT id, name, price, measure_name_id, product_category_id FROM product";

        preparedStatement = connection.prepareStatement(query);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildUpdateQuery(Integer productId, Product newProductParameters) throws SQLException {

        String query = "UPDATE product SET name=?, price=?, measure_name_id=?, product_category_id=? WHERE id=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newProductParameters.getName());
        preparedStatement.setBigDecimal(2, newProductParameters.getPrice());
        preparedStatement.setInt(3, MeasureName.getMeasureNameIdByEnum(newProductParameters.getMeasureName()));
        preparedStatement.setInt(4, ProductCategory.getProductCategoryIdByEnum(newProductParameters.getProductCategory()));
        preparedStatement.setInt(5, productId);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildDeleteQuery(Integer productId) throws SQLException {

        String query = "DELETE FROM product WHERE id=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);

        return preparedStatement;
    }
}