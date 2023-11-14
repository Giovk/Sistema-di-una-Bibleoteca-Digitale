package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LSForm {
    private Controller controller;
    private static JFrame frame;
    private JButton btSignIn;
    private JButton btSignUp;
    private JLabel txtPF;
    private JLabel image;
    private JPanel lsPanel;
    private int width = 0;
    private int height = 0;

    public LSForm() {
        controller = new Controller();

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(lsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        btSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewLoginForm newLoginForm = new NewLoginForm(0, frame, controller); //chiama il frame 'NewLoginForm'
                frame.setEnabled(false); //disabilita il frame
            }
        });

        btSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewLoginForm newLoginForm = new NewLoginForm(1, frame, controller); //chiama il frame 'NewLoginForm'
                frame.setEnabled(false);    //disabilita il frame
            }
        });

        btSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
                Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
                bt1igm = new ImageIcon(resbt1img);
                btSignIn.setIcon(bt1igm);   //imposta l'icona del JButton 'btSignIn' con l'immagine
                btSignIn.setForeground(Color.decode("#EEEEEE"));    //imposta il colore dello sfondo del JButton 'btSignIn'

            }
        });

        btSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type2.png")); //carica l'immagine nel percorso /button-type2.png
                Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
                bt1igm = new ImageIcon(resbt1img);
                btSignIn.setIcon(bt1igm);   //imposta l'icona del JButton 'btSignIn' con l'immagine
                btSignIn.setForeground(Color.decode("#D6D4D4"));    //imposta il colore dello sfondo del JButton 'btSignIn'

            }
        });

        btSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
                Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
                bt2igm = new ImageIcon(resbt2img);
                btSignUp.setIcon(bt2igm);   //imposta l'icona del JButton 'btSignUp' con l'immagine
                btSignUp.setForeground(Color.decode("#EEEEEE"));    //imposta il colore dello sfondo del JButton 'btSignUp'

            }
        });

        btSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type2.png")); //carica l'immagine nel percorso /button-type2.png
                Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
                bt2igm = new ImageIcon(resbt2img);
                btSignUp.setIcon(bt2igm);   //imposta l'icona del JButton 'btSignUp' con l'immagine
                btSignUp.setForeground(Color.decode("#D6D4D4"));    //imposta il colore delllo sfondo del JButton 'btSignUp'
            }
        });

        txtPF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                txtPF.setForeground(Color.decode("#D6D4D4"));   //imposta il colore dello sfondo della JLabel 'txtPF'
            }
        });

        txtPF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                txtPF.setForeground(Color.decode("#EEEEEE"));   //imposta il colore dello sfondo della JLabel 'txtPF'
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        txtPF = new JLabel();
        txtPF.setForeground(Color.decode("#EEEEEE"));   //imposta il colore dello sfondo della JLabel 'txtPF'

        image = new JLabel();
        image.setText("");  //imposta il testo della 'JLabel'

        ImageIcon lb1igm = new ImageIcon(this.getClass().getResource("/b.png"));    //carica l'immagine nel percorso /b.png
        Image reslb1img = lb1igm.getImage().getScaledInstance(300, 169, Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        lb1igm = new ImageIcon(reslb1img);
        image.setIcon(lb1igm);  //imposta l'icona della JLabel 'image' con l'immagine

        btSignIn = new JButton();
        btSignIn.setContentAreaFilled(false);   //toglie lo sfondo dell JButton 'btSignIn'
        btSignIn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));    //toglie il bordo del JButton 'btSignIn'
        btSignIn.setMargin(new Insets(0, 0, 0, 0)); //toglie il margine all'interno del JButton 'btSignIn'
        ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
        Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
        bt1igm = new ImageIcon(resbt1img);
        btSignIn.setIcon(bt1igm);   //imposta l'icona del JButton 'btSignIn' con l'immagine
        btSignIn.setHorizontalTextPosition(JButton.CENTER); //centra orizzontalmente il contenuto dell JButton 'btSignIn'
        btSignIn.setVerticalTextPosition(JButton.CENTER);   //centra verticalmente il contenuto dell JButton 'btSignIn'
        btSignIn.setForeground(Color.decode("#EEEEEE"));    //imposta il colore dello sfondo del JButton 'btSignIn'

        btSignUp = new JButton();
        btSignUp.setContentAreaFilled(false);   //toglie lo sfondo dell JButton 'btSignUp'
        btSignUp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));    //toglie il bordo dell JButton 'btSignUp'
        btSignUp.setMargin(new Insets(0, 0, 0, 0)); //toglie il margine all'interno del JButton 'btSignUp'
        ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type1.png")); //carica l'immagine nel percorso /button-type1.png
        Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagin
        bt2igm = new ImageIcon(resbt2img);
        btSignUp.setIcon(bt2igm);   //imposta l'icona del JButton 'btSignIn' con l'immagine
        btSignUp.setHorizontalTextPosition(JButton.CENTER); //centra orizzontalmente il contenuto dell JButton 'btSignUp'
        btSignUp.setVerticalTextPosition(JButton.CENTER);   //centra verticalmente il contenuto dell JButton 'btSignUp'
        btSignUp.setForeground(Color.decode("#EEEEEE"));    //imposta il colore dello sfondo del JButton 'btSignUp'
    }
}