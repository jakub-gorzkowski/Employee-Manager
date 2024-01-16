package Interface.DeletePage;

import Database.Model.Database;
import Entities.Employee.EmployeeProject;
import Queries.Delete.DeleteEntity;
import QueryHandlers.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAssignmentPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField employeeId;
    private JTextField projectId;

    public DeleteAssignmentPage(Database database) {
        this.database = database;
        setLayout(new GridLayout(7, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel employeeIdLabel = new JLabel("Employee ID");
        add(employeeIdLabel);
        employeeId = new JTextField();
        add(employeeId);

        JLabel projectIdLabel = new JLabel("Project ID");
        add(projectIdLabel);
        projectId = new JTextField();
        add(projectId);

        JButton submit = new JButton("Delete");
        add(submit);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Filter filter = new Filter();

        if (!employeeId.getText().isEmpty()) {
            filter.addFilter("employee_id", Integer.parseInt(employeeId.getText()));
        }

        if (!projectId.getText().isEmpty()) {
            filter.addFilter("project_id", Integer.parseInt(projectId.getText()));
        }

        try {
            DeleteEntity.performAction(database.getConnection(), EmployeeProject.getTABLE_NAME(), filter);
            JOptionPane.showMessageDialog(this, "Successfully removed.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        employeeId.setText("");
        projectId.setText("");
    }
}
