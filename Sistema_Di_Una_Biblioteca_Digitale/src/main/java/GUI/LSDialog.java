package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JSpinner giorno;
    private JSpinner mese;
    private JSpinner anno;
    private JTextField regPartitaIVAField;
    private JButton btRegistrati;
    private JPanel dataNascitaJPanel;
    private JPasswordField logPasswordField;
    private JButton btAccedi;
    private JTextField LogUserEmailField;

    public LSDialog(int n) {
        JFrame frame = new JFrame("Login/register");
        frame.setUndecorated(false);
        frame.setContentPane(this.contentPane);
        LSTabbedPane.setSelectedIndex(n);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
