package Interface.ReadPage;

import Database.Model.Database;
import Entities.Project.Project;
import Queries.Read.ReadEntityTable;
import QueryHandlers.Field;
import QueryHandlers.Filter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReadProjectPage extends JPanel implements ActionListener {
    private Database database;
    private JCheckBox id, name, clientId, isActive;
    private JTextField idFilter, nameFilter, clientIdFilter;
    private JCheckBox isActiveFilter;

    public ReadProjectPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);

        addRow(formPanel, "Id");
        addRow(formPanel, "Name");
        addRow(formPanel, "Client Id");
        addRow(formPanel, "Is active");

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
            case "Client Id":
                clientId = new JCheckBox();
                rowPanel.add(clientId);
                clientIdFilter = new JTextField();
                rowPanel.add(clientIdFilter);
                break;
            case "Is active":
                isActive = new JCheckBox();
                rowPanel.add(isActive);
                isActiveFilter = new JCheckBox();
                rowPanel.add(isActiveFilter);
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

        if (clientId.isSelected()) {
            field.addField("client_id");
        }

        if (isActive.isSelected()) {
            field.addField("is_active");
        }

        if (!idFilter.getText().isEmpty()) {
            filter.addFilter("id", Integer.parseInt(idFilter.getText()));
        }

        if (!nameFilter.getText().isEmpty()) {
            filter.addFilter("name", nameFilter.getText());
        }

        if (!clientIdFilter.getText().isEmpty()) {
            filter.addFilter("client_id", Integer.parseInt(clientIdFilter.getText()));
            filter.addFilter("is_active", isActiveFilter.isSelected());
        }

        try {
            ArrayList<ArrayList<Object>> retrievedData;
            retrievedData = ReadEntityTable.performAction(database.getConnection(), Project.getTABLE_NAME(), field, filter);

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

            JFrame frame = new JFrame(Project.getTABLE_NAME());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, "Error occurred.");
        }
    }
}