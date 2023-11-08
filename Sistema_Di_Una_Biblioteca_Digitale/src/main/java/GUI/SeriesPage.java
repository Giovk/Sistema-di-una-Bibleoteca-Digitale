package GUI;

import Controller.Controller;
import Model.Autore;
import Model.Libro;
import Model.Serie;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private JComboBox autoreCB;
    private JComboBox genereCB;
    private JLabel resetFiltriLabel;
    private JScrollPane seriesScrollPanel;
    private JTable seriesTable;
    private JTextField searchBarField;
    private JLabel searchImage;
    private JButton serieButton;
    private JPanel jpanel;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JPopupMenu utenteMenu;
    private ButtonGroup groupRB;
    private DefaultTableModel model;
    private Boolean active = false;
    private int numeroNotifiche;

    public SeriesPage(JFrame frameC, Controller controller){
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29));
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831));

        seriesScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);   //imposta largezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false); //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);


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

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // ------------------------------------------------------------------------ //
        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));

        genereCB.setEnabled(false); //disabilita il JComboBox 'genereCB'

        genereCB.setModel(new DefaultComboBoxModel<String>(controller.getSerieGeneri().toArray(new String[controller.getSerieGeneri().size()])));   //inserisce tutti gli elementi della lista dei generi delle serie come voci del JComboBox 'genereCB'
        genereCB.setSelectedIndex(-1);  //permette di avere 'genereCB' non selezionato


        autoreCB.setEnabled(false); //disabilita il JComboBox 'autoreCB'

        autoreCB.setModel(new DefaultComboBoxModel<String>(controller.getSerieAutori().toArray(new String[controller.getSerieAutori().size()])));   //inserisce tutti gli elementi della lista degli autori delle serie come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato

        autoreCB.setBackground(Color.decode("#FFD369"));
        autoreCB.setForeground(Color.decode("#222831"));
        autoreCB.setBorder(new LineBorder(Color.decode("#222831")));

        Object comp1 = autoreCB.getUI().getAccessibleChild(autoreCB, 0);
        if(comp1 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp1;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        }

        genereCB.setBackground(Color.decode("#FFD369"));
        genereCB.setForeground(Color.decode("#222831"));
        genereCB.setBorder(new LineBorder(Color.decode("#222831")));

        Object comp2 = genereCB.getUI().getAccessibleChild(genereCB, 0);
        if(comp1 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp1;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        }

        model = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "N. Libri", "Data di pubblicazione"}) {
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
        JTableHeader tableHeader = seriesTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        seriesTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        seriesTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));


        seriesTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'
        seriesScrollPanel.setBackground(new Color(0x222831));
        seriesScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        seriesScrollPanel.getViewport().setBackground(new Color(0x222831));



        genereRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'genereRB'
                    searchBarField.setText("");
                    genereCB.setEnabled(true);  //abilita 'genereCB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'genereRB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
                }
            }
        });

        autoreRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'autoreRB'
                    searchBarField.setText("");
                    autoreCB.setEnabled(true);  //abilita 'autoreCB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'collanaRB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                }
            }
        });

        seriesTable.setModel(model);

        if(controller.listaSerie != null){
            for (int i = 0; i < controller.listaSerie.size(); i++) model.addRow(new Object[]{controller.listaSerie.get(i).isbn, controller.listaSerie.get(i).titolo, controller.listaSerie.get(i).nLibri, controller.listaSerie.get(i).dataPubblicazione});
        }

        seriesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = seriesTable.getValueAt(seriesTable.getSelectedRow(), 0).toString();
                controller.nome_selected = seriesTable.getValueAt(seriesTable.getSelectedRow(), 1).toString();
                SeriePage sp = new SeriePage(frameC, controller); //chiama il frame 'bp'
                sp.frame.setVisible(true);  //rende visible il frame 'bp'
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
                    controller.getListaSerieGenere(genereCB.getSelectedItem().toString());

                    for (int i = 0; i < controller.listaSerieGenere.size(); i++){
                        model.addRow(new Object[]{controller.listaSerieGenere.get(i).isbn, controller.listaSerieGenere.get(i).titolo, controller.listaSerieGenere.get(i).nLibri, controller.listaSerieGenere.get(i).dataPubblicazione});
                    }
                }
            }
        });

        autoreCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoreCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella
                    controller.getListaSerieAutore(autoreCB.getSelectedItem().toString());

                    for (int i = 0; i < controller.listaSerieAutore.size(); i++){
                        model.addRow(new Object[]{controller.listaSerieAutore.get(i).isbn, controller.listaSerieAutore.get(i).titolo, controller.listaSerieAutore.get(i).nLibri, controller.listaSerieAutore.get(i).dataPubblicazione});
                    }
                }
            }
        });

        resetFiltriLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchBarField.setText("");
                groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
                model.setRowCount(0);   //elimina tutte le righe della teblla

                for(int i = 0; i < controller.listaSerie.size(); i++) model.addRow(new Object[]{controller.listaSerie.get(i).isbn, controller.listaSerie.get(i).titolo, controller.listaSerie.get(i).nLibri, controller.listaSerie.get(i).dataPubblicazione});
            }
        });

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

    public void search(Controller controller){  //esegue una ricerca nella tabella
        if (!searchBarField.getText().isBlank()) {  //controlla se è stato inserito un testo nel JTextField 'searchBarField'
            if(searchBarField.getText().contains("'")) searchBarField.setText(searchBarField.getText().replace("'", "’"));


            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for (int i = 0; i < controller.listaSerie.size(); i++) {    //scorre la lista dei libri 'listaLibri'
                if (controller.listaSerie.get(i).isbn.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaSerie.get(i).titolo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaSerie.get(i).dataPubblicazione.toString().toLowerCase().contains(searchBarField.getText().toLowerCase())) //controlla se l'isbn, il titolo, gli autori, il genere, la lingua, l'editore o la data di pubblicazione contiene il testo scritto in 'searchBarField'
                    model.addRow(new Object[]{controller.listaSerie.get(i).isbn, controller.listaSerie.get(i).titolo, controller.listaSerie.get(i).nLibri, controller.listaSerie.get(i).dataPubblicazione});
            }
        } else {
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for(int i = 0; i < controller.listaSerie.size(); i++) model.addRow(new Object[]{controller.listaSerie.get(i).isbn, controller.listaSerie.get(i).titolo, controller.listaSerie.get(i).nLibri, controller.listaSerie.get(i).dataPubblicazione});
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
        genereRB = new JRadioButton();
        genereRB.setIcon(radioIco);
        genereRB.setSelectedIcon(radioSelIco);
        autoreRB = new JRadioButton();
        autoreRB.setIcon(radioIco);
        autoreRB.setSelectedIcon(radioSelIco);
        groupRB.add(genereRB);
        groupRB.add(autoreRB);

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
