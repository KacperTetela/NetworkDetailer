package networkdetailer.com.model.hardware;

public class CpuGenerationCheckerService {

    public static int launch(String cpuModel) {
        // Przykładowa nazwa procesora: Intel(R) Core(TM) i7-8650U, Intel(R) Core(TM) i5-1035G1 lub AMD Ryzen 7 3700X
        if (cpuModel.contains("Intel") || cpuModel.contains("AMD")) {
            String[] parts = cpuModel.split("-");
            if (parts.length > 1) {
                String modelNumber = parts[1];

                if (cpuModel.contains("Intel")) {
                    if (modelNumber.length() >= 4) {
                        char firstChar = modelNumber.charAt(0);
                        if (Character.isDigit(firstChar)) {
                            int firstDigit = Character.getNumericValue(firstChar);
                            if (firstDigit == 1 && modelNumber.length() >= 5) {
                                // Obsługa procesorów 10. generacji i wyżej, np. 1035G1
                                char secondChar = modelNumber.charAt(1);
                                if (Character.isDigit(secondChar)) {
                                    int secondDigit = Character.getNumericValue(secondChar);
                                    return 10 + secondDigit; // Dla 10. generacji i wyższych
                                }
                            } else {
                                return firstDigit; // Dla starszych generacji, np. i7-8650U
                            }
                        }
                    }
                } else if (cpuModel.contains("AMD")) {
                    String[] amdParts = modelNumber.split(" ");
                    if (amdParts.length > 1) {
                        String amdModel = amdParts[1];
                        if (amdModel.length() >= 1) {
                            char firstChar = amdModel.charAt(0);
                            if (Character.isDigit(firstChar)) {
                                int firstDigit = Character.getNumericValue(firstChar);
                                return firstDigit; // Generacja procesora AMD, np. Ryzen 7 3700X to 3. generacja
                            }
                        }
                    }
                }
            }
        }
        return -1; // Nieznana generacja
    }
}
