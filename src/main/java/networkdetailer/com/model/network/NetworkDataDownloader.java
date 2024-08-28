package networkdetailer.com.model.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkDataDownloader {
    public static String[] get() {
        try {
            InetAddress ip = null;
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                // Ignorowanie interfejsów wirtualnych
                if (networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();

                    // Sprawdzanie, czy adres jest IPv4 i czy nie jest adresem lokalnym (127.0.0.1)
                    if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(':') == -1) {
                        ip = inetAddress;
                        break;
                    }
                }

                if (ip != null) {
                    break;
                }
            }

            if (ip == null) {
                throw new RuntimeException("Could not find a suitable network interface");
            }

            String hostName = ip.getHostName();

            // Usuń dopisek (np. ".home") z nazwy hosta
            if (hostName.contains(".")) {
                hostName = hostName.split("\\.")[0];
            }

            String macAddress = MacGetter.get(ip);

            System.out.println("Adres IP: " + ip.getHostAddress());
            System.out.println("Nazwa hosta: " + hostName);
            System.out.println("Adres MAC: " + macAddress);

            String[] tempArr = new String[3];
            tempArr[0] = ip.getHostAddress();
            tempArr[1] = hostName;
            tempArr[2] = macAddress;
            return tempArr;

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("IP data not initialized");
    }
}