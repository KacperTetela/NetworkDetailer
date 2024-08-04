package networkdetailer.com.view.main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    JPanel buttonsPanel = new ButtonsPanel();
    JPanel variablesPanel = new VariablesPanel();

    public MainPanel() {
        setLayout(new GridLayout(2,1));
        add(variablesPanel);
        add(buttonsPanel);
    }
}
