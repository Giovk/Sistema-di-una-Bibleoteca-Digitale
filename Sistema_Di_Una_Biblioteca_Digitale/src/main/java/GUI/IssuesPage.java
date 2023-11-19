package GUI;

import Controller.Controller;
import Model.Fascicolo;

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
import java.util.ArrayList;

public class IssuesPage {
    public JFrame frame;
    private JButton homeButton;
    private JRadioButton rivistaRB;
    private JRadioButton argomentoRB;
    private ButtonGroup groupRB;
    private JTable issuesTable;
    private JTextField searchBarField;
    private JLabel searchImage;
    private JScrollPane issuesScrollPanel;
    private JPanel jpanel2;
    private JPanel jpanel;
    private JLabel resetFiltriLabel;
    private JPanel buttonPanel;
    private JLabel closeBT;
    private JButton libriButton;
    private JButton utenteButton;
    private JButton serieButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JPanel panel2;
    private JPanel rivistaPanel;
    private JPanel argomentoPanel;
    private String linkString = "";
    private int aut;
    private Boolean active = false;
    private DefaultTableModel model;
    private int numeroNotifiche;

    public IssuesPage(JFrame frameC, Controller controller) {
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

        notificheLabel.setFont(controller.baseFontSize);

        searchBarField.setFont(controller.textFieldFont);

        rivistaRB.setFont(controller.impactFontSize);

        argomentoRB.setFont(controller.impactFontSize);

        resetFiltriLabel.setFont(controller.impactFontSize);

        issuesTable.setFont(controller.impactFontSize);
        issuesTable.setRowMargin(controller.screenWidth/640);
        issuesTable.setRowHeight(controller.screenHeight/36);

        panel2.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4))));

        issuesScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        JPopupMenu utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
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

        frame = new JFrame("Biblioteca Digitale");
        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
        frame.setIconImage(icona);
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

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


        // -------------------------------------------------------------------------------------- //
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

        // --------------------------------------------------------------------------- //
        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));

        Dimension dim = new Dimension((int) (controller.screenWidth/6.5), controller.screenHeight/24);

        NewComboBox rivistaCB = new NewComboBox<>();
        rivistaCB.setPreferredSize(dim);
        rivistaCB.setFont(controller.impactFontSize);
        rivistaCB.setFocusable(false);
        rivistaCB.setEditable(false);
        rivistaPanel.add(rivistaCB);

        NewComboBox argomentoCB = new NewComboBox<>();
        argomentoCB.setPreferredSize(dim);
        argomentoCB.setFont(controller.impactFontSize);
        argomentoCB.setFocusable(false);
        argomentoCB.setEditable(false);
        argomentoPanel.add(argomentoCB);


        rivistaCB.setEnabled(false); //disabilita il JComboBox 'rivistaCB'
        argomentoCB.setEnabled(false); //disabilita il JComboBox 'autoreCB'

        ArrayList<String> distinctRivisteList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati
        for (int i = 0; i < controller.listaRiviste.size(); i++){
            distinctRivisteList.add(controller.listaRiviste.get(i).titolo);
        }


        rivistaCB.setModel(new DefaultComboBoxModel<String>(distinctRivisteList.toArray(new String[distinctRivisteList.size()]))); //inserisce tutti gli elementi di 'distinctGenereList' come voci del JComboBox 'genereCB'
        rivistaCB.setSelectedIndex(-1);  //permette di avere 'rivistaCB' non selezionato
        ArrayList<String> totAutoreList = new ArrayList<String>(); //contiene i nomi e cognomi concatenati di tutti gli autori dei libri

        ArrayList<String> distinctArgomentiList = new ArrayList<>();   //contiene i nomi e i cognomi concatenati di tutti gli autori dei libri

        for (int i = 0; i < controller.listaRiviste.size(); i++) {    //scorre l'ArrayList di tutti i generi dei libri
            if (!distinctArgomentiList.contains(controller.listaRiviste.get(i).argomento))    //controlla se 'distinctGenereList' non contiene l'i-esimo elemento di genereList
                distinctArgomentiList.add(controller.listaRiviste.get(i).argomento);  //inserisce l'i-esimo elemento di genereList  in 'distinctGenereList'
        }

        argomentoCB.setModel(new DefaultComboBoxModel<String>(distinctArgomentiList.toArray(new String[distinctArgomentiList.size()]))); //inserisce tutti gli elementi di 'distinctAutoreList' come voci del JComboBox 'autoreCB'
        argomentoCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Titolo Rivista", "Numero", "Data Di Pubblicazione"}) {
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
        JTableHeader tableHeader = issuesTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        issuesTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        issuesTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));


        issuesTable.setModel(model); //imposta il modello dei dati della JTable 'issuesTable'
        issuesScrollPanel.setBackground(new Color(0x222831));
        issuesScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        issuesScrollPanel.getViewport().setBackground(new Color(0x222831));

        if (controller.listaFascicoli != null) {
            for (int i = 0; i < controller.listaFascicoli.size(); i++) {
                model.addRow(new Object[]{controller.listaFascicoli.get(i).rivista.titolo ,controller.listaFascicoli.get(i).numero, controller.listaFascicoli.get(i).dataPubblicazione});
            }
        }

        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        issuesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.selezionaFascicolo(((int) issuesTable.getValueAt(issuesTable.getSelectedRow(), 1)), issuesTable.getValueAt(issuesTable.getSelectedRow(), 0).toString());
                IssuePage ip = new IssuePage(frameC, controller); //chiama il frame 'bp'
                ip.frame.setVisible(true);  //rende visible il frame 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        rivistaRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'rivistaRB'
                    searchBarField.setText("");
                    rivistaCB.setEnabled(true);  //abilita 'genereCB'
                    argomentoCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    argomentoCB.setEnabled(false); //disabilita 'autoreCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'rivistaRB'
                    rivistaCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    rivistaCB.setEnabled(false); //disabilita 'genereCB'
                }
            }
        });

        resetFiltriLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchBarField.setText("");
                groupRB.clearSelection();   //deseleziona tutti i JRadioButton del ButtonGroup 'groupRB'
                model.setRowCount(0);
                for (int i = 0; i < controller.listaFascicoli.size(); i++) model.addRow(new Object[]{controller.listaFascicoli.get(i).rivista.titolo ,controller.listaFascicoli.get(i).numero, controller.listaFascicoli.get(i).dataPubblicazione});
            }
        });

        argomentoRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'autoreRB'
                    searchBarField.setText("");
                    argomentoCB.setEnabled(true);  //abilita 'autoreCB'
                    rivistaCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    rivistaCB.setEnabled(false); //disabilita 'genereCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'collanaRB'
                    argomentoCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    argomentoCB.setEnabled(false); //disabilita 'autoreCB'
                }
            }
        });

        rivistaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rivistaCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella

                    for (Fascicolo f : controller.listaFascicoli) {    //scorre la lista dei libri 'listaLibri'

                        if (rivistaCB.getSelectedItem() != null && rivistaCB.getSelectedItem().equals(f.rivista.titolo)) {    //controlla se l'elemento selezionato di 'genereCB' è uguale al genere del libro 'l'
                            model.addRow(new Object[]{f.rivista.titolo, f.numero, f.dataPubblicazione});
                        }
                    }
                }
            }
        });

        argomentoCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (argomentoCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella

                    for (Fascicolo f : controller.listaFascicoli) {    //scorre la lista dei libri 'listaLibri'

                        if (argomentoCB.getSelectedItem() != null && argomentoCB.getSelectedItem().equals(f.rivista.argomento)) {    //controlla se l'elemento selezionato di 'genereCB' è uguale al genere del libro 'l'
                            model.addRow(new Object[]{f.rivista.titolo, f.numero, f.dataPubblicazione});
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

        int numeroNotifiche = controller.getNumeroNotificheNonLette();

        numeroNotifiche = controller.getNumeroNotificheNonLette();

        setNumeroNotifiche(controller);

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller);
            }
        });

        timer.start();
        timer.setRepeats(true);
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

    private void search(Controller controller){    //esegue una ricerca nella tabella
        if (!searchBarField.getText().isBlank()) {  //controlla se è stato inserito un testo nel JTextField 'searchBarField'
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for (Fascicolo f : controller.listaFascicoli) {    //scorre la lista dei libri 'listaLibri'

                String numero = String.valueOf(f.numero);

                if (f.rivista.titolo.toLowerCase().contains(searchBarField.getText().replace("'", "’").toLowerCase()) || f.rivista.argomento.toLowerCase().contains(searchBarField.getText().replace("'", "’").toLowerCase()) || f.dataPubblicazione.toString().toLowerCase().contains(searchBarField.getText().replace("'", "’").toLowerCase()) || numero.toLowerCase().contains(searchBarField.getText().replace("'", "’").toLowerCase())) //controlla se l'isbn, il titolo, gli autori, il genere, la lingua, l'editore o la data di pubblicazione contiene il testo scritto in 'searchBarField'
                    model.addRow(new Object[]{f.rivista.titolo, f.numero, f.dataPubblicazione});
            }
        } else {
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for (int i = 0; i < controller.listaFascicoli.size(); i++) model.addRow(new Object[]{controller.listaFascicoli.get(i).rivista.titolo ,controller.listaFascicoli.get(i).numero, controller.listaFascicoli.get(i).dataPubblicazione});
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
        rivistaRB = new JRadioButton();
        rivistaRB.setIcon(radioIco);
        rivistaRB.setSelectedIcon(radioSelIco);

        argomentoRB = new JRadioButton();
        argomentoRB.setIcon(radioIco);
        argomentoRB.setSelectedIcon(radioSelIco);

        groupRB.add(rivistaRB);
        groupRB.add(argomentoRB);

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

