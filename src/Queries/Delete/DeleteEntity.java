package Queries.Delete;

import QueryHandlers.Filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEntity implements Delete {
    public static void performAction(Connection connection, String tableName, Filter filters) {
        String query = "DELETE FROM " + tableName + " WHERE " + filters.getFilters() + ";";

        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            filters.clearFilters();
        } catch (SQLException e) {
            throw new RuntimeException("No such column in the table");
        } catch (NullPointerException e) {}
    }
}
