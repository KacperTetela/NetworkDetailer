package networkdetailer.com.view;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    JButton saveToTxt = new JButton("saveToTxt");
    JButton saveToExcel = new JButton("saveToExcel");
    JButton refresh = new JButton("refresh");

    public ButtonsPanel() {
        setSize(250, 80);
        setLayout(new FlowLayout());

        saveToTxt.setFocusPainted(false);
        saveToExcel.setFocusPainted(false);
        refresh.setFocusPainted(false);

        add(saveToTxt);
        add(saveToExcel);
        add(refresh);
    }
}
