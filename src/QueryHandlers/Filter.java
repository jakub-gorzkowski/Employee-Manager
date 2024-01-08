package QueryHandlers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Filter {
    private Map<String, Object> restrictions;

    public Filter() {
        this.restrictions = new HashMap<>();
    }

    public void addFilter(String column, Object value) {
        restrictions.put(column, value);
    }

    public void addFilter(String table, String column, Object value) {
        restrictions.put(table + "." + column, value);
    }

    public String getFilters() {
        StringBuilder filters = new StringBuilder();

        for (Map.Entry<String, Object> entry : restrictions.entrySet()) {
            if (entry.getValue() instanceof BigDecimal) {
                filters.append(entry.getKey()).append(" = ").append(entry.getValue().toString()).append(" AND ");
            } else if (entry.getValue() != null) {
                filters.append(entry.getKey()).append(" = '").append(entry.getValue().toString()).append("' AND ");
            } else {
                filters.append(entry.getKey()).append(" = NULL AND ");
            }
        }

        if (!filters.isEmpty()) {
            filters.setLength(filters.length() - 5);
        }

        if (filters != null) {
            return filters.toString();
        } else {
            return "";
        }
    }

    public void clearFilters() {
        restrictions.clear();
    }
}
