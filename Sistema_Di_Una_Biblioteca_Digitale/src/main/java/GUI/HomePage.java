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
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class HomePage {
    public JFrame frame;
    private JPanel homepagePanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JPanel buttonPanel;
    private JLabel closeBT;
    private JButton serieButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JTextField searchBarField;
    private JLabel searchImage;
    private JPanel jpanel2;
    private ButtonGroup groupRB;
    private JRadioButton libriRB;
    private JRadioButton serieRB;
    private JRadioButton fascicoliRB;
    private JLabel resetFiltriLabel;
    private JScrollPane mainScrollPanel;
    private JTable mainTable;
    private JPanel panel2;
    private JPopupMenu utenteMenu;
    private Boolean active = false;
    private DefaultTableModel model;
    private int numeroNotifiche;

    public HomePage(JFrame frameC, Controller controller) {
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29)); //imposta il colore dello sfondo di un elemento di menu quando viene selezionato
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831)); //imposta il colore del testo di un elemento di menu quando viene selezionato
        UIManager.put("ScrollPane.background\n", new Color(0x222831)); //imposta il colore dello sfondo del 'JScrollPane'

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

        notificheLabel.setFont(controller.baseFontSize);

        searchBarField.setFont(controller.textFieldFont);

        libriRB.setFont(controller.impactFontSize);
        serieRB.setFont(controller.impactFontSize);
        fascicoliRB.setFont(controller.impactFontSize);
        resetFiltriLabel.setFont(controller.impactFontSize);

        mainTable.setFont(controller.impactFontSize);
        mainTable.setRowMargin(controller.screenWidth/640);
        mainTable.setRowHeight(controller.screenHeight/36);

        panel2.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4))));

        mainScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png")); //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);  //inizializza il colore della parte mobile della barra di scorrimento
                this.trackColor= new Color(0xFFD369);   //inizializza il colore della parte fissa della barra di scorrimento
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true); //inizializza il colore della parte più scura dell'ombra del lato inferiore della parte mobile della barra di scorrimento
                this.thumbLightShadowColor = new Color(0x323A48); //inizializza il colore della parte piu chiara dell'ombra del lato superiore della parte mobile della barra di scorrimento
                this.thumbHighlightColor = new Color(0x323A48); //inizializza il colore della parte mobile della barra di scorrimento quando viene attivata
                this.trackHighlightColor = new Color(0xCF9E29); //inizializza il colore della parte fissa della barra di scorrimento quando viene attivata
                this.scrollBarWidth = (int)(controller.screenWidth/75);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'increaseButton'
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

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));
        }

        frame = new JFrame("Homepage");
        frame.setUndecorated(true); //rimuove la decorazione della finestra
        frame.setContentPane(homepagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();


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


        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton utenteButton
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - (int) (controller.screenWidth/15.24), utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
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
                utenteButton.setBackground(Color.decode("#FFD369")); //imposta lo sfondo del JButton 'utenteButton'
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }
        });

        utenteProfilo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Profilo pf = new Profilo(frameC, controller); //chiama il frame 'pf'
                pf.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        utenteExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frameC.setVisible(true); //rende visibile il frame chiamante
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


        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                libriButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'libriButton'
            }
        });

        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libriButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'libriButton'
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                utenteButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'utenteButton'
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (active == false){   //controlla se è stato disattivato il menu "Utente"
                    super.mouseExited(e);
                    utenteButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'utenteButton'
                }
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'serieButton'
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    serieButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'serieButton'
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoliButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'fascicoliButton'
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoliButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'fascicoliButton'
            }
        });

        controller.getNotificheUtente();    //inizializza 'controller.listaNotifiche'
        setNumeroNotifiche(controller); //inizializza il testo della JLabel 'notificheLabel'

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller); //aggiorna il testo della JLabel 'notificheLabel'
            }
        });

        timer.start();  //avvia il timer
        timer.setRepeats(true); //fa ripetere il timer dopo che è scaduto

        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));  //imposta il colore del testo di un combo box quando viene disabilitato
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));  //imposta il colore dello sfondo di un combo box quando viene disabilitato

        ArrayList<String> distinctGenereList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati

        for (int i = 0; i < controller.listaLibri.size(); i++) {    //scorre l'ArrayList di tutti i libri
            if (!distinctGenereList.contains(controller.listaLibri.get(i).genere))    //controlla se 'distinctGenereList' non contiene il genere dell'i-esimo libro di 'controller.listaLibri'
                distinctGenereList.add(controller.listaLibri.get(i).genere);  //inserisce il genere dell'i-esimo libro in 'distinctGenereList'
        }

        ArrayList<String> totAutoreList = controller.allAutoriLibri(); //contiene i nomi e cognomi concatenati di tutti gli autori dei libri
        ArrayList<String> distinctAutoreList = controller.allAutoriDistintiLibri();   //contiene i nomi e i cognomi concatenati di tutti gli autori dei libri evitando dei duplicati

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Prodotto", "Quantità", "Fruizione", "Libreria", "Indirizzo", "Numero Di Telefono", "Sito Web"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0xCF9E29));  //imposta il colore dello sfondo dell'header della tabella
        headerRenderer.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo dell'header della tabella
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);   //centra orizzontalmente il contenuto dell'header della tabella
        Font headerFont = new Font("Impact", Font.PLAIN, 15); //inizializza il font Bebas Neue, grandezza 15 e stile Regular per i caratteri della tabella
        headerRenderer.setFont(headerFont); //imposta il font del contenuto delle celle della tabella a 'headerFont'
        JTableHeader tableHeader = mainTable.getTableHeader(); //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'mainTable'
        tableHeader.setDefaultRenderer(headerRenderer); //imposta il render di default della tabella a 'headerRender'

        mainTable.getTableHeader().setResizingAllowed(false);   //impedisce il ridimensionamento delle colonne

        mainTable.getTableHeader().setReorderingAllowed(false); //impedisce il riordinamento delle colonne

        tableHeader.setBorder(null);    //rimuove il bordo dell'header della tabella

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));  //inposta il render di default della tabella

        mainTable.setModel(model); //imposta il modello dei dati della JTable 'mainTable'
        mainScrollPanel.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JScrollPane 'mainScrollPanel'
        mainScrollPanel.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JScrollPane 'mainScrollPanel'
        mainScrollPanel.getViewport().setBackground(new Color(0x222831));   //imposta il colore dello sfondo della parte visibile del JScrollPane 'mainScrollPanel'

        controller.getInfoLibriPreferiti(); //inizializza 'controller.libriTitoloPreferiti', 'controller.possessolPreferiti' e 'controller.librerieLibriPreferiti'
        controller.getInfoSeriePreferiti(); //inizializza 'controller.serieTitoloPreferiti', 'controller.possessosPreferiti' e 'controller.librerieSeriePreferiti'
        controller.getInfoFascicoliPreferiti(); //inizializza 'controller.fascicoloTitoloPreferiti', 'controller.possessofPreferiti' e 'controller.librerieFascicoliPreferiti'

        if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {    //controlla se 'controller.libriTitoloPreferiti', 'controller.possessolPreferiti' e 'controller.librerieLibriPreferiti' non sono a null
            for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {  //scorre la lista dei libri preferiti dell'utente
                if (controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) { //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});    //aggiunge una nuova riga nella tabella
                } else if (controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) {   //controlla se l'i-esimo libro non è disponibile
                    model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile", controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                } else {
                    model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita, controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {    //controlla se 'controller.serieTitoloPreferiti', 'controller.possessosPreferiti' e 'controller.librerieSeriePreferiti' non sono a null
            for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {  //scorre la lista delle serie preferite dell'utente
                if(controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                }else if(controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0) { //controlla se l'i-esima serie non è disponibile
                    model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile" , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                } else {
                    model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {    //controlla se 'controller.fascicoliTitoloPreferiti', 'controller.possessofPreferiti' e 'controller.librerieFascicoliPreferiti' non sono a null
            for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {  //scorre la lista dei fascicoli preferiti dallutente
                if(controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});    //aggiunge una nuova riga nella tabella
                } else if(controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0) {    //controlla se l'i-esimo fascicolo non è disponibile
                    model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile", controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                } else {
                    model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita, controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);     //rende visibile il frame

        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(mainTable.isColumnSelected(6)){  //controlla se è stata selezionata la colonna "Sito Web"
                    try {
                        Desktop.getDesktop().browse(new URI(mainTable.getValueAt(mainTable.getSelectedRow(), 6).toString()));   //apre il link selezionato nella tabella
                    }catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if(controller.libriTitoloPreferiti.contains(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())){   //controlla se il contenuto della cella "Prodotto" della riga selezionata è presente in 'controller.libriTitoloPreferiti'
                        controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(20,mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().length()); //inizializza 'controller.nome_selected' con il titolo del libro selezionato
                        controller.isbn_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(0, 17); //inizializza 'controller.isbn_selected' con l'ISBN del libro selezionato
                        BookPage bp = new BookPage(frameC, controller); //chiama il frame 'bp'
                        bp.frame.setVisible(true);  //rende visible il frame 'bp'
                        frame.setVisible(false);    //rende invisibile il frame
                        frame.dispose();
                    } else if(controller.serieTitoloPreferiti.contains(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())){    //controlla se il contenuto della cella "Prodotto" della riga selezionata è presente in 'controller.serieTitoloPreferiti'
                        controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(20,mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().length());    //inizializza 'controller.nome_selected' con il titolo della serie selezionata
                        controller.isbn_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(0, 17); //inizializza 'controller.isbn_selected' con l'ISBN della serie selezionata
                        SeriePage sp = new SeriePage(frameC, controller); //chiama il frame 'sp'
                        sp.frame.setVisible(true);  //rende visible il frame 'sp'
                        frame.setVisible(false);    //rende invisibile il frame
                        frame.dispose();
                    } else {
                        controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();  //inizializza 'controller.nome_selected' con il contenuto della cella "Prodotto" della riga selezionata (titoloRivista N° numeroFascicolo)
                        int numero = Integer.valueOf(controller.nome_selected.substring(controller.nome_selected.indexOf("N°")+2, controller.nome_selected.length()));  //numero del fascicolo selezionato
                        controller.selezionaFascicolo(numero, controller.nome_selected.substring(0, controller.nome_selected.indexOf("N°")-1)); //inizializza 'controller.fascicolo_selected'
                        IssuePage ip = new IssuePage(frameC, controller); //chiama il frame 'ip'
                        ip.frame.setVisible(true);  //rende visible il frame 'ip'
                        frame.setVisible(false);    //rende invisibile il frame
                        frame.dispose();
                    }
                }

                mainTable.clearSelection(); //deseleziona tutte le celle selezionate in 'mainTable'
            }
        });

        libriRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){  //controlla se è stato selelzionato il JRadioButton 'libriRB'
                    model.setRowCount(0);   //rimuove tutte le righe della tabella
                    searchBarField.setText(""); //svuota il JTextField 'searchBarField'

                    if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {    //controlla se 'controller.libriTitoloPreferiti', 'controller.possessolPreferiti' e 'controller.librerieLibriPreferiti' non sono a null
                        for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {  //scorre la lista dei libri preferiti dell'utente
                            if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                                model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                            }else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) { //controlla se l'i-esimo libro non è disponibile
                                model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile" , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                            } else {
                                model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita, controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                            }
                        }
                    }
                }
            }
        });

        resetFiltriLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchBarField.setText(""); //svuota il JTextField 'searchBarField'
                groupRB.clearSelection();   //deseleziona tutti i JRadioButton del ButtonGroup 'groupRB'
                model.setRowCount(0);   //rimuove tutte le righe della tabella

                if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {    //controlla se 'controller.libriTitoloPreferiti', 'controller.possessolPreferiti' e 'controller.librerieLibriPreferiti' non sono a null
                    for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {  //scorre la lista dei libri preferiti dell'utente
                        if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                        }else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) { //controlla se l'i-esimo libro non è disponibile
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile" , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        } else {
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita, controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }

                if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {    //controlla se 'controller.serieTitoloPreferiti', 'controller.possessosPreferiti' e 'controller.librerieSeriePreferiti' non sono a null
                    for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {  //scorre la lista delle serie preferite dell'utente
                        if(controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")){   //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                        } else if(controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0){ //controlla se l'i-esima serie non è disponibile
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile" , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        } else {
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita, controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }

                if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {    //controlla se 'controller.fascicoliTitoloPreferiti', 'controller.possessofPreferiti' e 'controller.librerieFascicoliPreferiti' non sono a null
                    for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {  //scorre la lista dei fascicoli preferiti dallutente
                        if(controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                        } else if(controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0){ //controlla se l'i-esimo fascicolo non è disponibile
                            model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile" , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        } else {
                            model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita, controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }
            }
        });

        fascicoliRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selelzionato il JRadioButton 'fascicoliRB'
                    model.setRowCount(0);   //rimuove tutte le righe della tabella
                    searchBarField.setText("");     //svuota il JTextField 'searchBarField'

                    if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {    //controlla se 'controller.fascicoliTitoloPreferiti', 'controller.possessofPreferiti' e 'controller.librerieFascicoliPreferiti' non sono a null
                        for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {  //scorre la lista dei fascicoli preferiti dallutente
                            if(controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                                model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                            } else if(controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0) {    //controlla se l'i-esimo fascicolo non è disponibile
                                model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile" , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                            } else {
                                model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                            }
                        }
                    }
                }
            }
        });

        serieRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model.setRowCount(0);   //rimuove tutte le righe della tabella
                searchBarField.setText(""); //svuota il JTextField 'searchBarField'

                if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {    //controlla se 'controller.serieTitoloPreferiti', 'controller.possessosPreferiti' e 'controller.librerieSeriePreferiti' non sono a null
                    for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {  //scorre la lista delle serie preferite dell'utente
                        if(controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                        } else if(controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0){ //controlla se l'i-esimo fascicolo non è disponibile
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile" , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        } else{
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }
            }
        });

        searchImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                search(controller);  //esegue la ricerca
            }
        });

        searchImage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){   //controlla se è stato premuto il tasto "ENTER"
                    search(controller);  //esegue la ricerca
                }
            }
        });

        searchBarField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "ENTER"
                    search(controller);  //esegue la ricerca
                    e.consume();    //evita che il KeyEvent 'e' venga ulteriormente gestito
                }
            }
        });
    }

    private void search(Controller controller){    //esegue una ricerca nella tabella
        if (!searchBarField.getText().isBlank()) {  //controlla se è stato inserito un testo nel JTextField 'searchBarField'
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            if(searchBarField.getText().contains("'")) {    //controlla se è stato inserito il carattere ''' nella barra di ricerca
                searchBarField.setText(searchBarField.getText().replace("'", "’")); //sostutuisce il carattere ''' nella barra di ricerca con il carattere '’'
            }

            if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {    //controlla se 'controller.libriTitoloPreferiti', 'controller.possessolPreferiti' e 'controller.librerieLibriPreferiti' non sono a null
                for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {  //scorre la lista dei libri preferiti dell'utente
                    String quantita = String.valueOf(controller.possessolPreferiti.get(i).quantita);    //quantità disponibile dell'i-esimo libro

                    if(controller.libriTitoloPreferiti.get(i).toLowerCase().contains(searchBarField.getText().toLowerCase()) || quantita.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.possessolPreferiti.get(i).fruizione.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieLibriPreferiti.get(i).nome.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieLibriPreferiti.get(i).indirizzo != null && controller.librerieLibriPreferiti.get(i).indirizzo.toLowerCase().contains(searchBarField.getText().toLowerCase())) || controller.librerieLibriPreferiti.get(i).numeroTelefonico.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieLibriPreferiti.get(i).sitoWeb != null && controller.librerieLibriPreferiti.get(i).sitoWeb.toLowerCase().contains(searchBarField.getText().toLowerCase()))){    //controlla se una delle celle dell'i-esima riga della tabella contiene il contenuto della barra di ricerca
                        if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) {  //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb}); //aggiunge una nuova riga nella tabella
                        } else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) {    //controlla se l'i-esimo libro non è disponibile
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile", controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        }else {
                            model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita, controller.possessolPreferiti.get(i).fruizione, controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }
            }

            if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {    //controlla se 'controller.serieTitoloPreferiti', 'controller.possessosPreferiti' e 'controller.librerieSeriePreferiti' non sono a null
                for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {  //scorre la lista delle serie preferite dell'utente
                    String quantita = String.valueOf(controller.possessosPreferiti.get(i).quantita);    //quantità disponibile dell'i-esima serie

                    if (controller.serieTitoloPreferiti.get(i).toLowerCase().contains(searchBarField.getText().toLowerCase()) || quantita.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.possessosPreferiti.get(i).fruizione.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).nome.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).indirizzo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).numeroTelefonico.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieSeriePreferiti.get(i).sitoWeb != null && controller.librerieSeriePreferiti.get(i).sitoWeb.toLowerCase().contains(searchBarField.getText().toLowerCase()))){   //controlla se una delle celle dell'i-esima riga della tabella contiene il contenuto della barra di ricerca
                        if (controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")){  //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});    //aggiunge una nuova riga nella tabella
                        } else if (controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0) {   //controlla se l'i-esimo fascicolo non è disponibile
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile", controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
                        } else {
                            model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita, controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});  //aggiunge una nuova riga nella tabella
                        }
                    }
                }
            }

            if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {
                for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {
                    String quantita = String.valueOf(controller.possessofPreferiti.get(i).quantita);

                    if (controller.fascicoliTitoloPreferiti.get(i).toLowerCase().contains(searchBarField.getText().toLowerCase()) || quantita.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.possessofPreferiti.get(i).fruizione.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieFascicoliPreferiti.get(i).nome.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieFascicoliPreferiti.get(i).indirizzo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieFascicoliPreferiti.get(i).numeroTelefonico.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieFascicoliPreferiti.get(i).sitoWeb != null && controller.librerieFascicoliPreferiti.get(i).sitoWeb.toLowerCase().contains(searchBarField.getText().toLowerCase()))) {
                        if (controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                        else if (controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile", controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                        else model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita, controller.possessofPreferiti.get(i).fruizione, controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                    }
                }
            }

        } else {
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {
                for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {

                    if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                    else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile" , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                    else model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                }
            }

            if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {
                for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {

                    if(controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                    else if(controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile" , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                    else model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                }
            }

            if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {
                for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {

                    if(controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                    else if(controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile" , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                    else model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                }
            }
        }
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

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);

        searchBarField = new JTextField();
        searchBarField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));


        ImageIcon radioIco = new ImageIcon(this.getClass().getResource("/r2.png"));
        Image radioImg = radioIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8),Image.SCALE_SMOOTH);
        radioIco = new ImageIcon(radioImg);

        ImageIcon radioSelIco = new ImageIcon(this.getClass().getResource("/r1.png"));
        Image radioSelImg = radioSelIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8),Image.SCALE_SMOOTH);
        radioSelIco = new ImageIcon(radioSelImg);

        groupRB = new ButtonGroup();
        libriRB = new JRadioButton();
        libriRB.setIcon(radioIco);
        libriRB.setSelectedIcon(radioSelIco);
        serieRB = new JRadioButton();
        serieRB.setIcon(radioIco);
        serieRB.setSelectedIcon(radioSelIco);
        fascicoliRB = new JRadioButton();
        fascicoliRB.setIcon(radioIco);
        fascicoliRB.setSelectedIcon(radioSelIco);
        groupRB.add(libriRB);
        groupRB.add(serieRB);
        groupRB.add(fascicoliRB);

        ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/search.png"));
        Image searchImg = searchIcon.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(searchImg);
        searchImage = new JLabel(searchIcon);

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        notificaIco = new ImageIcon(notificaImg);

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);
    }
}