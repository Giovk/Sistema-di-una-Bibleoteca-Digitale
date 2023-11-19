package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Profilo {
    public JFrame frame;
    private JButton homeButton;
    private JButton utenteButton;
    private JPanel profilePanel;
    private JTextField usernameField;
    private JButton modificaButton;
    private JButton annullaButton;
    private JButton salvaButton;
    private JPasswordField passwordField1;
    private JTextField nameField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JPasswordField passwordField2;
    private JTextField pIVAField;
    private JLabel pIVALabel;
    private JLabel ripPassLabel;
    private JLabel oldPassLabel;
    private JPasswordField oldPassField;
    private JLabel oldPassNoteLabel;
    private JPanel buttonPanel;
    private JLabel closeBT;
    private JButton libriButton;
    private JLabel username;
    private JLabel showPass;
    private JTextField passwordTextField;
    private JLabel hidePass;
    private JTextField oldPasswordTextField;
    private JLabel showPass2;
    private JLabel hidePass2;
    private JLabel oldPassLabel2;
    private JLabel passLabel2;
    private JLabel passLabel;
    private JLabel cognomeLabel;
    private JLabel nomeLabel;
    private JLabel emailLabel;
    private JButton serieButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JLabel notificheLabelText;
    private JScrollPane notificheScrollPanel;
    private JTable notificheTable;
    private JPanel notifichePanel;
    private JPopupMenu utenteMenu;
    private JPopupMenu tabellaMenu;
    private Boolean active = false;
    private DefaultTableModel model;
    private int row_selected;
    private int numeroNotifiche;

    public Profilo(JFrame frameC, Controller controller) {
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29));
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831));
        UIManager.put("ScrollPane.background\n", new Color(0x222831));

        homeButton.setFont(controller.baseFontSize);
        homeButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));
        libriButton.setFont(controller.baseFontSize);
        libriButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));
        fascicoliButton.setFont(controller.baseFontSize);
        fascicoliButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));
        serieButton.setFont(controller.baseFontSize);
        serieButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));
        utenteButton.setFont(controller.baseFontSize);
        utenteButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));

        modificaButton.setFont(controller.baseFontSize);
        modificaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8)));
        annullaButton.setFont(controller.baseFontSize);
        annullaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8)));
        salvaButton.setFont(controller.baseFontSize);
        salvaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8)));

        username.setFont(controller.impactFontSize);
        nomeLabel.setFont(controller.impactFontSize);
        cognomeLabel.setFont(controller.impactFontSize);
        emailLabel.setFont(controller.impactFontSize);
        passLabel.setFont(controller.impactFontSize);
        passLabel2.setFont(controller.impactFontSize);
        ripPassLabel.setFont(controller.impactFontSize);
        pIVALabel.setFont(controller.impactFontSize);
        oldPassLabel.setFont(controller.impactFontSize);
        oldPassLabel2.setFont(controller.impactFontSize);
        oldPassNoteLabel.setFont(controller.impactFontSize);

        usernameField.setFont(controller.textFieldFont);
        usernameField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        usernameField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        nameField.setFont(controller.textFieldFont);
        nameField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        nameField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        cognomeField.setFont(controller.textFieldFont);
        cognomeField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        cognomeField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        emailField.setFont(controller.textFieldFont);
        emailField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        emailField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordField1.setFont(controller.textFieldFont);
        passwordField1.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordField1.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordField2.setFont(controller.textFieldFont);
        passwordField2.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordField2.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        oldPassField.setFont(controller.textFieldFont);
        oldPassField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        oldPassField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        oldPasswordTextField.setFont(controller.textFieldFont);
        oldPasswordTextField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        oldPasswordTextField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordTextField.setFont(controller.textFieldFont);
        passwordTextField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        passwordTextField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        pIVAField.setFont(controller.textFieldFont);
        pIVAField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));
        pIVAField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));


        notificheLabel.setFont(controller.baseFontSize);
        notificheLabelText.setFont(controller.impactFontSize);

        notificheTable.setFont(controller.impactFontSize);
        notificheTable.setRowMargin(controller.screenWidth/640);
        //notificheTable.setRowHeight(controller.screenHeight/36);

        notificheScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
                this.scrollBarWidth = (int)(controller.screenWidth/75);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));
                    }
                };

                decreaseButton.setBackground(new Color(0x222831));
                return decreaseButton;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton increaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));
                    }
                };

                increaseButton.setBackground(new Color(0x222831));
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;
                    case SwingConstants.NORTH: return uA;
                    case SwingConstants.EAST: return rA;
                    default: return lA;
                }
            }
        });

        utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem utenteExit = new JMenuItem("Logout");//crea la voce del menu "Logout"
        utenteExit.setFont(controller.baseFontSize);
        utenteExit.setHorizontalTextPosition(SwingConstants.CENTER);
        utenteExit.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteExit'
        utenteExit.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteExit'
        utenteExit.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteExit'
        utenteExit.setMinimumSize((new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8))));
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setFont(controller.baseFontSize);
        utenteProfilo.setHorizontalTextPosition(SwingConstants.CENTER);
        utenteProfilo.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusPainted(false);   //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteProfilo'
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusable(false);
        utenteProfilo.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteLibrerie.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setFont(controller.baseFontSize);
        utenteLibrerie.setHorizontalTextPosition(SwingConstants.CENTER);
        utenteLibrerie.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteLibrerie'
        utenteLibrerie.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));
        utenteMenu.setPopupSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/9.6))); //imposta le dimensioni del menu 'utenteMenu'
        utenteMenu.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del menu 'utenteMenu'
        utenteMenu.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del menu 'utenteMenu'
        utenteMenu.add(utenteProfilo);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        tabellaMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem tabellaElimina = new JMenuItem("Elimina");//crea la voce del menu "Logout"
        tabellaElimina.setBackground(new Color(0xFFD369));
        tabellaElimina.setFocusPainted(false);
        tabellaElimina.setFont(controller.baseFontSize);
        tabellaElimina.setBorder(BorderFactory.createEmptyBorder());
        JMenuItem tabellaVisualizzata = new JMenuItem("Notifica Visualizzata"); //crea la voce del menu "Profilo"
        tabellaVisualizzata.setBackground(new Color(0xFFD369));
        tabellaVisualizzata.setFocusPainted(false);
        tabellaVisualizzata.setFont(controller.baseFontSize);
        tabellaVisualizzata.setBorder(BorderFactory.createEmptyBorder());
        tabellaMenu.setBorder(BorderFactory.createEmptyBorder());
        tabellaMenu.setBackground(new Color(0xFFD369));
        tabellaMenu.add(tabellaElimina);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        tabellaMenu.add(tabellaVisualizzata); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));
        }

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(profilePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);


        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                System.exit(0);
            }
        });


        annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
        salvaButton.setVisible(false); //rende invisibile il JButton 'salvaModificheButton'
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

        usernameField.setText(controller.getUsername()); //imposta il testo di 'usernameField' con l'username dell'utente
        nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
        cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
        emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
        passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
        pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVA' con la partita IVA dell'utente


        if (controller.getPartitaIva() == null) {   //controlla se l utente non ha registrato una partita IVA
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
        }

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton utenteButton
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - (int) (controller.screenWidth/15.24), utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
            }
        });


        utenteExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frameC.setVisible(true);    //rende visibile il frame chiamante
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteLibrerie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                BookshopsPage bsp = new BookshopsPage(frameC, controller); //chiama il frame 'pf'
                bsp.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                hp.frame.setVisible(true);  //rende visibile il frame 'hp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                active = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                active = false;
                utenteButton.setBackground(Color.decode("#FFD369"));
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false;
                utenteButton.setBackground(Color.decode("#FFD369"));
            }
        });


        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                BooksPage bp = new BooksPage(frameC, controller);   //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });
        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                libriButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libriButton.setBackground(Color.decode("#FFD369"));
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homeButton.setBackground(Color.decode("#FFD369"));
            }
        });
        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                utenteButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (active == false){
                    super.mouseExited(e);
                    utenteButton.setBackground(Color.decode("#FFD369"));
                }
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieButton.setBackground(Color.decode("#FFD369"));
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SeriesPage sp = new SeriesPage(frameC, controller);   //chiama il frame 'bp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                IssuesPage ip = new IssuesPage(frameC, controller);   //chiama il frame 'bp'
                ip.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoliButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoliButton.setBackground(Color.decode("#FFD369"));
            }
        });

        //-------------------------------------------------------------------------------------------------//

        modificaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                modificaButton.setEnabled(false); //abilita il JButton 'modificaDatiUtenteButton'
                salvaButton.setVisible(true);  //rende visibile il JButton 'salvaModificheButton'
                annullaButton.setVisible(true); //rende visibile il JButton 'annullaButton'
                passwordField2.setVisible(true);    //rende visibile il JPasswordField 'passwordField2'
                ripPassLabel.setVisible(true);  //rende visibile la JLabel 'ripPassLabel'
                oldPassLabel.setVisible(true);  //rende visibile la JLabel 'oldPassLabel'
                oldPassField.setVisible(true);  //rende visibile il JPasswordLabel 'oldPassField'
                oldPassNoteLabel.setVisible(true);  //rende visibile la JLabel 'oldPassNoteLabel'
                passwordField1.setText(""); //imposta il testo del JPasswordField alla stringa vuota
                showPass.setVisible(true);
                showPass2.setVisible(true);

                usernameField.setEnabled(true); //abilita il JTextField 'usernameField'
                nameField.setEnabled(true); //abilita il JTextField 'nameField'
                cognomeField.setEnabled(true);  //abilita il JTextField 'cognomeField'
                emailField.setEnabled(true);    //abilita il JTextField 'emailField'
                passwordField1.setEnabled(true);    //abilita il JTextField 'passwordField'
                pIVAField.setEnabled(true); //abilita il JTextField 'pIVAField'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                modificaButton.setEnabled(true);  //abilita il JButton 'modificaDatiUtenteButton'
                annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
                salvaButton.setVisible(false); //rende invisibile il JButton 'salvaButton'
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
                showPass.setVisible(false);
                showPass2.setVisible(false);
                hidePass.setVisible(false);
                hidePass2.setVisible(false);
                passLabel2.setVisible(false);
                passLabel.setVisible(true);
                passwordField1.setVisible(true);
                passwordTextField.setVisible(false);
                oldPassLabel2.setVisible(false);
                oldPasswordTextField.setVisible(false);


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

        salvaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String emailU = emailField.getText();   //nuova email modificata dall'utente
                String nomeU = nameField.getText(); //nuovo nome modificato dall'utente
                String cognomeU = cognomeField.getText();   //nuovo cognome modificato dall'utente
                String usernameU = usernameField.getText(); //nuovo username modificatao dall'utente
                String pass1 = new String();

                if(showPass.isVisible() == true){
                    char[] password1 = passwordField1.getPassword();
                    pass1 = new String(password1);
                } else if (hidePass.isVisible() == true) {
                    pass1 = passwordTextField.getText();
                }

                char[] password2U = passwordField2.getPassword();   //nuova password ripetuta dall'utente
                String pass2 = new String(password2U);
                String partitaIVA = pIVAField.getText();    //nuova partita IVA modificata dall'utente



                //char[] password3U = oldPassField.getPassword(); //vecchia password inserita dall'utente per meffetuare le modifiche
                String pass3 = new String();
                if(showPass2.isVisible() == true){
                    char[] password2 = oldPassField.getPassword();
                    pass3 = new String(password2);
                } else if (hidePass2.isVisible() == true) {
                    pass3 = oldPasswordTextField.getText();
                }


                if (!controller.getPassword().equals(pass3)){
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La vecchia password non è corretta.");
                } else {
                    if(emailU.isBlank() || nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank()){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Non puoi eliminare questi campi!");
                        usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utente
                        nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                        cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                        emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                    } else {
                        if (usernameField.getText().contains("'") || passwordField1.getText().contains("'") || passwordField2.getText().contains("'")) {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il carattere \"'\" non è consentito per le Password e l'Username");
                        } else {
                            if (controller.verificaEmail(emailU) == false) {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "email non valida!");
                            } else {
                                emailU = controller.changeEmail(emailU);
                                if (controller.verificaNomeCognome(nomeU) == false || controller.verificaNomeCognome(cognomeU) == false) {
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il nome o il cognome non sono validi!");
                                } else {
                                    if (controller.verificaPassword(pass1) == false && !pass1.isBlank()) {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La password deve contenere 8 caratteri di cui: una maiuscola, un numero e un carattare speciale");
                                    } else {
                                        int[] error = controller.validaModUtente(emailU, usernameU, partitaIVA);    //controlla se 'emailU', 'usernameU' e/o 'partitaIVA' sono già stati utilizzati da un altro utente

                                        if (error[0] != 0) {
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'email è gia presente nel sistema.");
                                        } else {
                                            if (error[1] != 0) {
                                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'username è gia stato preso.");
                                            } else {
                                                if (error[2] != 0) {
                                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La Partita IVA è gia presente nel sistema.");
                                                } else {
                                                    if (error[3] != 0) {
                                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La Partita IVA non è corretta.");
                                                    } else {
                                                        if (!pass1.equals(pass2)) {   //controlla se la nuova password è diversa da quella ripetuta
                                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La nuova password non coincide.");
                                                        } else {
                                                            if (error[0] == 0 && error[1] == 0 && error[2] == 0 && error[3] == 0) {//ccontrolla se non ci sono errori
                                                                modificaButton.setEnabled(true);  //abilita il JButton 'modificaDatiUtenteButton'
                                                                annullaButton.setVisible(false);    //rende inivisibile il JButton 'annullaButton'
                                                                salvaButton.setVisible(false); //rende inivisibile il JButton 'salvaModificheButton'
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
                                                                showPass.setVisible(false);
                                                                showPass2.setVisible(false);
                                                                hidePass.setVisible(false);
                                                                hidePass2.setVisible(false);
                                                                passLabel2.setVisible(false);
                                                                passLabel.setVisible(true);
                                                                passwordTextField.setVisible(false);
                                                                passwordField1.setVisible(true);
                                                                oldPassLabel2.setVisible(false);
                                                                oldPasswordTextField.setVisible(false);

                                                                nomeU = nomeU.replace("'", "’");
                                                                cognomeU = cognomeU.replace("'", "’");

                                                                if (partitaIVA.isBlank()) partitaIVA = null;

                                                                if (pass1.isBlank())
                                                                    controller.modUtente(emailU, nomeU, cognomeU, usernameU, controller.getPassword(), partitaIVA); //se la password non è stata modificata, allora modifica i dati del utente usando la password attuale dell'utente
                                                                else
                                                                    controller.modUtente(emailU, nomeU, cognomeU, usernameU, pass1, partitaIVA);   //se la password non è stata modificata, allora modifica i dati del utente usando la nuova password

                                                                usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utete
                                                                nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                                                                cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                                                                emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                                                                passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
                                                                pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVAField' con la partita IVA dell'utente

                                                                passwordField2.setText(""); //imposta il testo di 'passwordField2' con la stringa vuota
                                                                oldPassField.setText("");   //imposta il testo di 'oldPassField' con la stringa vuota

                                                                if (controller.getPartitaIva() == null) {   //controlla se la partita IVA dell'utente è nulla
                                                                    utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
                                                                    utenteMenu.setPopupSize(new Dimension(controller.screenWidth/16, (int) (controller.screenHeight/14.4)));
                                                                    controller.librerieUtente.clear();
                                                                } else {
                                                                    utenteLibrerie.setVisible(true);   //rende invisibile la voce di menu 'utenteLibrerie'
                                                                    utenteMenu.setPopupSize(new Dimension(controller.screenWidth/16, (int) (controller.screenHeight/9.6)));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        modificaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                modificaButton.setIcon(modIco);

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                modificaButton.setIcon(modIco);
            }
        });


        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                annullaButton.setIcon(modIco);

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                annullaButton.setIcon(modIco);
            }
        });


        salvaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                salvaButton.setIcon(modIco);

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                modIco = new ImageIcon(modImg);
                salvaButton.setIcon(modIco);
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e4.png"));
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e3.png"));
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e3.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e4.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e4.png"));
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e3.png"));
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e3.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e4.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordField1.setVisible(false);
                showPass.setVisible(false);
                passLabel.setVisible(false);
                passLabel2.setVisible(true);
                passwordTextField.setVisible(true);
                hidePass.setVisible(true);
                passwordTextField.setText(new String(passwordField1.getPassword()));
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTextField.setVisible(false);
                hidePass.setVisible(false);
                passwordField1.setVisible(true);
                showPass.setVisible(true);
                passwordField1.setText(passwordTextField.getText());
                passLabel.setVisible(true);
                passLabel2.setVisible(false);

            }
        });


        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                oldPassField.setVisible(false);
                showPass2.setVisible(false);
                oldPassLabel.setVisible(false);
                oldPassLabel2.setVisible(true);
                oldPasswordTextField.setVisible(true);
                hidePass2.setVisible(true);
                oldPasswordTextField.setText(new String(oldPassField.getPassword()));
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                oldPasswordTextField.setVisible(false);
                hidePass2.setVisible(false);
                oldPassField.setVisible(true);
                showPass2.setVisible(true);
                oldPassField.setText(oldPasswordTextField.getText());
                oldPassLabel.setVisible(true);
                oldPassLabel2.setVisible(false);
            }
        });

        numeroNotifiche = controller.getNumeroNotificheNonLette();

        setNumeroNotifiche(controller);

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller);
                controller.getNotificheUtente();
                model.setRowCount(0);
                if (controller.listaNotifiche != null) {
                    for (int i = 0; i < controller.listaNotifiche.size(); i++) {
                        model.addRow(new Object[]{controller.listaNotifiche.get(i).testo, controller.listaNotifiche.get(i).dataInvio, controller.listaNotifiche.get(i).oraInvio});
                    }
                }
            }
        });

        timer.start();
        timer.setRepeats(true);
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Testo", "Data", "Ora"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        // Renderer personalizzato per l'header della tabella
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0xCF9E29));
        headerRenderer.setForeground(new Color(0xEEEEEE));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        Font headerFont = new Font("Impact", Font.PLAIN, 15); // Imposta il font Bebas Neue, grandezza 15 e stile Regular
        headerRenderer.setFont(headerFont);
        JTableHeader tableHeader = notificheTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        notificheTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        notificheTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));

        if (controller.listaNotifiche != null) {
            for (int i = 0; i < controller.listaNotifiche.size(); i++) {
                model.addRow(new Object[]{controller.listaNotifiche.get(i).testo, controller.listaNotifiche.get(i).dataInvio, controller.listaNotifiche.get(i).oraInvio});
            }
        }

        notificheTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'

        notificheTable.getColumnModel().getColumn(0).setCellRenderer(new MultiLineTableCellRenderer());

        notificheScrollPanel.setBackground(new Color(0x222831));
        notificheScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        notificheScrollPanel.getViewport().setBackground(new Color(0x222831));


        notificheTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){
                    row_selected = notificheTable.rowAtPoint(e.getPoint());
                    notificheTable.setRowSelectionInterval(row_selected, row_selected);


                    if (controller.getLetturaNotifica(notificheTable.getValueAt(row_selected, 0).toString(), notificheTable.getValueAt(row_selected, 1).toString(), notificheTable.getValueAt(row_selected, 2).toString()) == true){
                        tabellaVisualizzata.setVisible(false);
                    } else tabellaVisualizzata.setVisible(true);
                    tabellaMenu.show(notificheTable, e.getX(), e.getY());
                }
            }
        });

        tabellaElimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comferma = 0;
                comferma = new NewComfirmMessageDialog().comfirmDialog("Vuoi davvero eliminare questa notifica?");

                if(comferma == 1){
                    controller.rimuoviNotifica(notificheTable.getValueAt(row_selected, 0).toString(), notificheTable.getValueAt(row_selected, 1).toString(), notificheTable.getValueAt(row_selected, 2).toString());
                    model.removeRow(row_selected);
                    setNumeroNotifiche(controller);
                }
            }
        });

        tabellaVisualizzata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.visualizzaNotifica(notificheTable.getValueAt(row_selected, 0).toString(), notificheTable.getValueAt(row_selected, 1).toString(), notificheTable.getValueAt(row_selected, 2).toString());
                setNumeroNotifiche(controller);
                model.setRowCount(0);
                if (controller.listaNotifiche != null) {
                    for (int i = 0; i < controller.listaNotifiche.size(); i++) {
                        model.addRow(new Object[]{controller.listaNotifiche.get(i).testo, controller.listaNotifiche.get(i).dataInvio, controller.listaNotifiche.get(i).oraInvio});
                    }
                }
            }
        });
    }

    private void setNumeroNotifiche(Controller controller){
        numeroNotifiche = controller.getNumeroNotificheNonLette();

        if(numeroNotifiche < 100 && numeroNotifiche > 0){
            notificheLabel.setVisible(true);
            String numeroNotificheText = Integer.toString(numeroNotifiche);
            notificheLabel.setText(numeroNotificheText);
        }else if (numeroNotifiche >= 100) notificheLabel.setText("99+");
        else {
            notificheLabel.setVisible(false);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        cognomeField = new JTextField();
        cognomeField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        pIVAField = new JTextField();
        pIVAField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        passwordTextField = new JTextField();
        passwordTextField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        oldPasswordTextField = new JTextField();
        oldPasswordTextField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        oldPassField = new JPasswordField();
        oldPassField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        passwordField1 = new JPasswordField();
        passwordField1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
        passwordField2 = new JPasswordField();
        passwordField2.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e3.png"));
        Image showPimg = showPico.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        showPico = new ImageIcon(showPimg);
        showPass = new JLabel(showPico);
        showPass2 = new JLabel(showPico);

        ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e4.png"));
        Image hidePimg = hidePico.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        hidePico = new ImageIcon(hidePimg);
        hidePass = new JLabel(hidePico);
        hidePass2 = new JLabel(hidePico);

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);


        ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png"));
        Image modImg = modIco.getImage().getScaledInstance((int) (screenWidth/14.2222), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        modIco = new ImageIcon(modImg);
        modificaButton = new JButton(modIco);
        annullaButton = new JButton(modIco);
        salvaButton = new JButton(modIco);

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        notificaIco = new ImageIcon(notificaImg);

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);
    }
}