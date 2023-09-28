package GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.prefs.Preferences;

public class InstallFont {
    public InstallFont() {
        String fontFilePath = this.getClass().getResource("/BebasNeue-Regular.ttf").toString().substring(6, this.getClass().getResource("/BebasNeue-Regular.ttf").toString().length()); // Sostituisci con il percorso del tuo file font
        File fontFile = new File(fontFilePath);
        try {

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

