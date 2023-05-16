package GUI;

import Controller.Controller;
import ImplementazionePostgresDAO.LibroImplementazionePostgresDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    public JFrame frame;
    private JPanel homepagePanel;
    private JButton button1;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JPanel utentePanel;
    private JPanel homePanel;
    private JTextArea bookArea;
    private JPanel bookPanel;
    private JButton generaButton;
    private JScrollPane scrollBookArea;
    private JPanel MenuPanel;
    private JPopupMenu utenteMenu;
    public HomePage(JFrame frameC, Controller controller){
        //frameC.setEnabled(true);
        //frameC.setTitle("Homepage");

        utenteMenu = new JPopupMenu();
        JMenuItem utenteExit = new JMenuItem("Logout");
        JMenuItem utenteProfilo = new JMenuItem("Profilo");
        utenteMenu.add(utenteProfilo);
        utenteMenu.add(utenteExit);

        homePanel.setVisible(true);
        utentePanel.setVisible(false);
        bookPanel.setVisible(false);

        frame = new JFrame("Homepage");
        frame.setUndecorated(true);
        frame.setContentPane(homepagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utentePanel.setVisible(false);
                bookPanel.setVisible(false);
                homePanel.setVisible(true);
            }
        });

        libriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                utentePanel.setVisible(false);
                bookPanel.setVisible(true);
            }
        });
        generaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibroImplementazionePostgresDAO l = new LibroImplementazionePostgresDAO();
                l.printLibri(bookArea);
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    utenteMenu.show(utenteButton, utenteButton.getX(), utenteButton.getY()+25);
                }
            }
        });
        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        utenteProfilo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookPanel.setVisible(false);
                homePanel.setVisible(false);
                utentePanel.setVisible(true);
            }
        });
    }
}
