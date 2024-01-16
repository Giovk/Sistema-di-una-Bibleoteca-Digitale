package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;

public class SeriesPage {
    public JFrame frame;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JPanel jpanel2;
    private JRadioButton genereRB;
    private JRadioButton autoreRB;
    private JLabel resetFiltriLabel;
    private JScrollPane seriesScrollPanel;
    private JTable seriesTable;
    private JTextField searchBarField;
    private JLabel searchImage;
    private JButton serieButton;
    private JPanel jpanel;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JPanel panel2;
    private JPanel generePanel;
    private JPanel autorePanel;
    private JPopupMenu utenteMenu;
    private ButtonGroup groupRB;
    private DefaultTableModel model;
    private Boolean active = false;
    private int numeroNotifiche;

    public SeriesPage(JFrame frameC, Controller controller){
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29)); //imposta il colore dello sfondo di un elemento di menu quando viene selezionato
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831)); //imposta il colore del testo di un elemento di menu quando viene selezionato
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

        searchBarField.setFont(controller.textFieldFont);   //imposta il font della JTextField 'searchBarField'

        genereRB.setFont(controller.impactFontSize);    //imposta il font del JRadioButton 'genereRB'
        autoreRB.setFont(controller.impactFontSize);    //imposta il font del JRadioButton 'autoreRB'
        resetFiltriLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'resetFiltriLabel'

        seriesTable.setFont(controller.impactFontSize); //imposta il font della JTable 'seriesTable'
        seriesTable.setRowMargin(controller.screenWidth/640);   //imposta il margine tra le celle della JTable 'seriesTable'
        seriesTable.setRowHeight(controller.screenHeight/36);   //imposta l'altezza delle righe della JTable 'seriesTable'

        panel2.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4))));    //imposta la dimensione minima del JPanel 'panel2'

        seriesScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        if (controller.utente.getPartitaIVA() == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));    //adatta le dimensioni minime di 'utenteMenu'
        }

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false); //evita che l'utente modifichi le dimensioni del frame
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


        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));  //imposta il colore del testo di un combo box quando viene disabilitato
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));  //imposta il colore dello sfondo di un combo box quando viene disabilitato

        Dimension dim = new Dimension((int) (controller.screenWidth/6.5), controller.screenHeight/24);  //dimensione preferita dei combo box

        NewComboBox autoreCB = new NewComboBox<>();
        autoreCB.setPreferredSize(dim); //imposta a 'dim' la dimensione preferita del combo box 'autoreCB'
        autoreCB.setFont(controller.impactFontSize);    //imposta il font del combo box 'autoreCB'
        autoreCB.setFocusable(false);   //impedisce all'utente di interagire con il combo box 'autoreCB' tramite tastiera
        autoreCB.setEditable(false);    //impedisce all'utente di inserire il testo nel combo box 'autoreCB'
        autorePanel.add(autoreCB);  //inserisce 'autoreCB' nel JPanel 'autorePanel'

        NewComboBox genereCB = new NewComboBox<>();
        genereCB.setPreferredSize(dim); //imposta a 'dim' la dimensione preferita del combo box 'genereCB'
        genereCB.setFont(controller.impactFontSize);    //imposta il font del combo box 'genereCB'
        genereCB.setFocusable(false);   //impedisce all'utente di interagire con il combo box 'genereCB' tramite tastiera
        genereCB.setEditable(false);    //impedisce all'utente di inserire il testo nel combo box 'genereCB'
        generePanel.add(genereCB);  //inserisce 'autoreCB' nel JPanel 'generePanel'


        genereCB.setEnabled(false); //disabilita il JComboBox 'genereCB'

        genereCB.setModel(new DefaultComboBoxModel<String>(controller.getSerieGeneri().toArray(new String[controller.getSerieGeneri().size()])));   //inserisce tutti gli elementi della lista dei generi delle serie come voci del JComboBox 'genereCB'
        genereCB.setSelectedIndex(-1);  //permette di avere 'genereCB' non selezionato


        autoreCB.setEnabled(false); //disabilita il JComboBox 'autoreCB'

        autoreCB.setModel(new DefaultComboBoxModel<String>(controller.getSerieAutori().toArray(new String[controller.getSerieAutori().size()])));   //inserisce tutti gli elementi della lista degli autori delle serie come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato


        model = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "N. Libri", "Data di pubblicazione"}) {
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

        JTableHeader tableHeader = seriesTable.getTableHeader();    //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'seriesTable'

        tableHeader.setDefaultRenderer(headerRenderer); //imposta il render di default della tabella a 'headerRender'

        seriesTable.getTableHeader().setResizingAllowed(false); //impedisce il ridimensionamento delle colonne

        seriesTable.getTableHeader().setReorderingAllowed(false);   //impedisce il ridimensionamento delle colonne

        tableHeader.setBorder(null);    //rimuove il bordo dell'header della tabella

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));  //imposta il render di default della tabella


        seriesTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'
        seriesScrollPanel.setBackground(new Color(0x222831));   //imposta il colore dello sfondo del JScrollPane 'seriesScrollPanel'
        seriesScrollPanel.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JScrollPane 'seriesScrollPanel'
        seriesScrollPanel.getViewport().setBackground(new Color(0x222831)); //imposta il colore dello sfondo della parte visibile del JScrollPane 'seriesScrollPanel'


        genereRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'genereRB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
                } else if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'genereRB'
                    searchBarField.setText(""); //svuota il testo del JTextField 'searchBarField'
                    genereCB.setEnabled(true);  //abilita 'genereCB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                }
            }
        });

        autoreRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'collanaRB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                } else if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'autoreRB'
                    searchBarField.setText(""); //svuota il testo del JTextField 'searchBarField'
                    autoreCB.setEnabled(true);  //abilita 'autoreCB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
                }
            }
        });

        seriesTable.setModel(model);    //imposta il modello dei dati della JTable 'seriesTable'

        if(controller.listaSerie != null){  //controlla se 'controller.listaLibri' non è vuota
            for (int i = 0; i < controller.listaSerie.size(); i++){ //scorre 'controller.listaLibri'
                model.addRow(new Object[]{controller.listaSerie.get(i).getISBN(), controller.listaSerie.get(i).getTitolo(), controller.listaSerie.get(i).getNLibri(), controller.listaSerie.get(i).getDataPubblicazione()});    //aggiunge una nuova riga nella tabella
            }
        }

        seriesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = seriesTable.getValueAt(seriesTable.getSelectedRow(), 0).toString();  //inizializza 'controller.isbn_selected' con l'ISBN della serie selezionata
                controller.nome_selected = seriesTable.getValueAt(seriesTable.getSelectedRow(), 1).toString();  //inizializza 'controller.nome_selected' con il nome della serie selezionata

                SeriePage sp = new SeriePage(frameC, controller); //chiama il frame 'sp'
                sp.frame.setVisible(true);  //rende visible il frame 'sp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        searchBarField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){   //controlla se è stato premuto il tasto "ENTER"
                    search(controller); //esegue la ricerca
                    e.consume();    //evita che il KeyEvent 'e' venga ulteriormente gestito
                }
            }
        });

        searchImage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){   //controlla se è stato premuto il tasto "ENTER"
                    search(controller); //esegue la ricerca
                }

                e.consume();    //evita che il KeyEvent 'e' venga ulteriormente gestito
            }
        });

        searchImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                search(controller); //esegue la ricerca
            }
        });


        genereCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genereCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella
                    controller.getListaSerieGenere(genereCB.getSelectedItem().toString());  //inizializza 'listaSerieGenere' con tutte le serie con libri del genere selezionato

                    for (int i = 0; i < controller.listaSerieGenere.size(); i++){   //scorre 'controller.listaSerieGenere'
                        model.addRow(new Object[]{controller.listaSerieGenere.get(i).getISBN(), controller.listaSerieGenere.get(i).getTitolo(), controller.listaSerieGenere.get(i).getNLibri(), controller.listaSerieGenere.get(i).getDataPubblicazione()});     //aggiunge una nuova riga nella tabella
                    }
                }
            }
        });

        autoreCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoreCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella
                    controller.getListaSerieAutore(autoreCB.getSelectedItem().toString());  //inizializza 'listaSerieAutore' con tutte le serie con libri dell'autore selezionato

                    for (int i = 0; i < controller.listaSerieAutore.size(); i++){   //scorre 'controller.listaSerieAutore'
                        model.addRow(new Object[]{controller.listaSerieAutore.get(i).getISBN(), controller.listaSerieAutore.get(i).getTitolo(), controller.listaSerieAutore.get(i).getNLibri(), controller.listaSerieAutore.get(i).getDataPubblicazione()});    //aggiunge una nuova riga nella tabella
                    }
                }
            }
        });

        resetFiltriLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchBarField.setText(""); //svuota il testo del JTextField 'searchBarField'
                groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
                model.setRowCount(0);   //elimina tutte le righe della teblla

                for(int i = 0; i < controller.listaSerie.size(); i++){  //scorre 'controller.listaSerie'
                    model.addRow(new Object[]{controller.listaSerie.get(i).getISBN(), controller.listaSerie.get(i).getTitolo(), controller.listaSerie.get(i).getNLibri(), controller.listaSerie.get(i).getDataPubblicazione()});    //aggiunge una nuova riga nella tabella
                }
            }
        });

        numeroNotifiche = controller.getNumeroNotificheNonLette();  //inizializza 'numeroNotifiche' con il numero di notifiche dell'utente non lette

        setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'
            }
        });

        timer.start();  //avvia il timer
        timer.setRepeats(true); //fa ripetere il timer dopo che è scaduto
    }

    private void setNumeroNotifiche(Controller controller){ //aggiorna il testo della JLabel 'notificheLabel' con in base al numero di notifiche dell'utente non lette
        numeroNotifiche = controller.getNumeroNotificheNonLette();  //inizializza 'numeroNotifiche' con il numero di notifiche dell'utente non lette

        if (numeroNotifiche <= 0){  //controlla se non ci sono notifiche non lette
            notificheLabel.setVisible(false);   //rende invisibile la JLabel 'notificheLabel'
        }else if (numeroNotifiche >= 100) { //controlla se ci sono almeno 100 notifiche non lette
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'
            notificheLabel.setText("99+");  //imposta il testo della JLabel 'notificheLabel'
        }else{
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'

            String numeroNotificheText = Integer.toString(numeroNotifiche); //inizializza numeroNotificheText con 'numeroNotifiche'

            notificheLabel.setText(numeroNotificheText);    //imposta il testo della JLabel 'notificheLabel' con 'numeroNotificheText'
        }
    }//fine setNumeroNotifiche

    public void search(Controller controller){  //esegue una ricerca nella tabella
        if (searchBarField.getText().isBlank()) {  //controlla se non è stato inserito un testo nel JTextField 'searchBarField'
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for(int i = 0; i < controller.listaSerie.size(); i++){  //scorre la lista dei libri 'controller.listaSerie'
                model.addRow(new Object[]{controller.listaSerie.get(i).getISBN(), controller.listaSerie.get(i).getTitolo(), controller.listaSerie.get(i).getNLibri(), controller.listaSerie.get(i).getDataPubblicazione()});    //aggiunge una nuova riga nella tabella
            }
        } else {
            if(searchBarField.getText().contains("'")){ //controlla se il tetsto in 'searchBarField' contiene il carattere "'"
                searchBarField.setText(searchBarField.getText().replace("'", "’")); //sostituisce i "'" in 'searchBarField' con "'"
            }

            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for (int i = 0; i < controller.listaSerie.size(); i++) {    //scorre la lista dei libri 'listaLibri'
                if (controller.listaSerie.get(i).getISBN().toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaSerie.get(i).getTitolo().toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaSerie.get(i).getDataPubblicazione().toString().toLowerCase().contains(searchBarField.getText().toLowerCase())) { //controlla se l'ISBN, il titolo, o la data di pubblicazione contiene il testo scritto in 'searchBarField'
                    model.addRow(new Object[]{controller.listaSerie.get(i).getISBN(), controller.listaSerie.get(i).getTitolo(), controller.listaSerie.get(i).getNLibri(), controller.listaSerie.get(i).getDataPubblicazione()});    //aggiunge una nuova riga nella tabella
                }
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        searchBarField = new JTextField();  //inizializza il JTextField 'searchBarField'
        searchBarField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'searchBarField'

        ImageIcon radioIco = new ImageIcon(this.getClass().getResource("/r2.png")); //carica l'immagine nel percorso /r2.png
        Image radioImg = radioIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8),Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        radioIco = new ImageIcon(radioImg); //reinizializza l'ImageIcon 'radioIco' con l'Image 'radioImg'

        ImageIcon radioSelIco = new ImageIcon(this.getClass().getResource("/r1.png"));  //carica l'immagine nel percorso /r2.png
        Image radioSelImg = radioSelIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8),Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

        radioSelIco = new ImageIcon(radioSelImg);   //reinizializza l'ImageIcon 'radioSelIco' con l'Image 'radioSelImg'

        groupRB = new ButtonGroup();    //inizializza il ButtonGroup 'groupRB'
        genereRB = new JRadioButton();  //inizializza il JRadioButton 'genereRB'
        genereRB.setIcon(radioIco); //imposta l'icona della 'genereRB' con l'immagine 'radioIco'
        genereRB.setSelectedIcon(radioSelIco);  //imposta l'icona dI 'genereRB' quando una suo bottone viene selzionato con l'immagine 'radioSelIco'
        autoreRB = new JRadioButton();  //inizializza il JRadioButton 'autoreRB'
        autoreRB.setIcon(radioIco); //imposta l'icona della 'autoreRB' con l'immagine 'radioIco'
        autoreRB.setSelectedIcon(radioSelIco);  //imposta l'icona di 'autoreRB' quando una suo bottone viene selzionato con l'immagine 'radioSelIco'
        groupRB.add(genereRB);  //aggiunge 'genereRB' in 'gruopRB'
        groupRB.add(autoreRB);  //aggiunge 'autoreRB' in 'gruopRB'

        ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/search.png"));   //carica l'immagine nel percorso /search.png
        Image searchImg = searchIcon.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        searchIcon = new ImageIcon(searchImg);  //reinizializza l'ImageIcon 'searchIcon' con l'Image 'searchImg'
        searchImage = new JLabel(searchIcon);   //inizializza la JLabel 'searchImage' con l'ImageIcon 'searchImage'

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));    //carica l'immagine nel percorso /notifica.png
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

        notificaIco = new ImageIcon(notificaImg);   //reinizializza l'ImageIcon 'notificaIco' con l'Image 'notificaImg'

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);    //imposta l'icona della JLabel 'notificheLabel' con l'immagine 'notificaIco'
    }
}