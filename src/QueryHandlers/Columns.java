package QueryHandlers;

import java.util.ArrayList;

public class Columns {
    private ArrayList<String> columns;
    private ArrayList<String> formattedColumns;

    public Columns() {
        this.columns = new ArrayList<>();
        this.formattedColumns = new ArrayList<>();
    }

    public void addColumn(String column) {
        columns.add(column);
    }

    public void addFormattedColumn(String column) {
        formattedColumns.add(column);
    }

    public String getColumns(String tableLabel) {
        StringBuilder fields = new StringBuilder();
        StringBuilder tableName = new StringBuilder(tableLabel);

        if (tableLabel.length() > 1 && tableLabel.charAt(tableLabel.length() - 1) == 's') {
            tableName.setLength(tableName.length() - 1);
        }

        for (String field: columns) {
            fields.append(tableLabel + "." + field).append(" AS ").append(tableName + "_" + field).append(", ");

            if (!this.formattedColumns.contains(tableName + "_" + field)) {
                this.addFormattedColumn(tableName + "_" + field);
            }
        }

        if (!fields.isEmpty()) {
            fields.setLength(fields.length() - 2);
        }

        return fields.toString();
    }

    public ArrayList<String> getColumnsList() {
        return columns;
    }

    public ArrayList<String> getFormattedColumns() {
        return formattedColumns;
    }

    public void clearColumns() {
        columns.clear();
    }

    public void clearFormattedColumns() {
        formattedColumns.clear();
    }
}
