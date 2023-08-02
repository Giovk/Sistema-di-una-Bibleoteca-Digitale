package GUI;

import Controller.Controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class NewLoginForm extends JDialog {
    public static JFrame frame;
    private JPanel contentPane;
    private JLabel imageLabel;
    private JLabel closeBT;
    private JPanel dxPanel;
    private JButton accediButton;
    private JTextField usernameTF;
    private JTextField nomeTF;
    private JPasswordField passwordF1;
    private JTextField passwordF2;
    private JButton regButton;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JTextField emailTF;
    private JTextField cognomeTF;
    private JLabel passwordLabel1;
    private JLabel ripetiPasswordLabel;
    private JPasswordField passwordF3;
    private JLabel dataDiNascitaLabel;
    private JLabel partitaIvaLabel;
    private JTextField partitaIVATF;
    private JLabel showPass;
    private JLabel hidePass;
    private JPanel regPanel3;
    private JPanel regPanel2;
    private JButton registratiButton;
    private JPanel sxPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPanel1;
    private JPanel registerPanel;
    private JLabel indicazioniLabel;
    private JLabel passwordLabel2;
    private JScrollPane scrollPanel2;
    private JPanel accediPanel;
    private JPanel regPanel;
    private JPanel accPanel;
    private JTextField usernameTF2;
    private JPasswordField passwordTF4;
    private JLabel usernameLabel2;
    private JLabel passwordLabel4;
    private JLabel passwordLabel5;
    private JTextField passwordTF5;
    private JLabel showPass2;
    private JLabel hidePass2;
    private JButton loginButton;
    private JTextField dataNascitaTF;
    private JLabel calendarIMG;
    private ImageIcon imagine;
    private ImageIcon closeImg;
    public int menuAcc;

    private DatePicker datePicker;
    private Boolean debug = false;

    public NewLoginForm(int joinD, JFrame frameC, Controller controller) {
        datePicker = new DatePicker(calendarIMG);

        frame = new JFrame("NewLoginForm");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);
        DebugMode(frameC, controller, debug);

        if(joinD == 0){
            accPanel.setVisible(true);
            regPanel.setVisible(false);
            accediButton.setBackground(Color.decode("#cf9e29"));
            registratiButton.setBackground(Color.decode("#FFD369"));
            menuAcc = 1;
        }else{
            accPanel.setVisible(false);
            regPanel.setVisible(true);
            registratiButton.setBackground(Color.decode("#cf9e29"));
            accediButton.setBackground(Color.decode("#FFD369"));
            menuAcc = 0;
        }

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frameC.toFront();
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

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                frameC.toFront();
            }
        });


        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                regButton.setIcon(regBTico);
                regButton.setForeground(Color.decode("#D6D4D4"));
            }
        });
        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                regButton.setIcon(regBTico);
                regButton.setForeground(Color.decode("#EEEEEE"));
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                loginButton.setIcon(regBTico);
                loginButton.setForeground(Color.decode("#D6D4D4"));
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                loginButton.setIcon(regBTico);
                loginButton.setForeground(Color.decode("#EEEEEE"));
            }
        });


        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF4.setVisible(false);
                showPass2.setVisible(false);
                passwordLabel4.setVisible(false);
                passwordLabel5.setVisible(true);
                passwordTF5.setVisible(true);
                hidePass2.setVisible(true);
                passwordTF5.setText(new String(passwordTF4.getPassword()));
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF5.setVisible(false);
                hidePass2.setVisible(false);
                passwordTF4.setVisible(true);
                showPass2.setVisible(true);
                passwordTF4.setText(passwordTF5.getText());
                passwordLabel4.setVisible(true);
                passwordLabel5.setVisible(false);
            }
        });


        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF1.setVisible(false);
                showPass.setVisible(false);
                passwordLabel1.setVisible(false);
                passwordLabel2.setVisible(true);
                passwordF2.setVisible(true);
                hidePass.setVisible(true);
                passwordF2.setText(new String(passwordF1.getPassword()));
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF2.setVisible(false);
                hidePass.setVisible(false);
                passwordF1.setVisible(true);
                showPass.setVisible(true);
                passwordF1.setText(passwordF2.getText());
                passwordLabel1.setVisible(true);
                passwordLabel2.setVisible(false);

            }
        });

        calendarIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                datePicker.d.setVisible(true);
                dataNascitaTF.setText(datePicker.setPickedDate());
            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 1;
                regPanel.setVisible(false);
                accPanel.setVisible(true);
                accediButton.setBackground(Color.decode("#cf9e29"));
                registratiButton.setBackground(Color.decode("#FFD369"));
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 0;
                accPanel.setVisible(false);
                regPanel.setVisible(true);
                accediButton.setBackground(Color.decode("#FFD369"));
                registratiButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                accediButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (menuAcc == 0) accediButton.setBackground(Color.decode("#FFD369"));
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registratiButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (menuAcc == 1) registratiButton.setBackground(Color.decode("#FFD369"));
            }
        });

        // --------------------------------------------------------------------------//

        passwordTF4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login(frameC, controller);
                }
                e.consume();
            }
        });

        passwordTF5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login(frameC, controller);
                }
            }
        });

        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login(frameC, controller);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(frameC, controller);
            }
        });

        passwordF3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    register(frameC, controller);
                }
            }
        });

        partitaIVATF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    register(frameC, controller);
                }
            }
        });

        dataNascitaTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    register(frameC, controller);
                }
            }
        });

        regButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    register(frameC, controller);
                }
            }
        });

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register(frameC, controller);
            }
        });
    }


    private void login(JFrame frameC, Controller controller){
        String userEmail = usernameTF2.getText(); //username o email inserito dall'utente per effettuare l'accesso
        //password inserita dall'utente per effettuare l'accesso
        String password = new String();
        if(showPass2.isVisible() == true){
            char[] pass = passwordTF4.getPassword();
            password = new String(pass);
        } else if (hidePass2.isVisible() == true) {
            password = passwordTF5.getText();
        }

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

    private void DebugMode(JFrame frameC, Controller controller, Boolean debug){
        if(debug == true){
            frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            controller.setUtente("Admin1", "admin"); //salva in memoria l'utente che ha accesso usando 'userEmail' e 'Password'
            HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
            frameC.setVisible(false);   //rende invisibile il frame chiamante 'frameC'
            hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
            frame.dispose();
            frameC.toFront();
        }
    }

    private void register(JFrame frameC, Controller controller){
        String emailU = emailTF.getText();    //email inserita dall'utente per effettuare la registrazione
        String nomeU = nomeTF.getText();  //nome inserito dall'utente per effettuare la registrazione
        String cognomeU = cognomeTF.getText(); //cognome inserito dall'utente per effettuare la registrazione
        String usernameU = usernameTF.getText();  //username inserito dall'utente per effettuare la registrazione
        String password1 = new String();
        if(showPass.isVisible() == true){
            char[] pass1 = passwordF1.getPassword();
            password1 = new String(pass1);
        } else if (hidePass.isVisible() == true) {
            password1 = passwordF2.getText();
        }

        char[] pass2 = passwordF3.getPassword();    //password ripetuta dall'utente per effettuare la registrazione
        String password2 = new String(pass2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //calendario per l'input della data di nascita dell utente
        String dt;  //data di nascita dell'utente

        String partitaIVA = partitaIVATF.getText(); //partita IVA inserita dall'utente per effettuare la registrazione

        if (nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank() || password1.isBlank() || password2.isBlank()) { //controlla se qualche campo obbligatorio è stato lasciato vuoto
            //fieldError.setText("Compilare tutti i campi obbligatori");  //imposta il testo di un messaggio di errore nella JLabel 'fieldError'
            //fieldError.setVisible(true);    //rende visibile la JLabel 'fieldError' contenente un messaggio di errore
            //frame.setResizable(true);   //permette all'utente di modificare le dimensioni del frame
            JOptionPane.showMessageDialog(frame, "Compilare tutti i campi obbligatori.");
        } else {
            if (password1.equals(password2) == false) {//controlla se la password scelta dall'utente 'pass1' è diversa da quella ripetuta
                //regError.setText("Le password non coincidono"); //imposta il testo di un messaggio di errore nella JLabel 'fieldError'
                // regError.setVisible(true);  //rende visibile la JLabel 'fieldError' contenente un messaggio di errore
                //frame.setResizable(true);   //permette all'utente di modificare le dimensioni del frame
                JOptionPane.showMessageDialog(frame, "Le password non coincidono.");
            } else {
                //regError.setVisible(false); //rende invisibile la JLabel 'regError'
                JOptionPane.showMessageDialog(frame, "Registrazione Completata.");

                dt = dataNascitaTF.getText();
                if (dt.isBlank()) dt = null;


                controller.aggiungiUtente(emailU, nomeU, cognomeU, usernameU, password1, dt, partitaIVA); //registra un nuovo utente con i dati che ha inserito
                frameC.setEnabled(true); //rende visibile il frame chiamante 'frameC'
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                frameC.setVisible(false); //rende invisibile il frame chiamante 'frameC'
                hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
                frame.dispose();
                frameC.toFront();
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imagine = new ImageIcon(this.getClass().getResource("/b.png"));
        Image imagine2 = imagine.getImage().getScaledInstance(340, 94, Image.SCALE_SMOOTH);
        imagine = new ImageIcon(imagine2);
        imageLabel = new JLabel(imagine);

        ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
        Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
        regBTico = new ImageIcon(regBTimg);
        regButton = new JButton(regBTico);
        loginButton = new JButton(regBTico);

        ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
        Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        showPico = new ImageIcon(showPimg);
        showPass = new JLabel(showPico);
        showPass2 = new JLabel(showPico);

        ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
        Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        hidePico = new ImageIcon(hidePimg);
        hidePass = new JLabel(hidePico);
        hidePass2 = new JLabel(hidePico);


        emailTF = new JTextField();
        emailTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        nomeTF = new JTextField();
        nomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        cognomeTF = new JTextField();
        cognomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        usernameTF = new JTextField();
        usernameTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF1 = new JPasswordField();
        passwordF1.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF2 = new JTextField();
        passwordF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF3 = new JPasswordField();
        passwordF3.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        partitaIVATF = new JTextField();
        partitaIVATF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        dataNascitaTF = new JTextField();
        dataNascitaTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));


        usernameTF2 = new JTextField();
        usernameTF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordTF4 = new JPasswordField();
        passwordTF4.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordTF5 = new JTextField();
        passwordTF5.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));

        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);

        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar.png"));
        Image calendarIm = calendarIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        calendarIco = new ImageIcon(calendarIm);
        calendarIMG = new JLabel(calendarIco);
    }
}