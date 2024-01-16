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
        setLayout(new GridLayout(7, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        name = new JTextField();
        add(name);

        JLabel surnameLabel = new JLabel("Surname");
        add(surnameLabel);
        surname = new JTextField();
        add(surname);

        JLabel emailLabel = new JLabel("Email");
        add(emailLabel);
        email = new JTextField();
        add(email);

        JLabel phoneNumberLabel = new JLabel("Phone number");
        add(phoneNumberLabel);
        phoneNumber = new JTextField();
        add(phoneNumber);

        JLabel roleLabel = new JLabel("Role");
        add(roleLabel);
        role = new JComboBox<>(EmployeeRole.values());
        add(role);

        JLabel salaryLabel = new JLabel("Salary");
        add(salaryLabel);
        salary = new JTextField();
        add(salary);

        JButton submit = new JButton("Insert");
        add(submit);
        submit.addActionListener(this);
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
