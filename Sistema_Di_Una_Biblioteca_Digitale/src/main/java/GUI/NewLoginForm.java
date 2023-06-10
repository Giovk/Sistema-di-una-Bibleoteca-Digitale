package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewLoginForm extends JDialog {
    public static JFrame frame;
    private JPanel contentPane;
    private JLabel imageLabel;
    private JLabel closeBT;
    private JPanel dxPanel;
    private JButton accediButton;
    private JTextField usernameTF;
    private JTextField nomeTF;
    private JPasswordField passwordF1;
    private JTextField passwordF2;
    private JButton regButton;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel emailLabel;
    private JLabel usernameLabel;
    private JTextField emailTF;
    private JTextField cognomeTF;
    private JLabel passwordLabel1;
    private JLabel ripetiPasswordLabel;
    private JPasswordField passwordF3;
    private JLabel dataDiNascitaLabel;
    private JLabel partitaIvaLabel;
    private JTextField partitaIVATF;
    private JLabel showPass;
    private JLabel hidePass;
    private JPanel regPanel3;
    private JPanel regPanel2;
    private JButton registratiButton;
    private JPanel sxPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPanel1;
    private JPanel registerPanel;
    private JLabel indicazioniLabel;
    private JLabel passwordLabel2;
    private JScrollPane scrollPanel2;
    private JPanel accediPanel;
    private JPanel regPanel;
    private JPanel accPanel;
    private JTextField usernameTF2;
    private JPasswordField passwordTF4;
    private JLabel usernameLabel2;
    private JLabel passwordLabel4;
    private JLabel passwordLabel5;
    private JTextField passwordTF5;
    private JLabel showPass2;
    private JLabel hidePass2;
    private JButton loginButton;
    private ImageIcon imagine;
    private ImageIcon closeImg;
    public int menuAcc;

    public NewLoginForm() {

        menuAcc = 0;


        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                dispose();
            }
        });


        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                regButton.setIcon(regBTico);
                regButton.setForeground(Color.decode("#D6D4D4"));
            }
        });
        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                regButton.setIcon(regBTico);
                regButton.setForeground(Color.decode("#EEEEEE"));
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type4.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                loginButton.setIcon(regBTico);
                loginButton.setForeground(Color.decode("#D6D4D4"));
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
                Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
                regBTico = new ImageIcon(regBTimg);
                loginButton.setIcon(regBTico);
                loginButton.setForeground(Color.decode("#EEEEEE"));
            }
        });





        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass2.setIcon(showPico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass2.setIcon(hidePico);
            }
        });
        showPass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF4.setVisible(false);
                showPass2.setVisible(false);
                passwordLabel4.setVisible(false);
                passwordLabel5.setVisible(true);
                passwordTF5.setVisible(true);
                hidePass2.setVisible(true);
                passwordTF5.setText(new String(passwordTF4.getPassword()));
            }
        });
        hidePass2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordTF5.setVisible(false);
                hidePass2.setVisible(false);
                passwordTF4.setVisible(true);
                showPass2.setVisible(true);
                passwordTF4.setText(passwordTF5.getText());
                passwordLabel4.setVisible(true);
                passwordLabel5.setVisible(false);
            }
        });



        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                showPico = new ImageIcon(showPimg);
                showPass.setIcon(showPico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e1.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
                Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                hidePico = new ImageIcon(hidePimg);
                hidePass.setIcon(hidePico);
            }
        });
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF1.setVisible(false);
                showPass.setVisible(false);
                passwordLabel1.setVisible(false);
                passwordLabel2.setVisible(true);
                passwordF2.setVisible(true);
                hidePass.setVisible(true);
                passwordF2.setText(new String(passwordF1.getPassword()));
            }
        });
        hidePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordF2.setVisible(false);
                hidePass.setVisible(false);
                passwordF1.setVisible(true);
                showPass.setVisible(true);
                passwordF1.setText(passwordF2.getText());
                passwordLabel1.setVisible(true);
                passwordLabel2.setVisible(false);

            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 1;
                regPanel.setVisible(false);
                accPanel.setVisible(true);
                accediButton.setBackground(Color.decode("#cf9e29"));
                registratiButton.setBackground(Color.decode("#FFD369"));
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 0;
                accPanel.setVisible(false);
                regPanel.setVisible(true);
                accediButton.setBackground(Color.decode("#FFD369"));
                registratiButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                accediButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(menuAcc == 0) accediButton.setBackground(Color.decode("#FFD369"));
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registratiButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if(menuAcc == 1) registratiButton.setBackground(Color.decode("#FFD369"));
            }
        });
        accediButton.addFocusListener(new FocusAdapter() {
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("NewLoginForm");
        frame.setUndecorated(true);
        frame.setContentPane(new NewLoginForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imagine = new ImageIcon(this.getClass().getResource("/b.png"));
        Image imagine2 = imagine.getImage().getScaledInstance(340,94, Image.SCALE_SMOOTH);
        imagine = new ImageIcon(imagine2);
        imageLabel = new JLabel(imagine);

        ImageIcon regBTico = new ImageIcon(this.getClass().getResource("/button-type3.png"));
        Image regBTimg = regBTico.getImage().getScaledInstance(100, 20, Image.SCALE_SMOOTH);
        regBTico = new ImageIcon(regBTimg);
        regButton = new JButton(regBTico);
        loginButton = new JButton(regBTico);

        ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
        Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        showPico = new ImageIcon(showPimg);
        showPass = new JLabel(showPico);
        showPass2 = new JLabel(showPico);

        ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
        Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        hidePico = new ImageIcon(hidePimg);
        hidePass = new JLabel(hidePico);
        hidePass2 = new JLabel(hidePico);



        emailTF = new JTextField();
        emailTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        nomeTF = new JTextField();
        nomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        cognomeTF = new JTextField();
        cognomeTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        usernameTF = new JTextField();
        usernameTF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF1 = new JPasswordField();
        passwordF1.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF2 = new JTextField();
        passwordF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordF3= new JPasswordField();
        passwordF3.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        partitaIVATF = new JTextField();
        partitaIVATF.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));

        usernameTF2 = new JTextField();
        usernameTF2.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordTF4 = new JPasswordField();
        passwordTF4.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        passwordTF5 = new JTextField();
        passwordTF5.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));

        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);
    }
}
