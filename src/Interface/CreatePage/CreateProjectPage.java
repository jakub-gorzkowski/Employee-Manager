package Interface.CreatePage;

import Database.Model.Database;
import Entities.Project.Project;
import Queries.Create.CreateProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectPage extends JPanel implements ActionListener {
    private Database database;
    private JTextField name;
    private JTextField clientId;
    private JCheckBox isActive;

    public CreateProjectPage(Database database) {
        this.database = database;
        setLayout(new GridLayout(4, 2, 10, 10));
        setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name");
        add(nameLabel);
        name = new JTextField();
        add(name);

        JLabel clientIdLabel = new JLabel("Client ID");
        add(clientIdLabel);
        clientId = new JTextField();
        add(clientId);

        JLabel isActiveLabel = new JLabel("Is active");
        add(isActiveLabel);
        isActive = new JCheckBox();
        add(isActive);

        JButton submit = new JButton("Insert");
        add(submit);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField[] inputFields = {name, clientId};
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
            CreateProject.performAction(database.getConnection(),
                    new Project(
                        name.getText(),
                        Integer.parseInt(clientId.getText()),
                        isActive.isSelected()
                    )
            );
            JOptionPane.showMessageDialog(this, "Successfully created.");
            name.setText("");
            clientId.setText("");
            isActive.setSelected(false);
        }
    }
}
