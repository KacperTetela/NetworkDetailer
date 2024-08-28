package networkdetailer.com.model.hardware;

import oshi.SystemInfo;
import oshi.hardware.*;

import java.util.Arrays;

public class SystemSpecsDownloader {
    private String cpuModel;
    private int cpuGeneration;
    private long ramGB;
    private long diskSpaceGB;
    private String diskType;
    private String baseboard;
    private String boardModel;
    private String bios;
    private String[] ghz;
    private CPUInfo cpuInfo;

    public SystemSpecsDownloader() {
        SystemInfo systemInfo = new SystemInfo();
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        Firmware firmware = computerSystem.getFirmware();
        // Pobieranie informacji o CPU
        CentralProcessor processor = hal.getProcessor();
        cpuModel = processor.getProcessorIdentifier().getName();
        cpuGeneration = CPUInfoChecker.identify(cpuModel).generation();
        ghz = cpuModel.split("[ GHz]", 256);
        System.out.println(Arrays.toString(ghz));
        //for (Object element : ghz) {
        //    if (element instanceof Float) {float floatValue = (float) element;}
        //    else if (element instanceof String) {String stringvalue = (String) element;}
        //};

        //Motherboard info
        Baseboard baseboard = computerSystem.getBaseboard();
        boardModel = baseboard.getModel();
        this.baseboard = baseboard.getManufacturer();
        bios = firmware.getName(); // "getVersion()???"
        // Pobieranie informacji o RAM
        GlobalMemory memory = hal.getMemory();
        ramGB = memory.getTotal() / (1024 * 1024 * 1000); // Konwersja bajtów do GB


        // Pobieranie informacji o dysku
        long totalDiskSpace = 0;
        String diskTypeDetected = "Unknown";



        for (HWDiskStore disk : hal.getDiskStores()) {
            totalDiskSpace += disk.getSize();



            if (diskTypeDetected.equals("Unknown") && disk.getModel().toLowerCase().contains("ssd")) {
                diskTypeDetected = "SSD";
            } else if (diskTypeDetected.equals("Unknown") && disk.getModel().toLowerCase().contains("hdd")) {
                diskTypeDetected = "HDD";
            }
        }

        this.diskSpaceGB = totalDiskSpace / (1024 * 1024 * 1024); // Konwersja bajtów do GB
        this.diskType = diskTypeDetected;
    }

    public double getCpuGhz() {
        return Double.valueOf(ghz[5]);
    }

    public String getCpuModel() {
        return String.valueOf(ghz[2]);
    }

    public int getCpuGeneration() {
        return cpuGeneration;
    }

    public long getRamGB() {
        return ramGB;
    }

    public long getDiskSpaceGB() {
        return diskSpaceGB;
    }

    public String getDiskType() {
        return diskType;
    }

    public String getBaseboard() {
        return baseboard;
    }

    public String getBoardModel() {
        return boardModel;
    }

    public String getBios() {
        return bios;
    }
}