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

public class DAOProduct extends DAO implements DAOFunctionality<Product> {
    private ProductQueryBuilder productQueryBuilder = new ProductQueryBuilder(super.getConnection()); //TODO: Robert to advise if this piece is needed or can workaround?

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
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                BigDecimal price = BigDecimal.valueOf(resultSet.getDouble("price"));
                MeasureName measureName = MeasureName.getMeasureNameEnumById(resultSet.getInt("measure_name_id"));
                ProductCategory productCategory = ProductCategory.getProductCategoryEnumById(resultSet.getInt("product_category_id"));
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
    public Product update(Long id, Product newProductParameters) {

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
    public Long delete(Long id) {

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