package networkdetailer.com.controller;

import networkdetailer.com.model.data.DataCollector;

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

    public static void exportToExcel() {
        DataCollector.getInstance().saveToExcel();
    }
}
