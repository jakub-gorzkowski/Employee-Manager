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
        setLayout(new GridLayout(5, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel idLabel = new JLabel("ID");
        add(idLabel);
        id = new JTextField();
        add(id);

        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        name = new JTextField();
        add(name);

        JLabel clientIdLabel = new JLabel("Client ID");
        add(clientIdLabel);
        clientId = new JTextField();
        add(clientId);

        JButton submit = new JButton("Delete");
        add(submit);
        submit.addActionListener(this);
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
