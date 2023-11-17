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
    private JLabel nomeLabel;
    private JLabel numeroTelefonicoLabel;
    private JLabel sitoWebLabel;
    private JLabel indirizzoLabel;
    private JLabel viaLabel;
    private JLabel comuneLabel;
    private JLabel capLabel;
    private JLabel numeroCivicoLabel;
    private JLabel provinciaLabel;
    private JLabel nazioneLabel;

    public CreaLibreria(JFrame frameC, Controller controller, DefaultTableModel model){
        notificheLabelText.setFont(controller.impactFontSize);
        nomeLabel.setFont(controller.baseFontSize);
        numeroTelefonicoLabel.setFont(controller.baseFontSize);
        sitoWebLabel.setFont(controller.baseFontSize);
        indirizzoLabel.setFont(controller.baseFontSize);
        viaLabel.setFont(controller.baseFontSize);
        numeroCivicoLabel.setFont(controller.baseFontSize);
        comuneLabel.setFont(controller.baseFontSize);
        provinciaLabel.setFont(controller.baseFontSize);
        nazioneLabel.setFont(controller.baseFontSize);
        capLabel.setFont(controller.baseFontSize);

        nomeField.setFont(controller.textFieldFont);
        ntField.setFont(controller.textFieldFont);
        swField.setFont(controller.textFieldFont);
        viaField.setFont(controller.textFieldFont);
        viaField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));
        ncField.setFont(controller.textFieldFont);
        ncField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));
        comuneField.setFont(controller.textFieldFont);
        comuneField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));
        provinciaField.setFont(controller.textFieldFont);
        provinciaField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));
        capField.setFont(controller.textFieldFont);
        capField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));
        nazioneField.setFont(controller.textFieldFont);
        nazioneField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));

        aggiungiButton.setFont(controller.baseFontSize);

        frame = new JFrame("Crea Libreria");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension((int)(controller.screenWidth/1.7777), (int)(controller.screenHeight/1.6744)));
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

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                aggiungiButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'serieButton'
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aggiungiButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'fascicoliButton'
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
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
