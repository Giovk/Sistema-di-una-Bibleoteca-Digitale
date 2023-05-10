package GUI;

import javax.swing.*;
import java.awt.event.*;

public class LSForm {
    private static JFrame frame;
    private JButton btLogin;
    private JButton btSignIn;
    private JLabel txtPF;
    private JLabel image;
    private JPanel lsPanel;
    private static ImageIcon image2;

    public LSForm() {
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(0, frame);
                frame.setEnabled(false);
            }
        });
        btSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(1, frame);
                frame.setEnabled(false);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true);
        frame.setContentPane(new LSForm().lsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

