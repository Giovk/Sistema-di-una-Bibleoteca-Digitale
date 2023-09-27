package GUI;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class InstallFont {

    public InstallFont() {
        String fontFilePath = this.getClass().getResource("/BebasNeue-Regular.ttf").toString().substring(6, this.getClass().getResource("/BebasNeue-Regular.ttf").toString().length()); // Sostituisci con il percorso del tuo file font

        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println(fontFilePath);

        if (osName.contains("win")) {
            // Codice per Windows
            boolean success = installFontOnWindows(fontFilePath);

            if (success) {
                System.out.println("Il font è stato installato con successo su Windows.");
            } else {
                System.err.println("Si è verificato un errore durante l'installazione del font su Windows.");
            }
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

    private static boolean installFontOnWindows(String fontFilePath) {
        try {
            // Copia il file del font nella directory dei font di Windows
            File fontFile = new File(fontFilePath);
            File destinationDir = new File(System.getProperty("user.home") + "\\AppData\\Local\\Microsoft\\Windows\\Fonts");
            File destinationFile = new File(destinationDir, fontFile.getName());

            if (!destinationDir.exists() && !destinationDir.mkdirs()) {
                return false;
            }

            if (!fontFile.exists()) {
                System.err.println("Il file del font non esiste.");
                return false;
            }

            if (destinationFile.exists()) {
                System.err.println("Il font è già presente nella directory dei font di Windows.");
                return true; // Il font è già installato
            }

            java.nio.file.Files.copy(fontFile.toPath(), destinationFile.toPath());

            // Registra il font nel registro di sistema
            String fontRegistryPath = "Software\\Microsoft\\Windows NT\\CurrentVersion\\Fonts";
            Preferences userRoot = Preferences.userRoot();
            Preferences fontRegistry = userRoot.node(fontRegistryPath);
            fontRegistry.put(fontFile.getName(), destinationFile.getAbsolutePath());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void installFontOnMac(String fontFilePath) {
        // Codice per installare il font su macOS
        // Devi utilizzare gli strumenti di sistema di macOS per l'installazione del font
        // Questo codice dovrebbe essere implementato separatamente.
    }

    private static void installFontOnUnix(String fontFilePath) {
        Process process;
        try {
            // Esegui il comando per copiare il font nella directory dei font
            String copyCommand = "cp " + fontFilePath + " /usr/share/fonts/truetype/";
            process = Runtime.getRuntime().exec(copyCommand);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Si è verificato un errore durante l'installazione del font su Unix/Lunix.");
            }

            // Aggiorna la cache dei font
            String updateCacheCommand = "fc-cache -f -v";
            process = Runtime.getRuntime().exec(updateCacheCommand);

            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.err.println("Si è verificato un errore durante l'installazione del font su Unix/Lunix.");
            }

            System.out.println("Il font è stato installato con successo su Unix/Linux.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Si è verificato un errore durante l'installazione del font su Unix/Lunix.");
        }
    }
}

