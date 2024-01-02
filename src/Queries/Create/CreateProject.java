package Queries.Create;

import Entities.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateProject implements Create {
    public static void performAction(Connection connection, Project project) {
        String query = "INSERT INTO " + project.getTABLE_NAME() + " (name, client_id, is_active) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getClientId());
            preparedStatement.setBoolean(3, project.isActive());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } catch (NullPointerException e) {}
    }
}
