package Database;

import Database.Database;
import org.postgresql.util.PSQLException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private Database database;
    private String url;

    public ConnectionManager(Database database) {
        this.database = database;
        this.url = "jdbc:postgresql://localhost:" + database.getPort() + "/" + database.getName();
    }

    public void connect(String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            database.setConnection(DriverManager.getConnection(url, username, password));

            if (database.getConnection() != null) {
                System.out.println("Connected");
            }
        } catch (PSQLException e) {
            System.out.println("Invalid username or password");
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (database.getConnection() != null && !database.getConnection().isClosed()) {
                database.getConnection().close();
                database.setConnection(null);
                System.out.println("Disconnected");
            }
        } catch (SQLException e) {

        }
    }
}
