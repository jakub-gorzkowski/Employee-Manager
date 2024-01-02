import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username, password;
        Database database = Database.getInstance();
        ConnectionManager connectionManager = new ConnectionManager(database);

        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        password = scanner.nextLine();

        connectionManager.connect(username, password);
        System.out.println(database);

        connectionManager.disconnect();
        System.out.println(database);
    }
}