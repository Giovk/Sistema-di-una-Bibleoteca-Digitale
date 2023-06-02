package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.*;


public class LSForm {
    private Controller controller;
    private static JFrame frame;
    private JButton btLogin;
    private JButton btSignUp;
    private JLabel txtPF;
    private JLabel image;
    private JPanel lsPanel;
    private ImageIcon image2;

    public LSForm() {
        controller = new Controller();

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(0, frame, controller); //chiama il frame 'lsDialog'
                frame.setEnabled(false); //disabilita il frame
            }
        });

        btSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LSDialog lsDialog = new LSDialog(1, frame, controller); //chiama il frame 'lsDialog'
                frame.setEnabled(false);//disabilita il frame
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(new LSForm().lsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);   //imposta largezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);
    }
}

