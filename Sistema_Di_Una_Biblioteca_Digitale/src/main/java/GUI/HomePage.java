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
    private JLabel text1;
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

    private JPopupMenu utenteMenu;
    private Boolean active = false;
    private DefaultTableModel model;
    private int numeroNotifiche;

    public HomePage(JFrame frameC, Controller controller) {
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29));
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831));
        UIManager.put("ScrollPane.background\n", new Color(0x222831));
        mainScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(25, 15);
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
                        return new Dimension(25, 15);
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
        utenteExit.setBackground(new Color(0xFFD369));
        utenteExit.setFocusPainted(false);
        utenteExit.setBorder(BorderFactory.createEmptyBorder());
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setBackground(new Color(0xFFD369));
        utenteProfilo.setFocusPainted(false);
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder());
        utenteProfilo.setFocusable(false);
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteLibrerie.setBackground(new Color(0xFFD369));
        utenteLibrerie.setFocusPainted(false);
        utenteLibrerie.setBorder(BorderFactory.createEmptyBorder());
        utenteMenu.setPopupSize(new Dimension(80, 75));
        utenteMenu.setBorder(BorderFactory.createEmptyBorder());
        utenteMenu.setBackground(new Color(0xFFD369));
        utenteMenu.add(utenteProfilo);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension(80, 50));
        }

        frame = new JFrame("Homepage");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(homepagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                System.exit(0);
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - 80, utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
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

        utenteProfilo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profilo pf = new Profilo(frameC, controller); //chiama il frame 'pf'
                pf.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true); //rende visibile il frame chiamante
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteLibrerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookshopsPage bsp = new BookshopsPage(frameC, controller); //chiama il frame 'pf'
                bsp.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BooksPage bp = new BooksPage(frameC, controller);   //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SeriesPage sp = new SeriesPage(frameC, controller);   //chiama il frame 'bp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                IssuesPage ip = new IssuesPage(frameC, controller);   //chiama il frame 'bp'
                ip.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
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

        controller.getNotificheUtente();

        setNumeroNotifiche(controller);

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller);
            }
        });

        timer.start();
        timer.setRepeats(true);

        // --------------------------------------------------------------------------------- //

        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));

        ArrayList<String> distinctGenereList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati

        for (int i = 0; i < controller.listaLibri.size(); i++) {    //scorre l'ArrayList di tutti i generi dei libri
            if (!distinctGenereList.contains(controller.listaLibri.get(i).genere))    //controlla se 'distinctGenereList' non contiene l'i-esimo elemento di genereList
                distinctGenereList.add(controller.listaLibri.get(i).genere);  //inserisce l'i-esimo elemento di genereList  in 'distinctGenereList'
        }

        ArrayList<String> totAutoreList = controller.allAutoriLibri(); //contiene i nomi e cognomi concatenati di tutti gli autori dei libri
        ArrayList<String> distinctAutoreList = controller.allAutoriDistintiLibri();   //contiene i nomi e i cognomi concatenati di tutti gli autori dei libri


        model = new DefaultTableModel(new Object[][]{}, new String[]{"Prodotto", "Quantità", "Fruizione", "Libreria", "Indirizzo", "Numero Di Telefono", "Sito Web"}) {
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
        JTableHeader tableHeader = mainTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        mainTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        mainTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));


        mainTable.setModel(model); //imposta il modello dei dati della JTable 'mainTable'
        mainScrollPanel.setBackground(new Color(0x222831));
        mainScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPanel.getViewport().setBackground(new Color(0x222831));

        controller.getInfoLibriPreferiti();
        controller.getInfoSeriePreferiti();
        controller.getInfoFascicoliPreferiti();

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

        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

       /* mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
                controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 1).toString();
                BookPage bp = new BookPage(frameC, controller); //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visible il frame 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });*/

        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(mainTable.isColumnSelected(6)){
                    try {
                        Desktop.getDesktop().browse(new URI(mainTable.getValueAt(mainTable.getSelectedRow(), 6).toString()));
                    }catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if(controller.libriTitoloPreferiti.contains(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())){
                        controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(20,mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().length());
                        controller.isbn_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(0, 17);
                        BookPage bp = new BookPage(frameC, controller); //chiama il frame 'bp'
                        bp.frame.setVisible(true);  //rende visible il frame 'bp'
                        frame.setVisible(false);    //rende invisibile il frame
                        frame.dispose();
                    } else if(controller.serieTitoloPreferiti.contains(mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString())){
                        controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(20,mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().length());
                        controller.isbn_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString().substring(0, 17);
                        SeriePage sp = new SeriePage(frameC, controller); //chiama il frame 'bp'
                        sp.frame.setVisible(true);  //rende visible il frame 'bp'
                        frame.setVisible(false);    //rende invisibile il frame
                        frame.dispose();
                    } else {
                            controller.nome_selected = mainTable.getValueAt(mainTable.getSelectedRow(), 0).toString();
                            int numero = Integer.valueOf(controller.nome_selected.substring(controller.nome_selected.indexOf("N°")+2, controller.nome_selected.length()));
                            controller.selezionaFascicolo(numero, controller.nome_selected.substring(0, controller.nome_selected.indexOf("N°")-1));
                            IssuePage ip = new IssuePage(frameC, controller); //chiama il frame 'bp'
                            ip.frame.setVisible(true);  //rende visible il frame 'bp'
                            frame.setVisible(false);    //rende invisibile il frame
                            frame.dispose();
                    }
                }
                mainTable.clearSelection();
            }
        });

        libriRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    model.setRowCount(0);
                    searchBarField.setText("");

                    if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {
                        for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {

                            if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                            else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile" , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                            else model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                        }
                    }
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
        });

        fascicoliRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    model.setRowCount(0);
                    searchBarField.setText("");

                    if (controller.fascicoliTitoloPreferiti != null && controller.possessofPreferiti != null && controller.librerieFascicoliPreferiti != null) {
                        for (int i = 0; i < controller.fascicoliTitoloPreferiti.size(); i++) {

                            if(controller.possessofPreferiti.get(i).fruizione.equals("Digitale") || controller.possessofPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "∞", controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                            else if(controller.possessofPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessofPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), "Non Diponibile" , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                            else model.addRow(new Object[]{controller.fascicoliTitoloPreferiti.get(i), controller.possessofPreferiti.get(i).quantita , controller.possessofPreferiti.get(i).fruizione,controller.librerieFascicoliPreferiti.get(i).nome, controller.librerieFascicoliPreferiti.get(i).indirizzo, controller.librerieFascicoliPreferiti.get(i).numeroTelefonico, controller.librerieFascicoliPreferiti.get(i).sitoWeb});
                        }
                    }
                }
            }
        });

        serieRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                model.setRowCount(0);
                searchBarField.setText("");

                if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {
                    for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {

                        if(controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                        else if(controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile" , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                        else model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita , controller.possessosPreferiti.get(i).fruizione,controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
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

            if(searchBarField.getText().contains("'")) searchBarField.setText(searchBarField.getText().replace("'", "’"));

            if (controller.libriTitoloPreferiti != null && controller.possessolPreferiti != null && controller.librerieLibriPreferiti != null) {
                for (int i = 0; i < controller.libriTitoloPreferiti.size(); i++) {
                    String quantita = String.valueOf(controller.possessolPreferiti.get(i).quantita);
                    if(controller.libriTitoloPreferiti.get(i).toLowerCase().contains(searchBarField.getText().toLowerCase()) || quantita.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.possessolPreferiti.get(i).fruizione.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieLibriPreferiti.get(i).nome.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieLibriPreferiti.get(i).indirizzo != null && controller.librerieLibriPreferiti.get(i).indirizzo.toLowerCase().contains(searchBarField.getText().toLowerCase())) || controller.librerieLibriPreferiti.get(i).numeroTelefonico.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieLibriPreferiti.get(i).sitoWeb != null && controller.librerieLibriPreferiti.get(i).sitoWeb.toLowerCase().contains(searchBarField.getText().toLowerCase()))){
                        if(controller.possessolPreferiti.get(i).fruizione.equals("Digitale") || controller.possessolPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "∞", controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                        else if(controller.possessolPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessolPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), "Non Diponibile" , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                        else model.addRow(new Object[]{controller.libriTitoloPreferiti.get(i), controller.possessolPreferiti.get(i).quantita , controller.possessolPreferiti.get(i).fruizione,controller.librerieLibriPreferiti.get(i).nome, controller.librerieLibriPreferiti.get(i).indirizzo, controller.librerieLibriPreferiti.get(i).numeroTelefonico, controller.librerieLibriPreferiti.get(i).sitoWeb});
                    }
                }
            }

            if (controller.serieTitoloPreferiti != null && controller.possessosPreferiti != null && controller.librerieSeriePreferiti != null) {
                for (int i = 0; i < controller.serieTitoloPreferiti.size(); i++) {
                    String quantita = String.valueOf(controller.possessosPreferiti.get(i).quantita);

                    if (controller.serieTitoloPreferiti.get(i).toLowerCase().contains(searchBarField.getText().toLowerCase()) || quantita.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.possessosPreferiti.get(i).fruizione.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).nome.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).indirizzo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.librerieSeriePreferiti.get(i).numeroTelefonico.toLowerCase().contains(searchBarField.getText().toLowerCase()) || (controller.librerieSeriePreferiti.get(i).sitoWeb != null && controller.librerieSeriePreferiti.get(i).sitoWeb.toLowerCase().contains(searchBarField.getText().toLowerCase()))){
                        if (controller.possessosPreferiti.get(i).fruizione.equals("Digitale") || controller.possessosPreferiti.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "∞", controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                        else if (controller.possessosPreferiti.get(i).fruizione.equals("Cartaceo") && controller.possessosPreferiti.get(i).quantita == 0) model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), "Non Diponibile", controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
                        else model.addRow(new Object[]{controller.serieTitoloPreferiti.get(i), controller.possessosPreferiti.get(i).quantita, controller.possessosPreferiti.get(i).fruizione, controller.librerieSeriePreferiti.get(i).nome, controller.librerieSeriePreferiti.get(i).indirizzo, controller.librerieSeriePreferiti.get(i).numeroTelefonico, controller.librerieSeriePreferiti.get(i).sitoWeb});
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
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);

        searchBarField = new JTextField();
        searchBarField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));


        ImageIcon radioIco = new ImageIcon(this.getClass().getResource("/r2.png"));
        Image radioImg = radioIco.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        radioIco = new ImageIcon(radioImg);

        ImageIcon radioSelIco = new ImageIcon(this.getClass().getResource("/r1.png"));
        Image radioSelImg = radioSelIco.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
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
        Image searchImg = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(searchImg);
        searchImage = new JLabel(searchIcon);

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));
        Image notificaImg = notificaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        notificaIco = new ImageIcon(notificaImg);

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);
    }
}
