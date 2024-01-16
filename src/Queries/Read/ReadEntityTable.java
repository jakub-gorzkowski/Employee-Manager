package Queries.Read;

import QueryHandlers.Field;
import QueryHandlers.Filter;

import java.sql.*;
import java.util.ArrayList;

public class ReadEntityTable implements Read {
    public static ArrayList<ArrayList<Object>> performAction(Connection connection, String tableName, Field fields, Filter filters) {
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
            ArrayList<ArrayList<Object>> data = new ArrayList<>();
            data.add(new ArrayList<>());

            if (fields.getFields() != "") {
                for (String field : fields.getFieldList()) {
                    data.get(0).add(field);
                }

                while (output.next()) {
                    ArrayList<Object> row = new ArrayList<>();
                    for (String field : fields.getFieldList()) {
                        row.add(output.getObject(field));
                    }
                    data.add(row);
                }
            } else {
                for (int index = 1; index <= metaData.getColumnCount(); index++) {
                    data.get(0).add(metaData.getColumnName(index));
                }

                while (output.next()) {
                    ArrayList<Object> row = new ArrayList<>();
                    for (int index = 1; index <= metaData.getColumnCount(); index++) {
                        row.add(output.getObject(index));
                    }
                    data.add(row);
                }
            }

            fields.clearFields();
            filters.clearFilters();
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}