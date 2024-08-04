package networkdetailer.com.model;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DataDownloader {
    public static String[] get() {
        try {
            InetAddress ip = InetAddress.getLocalHost();

            String hostName = ip.getHostName();

            MacGetter macGetter = new MacGetter();
            String macAddress = macGetter.get(ip);

            System.out.println("Adres IP: " + ip.getHostAddress());
            System.out.println("Nazwa hosta: " + hostName);
            System.out.println("Adres MAC: " + macAddress);

            String[] tempArr = new String[3];
            tempArr[0] = ip.getHostAddress();
            tempArr[1] = hostName;
            tempArr[2] = macAddress;
            return tempArr;

        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("IP data not initialized");
    }
}
