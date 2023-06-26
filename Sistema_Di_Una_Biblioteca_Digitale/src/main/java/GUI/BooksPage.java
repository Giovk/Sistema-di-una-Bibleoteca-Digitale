package GUI;

import Controller.Controller;
import Model.Autore;
import Model.Libro;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
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
    private JPanel jpanel3;
    private JPanel jpanel;
    private JLabel resetFiltriLabel;
    private JPanel buttonPanel;
    private JLabel closeBT;
    private JButton libriButton;
    private JButton utenteButton;
    private String linkString = "";
    private int aut;
    private Boolean active = false;


    public BooksPage(JFrame frameC, Controller controller) {
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29));
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831));

        JPopupMenu utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
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

        if (controller.getPartitaIva() == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
        }

        ArrayList<String> isbnList = controller.getLibriISBN(); //ISBN di tutti i libri nel DB
        ArrayList<String> titoloList = controller.getLibriTitolo(); //titoli di tutti i libri nel DB
        ArrayList<String> genereList = controller.getLibriGenere(); //generi di tutti i libri nel DB
        ArrayList<String> linguaList = controller.getLibriLingua(); //lingue di tutti i libri nel DB
        ArrayList<String> editoreList = controller.getLibriEditore();   //editori di tutti i libri nel DB
        ArrayList<String> dataPubblicazioneList = controller.getLibriDataPubblicazione();   //date di pubblicazione di tutti i libri nel DB
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

        // --------------------------------------------------------------------------- //
        collanaCB.setEnabled(false);    //disabilita il JComboBox 'collanaCB'
        genereCB.setEnabled(false); //disabilita il JComboBox 'genereCB'
        autoreCB.setEnabled(false); //disabilita il JComboBox 'autoreCB'

        ArrayList<String> distinctGenereList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati

        for (int i = 0; i < genereList.size(); i++) {    //scorre l'ArrayList di tutti i generi dei libri
            if (!distinctGenereList.contains(genereList.get(i)))
                distinctGenereList.add(genereList.get(i));  //se 'distinctGenereList' non contiene l'i-esimo elemento di genereList, allora lo inserisce in 'distinctGenereList'
        }

        genereCB.setModel(new DefaultComboBoxModel<String>(distinctGenereList.toArray(new String[distinctGenereList.size()]))); //inserisce tutti gli elementi di 'distinctGenereList' come voci del JComboBox 'genereCB'
        genereCB.setSelectedIndex(-1);  //permette di avere 'genereCB' non selezionato

        ArrayList<String> totAutoreList = new ArrayList<String>(); //contiene i nomi e cognomi conatenati di tutti gli autori dei libri senza duplicati
        for (Libro l: controller.listaLibri) {    //scorre 'autoreNomeList' e 'autoreNomeList'
            aut = 0;
            for (Autore a: l.autori) {

                if (aut == 0) linkString = a.nome + " " + a.cognome;
                else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                aut++;
            }
                totAutoreList.add(linkString);  //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
        }

        ArrayList<String> distinctAutoreList = new ArrayList<>();
        for (Libro l: controller.listaLibri) {    //scorre 'autoreNomeList' e 'autoreNomeList'
            for (Autore a : l.autori) {
                linkString = a.nome + " " + a.cognome;
                if (!distinctAutoreList.contains(linkString)) {
                    distinctAutoreList.add(linkString);  //se 'distinctAutoriList' non contiene l'i-esimo elemento di genereList, allora lo inserisce in 'distinctGenereList'
                }
            }
        }



        autoreCB.setModel(new DefaultComboBoxModel<String>(distinctAutoreList.toArray(new String[distinctAutoreList.size()]))); //inserisce tutti gli elementi di 'distinctAutoreList' come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato

        collanaCB.setModel(new DefaultComboBoxModel<String>(collanaList.toArray(new String[collanaList.size()])));  //inserisce tutti gli elementi di 'collanaList' come voci del JComboBox 'collanaCB'
        collanaCB.setSelectedIndex(-1); //permette di avere 'collanaCB' non selezionato
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Autori", "Genere", "Lingua", "Editore", "Data di pubblicazione"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };

        booksTable.setModel(model);
        //DefaultTableModel model = (DefaultTableModel) booksTable.getModel();

        if (isbnList != null && titoloList != null && totAutoreList != null && genereList != null && linguaList != null && editoreList != null && dataPubblicazioneList != null) {
            for (int i = 0; i < isbnList.size(); i++) {
                model.addRow(new Object[]{isbnList.get(i), titoloList.get(i), totAutoreList.get(i), genereList.get(i), linguaList.get(i), editoreList.get(i), dataPubblicazioneList.get(i)});
            }
        }


        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);



        booksTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = booksTable.getValueAt(booksTable.getSelectedRow(), 0).toString();
                controller.nome_l = booksTable.getValueAt(booksTable.getSelectedRow(), 1).toString();
                BookPage bp = new BookPage(frameC, controller); //chiama il frame 'pf'
                bp.frame.setVisible(true);  //rende visible il frame 'pf'
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
                if (genereCB.getSelectedItem() != null) {
                    model.setRowCount(0);
                    ArrayList<Libro> listaLibri = controller.getLibri();
                    for (Libro l : listaLibri) {
                        if (genereCB.getSelectedItem() != null && genereCB.getSelectedItem().equals(l.genere)) {
                            aut = 0;
                            for (Autore a : l.autori) {

                                if (aut == 0) linkString = a.nome + " " + a.cognome;
                                else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                                aut++;
                            }
                            //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
                            model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                        }
                    }
                }
            }
        });

        autoreCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (autoreCB.getSelectedItem() != null) {
                    model.setRowCount(0);
                    ArrayList<Libro> listaLibri = controller.getLibri();
                    for (Libro l : listaLibri) {
                        if (autoreCB.getSelectedItem() != null) {
                            aut = 0;
                            for (Autore a : l.autori) {

                                if (aut == 0) linkString = a.nome + " " + a.cognome;
                                else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                                aut++;
                            }
                            //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
                            if (linkString.contains(autoreCB.getSelectedItem().toString()))
                                model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                        }
                    }
                }
            }
        });

        collanaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Libro> libriCollana = new ArrayList<>();
                if (collanaCB.getSelectedItem() != null){
                    model.setRowCount(0);
                    libriCollana = controller.getLibri(collanaCB.getSelectedItem().toString());
                }
                for (Libro l : libriCollana) {
                    if (collanaCB.getSelectedItem() != null) {
                        aut = 0;
                        for (Autore a : l.autori) {

                            if (aut == 0) linkString = a.nome + " " + a.cognome;
                            else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                            aut++;
                        }
                        //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
                            model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                    }
                }
            }
        });
        searchImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!searchBarField.getText().isBlank()) {
                    model.setRowCount(0);
                    ArrayList<Libro> listaLibri = controller.getLibri();
                    for (Libro l : listaLibri) {
                        aut = 0;
                        for (Autore a : l.autori) {

                            if (aut == 0) linkString = a.nome + " " + a.cognome;
                            else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                            aut++;
                        }
                        //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
                        if (l.isbn.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.titolo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || linkString.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.genere.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.lingua.toLowerCase().contains((searchBarField.getText().toLowerCase())) || l.editore.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.dataPubblicazione.toString().toLowerCase().contains(searchBarField.getText().toLowerCase()))
                            model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                    }
                } else {
                    groupRB.clearSelection();
                    model.setRowCount(0);
                    for (int i = 0; i < controller.listaLibri.size(); i++) model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, totAutoreList.get(i), controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
                }
            }
        });
        searchBarField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (!searchBarField.getText().isBlank()) {
                        groupRB.clearSelection();
                        model.setRowCount(0);
                        ArrayList<Libro> listaLibri = controller.getLibri();
                        for (Libro l : listaLibri) {
                            aut = 0;
                            for (Autore a : l.autori) {

                                if (aut == 0) linkString = a.nome + " " + a.cognome;
                                else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                                aut++;
                            }
                            //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
                            if (l.isbn.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.titolo.toLowerCase().contains(searchBarField.getText().toLowerCase()) || linkString.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.genere.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.lingua.toLowerCase().contains((searchBarField.getText().toLowerCase())) || l.editore.toLowerCase().contains(searchBarField.getText().toLowerCase()) || l.dataPubblicazione.toString().toLowerCase().contains(searchBarField.getText().toLowerCase()))
                                model.addRow(new Object[]{l.isbn, l.titolo, linkString, l.genere, l.lingua, l.editore, l.dataPubblicazione});
                        }
                    } else {
                        groupRB.clearSelection();
                        model.setRowCount(0);
                        for (int i = 0; i < controller.listaLibri.size(); i++) model.addRow(new Object[]{controller.listaLibri.get(i).isbn, controller.listaLibri.get(i).titolo, totAutoreList.get(i), controller.listaLibri.get(i).genere, controller.listaLibri.get(i).lingua, controller.listaLibri.get(i).editore, controller.listaLibri.get(i).dataPubblicazione});
                    }
                    e.consume();
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
        booksTable.addComponentListener(new ComponentAdapter() {
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);

        groupRB = new ButtonGroup();
        genereRB = new JRadioButton();
        autoreRB = new JRadioButton();
        collanaRB = new JRadioButton();
        groupRB.add(genereRB);
        groupRB.add(autoreRB);
        groupRB.add(collanaRB);

        ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/search.png"));
        Image searchImg = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(searchImg);
        searchImage = new JLabel(searchIcon);

    }

}