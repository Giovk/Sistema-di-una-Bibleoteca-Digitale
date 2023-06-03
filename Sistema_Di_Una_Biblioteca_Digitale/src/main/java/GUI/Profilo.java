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
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
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
                modificaDatiUtenteButton.setEnabled(true);  //abilita il JButton 'modificaDatiUtenteButton'
                annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
                salvaModificheButton.setVisible(false); //rende invisibile il JButton 'salvaButton'
                usernameField.setEnabled(false);    //disabilita il JTextField 'usernameField'
                nameField.setEnabled(false);    //disabilita il JTextField 'nameField'
                cognomeField.setEnabled(false); //disabilita il JTextField 'cognomeField'
                emailField.setEnabled(false);   //disabilita il JTextField 'emailField'
                passwordField1.setEnabled(false);   //disabilita il JPasswordField 'passwordField1'
                ripPassLabel.setVisible(false); //rende invisibile la JLabel 'ripPassLabel'
                passwordField2.setVisible(false);   //rende invisibile la JPasswordFiedl 'passwordField2'
                pIVAField.setEnabled(false);    //disabilita il JTextField 'pIVAField'
                oldPassLabel.setVisible(false); //rende invisibile la JLabel 'oldPassLabel'
                oldPassField.setVisible(false); //rende invisibile il JPasswordField 'passwordField'
                oldPassNoteLabel.setVisible(false); //rende invisibile la JLabel 'oldPassNoteLabel'

                newPassErrorLabel.setVisible(false);    //rende invisibile la JLabel 'newPassErrorLabel'
                partitaivaErrorLabel.setVisible(false); //rende invisibile la JLabel 'partitaivaErrorLabel'
                usernameErrorLabel.setVisible(false);   //rende invisibile la JLabel 'usernameErrorLabel'
                emailErrorLabel.setVisible(false);  //rende invisibile la JLabel 'emailErrorLabel'
                oldPassErrorLabel.setVisible(false);    //rende invisibile la JLabel 'oldPassErrorLabel'

                usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utente
                nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
                passwordField2.setText(""); //imposta il testo 'passwordField2' con la stringa vuota
                pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVA' con la partita IVA dell'utente
                oldPassField.setText("");   //imposta il testo 'oldPassField' con la stringa vuota
            }
        });

        salvaModificheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailU = emailField.getText();   //nuova email modificata dall'utente
                String nomeU = nameField.getText(); //nuovo nome modificato dall'utente
                String cognomeU = cognomeField.getText();   //nuovo cognome modificato dall'utente
                String usernameU = usernameField.getText(); //nuovo username modificatao dall'utente
                char[] password1U = passwordField1.getPassword();   //nuova password modificata dall'utente
                char[] password2U = passwordField2.getPassword();   //nuova password ripetuta dall'utente
                String pass1 = new String(password1U);
                String pass2 = new String(password2U);
                String partitaIVA = pIVAField.getText();    //nuova partita IVA modificata dall'utente
                char[] password3U = oldPassField.getPassword(); //vecchia password inserita dall'utente per meffetuare le modifiche
                String pass3 = new String(password3U);

                if(!controller.getPassword().equals(pass3)) oldPassErrorLabel.setVisible(true); //se la vecchia password è errata, allora rende visibile la JLabel 'oldPassErrorLabel'
                else {
                    oldPassErrorLabel.setVisible(false);    //rende invisibile JLabel 'oldPassErrorLabel'
                    int[] error = controller.validaModUtente(emailU, usernameU, partitaIVA);    //controlla se 'emailU', 'usernameU' e/o 'partitaIVA' sono già stati utilizzati da un altro utente

                    if (error[0] != 0) emailErrorLabel.setVisible(true);    //se 'emailU' è già stata utilizzata da un altro utente rende visibile la JLabel 'emailErrorLabel'
                    else emailErrorLabel.setVisible(false); //se 'emailU' non è già stata utilizzata da un altro utente rende invisibile la JLabel 'emailErrorLabel'

                    if (error[1] != 0) usernameErrorLabel.setVisible(true); //se 'usernameU' è gia stato utilizzato da un altro utente rende visibile la JLabel 'usernameErrorLabel'
                    else usernameErrorLabel.setVisible(false);  //se 'usernameU' non è gia stato utilizzato da un altro utente rende visibile la JLabel 'usernameErrorLabel'

                    if (error[2] != 0) partitaivaErrorLabel.setVisible(true);   //se 'partitaIVA' è già stata utilizzata da un altro utente rende visibile la JLabel 'partitaivaErrorLabel'
                    else partitaivaErrorLabel.setVisible(false);    //se 'partitaIVA' non è già stata utilizzata da un altro utente rende visibile la JLabel 'partitaivaErrorLabel'

                    //if (error[0] == 0) emailErrorLabel.setVisible(false);
                    //if (error[1] == 0) usernameErrorLabel.setVisible(false);
                    //if (error[2] == 0) partitaivaErrorLabel.setVisible(false);

                    if(!pass1.equals(pass2)){   //controlla se la nuova password è diversa da quella ripetuta
                        newPassErrorLabel.setVisible(true); //rende visibile la JLabel 'newPassErrorLabel'
                        error[3] = 1;   //mette a 1 'error[3]' che indica l'errore delle nuove password non coincidenti
                    }

                    if (error[0] == 0 && error[1] == 0 && error[2] == 0 && error[3] == 0){  //ccontrolla se non ci sono errori
                        modificaDatiUtenteButton.setEnabled(true);  //abilita il JButton 'modificaDatiUtenteButton'
                        annullaButton.setVisible(false);    //rende inivisibile il JButton 'annullaButton'
                        salvaModificheButton.setVisible(false); //rende inivisibile il JButton 'salvaModificheButton'
                        usernameField.setEnabled(false);    //disabilita il JTextField 'usernameField'
                        nameField.setEnabled(false);    //disabilita il JTextField 'nameField'
                        cognomeField.setEnabled(false); //disabilita il JTextField 'cognomeField'
                        emailField.setEnabled(false);   //disabilita il JTextField 'emailField'
                        passwordField1.setEnabled(false);   //disabilita il JPasswordField 'passwordField1'
                        ripPassLabel.setVisible(false); //rende invisibile la JLabel 'ripPassLabel'
                        passwordField2.setVisible(false);   //rende invisibile il JPasswordField 'passwordField2'
                        pIVAField.setEnabled(false);    //disabilita il JTextField 'pIVAField'
                        oldPassLabel.setVisible(false); //rende invisibile la JLabel 'oldPassLabel'
                        oldPassField.setVisible(false); //rende invisibile il JPasswordField 'oldPassField'
                        oldPassNoteLabel.setVisible(false); //rende invisibile la JLabel 'oldPassNoteLabel'

                        if(pass1.isBlank()) controller.modUtente(emailU, nomeU, cognomeU, usernameU, controller.getPassword(), partitaIVA); //se la password non è stata modificata, allora modifica i dati del utente usando la password attuale dell'utente
                        else controller.modUtente(emailU, nomeU, cognomeU, usernameU, pass1, partitaIVA);   //se la password non è stata modificata, allora modifica i dati del utente usando la nuova password

                        usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utete
                        nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                        cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                        emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                        passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
                        pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVAField' con la partita IVA dell'utente

                        passwordField2.setText(""); //imposta il testo di 'passwordField2' con la stringa vuota
                        oldPassField.setText("");   //imposta il testo di 'oldPassField' con la stringa vuota

                        newPassErrorLabel.setVisible(false);    //rende invisibile la JLabel 'newPassErrorLabel'
                        partitaivaErrorLabel.setVisible(false); //rende invisibile la JLabel 'partitaivaErrorLabel'
                        usernameErrorLabel.setVisible(false);   //rende invisibile la JLabel 'usernameErrorLabel'
                        emailErrorLabel.setVisible(false);  //rende invisibile la JLabel 'emailErrorLabel'
                    }
                }
            }
        });
    }
}