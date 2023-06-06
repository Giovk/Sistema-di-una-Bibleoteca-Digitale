package GUI;

import javax.swing.*;
import java.awt.*;
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
    private JLabel passwordLabel;
    private JLabel ripetiPasswordLabel;
    private JPasswordField passwordF3;
    private JLabel dataDiNascitaLabel;
    private JLabel partitaIvaLabel;
    private JTextField partitaIVATF;
    private JLabel showPass;
    private JLabel hidePass;
    private JPanel regPanel3;
    private JPanel regPanel2;
    private JPanel regPanel1;
    private JButton registratiButton;
    private JPanel sxPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPanel;
    private JPanel registerPanel;
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

            }
        });
        accediButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 1;
                registerPanel.setVisible(false);
                //accediPanel.setVisible(true);
                accediButton.setBackground(Color.decode("#cf9e29"));
                registratiButton.setBackground(Color.decode("#FFD369"));
            }
        });

        registratiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                menuAcc = 0;
                //accediPanel.setVisible(false);
                registerPanel.setVisible(true);
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

        ImageIcon showPico = new ImageIcon(this.getClass().getResource("/e1.png"));
        Image showPimg = showPico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        showPico = new ImageIcon(showPimg);
        showPass = new JLabel(showPico);

        ImageIcon hidePico = new ImageIcon(this.getClass().getResource("/e2.png"));
        Image hidePimg = hidePico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        hidePico = new ImageIcon(hidePimg);
        hidePass = new JLabel(hidePico);



        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);
    }
}
