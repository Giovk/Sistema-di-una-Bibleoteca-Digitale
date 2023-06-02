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
        frame = new JFrame("Profilo");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(profilePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);//imposta largezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
        salvaModificheButton.setVisible(false); //rende invisibile il JButton 'salvaModificheButton'
        usernameField.setEnabled(false);    //disabilita il JTextField 'usernameField'
        nameField.setEnabled(false);    //disabilita il JTextField 'nameField'
        cognomeField.setEnabled(false); //disabilita il JTextField 'cognomeField'
        emailField.setEnabled(false);   //disabilita il JTextField 'emailField'
        passwordField1.setEnabled(false); //disabilita il JPasswordField 'passwordField1'
        ripPassLabel.setVisible(false); //rende invisibile la JLabel 'ripPassLabel'
        passwordField2.setVisible(false);//rende invisibile il JPasswordField 'passwordField2'
        pIVAField.setEnabled(false);    //disabilita il JTextField 'pIVAField'
        oldPassLabel.setVisible(false);     //rende invisibile la JLabel 'oldPassLabel'
        oldPassField.setVisible(false); //rende invisibile JPasswordField 'oldPassField'
        oldPassNoteLabel.setVisible(false); //rende invisibile la JLabel 'oldPassNoteLabel'

        usernameErrorLabel.setVisible(false);   //rende invisibile la JLabel 'usernameErrorLabel'
        emailErrorLabel.setVisible(false);  //rende invisibile la JLabel 'emailErrorLabel'
        partitaivaErrorLabel.setVisible(false); //rende invisibile la JLabel 'partitaivaErrorLabel'
        oldPassErrorLabel.setVisible(false);    //rende invisibile la JLabel 'oldPassErrorLabel'
        newPassErrorLabel.setVisible(false);    //rende invisibile la JLabel 'newPassErrorLabel'

        usernameField.setText(controller.getUsername()); //imposta il testo di 'usernameField' con l'username dell'utente
        nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
        cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
        emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
        passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
        pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVA' con la partita IVA dell'utente

        utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem utenteExit = new JMenuItem("Logout"); //crea la voce del menu "Logout"
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        if (controller.getPartitaIva() == null) {   //controlla se l utente non ha registrato una partita IVA
            utenteLibrerie.setVisible(false);
        }

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getX(), utenteButton.getY() + 25);   //mostra le voci del menu 'utenteMenu'
                }
            }
        });

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);    //rende visibile il frame chiamante
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                hp.frame.setVisible(true);  //rende visibile il frame 'hp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        modificaDatiUtenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaDatiUtenteButton.setEnabled(false); //abilita il JButton 'modificaDatiUtenteButton'
                salvaModificheButton.setVisible(true);  //rende visibile il JButton 'salvaModificheButton'
                annullaButton.setVisible(true); //rende visibile il JButton 'annullaButton'
                passwordField2.setVisible(true);    //rende visibile il JPasswordField 'passwordField2'
                ripPassLabel.setVisible(true);  //rende visibile la JLabel 'ripPassLabel'
                oldPassLabel.setVisible(true);  //rende visibile la JLabel 'oldPassLabel'
                oldPassField.setVisible(true);  //rende visibile il JPasswordLabel 'oldPassField'
                oldPassNoteLabel.setVisible(true);  //rende visibile la JLabel 'oldPassNoteLabel'
                passwordField1.setText(""); //imposta il testo del JPasswordField alla stringa vuota

                usernameField.setEnabled(true); //abilita il JTextField 'usernameField'
                nameField.setEnabled(true); //abilita il JTextField 'nameField'
                cognomeField.setEnabled(true);  //abilita il JTextField 'cognomeField'
                emailField.setEnabled(true);    //abilita il JTextField 'emailField'
                passwordField1.setEnabled(true);    //abilita il JTextField 'passwordField'
                pIVAField.setEnabled(true); //abilita il JTextField 'pIVAField'
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

