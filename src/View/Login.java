package View;

import Database.ConnectionManager;
import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    ConnectionManager connectionManager;
    Database database;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JTextField login = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel usernameLabel = new JLabel("login");
    JLabel passwordLabel = new JLabel("password");
    JLabel message = new JLabel();

    private static Login singleton = null;

    public static Login getInstance(Database database, ConnectionManager connectionManager) {
        if (singleton == null) {
            singleton = new Login(database, connectionManager);
        }
        return singleton;
    }

    private Login(Database database, ConnectionManager connectionManager) {
        this.database = database;
        this.connectionManager = connectionManager;

        usernameLabel.setBounds(43, 15, 75, 25);
        passwordLabel.setBounds(43, 65, 75, 25);

        message.setBounds(43, 150, 250, 25);
        message.setFont(new Font(null, Font.PLAIN, 15));

        login.setBounds(43, 40, 200, 25);
        password.setBounds(43, 90, 200, 25);

        loginButton.setBounds(43, 125, 200, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        frame.add(usernameLabel);
        frame.add(login);
        frame.add(passwordLabel);
        frame.add(password);
        frame.add(loginButton);
        frame.add(message);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 240);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String givenLogin = login.getText();
            String givenPassword = String.valueOf(password.getPassword());

            connectionManager.connect(givenLogin, givenPassword);

            if (database.getConnection() == null) {
                message.setText("Invalid username or password");
            } else {
                message.setText("");
                frame.dispose();
                HomePage homePage = new HomePage(database, connectionManager);
            }
        }
    }
}
