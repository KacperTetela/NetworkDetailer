package networkdetailer.com.model;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Connection {
    public static void main(String[] args) {
        try {
            // Pobranie lokalnego adresu IP komputera
            InetAddress ip = InetAddress.getLocalHost();

            // Pobranie nazwy hosta
            String hostName = ip.getHostName();

            MacAddressDownloader macAddressDownloader = new MacAddressDownloader();
            String macAddress = macAddressDownloader.get(ip);

            // Wyświetlenie wyników
            System.out.println("Adres IP: " + ip.getHostAddress());
            System.out.println("Nazwa hosta: " + hostName);
            System.out.println("Adres MAC: " + macAddress);

        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
}
