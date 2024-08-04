package networkdetailer.com.view;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JPanel labelPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JLabel messageLabel;
    private JButton exitButton = new JButton("Close");

    public InformationPanel(String message) {
        this.messageLabel = new JLabel(message);
        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        setLayout(new GridLayout(2, 1, 0, 25));
        labelPanel.add(messageLabel);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setFocusPainted(false);
        buttonsPanel.add(exitButton);
        add(labelPanel);
        add(buttonsPanel);
    }
}
