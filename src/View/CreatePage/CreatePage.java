package View.CreatePage;

import Database.Database;

import javax.swing.*;
import java.awt.*;

public class CreatePage extends JPanel {
    Database database;
    private JTabbedPane tables = new JTabbedPane();

    public CreatePage(Database database) {
        this.database = database;
        setLayout(new BorderLayout());
        tables.addTab("Employees", new CreateEmployeePage(database));
        tables.addTab("Clients", new CreateClientPage(database));
        tables.addTab("Projects", new CreateProjectPage(database));
        tables.add("Assignments", new CreateAssignmentPage(database));
        add(tables, BorderLayout.CENTER);
    }
}
