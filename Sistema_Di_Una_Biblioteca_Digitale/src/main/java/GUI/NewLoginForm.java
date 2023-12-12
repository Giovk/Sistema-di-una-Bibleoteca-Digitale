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
        accediButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'accediButton'
        registratiButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'registratiButton'
        regButton.setFont(controller.impactFontSize);   //imposta il font del JButton 'regButton'
        loginButton.setFont(controller.impactFontSize); //imposta il font del JButton 'loginButton'

        emailLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'emailLabel'
        nomeLabel.setFont(controller.impactFontSize);   //imposta il font della JLabel 'nomeLabel'
        cognomeLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'cognomeLabel'
        usernameLabel.setFont(controller.impactFontSize);   //imposta il font della JLabel 'usernameLabel'
        passwordLabel1.setFont(controller.impactFontSize);  //imposta il font della JLabel 'passwordLabel1'
        passwordLabel2.setFont(controller.impactFontSize);  //imposta il font della JLabel 'passwordLabel2'
        ripetiPasswordLabel.setFont(controller.impactFontSize); //imposta il font della JLabel 'ripetiPasswordLabel'
        dataDiNascitaLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'dataDiNascitaLabel'
        partitaIvaLabel.setFont(controller.impactFontSize); //imposta il font della JLabel 'partitaIvaLabel'
        indicazioniLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'indicazioniLabel'
        emailTF.setFont(controller.textFieldFont);  //imposta il font del JTextField 'emailTF'
        nomeTF.setFont(controller.textFieldFont);   //imposta il font del JTextField 'nomeTF'
        cognomeTF.setFont(controller.textFieldFont);    //imposta il font del JTextField 'cognomeTF'
        usernameTF.setFont(controller.textFieldFont);   //imposta il font del JTextField 'usernameTF'
        passwordF1.setFont(controller.textFieldFont);   //imposta il font del JPasswordField 'passwordF1'
        passwordF2.setFont(controller.textFieldFont);   //imposta il font del JTextField 'passwordF2'
        passwordF3.setFont(controller.textFieldFont);   //imposta il font del JPasswordField 'passwordF3'
        dataNascitaTF.setFont(controller.textFieldFont);    //imposta il font del JTextField 'dataNascitaTF'
        partitaIVATF.setFont(controller.textFieldFont); //imposta il font del JTextField 'partitaIVATF'

        usernameLabel2.setFont(controller.impactFontSize);  //imposta il font della JLabel 'usernameLabel2'
        passwordLabel5.setFont(controller.impactFontSize);  //imposta il font della JLabel 'passwordLabel5'
        passwordLabel4.setFont(controller.impactFontSize);  //imposta il font della JLabel 'passwordLabel4'
        usernameTF2.setFont(controller.textFieldFont);  //imposta il font del JTextField 'usernameTF2'
        passwordTF4.setFont(controller.textFieldFont);  //imposta il font del JTextField 'passwordTF4'
        passwordTF5.setFont(controller.textFieldFont);  //imposta il font del JTextField 'passwordTF5'

        datePicker = new DatePicker(calendarIMG);

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //rimuove la decorazione del frame
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();
        frame.setSize((int) (controller.screenWidth/1.7777), (int) (controller.screenHeight/1.5));    //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame
        DebugMode(frameC, controller, debug);   //effettua l'accesso

        if(joinD == 0){ //controlla se l'utetnte sta effettuando l'accesso (o la registrazione)
            accPanel.setVisible(true);  //rende visibile il JPanel 'accPanel'
            regPanel.setVisible(false); //rende invisibile il JPanel 'regPanel'
            accediButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'accediButton'
            registratiButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'registratiButton'
            menuAcc = 1;    //aggiorna il flag che indica il JPanel aperto dall'utente
        }else{
            accPanel.setVisible(false); //rende invisibile il JPanel 'accPanel'
            regPanel.setVisible(true);  //rende visibile il JPanel 'regPanel'
            registratiButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'registratiButton'
            accediButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'accediButton'
            menuAcc = 0;    //aggiorna il flag che indica il JPanel aperto dall'utente
        }

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));   //carica l'immagine nel percorso /button-type4.png
                Image regBTimg = regBTico.getImage().getScaledInstance((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/36), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

                regBTico = new ImageIcon(regBTimg); //reinizializza l'ImageIcon 'regBTico' con l'Image 'regBTimg'
                regButton.setIcon(regBTico);    //imposta l'icona del JButton 'regButton' con l'immagine
                regButton.setForeground(Color.decode("#D6D4D4"));   //imposta il colore dello sfondo del JButton 'regButton'
            }
        });

        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));   //carica l'immagine nel percorso /button-type3.png
                Image regBTimg = regBTico.getImage().getScaledInstance((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/36), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

                regBTico = new ImageIcon(regBTimg); //reinizializza l'ImageIcon 'regBTico' con l'Image 'regBTimg'
                regButton.setIcon(regBTico);    //imposta l'icona del JButton 'regButton' con l'immagine
                regButton.setForeground(Color.decode("#EEEEEE"));   //imposta il colore dello sfondo del JButton 'regButton'
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));   //carica l'immagine nel percorso /button-type4.png
                Image regBTimg = regBTico.getImage().getScaledInstance((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/36), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

                regBTico = new ImageIcon(regBTimg); //reinizializza l'ImageIcon 'regBTico' con l'Image 'regBTimg'
                loginButton.setIcon(regBTico);  //imposta l'icona del JButton 'loginButton' con l'immagine
                loginButton.setForeground(Color.decode("#D6D4D4")); //imposta il colore dello sfondo del JButton 'loginButton'
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));   //carica l'immagine nel percorso /button-type3.png
                Image regBTimg = regBTico.getImage().getScaledInstance((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/36), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

                regBTico = new ImageIcon(regBTimg); //reinizializza l'ImageIcon 'regBTico' con l'Image 'regBTimg'
                loginButton.setIcon(regBTico);  //imposta l'icona del JButton 'loginButton' con l'immagine
                loginButton.setForeground(Color.decode("#EEEEEE")); //imposta il colore dello sfondo del JButton 'loginButton'
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png")); //carica l'immagine nel percorso /e2.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass2.setIcon(showPico);    //imposta l'icona della JLabel 'showPass2' con l'immagine
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png")); //carica l'immagine nel percorso /e1.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass2.setIcon(showPico);    //imposta l'icona della JLabel 'showPass2' con l'immagine
            }
        });

        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png")); //carica l'immagine nel percorso /e1.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass2.setIcon(hidePico);    //imposta l'icona della JLabel 'hidePass2' con l'immagine
            }
        });

        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png")); //carica l'immagine nel percorso /e2.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass2.setIcon(hidePico);    //imposta l'icona della JLabel 'hidePass2' con l'immagine
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF4.setVisible(false);  //rende invisibile il JPasswordField 'passwordTF4'
                showPass2.setVisible(false);    //rende invisibile la JLabel 'showPass2'
                passwordLabel4.setVisible(false);   //rende invisibile la JLabel 'passwordLabel4'
                passwordLabel5.setVisible(true);    //rende visibile la JLabel 'passwordLabel5'
                passwordTF5.setVisible(true);   //rende visibile il JTextField 'passwordTF5'
                hidePass2.setVisible(true); //rende visibile la JLabel 'hidePass2'
                passwordTF5.setText(new String(passwordTF4.getPassword())); //imposta il testo del JTextField 'passwordTF5' con quello dell JPasswordField 'passwordTF4'
            }
        });

        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF5.setVisible(false);  //rende invisibile il JPasswordField 'passwordTF5'
                hidePass2.setVisible(false);    //rende invisibile la JLabel 'hidePass2'
                passwordTF4.setVisible(true);   //rende visibile la JPasswordField 'passwordLabel4'
                showPass2.setVisible(true); //rende visibile la JLabel 'showPass2'
                passwordTF4.setText(passwordTF5.getText()); //imposta il testo del JPasswordField 'passwordTF4' con quello dell JPasswordField 'passwordTF5'
                passwordLabel4.setVisible(true);    //rende visibile la JLabel 'passwordLabel4'
                passwordLabel5.setVisible(false);   //rende invisibile la JLabel 'passwordLabel5'
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png")); //carica l'immagine nel percorso /e2.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                showPass.setIcon(showPico); //imposta l'icona della JLabel 'showPass' con l'immagine
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png")); //carica l'immagine nel percorso /e1.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                showPass.setIcon(showPico); //imposta l'icona della JLabel 'showPass' con l'immagine
            }
        });

        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png")); //carica l'immagine nel percorso /e1.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass.setIcon(hidePico); //imposta l'icona della JLabel 'hidePass' con l'immagine
            }
        });

        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png")); //carica l'immagine nel percorso /e2.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass.setIcon(hidePico); //imposta l'icona della JLabel 'hidePass' con l'immagine
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF1.setVisible(false);   //rende invisibile il JPasswordField 'passwordF1'
                showPass.setVisible(false); //rende invisibile la JLabel 'showPass'
                passwordLabel1.setVisible(false);   //rende invisibile la JLabel 'passwordLabel1'
                passwordLabel2.setVisible(true);    //rende visibile la JLabel 'passwordLabel2'
                passwordF2.setVisible(true);    //rende visibile il JTextField 'passwordF2'
                hidePass.setVisible(true);  //rende visibile la JLabel 'hidePass'
                passwordF2.setText(new String(passwordF1.getPassword())); //imposta il testo del JTextField 'passwordF2' con quello del JPasswordField 'passwordF1'
            }
        });

        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF2.setVisible(false);   //rende invisibile il JTextField 'passwordF2'
                hidePass.setVisible(false); //rende invisibile la JLabel 'hidePass'
                passwordF1.setVisible(true);    //rende visibile il JPasswordField 'passwordF1'
                showPass.setVisible(true);  //rende visibile la JLabel 'showPass'
                passwordF1.setText(passwordF2.getText());   //imposta il testo del JPasswordField 'passwordF1' con quello del JTextField 'passwordF2'
                passwordLabel1.setVisible(true);    //rende visibile la JLabel 'passwordLabel1'
                passwordLabel2.setVisible(false);   //rende invisibile la JLabel 'passwordLabel2'
            }
        });

        calendarIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                datePicker.d.setVisible(true);  //rende visibile il calendario
                dataNascitaTF.setText(datePicker.setPickedDate());  //imposta il testo del JTextField 'dataNascitaTF' con la data scelta dall'utente
            }
        });

        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 1;    //indica che l'utente sta effettuando l'accesso
                regPanel.setVisible(false); //rende invisibile la JLabel 'regPanel'
                accPanel.setVisible(true);  //rende visibile la JLabel 'accPanel'
                accediButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'accediButton'
                registratiButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'registratiButton'
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 0;    //indica che l'utente sta effettuando l'accesso
                accPanel.setVisible(false); //rende invisibile la JLabel 'accPanel'
                regPanel.setVisible(true);  //rende visibile la JLabel 'regPanel'
                accediButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'accediButton'
                registratiButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'registratiButton'
            }
        });

        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                accediButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'accediButton'
            }
        });

        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (menuAcc == 0) { //controlla se l'utente sta effettuando l'accesso
                    accediButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'accediButton'
                }
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registratiButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'registratiButton'
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (menuAcc == 1) { //controlla se l'utente sta effettuando l'accesso
                    registratiButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'registratiButton'
                }
            }
        });


        passwordTF4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    login(frameC, controller);  //effettua l'accesso
                    e.consume();    //evita che il KeyEvent 'e' venga ulteriormente gestito
                }
            }
        });

        passwordTF5.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    login(frameC, controller);  //effettua l'accesso
                }
            }
        });

        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    login(frameC, controller);  //effettua l'accesso
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(frameC, controller);  //effettua l'accesso
            }
        });

        passwordF3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    register(frameC, controller);   //effettua la registrazione
                }
            }
        });

        partitaIVATF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    register(frameC, controller);   //effettua la registrazione
                }
            }
        });

        dataNascitaTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    register(frameC, controller);   //effettua la registrazione
                }
            }
        });

        regButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "Enter"
                    register(frameC, controller);   //effettua la registrazione
                }
            }
        });

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register(frameC, controller);   //effettua la registrazione
            }
        });
    }

    private void login(JFrame frameC, Controller controller){   //effettua l'accesso dell'utente
        String userEmail = usernameTF2.getText(); //username o email inserito dall'utente per effettuare l'accesso
        String password = new String(); //password inserita dall'utente per effettuare l'accesso

        if(hidePass2.isVisible() == true){  //controlla se la password è visibile
            password = passwordTF5.getText();   //inserisce il testo del JTextField 'passwordTF5' in 'password'
        }else if(showPass2.isVisible() == true) {   //controlla se la password non è visibile
            char[] pass = passwordTF4.getPassword();    //inserisce il testo del JPasswordField 'passwordT4' in 'pass'
            password = new String(pass);    //inserisce il contenuto di 'pass' in 'password'
        }

        if (controller.validaUtente(userEmail, password) < 1) { //controlla se non esiste un utente registrato che ha come username o email 'userMail' e come password 'password'
            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo utente non esiste");  //mostra un messaggio di errore
        } else {
            frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            controller.setUtente(userEmail, password); //salva in memoria l'utente che ha accesso usando 'userEmail' e 'Password'
            HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
            frameC.setVisible(false);   //rende invisibile il frame chiamante 'frameC'
            hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
            frame.dispose();
        }
    }//fine login

    private void DebugMode(JFrame frameC, Controller controller, Boolean debug){
        if(debug == true){
            frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            controller.setUtente("Admin1", "admin"); //salva in memoria l'utente che ha accesso usando 'userEmail' e 'Password'
            HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
            frameC.setVisible(false);   //rende invisibile il frame chiamante 'frameC'
            hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
            frame.dispose();
            frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
        }
    }//fine DebugMode

    private void register(JFrame frameC, Controller controller){    //effettua la registrazione dell'utente
        String emailU = emailTF.getText();    //email inserita dall'utente per effettuare la registrazione
        String nomeU = nomeTF.getText();  //nome inserito dall'utente per effettuare la registrazione
        String cognomeU = cognomeTF.getText(); //cognome inserito dall'utente per effettuare la registrazione
        String usernameU = usernameTF.getText();  //username inserito dall'utente per effettuare la registrazione
        String password1 = new String();    //password inserita dall'utente per effettuare la registrazione

        if(hidePass.isVisible() == true){   //controlla se la password è visibile
            password1 = passwordF2.getText();   //inserisce il testo del JTextField 'passwordF2' in 'password1'
        }else if(showPass.isVisible() == true){ //controlla se la password non è visibile
            char[] pass1 = passwordF1.getPassword();        //inserisce il testo del JPasswordField 'passwordT4' in 'pass1'
            password1 = new String(pass1);  //inserisce il contenuto di 'pass1' in 'password1'
        }

        char[] pass2 = passwordF3.getPassword();    //password ripetuta dall'utente per effettuare la registrazione
        String password2 = new String(pass2);   //inserisce il contenuto di 'pass2' in 'password2'
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //calendario per l'input della data di nascita dell utente
        String dt;  //data di nascita dell'utente

        String partitaIVA = partitaIVATF.getText(); //partita IVA inserita dall'utente per effettuare la registrazione

        if (emailU.isBlank() || nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank() || password1.isBlank() || password2.isBlank()) { //controlla se qualche campo obbligatorio è stato lasciato vuoto
            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");
        } else {
            if (controller.verificaEmail(emailU) == false){ //controlla se è stata inserita una email non valida
                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "email non valida!");
            } else {
                emailU = controller.changeEmail(emailU);    //rende minuscole tutte le lettere maiuscole di 'emailU'
                if (usernameU.contains("'") || password1.contains("'") || password2.contains("'")) { //controlla se l'username o na delle assword contengono il carattere '''
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "il carattere ' non è ammesso");
                } else {
                    if (controller.verificaNomeCognome(nomeU) == false || controller.verificaNomeCognome(cognomeU) == false) {  //controlla se è stato inserito un nome o cognome non valido
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il nome o il cognome non sono validi!");
                    } else {
                        if(controller.verificaPassword(password1) == false){ //controlla se è stata inserita una password non valida
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La password deve contenere 8 caratteri di cui: una maiuscola, un numero e un carattare speciale");
                        } else {
                            if (password1.equals(password2) == false) {//controlla se la password scelta dall'utente 'pass1' è diversa da quella ripetuta
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Le password non coincidono");
                            } else {
                                if(controller.verificaPartitaIVA(partitaIVA) == false){ //controlla se è stata inserita una partita IVA non valida
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La partita IVA non è corretta");
                                } else {
                                    int[] error = controller.validaInsUtente(emailU, usernameU, partitaIVA);    //controlla se l'email, l'usrname e/o la partita IVA sono già stati utilizzati
                                    String errorString = "";

                                    if(error[0] != 0){  //controlla se l'email è stata gia utilizzata
                                        errorString = "L'email";    //inizializza 'erroreString'
                                    }

                                    if (error[1] != 0){ //controlla se l'username è già stato utilizzato
                                        if (error[0] != 0 && error[2] != 0){    //controlla se l'email e la partita IVA sono gia stati utilizzati
                                            errorString = errorString + ", l'username"; //modifica 'errorString'
                                        } else if (error[0] != 0){  //controlla se l'email è gia stata utilizzata
                                            errorString = errorString + " e l'username";    //modifica 'erroreString'
                                        } else {
                                            errorString = "L'username"; //inizializza 'errorString'
                                        }
                                    }

                                    if (error[2] != 0){ //controlla se la partita IVA è già stata utilizzata
                                        if (error[1] != 0 || error[0] != 0){    //controlla se l'username o l'email sono gia stati utilizzati
                                            errorString = errorString + " e la partita iva";    //modifica 'errorString'
                                        } else {
                                            errorString = "La partita iva"; //inizializza 'errorString'
                                        }
                                    }

                                    if (error[0] != 0 || error[1] != 0 || error[2] != 0){
                                        errorString = errorString + " sono/è già stati/o utilizzati/o"; //termina 'errorString'

                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, errorString);
                                    } else {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(1, "Registrazione Completata");

                                        dt = dataNascitaTF.getText();   //inserisce il testo del JTextField in 'dt'

                                        if (dt.isBlank()) { //controlla se non è stata inserita nessuna data di nascita
                                            dt = null;  //pone 'dt' a null
                                        }

                                        nomeU = nomeU.replace("'", "’");    //sostituisce eventuali ''' nel nome con '’'
                                        cognomeU = cognomeU.replace("'", "’");  //sostituisce eventuali ''' nel nome con '’'
                                        controller.aggiungiUtente(emailU, nomeU, cognomeU, usernameU, password1, dt, partitaIVA); //registra un nuovo utente con i dati che ha inserito
                                        frameC.setEnabled(true); //abilita il frame chiamante 'frameC'
                                        HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                                        frameC.setVisible(false); //rende invisibile il frame chiamante 'frameC'
                                        hp.frame.setVisible(true);  //rende visibile il frame chiamato 'hp'
                                        frame.dispose();
                                        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }//fine register


    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        imagine = new ImageIcon(this.getClass().getResource("/b.png")); //carica l'immagine nel percorso /b.png
        Image imagine2 = imagine.getImage().getScaledInstance((int) (screenWidth/5.12), (int) (screenHeight/5.1428), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
        imagine = new ImageIcon(imagine2);  //reinizializza l'ImageIcon 'imagine' con l'Image 'imagine2'
        imageLabel = new JLabel(imagine);   //inizializza la JLabel 'imageLabel' con 'imagine'

        ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));   //carica l'immagine nel percorso /button-type3.png
        Image regBTimg = regBTico.getImage().getScaledInstance((int) (screenWidth/12.8), (int) (screenHeight/36), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

        regBTico = new ImageIcon(regBTimg); //reinizializza l'ImageIcon 'regBTico' con l'Image 'regBTimg'
        regButton = new JButton(regBTico);  //inizializza il JButton 'regButton' con 'regBTico'
        loginButton = new JButton(regBTico);    //inizializza il JButton 'loginButton' con 'regBTico'

        ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png")); //carica l'immagine nel percorso /e1.png
        Image showPimg = showPico.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
        showPass = new JLabel(showPico);    //inizializza la JLabel 'showPass' con 'showPico'
        showPass2 = new JLabel(showPico);   //inizializza la JLabel 'showPass2' con 'showPico'

        ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png")); //carica l'immagine nel percorso /e1.png
        Image hidePimg = hidePico.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
        hidePass = new JLabel(hidePico);    //inizializza la JLabel 'hidePass' con 'hidePico'
        hidePass2 = new JLabel(hidePico);   //inizializza la JLabel 'hidePass2' con 'hidePico'

        emailTF = new JTextField(); //inizializza il JTextField 'emailTF'
        emailTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831"))); //imposta il colore del bordo del JTextField
        nomeTF = new JTextField();  //inizializza il JTextField 'nomeTF'
        nomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));  //imposta il colore del bordo del JTextField 'nomeTF'
        cognomeTF = new JTextField();   //inizializza il JTextField 'cognomeTF'
        cognomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));   //imposta il colore del bordo del JTextField 'cognomeTF'
        usernameTF = new JTextField();  //inizializza il JTextField 'usernameTF'
        usernameTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));  //imposta il colore del bordo del JTextField 'usernameTF'
        passwordF1 = new JPasswordField();  //inizializza il JTextField 'passwordF1'
        passwordF1.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));  //imposta il colore del bordo del JPasswordField 'passwordF1'
        passwordF2 = new JTextField();  //inizializza il JTextField 'passwordF2'
        passwordF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));  //imposta il colore del bordo del JTextField 'passwordF2'
        passwordF3 = new JPasswordField();  //inizializza il JTextField 'passwordF3'
        passwordF3.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));  //imposta il colore del bordo del JPasswordField 'passwordF3'
        partitaIVATF = new JTextField();    //inizializza il JTextField 'partitaIVATF'
        partitaIVATF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));    //imposta il colore del bordo del JTextField 'partitaIVATF'
        dataNascitaTF = new JTextField();   //inizializza il JTextField 'dataNascitaTF'
        dataNascitaTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));   //imposta il colore del bordo del JTextField 'dataNascitaTF'

        usernameTF2 = new JTextField(); //inizializza il JTextField 'usernameTF2'
        usernameTF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831"))); //imposta il colore del bordo del JTextField 'usernameTF2'
        passwordTF4 = new JPasswordField(); //inizializza il JPasswordField 'passwordTF4'
        passwordTF4.setBorder(BorderFactory.createLineBorder(Color.decode("#222831"))); //imposta il colore del bordo del JPasswordField 'passwordTF4'
        passwordTF5 = new JTextField(); //inizializza il JTextField 'passwordTF5'
        passwordTF5.setBorder(BorderFactory.createLineBorder(Color.decode("#222831"))); //imposta il colore del bordo del JTextField 'passwordTF5'

        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));    //carica l'immagine nel percorso /close.png
        Image imagine3 = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
        closeImg = new ImageIcon(imagine3); //reinizializza l'ImageIcon 'closeImg' con l'Image 'imagine3'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar.png"));    //carica l'immagine nel percorso /Calendar.png
        Image calendarIm = calendarIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);    //imposta le dimension dell'immagine
        calendarIco = new ImageIcon(calendarIm);    //reinizializza l'ImageIcon 'calendarIco' con l'Image 'calendarIm'
        calendarIMG = new JLabel(calendarIco);  //inizializza la JLabel 'calendarIMG' con 'calendarIco'
    }
}