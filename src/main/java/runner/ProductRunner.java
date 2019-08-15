package runner;

import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ProductRunner {
    public static void main(String[] args) throws SQLException {
        Product product = new Product("f", BigDecimal.valueOf(5), MeasureName.GRAM, ProductCategory.TEA);
        System.out.println(product.getClass().isInstance(Product.class));
    }
}