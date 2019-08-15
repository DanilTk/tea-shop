package resources.QueryBuilder;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryBuilderFunctionality {

    PreparedStatement buildAddQuery(Product product) throws SQLException;

    PreparedStatement buildGetAllQuery() throws SQLException;

    PreparedStatement buildUpdateQuery(Long productId, Product newProductParameters) throws SQLException;

    PreparedStatement buildDeleteQuery(Long productId) throws SQLException;
}