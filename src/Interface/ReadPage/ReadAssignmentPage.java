package Interface.ReadPage;

import Database.Model.Database;
import Entities.Employee.EmployeeProject;
import Entities.Employee.EmployeeRole;
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

public class ReadAssignmentPage extends JPanel implements ActionListener {
    private Database database;
    private JCheckBox employeeId, projectId;
    private JTextField employeeIdFilter, projectIdFilter;

    public ReadAssignmentPage(Database database) {
        this.database = database;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        formPanel.setBackground(Color.WHITE);


        addRow(formPanel, "Employee Id");
        addRow(formPanel, "Project Id");

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
            case "Employee Id":
                employeeId = new JCheckBox();
                rowPanel.add(employeeId);
                employeeIdFilter= new JTextField();
                rowPanel.add(employeeIdFilter);
                break;
            case "Project Id":
                projectId = new JCheckBox();
                rowPanel.add(projectId);
                projectIdFilter = new JTextField();
                rowPanel.add(projectIdFilter);
                break;
        }
        panel.add(rowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Field field = new Field();
        Filter filter = new Filter();

        if (employeeId.isSelected()) {
            field.addField("employee_id");
        }

        if (projectId.isSelected()) {
            field.addField("project_id");
        }

        if (!employeeIdFilter.getText().isEmpty()) {
            filter.addFilter("employee_id", Integer.parseInt(employeeIdFilter.getText()));
        }

        if (!projectIdFilter.getText().isEmpty()) {
            filter.addFilter("project_id", projectIdFilter.getText());
        }

        try {
            ArrayList<ArrayList<Object>> retrievedData;
            retrievedData = ReadEntityTable.performAction(database.getConnection(), EmployeeProject.getTABLE_NAME(), field, filter);

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
