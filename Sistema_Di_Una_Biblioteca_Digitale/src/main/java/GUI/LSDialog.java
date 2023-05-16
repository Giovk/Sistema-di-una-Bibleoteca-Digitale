package GUI;

import com.toedter.calendar.JDateChooser;
import Controller.Controller;

import javax.swing.*;
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
    private JTextField logUserEmailField;
    private JPanel datePicker;
    private JLabel regError;
    private JPanel lsdialogPanel;
    private JLabel fieldError;
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

        btAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = logUserEmailField.getText();
                char[] pass = logPasswordField.getPassword();
                String password = new String(pass);

                if(controller.validaUtente(userEmail, password) >= 1){
                    frameC.setEnabled(true);
                    HomePage hp = new HomePage(frameC, controller);
                    frameC.setVisible(false);
                    hp.frame.setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "L'Account non esiste.");
                }
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
                String pass1 = new String(password1U);
                String pass2 = new String(password2U);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dt = sdf.format(jdc.getDate());
                String partitaIVA = regPartitaIVAField.getText();

                System.out.println(emailU + "\n" + nomeU + "\n" + cognomeU + "\n" + usernameU + "\n" + dt + "\n" + partitaIVA);

                if(nomeU.isBlank() || cognomeU.isBlank() || usernameU.isBlank() || pass1.isBlank() || pass2.isBlank()) {
                    fieldError.setText("Compilare tutti i campi obbligatori");
                    fieldError.setVisible(true);
                    frame.setResizable(true);
                } else {
                    if (pass1.equals(pass2) == false) {
                        regError.setText("Le password non coincidono");
                        regError.setVisible(true);
                        frame.setResizable(true);
                    } else {
                        regError.setVisible(false);
                        System.out.println(pass1);
                        JOptionPane.showMessageDialog(frame, "Registrazione Completata.");
                        controller.aggiungiUtente(emailU, nomeU, cognomeU, usernameU, pass1, dt, partitaIVA);
                        frameC.setEnabled(true);
                        HomePage hp = new HomePage(frameC, controller);
                        frameC.setVisible(false);
                        hp.frame.setVisible(true);
                        frame.dispose();
                    }
                }
            }
        });
    }
}

