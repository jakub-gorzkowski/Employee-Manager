package Queries.Delete;

import QueryHandlers.Filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEntity implements Delete {
    public static void performAction(Connection connection, String tableName, Filter filters) {
        String query = "DELETE FROM " + tableName + " WHERE " + filters.toString() + ";";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(query);
            preparedStatement.executeUpdate();
            filters.clearFilters();
        } catch (SQLException e) {
            System.out.println(query);
            throw new RuntimeException("No such column in the table");
        } catch (NullPointerException e) {}
    }
}
