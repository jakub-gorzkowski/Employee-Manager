package Queries.Delete;

import QueryHandlers.Filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEntity implements Delete {
    public static void performAction(Connection connection, String tableName, Filter filters) {
        String query = "DELETE FROM " + tableName + " WHERE " + filters.getFilters() + ";";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
                filters.clearFilters();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("No such column in the table");
            } catch (NullPointerException e) {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to set auto-commit back to true.");
            }
        }
    }
}
