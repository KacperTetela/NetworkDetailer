package networkdetailer.com.view.main;

import networkdetailer.com.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VariablesPanel extends JPanel {
    private static VariablesPanel instance;

    JPanel variables = new JPanel();
    JPanel variablesValues = new JPanel();
    private JLabel hostname = new JLabel("Host Name:");
    private JLabel ipAddress = new JLabel("IP Address:");
    private JLabel mac = new JLabel("Physical Address:");
    private JLabel hostnameValue = new JLabel();
    private JLabel ipAddressValue = new JLabel();
    private JLabel macValue = new JLabel();

    public VariablesPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        variables.setLayout(new GridLayout(3, 1, 10, 10));
        variables.add(hostname);
        variables.add(ipAddress);
        variables.add(mac);

        variablesValues.setLayout(new GridLayout(3, 1, 10, 10));

        add(variables);
        add(variablesValues);

        variablesValues.add(hostnameValue);
        variablesValues.add(ipAddressValue);
        variablesValues.add(macValue);

        instance = this;
        updateData();
    }

    public void updateData() {
        hostnameValue.setText("");
        ipAddressValue.setText("");
        macValue.setText("");
        Timer timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hostnameValue.setText(Controller.getHostname());
                ipAddressValue.setText(Controller.getIP());
                macValue.setText(Controller.getMAC());
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void updateStaticData() {
        if (instance != null) {
            instance.updateData();
        }
    }
}
