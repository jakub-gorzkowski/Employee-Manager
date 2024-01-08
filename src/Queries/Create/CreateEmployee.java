package Queries.Create;

import Entities.Person.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEmployee implements Create {
    public static void performAction(Connection connection, Employee employee) {
        String query = "INSERT INTO " + employee.getTABLE_NAME() + " (name, surname, email, phone_number, role, salary) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getRole());
            preparedStatement.setBigDecimal(6, employee.getSalary());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } catch (NullPointerException e) {}
    }
}
