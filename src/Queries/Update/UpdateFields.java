package Queries.Update;

import QueryHandlers.Filter;
import QueryHandlers.Value;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFields {
    public static void performAction(Connection connection, String tableName, Value values, Filter filters) {
        String query = "UPDATE " + tableName + " SET " + values.getUpdates() + " WHERE " + filters.getFilters() + ";";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            filters.clearFilters();
            values.clearUpdates();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {}
    }
}

