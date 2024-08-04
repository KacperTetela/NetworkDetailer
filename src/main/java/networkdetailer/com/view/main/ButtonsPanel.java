package networkdetailer.com.view.main;

import networkdetailer.com.controller.Controller;
import networkdetailer.com.view.InformationPanel;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JButton saveToTxt = new JButton("Save as TXT");
    JButton saveToExcel = new JButton("Export to Excel");
    JButton refresh = new JButton("Refresh Data");

    public ButtonsPanel() {
        setSize(250, 80);
        setLayout(new FlowLayout());

        saveToTxt.setFocusPainted(false);
        saveToExcel.setFocusPainted(false);
        refresh.setFocusPainted(false);

        saveToExcel.addActionListener(e -> Controller.exportToExcel());
        saveToTxt.addActionListener(e -> System.exit(0));
        refresh.addActionListener(e -> System.exit(0));

        add(saveToExcel);
        add(saveToTxt);
        add(refresh);
    }
}
