package GUI;

import com.toedter.calendar.JDateChooser;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class LSDialog extends JDialog {
    private JFrame frame;
    private JPanel contentPane;
    private JTabbedPane LSTabbedPane;
    private JPanel registerJPanel;
    private JPanel loginJPanel;
    private JTextField regEmailField;
    private JTextField regNameField;
    private JTextField regSurnameField;
    private JPasswordField regPasswordField;
    private JPasswordField regPassword2Field;
    private JTextField regUsernameField;
    private JTextField regPartitaIVAField;
    private JButton btRegistrati;
    private JPasswordField logPasswordField;
    private JButton btAccedi;
    private JTextField LogUserEmailField;
    private JPanel datePicker;
    private JLabel regError;
    private JPanel lsdialogPanel;
    JDateChooser jdc = new JDateChooser();

    public LSDialog(int n, JFrame frameC, Controller controller) {
        jdc.setDateFormatString("yyyy-MM-dd");
        datePicker.add(jdc);
        regError.setVisible(false);
        frame = new JFrame("Login/register");
        frame.setUndecorated(false);
        frame.setContentPane(this.contentPane);
        LSTabbedPane.setSelectedIndex(n);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameC.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        btRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailU = regEmailField.getText();
                String nomeU = regNameField.getText();
                String cognomeU = regSurnameField.getText();
                String usernameU = regUsernameField.getText();
                char[] password1U = regPasswordField.getPassword();
                char[] password2U = regPassword2Field.getPassword();
                String pass1 = "";
                String pass2 = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dt = sdf.format(jdc.getDate());
                String partitaIVA = regPartitaIVAField.getText();

                for (char c : password1U) {
                    pass1 = pass1 + c;
                }

                for (char c : password2U) {
                    pass2 = pass2 + c;
                }


                System.out.println(emailU + "\n" + nomeU + "\n" + cognomeU + "\n" + usernameU + "\n" + dt + "\n" + partitaIVA);
                if (pass1.equals(pass2) == false) {
                    regError.setText("Le password non coincidono");
                    regError.setVisible(true);
                    frame.setResizable(true);
                } else {
                    System.out.println(pass1);
                    JOptionPane.showMessageDialog(frame, "Registrazione Completata.");
                    controller.aggiungiUtente(emailU,nomeU, cognomeU, usernameU, pass1, dt, partitaIVA);
                    frameC.setEnabled(true);
                    HomePage hp = new HomePage(frameC, controller);
                    frameC.setVisible(false);
                    hp.frame.setVisible(true);
                    frame.dispose();
                }
            }
        });
    }
}

