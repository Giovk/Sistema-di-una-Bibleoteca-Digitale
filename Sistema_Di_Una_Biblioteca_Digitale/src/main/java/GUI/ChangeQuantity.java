package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ChangeQuantity {
    public JFrame frame;
    private JPanel contentPane;
    private JLabel closeBT;
    private JSpinner quantitaSpinner;
    private JButton okButton;
    private JLabel quantitaLabel;

    public ChangeQuantity(JFrame frameC, Controller controller, DefaultTableModel model, String formato, int value){
        quantitaLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'quantitaLabel'
        quantitaSpinner.setFont(controller.textFieldFont);  //imposta il font dello JSpinner 'quantitaSpinner'
        okButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'okButton'


        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/4.2666), controller.screenHeight/9);    //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        quantitaSpinner.setValue(value);    //imposta il valore di 'quantitaSpinner' a 'valuea'

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                okButton.setBackground(Color.decode("#cf9e29"));    //imposta lo sfondo del JButton 'okButton'
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                okButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'okButton'
            }
        });


        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                onOk(model, formato, controller, value, frameC);
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                frameC.toFront();
            }
        });
    }

    public ChangeQuantity(JFrame frameC, Controller controller, DefaultTableModel model, String formato, int numero, int value){
        quantitaLabel.setFont(controller.baseFontSize);
        quantitaSpinner.setFont(controller.textFieldFont);
        okButton.setFont(controller.baseFontSize);

        frame = new JFrame("Quantita");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/4.2666), controller.screenHeight/9);
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

        quantitaSpinner.setValue(value);

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
                onOk(model, formato, controller, numero, value,frameC);
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
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

        quantitaSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaSpinner.getValue() > 9999) quantitaSpinner.setValue(9999);
                if((int)quantitaSpinner.getValue() < 0) quantitaSpinner.setValue(0);
            }
        });
    }

    private void onOk(DefaultTableModel model, String formato, Controller controller, int numero, int value, JFrame frameC){

        value = ((int) quantitaSpinner.getValue());

        controller.selezionaFascicolo(numero, controller.nome_selected.substring(0, controller.nome_selected.indexOf("N°")-1));

        controller.modQuantitaFascicolo(formato, value);

        model.setRowCount(0);

        controller.getPossessoLibreria();

        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {
            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {

                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});
                else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});
            }
        }

        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {
            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {

                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});
                else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});
            }
        }

        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) {
            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) {

                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});
                else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});
            }
        }

        frame.setVisible(false);
        frameC.setEnabled(true);
        frame.dispose();
        frameC.toFront();
    }
    private void onOk(DefaultTableModel model, String formato, Controller controller, int value, JFrame frameC){
        value = ((int) quantitaSpinner.getValue());

        controller.modQuantitaLibro(formato, value);

        model.setRowCount(0);

        controller.getPossessoLibreria();

        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {
            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {

                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});
                else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});
            }
        }

        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {
            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {

                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});
                else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});
            }
        }

        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) {
            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) {

                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});
                else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});
                else model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});
            }
        }

        frame.setVisible(false);
        frameC.setEnabled(true);
        frame.dispose();
        frameC.toFront();
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

        SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 9999, 1);
        quantitaSpinner = new JSpinner(snm);

        quantitaSpinner.setBorder(new LineBorder(new Color(0xFFD369)));

        JComponent editor = quantitaSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setForeground(new Color(0x222831));
            textField.setBackground(new Color(0xFFD369));
            textField.setBorder(new LineBorder(new Color(0x222831)));
        }

        quantitaSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), screenHeight/48);
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(spinner.getPreviousValue() != null) {
                            spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
                        }
                    }
                });

                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }

            @Override
            protected Component createNextButton() {
                JButton button = new JButton(new ImageIcon(uA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), screenHeight/48);
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(spinner.getNextValue() != null) {
                            spinner.setValue(spinner.getNextValue()); // Azione per decrementare il valore
                        }
                    }
                });

                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }
        });
    }
}
