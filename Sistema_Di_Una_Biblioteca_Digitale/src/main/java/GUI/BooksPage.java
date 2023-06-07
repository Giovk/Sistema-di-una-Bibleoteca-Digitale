package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    public BooksPage(JFrame frameC, Controller controller){
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

        ArrayList<String> distinctGenereList = new ArrayList<String>(); //contiene tutti i generi dei libri senza duplicati

        for (int i = 0; i < genereList.size(); i++){    //scorre l'ArrayList di tutti i generi dei libri
            if(!distinctGenereList.contains(genereList.get(i))) distinctGenereList.add(genereList.get(i));  //se 'distinctGenereList' non contiene l'i-esimo elemento di genereList, allora lo inserisce in 'distinctGenereList'
        }

        genereCB.setModel(new DefaultComboBoxModel<String>(distinctGenereList.toArray(new String[distinctGenereList.size()]))); //inserisce tutti gli elementi di 'distinctGenereList' come voci del JComboBox 'genereCB'
        genereCB.setSelectedIndex(-1);  //permette di avere 'genereCB' non selezionato

        ArrayList<String> linkAutoreList = new ArrayList<String>(); //contiene i nomi e cognomi conatenati di tutti gli autori dei libri
        ArrayList<String> distinctAutoreList = new ArrayList<String>(); //contiene i nomi e cognomi conatenati di tutti gli autori dei libri senza duplicati

        for (int i = 0; i < autoreNomeList.size(); i++){    //scorre 'autoreNomeList' e 'autoreNomeList'
            linkAutoreList.add(autoreNomeList.get(i) + " " + autoreCognomeList.get(i)); //aggiunge in 'linkAutoreList' il nome e cognome concatenati del i-esimo autore

            if(!distinctAutoreList.contains(linkAutoreList.get(i))) distinctAutoreList.add(linkAutoreList.get(i));  //se l'i-esimo elemento di 'linkAutoreList' non è contenuto in 'distinctAutoreList', allora lo inserisce in 'distinctAutoreList'
        }

        autoreCB.setModel(new DefaultComboBoxModel<String>(distinctAutoreList.toArray(new String[distinctAutoreList.size()]))); //inserisce tutti gli elementi di 'distinctAutoreList' come voci del JComboBox 'autoreCB'
        autoreCB.setSelectedIndex(-1);  //permette di avere 'autoreCB' non selezionato

        collanaCB.setModel(new DefaultComboBoxModel<String>(collanaList.toArray(new String[collanaList.size()])));  //inserisce tutti gli elementi di 'collanaList' come voci del JComboBox 'collanaCB'
        collanaCB.setSelectedIndex(-1); //permette di avere 'collanaCB' non selezionato

        booksTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Genere", "Lingua", "Editore", "Data di pubblicazione"}));
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();

        if (isbnList != null && titoloList != null && genereList != null && linguaList != null && editoreList != null && dataPubblicazioneList != null){
            for (int i = 0; i < isbnList.size(); i++){
                model.addRow(new Object[]{isbnList.get(i), titoloList.get(i), genereList.get(i), linguaList.get(i), editoreList.get(i), dataPubblicazioneList.get(i)});
            }
        }

        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

    }
        private void createUIComponents () {
            // TODO: place custom component creation code here
            collanaRB = new JRadioButton();
            autoreRB = new JRadioButton();
            genereRB = new JRadioButton();
            groupRB = new ButtonGroup();
            groupRB.add(collanaRB);
            groupRB.add(genereRB);
            groupRB.add(autoreRB);


            ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/search.png"));
            Image searchImg = searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            searchIcon = new ImageIcon(searchImg);
            searchImage = new JLabel(searchIcon);

        }
    }