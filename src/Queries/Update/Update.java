package Queries.Update;

import java.sql.Connection;

public interface Update {
    static void performAction(Connection connection, Object entity, String filters) {}
}
