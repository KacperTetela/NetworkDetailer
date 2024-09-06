package networkdetailer.com.model.network;

import networkdetailer.com.model.data.NetworkData;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class NetworkDownloader {
    private InetAddress ip;
    private String ipHostAddress;
    private String macAddress;
    private String hostName;

    public NetworkData get() {
        try {
            ip = IPGetter.get();
            ipHostAddress = ip.getHostAddress();
            macAddress = MacGetter.get(ip);
            hostName = ip.getHostName();

            // Remove the suffix (e.g. ".home") from the hostname
            if (hostName.contains(".")) {
                hostName = hostName.split("\\.")[0];
            }

            System.out.println("IP Address: " + ipHostAddress);
            System.out.println("MAC Address: " + macAddress);
            System.out.println("Hostname: " + hostName);

            return new NetworkData(ipHostAddress, hostName, macAddress);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("IP data not initialized");
    }
}