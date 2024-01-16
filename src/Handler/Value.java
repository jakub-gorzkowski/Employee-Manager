package Handler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Value {
    private Map<String, Object> updates;

    public Value() {
        this.updates = new HashMap<>();
    }

    public void addUpdate(String column, Object value) {
        updates.put(column, value);
    }

    public String getUpdates() {
        StringBuilder filters = new StringBuilder();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            if (entry.getValue() instanceof BigDecimal) {
                filters.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
            } else if (entry.getValue() != null) {
                filters.append(entry.getKey()).append(" = '").append(entry.getValue()).append("', ");
            } else {
                filters.append(entry.getKey()).append(" = NULL, ");
            }
        }

        if (!filters.isEmpty()) {
            filters.setLength(filters.length() - 2);
        }

        if (filters != null) {
            return filters.toString();
        } else {
            return "";
        }
    }

    public void clearUpdates() {
        updates.clear();
    }
}
