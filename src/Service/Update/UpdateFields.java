package Service.Update;

import Handler.Filter;
import Handler.Value;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFields {
    public static void performAction(Connection connection, String tableName, Value values, Filter filters) {
        String query = "UPDATE " + tableName + " SET " + values.getUpdates() + " WHERE " + filters.getFilters() + ";";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
                connection.commit();
                filters.clearFilters();
                values.clearUpdates();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to set auto-commit");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to set auto-commit back to true.");
            }
        }
    }
}

