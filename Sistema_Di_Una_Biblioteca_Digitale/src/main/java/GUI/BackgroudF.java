package GUI;

import javax.swing.*;
import java.awt.*;

public class BackgroudF extends JFrame {
    private JButton b1;
    private JLabel l1;

    public BackgroudF()
    {
        setTitle("Background Color for JFrame");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        /*
        One way
        -----------------
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
        add(background);
        background.setLayout(new FlowLayout());
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        background.add(l1);
        background.add(b1);
        */

        // Another way
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:/Users/giovk/Documents/GitHub/Sistema-di-una-Bibleoteca-Digitale/Sistema_Di_Una_Biblioteca_Digitale/src/main/java/GUI/Biblioteca.png")));
        setLayout(new FlowLayout());
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        add(l1);
        add(b1);
        // Just for refresh :) Not optional!
        setSize(399,399);
        setSize(400,400);
    }

    public static void main(String args[])
    {
        new BackgroudF();
    }
}
