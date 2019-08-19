package resources.QueryBuilder;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ClientQueryBuilder implements QueryBuilder<Client> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ClientQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PreparedStatement buildAddQuery(Client client) throws SQLException {
        String query = "INSERT INTO client (first_name, last_name, creation_timestamp, deletion_timestamp) VALUES (?, ?, ?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(client.getCreationTimestamp()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getDeletionTimestamp()));

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildGetAllQuery() throws SQLException {
        String query = "SELECT id, first_name, last_name, creation_timestamp, deletion_timestamp FROM cllient";

        return connection.prepareStatement(query);
    }

    @Override
    public PreparedStatement buildUpdateQuery(Integer id, Client newClientParameters) throws SQLException {
        String query = "UPDATE client SET first_name=?, last_name=?, deletion_timestamp=? WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newClientParameters.getFirstName());
        preparedStatement.setString(2, newClientParameters.getLastName());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(newClientParameters.getDeletionTimestamp()));
        preparedStatement.setInt(5, id);

        return preparedStatement;
    }

    @Override
    public PreparedStatement buildDeleteQuery(Integer id) throws SQLException {
        String query = "DELETE FROM client WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }
}