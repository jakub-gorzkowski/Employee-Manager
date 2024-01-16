package View.UpdatePage;

import Database.Database;
import Model.Employee.EmployeeProject;
import Service.Update.UpdateFields;
import Handler.Filter;
import Handler.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAssignmentPage extends JPanel implements ActionListener {

    private Database database;
    private JTextField employeeIdFilter, projectIdFilter;
    private JTextField employeeIdUpdate, projectIdUpdate;

    public UpdateAssignmentPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Project ID Filter");
        addRow(formPanel, "Project ID Update");
        addRow(formPanel, "Employee ID Filter");
        addRow(formPanel, "Employee ID Update");

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton submit = new JButton("Update");
        mainPanel.add(submit, BorderLayout.SOUTH);
        submit.addActionListener(this);

        add(mainPanel);
    }

    private void addRow(JPanel panel, String label) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel labelComponent = new JLabel(label);
        rowPanel.add(labelComponent);

        switch (label) {
            case "Employee ID Filter":
                employeeIdFilter = new JTextField();
                rowPanel.add(employeeIdFilter);
                break;
            case "Project ID Filter":
                projectIdFilter = new JTextField();
                rowPanel.add(projectIdFilter);
                break;
            case "Employee ID Update":
                employeeIdUpdate = new JTextField();
                rowPanel.add(employeeIdUpdate);
                break;
            case "Project ID Update":
                projectIdUpdate = new JTextField();
                rowPanel.add(projectIdUpdate);
                break;
        }

        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Value value = new Value();
        Filter filter = new Filter();

        if (!projectIdFilter.getText().isEmpty()) {
            filter.addFilter("project_id", projectIdFilter.getText());
        }

        if (!projectIdUpdate.getText().isEmpty()) {
            value.addUpdate("project_id", projectIdUpdate.getText());
        }

        if (!employeeIdFilter.getText().isEmpty()) {
            filter.addFilter("employee_id", employeeIdFilter.getText());
        }

        if (!employeeIdUpdate.getText().isEmpty()) {
            value.addUpdate("employee_id", employeeIdUpdate.getText());
        }

        try {
            UpdateFields.performAction(database.getConnection(), EmployeeProject.getTABLE_NAME(), value, filter);
            JOptionPane.showMessageDialog(this, "Updated successfully.");
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        projectIdUpdate.setText("");
        projectIdFilter.setText("");
        employeeIdUpdate.setText("");
        employeeIdFilter.setText("");
    }
}