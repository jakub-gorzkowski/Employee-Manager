package View.CreatePage;

import Database.Database;
import Model.Employee.EmployeeProject;
import Service.Create.CreateEmployeeProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAssignmentPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField employeeId;
    private JTextField projectId;

    public CreateAssignmentPage(Database database) {
        this.database = database;
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Employee Id");
        addRow(formPanel, "Project Id");

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton submit = new JButton("Insert");
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
        JTextField[] inputFields = {employeeId, projectId};
        boolean requiredFieldEmpty = false;

        for (JTextField textField : inputFields) {
            if (textField.getText().isEmpty()) {
                requiredFieldEmpty = true;
                break;
            }
        }

        if (requiredFieldEmpty) {
            JOptionPane.showMessageDialog(this, "Can't create record, missing required fields.");
        } else {
            try {
                CreateEmployeeProject.performAction(database.getConnection(),
                        new EmployeeProject(
                                Integer.parseInt(employeeId.getText()),
                                Integer.parseInt(projectId.getText())
                        )
                );
                JOptionPane.showMessageDialog(this, "Successfully created.");
                employeeId.setText("");
                projectId.setText("");
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(this, "Record already exists.");
            }
        }
    }
}
