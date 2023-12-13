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
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29)); //imposta il colore dello sfondo di un elemento di menu quando viene selezionato
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831)); //imposta il colore del testo di un elemento di menu quando viene selezionato
        UIManager.put("ScrollPane.background\n", new Color(0x222831));  //imposta il colore dello sfondo del JScrollPane
        homeButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'homeButton'
        homeButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight())); //imposta la dimensione minima del JButton 'homeButton'
        libriButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'libriButton'
        libriButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'libriButton'
        fascicoliButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'fascicoliButton'
        fascicoliButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'fascicoliButton'
        serieButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'serieButton'
        serieButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'serieButton'
        utenteButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'utenteButton'
        utenteButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));   //imposta la dimensione minima del JButton 'utenteButton'

        modificaButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'modificaButton'
        modificaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8))); //imposta la dimensione minima del JButton 'modificaButton'
        annullaButton.setFont(controller.baseFontSize); //imposta il font del JButton 'annullaButton'
        annullaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8)));  //imposta la dimensione minima del JButton 'annullaButton'
        salvaButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'salvaButton'
        salvaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/13.4736), (int) (controller.screenHeight/28.8)));    //imposta la dimensione minima del JButton 'salvaButton'

        username.setFont(controller.impactFontSize);    //imposta il font della JLabel 'username'
        nomeLabel.setFont(controller.impactFontSize);   //imposta il font della JLabel 'nomeLabel'
        cognomeLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'cognomeLabel'
        emailLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'emailLabel'
        passLabel.setFont(controller.impactFontSize);   //imposta il font della JLabel 'passLabel'
        passLabel2.setFont(controller.impactFontSize);  //imposta il font della JLabel 'passLabel2'
        ripPassLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'ripPassLabel'
        pIVALabel.setFont(controller.impactFontSize);   //imposta il font della JLabel 'pIVALabel'
        oldPassLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'oldPassLabel'
        oldPassLabel2.setFont(controller.impactFontSize);   //imposta il font della JLabel 'oldPassLabel2'
        oldPassNoteLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'oldPassNoteLabel'

        usernameField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'usernameField'
        usernameField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione minima del JTextField 'usernameField'
        usernameField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione massima del JTextField 'usernameField'
        nameField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'nameField'
        nameField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione minima del JTextField 'nameField'
        nameField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione massima del JTextField 'nameField'
        cognomeField.setFont(controller.textFieldFont); //imposta il font del JTextField 'cognomeField'
        cognomeField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione minima del JTextField 'cognomeField'
        cognomeField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione massima del JTextField 'cognomeField'
        emailField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'emailField'
        emailField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione minima del JTextField 'emailField'
        emailField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione massima del JTextField 'emailField'
        passwordField1.setFont(controller.textFieldFont);   //imposta il font del JPasswordField 'passwordField1'
        passwordField1.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione minima del JPasswordField 'passwordField1'
        passwordField1.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione massima del JPasswordField 'passwordField1'
        passwordField2.setFont(controller.textFieldFont);   //imposta il font del JPasswordField 'passwordField2'
        passwordField2.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione minima del JPasswordField 'passwordField2'
        passwordField2.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));  //imposta la dimensione massima del JPasswordField 'passwordField2'
        oldPassField.setFont(controller.textFieldFont); //imposta il font del JPasswordField 'oldPassField'
        oldPassField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione minima del JPasswordField 'oldPassField'
        oldPassField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione massima del JPasswordField 'oldPassField'
        oldPasswordTextField.setFont(controller.textFieldFont); //imposta il font del JTextField 'oldPasswordTextField'
        oldPasswordTextField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione minima del JTextField 'oldPasswordTextField'
        oldPasswordTextField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));    //imposta la dimensione massima del JTextField 'oldPasswordTextField'
        passwordTextField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'passwordTextField'
        passwordTextField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione minima del JTextField 'passwordTextField'
        passwordTextField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione massima del JTextField 'passwordTextField'
        pIVAField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'pIVAField'
        pIVAField.setMinimumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione minima del JTextField 'pIVAField'
        pIVAField.setMaximumSize(new Dimension((int) (controller.screenWidth/5.12), -1));   //imposta la dimensione massima del JTextField 'pIVAField'

        notificheLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'notificheLabel'
        notificheLabelText.setFont(controller.impactFontSize);  //imposta il font della JLabel 'notificheLabelText'

        notificheTable.setFont(controller.impactFontSize);  //imposta il font della JTable 'notificheTable'
        notificheTable.setRowMargin(controller.screenWidth/640);    //imposta il margine tra le celle della JTable 'notificheTable'

        notificheScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);  //inizializza il colore della parte mobile della barra di scorrimento
                this.trackColor= new Color(0xFFD369);   //inizializza il colore della parte fissa della barra di scorrimento
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true); //inizializza il colore della parte più scura dell'ombra del lato inferiore della parte mobile della barra di scorrimento
                this.thumbLightShadowColor = new Color(0x323A48); //inizializza il colore della parte piu chiara dell'ombra del lato superiore della parte mobile della barra di scorrimento
                this.thumbHighlightColor = new Color(0x323A48); //inizializza il colore della parte mobile della barra di scorrimento quando viene attivata
                this.trackHighlightColor = new Color(0xCF9E29); //inizializza il colore della parte fissa della barra di scorrimento quando viene attivata
                this.scrollBarWidth = (int)(controller.screenWidth/75); //imposta la larghezza della barra di scorrimento
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));  //inizializza le dimensioni del JButton 'decreaseButton'
                    }
                };

                decreaseButton.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JButton 'decreaseButton'
                return decreaseButton;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton increaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));  //inizializza le dimensioni del JButton 'increaseButton'
                    }
                };

                increaseButton.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JButton 'increaseButton'
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;   //restituisce 'dA'
                    case SwingConstants.NORTH: return uA;   //restituisce 'uA'
                    case SwingConstants.EAST: return rA;    //restituisce 'rA'
                    default: return lA; //restituisce 'lA'
                }
            }
        });

        utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem utenteExit = new JMenuItem("Logout");//crea la voce del menu "Logout"
        utenteExit.setFont(controller.baseFontSize);    //imposta il font del JMenuItem 'utenteExit'
        utenteExit.setHorizontalTextPosition(SwingConstants.CENTER);    //centra orizzontalmente il testo del JMenuItem 'utenteExit'
        utenteExit.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteExit'
        utenteExit.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteExit'
        utenteExit.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteExit'
        utenteExit.setMinimumSize((new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)))); //imposta la dimensione minima del JMenuItem 'utenteExit'
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setFont(controller.baseFontSize); //imposta il font del JMenuItem 'utenteProfilo'
        utenteProfilo.setHorizontalTextPosition(SwingConstants.CENTER); //centra orizzontalmente il testo del JMenuItem 'utenteProfilo'
        utenteProfilo.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusPainted(false);   //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteProfilo'
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusable(false);  //impedisce all'utente di interagire con il JMenuItem 'utenteProfilo' tramite tastiera
        utenteProfilo.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));    //imposta la dimensione minima del JMenuItem 'utenteProfilo'
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteLibrerie.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setFont(controller.baseFontSize);    //imposta il font del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setHorizontalTextPosition(SwingConstants.CENTER);    //centra orizzontalmente il testo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteLibrerie'
        utenteLibrerie.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));   //imposta la dimensione minima del JMenuItem 'utenteLibrerie'
        utenteMenu.setPopupSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/9.6))); //imposta le dimensioni del menu 'utenteMenu'
        utenteMenu.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del menu 'utenteMenu'
        utenteMenu.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del menu 'utenteMenu'
        utenteMenu.add(utenteProfilo);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        tabellaMenu = new JPopupMenu();  //crea il menu 'utenteMenu'

        JMenuItem tabellaElimina = new JMenuItem("Elimina");//crea la voce del menu "Logout"

        tabellaElimina.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'tabellaElimina'
        tabellaElimina.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'tabellaElimina'
        tabellaElimina.setFont(controller.baseFontSize);    //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'tabellaElimina'
        tabellaElimina.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'tabellaElimina'

        JMenuItem tabellaVisualizzata = new JMenuItem("Notifica Visualizzata"); //crea la voce del menu "Profilo"

        tabellaVisualizzata.setBackground(new Color(0xFFD369)); //imposta il colore dello sfondo del JMenuItem 'tabellaVisualizzata'
        tabellaVisualizzata.setFocusPainted(false); //imposta il colore dello sfondo del JMenuItem 'tabellaVisualizzata'
        tabellaVisualizzata.setFont(controller.baseFontSize);   //imposta il colore dello sfondo del JMenuItem 'tabellaVisualizzata'
        tabellaVisualizzata.setBorder(BorderFactory.createEmptyBorder());   //imposta il colore dello sfondo del JMenuItem 'tabellaVisualizzata'
        tabellaMenu.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del menu 'tabellaMenu'
        tabellaMenu.setBackground(new Color(0xFFD369)); //imposta il colore dello sfondo del menu 'tabellaMenu'
        tabellaMenu.add(tabellaElimina);  //aggiunge la voce 'tabellaElimina' al menu 'tabellaMenu'
        tabellaMenu.add(tabellaVisualizzata); //aggiunge la voce 'tabellaVisualizzata' al menu 'tabellaMenu'

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));    //adatta le dimensioni minime di 'utenteMenu'
        }

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(profilePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                System.exit(0); //termina l'esecuzione
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
                BookshopsPage bsp = new BookshopsPage(frameC, controller); //chiama il frame 'bsp'
                bsp.frame.setVisible(true);  //rende visible il frame 'bsp'
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
                active = true;  //aggiorna 'active'
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
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
                libriButton.setBackground(Color.decode("#cf9e29")); //imposta lo sfondo del JButton 'libriButton'
            }
        });
        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libriButton.setBackground(Color.decode("#FFD369")); //imposta lo sfondo del JButton 'libriButton'
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeButton.setBackground(Color.decode("#cf9e29"));  //imposta lo sfondo del JButton 'homeButton'
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homeButton.setBackground(Color.decode("#FFD369"));  //imposta lo sfondo del JButton 'homeButton'
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                utenteButton.setBackground(Color.decode("#cf9e29"));    //imposta lo sfondo del JButton 'utenteButton'
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (active == false){   //controlla se è stato disattivato il menu "Utente"
                    super.mouseExited(e);
                    utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
                }
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieButton.setBackground(Color.decode("#cf9e29")); //imposta lo sfondo del JButton 'serieButton'
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieButton.setBackground(Color.decode("#FFD369")); //imposta lo sfondo del JButton 'serieButton'
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SeriesPage sp = new SeriesPage(frameC, controller);   //chiama il frame 'sp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'sp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                IssuesPage ip = new IssuesPage(frameC, controller);   //chiama il frame 'ip'
                ip.frame.setVisible(true);  //rende visibile il frame chiamato 'ip'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoliButton.setBackground(Color.decode("#cf9e29")); //imposta lo sfondo del JButton 'fascicoliButton'
            }
        });
        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoliButton.setBackground(Color.decode("#FFD369")); //imposta lo sfondo del JButton 'fascicoliButton'
            }
        });


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
                showPass.setVisible(true);  //rende visibile la JLabel 'showPass'
                showPass2.setVisible(true); //rende visibile la JLabel 'showPass2'

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
                showPass.setVisible(false); //rende invisibile la JLabel 'showPass'
                showPass2.setVisible(false);    //rende invisibile la JLabel 'showPass2'
                hidePass.setVisible(false); //rende invisibile la JLabel 'hidePass'
                hidePass2.setVisible(false);    //rende invisibile la JLabel 'hidePass2'
                passLabel2.setVisible(false);   //rende invisibile la JLabel 'passLabel2'
                passLabel.setVisible(true); //rende visibile la JLabel 'passLabel'
                passwordField1.setVisible(true);    //rende invisibile il JPasswordField 'passwordField1'
                passwordTextField.setVisible(false);    //rende invisibile il JTextField 'passwordTextField'
                oldPassLabel2.setVisible(false);    //rende invisibile la JLabel 'oldPassLabel2'
                oldPasswordTextField.setVisible(false); //rende invisibile il JTextField 'oldPasswordTextField'

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

                if (hidePass.isVisible() == true) { //controlla se 'hidePass' è visibile
                    pass1 = passwordTextField.getText();    //inserisce il testo del JTextField 'passwordTextField' in 'pass1'
                }else if(showPass.isVisible() == true){ //controlla se 'showPass' è visibile
                    char[] password1 = passwordField1.getPassword();    //inserisce il testo del JPasswordField 'passwordField1' in 'password1'
                    pass1 = new String(password1);  //inserisce il contenuto di 'password1' in 'pass1'
                }

                char[] password2U = passwordField2.getPassword();   //nuova password ripetuta dall'utente
                String pass2 = new String(password2U);
                String partitaIVA = pIVAField.getText();    //nuova partita IVA modificata dall'utente

                String pass3 = new String();

                if (hidePass2.isVisible() == true) {    //controlla se 'hidePass2' è visibile
                    pass3 = oldPasswordTextField.getText(); //inserisce il testo del JTextField 'oldPasswordTextField' in 'pass3'
                }
                else if(showPass2.isVisible() == true){ //controlla se 'showPass2' è visibile
                    char[] password2 = oldPassField.getPassword();  //inserisce il testo del JPasswordField 'oldPasswordField1' in 'password2'
                    pass3 = new String(password2);  //inserisce il contenuto di 'password2' in 'pass3'
                }

                if (!controller.getPassword().equals(pass3)){   //controlla se la vecchia password inserita è giusta
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La vecchia password non è corretta.");   //mostra un messaggio di errore
                } else {
                    if(emailU.isBlank() || nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank()){   //controlla se è stato eliminato uno dei campi obbligatori
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Non puoi eliminare questi campi!");  //mostra un messaggio di errore
                        usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utente
                        nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                        cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                        emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                    } else {
                        if (usernameField.getText().contains("'") || passwordField1.getText().contains("'") || passwordField2.getText().contains("'")) {    //controlla se 'usernameField', ' passwordField1' o 'passwordField2' contiene il carattere "'"
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il carattere \"'\" non è consentito per le Password e l'Username");  //mostra un messaggio di errore
                        } else {
                            if (controller.verificaEmail(emailU) == false) {    //controlla se il formato dell'email iserita non è corretto
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "email non valida!"); //mostra un messaggio di errore
                            } else {
                                emailU = controller.changeEmail(emailU);    //aggiorna l'email dell'utente

                                if (controller.verificaNomeCognome(nomeU) == false || controller.verificaNomeCognome(cognomeU) == false) {  //controlla se il formato del nome o del cognome iseriti non sono corretti
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il nome o il cognome non sono validi!"); //mostra un messaggio di errore
                                } else {
                                    if (controller.verificaPassword(pass1) == false && !pass1.isBlank()) {  //controlla se il formato della password iserita non è corretto
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La password deve contenere 8 caratteri di cui: una maiuscola, un numero e un carattare speciale");   //mostra un messaggio di errore
                                    } else {
                                        int[] error = controller.validaModUtente(emailU, usernameU, partitaIVA);    //controlla se 'emailU', 'usernameU' e/o 'partitaIVA' sono già stati utilizzati da un altro utente

                                        if (error[0] != 0) {    //controlla se la nuova email è già stata utilizzata
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'email è già stata utilizzata.");   //mostra un messaggio di errore
                                        } else {
                                            if (error[1] != 0) {    //controlla se il nuovo username è già stato utilizzato
                                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'username è già stato utilizzato."); //mostra un messaggio di errore
                                            } else {
                                                if (error[2] != 0) {    //controlla se la nuova partita IVA è già stata utilizzata
                                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La partita IVA è già stata utilizzata.");    //mostra un messaggio di errore
                                                } else {
                                                    if (error[3] != 0) {    //controlla se il formato della partia IVA iserita non è corretto
                                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La partita IVA non è corretta.");    //mostra un messaggio di errore
                                                    } else {
                                                        if (!pass1.equals(pass2)) {   //controlla se la nuova password è diversa da quella ripetuta
                                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "La nuova password non coincide.");   //mostra un messaggio di errore
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
                                                                showPass.setVisible(false); //rende invisibile la JLabel 'showPass'
                                                                showPass2.setVisible(false);    //rende invisibile la JLabel 'showPass2'
                                                                hidePass.setVisible(false); //rende invisibile la JLabel 'hidePass'
                                                                hidePass2.setVisible(false);    //rende invisibile la JLabel 'hidePass2'
                                                                passLabel2.setVisible(false);   //rende invisibile la JLabel 'passLabel2'
                                                                passLabel.setVisible(true); //rende visibile la JLabel 'passLabel'
                                                                passwordTextField.setVisible(false);    //rende invisibile il JTextField 'passwordTextField'
                                                                passwordField1.setVisible(true);    //rende visibile il JPasswordField 'passwordField1'
                                                                oldPassLabel2.setVisible(false);    //rende invisibile la JLabel 'oldPassLabel2'
                                                                oldPasswordTextField.setVisible(false); //rende invisibile il JTextField 'oldPasswordTextField'

                                                                nomeU = nomeU.replace("'", "’");    //sostutuisce il carattere "'" in 'nomeU' con il carattere "’"
                                                                cognomeU = cognomeU.replace("'", "’");  //sostutuisce il carattere "'" in 'nomeU' con il carattere "’"

                                                                if (partitaIVA.isBlank()){  //controlla se l'utente ha inserito una partita IVA
                                                                    partitaIVA = null;  //pone a NULL 'partitaIVA'
                                                                }

                                                                if (pass1.isBlank()) {  //controlla se la password non è stata modificata
                                                                    controller.modUtente(emailU, nomeU, cognomeU, usernameU, controller.getPassword(), partitaIVA); //modifica i dati del utente usando la password attuale dell'utente
                                                                }else {
                                                                    controller.modUtente(emailU, nomeU, cognomeU, usernameU, pass1, partitaIVA);   //modifica i dati del utente usando la nuova password
                                                                }

                                                                usernameField.setText(controller.getUsername());    //imposta il testo di 'usernameField' con l'username dell'utete
                                                                nameField.setText(controller.getNome());    //imposta il testo di 'nameField' con il nome dell'utente
                                                                cognomeField.setText(controller.getCognome());  //imposta il testo di 'cognomeField' con il cognome dell'utente
                                                                emailField.setText(controller.getEmail());  //imposta il testo di 'emailField' con l'email dell'utente
                                                                passwordField1.setText(controller.getPassword());   //imposta il testo di 'passwordField1' con la password dell'utente
                                                                pIVAField.setText(controller.getPartitaIva());  //imposta il testo di 'pIVAField' con la partita IVA dell'utente

                                                                passwordField2.setText(""); //imposta il testo di 'passwordField2' con la stringa vuota
                                                                oldPassField.setText("");   //imposta il testo di 'oldPassField' con la stringa vuota

                                                                if (controller.getPartitaIva() != null) {   //controlla se la partita IVA dell'utente non è nulla
                                                                    utenteLibrerie.setVisible(true);   //rende invisibile la voce di menu 'utenteLibrerie'
                                                                    utenteMenu.setPopupSize(new Dimension(controller.screenWidth/16, (int) (controller.screenHeight/9.6))); //imposta le dimensioni del menu 'utenteMenu'
                                                                } else {
                                                                    utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
                                                                    utenteMenu.setPopupSize(new Dimension(controller.screenWidth/16, (int) (controller.screenHeight/14.4)));    //imposta le dimensioni del menu 'utenteMenu'
                                                                    controller.librerieUtente.clear();  //svuota 'controller.librerieUtente'
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

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png")); //carica l'immagine nel percorso /button-type2.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                modificaButton.setIcon(modIco); //inizializza il JButton 'modificaButton' con 'modIco'
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                modificaButton.setIcon(modIco); //inizializza il JButton 'modificaButton' con 'modIco'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png")); //carica l'immagine nel percorso /button-type2.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                annullaButton.setIcon(modIco);  //inizializza il JButton 'annullaButton' con 'modIco'

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                annullaButton.setIcon(modIco);  //inizializza il JButton 'annullaButton' con 'modIco'
            }
        });

        salvaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type2.png")); //carica l'immagine nel percorso /button-type2.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                salvaButton.setIcon(modIco);    //inizializza il JButton 'salvaButton' con 'modIco'

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon modIco = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
                Image modImg = modIco.getImage().getScaledInstance((int) (controller.screenWidth/14.2222), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

                modIco = new ImageIcon(modImg); //reinizializza l'ImageIcon 'modIco' con l'Image 'modImg'
                salvaButton.setIcon(modIco);    //inizializza il JButton 'salvaButton' con 'modIco'
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e4.png")); //carica l'immagine nel percorso /e4.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass2.setIcon(showPico);    //imposta l'icona della JLabel 'showPass2' con 'showPimg'
            }
        });

        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e3.png")); //carica l'immagine nel percorso /e3.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass2.setIcon(showPico);    //imposta l'icona della JLabel 'showPass2' con 'showPico'
            }
        });

        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e3.png")); //carica l'immagine nel percorso /e3.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass2.setIcon(hidePico);    //imposta l'icona della JLabel 'showPass2' con 'hidePico'
            }
        });

        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e4.png")); //carica l'immagine nel percorso /e4.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass2.setIcon(hidePico);    //imposta l'icona della JLabel 'showPass2' con 'hidePico'
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e4.png")); //carica l'immagine nel percorso /e4.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass.setIcon(showPico); //imposta l'icona della JLabel 'showPass' con 'showPico'
            }
        });

        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e3.png")); //carica l'immagine nel percorso /e3.png
                Image showPimg = showPico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                showPico = new ImageIcon(showPimg); //reinizializza l'ImageIcon 'showPico' con l'Image 'showPimg'
                showPass.setIcon(showPico); //imposta l'icona della JLabel 'showPass' con 'showPico'
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e3.png")); //carica l'immagine nel percorso /e3.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass.setIcon(hidePico); //imposta l'icona della JLabel 'hidePass' con 'hidePico'
            }
        });

        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e4.png")); //carica l'immagine nel percorso /e4.png
                Image hidePimg = hidePico.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

                hidePico = new ImageIcon(hidePimg); //reinizializza l'ImageIcon 'hidePico' con l'Image 'hidePimg'
                hidePass.setIcon(hidePico); //imposta l'icona della JLabel 'hidePass' con 'hidePico'
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
                    } else {
                        tabellaVisualizzata.setVisible(true);
                    }

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