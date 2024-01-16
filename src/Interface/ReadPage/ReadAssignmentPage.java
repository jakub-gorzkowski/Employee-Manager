package Interface.ReadPage;

import Database.Model.Database;
import QueryHandlers.Field;
import QueryHandlers.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadAssignmentPage extends JPanel implements ActionListener {
    private Database database;
    private JCheckBox employeeId, employeeName, surname, email, phoneNumber, role, salary, projectId, projectName, clientId, isActive;
    private JTextField employeeIdFilter, employeeNameFilter, surnameFilter, emailFilter, phoneNumberFilter, roleFilter, salaryFilter, projectIdFilter, projectNameFilter, clientIdFilter;
    private JCheckBox isActiveFilter;

    public ReadAssignmentPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(11, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Employee Id");
        addRow(formPanel, "Employee name");
        addRow(formPanel, "Surname");
        addRow(formPanel, "Email");
        addRow(formPanel, "Phone number");
        addRow(formPanel, "Role");
        addRow(formPanel, "Salary");

        addRow(formPanel, "Project Id");
        addRow(formPanel, "Project name");
        addRow(formPanel, "Client Id");
        addRow(formPanel, "Is active");

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton submit = new JButton("Read");
        mainPanel.add(submit, BorderLayout.SOUTH);
        submit.addActionListener(this);

        add(mainPanel);
    }

    private void addRow(JPanel panel, String label) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel labelComponent = new JLabel(label);
        rowPanel.add(labelComponent);

        switch (label) {
            case "Employee Id":
                employeeId = new JCheckBox();
                rowPanel.add(employeeId);
                employeeIdFilter = new JTextField();
                rowPanel.add(employeeIdFilter);
                break;
            case "Name":
                employeeName = new JCheckBox();
                rowPanel.add(employeeName);
                employeeNameFilter = new JTextField();
                rowPanel.add(employeeNameFilter);
                break;
            case "Surname":
                surname = new JCheckBox();
                rowPanel.add(surname);
                surnameFilter = new JTextField();
                rowPanel.add(surnameFilter);
                break;
            case "Email":
                email = new JCheckBox();
                rowPanel.add(email);
                emailFilter = new JTextField();
                rowPanel.add(emailFilter);
                break;
            case "Phone number":
                phoneNumber = new JCheckBox();
                rowPanel.add(phoneNumber);
                phoneNumberFilter = new JTextField();
                rowPanel.add(phoneNumberFilter);
                break;
            case "Role":
                role = new JCheckBox();
                rowPanel.add(role);
                roleFilter = new JTextField();
                rowPanel.add(roleFilter);
                break;
            case "Salary":
                salary = new JCheckBox();
                rowPanel.add(salary);
                salaryFilter = new JTextField();
                rowPanel.add(salaryFilter);
                break;
            case "Project Id":
                projectId = new JCheckBox();
                rowPanel.add(projectId);
                projectIdFilter = new JTextField();
                rowPanel.add(projectIdFilter);
                break;
            case "Project name":
                projectName = new JCheckBox();
                rowPanel.add(projectName);
                projectNameFilter = new JTextField();
                rowPanel.add(projectNameFilter);
                break;
            case "Client Id":
                clientId = new JCheckBox();
                rowPanel.add(clientId);
                clientIdFilter = new JTextField();
                rowPanel.add(clientIdFilter);
                break;
            case "Is active":
                isActive = new JCheckBox();
                rowPanel.add(isActive);
                isActiveFilter = new JCheckBox();
                rowPanel.add(isActiveFilter);
                break;
        }

        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Field field = new Field();
        Filter filter = new Filter();
/*
        if (employeeId.isSelected()) {
            field.addField();
        }

        if (employeeName.isSelected()) {
            field.addField("name");
        }

        if (surname.isSelected()) {
            field.addField("surname");
        }

        if (email.isSelected()) {
            field.addField("email");
        }

        if (phoneNumber.isSelected()) {
            field.addField("phone_number");
        }

        if (company.isSelected()) {
            field.addField("company");
        }

        if (!idFilter.getText().isEmpty()) {
            filter.addFilter("id", Integer.parseInt(id.getText()));
        }

        if (!nameFilter.getText().isEmpty()) {
            filter.addFilter("name", nameFilter.getText());
        }

        if (!surnameFilter.getText().isEmpty()) {
            filter.addFilter("surname", surnameFilter.getText());
        }

        if (!emailFilter.getText().isEmpty()) {
            filter.addFilter("email", emailFilter.getText());
        }

        if (!phoneNumberFilter.getText().isEmpty()) {
            filter.addFilter("phone_number", phoneNumberFilter.getText());
        }

        if (!company.getText().isEmpty()) {
            filter.addFilter("company", company.getText());
        }

        try {
            ArrayList<ArrayList<Object>> retrievedData;
            retrievedData = ReadEntityTable.performAction(database.getConnection(), Client.getTABLE_NAME(), field, filter);

            ArrayList<Object> columnNames = retrievedData.get(0);

            retrievedData.remove(0);

            DefaultTableModel model = new DefaultTableModel();

            for (Object columnName : columnNames) {
                model.addColumn(columnName);
            }

            for (ArrayList<Object> row : retrievedData) {
                model.addRow(row.toArray());
            }

            JTable table = new JTable(model);

            JFrame frame = new JFrame("Result");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(table));
            frame.pack();
            frame.setVisible(true);

        } catch (RuntimeException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }*/
    }
}
