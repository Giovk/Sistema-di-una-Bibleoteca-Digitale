package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewShowMessageDialog extends JDialog {
    private JPanel contentPane;
    private JLabel typeLabel;
    private JLabel typeMessageLabel;
    private JButton okButton;
    private int value = 0;

    public NewShowMessageDialog(int typeIcon, String message) {
        this.setUndecorated(true);
        this.setSize(150,100);
        this.setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));

        switch (typeIcon){
            case 1:
                ImageIcon doneIco = new ImageIcon(this.getClass().getResource("/done.png"));
                Image doneImg = doneIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                doneIco = new ImageIcon(doneImg);
                typeLabel.setIcon(doneIco);

                break;
            case 2:
                ImageIcon errorIco = new ImageIcon(this.getClass().getResource("/error.png"));
                Image errorImg = errorIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                errorIco = new ImageIcon(errorImg);
                typeLabel.setIcon(errorIco);

                break;
            default:
                typeLabel.setText("Image Not Found Or Not Selected");
        }

        typeMessageLabel.setText(message);


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
                onOK();
            }
        });

        /*buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });*/

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
