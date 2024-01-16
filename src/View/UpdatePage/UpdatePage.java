package View.UpdatePage;

import Database.Database;

import javax.swing.*;
import java.awt.*;

public class UpdatePage extends JPanel {

    Database database;
    private JTabbedPane tables = new JTabbedPane();

    public UpdatePage(Database database) {
        this.database = database;
        setLayout(new BorderLayout());
        tables.addTab("Employees", new UpdateEmployeePage(database));
        tables.addTab("Clients", new UpdateClientPage(database));
        tables.addTab("Projects", new UpdateProjectPage(database));
        tables.add("Assignments", new UpdateAssignmentPage(database));
        add(tables, BorderLayout.CENTER);
    }
}