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
        ArrayList<String> isbnList = controller.getLibriISBN();
        ArrayList<String> titoloList = controller.getLibriTitolo();
        ArrayList<String> genereList = controller.getLibriGenere();
        ArrayList<String> linguaList = controller.getLibriLingua();
        ArrayList<String> editoreList = controller.getLibriEditore();
        ArrayList<String> dataPubblicazioneList = controller.getLibriDataPubblicazione();


        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true);
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        ArrayList<String> distinctGenereList = new ArrayList<String>();
        for (int i = 0; i < genereList.size(); i++){
            if(!distinctGenereList.contains(genereList.get(i))) distinctGenereList.add(genereList.get(i));
        }

        genereCB.setModel(new DefaultComboBoxModel<String>(distinctGenereList.toArray(new String[distinctGenereList.size()])));
        genereCB.setSelectedIndex(-1);

        booksTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Genere", "Lingua", "Editore", "Data di pubblicazione"}));
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
        if (isbnList != null && titoloList != null && genereList != null && linguaList != null && editoreList != null && dataPubblicazioneList != null){
            for (int i = 0; i < isbnList.size(); i++){
                model.addRow(new Object[]{isbnList.get(i), titoloList.get(i), genereList.get(i), linguaList.get(i), editoreList.get(i), dataPubblicazioneList.get(i)});
            }
        }

        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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