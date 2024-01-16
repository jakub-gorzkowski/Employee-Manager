package Queries.Create;

import Entities.Employee.EmployeeProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEmployeeProject implements Create {
    public static void performAction(Connection connection, EmployeeProject employeeProject) {
        String query = "INSERT INTO " + employeeProject.getTABLE_NAME() + " (employee_id, project_id) VALUES (?, ?);";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, employeeProject.getEmployeeId());
                preparedStatement.setInt(2, employeeProject.getProjectId());

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
                throw new RuntimeException("Unable to set auto-commit back to true.");
            }
        }
    }
}
