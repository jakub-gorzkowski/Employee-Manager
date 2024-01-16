package Interface.CreatePage;

import Database.Model.Database;
import Entities.Employee.EmployeeProject;
import Queries.Create.CreateEmployeeProject;

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
        setLayout(new GridLayout(4, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel employeeIdLabel = new JLabel("Employee ID");
        add(employeeIdLabel);
        employeeId = new JTextField();
        add(employeeId);

        JLabel projectIdLabel = new JLabel("Project ID");
        add(projectIdLabel);
        projectId = new JTextField();
        add(projectId);

        JButton submit = new JButton("Insert");
        add(submit);
        submit.addActionListener(this);
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
            CreateEmployeeProject.performAction(database.getConnection(),
                    new EmployeeProject(
                            Integer.parseInt(employeeId.getText()),
                            Integer.parseInt(projectId.getText())
                    )
            );
            JOptionPane.showMessageDialog(this, "Successfully created.");
            employeeId.setText("");
            projectId.setText("");
        }
    }
}
