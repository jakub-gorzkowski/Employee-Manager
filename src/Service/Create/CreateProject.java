package Service.Create;

import Model.Project.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateProject implements Create {
    public static void performAction(Connection connection, Project project) {
        String query = "INSERT INTO " + project.getTABLE_NAME() + " (name, client_id, is_active) VALUES (?, ?, ?);";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, project.getName());
                preparedStatement.setInt(2, project.getClientId());
                preparedStatement.setBoolean(3, project.isActive());

                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException();
            } catch (NullPointerException e) {
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to set auto-commit.");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to set auto-commit back to true");
            }
        }
    }
}
