package GUI;

import javax.swing.*;

public class BooksPage {
    private static JFrame frame;
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

    public static void main(String[] args) {
        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true);
        frame.setContentPane(new BooksPage().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        collanaRB = new JRadioButton();
        autoreRB = new JRadioButton();
        genereRB = new JRadioButton();
        groupRB = new ButtonGroup();
        groupRB.add(collanaRB);
        groupRB.add(genereRB);
        groupRB.add(autoreRB);
    }
}
