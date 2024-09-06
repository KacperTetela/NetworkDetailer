package networkdetailer.com.model.hardware;

public class RequirementsCheckerService {
    private static final int MIN_INTEL_CPU_GENERATION = 8; // Minimum requirements
    private static final int MIN_AMD_CPU_GENERATION = 2;
    private static final long MIN_RAM_GB = 4;
    private static final long MIN_DISK_SPACE_GB = 64;

    public static boolean check(HardwareDownloader specs) {
        boolean cpuOk = specs.getCpuGeneration() >= MIN_INTEL_CPU_GENERATION;
        boolean ramOk = specs.getRamGB() >= MIN_RAM_GB;
        boolean diskOk = specs.getDiskSpaceGB() >= MIN_DISK_SPACE_GB;

        if (cpuOk && ramOk && diskOk) {
            System.out.println("The hardware meets the minimum system requirements for Windows 11");
            System.out.println("Disk type: " + specs.getDiskType());
            return true;
        } else {
            System.out.println("Your hardware does not meet the minimum system requirements for Windows 11");
            if (!cpuOk) System.out.println("- CPU does not meet generation requirements (min. 8)");
            if (!ramOk) System.out.println("- RAM does not meet requirements");
            if (!diskOk) System.out.println("- The disk does not meet the requirements");
            System.out.println("Disk type: " + specs.getDiskType());
            return false;
        }
    }
}