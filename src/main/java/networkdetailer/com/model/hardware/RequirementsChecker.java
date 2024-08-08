package networkdetailer.com.model.hardware;

public class RequirementsChecker {
    private static final int MIN_CPU_GENERATION = 8; // Minimalna generacja CPU
    private static final long MIN_RAM_GB = 4;
    private static final long MIN_DISK_SPACE_GB = 64;


    public static boolean check(SystemSpecsDownloader specs) {
        boolean cpuOk = specs.getCpuGeneration() >= MIN_CPU_GENERATION;
        boolean ramOk = specs.getRamGB() >= MIN_RAM_GB;
        boolean diskOk = specs.getDiskSpaceGB() >= MIN_DISK_SPACE_GB;

        if (cpuOk && ramOk && diskOk) {
            System.out.println("Sprzęt spełnia minimalne wymagania systemowe dla Windows 11.");
            System.out.println("Typ dysku: " + specs.getDiskType());
            return true;
        } else {
            System.out.println("Sprzęt nie spełnia minimalnych wymagań systemowych dla Windows 11.");
            if (!cpuOk) System.out.println("- CPU nie spełnia wymagań generacji (min. 8).");
            if (!ramOk) System.out.println("- RAM nie spełnia wymagań.");
            if (!diskOk) System.out.println("- Dysk nie spełnia wymagań.");
            System.out.println("Typ dysku: " + specs.getDiskType());
            return false;
        }
    }
}