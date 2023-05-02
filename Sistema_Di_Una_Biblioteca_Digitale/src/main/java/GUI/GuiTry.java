package GUI;

import javax.swing.*;
import java.awt.*;

public class GuiTry extends JFrame {
    private JTextArea textArea;
    private JButton button;

    public GuiTry(){
        super ("Biblioteca Digitale");

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        button = new JButton("Ciao");

        add(textArea, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_START);

        setSize(800, 500);

        setLocationRelativeTo(null);

        setResizable(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setVisible(true);
    }
}

