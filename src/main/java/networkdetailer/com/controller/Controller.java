package networkdetailer.com.controller;

import networkdetailer.com.model.DataCollector;

public class Controller {
    public static String getIP() {
        return DataCollector.getInstance().getiP();
    }

    public static String getHostname() {
        return DataCollector.getInstance().getHostName();
    }

    public static String getMAC() {
        return DataCollector.getInstance().getMac();
    }

    public static int exportToExcel() {
        return DataCollector.getInstance().saveToExcel();
    }

    public static boolean saveAsTxt() {
        return DataCollector.getInstance().saveToTxt();
    }

    public static void refresh() {
        DataCollector.getInstance().refresh();
    }
}
