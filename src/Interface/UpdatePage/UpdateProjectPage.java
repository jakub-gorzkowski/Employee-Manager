package Interface.UpdatePage;

import Database.Model.Database;
import Entities.Project.Project;
import Queries.Update.UpdateFields;
import QueryHandlers.Filter;
import QueryHandlers.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProjectPage extends JPanel implements ActionListener {

    private Database database;
    private JTextField idFilter, nameFilter, clientIdFilter;
    private JTextField nameUpdate, clientIdUpdate;
    private JCheckBox isActiveFilter, isActiveUpdate;

    public UpdateProjectPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id Filter");
        addRow(formPanel, "");
        addRow(formPanel, "Name Filter");
        addRow(formPanel, "Name Update");
        addRow(formPanel, "Client ID Filter");
        addRow(formPanel, "Client ID Update");
        addRow(formPanel, "Is Active Filter");
        addRow(formPanel, "Is Active Update");

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
            case "Client ID Filter":
                clientIdFilter = new JTextField();
                rowPanel.add(clientIdFilter);
                break;
            case "Client ID Update":
                clientIdUpdate = new JTextField();
                rowPanel.add(clientIdUpdate);
                break;
            case "Is Active Filter":
                isActiveFilter = new JCheckBox();
                rowPanel.add(isActiveFilter);
                break;
            case "Is Active Update":
                isActiveUpdate = new JCheckBox();
                rowPanel.add(isActiveUpdate);
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

        if (!clientIdFilter.getText().isEmpty()) {
            filter.addFilter("client_id", clientIdFilter.getText());
        }

        if (!clientIdUpdate.getText().isEmpty()) {
            value.addUpdate("client_id", clientIdUpdate.getText());
        }

        filter.addFilter("is_active", isActiveFilter.isSelected());
        value.addUpdate("is_active", isActiveUpdate.isSelected());


        try {
            UpdateFields.performAction(database.getConnection(), Project.getTABLE_NAME(), value, filter);
            JOptionPane.showMessageDialog(this, "Updated successfully.");
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        idFilter.setText("");
        nameFilter.setText("");
        nameUpdate.setText("");
        clientIdFilter.setText("");
        clientIdUpdate.setText("");
        isActiveFilter.setText("");
        isActiveUpdate.setText("");
    }
}
