package Queries.Create;

import Entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateClient implements Create {
    public static void performAction(Connection connection, Client client) {
        String query;

        if (client.getCompany() == null) {
            query = "INSERT INTO " + client.getTABLE_NAME() + " (name, surname, email, phone_number) VALUES (?, ?, ?, ?);";
        } else {
            query = "INSERT INTO " + client.getTABLE_NAME() + " (name, surname, email, phone_number, company) VALUES (?, ?, ?, ?, ?);";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPhoneNumber());

            if (client.getCompany() != null) {
                preparedStatement.setString(5, client.getCompany());
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } catch (NullPointerException e) {}
    }
}
