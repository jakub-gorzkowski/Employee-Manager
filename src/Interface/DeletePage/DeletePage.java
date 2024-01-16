package Interface.DeletePage;

import Database.Model.Database;

import javax.swing.*;
import java.awt.*;

public class DeletePage extends JPanel {

    Database database;
    private JTabbedPane tables = new JTabbedPane();

    public DeletePage(Database database) {
        this.database = database;
        setLayout(new BorderLayout());
        tables.addTab("Employees", new DeleteEmployeePage(database));
        tables.addTab("Clients", new DeleteClientPage(database));
        tables.addTab("Projects", new DeleteProjectPage(database));
        tables.add("Assignments", new DeleteAssignmentPage(database));
        add(tables, BorderLayout.CENTER);
    }
}
