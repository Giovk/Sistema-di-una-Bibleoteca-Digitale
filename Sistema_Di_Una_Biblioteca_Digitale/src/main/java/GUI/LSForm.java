package GUI;

import Controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.Buffer;

public class LSForm {
    private Controller controller;
    private static JFrame frame;
    private JButton btSignIn;
    private JButton btSignUp;
    private JLabel txtPF;
    private JLabel image;
    private JPanel lsPanel;
    private ImageIcon image2;

    public LSForm() {
        controller = new Controller();
        btSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LSDialog lsDialog = new LSDialog(0, frame, controller); //chiama il frame 'lsDialog'
                NewLoginForm newLoginForm = new NewLoginForm(0, frame, controller);
                frame.setEnabled(false); //disabilita il frame
            }
        });

        btSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LSDialog lsDialog = new LSDialog(1, frame, controller); //chiama il frame 'lsDialog'
                NewLoginForm newLoginForm = new NewLoginForm(1, frame, controller);
                frame.setEnabled(false);//disabilita il frame
            }
        });

        btSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
                bt1igm = new ImageIcon(resbt1img);
                btSignIn.setIcon(bt1igm);
                btSignIn.setForeground(Color.decode("#EEEEEE"));

            }
        });

        btSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
                bt1igm = new ImageIcon(resbt1img);
                btSignIn.setIcon(bt1igm);
                btSignIn.setForeground(Color.decode("#D6D4D4"));

            }
        });

        btSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
                bt2igm = new ImageIcon(resbt2img);
                btSignUp.setIcon(bt2igm);
                btSignUp.setForeground(Color.decode("#EEEEEE"));

            }
        });

        btSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
                bt2igm = new ImageIcon(resbt2img);
                btSignUp.setIcon(bt2igm);
                btSignUp.setForeground(Color.decode("#D6D4D4"));
            }
        });

        txtPF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                txtPF.setForeground(Color.decode("#D6D4D4"));
            }
        });

        txtPF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                txtPF.setForeground(Color.decode("#EEEEEE"));
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(new LSForm().lsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);
    }

    private void createUIComponents() {
        InstallFont installFont = new InstallFont();

        // TODO: place custom component creation code here
        txtPF = new JLabel();
        txtPF.setForeground(Color.decode("#EEEEEE"));

        image = new JLabel();
        image.setText("");


        ImageIcon lb1igm = new ImageIcon(this.getClass().getResource("/b.png"));
        Image reslb1img = lb1igm.getImage().getScaledInstance(300, 169, Image.SCALE_SMOOTH);
        lb1igm = new ImageIcon(reslb1img);
        image.setIcon(lb1igm);


        btSignIn = new JButton();
        btSignIn.setContentAreaFilled(false);
        btSignIn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btSignIn.setMargin(new Insets(0, 0, 0, 0));
        ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
        Image resbt1img = bt1igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
        bt1igm = new ImageIcon(resbt1img);
        btSignIn.setIcon(bt1igm);
        btSignIn.setHorizontalTextPosition(JButton.CENTER);
        btSignIn.setVerticalTextPosition(JButton.CENTER);
        btSignIn.setForeground(Color.decode("#EEEEEE"));

        btSignUp = new JButton();
        btSignUp.setContentAreaFilled(false);
        btSignUp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btSignUp.setMargin(new Insets(0, 0, 0, 0));
        ImageIcon bt2igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
        Image resbt2img = bt2igm.getImage().getScaledInstance(150, 41, Image.SCALE_SMOOTH);
        bt2igm = new ImageIcon(resbt2img);
        btSignUp.setIcon(bt2igm);
        btSignUp.setHorizontalTextPosition(JButton.CENTER);
        btSignUp.setVerticalTextPosition(JButton.CENTER);
        btSignUp.setForeground(Color.decode("#EEEEEE"));
    }
}

