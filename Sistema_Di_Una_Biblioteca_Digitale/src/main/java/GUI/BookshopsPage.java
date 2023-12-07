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

public class BookshopsPage {
    public JFrame frame;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JButton serieButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JPanel bookshopsPagePanel;
    private JLabel librerieLabelText;
    private JLabel aggiungiLabel;
    private JScrollPane librerieScrollPanel;
    private JTable librerieTable;
    private JPanel libreriePanel;
    private JPopupMenu utenteMenu;
    private JPopupMenu libreriaMenu;
    private DefaultTableModel model;
    private Boolean active = false;
    private int numeroNotifiche;
    private int row_selected;

    public BookshopsPage(JFrame frameC, Controller controller){
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

        librerieTable.setFont(controller.impactFontSize);   //imposta il font della JTable 'librerieTable'
        librerieTable.setRowMargin(controller.screenWidth/640); //imposta il margine tra le celle della JTable 'librerieTable'
        librerieTable.setRowHeight(controller.screenHeight/36); //imposta l'altezza delle righe della JTable 'librerieTable'

        libreriePanel.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4)))); //imposta la dimensione minima del JPanel 'elementiPanel'

        librerieScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);  //inizializza il colore della parte mobile della barra di scorrimento
                this.trackColor= new Color(0xFFD369);   //inizializza il colore della parte fissa della barra di scorrimento
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);    //inizializza il colore della parte più scura dell'ombra del lato inferiore della parte mobile della barra di scorrimento
                this.thumbLightShadowColor = new Color(0x323A48);   //inizializza il colore della parte piu chiara dell'ombra del lato superiore della parte mobile della barra di scorrimento
                this.thumbHighlightColor = new Color(0x323A48); //inizializza il colore della parte mobile della barra di scorrimento quando viene attivata
                this.trackHighlightColor = new Color(0xCF9E29); //inizializza il colore della parte fissa della barra di scorrimento quando viene attivata
                this.scrollBarWidth = (int)(controller.screenWidth/75); //imposta la larghezza della barra di scorrimento
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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
        JMenuItem utenteExit = new JMenuItem("Logout"); //crea la voce del menu "Logout"
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
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteExit' al menu 'utenteMenu'

        libreriaMenu = new JPopupMenu();    //crea il menu 'libreriaMenu'
        JMenuItem eliminaLibreria = new JMenuItem("Elimina libreria");  //crea la voce del menu "Elimina libreria"
        eliminaLibreria.setBackground(new Color(0xFFD369)); //imposta il colore dello sfondo del JMenuItem 'eliminaLibrerie'
        eliminaLibreria.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JMenuItem 'eliminaLibrerie'
        eliminaLibreria.setFont(controller.baseFontSize);   //imposta il font del JMenuItem 'eliminaLibrerie'
        libreriaMenu.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del menu 'libreriaMenu'
        libreriaMenu.setBackground(new Color(0xFFD369));    //imposta il colore dello sfondo del menu 'libreriaMenu'
        libreriaMenu.add(eliminaLibreria);  //aggiunge la voce 'eliminaLibreria' al menu 'libreriaMenu'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(bookshopsPagePanel);
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
                frameC.setEnabled(true);    //rende visibile il frame chiamante 'frameC'
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

        utenteExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
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

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
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
                frameC.setVisible(true); //rende visibile il frame chiamante 'frameC'
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


        model = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Numero Telefonico", "SitoWeb", "Indirizzo"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();   //DefaultTableCellRenderer per la formattazione dell'header della tabella

        headerRenderer.setBackground(new Color(0xCF9E29));  //imposta il colore dello sfondo dell'header della tabella
        headerRenderer.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo dell'header della tabella
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);   //centra orizzontalmente il contenuto dell'header della tabella

        Font headerFont = new Font("Impact", Font.PLAIN, 15); // imposta il font Bebas Neue, grandezza 15 e stile Regular

        headerRenderer.setFont(headerFont); //inizializza il font Bebas Neue, grandezza 15 e stile Regular per i caratteri della tabella

        JTableHeader tableHeader = librerieTable.getTableHeader();  //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'librerieTable'

        tableHeader.setDefaultRenderer(headerRenderer); //imposta il render di default della tabella a 'headerRender'

        librerieTable.getTableHeader().setResizingAllowed(false);   //impedisce il ridimensionamento delle colonne

        librerieTable.getTableHeader().setReorderingAllowed(false); //impedisce il riordinamento delle colonne

        tableHeader.setBorder(null);    //rimuove il bordo dell'header della tabella

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));  //inposta il render di default della tabella

        controller.getLibrerieUtente(); //inizializza 'controller.librerieUtente' con tutte le librerie dell'utente

        if (controller.librerieUtente != null) {    //controlla se l'utente ha delle librerie
            for (int i = 0; i < controller.librerieUtente.size(); i++) {     //scorre 'controller.librerieUtente'
                model.addRow(new Object[]{controller.librerieUtente.get(i).nome, controller.librerieUtente.get(i).numeroTelefonico, controller.librerieUtente.get(i).sitoWeb, controller.librerieUtente.get(i).indirizzo, controller.librerieUtente.get(i).sitoWeb});   //aggiunge una nuova riga nella tabella
            }
        }

        librerieTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'

        librerieScrollPanel.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JScrollPane 'librerieScrollPanel'
        librerieScrollPanel.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JScrollPane 'librerieScrollPanel'
        librerieScrollPanel.getViewport().setBackground(new Color(0x222831));   //imposta il colore dello sfondo della parte visibile del JScrollPane 'librerieScrollPanel'

        librerieTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(e.getButton() == MouseEvent.BUTTON3){    //controlla se è stato cliccato con il tasto destro del mouse la JTable 'librerieTable'
                    row_selected = librerieTable.rowAtPoint(e.getPoint());  //inizializza 'row_selected' con l'indice della riga nel punto cliccato
                    librerieTable.setRowSelectionInterval(row_selected, row_selected);  //seleziona la riga con indice 'row_selected'
                    libreriaMenu.show(librerieTable, e.getX(), e.getY());   //mostra il JPoppupMenu 'libreriaMenu'
                } else if (librerieTable.isColumnSelected(2)) { //controlla se è stata selezionata la 4 colonna dalla JTable 'table1'
                    try {
                        Desktop.getDesktop().browse(new URI(librerieTable.getValueAt(librerieTable.getSelectedRow(), 2).toString()));   //inserisce un link nella cella (librerieTable.getSelectedRow(), 2)
                    }catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }

                    librerieTable.clearSelection(); //deseleziona tutte le celle selezionate in 'librerieTable'
                } else {
                    controller.libreria_selected = controller.librerieUtente.get(librerieTable.getSelectedRow());   //inizializza 'controller.libreria_selected' la libreria selezionata
                    librerieTable.clearSelection();

                    BookshopPage bsp = new BookshopPage(frameC, controller);   //chiama il frame 'bsp'
                    bsp.frame.setVisible(true);  //rende visibile il frame chiamato 'bsp'
                    frame.setVisible(false);    //rende invisibile il frame
                    frame.dispose();
                }
            }
        });

        aggiungiLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreaLibreria cl = new CreaLibreria(frame, controller, model);   //chiama il JDialog (CreaLibreria) 'cl'
                frame.setEnabled(false);    //rende invisibile il frame
            }
        });


        eliminaLibreria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comferma = 0;   //flag di conferma dell'utente

                comferma = new NewComfirmMessageDialog().comfirmDialog("Vuoi davvero eliminare questa libreria?");  //mostra un messaggio di conferma e aggiorna 'conferma'

                if(comferma == 1){  //controlla la conferma dell'utente
                    controller.removeLibreria(row_selected);    //elimina la libreria selezionata
                    model.removeRow(row_selected);  //rimuove la riga selezionata dalla tabella
                }
            }
        });
    }

    private void setNumeroNotifiche(Controller controller){ //aggiorna il testo della JLabel 'notificheLabel' con in base al numero di notifiche dell'utente non lette
        numeroNotifiche = controller.getNumeroNotificheNonLette();  //inizializza 'numeroNotifiche' con il numero di notifiche dell'utente non lette

        if (numeroNotifiche >= 100){    //controlla se ci sono almeno 100 notifiche non lette
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'
            notificheLabel.setText("99+");  //imposta il testo della JLabel 'notificheLabel'
        }else if (numeroNotifiche <= 0) {   //controlla se non ci sono notifiche non lette
            notificheLabel.setVisible(false);   //rende invisibile la JLabel 'notificheLabel'
        }else{
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'

            String numeroNotificheText = Integer.toString(numeroNotifiche); //inizializza numeroNotificheText con 'numeroNotifiche'

            notificheLabel.setText(numeroNotificheText);    //imposta il testo della JLabel 'notificheLabel' con 'numeroNotificheText'
        }
    }//fine setNumeroNotifiche

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));    //carica l'immagine nel percorso /notifica.png
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

        notificaIco = new ImageIcon(notificaImg);   //reinizializza l'ImageIcon 'notificaIco' con l'Image 'notificaImg'

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);    //imposta l'icona della JLabel 'notificheLabel' con l'immagine 'notificaIco'
    }
}