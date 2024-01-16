package View.UpdatePage;

import Database.Database;
import Model.Person.Client;
import Service.Update.UpdateFields;
import Handler.Filter;
import Handler.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpdateClientPage extends JPanel implements ActionListener {

    private Database database;
    private JTextField idFilter, nameFilter, surnameFilter, emailFilter, phoneNumberFilter, companyFilter;
    private JTextField idUpdate, nameUpdate, surnameUpdate, emailUpdate, phoneNumberUpdate, companyUpdate;

    public UpdateClientPage(Database database) {
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
        addRow(formPanel, "Company Filter");
        addRow(formPanel, "Company Update");

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
            case "Id Filter":
                idFilter = new JTextField();
                rowPanel.add(idFilter);
                break;
            case "Name Filter":
                nameFilter = new JTextField();
                rowPanel.add(nameFilter);
                break;
            case "Surname Filter":
                surnameFilter = new JTextField();
                rowPanel.add(surnameFilter);
                break;
            case "Email Filter":
                emailFilter = new JTextField();
                rowPanel.add(emailFilter);
                break;
            case "Phone number Filter":
                phoneNumberFilter = new JTextField();
                rowPanel.add(phoneNumberFilter);
                break;
            case "Company Filter":
                companyFilter = new JTextField();
                rowPanel.add(companyFilter);
                break;
            case "Name Update":
                nameUpdate = new JTextField();
                rowPanel.add(nameUpdate);
                break;
            case "Surname Update":
                surnameUpdate = new JTextField();
                rowPanel.add(surnameUpdate);
                break;
            case "Email Update":
                emailUpdate = new JTextField();
                rowPanel.add(emailUpdate);
                break;
            case "Phone number Update":
                phoneNumberUpdate = new JTextField();
                rowPanel.add(phoneNumberUpdate);
                break;
            case "Company Update":
                companyUpdate = new JTextField();
                rowPanel.add(companyUpdate);
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

        if (!companyFilter.getText().isEmpty()) {
            filter.addFilter("company", companyFilter.getText());
        }

        if (!companyUpdate.getText().isEmpty()) {
            value.addUpdate("company", companyUpdate.getText());
        }

        try {
            UpdateFields.performAction(database.getConnection(), Client.getTABLE_NAME(), value, filter);
            JOptionPane.showMessageDialog(this, "Updated successfully.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        idFilter.setText("");
        nameFilter.setText("");
        surnameFilter.setText("");
        emailFilter.setText("");
        phoneNumberFilter.setText("");
        companyFilter.setText("");
        nameUpdate.setText("");
        surnameUpdate.setText("");
        emailUpdate.setText("");
        phoneNumberUpdate.setText("");
        companyUpdate.setText("");
    }
}
