package Queries.Create;

import java.sql.Connection;

public interface Create {
    static void performAction(Connection connection, String tableName, Object entity) {}
}
