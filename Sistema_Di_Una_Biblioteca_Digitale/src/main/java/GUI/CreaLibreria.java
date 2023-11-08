package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CreaLibreria {
    public JFrame frame;
    private JPanel contentPane;
    private JLabel closeBT;
    private JLabel notificheLabelText;
    private JButton aggiungiButton;
    private JTextField nomeField;
    private JTextField ntField;
    private JTextField swField;
    private JTextField viaField;
    private JTextField ncField;
    private JTextField comuneField;
    private JTextField provinciaField;
    private JTextField capField;
    private JTextField nazioneField;
    public CreaLibreria(JFrame frameC, Controller controller, DefaultTableModel model){
        frame = new JFrame("Crea Libreria");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 430);
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                frameC.toFront();
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                frameC.toFront();
            }
        });

        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nomeField.getText().isBlank() || ntField.getText().isBlank()){
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori!");
                } else {
                    if (controller.verificaNumeroTelefonicoLibreria(ntField.getText()) == false) {
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il numero telefonico è errato!");
                    } else {
                        if (controller.presenzaNumeroTelefonicoLibreria(ntField.getText()) == false) {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il numero telefonico è già presente!");
                        } else {

                            String indirizzo = "";

                            if (!viaField.getText().isBlank() && !ncField.getText().isBlank() && !comuneField.getText().isBlank() && !capField.getText().isBlank() && !nazioneField.getText().isBlank()) {
                                if (provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)"))
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();
                                else
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();
                            } else indirizzo = "";

                            if (indirizzo.isBlank() && swField.getText().isBlank()) {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Almeno uno dei campi tra indirizzo o sito web deve essere compilato!");
                            } else {

                                if (controller.presenzaLibreria(nomeField.getText().replace("'", "’"), swField.getText(), indirizzo.replace("'", "’")) == false) {
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libreria è gia presente!");
                                } else {
                                    controller.addLibreria(nomeField.getText().replace("'", "’"), ntField.getText(), swField.getText(), indirizzo.replace("'", "’"));

                                    model.setRowCount(0);
                                    if (controller.librerieUtente != null) {
                                        for (int i = 0; i < controller.librerieUtente.size(); i++) {
                                            model.addRow(new Object[]{controller.librerieUtente.get(i).nome, controller.librerieUtente.get(i).numeroTelefonico, controller.librerieUtente.get(i).sitoWeb, controller.librerieUtente.get(i).indirizzo, controller.librerieUtente.get(i).sitoWeb});
                                        }
                                    }

                                    frame.setVisible(false);
                                    frameC.setEnabled(true);
                                    frame.dispose();
                                    frameC.toFront();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);

        nomeField = new JTextField();
        nomeField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        ntField = new JTextField();
        ntField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        swField = new JTextField();
        swField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        viaField = new JTextField();
        viaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        ncField = new JTextField();
        ncField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        comuneField = new JTextField();
        comuneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        provinciaField = new JTextField();
        provinciaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        capField = new JTextField();
        capField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        nazioneField = new JTextField();
        nazioneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));


    }
}
