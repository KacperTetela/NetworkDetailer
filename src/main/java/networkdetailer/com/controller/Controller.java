package networkdetailer.com.controller;

import networkdetailer.com.model.data.DataCollector;

public class Controller {
    public static String getIP() {
        return DataCollector.getInstance().getNetworkData().ip();
    }

    public static String getHostname() {
        return DataCollector.getInstance().getNetworkData().hostname();
    }

    public static String getMAC() {
        return DataCollector.getInstance().getNetworkData().mac();
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
        return DataCollector.getInstance().getCpuData().name();
    }

    public static String getCpuGen() {
        return String.valueOf(DataCollector.getInstance().getCpuData().generation().generation());
    }

    public static String getCpuGhz() {
        if (Double.valueOf(DataCollector.getInstance().getCpuData().ghz()) == 0.0)
            return "UNKNOWN";
        return String.valueOf(DataCollector.getInstance().getCpuData().ghz());
    }

    public static String getRamValue() {
        return String.valueOf(DataCollector.getInstance().getMemoryData().ramGB());
    }

    public static String getDiskSpace() {
        return String.valueOf(DataCollector.getInstance().getMemoryData().diskSpaceGB());
    }

    public static String getDiskType() {
        return String.valueOf(DataCollector.getInstance().getMemoryData().diskType());
    }

    public static String getWindowsRequirements() {
        return String.valueOf(DataCollector.getInstance().isWindowsRequirements());
    }

    public static String getBios() {
        return String.valueOf(DataCollector.getInstance().getMoboData().bios());
    }

    public static String getCpuManufacturer() {
        return String.valueOf(DataCollector.getInstance().getCpuData().generation().cpuManufacturer());
    }
}
