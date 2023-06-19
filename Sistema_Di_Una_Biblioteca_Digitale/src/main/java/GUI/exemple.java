package GUI;

import javax.swing.*;

public class Exemple extends JPanel {
    public JPanel panel;
    public JLabel l = new JLabel("ciao");
    public Exemple(){
        add(l);
        setSize(50,100);
    }
}
