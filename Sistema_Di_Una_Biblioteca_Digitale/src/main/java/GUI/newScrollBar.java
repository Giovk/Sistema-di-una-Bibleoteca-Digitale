package GUI;

import java.awt.*;
import javax.swing.*;

/**
 * The type New scroll bar.
 */
public class newScrollBar extends JScrollBar {

    /**
     * Instantiates a new New scroll bar.
     */
    public newScrollBar() {
        setUI(new NewScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(34, 40, 49));
        setBackground(new Color(0xCF9E29));
        setUnitIncrement(20);
    }
}

