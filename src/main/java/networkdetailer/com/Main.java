package networkdetailer.com;

import networkdetailer.com.view.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame.getInstance().initialMainFrame();
        });
    }
}