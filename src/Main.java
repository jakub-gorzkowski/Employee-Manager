import Database.ConnectionManager;
import Database.Database;
import View.Login;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        ConnectionManager connectionManager = new ConnectionManager(database);
        Login.getInstance(database, connectionManager);
    }
}