package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.*;


public class LSForm {
    private Controller controller;
    private static JFrame frame;
    private JButton btLogin;
    private JButton btSignIn;
    private JLabel txtPF;
    private JLabel image;
    private JPanel lsPanel;
    private static ImageIcon image2;

    public LSForm() {
        controller = new Controller();
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(0, frame, controller);
                frame.setEnabled(false);
            }
        });
        btSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(1, frame, controller);
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

