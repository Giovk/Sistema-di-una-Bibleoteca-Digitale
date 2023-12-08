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
                onOk(model, formato, controller, value, frameC);    //aggiorna il contenuto della tabella e chiude la finestra
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
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //rende visibile il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });
    }

    public ChangeQuantity(JFrame frameC, Controller controller, DefaultTableModel model, String formato, int numero, int value){
        quantitaLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'quantitaLabel'
        quantitaSpinner.setFont(controller.textFieldFont);  //imposta il font dello JSpinner 'quantitaSpinner'
        okButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'okButton'

        frame = new JFrame("Quantita");
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


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOk(model, formato, controller, numero, value,frameC); //aggiorna il contenuto della tabella e chiude la finestra
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
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        quantitaSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaSpinner.getValue() > 9999) {    //controlla se il valore dello JSpinner 'quantitaSpinner' è maggiore di 9999
                    quantitaSpinner.setValue(9999); //imposta il valore di 'quantitaSpinner' a 9999
                }

                if((int)quantitaSpinner.getValue() < 0){    //controlla se il valore dello JSpinner 'quantitaSpinner' è minore di 0
                    quantitaSpinner.setValue(0);    //imposta il valore di 'quantitaSpinner' a 0
                }
            }
        });
    }

    private void onOk(DefaultTableModel model, String formato, Controller controller, int numero, int value, JFrame frameC){    //modifica la quantità disponibile del fascicolo selezionato e chiude la finestra

        value = ((int) quantitaSpinner.getValue()); //nuova quantità disponibile

        controller.selezionaFascicolo(numero, controller.nome_selected.substring(0, controller.nome_selected.indexOf("N°")-1)); //inizializza 'controller.fascicolo_selected' con il fascicolo selezionato

        controller.modQuantitaFascicolo(formato, value);    //modifica la quantità del fascicolo selezionato 'controller.fascicolo_selected'

        model.setRowCount(0);   //rimuove tutte le righe della tabella

        controller.getPossessoLibreria();   //aggiorna 'controller.possessoLLibreria', 'controller.possessoSLibreria' e 'controller.possessoFLibreria'

        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se 'controller.titoloLibriLibreria' e 'controller.possessoLLibreria' non sono a NULL
            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria selezionata
                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0){   //controlla se l'i-esimo libro non è disponibile
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se 'controller.titoloSerieLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria selezionata
                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0){   //controlla se l'i-esima serie non è disponibile
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se 'controller.fascicoliLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria selezionata
                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0){   //controlla se l'i-esimo fascicolo non è disponibile
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        frame.setVisible(false);    //rende invisibile il frame
        frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
        frame.dispose();
        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
    }//fine onOk

    private void onOk(DefaultTableModel model, String formato, Controller controller, int value, JFrame frameC){    //modifica la quantità disponibile del libro selezionato e chiude la finestra
        value = ((int) quantitaSpinner.getValue()); //nuova quantità disponibile

        controller.modQuantitaLibro(formato, value);    //modifica la quantità del libro selezionato con ISBN 'controller.isbn_selected'

        model.setRowCount(0);   //rimuove tutte le righe della tabella

        controller.getPossessoLibreria();   //inizializza 'controller.possessoLLibreria', 'controller.possessoSLibreria' e 'controller.possessoFLibreria'

        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se 'controller.titoloLibriLibreria' e 'controller.possessoLLibreria' non sono a NULL
            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria selezionata
                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0){   //controlla se l'i-esimo libro non è disponibile
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else{
                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se 'controller.titoloSerieLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria selezionata
                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0){   //controlla se l'i-esima serie non è disponibile
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else {
                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                }
            }
        }

        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se 'controller.fascicoliLibreria' e 'controller.possessoSLibreria' non sono a NULL
            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria selezionata
                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                } else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0){   //controlla se l'i-esimo fascicolo non è disponibile
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                } else {
                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                }
            }
        }

        frame.setVisible(false);    //rende invisibile il frame
        frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
        frame.dispose();
        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 9999, 1); //inizializza il model dello JSpinner

        quantitaSpinner = new JSpinner(snm);    //inizializza lo JSpinner 'quantitaSpinner' con model 'snm'

        quantitaSpinner.setBorder(new LineBorder(new Color(0xFFD369))); //imposta il colore del bordo di 'quantitaSpinner'

        JComponent editor = quantitaSpinner.getEditor();    //editor di 'quantitaSpinner' per personalizzarlo

        if (editor instanceof JSpinner.DefaultEditor) { //controlla se 'editor' è un JSpinner.DefaultEditor (editor di default degli JSpinner)
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();    //JtextField di 'quantitaLibroSpinner'

            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setBorder(new LineBorder(new Color(0x222831)));   //imposta il colore del bordo di 'quantitaSpinner'
        }

        quantitaSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), screenHeight/48); //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(spinner.getPreviousValue() != null) {    //controlla se il valore precedente dello JSpinner è 'spinner' è diverso da null
                            spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
                        }
                    }
                });

                button.setBackground(new Color(0x222831)); //imposta il colore di sfondo di 'button'
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo di 'button'
                return button;
            }

            @Override
            protected Component createNextButton() {
                JButton button = new JButton(new ImageIcon(uA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), screenHeight/48); //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(spinner.getNextValue() != null) {    //controlla se il valore successivo dello JSpinner è 'spinner' è diverso da NULL
                            spinner.setValue(spinner.getNextValue());   //imposta il valore dello JSpinner 'spinner' con il suo valore successivo
                        }
                    }
                });

                button.setBackground(new Color(0x222831));  //imposta il colore di sfondo di 'button'
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo di 'button'
                return button;
            }
        });
    }
}