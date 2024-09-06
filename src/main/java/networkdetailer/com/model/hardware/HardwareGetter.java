package networkdetailer.com.model.hardware;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;

public class HardwareGetter {
    public synchronized static SystemInfo getSystemInfo() {
        return new SystemInfo();
    }

    public synchronized static ComputerSystem getComputerSystem() {
        ComputerSystem computerSystem = getSystemInfo().getHardware().getComputerSystem();
        return computerSystem;
    }

    public synchronized static HardwareAbstractionLayer getHal() {
        return getSystemInfo().getHardware();
    }

    public static Baseboard getBaseboard() {
        return getComputerSystem().getBaseboard();
    }
}
