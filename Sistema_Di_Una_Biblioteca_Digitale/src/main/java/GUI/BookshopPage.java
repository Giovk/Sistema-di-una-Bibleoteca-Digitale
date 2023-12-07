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

public class BookshopPage {
    public JFrame frame;
    private JPanel bookshopPagePanel;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JButton serieButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JPanel elementiPanel;
    private JLabel librerieLabelText;
    private JLabel aggiungiLabel;
    private JScrollPane elementiScrollPanel;
    private JTable elementiTable;
    private JLabel backButton;
    private JPopupMenu utenteMenu;
    private JPopupMenu elementiMenu;
    private DefaultTableModel model;
    private Boolean active = false;
    private int numeroNotifiche;
    private int row_selected;

    public BookshopPage(JFrame frameC, Controller controller){
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

        notificheLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'notificheLabel'

        librerieLabelText.setFont(controller.impactFontSize);   //imposta il font della JLabel 'librerieLabelText'
        aggiungiLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'aggiungiLabel'

        elementiTable.setFont(controller.impactFontSize);   //imposta il font della JTable 'elementiTable'
        elementiTable.setRowMargin(controller.screenWidth/640); //imposta il margine tra le celle della JTable 'elementiTable'
        elementiTable.setRowHeight(controller.screenHeight/36); //imposta l'altezza delle righe della JTable 'elementiTable'

        elementiPanel.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4)))); //imposta la dimensione minima del JPanel 'elementiPanel'

        elementiScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);  //inizializza il colore della parte mobile della barra di scorrimento
                this.trackColor= new Color(0xFFD369);   //inizializza il colore della parte fissa della barra di scorrimento
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);    //inizializza il colore della parte più scura dell'ombra del lato inferiore della parte mobile della barra di scorrimento
                this.thumbLightShadowColor = new Color(0x323A48);   //inizializza il colore della parte piu chiara dell'ombra del lato superiore della parte mobile della barra di scorrimento
                this.thumbHighlightColor = new Color(0x323A48); //inizializza il colore della parte mobile della barra di scorrimento quando viene attivata
                this.trackHighlightColor = new Color(0xCF9E29); //inizializza il colore della parte fissa della barra di scorrimento quando viene attivata
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(25, 15);   //inizializza le dimensioni del JButton 'decreaseButton'
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
                        return new Dimension(25, 15);   //inizializza le dimensioni del JButton 'increaseButton'
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
        utenteExit.setMinimumSize((new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8))));
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setFont(controller.baseFontSize); //imposta il font del JMenuItem 'utenteProfilo'
        utenteProfilo.setHorizontalTextPosition(SwingConstants.CENTER); //centra orizzontalmente il testo del JMenuItem 'utenteProfilo'
        utenteProfilo.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusPainted(false);   //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteProfilo'
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusable(false);  //impedisce all'utente di interagire con il JMenuItem 'utenteProfilo' tramite tastiera
        utenteProfilo.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));    //imposta la dimensione minima del JMenuItem 'utenteExit'
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

        elementiMenu = new JPopupMenu();    //crea il menu 'elementiMenu'
        JMenuItem eliminaElemento = new JMenuItem("Elimina");   //crea la voce del menu "Elimina"
        eliminaElemento.setBackground(new Color(0xFFD369)); //imposta il colore dello sfondo del JMenuItem 'eliminaElemento'
        eliminaElemento.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JMenuItem 'eliminaElemento'
        eliminaElemento.setFont(controller.baseFontSize);   //imposta il font del JMenuItem 'eliminaElemento'
        JMenuItem modificaQuantita = new JMenuItem("Modifica quantità");    //crea la voce del menu "Elimina"
        modificaQuantita.setBackground(new Color(0xFFD369));    //imposta il colore dello sfondo del JMenuItem 'modificaQuantita'
        modificaQuantita.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JMenuItem 'modificaQuantita'
        modificaQuantita.setFont(controller.baseFontSize);  //imposta il font del JMenuItem 'modificaQuantita'
        elementiMenu.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JPoppUp 'elementiMenu'
        elementiMenu.setBackground(new Color(0xFFD369));    //imposta il colore dello sfondo del JPoppUp 'elementiMenu'
        elementiMenu.add(eliminaElemento);  //aggiunge la voce 'eliminaElemento' al menu 'elementiMenu.'
        elementiMenu.add(modificaQuantita); //aggiunge la voce 'modificaQuantita' al menu 'elementiMenu.'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(bookshopPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                System.exit(0);
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton utenteButton
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - (int) (controller.screenWidth/15.24), utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
            }
        });

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);    //rende visibile il frame chiamante 'frameC'
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
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }
        });

        utenteExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frameC.setVisible(true);    //rende visibile il frame chiamante 'frameC'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
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

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeButton.setBackground(Color.decode("#cf9e29"));  //imposta il colore dello sfondo del JButton 'homeButton'
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homeButton.setBackground(Color.decode("#FFD369"));  //imposta il colore dello sfondo del JButton 'homeButton'
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
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

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

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                BookshopsPage bsp = new BookshopsPage(frameC, controller);  //chiama il frame 'bsp'
                bsp.frame.setVisible(true); //rende visibile il frame chiamato 'bsp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        controller.getNotificheUtente();    //inizializza 'controller.notifiche'

        setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'
            }
        });

        timer.start();  //avvia il timer
        timer.setRepeats(true); //fa ripetere il timer dopo che è scaduto


        model = new DefaultTableModel(new Object[][]{}, new String[]{"Prodotto", "Quantità", "Fruizione"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();   //DefaultTableCellRenderer per la formattazione dell'header della tabella

        headerRenderer.setBackground(new Color(0xCF9E29));  //imposta il colore dello sfondo dell'header della tabella
        headerRenderer.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo dell'header della tabella
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);   //centra orizzontalmente il contenuto dell'header della tabella

        Font headerFont = new Font("Impact", Font.PLAIN, 15); //imposta il font Bebas Neue, grandezza 15 e stile Regular

        headerRenderer.setFont(headerFont); //inizializza il font Bebas Neue, grandezza 15 e stile Regular per i caratteri della tabella

        JTableHeader tableHeader = elementiTable.getTableHeader();  //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'elementiTable'

        tableHeader.setDefaultRenderer(headerRenderer); //imposta il render di default della tabella a 'headerRender'

        elementiTable.getTableHeader().setResizingAllowed(false);   //impedisce il ridimensionamento delle colonne

        elementiTable.getTableHeader().setReorderingAllowed(false); //impedisce il riordinamento delle colonne

        tableHeader.setBorder(null);    //rimuove il bordo dell'header della tabella

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));  //inposta il render di default della tabella

        controller.getPossessoLibreria();   //inizializza 'controller.possessoLLibreria', 'controller.possessoSLibreria' e 'controller.possessoFLibreria'

        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se 'controller.titoloLibriLibreria' e 'controller.possessoLLibreria' non sono A NULL
            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria selezionata
                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) {  //controlla se l'i-esimo libro non è disponibile
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile", controller.possessoLLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                }else{
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se 'controller.titoloSerieLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria selezionata
                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0){   //controlla se l'i-esima serie non è disponibile
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se 'controller.fascicoliLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria selezionata
                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) {    //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0){   //controlla se l'i-esimo fascicolo non è disponibile
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        elementiTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'

        elementiScrollPanel.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JScrollPane 'elementiScrollPanel'
        elementiScrollPanel.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JScrollPane 'elementiScrollPanel'
        elementiScrollPanel.getViewport().setBackground(new Color(0x222831));   //imposta il colore dello sfondo della parte visibile del JScrollPane 'elementiScrollPanel'

        elementiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            if(e.getButton() == MouseEvent.BUTTON3) {   //controlla se è stato cliccato con il tasto destro del mouse la JTable 'elementiTable'
                row_selected = elementiTable.rowAtPoint(e.getPoint());  //inizializza 'row_selected' con l'indice della riga nel punto cliccato
                elementiTable.setRowSelectionInterval(row_selected, row_selected);

                if(!controller.titoloSerieLibreria.contains(elementiTable.getValueAt(row_selected, 0))) {
                    if (elementiTable.getValueAt(row_selected, 2).toString().contains("Digitale") || elementiTable.getValueAt(row_selected, 2).toString().contains("AudioLibro")){
                        modificaQuantita.setVisible(false);
                        elementiMenu.show(elementiTable, e.getX(), e.getY());
                    } else {
                        modificaQuantita.setVisible(true);
                        elementiMenu.show(elementiTable, e.getX(), e.getY());
                    }
                }
            }
            }
        });

        eliminaElemento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comferma = 0;
                comferma = new NewComfirmMessageDialog().comfirmDialog("Vuoi davvero eliminare questa notifica?");

                if(comferma == 1) {
                    if (controller.titoloLibriLibreria.contains(elementiTable.getValueAt(elementiTable.getSelectedRow(), 0).toString())) {
                        controller.isbn_selected = elementiTable.getValueAt(elementiTable.getSelectedRow(), 0).toString().substring(0, 17);
                        controller.eliminaPossessoL(elementiTable.getValueAt(elementiTable.getSelectedRow(), 2).toString());
                    } else {
                        controller.nome_selected = elementiTable.getValueAt(elementiTable.getSelectedRow(), 0).toString();
                        int numero = Integer.valueOf(controller.nome_selected.substring(controller.nome_selected.indexOf("N°") + 2, controller.nome_selected.length()));
                        controller.selezionaFascicolo(numero, controller.nome_selected.substring(0, controller.nome_selected.indexOf("N°") - 1));
                        controller.eliminaPossessoF(elementiTable.getValueAt(elementiTable.getSelectedRow(), 2).toString());
                    }
                    model.removeRow(row_selected);
                    model.setRowCount(0);

                    controller.getPossessoLibreria();

                    if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {
                        for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {

                            if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});
                            else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});
                        }
                    }

                    if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {
                        for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {

                            if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});
                            else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});
                        }
                    }

                    if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) {
                        for (int i = 0; i < controller.fascicoliLibreria.size(); i++) {

                            if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});
                            else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});
                        }
                    }
                }
                elementiTable.clearSelection();
            }
        });

        modificaQuantita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(controller.titoloLibriLibreria.contains(elementiTable.getValueAt(row_selected, 0).toString())){
                        controller.isbn_selected = elementiTable.getValueAt(row_selected, 0).toString().substring(0, 17);

                        int valore = 0;
                        if(elementiTable.getValueAt(row_selected, 1).toString().equals("Non Diponibile")) valore = 0;
                        else valore = ((int) elementiTable.getValueAt(row_selected, 1));

                        ChangeQuantity cq = new ChangeQuantity(frame, controller, model, elementiTable.getValueAt(row_selected, 2).toString(), valore);
                        frame.setEnabled(false);
                    } else {
                        controller.nome_selected = elementiTable.getValueAt(row_selected, 0).toString();
                        int numero = Integer.valueOf(controller.nome_selected.substring(controller.nome_selected.indexOf("N°")+2, controller.nome_selected.length()));

                        int valore = 0;
                        if(elementiTable.getValueAt(row_selected, 1).toString().equals("Non Diponibile")) valore = 0;
                        else valore = ((int) elementiTable.getValueAt(row_selected, 1));

                        ChangeQuantity cq = new ChangeQuantity(frame, controller, model, elementiTable.getValueAt(row_selected, 2).toString(), numero ,valore);
                        frame.setEnabled(false);
                    }

                    model.setRowCount(0);

                    controller.getPossessoLibreria();

                    if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {
                        for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {

                            if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});
                            else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});
                        }
                    }

                    if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {
                        for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {

                            if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});
                            else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});
                        }
                    }

                    if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) {
                        for (int i = 0; i < controller.fascicoliLibreria.size(); i++) {

                            if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});
                            else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});
                            else model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});
                        }
                    }
            }
        });

        aggiungiLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AggiungiElementoForm aef = new AggiungiElementoForm(frame, controller, model);
                frame.setEnabled(false);
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

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);

        ImageIcon backIco = new ImageIcon(this.getClass().getResource("/back.png"));
        Image backImg = backIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        backIco = new ImageIcon(backImg);
        backButton = new JLabel(backIco);

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        notificaIco = new ImageIcon(notificaImg);

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);
    }
}
