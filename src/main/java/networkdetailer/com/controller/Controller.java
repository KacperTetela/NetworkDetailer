package networkdetailer.com.controller;

import networkdetailer.com.model.data.DataCollector;

public class Controller {
    public static String getIP() {
        return DataCollector.getInstance().getIp();
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

    public static String getCpuName() {
        return DataCollector.getInstance().getCpuName();
    }

    public static String getCpuGen() {
        return String.valueOf(DataCollector.getInstance().getCpuGen());
    }

    public static String getCpuGhz() {
        System.out.println(DataCollector.getInstance().getCpuGhz());
        System.out.println(DataCollector.getInstance().getCpuGhz());
        System.out.println(DataCollector.getInstance().getCpuGhz());
        if (DataCollector.getInstance().getCpuGhz().equals("0"))
            return "UNKNOWN";
        return String.valueOf(DataCollector.getInstance().getCpuGhz());
    }

    public static String getRamValue() {
        return String.valueOf(DataCollector.getInstance().getRam());
    }

    public static String getDiskSpace() {
        return String.valueOf(DataCollector.getInstance().getDiskSpace());
    }

    public static String getDiskType() {
        return String.valueOf(DataCollector.getInstance().getDiskType());
    }

    public static String getWindowsRequirements() {
        return String.valueOf(DataCollector.getInstance().isWindowsRequirements());
    }

    public static String getBios() {
        return String.valueOf(DataCollector.getInstance().getBios());
    }
}
