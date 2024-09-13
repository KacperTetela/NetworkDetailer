package networkdetailer.com;

import networkdetailer.com.view.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //DataCollector
        //Controller
        SwingUtilities.invokeLater(() -> {
            Frame.getInstance().initialMainFrame();
        });
    }
}