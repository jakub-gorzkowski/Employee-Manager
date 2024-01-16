package Interface.CreatePage;

import Database.Model.Database;
import Entities.Person.Client;
import Queries.Create.CreateClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClientPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField name;
    private JTextField surname;
    private JTextField email;
    private JTextField phoneNumber;
    private JTextField company;

    public CreateClientPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Name");
        addRow(formPanel, "Surname");
        addRow(formPanel, "Email");
        addRow(formPanel, "Phone number");
        addRow(formPanel, "Company");

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
            case "Company":
                company = new JTextField();
                rowPanel.add(company);
                break;
        }
        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField[] inputFields = {name, surname, email, phoneNumber};
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
            if (company.getText().isEmpty()) {
                CreateClient.performAction(database.getConnection(),
                        new Client(
                                name.getText(),
                                surname.getText(),
                                email.getText(),
                                phoneNumber.getText()
                        )
                );
            } else {
                CreateClient.performAction(database.getConnection(),
                        new Client(
                                name.getText(),
                                surname.getText(),
                                email.getText(),
                                phoneNumber.getText(),
                                company.getText()
                        )
                );
            }
            JOptionPane.showMessageDialog(this, "Successfully created.");
            name.setText("");
            surname.setText("");
            email.setText("");
            phoneNumber.setText("");
            company.setText("");
        }
    }
}
