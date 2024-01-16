package Queries.Read;

import QueryHandlers.Filter;
import QueryHandlers.Columns;

import java.sql.*;
import java.util.ArrayList;

public class ReadEmployeeProject implements Read {
    public static ArrayList<ArrayList<Object>> performAction(Connection connection, String tableName, String table1, Columns fields1, String table2, Columns fields2, Filter filters) {
        StringBuilder query = new StringBuilder("SELECT ");
        ArrayList<ArrayList<Object>> data = new ArrayList<>();

        if (fields1.getColumns(table1) != "" && fields2.getColumns(table2) != "") {
            query.append(fields1.getColumns(table1) + ", " + fields2.getColumns(table2) + " FROM " + table1 + " ");
            query.append("JOIN " + tableName + " ON " + table1 + ".id = " + tableName + ".employee_id" + " ");
            query.append("JOIN " + table2 + " ON " + tableName + ".project_id = " + table2 + ".id");

            if (filters.getFilters() != "") {
                query.append(" WHERE " + filters.getFilters() + ";");
                filters.clearFilters();
            } else {
                query.append(";");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
                ResultSetMetaData metaData = preparedStatement.getMetaData();
                ResultSet output = preparedStatement.executeQuery();
                ArrayList<String> fields = new ArrayList<>();
                fields.addAll(fields1.getFormattedColumns());
                fields.addAll(fields2.getFormattedColumns());

                while (output.next()) {
                    int index = 0;
                    for (String field : fields) {
                        System.out.println(metaData.getColumnName(++index) + ": " + output.getObject(field));
                    }

                    System.out.println();
                }

                fields1.clearColumns();
                fields1.clearFormattedColumns();
                fields2.clearColumns();
                fields2.clearFormattedColumns();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }
}
