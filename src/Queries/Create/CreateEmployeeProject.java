package Queries.Create;

import Entities.EmployeeProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEmployeeProject implements Create {
    public static void performAction(Connection connection, EmployeeProject employeeProject) {
        String query = "INSERT INTO " + employeeProject.getTABLE_NAME() + " (employee_id, project_id) VALUES (?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeProject.getEmployeeId());
            preparedStatement.setInt(2, employeeProject.getProjectId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } catch (NullPointerException e) {}
    }
}
