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
    private JLabel cpuName = new JLabel("CPU Name:");
    private JLabel cpuGen = new JLabel("CPU Gen:");
    private JLabel cpuGhz = new JLabel("GHz:");
    private JLabel ram = new JLabel("(GB)RAM:");
    private JLabel diskSpace = new JLabel("(GB)Disk Space:");
    private JLabel diskType = new JLabel("Disk Type:");
    private JLabel windowsRequirements = new JLabel("Windows requirements:");
    private JLabel bios = new JLabel("Bios version:");

    private JLabel hostnameValue = new JLabel();
    private JLabel ipAddressValue = new JLabel();
    private JLabel macValue = new JLabel();
    private JLabel cpuNameValue = new JLabel();
    private JLabel cpuGenValue = new JLabel();
    private JLabel cpuGhzValue = new JLabel();
    private JLabel ramValue = new JLabel();
    private JLabel diskSpaceValue = new JLabel();
    private JLabel diskTypeValue = new JLabel();
    private JLabel windowsRequirementsValue = new JLabel();
    private JLabel biosValue = new JLabel();

    public VariablesPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        variables.setLayout(new GridLayout(11, 1, 10, 10));
        variables.add(hostname);
        variables.add(ipAddress);
        variables.add(mac);
        variables.add(cpuName);
        variables.add(cpuGen);
        variables.add(cpuGhz);
        variables.add(ram);
        variables.add(diskSpace);
        variables.add(diskType);
        variables.add(windowsRequirements);
        variables.add(bios);

        variablesValues.setLayout(new GridLayout(11, 1, 10, 10));

        add(variables);
        add(variablesValues);

        variablesValues.add(hostnameValue);
        variablesValues.add(ipAddressValue);
        variablesValues.add(macValue);
        variablesValues.add(cpuNameValue);
        variablesValues.add(cpuGenValue);
        variablesValues.add(cpuGhzValue);
        variablesValues.add(ramValue);
        variablesValues.add(diskSpaceValue);
        variablesValues.add(diskTypeValue);
        variablesValues.add(windowsRequirementsValue);
        variablesValues.add(biosValue);

        instance = this;
        updateData();
    }

    public void updateData() {
        hostnameValue.setText("");
        ipAddressValue.setText("");
        macValue.setText("");
        cpuNameValue.setText("");
        cpuGenValue.setText("");
        cpuGhzValue.setText("");
        ramValue.setText("");
        diskSpaceValue.setText("");
        diskTypeValue.setText("");
        windowsRequirementsValue.setText("");
        biosValue.setText("");

        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hostnameValue.setText(Controller.getHostname());
                ipAddressValue.setText(Controller.getIP());
                macValue.setText(Controller.getMAC());
                cpuNameValue.setText(Controller.getCpuName());
                cpuGenValue.setText(Controller.getCpuGen());
                cpuGhzValue.setText(Controller.getCpuGhz());
                ramValue.setText(Controller.getRamValue());
                diskSpaceValue.setText(Controller.getDiskSpace());
                diskTypeValue.setText(Controller.getDiskType());
                windowsRequirementsValue.setText(Controller.getWindowsRequirements());
                biosValue.setText(Controller.getBios());
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
