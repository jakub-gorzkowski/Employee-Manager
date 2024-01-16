package Interface.ReadPage;

import Database.Model.Database;

import javax.swing.*;
import java.awt.*;
public class ReadPage extends JPanel{
    Database database;
    private JTabbedPane tables = new JTabbedPane();

    public ReadPage(Database database) {
        this.database = database;
        setLayout(new BorderLayout());
        tables.addTab("Employees", new ReadEmployeePage(database));
        tables.addTab("Clients", new ReadClientPage(database));
        tables.addTab("Projects", new ReadProjectPage(database));
        tables.add("Assignments", new ReadAssignmentPage(database));
        add(tables, BorderLayout.CENTER);
    }
}
