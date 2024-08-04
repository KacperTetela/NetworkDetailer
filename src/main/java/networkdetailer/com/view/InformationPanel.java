package networkdetailer.com.view;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JPanel labelPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JLabel messageLabel;
    private JButton exitButton = new JButton("Exit");

    public InformationPanel(String message) {
        this.messageLabel = new JLabel(message);
        setLayout(new GridLayout(2,1));
        add(messageLabel);
        add(exitButton);
    }
}
