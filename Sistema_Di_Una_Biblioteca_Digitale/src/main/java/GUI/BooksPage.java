package GUI;

import Controller.Controller;
import Model.Autore;
import Model.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BooksPage {
    public JFrame frame;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
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
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel;
    private JLabel resetFiltriLabel;


    public BooksPage(JFrame frameC, Controller controller) {
        ArrayList<String> isbnList = controller.getLibriISBN(); //ISBN di tutti i libri nel DB
        ArrayList<String> titoloList = controller.getLibriTitolo(); //titoli di tutti i libri nel DB
        ArrayList<String> genereList = controller.getLibriGenere(); //generi di tutti i libri nel DB
        ArrayList<String> linguaList = controller.getLibriLingua(); //lingue di tutti i libri nel DB
        ArrayList<String> editoreList = controller.getLibriEditore();   //editori di tutti i libri nel DB
        ArrayList<String> dataPubblicazioneList = controller.getLibriDataPubblicazione();   //date di pubblicazione di tutti i libri nel DB
        ArrayList<String> autoreNomeList = controller.getAutoriLibroNome(); //nomi di tutti gli autori dei libti nel DB
        ArrayList<String> autoreCognomeList = controller.getAutoriLibroCognome();   //cognomi di tutti gli autori dei libti nel DB
        ArrayList<String> collanaList = controller.getCollanaNome();    //collane di libri nel DB

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

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

        ArrayList<String> distinctAutoreList = new ArrayList<String>(); //contiene i nomi e cognomi conatenati di tutti gli autori dei libri senza duplicati
        String linkString = "";
        int aut;
        for (Libro l: controller.listaLibri) {    //scorre 'autoreNomeList' e 'autoreNomeList'
            aut = 0;
            for (Autore a: l.autori) {

                if (aut == 0) linkString = a.nome + " " + a.cognome;
                else linkString = linkString + "\n" + a.nome + " " + a.cognome;
                aut++;
            }
                distinctAutoreList.add(linkString);  //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
        }

        autoreCB.setModel(new DefaultComboBoxModel<String>(distinctAutoreList.toArray(new String[distinctAutoreList.size()]))); //inserisce tutti gli elementi di 'distinctAutoreList' come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato

        collanaCB.setModel(new DefaultComboBoxModel<String>(collanaList.toArray(new String[collanaList.size()])));  //inserisce tutti gli elementi di 'collanaList' come voci del JComboBox 'collanaCB'
        collanaCB.setSelectedIndex(-1); //permette di avere 'collanaCB' non selezionato

        booksTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Autori", "Genere", "Lingua", "Editore", "Data di pubblicazione"}));
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();

        if (isbnList != null && titoloList != null && distinctAutoreList != null && genereList != null && linguaList != null && editoreList != null && dataPubblicazioneList != null) {
            for (int i = 0; i < isbnList.size(); i++) {
                model.addRow(new Object[]{isbnList.get(i), titoloList.get(i), distinctAutoreList.get(i), genereList.get(i), linguaList.get(i), editoreList.get(i), dataPubblicazioneList.get(i)});
            }
        }


        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        genereRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'genereRB'
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
                groupRB.clearSelection();   //deseleziona tutti i JRadioButton del ButtonGroup 'groupRB'
                model.setRowCount(0);
                for (int i = 0; i < isbnList.size(); i++) {
                    model.addRow(new Object[]{isbnList.get(i), titoloList.get(i), distinctAutoreList.get(i), genereList.get(i), linguaList.get(i), editoreList.get(i), dataPubblicazioneList.get(i)});
                }
            }
        });

        collanaRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //controlla se è stato selezionato 'collanaRB'
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
                model.setRowCount(0);
                ArrayList<Libro> listaLibri = controller.getLibri();
                for (Libro l : listaLibri) {
                    if (genereCB.getSelectedItem() != null && genereCB.getSelectedItem().equals(l.genere))
                        model.addRow(new Object[]{l.isbn, l.titolo, "", l.genere, l.lingua, l.editore, l.dataPubblicazione});
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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