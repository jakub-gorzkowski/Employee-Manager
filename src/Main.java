import Database.Managers.ConnectionManager;
import Database.Model.Database;
import Interface.Login;
import QueryHandlers.Filter;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        ConnectionManager connectionManager = new ConnectionManager(database);

        new Login(database, connectionManager);
    }
}