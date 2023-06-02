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
    private JPanel panel1;
    private JButton button1;
    private ImageIcon imagine;
    private ImageIcon closeImg;

    public NewLoginForm() {


        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                dispose();
            }
        });


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
                Image resbt1img = bt1igm.getImage().getScaledInstance(150,50, Image.SCALE_SMOOTH);
                bt1igm = new ImageIcon(resbt1img);
                button1.setIcon(bt1igm);
                button1.setForeground(Color.decode("#FFFFFF"));
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type2.png"));
                Image resbt1img = bt1igm.getImage().getScaledInstance(150,50, Image.SCALE_SMOOTH);
                bt1igm = new ImageIcon(resbt1img);
                button1.setIcon(bt1igm);
                button1.setForeground(Color.decode("#B8B6B6"));
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

        button1 = new JButton();
        button1.setContentAreaFilled(false);

        button1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1));
        button1.setMargin(new Insets(0,0,0,0));
        ImageIcon bt1igm = new ImageIcon(this.getClass().getResource("/button-type1.png"));
        Image resbt1img = bt1igm.getImage().getScaledInstance(150,50, Image.SCALE_SMOOTH);
        bt1igm = new ImageIcon(resbt1img);
        button1.setIcon(bt1igm);
        button1.setHorizontalTextPosition(JButton.CENTER);
        button1.setVerticalTextPosition(JButton.CENTER);
        button1.setForeground(Color.decode("#FFFFFF"));


        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);
    }
}
