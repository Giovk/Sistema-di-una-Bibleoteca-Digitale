package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VoidButton extends JButton {
    public VoidButton(){
        setContentAreaFilled(false);

        setBorder(new EmptyBorder(3,3,3,3));
    }
}
