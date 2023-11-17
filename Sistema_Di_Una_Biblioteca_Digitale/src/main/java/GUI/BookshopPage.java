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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

        librerieLabelText.setFont(controller.impactFontSize);
        aggiungiLabel.setFont(controller.baseFontSize);

        elementiTable.setFont(controller.impactFontSize);
        elementiTable.setRowMargin(controller.screenWidth/640);
        elementiTable.setRowHeight(controller.screenHeight/36);

        elementiPanel.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4))));

        elementiScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        elementiMenu = new JPopupMenu();
        JMenuItem eliminaElemento = new JMenuItem("Elimina");
        eliminaElemento.setBackground(new Color(0xFFD369));
        eliminaElemento.setBorder(BorderFactory.createEmptyBorder());
        eliminaElemento.setFont(controller.baseFontSize);
        JMenuItem modificaQuantita = new JMenuItem("Modifica quantità");
        modificaQuantita.setBackground(new Color(0xFFD369));
        modificaQuantita.setBorder(BorderFactory.createEmptyBorder());
        modificaQuantita.setFont(controller.baseFontSize);
        elementiMenu.setBorder(BorderFactory.createEmptyBorder());
        elementiMenu.setBackground(new Color(0xFFD369));
        elementiMenu.add(eliminaElemento);
        elementiMenu.add(modificaQuantita);


        frame = new JFrame("Profilo");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(bookshopPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null); //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
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

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton utenteButton
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - (int) (controller.screenWidth/15.24), utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
            }
        });


        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);    //rende visibile il frame chiamante
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

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true); //rende visibile il frame chiamante
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
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

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BookshopsPage bsp = new BookshopsPage(frameC, controller);
                bsp.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
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

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Prodotto", "Quantità", "Fruizione"}) {
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
        JTableHeader tableHeader = elementiTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        elementiTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        elementiTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));

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

        elementiTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'

        elementiScrollPanel.setBackground(new Color(0x222831));
        elementiScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        elementiScrollPanel.getViewport().setBackground(new Color(0x222831));

        elementiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    row_selected = elementiTable.rowAtPoint(e.getPoint());
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
