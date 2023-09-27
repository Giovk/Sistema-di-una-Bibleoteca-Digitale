package GUI;

import java.io.IOException;

public class InstallFont {

    public InstallFont() {
        String fontFilePath = this.getClass().getResource("/BebasNeue-Regular.ttf").toString(); // Sostituisci con il percorso del tuo file font

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            // Codice per Windows
            installFontOnWindows(fontFilePath);
        } else if (osName.contains("mac")) {
            // Codice per macOS
            installFontOnMac(fontFilePath);
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("unix")) {
            // Codice per sistemi Unix/Linux
            installFontOnUnix(fontFilePath);
        } else {
            System.err.println("Sistema operativo non supportato.");
        }
    }

    private static void installFontOnWindows(String fontFilePath) {
        // Codice per installare il font su Windows
        try {
            Process process = Runtime.getRuntime().exec("cmd /c copy " + fontFilePath + " C:\\Windows\\Fonts\\");
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Il font è stato installato con successo su Windows.");
            } else {
                System.err.println("Si è verificato un errore durante l'installazione del font su Windows.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void installFontOnMac(String fontFilePath) {
        // Codice per installare il font su macOS
        // Devi utilizzare gli strumenti di sistema di macOS per l'installazione del font
        // Questo codice dovrebbe essere implementato separatamente.
    }

    private static void installFontOnUnix(String fontFilePath) {
        // Codice per installare il font su sistemi Unix/Linux
        // Devi utilizzare gli strumenti di sistema appropriati per l'installazione del font
        // Questo codice dovrebbe essere implementato separatamente.
    }
}

