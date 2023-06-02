package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Profilo {
    public JFrame frame;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JPanel profilePanel;
    private JTextField usernameField;
    private JButton modificaDatiUtenteButton;
    private JButton annullaButton;
    private JButton salvaModificheButton;
    private JPasswordField passwordField1;
    private JTextField nameField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JPasswordField passwordField2;
    private JTextField pIVAField;
    private JLabel pIVALabel;
    private JLabel ripPassLabel;
    private JLabel usernameErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel partitaivaErrorLabel;
    private JLabel oldPassLabel;
    private JPasswordField oldPassField;
    private JLabel oldPassNoteLabel;
    private JLabel oldPassErrorLabel;
    private JLabel newPassErrorLabel;
    private JPopupMenu utenteMenu;

    public Profilo (JFrame frameC, Controller controller){
        //homeButton.setBorder(BorderFactory.createEmptyBorder());
        frame = new JFrame("Profilo");
        frame.setUndecorated(true);
        frame.setContentPane(profilePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        annullaButton.setVisible(false);
        salvaModificheButton.setVisible(false);
        usernameField.setEnabled(false);
        nameField.setEnabled(false);
        cognomeField.setEnabled(false);
        emailField.setEnabled(false);
        passwordField1.setEnabled(false);
        ripPassLabel.setVisible(false);
        passwordField2.setVisible(false);
        pIVAField.setEnabled(false);
        oldPassLabel.setVisible(false);
        oldPassField.setVisible(false);
        oldPassNoteLabel.setVisible(false);

        usernameErrorLabel.setVisible(false);
        emailErrorLabel.setVisible(false);
        partitaivaErrorLabel.setVisible(false);
        oldPassErrorLabel.setVisible(false);
        newPassErrorLabel.setVisible(false);


        usernameField.setText(controller.getUsername());
        nameField.setText(controller.getNome());
        cognomeField.setText(controller.getCognome());
        emailField.setText(controller.getEmail());
        passwordField1.setText(controller.getPassword());
        pIVAField.setText(controller.getPartitaIva());


        utenteMenu = new JPopupMenu();
        JMenuItem utenteExit = new JMenuItem("Logout");
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");
        utenteMenu.add(utenteLibrerie);
        utenteMenu.add(utenteExit);

        if (controller.getPartitaIva() == null) {
            utenteLibrerie.setVisible(false);
            System.out.println(controller.getPartitaIva());
        }

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getX(), utenteButton.getY() + 25);
                }
            }
        });

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(frameC, controller);
                hp.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        modificaDatiUtenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaDatiUtenteButton.setEnabled(false);
                salvaModificheButton.setVisible(true);
                annullaButton.setVisible(true);
                passwordField2.setVisible(true);
                ripPassLabel.setVisible(true);
                oldPassLabel.setVisible(true);
                oldPassField.setVisible(true);
                oldPassNoteLabel.setVisible(true);
                passwordField1.setText("");

                usernameField.setEnabled(true);
                nameField.setEnabled(true);
                cognomeField.setEnabled(true);
                emailField.setEnabled(true);
                passwordField1.setEnabled(true);
                pIVAField.setEnabled(true);
            }
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaDatiUtenteButton.setEnabled(true);
                annullaButton.setVisible(false);
                salvaModificheButton.setVisible(false);
                usernameField.setEnabled(false);
                nameField.setEnabled(false);
                cognomeField.setEnabled(false);
                emailField.setEnabled(false);
                passwordField1.setEnabled(false);
                ripPassLabel.setVisible(false);
                passwordField2.setVisible(false);
                pIVAField.setEnabled(false);
                oldPassLabel.setVisible(false);
                oldPassField.setVisible(false);
                oldPassNoteLabel.setVisible(false);

                newPassErrorLabel.setVisible(false);
                partitaivaErrorLabel.setVisible(false);
                usernameErrorLabel.setVisible(false);
                emailErrorLabel.setVisible(false);
                oldPassErrorLabel.setVisible(false);

                usernameField.setText(controller.getUsername());
                nameField.setText(controller.getNome());
                cognomeField.setText(controller.getCognome());
                emailField.setText(controller.getEmail());
                passwordField1.setText(controller.getPassword());
                passwordField2.setText("");
                pIVAField.setText(controller.getPartitaIva());
                oldPassField.setText("");
            }
        });
        salvaModificheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailU = emailField.getText();
                String nomeU = nameField.getText();
                String cognomeU = cognomeField.getText();
                String usernameU = usernameField.getText();
                char[] password1U = passwordField1.getPassword();
                char[] password2U = passwordField2.getPassword();
                String pass1 = new String(password1U);
                String pass2 = new String(password2U);
                String partitaIVA = pIVAField.getText();
                char[] password3U = oldPassField.getPassword();
                String pass3 = new String(password3U);


                if(!controller.getPassword().equals(pass3)) oldPassErrorLabel.setVisible(true);
                else {
                    oldPassErrorLabel.setVisible(false);
                    int[] error = controller.validaModUtente(emailU, usernameU, partitaIVA);
                    if (error[0] != 0) emailErrorLabel.setVisible(true);
                    if (error[1] != 0) usernameErrorLabel.setVisible(true);
                    if (error[2] != 0) partitaivaErrorLabel.setVisible(true);
                    if (error[0] == 0) emailErrorLabel.setVisible(false);
                    if (error[1] == 0) usernameErrorLabel.setVisible(false);
                    if (error[2] == 0) partitaivaErrorLabel.setVisible(false);
                    if(!pass1.equals(pass2)){
                        newPassErrorLabel.setVisible(true);
                        error[3] = 1;
                    }
                    if (error[0] == 0 && error[1] == 0 && error[2] == 0 && error[3] == 0){
                        System.out.println("apposto");
                        modificaDatiUtenteButton.setEnabled(true);
                        annullaButton.setVisible(false);
                        salvaModificheButton.setVisible(false);
                        usernameField.setEnabled(false);
                        nameField.setEnabled(false);
                        cognomeField.setEnabled(false);
                        emailField.setEnabled(false);
                        passwordField1.setEnabled(false);
                        ripPassLabel.setVisible(false);
                        passwordField2.setVisible(false);
                        pIVAField.setEnabled(false);
                        oldPassLabel.setVisible(false);
                        oldPassField.setVisible(false);
                        oldPassNoteLabel.setVisible(false);

                        if(pass1.isBlank()) controller.modUtente(emailU, nomeU, cognomeU, usernameU, controller.getPassword(), partitaIVA);
                        else controller.modUtente(emailU, nomeU, cognomeU, usernameU, pass1, partitaIVA);


                        usernameField.setText(controller.getUsername());
                        nameField.setText(controller.getNome());
                        cognomeField.setText(controller.getCognome());
                        emailField.setText(controller.getEmail());
                        passwordField1.setText(controller.getPassword());
                        pIVAField.setText(controller.getPartitaIva());

                        passwordField2.setText("");
                        oldPassField.setText("");

                        newPassErrorLabel.setVisible(false);
                        partitaivaErrorLabel.setVisible(false);
                        usernameErrorLabel.setVisible(false);
                        emailErrorLabel.setVisible(false);
                    }
                }
            }
        });
    }

}

