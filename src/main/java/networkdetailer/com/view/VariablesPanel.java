package networkdetailer.com.view;

import javax.swing.*;
import java.awt.*;

public class VariablesPanel extends JPanel {
    JPanel variables = new JPanel();
    JPanel variablesValues = new JPanel();
    private JLabel hostname = new JLabel("Host Name:");
    private JLabel ipAddress = new JLabel("IP Address:");
    private JLabel mac = new JLabel("Physical Address:");
    private JLabel hostnameValue = new JLabel("Kacper-PC");
    private JLabel ipAddressValue = new JLabel("127.0.0.1");
    private JLabel macValue = new JLabel("23;23;23;23");

    public VariablesPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        variables.setLayout(new GridLayout(3, 1, 10, 10));
        variables.add(hostname);
        variables.add(ipAddress);
        variables.add(mac);

        variablesValues.setLayout(new GridLayout(3, 1, 10, 10));
        variablesValues.add(hostnameValue);
        variablesValues.add(ipAddressValue);
        variablesValues.add(macValue);

        add(variables);
        add(variablesValues);
    }
}
