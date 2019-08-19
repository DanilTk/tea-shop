package resources.DAO;

import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;
import resources.QueryBuilder.ProductQueryBuilder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends ConnectionManager implements DAO<Product> {
    private ProductQueryBuilder productQueryBuilder = new ProductQueryBuilder(super.getConnection());

    @Override
    public Product add(Product product) {

        try {
            PreparedStatement preparedStatement = productQueryBuilder.buildAddQuery(product);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO: handle exception and add logger
        }
        return product;
    }

    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = productQueryBuilder.buildGetAllQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("price"));
                MeasureName measureName = MeasureName.valueOf(resultSet.getString("measure_name"));
                ProductCategory productCategory = ProductCategory.valueOf(resultSet.getString("product_category"));
                Product product = new Product(id, name, price, measureName, productCategory);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO: handle exception and add logger
        }
        return products;
    }

    @Override
    public Product update(Integer id, Product newProductParameters) {

        try {
            PreparedStatement preparedStatement = productQueryBuilder.buildUpdateQuery(id, newProductParameters);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO: handle exception and add logger
        }
        return newProductParameters;
    }

    @Override
    public Integer delete(Integer id) {

        try {
            PreparedStatement preparedStatement = productQueryBuilder.buildDeleteQuery(id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO: handle exception and add logger
        }
        return id;
    }
}