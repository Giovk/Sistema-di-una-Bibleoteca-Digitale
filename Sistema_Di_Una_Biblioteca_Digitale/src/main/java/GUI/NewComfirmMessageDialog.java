package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class NewComfirmMessageDialog extends JDialog {
    private JPanel contentPane;
    private JLabel typeLabel;
    private JLabel typeMessageLabel;
    private JButton okButton;
    private JButton noButton;
    private int value = 0;

    public NewComfirmMessageDialog() {

    }

    public int comfirmDialog(String message){
        this.setUndecorated(true);
        this.setSize(150,100);
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));

        ImageIcon attentionIco = new ImageIcon(this.getClass().getResource("/attention.png"));
        Image attentionImg = attentionIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        attentionIco = new ImageIcon(attentionImg);
        typeLabel.setIcon(attentionIco);

        typeMessageLabel.setText(message);
        okButton.setText("Si");


        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                okButton.setBackground(Color.decode("#cf9e29"));
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                okButton.setBackground(Color.decode("#FFD369"));
            }
        });


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onSI();
            }
        });

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                noButton.setBackground(Color.decode("#cf9e29"));
            }
        });

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                noButton.setBackground(Color.decode("#FFD369"));
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onNO();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                value = onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);

        return value;
    }
    private int onSI(){
            dispose();
            return 1;
            }

    private int onNO(){
            dispose();
            return 2;
            }

    private int onCancel() {
            // add your code here if necessary
            dispose();
            return 0;
            }
}
