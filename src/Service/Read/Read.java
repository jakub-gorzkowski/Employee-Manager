package Service.Read;

import java.sql.Connection;

public interface Read {
    static void performAction(Connection connection, Object entity, String filters) {}
}
