package Interface.DeletePage;

import Database.Model.Database;
import Entities.Project.Project;
import Queries.Delete.DeleteEntity;
import QueryHandlers.Filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProjectPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField id;
    private JTextField name;
    private JTextField clientId;
    public DeleteProjectPage(Database database) {
        this.database = database;
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id");
        addRow(formPanel, "Name");
        addRow(formPanel, "Client Id");
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
            case "Client Id":
                clientId = new JTextField();
                rowPanel.add(clientId);
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

        if (!clientId.getText().isEmpty()) {
            filter.addFilter("client_id", Integer.parseInt(clientId.getText()));
        }

        try {
            DeleteEntity.performAction(database.getConnection(), Project.getTABLE_NAME(), filter);
            JOptionPane.showMessageDialog(this, "Successfully removed.");
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }

        id.setText("");
        name.setText("");
        clientId.setText("");
    }
}
