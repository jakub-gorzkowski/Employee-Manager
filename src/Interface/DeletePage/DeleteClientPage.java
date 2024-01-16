package Interface.DeletePage;

import Database.Model.Database;
import Entities.Person.Client;
import Queries.Delete.DeleteEntity;
import QueryHandlers.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteClientPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField id;
    private JTextField name;
    private JTextField surname;
    private JTextField email;
    private JTextField phoneNumber;
    private JTextField company;

    public DeleteClientPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id");
        addRow(formPanel, "Name");
        addRow(formPanel, "Surname");
        addRow(formPanel, "Email");
        addRow(formPanel, "Phone number");
        addRow(formPanel, "Company");

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
            case "Id":
                id = new JTextField();
                rowPanel.add(id);
                break;
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
            case "Company":
                company = new JTextField();
                rowPanel.add(company);
                break;
        }
        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Filter filter = new Filter();

        if (!id.getText().isEmpty()) {
            filter.addFilter("id", Integer.parseInt(id.getText()));
        }

        if (!name.getText().isEmpty()) {
            filter.addFilter("name", name.getText());
        }

        if (!surname.getText().isEmpty()) {
            filter.addFilter("surname", surname.getText());
        }

        if (!email.getText().isEmpty()) {
            filter.addFilter("email", email.getText());
        }

        if (!phoneNumber.getText().isEmpty()) {
            filter.addFilter("phone_number", phoneNumber.getText());
        }

        if (!company.getText().isEmpty()) {
            filter.addFilter("company", company.getText());
        }

        try {
            DeleteEntity.performAction(database.getConnection(), Client.getTABLE_NAME(), filter);
            JOptionPane.showMessageDialog(this, "Successfully removed.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        name.setText("");
        surname.setText("");
        email.setText("");
        phoneNumber.setText("");
        company.setText("");
    }
}
