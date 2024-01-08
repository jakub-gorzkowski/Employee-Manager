package Database.Model;

import java.sql.Connection;

public class Database {
    private final String name = "Company_HR";
    private final String port = "5432";
    Connection connection = null;
    private static Database database = null;

    private Database() {}

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public String getName() {
        return name;
    }

    public String getPort() {
        return port;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "Database.Model.Database{" +
                "name='" + name + '\'' +
                ", port='" + port + '\'' +
                ", connection=" + connection +
                '}';
    }
}
