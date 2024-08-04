package networkdetailer.com.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    JPanel mainPanel = new MainPanel();
    JPanel informationPanel;

    public Frame() {
        setTitle("Network Detailer");
        //getContentPane().setBackground(Color.lightGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(true);
        setSize(330, 180);
        setVisible(true);
        //pack();
        setLocationRelativeTo(null);
    }

    public void initialMainFrame() {
        mainPanel = new MainPanel();
        add(mainPanel);
    }

    public void informationPanelPlayer(String message) {
        informationPanel = new InformationPanel(message);
        add(informationPanel);
    }

}