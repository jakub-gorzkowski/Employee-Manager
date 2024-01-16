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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Employee Id");
        addRow(formPanel, "Project Id");

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton submit = new JButton("Delete");
        mainPanel.add(submit, BorderLayout.SOUTH);
        submit.addActionListener(this);

        add(mainPanel);
    }

    private void addRow(JPanel panel, String label) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JLabel labelComponent = new JLabel(label);
        rowPanel.add(labelComponent);

        switch (label) {
            case "Employee Id":
                employeeId = new JTextField();
                rowPanel.add(employeeId);
                break;
            case "Project Id":
                projectId = new JTextField();
                rowPanel.add(projectId);
                break;
        }
        panel.add(rowPanel);
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
