package resources.QueryBuilder;

import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductQueryBuilder implements QueryBuilder<Product> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private EnumQueryBuilder enumQueryBuilder;

    public ProductQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PreparedStatement buildAddQuery(Product product) throws SQLException {
        String query = "INSERT INTO product (name, price, measure_name_id, product_category_id) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setBigDecimal(2, product.getPrice());
        preparedStatement.setInt(3, mapMeasureName(product.getMeasureName()));
        preparedStatement.setInt(4, mapProductCategory(product.getProductCategory()));

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildGetAllQuery() throws SQLException {
        String query = "SELECT product.id, name, price, measure_name, product_category FROM product" +
                "LEFT JOIN measure_name ON product.id=measure_name.id" +
                "LEFT JOIN product_category ON product.id=product_category.id";

        return connection.prepareStatement(query);
    }

    @Override
    public PreparedStatement buildUpdateQuery(Integer id, Product newProductParameters) throws SQLException {
        String query = "UPDATE product SET name=?, price=?, measure_name_id=?, product_category_id=? WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newProductParameters.getName());
        preparedStatement.setBigDecimal(2, newProductParameters.getPrice());
        preparedStatement.setInt(3, mapMeasureName(newProductParameters.getMeasureName()));
        preparedStatement.setInt(4, mapProductCategory(newProductParameters.getProductCategory()));
        preparedStatement.setInt(5, id);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildDeleteQuery(Integer id) throws SQLException {

        String query = "DELETE FROM product WHERE id=?";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }

    private Integer mapMeasureName(MeasureName measureName) throws SQLException { //TODO: handle exception, make method static
        enumQueryBuilder = new EnumQueryBuilder(connection);
        ResultSet availableMeasureNames = enumQueryBuilder.buildGetMeasureNamesQuery().executeQuery();
        Map<MeasureName, Integer> enumsMap = new HashMap<>();
        while (availableMeasureNames.next()) {
            MeasureName measure = MeasureName.valueOf(availableMeasureNames.getString(0));
            Integer id = availableMeasureNames.getInt(1);
            enumsMap.put(measure, id);
        }
        return enumsMap.get(measureName);
    }

    private Integer mapProductCategory(ProductCategory productCategory) throws SQLException { //TODO: handle exception, make method static
        enumQueryBuilder = new EnumQueryBuilder(connection);
        ResultSet availableProductCategories = enumQueryBuilder.buildGetProductCategoriesQuery().executeQuery();
        Map<ProductCategory, Integer> enumsMap = new HashMap<>();
        while (availableProductCategories.next()) {
            ProductCategory category = ProductCategory.valueOf(availableProductCategories.getString(0));
            Integer id = availableProductCategories.getInt(1);
            enumsMap.put(category, id);
        }
        return enumsMap.get(productCategory);
    }
}