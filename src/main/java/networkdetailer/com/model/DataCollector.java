package networkdetailer.com.model;

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
    private String iP;
    private String hostName;
    private String mac;

    private DataCollector() {
        refresh();
    }

    public static synchronized DataCollector getInstance() {
        if (instance == null) {
            instance = new DataCollector();
        }
        return instance;
    }

    public synchronized void refresh() {
        String[] dataArray = DataDownloader.get();
        this.iP = dataArray[0];
        this.hostName = dataArray[1];
        this.mac = dataArray[2];
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
                        row.getCell(1).setCellValue(getiP());
                        row.getCell(2).setCellValue(getMac());
                        hostnameExists = true;
                        break;
                    }
                }

                // Add new row if hostname does not exist
                if (!hostnameExists) {
                    Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                    newRow.createCell(0).setCellValue(getHostName());
                    newRow.createCell(1).setCellValue(getiP());
                    newRow.createCell(2).setCellValue(getMac());
                }
            } else {
                // Create new workbook and sheet
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Network Details");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Host Name");
                headerRow.createCell(1).setCellValue("IP Address");
                headerRow.createCell(2).setCellValue("Physical Address");

                // Add new row
                Row newRow = sheet.createRow(1);
                newRow.createCell(0).setCellValue(getHostName());
                newRow.createCell(1).setCellValue(getiP());
                newRow.createCell(2).setCellValue(getMac());
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
            String filePath = jarPath.resolve(hostName + ".txt").toString();

            File file = new File(filePath);

            FileWriter writer = new FileWriter(file);
            writer.write("IP Address: " + getiP() + System.lineSeparator());
            writer.write("Host Name: " + getHostName() + System.lineSeparator());
            writer.write("MAC Address: " + getMac() + System.lineSeparator());
            writer.close();

            return true;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getiP() {
        return iP;
    }

    public String getHostName() {
        return hostName;
    }

    public String getMac() {
        return mac;
    }
}
