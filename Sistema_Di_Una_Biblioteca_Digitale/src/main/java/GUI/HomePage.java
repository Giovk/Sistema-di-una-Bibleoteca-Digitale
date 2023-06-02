package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    public JFrame frame;
    private JPanel homepagePanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel text1;

    private JPopupMenu utenteMenu;

    public HomePage(JFrame frameC, Controller controller) {
        //frameC.setEnabled(true);
        //frameC.setTitle("Homepage");
        text1.setText("Ciao " + controller.getUsername() + ", Sei nella Homepage!");
        utenteMenu = new JPopupMenu();
        JMenuItem utenteExit = new JMenuItem("Logout");
        JMenuItem utenteProfilo = new JMenuItem("Profilo");
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");
        utenteMenu.add(utenteProfilo);
        utenteMenu.add(utenteLibrerie);
        utenteMenu.add(utenteExit);

        if (controller.getPartitaIVA() == null) {
            utenteLibrerie.setVisible(false);
            System.out.println(controller.getPartitaIVA());
        }

        frame = new JFrame("Homepage");
        frame.setUndecorated(true);
        frame.setContentPane(homepagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        libriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getX(), utenteButton.getY() + 25);
                }
            }
        });

        utenteProfilo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profilo pf = new Profilo(frameC, controller);
                pf.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();

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

    }

}
