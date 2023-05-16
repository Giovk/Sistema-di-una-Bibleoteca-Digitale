package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Profilo {
    public JFrame frame;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JPanel profilePanel;
    private JTextField textField1;
    private JButton cambiaButton;
    private JLabel text1;

    private JPopupMenu utenteMenu;

    public Profilo (JFrame frameC, Controller controller, Utente user){
        text1.setText("Ciao " + user.username + ", Sei nel Profilo!");

        frame = new JFrame("Profilo");
        frame.setUndecorated(true);
        frame.setContentPane(profilePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        utenteMenu = new JPopupMenu();
        JMenuItem utenteExit = new JMenuItem("Logout");
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");
        utenteMenu.add(utenteLibrerie);
        utenteMenu.add(utenteExit);

        if (user.partitaIVA == null) {
            utenteLibrerie.setVisible(false);
            System.out.println(user.partitaIVA);
        }

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getX(), utenteButton.getY() + 25);
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
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(frameC, controller, user);
                hp.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }

}
