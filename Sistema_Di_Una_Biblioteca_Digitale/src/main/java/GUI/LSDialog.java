package GUI;

import Model.Utente;
import com.toedter.calendar.JDateChooser;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class LSDialog extends JDialog {
    private JFrame frame;
    private JPanel contentPane;
    private JTabbedPane LSTabbedPane;
    private JPanel registerJPanel;
    private JPanel loginJPanel;
    private JTextField regEmailField;
    private JTextField regNameField;
    private JTextField regSurnameField;
    private JPasswordField regPasswordField;
    private JPasswordField regPassword2Field;
    private JTextField regUsernameField;
    private JTextField regPartitaIVAField;
    private JButton btRegistrati;
    private JPasswordField logPasswordField;
    private JButton btAccedi;
    private JTextField logUserEmailField;
    private JPanel datePicker;
    private JLabel regError;
    private JPanel lsdialogPanel;
    private JLabel fieldError;
    JDateChooser jdc = new JDateChooser();

    public LSDialog(int n, JFrame frameC, Controller controller) {
        jdc.setDateFormatString("yyyy-MM-dd");  //imposta il formato della data di nascita
        datePicker.add(jdc);
        regError.setVisible(false); //rende invisibile la JLabel 'regError'
        frame = new JFrame("Login/register");
        frame.setUndecorated(false); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPane);
        LSTabbedPane.setSelectedIndex(n); //seleziona il JPanel con indice 'n' (n=0 => JPanel 'loginJPanel', n=1 => JPanel 'registerJPanel')
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        ImageIcon ico1 = new ImageIcon(this.getClass().getResource("/b.png"));
        Image img1 = ico1.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ico1 = new ImageIcon(img1);
        LSTabbedPane.setIconAt(0, ico1);
        frame.setLocationRelativeTo(null);//posiziona il frame al centro dello schermo
        frame.setResizable(false);//evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        btAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = logUserEmailField.getText(); //username o email inserito dall'utente per effettuare l'accesso
                char[] pass = logPasswordField.getPassword();   //password inserita dall'utente per effettuare l'accesso
                String password = new String(pass);

                if (controller.validaUtente(userEmail, password) < 1) { //controlla se non esiste un utente registrato che ha come username o email 'userMail' e come password 'password'
                    JOptionPane.showMessageDialog(frame, "L'Account non esiste.");
                } else {
                    frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                    controller.setUtente(userEmail, password); //salva in memoria l'utente che ha accesso usando 'userEmail' e 'Password'
                    HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                    frameC.setVisible(false);   //rende invisibile il frame chiamante 'frameC'
                    hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
                    frame.dispose();
                }
            }
        });

        btRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailU = regEmailField.getText();    //email inserita dall'utente per effettuare la registrazione
                String nomeU = regNameField.getText();  //nome inserito dall'utente per effettuare la registrazione
                String cognomeU = regSurnameField.getText(); //cognome inserito dall'utente per effettuare la registrazione
                String usernameU = regUsernameField.getText();  //username inserito dall'utente per effettuare la registrazione
                char[] password1U = regPasswordField.getPassword(); //password scelta dall'utente per effettuare la registrazione
                char[] password2U = regPassword2Field.getPassword();    //password ripetuta dall'utente per effettuare la registrazione
                String pass1 = new String(password1U);
                String pass2 = new String(password2U);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //calendario per l'input della data di nascita dell utente
                String dt;  //data di nascita dell'utente

                String partitaIVA = regPartitaIVAField.getText(); //partita IVA inserita dall'utente per effettuare la registrazione

                if (nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank() || pass1.isBlank() || pass2.isBlank()) { //controlla se qualche campo obbligatorio è stato lasciato vuoto
                    fieldError.setText("Compilare tutti i campi obbligatori");  //imposta il testo di un messaggio di errore nella JLabel 'fieldError'
                    fieldError.setVisible(true);    //rende visibile la JLabel 'fieldError' contenente un messaggio di errore
                    frame.setResizable(true);   //permette all'utente di modificare le dimensioni del frame
                } else {
                    if (pass1.equals(pass2) == false) {//controlla se la password scelta dall'utente 'pass1' è diversa da quella ripetuta
                        regError.setText("Le password non coincidono"); //imposta il testo di un messaggio di errore nella JLabel 'fieldError'
                        regError.setVisible(true);  //rende visibile la JLabel 'fieldError' contenente un messaggio di errore
                        frame.setResizable(true);   //permette all'utente di modificare le dimensioni del frame
                    } else {
                        regError.setVisible(false); //rende invisibile la JLabel 'regError'
                        JOptionPane.showMessageDialog(frame, "Registrazione Completata.");

                        try{
                          dt = sdf.format(jdc.getDate());   //inserisce in 'dt' la data di nascita inserita dall utente
                        } catch (NullPointerException e1){  //l'eccezione 'e1' si verifica se l'utente non inserisce una data di nascita
                            dt = null;
                        }

                        controller.aggiungiUtente(emailU, nomeU, cognomeU, usernameU, pass1, dt, partitaIVA); //registra un nuovo utente con i dati che ha inserito
                        frameC.setEnabled(true); //rende visibile il frame chiamante 'frameC'
                        HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                        frameC.setVisible(false); //rende visibile il frame chiamante 'frameC'
                        hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
                        frame.dispose();
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        /*LSTabbedPane = new JTabbedPane();
        ImageIcon ico1 = new ImageIcon(this.getClass().getResource("/b.png"));
        Image img1 = ico1.getImage().getScaledInstance(300,169, Image.SCALE_SMOOTH);
        ico1 = new ImageIcon(img1);
        LSTabbedPane.setIconAt(0, ico1);*/
        //LSTabbedPane.setIconAt(0, ico1);
    }
}

