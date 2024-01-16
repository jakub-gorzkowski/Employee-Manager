package Service.Delete;

import java.sql.Connection;

public interface Delete {
    static void performAction(Connection connection, String tableName, String filters) {}
}
