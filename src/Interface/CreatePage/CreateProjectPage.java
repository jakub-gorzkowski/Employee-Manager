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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Name");
        addRow(formPanel, "Client Id");
        addRow(formPanel, "Is active");
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
            case "Client Id":
                clientId = new JTextField();
                rowPanel.add(clientId);
                break;
            case "Is active":
                isActive = new JCheckBox();
                rowPanel.add(isActive);
                break;
        }
        panel.add(rowPanel);
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
