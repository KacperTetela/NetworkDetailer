package networkdetailer.com.model.data;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public boolean saveToExcel() {
        try {
            // Pobierz ścieżkę do katalogu, gdzie znajduje się JAR (lub katalog w środowisku deweloperskim)
            Path jarPath = Paths.get(DataCollector.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            String filePath = jarPath.resolve("network_details.xlsx").toString();

            File file = new File(filePath);
            Workbook workbook;
            Sheet sheet;
            int rowCount = 0;

            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                rowCount = sheet.getLastRowNum();
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Network Details");
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("IP Address");
                headerRow.createCell(1).setCellValue("Host Name");
                headerRow.createCell(2).setCellValue("MAC Address");
            }

            Row row = sheet.createRow(rowCount + 1);
            row.createCell(0).setCellValue(getiP());
            row.createCell(1).setCellValue(getHostName());
            row.createCell(2).setCellValue(getMac());

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

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
