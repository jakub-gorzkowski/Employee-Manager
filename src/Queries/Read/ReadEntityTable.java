package Queries.Read;

import QueryHandlers.Field;
import QueryHandlers.Filter;

import java.sql.*;

public class ReadEntityTable implements Read {
    public static void performAction(Connection connection, String tableName, Field fields, Filter filters) {
        String query;

        if (fields.getFields() == "" && filters.getFilters() == "") {
            query = "SELECT * FROM " + tableName + ";";
        } else if (fields.getFields() == "") {
            query = "SELECT * FROM " + tableName + " WHERE " + filters.getFilters() + ";";
        } else if (filters.getFilters() == "") {
            query = "SELECT " + fields.getFields() + " FROM " + tableName + ";";
        } else {
            query = "SELECT " + fields.getFields() + " FROM " + tableName + " WHERE " + filters.getFilters() + ";";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            ResultSet output = preparedStatement.executeQuery();
            int index;

            if (fields.getFields() != "") {
                while (output.next()) {
                    index = 1;
                    for (String field : fields.getFieldList()) {
                        System.out.println(metaData.getColumnName(index++) + ": " + output.getObject(field));
                    }
                    System.out.println();
                }
            } else {
                while (output.next()) {
                    for (index = 1; index <= output.getMetaData().getColumnCount(); index++) {
                        System.out.println(metaData.getColumnName(index) + ": " + output.getObject(index));
                    }
                    System.out.println();
                }
            }

            fields.clearFields();
            filters.clearFilters();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
