package Interface.CreatePage;

import Database.Model.Database;
import Entities.Employee.EmployeeRole;
import Entities.Person.Employee;
import Queries.Create.CreateEmployee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CreateEmployeePage extends JPanel implements ActionListener {
    private Database database;
    private JTextField name;
    private JTextField surname;
    private JTextField email;
    private JTextField phoneNumber;
    private JComboBox<EmployeeRole> role;
    private JTextField salary;

    public CreateEmployeePage(Database database) {
        this.database = database;
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Name");
        addRow(formPanel, "Surname");
        addRow(formPanel, "Email");
        addRow(formPanel, "Phone number");
        addRow(formPanel, "Role");
        addRow(formPanel, "Salary");

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
            case "Name":
                name = new JTextField();
                rowPanel.add(name);
                break;
            case "Surname":
                surname = new JTextField();
                rowPanel.add(surname);
                break;
            case "Email":
                email = new JTextField();
                rowPanel.add(email);
                break;
            case "Phone number":
                phoneNumber = new JTextField();
                rowPanel.add(phoneNumber);
                break;
            case "Role":
                role = new JComboBox<>(EmployeeRole.values());
                rowPanel.add(role);
                break;
            case "Salary":
                salary = new JTextField();
                rowPanel.add(salary);
                break;
        }
        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField[] inputFields = {name, surname, email, phoneNumber, salary};
        boolean anyFieldEmpty = false;

        for (JTextField textField : inputFields) {
            if (textField.getText().isEmpty()) {
                anyFieldEmpty = true;
                break;
            }
        }

        if (anyFieldEmpty) {
            JOptionPane.showMessageDialog(this, "Can't create record, missing fields.");
        } else if (salary.getText().contains("-")) {
            JOptionPane.showMessageDialog(this, "Can't create record, invalid arguments.");
            salary.setText("");
        } else {
            try {
                CreateEmployee.performAction(database.getConnection(),
                        new Employee(
                                name.getText(),
                                surname.getText(),
                                email.getText(),
                                phoneNumber.getText(),
                                (EmployeeRole) role.getSelectedItem(),
                                new BigDecimal(salary.getText())
                        )
                );

                JOptionPane.showMessageDialog(this, "Successfully created.");
                name.setText("");
                surname.setText("");
                email.setText("");
                phoneNumber.setText("");
                salary.setText("");
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Can't create record, invalid arguments.");
            }
        }
    }
}
