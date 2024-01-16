package View.UpdatePage;

import Database.Database;
import Model.Employee.EmployeeRole;
import Model.Person.Employee;
import Service.Update.UpdateFields;
import Handler.Filter;
import Handler.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployeePage extends JPanel implements ActionListener {

    private Database database;
    private JTextField idFilter, nameFilter, surnameFilter, emailFilter, phoneNumberFilter, salaryFilter;
    private JTextField nameUpdate, surnameUpdate, emailUpdate, phoneNumberUpdate, salaryUpdate;

    private JComboBox<Object> roleFilter, roleUpdate;

    public UpdateEmployeePage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id Filter");
        addRow(formPanel, "");
        addRow(formPanel, "Name Filter");
        addRow(formPanel, "Name Update");
        addRow(formPanel, "Surname Filter");
        addRow(formPanel, "Surname Update");
        addRow(formPanel, "Email Filter");
        addRow(formPanel, "Email Update");
        addRow(formPanel, "Phone number Filter");
        addRow(formPanel, "Phone number Update");
        addRow(formPanel, "Role Filter");
        addRow(formPanel, "Role Update");
        addRow(formPanel, "Salary Filter");
        addRow(formPanel, "Salary Update");

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
            case "Id Filter":
                idFilter = new JTextField();
                rowPanel.add(idFilter);
                break;
            case "Name Filter":
                nameFilter = new JTextField();
                rowPanel.add(nameFilter);
                break;
            case "Name Update":
                nameUpdate = new JTextField();
                rowPanel.add(nameUpdate);
                break;
            case "Surname Filter":
                surnameFilter = new JTextField();
                rowPanel.add(surnameFilter);
                break;
            case "Surname Update":
                surnameUpdate = new JTextField();
                rowPanel.add(surnameUpdate);
                break;
            case "Email Filter":
                emailFilter = new JTextField();
                rowPanel.add(emailFilter);
                break;
            case "Email Update":
                emailUpdate = new JTextField();
                rowPanel.add(emailUpdate);
                break;
            case "Phone number Filter":
                phoneNumberFilter = new JTextField();
                rowPanel.add(phoneNumberFilter);
                break;
            case "Phone number Update":
                phoneNumberUpdate = new JTextField();
                rowPanel.add(phoneNumberUpdate);
                break;
            case "Role Filter":
                roleFilter = new JComboBox<>(EmployeeRole.values());
                roleFilter.addItem("");
                roleFilter.setSelectedItem("");
                rowPanel.add(roleFilter);
                break;
            case "Role Update":
                roleUpdate = new JComboBox<>(EmployeeRole.values());
                rowPanel.add(roleUpdate);
                break;
            case "Salary Filter":
                salaryFilter = new JTextField();
                rowPanel.add(salaryFilter);
                break;
            case "Salary Update":
                salaryUpdate = new JTextField();
                rowPanel.add(salaryUpdate);
                break;
            case "":
                rowPanel.add(new JLabel(""));
                break;
        }

        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Value value = new Value();
        Filter filter = new Filter();

        if (!idFilter.getText().isEmpty()) {
            filter.addFilter("id", idFilter.getText());
        }

        if (!nameFilter.getText().isEmpty()) {
            filter.addFilter("name", nameFilter.getText());
        }

        if (!nameUpdate.getText().isEmpty()) {
            value.addUpdate("name", nameUpdate.getText());
        }

        if (!surnameFilter.getText().isEmpty()) {
            filter.addFilter("surname", surnameFilter.getText());
        }

        if (!surnameUpdate.getText().isEmpty()) {
            value.addUpdate("surname", surnameUpdate.getText());
        }

        if (!emailFilter.getText().isEmpty()) {
            filter.addFilter("email", emailFilter.getText());
        }

        if (!emailUpdate.getText().isEmpty()) {
            value.addUpdate("email", emailUpdate.getText());
        }

        if (!phoneNumberFilter.getText().isEmpty()) {
            filter.addFilter("phone_number", phoneNumberFilter.getText());
        }

        if (!phoneNumberUpdate.getText().isEmpty()) {
            value.addUpdate("phone_number", phoneNumberUpdate.getText());
        }

        if (!roleFilter.getSelectedItem().equals("")) {
            filter.addFilter("role", roleFilter.getSelectedItem());
        }

        if (!roleUpdate.getSelectedItem().equals("")) {
            value.addUpdate("role", roleUpdate.getSelectedItem());
        }

        if (!salaryFilter.getText().isEmpty()) {
            filter.addFilter("salary", salaryFilter.getText());
        }

        if (!salaryUpdate.getText().isEmpty()) {
            value.addUpdate("salary", salaryUpdate.getText());
        }

        try {
            UpdateFields.performAction(database.getConnection(), Employee.getTABLE_NAME(), value, filter);
            JOptionPane.showMessageDialog(this, "Updated successfully.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        idFilter.setText("");
        nameFilter.setText("");
        nameUpdate.setText("");
        surnameFilter.setText("");
        surnameUpdate.setText("");
        emailFilter.setText("");
        emailUpdate.setText("");
        phoneNumberFilter.setText("");
        phoneNumberUpdate.setText("");
        salaryFilter.setText("");
        salaryUpdate.setText("");
    }
}
