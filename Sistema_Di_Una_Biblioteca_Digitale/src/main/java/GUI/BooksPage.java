package GUI;

import Controller.Controller;
import Model.Autore;
import Model.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BooksPage {
    public JFrame frame;
    private JButton homeButton;
    private JRadioButton genereRB;
    private JRadioButton autoreRB;
    private JRadioButton collanaRB;
    private ButtonGroup groupRB;
    private JComboBox genereCB;
    private JComboBox autoreCB;
    private JComboBox collanaCB;
    private JTable booksTable;
    private JTextField searchBarField;
    private JLabel searchImage;
    private JScrollPane booksScrollPanel;
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
    private String linkString = "";
    private int aut;
    private Boolean active = false;

    private DefaultTableModel model;
    private int numeroNotifiche;
    private JPopupMenu utenteMenu;

    public BooksPage(JFrame frameC, Controller controller) {
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

        genereRB.setFont(controller.impactFontSize);
        genereCB.setFont(controller.impactFontSize);
        autoreRB.setFont(controller.impactFontSize);
        autoreCB.setFont(controller.impactFontSize);
        collanaRB.setFont(controller.impactFontSize);
        collanaCB.setFont(controller.impactFontSize);
        resetFiltriLabel.setFont(controller.impactFontSize);

        booksTable.setFont(controller.impactFontSize);
        booksTable.setRowMargin(controller.screenWidth/640);
        booksTable.setRowHeight(controller.screenHeight/36);

        panel2.setMinimumSize(new Dimension(controller.screenWidth, controller.screenHeight - ((int) (controller.screenHeight/4))));

        booksScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);

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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
        //utenteMenu.getPop
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

        ArrayList<String> collanaList = controller.getCollanaNome();    //collane di libri nel DB

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
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

        // -------------------------------------------------------------------------------------- //
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                hp.frame.setVisible(true);  //rende visibile il frame 'hp'
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

        // --------------------------------------------------------------------------- //
        UIManager.put("ComboBox.disabledForeground", new Color(0x222831));
        UIManager.put("ComboBox.disabledBackground", new Color(0xFFD369));

        collanaCB.setEnabled(false);    //disabilita il JComboBox 'collanaCB'
        genereCB.setEnabled(false); //disabilita il JComboBox 'genereCB'
        autoreCB.setEnabled(false); //disabilita il JComboBox 'autoreCB'


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
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72),  Image.SCALE_SMOOTH);
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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

        collanaCB.setBackground(Color.decode("#FFD369"));
        collanaCB.setForeground(Color.decode("#222831"));
        collanaCB.setBorder(new LineBorder(Color.decode("#222831")));

        Object comp3 = collanaCB.getUI().getAccessibleChild(collanaCB, 0);
        if(comp3 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp3;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) , Image.SCALE_SMOOTH);
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
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


        ArrayList<String> distinctGenereList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati

        for (int i = 0; i < controller.listaLibri.size(); i++) {    //scorre l'ArrayList di tutti i generi dei libri
            if (!distinctGenereList.contains(controller.listaLibri.get(i).genere))    //controlla se 'distinctGenereList' non contiene l'i-esimo elemento di genereList
                distinctGenereList.add(controller.listaLibri.get(i).genere);  //inserisce l'i-esimo elemento di genereList  in 'distinctGenereList'
        }

        genereCB.setModel(new DefaultComboBoxModel<String>(distinctGenereList.toArray(new String[distinctGenereList.size()]))); //inserisce tutti gli elementi di 'distinctGenereList' come voci del JComboBox 'genereCB'
        genereCB.setSelectedIndex(-1);  //permette di avere 'genereCB' non selezionato

        ArrayList<String> totAutoreList = controller.allAutoriLibri(); //contiene i nomi e cognomi concatenati di tutti gli autori dei libri
        ArrayList<String> distinctAutoreList = controller.allAutoriDistintiLibri();   //contiene i nomi e i cognomi concatenati di tutti gli autori dei libri

        autoreCB.setModel(new DefaultComboBoxModel<String>(distinctAutoreList.toArray(new String[distinctAutoreList.size()]))); //inserisce tutti gli elementi di 'distinctAutoreList' come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato
        collanaCB.setModel(new DefaultComboBoxModel<String>(collanaList.toArray(new String[collanaList.size()])));  //inserisce tutti gli elementi di 'collanaList' come voci del JComboBox 'collanaCB'
        collanaCB.setSelectedIndex(-1); //permette di avere 'collanaCB' non selezionato

        model = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Autori", "Genere", "Lingua", "Editore", "Data di pubblicazione"}) {
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
        JTableHeader tableHeader = booksTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        booksTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        booksTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));


        booksTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'
        booksScrollPanel.setBackground(new Color(0x222831));
        booksScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        booksScrollPanel.getViewport().setBackground(new Color(0x222831));

        if (controller.listaLibri != null) {
            for (int i = 0; i < controller.listaLibri.size(); i++) {
                model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, totAutoreList.get(i), controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
            }
        }

        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
                controller.nome_selected = booksTable.getValueAt(booksTable.getSelectedRow(), 1).toString();
                BookPage bp = new BookPage(frameC, controller); //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visible il frame 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        genereRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'genereRB'
                    searchBarField.setText("");
                    genereCB.setEnabled(true);  //abilita 'genereCB'
                    collanaCB.setSelectedIndex(-1); //deseleziona 'collanaCB'
                    collanaCB.setEnabled(false);    //disabilita 'collanaCB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'genereRB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
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
                for (int i = 0; i < controller.listaLibri.size(); i++) model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, totAutoreList.get(i), controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
            }
        });

        collanaRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'collanaRB'
                    searchBarField.setText("");
                    collanaCB.setEnabled(true); //abilita 'collanaCB'
                    genereCB.setSelectedIndex(-1);  //deseleziona 'genereCB'
                    genereCB.setEnabled(false); //disabilita 'genereCB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'collanaRB'
                    collanaCB.setSelectedIndex(-1); //deseleziona 'collanaCB'
                    collanaCB.setEnabled(false);    //disabilita 'collanaCB'
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
                    collanaCB.setSelectedIndex(-1); //deseleziona 'collanaCB'
                    collanaCB.setEnabled(false);    //disabilita 'collanaCB'
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {  //controlla se è stato deselezionato 'collanaRB'
                    autoreCB.setSelectedIndex(-1);  //deseleziona 'autoreCB'
                    autoreCB.setEnabled(false); //disabilita 'autoreCB'
                }
            }
        });

        genereCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (genereCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'genereCB'
                    model.setRowCount(0);   //elimina le righe della tabella

                    ArrayList<Libro> listaLibri = controller.listaLibri;    //lista contenente tutti i libri

                    for (Libro l : listaLibri) {    //scorre la lista dei libri 'listaLibri'

                        if (genereCB.getSelectedItem() != null && genereCB.getSelectedItem().equals(l.genere)) {    //controlla se l'elemento selezionato di 'genereCB' è uguale al genere del libro 'l'
                            aut = 0;    //numero di autori del libro 'l'

                            for (Autore a : l.autori) { //scorre tutti gli autori del libro 'l'

                                if (aut == 0) linkString = a.nome + " " + a.cognome;    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                                else linkString = linkString + "\n" + a.nome + " " + a.cognome; //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo

                                aut++;  //incrementa il numero di autori di 'l'
                            }

                            model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                        }
                    }
                }
            }
        });

        autoreCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoreCB.getSelectedItem() != null) {   //controlla se è stato selezionato un elemento di 'autoreCB'
                    model.setRowCount(0);   //elimina le righe della tabella

                    for (int i = 0; i < controller.listaLibri.size(); i++) {    //scorre la lista dei libri 'listaLibri'

                        if (autoreCB.getSelectedItem() != null) {
                            for (int j = 0; j < controller.listaLibri.get(i).autori.size(); j++) { //scorre tutti gli autori del libro 'l'

                                if (j == 0) linkString = controller.listaLibri.get(i).autori.get(j).nome + " " + controller.listaLibri.get(i).autori.get(j).cognome;    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                                else linkString = linkString + " \n" + controller.listaLibri.get(i).autori.get(j).nome + " " + controller.listaLibri.get(i).autori.get(j).cognome; //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo
                            }

                            if (linkString.contains(autoreCB.getSelectedItem().toString())) //controlla se l'elemento selezionato di 'autoreCB' è contenuto in 'linkString'
                                model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, linkString, controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
                        }
                    }
                }
            }
        });

        collanaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Libro> libriCollana = new ArrayList<>();  //lista dei libri di una collana

                if (collanaCB.getSelectedItem() != null){   //controlla se è stato selezionato un elemento di 'collanaCB'
                    model.setRowCount(0);   //elimina le righe della tabella
                    controller.getLibri(collanaCB.getSelectedItem().toString()); //inizializza 'libriCollana' con tutti i libri della collana selezionata
                }

                for (int i = 0; i < controller.listaLibriCollana.size(); i++) {  //scorre i libri della collana selezionata

                    if (collanaCB.getSelectedItem() != null) {
                        for (int j = 0; j < controller.listaLibriCollana.get(i).autori.size(); j++) { //scorre tutti gli autori del libro 'l'

                            if (j == 0) linkString = controller.listaLibriCollana.get(i).autori.get(j).nome + " " + controller.listaLibriCollana.get(i).autori.get(j).cognome;    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                            else linkString = linkString + " \n" + controller.listaLibriCollana.get(i).autori.get(j).nome + " " + controller.listaLibriCollana.get(i).autori.get(j).cognome; //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo
                        }

                        model.addRow(new Object[]{controller.listaLibriCollana.get(i).isbn, controller.listaLibriCollana.get(i).titolo, linkString, controller.listaLibriCollana.get(i).genere, controller.listaLibriCollana.get(i).lingua, controller.listaLibriCollana.get(i).editore, controller.listaLibriCollana.get(i).dataPubblicazione});
                    }
                }
            }
        });

        searchImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                search(controller, totAutoreList);  //esegue la ricerca
            }
        });

        searchImage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){   //controlla se è stato premuto il tasto "ENTER"
                    search(controller, totAutoreList);  //esegue la ricerca
                }
            }
        });

        searchBarField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){    //controlla se è stato premuto il tasto "ENTER"
                    search(controller, totAutoreList);  //esegue la ricerca
                    e.consume();    //evita che il KeyEvent 'e' venga ulteriormente gestito
                }
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

    private void search(Controller controller, ArrayList<String> totAutoreList){    //esegue una ricerca nella tabella
        if (!searchBarField.getText().isBlank()) {  //controlla se è stato inserito un testo nel JTextField 'searchBarField'
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            if(searchBarField.getText().contains("'")) searchBarField.setText(searchBarField.getText().replace("'", "’"));


            for (int i = 0; i < controller.listaLibri.size(); i++) {    //scorre la lista dei libri 'listaLibri'
                for (int j = 0; j < controller.listaLibri.get(i).autori.size(); j++) { //scorre tutti gli autori del libro 'l'
                    if (j == 0) linkString = controller.listaLibri.get(i).autori.get(j).nome + " " + controller.listaLibri.get(i).autori.get(j).cognome;    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                    else linkString = linkString + " \n" + controller.listaLibri.get(i).autori.get(j).nome + " " + controller.listaLibri.get(i).autori.get(j).cognome; //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo
                }

                if (controller.listaLibri.get(i).isbn.toLowerCase().contains(searchBarField.getText().toLowerCase()) ||controller.listaLibri.get(i).titolo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || linkString.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaLibri.get(i).genere.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaLibri.get(i).lingua.toLowerCase().contains((searchBarField.getText().toLowerCase())) || controller.listaLibri.get(i).editore.toLowerCase().contains(searchBarField.getText().toLowerCase()) || controller.listaLibri.get(i).dataPubblicazione.toString().toLowerCase().contains(searchBarField.getText().toLowerCase())) //controlla se l'isbn, il titolo, gli autori, il genere, la lingua, l'editore o la data di pubblicazione contiene il testo scritto in 'searchBarField'
                    model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, linkString, controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
            }
        } else {
            groupRB.clearSelection();   //deseleziona tutti i bottoni del 'ButtonGroup' groupRB
            model.setRowCount(0);   //elimina tutte le righe della teblla

            for (int i = 0; i < controller.listaLibri.size(); i++) model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, totAutoreList.get(i), controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
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
        genereRB = new JRadioButton();
        genereRB.setIcon(radioIco);
        genereRB.setSelectedIcon(radioSelIco);
        autoreRB = new JRadioButton();
        autoreRB.setIcon(radioIco);
        autoreRB.setSelectedIcon(radioSelIco);
        collanaRB = new JRadioButton();
        collanaRB.setIcon(radioIco);
        collanaRB.setSelectedIcon(radioSelIco);
        groupRB.add(genereRB);
        groupRB.add(autoreRB);
        groupRB.add(collanaRB);

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







