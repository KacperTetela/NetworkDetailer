package networkdetailer.com.model.data;

import networkdetailer.com.model.hardware.RequirementsChecker;
import networkdetailer.com.model.hardware.HardwareDownloader;
import networkdetailer.com.model.network.NetworkDownloader;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataCollector {
    private static DataCollector instance;

    private CPUData cpuData;
    private MOBOData moboData;
    private MemoryData memoryData;
    private NetworkData networkData;

    private NetworkDownloader networkDownloader;
    private HardwareDownloader hardwareDownloader;

    private DataCollector() {
        networkDownloader = new NetworkDownloader();
        hardwareDownloader = new HardwareDownloader();
        refresh();
    }

    public static synchronized DataCollector getInstance() {
        if (instance == null) {
            instance = new DataCollector();
        }
        return instance;
    }

    public synchronized void refresh() {
        networkData = networkDownloader.get();
        hardwareDownloader.initialise();
        cpuData = hardwareDownloader.getCpuData();
        moboData = hardwareDownloader.getMoboData();
        memoryData = hardwareDownloader.getMemoryData();
    }

    public int saveToExcel() {
        /**
         * int = 0 Error
         * int = 1 Success
         * int = 2 Hostname already exists
         */
        Path jarPath;
        try {
            jarPath = Paths.get(DataCollector.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return 0;
        }

        String filePath = jarPath.resolve("network_details.xlsx").toString();
        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;
        boolean hostnameExists = false;

        try {
            if (file.exists()) {
                // Load existing workbook
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();

                // Check for existing hostname and update if found
                int rowCount = sheet.getLastRowNum();
                for (int i = 1; i <= rowCount; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null && row.getCell(0) != null && row.getCell(0).getStringCellValue().equals(getHostName())) {
                        // Update existing row
                        row.getCell(0).setCellValue(getHostName());
                        row.getCell(1).setCellValue(getIp());
                        row.getCell(2).setCellValue(getMac());
                        row.getCell(3).setCellValue(getCpuName());
                        row.getCell(4).setCellValue(getCpuGen());
                        row.getCell(5).setCellValue(getCpuGhz());
                        row.getCell(6).setCellValue(getRam());
                        row.getCell(7).setCellValue(getDiskSpace());
                        row.getCell(8).setCellValue(getDiskType());
                        row.getCell(9).setCellValue(getBios());
                        row.getCell(10).setCellValue(isWindowsRequirements() ? "Yes" : "No");
                        hostnameExists = true;
                        break;
                    }
                }

                // Add new row if hostname does not exist
                if (!hostnameExists) {
                    Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                    newRow.createCell(0).setCellValue(getHostName());
                    newRow.createCell(1).setCellValue(getIp());
                    newRow.createCell(2).setCellValue(getMac());
                    newRow.createCell(3).setCellValue(getCpuName());
                    newRow.createCell(4).setCellValue(getCpuGen());
                    newRow.createCell(5).setCellValue(getCpuGhz());
                    newRow.createCell(6).setCellValue(getRam());
                    newRow.createCell(7).setCellValue(getDiskSpace());
                    newRow.createCell(8).setCellValue(getDiskType());
                    newRow.createCell(9).setCellValue(getBios());
                    newRow.createCell(10).setCellValue(isWindowsRequirements() ? "Yes" : "No");
                }
            } else {
                // Create new workbook and sheet
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Network Details");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Host Name");
                headerRow.createCell(1).setCellValue("IP Address");
                headerRow.createCell(2).setCellValue("Physical Address");
                headerRow.createCell(3).setCellValue("CPU Name");
                headerRow.createCell(4).setCellValue("CPU Generation");
                headerRow.createCell(5).setCellValue("CPU GHz");
                headerRow.createCell(6).setCellValue("RAM (GB)");
                headerRow.createCell(7).setCellValue("Disk Space (GB)");
                headerRow.createCell(8).setCellValue("Disk Type");
                headerRow.createCell(9).setCellValue("BIOS");
                headerRow.createCell(10).setCellValue("Windows Requirements");

                // Add new row
                Row newRow = sheet.createRow(1);
                newRow.createCell(0).setCellValue(getHostName());
                newRow.createCell(1).setCellValue(getIp());
                newRow.createCell(2).setCellValue(getMac());
                newRow.createCell(3).setCellValue(getCpuName());
                newRow.createCell(4).setCellValue(getCpuGen());
                newRow.createCell(5).setCellValue(getCpuGhz());
                newRow.createCell(6).setCellValue(getRam());
                newRow.createCell(7).setCellValue(getDiskSpace());
                newRow.createCell(8).setCellValue(getDiskType());
                newRow.createCell(9).setCellValue(getBios());
                newRow.createCell(10).setCellValue(isWindowsRequirements() ? "Yes" : "No");
            }

            // Write the changes to the file
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }
            workbook.close();

            return hostnameExists ? 2 : 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean saveToTxt() {
        try {
            Path jarPath = Paths.get(DataCollector.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            String filePath = jarPath.resolve(networkData.hostname() + ".txt").toString();

            File file = new File(filePath);

            FileWriter writer = new FileWriter(file);
            writer.write("IP Address: " + getIp() + System.lineSeparator());
            writer.write("Host Name: " + getHostName() + System.lineSeparator());
            writer.write("MAC Address: " + getMac() + System.lineSeparator());
            writer.write("CPU Name: " + getCpuName() + System.lineSeparator());
            writer.write("CPU Generation: " + getCpuGen() + System.lineSeparator());
            writer.write("CPU GHz: " + getCpuGhz() + System.lineSeparator());
            writer.write("RAM (GB): " + getRam() + System.lineSeparator());
            writer.write("Disk Space (GB): " + getDiskSpace() + System.lineSeparator());
            writer.write("Disk Type: " + getDiskType() + System.lineSeparator());
            writer.write("BIOS Version: " + getBios() + System.lineSeparator());
            writer.write("Windows Requirements: " + (isWindowsRequirements() ? "Yes" : "No") + System.lineSeparator());
            writer.close();

            return true;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getIp() {
        return networkData.ip();
    }

    public String getHostName() {
        return networkData.hostname();
    }

    public String getMac() {
        return networkData.mac();
    }

    public String getCpuName() {
        return cpuData.name();
    }

    public String getCpuGen() {
        return new String(String.valueOf(cpuData.generation().generation()));
    }

    public String getCpuGhz() {
        return new String(String.valueOf(cpuData.ghz()));
    }

    public String getRam() {
        return new String(String.valueOf(memoryData.ramGB()));
    }

    public String getDiskSpace() {
        return new String(String.valueOf(memoryData.diskSpaceGB()));
    }

    public String getDiskType() {
        return new String(String.valueOf(memoryData.diskType()));
    }

    public boolean isWindowsRequirements() {
        return RequirementsChecker.check(cpuData, memoryData);
    }

    public String getBios() {
        return new String(String.valueOf(moboData.bios()));
    }
}
