package networkdetailer.com.view;

import networkdetailer.com.view.main.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static Frame instance;

    private final ImageIcon icon = new ImageIcon(getClass().getResource("/search.png"));
    JPanel mainPanel = new MainPanel();
    JPanel informationPanel;

    public static synchronized Frame getInstance() {
        if (instance == null) {
            instance = new Frame();
        }
        return instance;
    }

    private Frame() {
        setTitle("Network Detailer");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setSize(400, 380);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void initialMainFrame() {
        mainPanel = new MainPanel();
        add(mainPanel);
    }

    public void informationPanelPlayer(String message) {
        informationPanel = new InformationPanel(message);
        add(informationPanel);
        mainPanel.setVisible(false);
    }
}