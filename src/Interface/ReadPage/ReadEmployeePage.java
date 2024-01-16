package Interface.ReadPage;

import Database.Model.Database;
import Entities.Employee.EmployeeRole;
import Entities.Person.Client;
import Entities.Person.Employee;
import Queries.Read.ReadEntityTable;
import QueryHandlers.Field;
import QueryHandlers.Filter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ReadEmployeePage extends JPanel implements ActionListener {
    private Database database;
    private JCheckBox id, name, surname, email, phoneNumber, role, salary;
    private JTextField idFilter, nameFilter, surnameFilter, emailFilter, phoneNumberFilter, salaryFilter;
    private JComboBox<Object> roleFilter;

    public ReadEmployeePage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id");
        addRow(formPanel, "Name");
        addRow(formPanel, "Surname");
        addRow(formPanel, "Email");
        addRow(formPanel, "Phone number");
        addRow(formPanel, "Role");
        addRow(formPanel, "Salary");

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
            case "Id":
                id = new JCheckBox();
                rowPanel.add(id);
                idFilter = new JTextField();
                rowPanel.add(idFilter);
                break;
            case "Name":
                name = new JCheckBox();
                rowPanel.add(name);
                nameFilter = new JTextField();
                rowPanel.add(nameFilter);
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
                roleFilter = new JComboBox<>(EmployeeRole.values());
                roleFilter.addItem("");
                rowPanel.add(roleFilter);
                break;
            case "Salary":
                salary = new JCheckBox();
                rowPanel.add(salary);
                salaryFilter = new JTextField();
                rowPanel.add(salaryFilter);
                break;
        }
        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Field field = new Field();
        Filter filter = new Filter();

        if (id.isSelected()) {
            field.addField("id");
        }

        if (name.isSelected()) {
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

        if (role.isSelected()) {
            field.addField("role");
        }

        if (salary.isSelected()) {
            field.addField("salary");
        }

        if (!idFilter.getText().isEmpty()) {
            filter.addFilter("id", Integer.parseInt(idFilter.getText()));
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

        if (!salaryFilter.getText().isEmpty()) {
            filter.addFilter("salary", new BigDecimal(salary.getText()));
        }

        if (!roleFilter.getSelectedItem().equals("")) {
            filter.addFilter("role", (EmployeeRole) roleFilter.getSelectedItem());
        }

        try {
            ArrayList<ArrayList<Object>> retrievedData;
            retrievedData = ReadEntityTable.performAction(database.getConnection(), Employee.getTABLE_NAME(), field, filter);

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

            JFrame frame = new JFrame(Employee.getTABLE_NAME());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (RuntimeException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }
    }
}
