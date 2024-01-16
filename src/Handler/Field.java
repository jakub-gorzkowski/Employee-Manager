package Handler;

import java.util.ArrayList;

public class Field {
    private ArrayList<String> columns;

    public Field() {
        this.columns = new ArrayList<>();
    }

    public void addField(String column) {
        columns.add(column);
    }

    public String getFields() {
        StringBuilder fields = new StringBuilder();

        for (String field: columns) {
            fields.append(field).append(", ");
        }

        if (!fields.isEmpty()) {
            fields.setLength(fields.length() - 2);
        }

        return fields.toString();
    }

    public ArrayList<String> getFieldList() {
        return columns;
    }

    public void clearFields() {
        columns.clear();
    }
}
