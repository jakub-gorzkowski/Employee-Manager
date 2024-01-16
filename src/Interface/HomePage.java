package Interface;

import Database.Managers.ConnectionManager;
import Database.Model.Database;
import Interface.CreatePage.CreatePage;
import Interface.DeletePage.DeletePage;
import Interface.ReadPage.ReadPage;
import Interface.UpdatePage.UpdatePage;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    JTabbedPane menu = new JTabbedPane();
    JFrame frame = new JFrame();

    public HomePage(Database database, ConnectionManager connectionManager) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                connectionManager.disconnect();
                frame.dispose();
            }
        });
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        menu.setBounds(0, 0, WINDOW_WIDTH - 15, WINDOW_HEIGHT - 39);
        menu.addTab("Insert", new CreatePage(database));
        menu.addTab("Read", new ReadPage(database));
        menu.addTab("Update", new UpdatePage(database));
        menu.addTab("Delete", new DeletePage(database));
        frame.getContentPane().add(menu);

        frame.setVisible(true);
    }
}
