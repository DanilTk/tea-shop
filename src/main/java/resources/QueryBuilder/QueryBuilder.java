package resources.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryBuilder<T> {

    PreparedStatement buildAddQuery(T instance) throws SQLException;

    PreparedStatement buildGetAllQuery() throws SQLException;

    PreparedStatement buildUpdateQuery(Integer id, T newParameters) throws SQLException;

    PreparedStatement buildDeleteQuery(Integer id) throws SQLException;
}