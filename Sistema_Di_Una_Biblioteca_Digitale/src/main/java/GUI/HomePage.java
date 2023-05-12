package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    public JFrame frame;
    private JPanel homepagePanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton utenteButton;
    private JPopupMenu utenteMenu;
    public HomePage(JFrame frameC, Controller controller){
        //frameC.setEnabled(true);
        //frameC.setTitle("Homepage");

        utenteMenu = new JPopupMenu();
        JMenuItem utenteExit = new JMenuItem("Logout");
        JMenuItem utenteProfilo = new JMenuItem("Profilo");
        utenteMenu.add(utenteProfilo);
        utenteMenu.add(utenteExit);

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
    }
}
