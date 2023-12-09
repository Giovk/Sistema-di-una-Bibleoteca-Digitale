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
import java.sql.Date;

public class   CreaPresentazione {
    public JFrame frame;
    private JPanel contentPanel;
    private JPanel closePanel;
    private JLabel closeBT;
    private JLabel presentazioneLabel;
    private JTextField strutturaField;
    private JTextField viaField;
    private JTextField comuneField;
    private JTextField capField;
    private JTextField ncField;
    private JTextField provinciaField;
    private JTextField nazioneField;
    private JButton aggiungiButton;
    private JLabel calendarIMG;
    private JTextField dataField;
    private JSpinner hourSpinner;
    private JSpinner minutesSpinner;
    private JLabel separatorLabel;
    private JLabel strutturaLabel;
    private JLabel indirizzoLabel;
    private JLabel viaLabel;
    private JLabel comuneLabel;
    private JLabel capLabel;
    private JLabel numeroCivicoLabel;
    private JLabel provinciaLabel;
    private JLabel nazioneLabel;
    private JLabel dataLabel;
    private JLabel oraLabel;
    private DatePickerMoreDay datePicker;

    public CreaPresentazione(JFrame frameC, Controller controller, DefaultTableModel model2){
        datePicker = new DatePickerMoreDay(calendarIMG);

        presentazioneLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'presentazioneLabel'
        indirizzoLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'indirizzoLabel'
        strutturaLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'strutturaLabel'
        strutturaField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'strutturaField'
        strutturaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'strutturaField'
        viaLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'viaLabel'
        viaField.setFont(controller.textFieldFont); //imposta il font del JTextField 'viaLabel'
        viaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'viaField'
        numeroCivicoLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'numeroCivicoLabel'
        ncField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'ncLabel'
        ncField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JTextField 'ncField'
        comuneLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'comuneLabel'
        comuneField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'comuneLabel'
        comuneField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JTextField 'comuneField'
        provinciaLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'provinciaLabel'
        provinciaField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'provinciaLabel'
        provinciaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'provinciaField'
        capLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'capLabel'
        capField.setFont(controller.textFieldFont); //imposta il font del JTextField 'capLabel'
        capField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'capField'
        nazioneLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'nazioneLabel'
        nazioneField.setFont(controller.textFieldFont); //imposta il font del JTextField 'nazioneLabel'
        nazioneField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'nazioneField'
        dataLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'dataLabel'
        dataField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'dataLabel'
        dataField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'dataField'
        oraLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'oraLabel'
        separatorLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'separatorLabel'
        hourSpinner.setFont(controller.textFieldFont);  //imposta il font dello JSpinner 'hourSpinner'
        minutesSpinner.setFont(controller.textFieldFont);   //imposta il font dello JSpinner 'minutesSpinner'

        aggiungiButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'aggiungiButton'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/1.7777), (int) (controller.screenHeight/1.5));  //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));   //imposta il bordo del JPanel 'contentPanel'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        calendarIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                datePicker.d.setVisible(true);  //rende visibile il calendario
                dataField.setText(datePicker.setPickedDate());  //imposta il testo del JTextField 'textField6' con la data scelta dall'utente
            }
        });

        hourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)hourSpinner.getValue() == 24) { //controlla se il valore dello JSpinner 'hourSpinner' è uguale a 24
                    hourSpinner.setValue(0);    //imposta il valore dello JSpinner 'hourSpinner' a 0
                }

                if((int)hourSpinner.getValue() < 0) {   //controlla se il valore dello JSpinner 'hourSpinner' è minore di 0
                    hourSpinner.setValue(23);   //imposta il valore dello JSpinner 'hourSpinner' di 23
                }
            }
        });

        minutesSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)minutesSpinner.getValue() == 60) {  //controlla se il valore dello JSpinner 'minutesSpinner' è uguale a 60
                    minutesSpinner.setValue(0); //imposta il valore dello JSpinner 'minutesSpinner' a 0
                    hourSpinner.setValue((int) hourSpinner.getValue() + 1); //incrementa di 1 il valore dello JSpinner 'hourSpinner'
                }

                if((int)minutesSpinner.getValue() < 0) {    //controlla se il valore dello JSpinner 'minutesSpinner' è minore di 0
                    minutesSpinner.setValue(59);    //imposta il valore dello JSpinner 'minutesSpinner' a 59
                    hourSpinner.setValue((int) hourSpinner.getValue() - 1); //decrementa di 1 il valore dello JSpinner 'hourSpinner'
                }
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aggiungiButton.setBackground(Color.decode("#cf9e29"));  //imposta il colore dello sfondo del JButton 'aggiungiButton'
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                aggiungiButton.setBackground(Color.decode("#FFD369"));  //imposta il colore dello sfondo del JButton 'aggiungiButton'
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                String orario = ""; //orario della presentazione

                if ((int) hourSpinner.getValue() < 10){ //controlla se il valore dello JSpinner 'hourSpinner' è minore di 10
                    orario += "0" + hourSpinner.getValue().toString() + ":";    //inizializza 'orario' con l'ora dell'orario della presentazione
                } else{
                    orario += hourSpinner.getValue().toString() + ":";  //inizializza 'orario' con l'ora dell'orario della presentazione
                }

                if ((int) minutesSpinner.getValue() < 10){  //controlla se il valore dello JSpinner 'minutesSpinner' è minore di 10
                    orario += "0" + minutesSpinner.getValue().toString();   //aggiunge a 'orario' i minuti dell'orario della presentazione
                } else{
                    orario += minutesSpinner.getValue().toString(); //aggiunge a 'orario' i minuti dell'orario della presentazione
                }

                if (strutturaField.getText().isBlank() || viaField.getText().isBlank() || ncField.getText().isBlank() || comuneField.getText().isBlank() || capField.getText().isBlank() || nazioneField.getText().isBlank() || dataField.getText().isBlank()){ //controlla se non è stato inserito uno dei campi obbligatori
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");   //mostra un messaggio di errore
                } else {
                    if (controller.getDataLibro().after(Date.valueOf(dataField.getText())) == true){    //controlla se il libro è stato pubblicato dopo alla presentazione
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo libro è stato pubblicato dopo!"); //mostra un messaggio di errore
                    } else {
                        String indirizzo = "";  //inidirizzo della resentazione

                        if(provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)")){   //controlla se non è stata inserita la provincia
                            indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();    //inserisce in 'indirizzo' l'indirizzo inserito
                        } else{
                            indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();  //inserisce in 'indirizzo' l'indirizzo inserito
                        }

                        if(controller.addPresentazione(strutturaField.getText().replace("'", "’"), indirizzo.replace("'", "’"), dataField.getText(), orario) == false) {    //controlla se questa struttura gia è occupata per un'altra presentazione
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa struttura è gia occupata!");  //mostra un messaggio di errore
                        } else {
                            controller.getPresentazione();  //inizializza 'controller.listaPresentazioni'

                            model2.setRowCount(0);  //rimuove tutte le righe della tabella

                            for(int i = 0; i < controller.listaPresentazioni.size(); i++){  //scorre 'controller.listaPresentazioni'
                                model2.addRow(new Object[]{controller.listaPresentazioni.get(i).luogo, controller.listaPresentazioni.get(i).struttura, controller.listaPresentazioni.get(i).data, controller.listaPresentazioni.get(i).ora});   //aggiunge una nuova riga nella tabella
                            }

                            frame.setVisible(false);    //rende invisibile il frame
                            frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                            frame.dispose();
                            frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                        }
                    }

                }
            }
        });
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

        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));   //carica l'immagine nel percorso /Calendar2.png
        Image calendarIm = calendarIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

        calendarIco = new ImageIcon(calendarIm);    //reinizializza l'ImageIcon 'calendarIco' con l'Image 'calendarIm'
        calendarIMG = new JLabel(calendarIco);  //inizializza la JLabel 'calendarIMG' dell'icona del calendario

        strutturaField = new JTextField();  //inizializza il JTextField 'strutturaField'
        strutturaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'strutturaField'

        viaField = new JTextField();    //inizializza il JTextField 'viaField'
        viaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'viaField'

        ncField = new JTextField(); //inizializza il JTextField 'ncField'
        ncField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'ncField'

        comuneField = new JTextField(); //inizializza il JTextField 'comuneField'
        comuneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'comuneField'

        provinciaField = new JTextField();  //inizializza il JTextField 'provinciaField'
        provinciaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'provinciaField'

        capField = new JTextField();    //inizializza il JTextField 'capField'
        capField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'capField'

        nazioneField = new JTextField();    //inizializza il JTextField 'nazioneField'
        nazioneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'nazioneField'

        dataField = new JTextField();   //inizializza il JTextField 'dataField'
        dataField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));   //imposta il colore del bordo del JTextField 'dataField'

        SpinnerNumberModel snm1 = new SpinnerNumberModel(00, -1, 24, 1);    //inizializza il model dello JSpinner

        hourSpinner = new JSpinner(snm1);   //inizializza lo JSpinner 'hourSpinner' con model 'snm1'

        hourSpinner.setBorder(new LineBorder(new Color(0xFFD369))); //imposta il colore del bordo di 'hourSpinner'

        JComponent editor = hourSpinner.getEditor();    //editor di 'hourSpinner' per personalizzarlo

        if (editor instanceof JSpinner.DefaultEditor) { //controlla se 'editor' è un JSpinner.DefaultEditor (editor di default degli JSpinner)
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();    //JtextField di 'hourSpinner'

            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setBorder(new LineBorder(new Color(0x222831)));   //imposta il colore del bordo di 'quantitaLibroSpinner'
        }

        hourSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), (screenHeight/48));   //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getPreviousValue()); //imposta il valore dello JSpinner 'spinner' con il suo valore precedente
                    }
                });

                button.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JButton 'button'
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JButton 'button'
                return button;
            }

            @Override
            protected Component createNextButton() {
                JButton button = new JButton(new ImageIcon(uA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), (screenHeight/48));   //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getNextValue()); //imposta il valore dello JSpinner 'spinner' con il suo valore successivo
                    }
                });

                button.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JButton 'button'
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JButton 'button'
                return button;
            }
        });

        SpinnerNumberModel snm2 = new SpinnerNumberModel(00, -1, 60, 1);    //inizializza il model dello JSpinner
        minutesSpinner = new JSpinner(snm2);    //inizializza lo JSpinner 'minutesSpinner' con model 'snm2'

        minutesSpinner.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo di 'minutesSpinner'

        JComponent editor2 = minutesSpinner.getEditor();    //editor di 'hourSpinner' per personalizzarlo

        if (editor2 instanceof JSpinner.DefaultEditor) {    //controlla se 'editor2' è un JSpinner.DefaultEditor (editor di default degli JSpinner)
            JTextField textField = ((JSpinner.DefaultEditor) editor2).getTextField();   //JtextField di 'minutesSpinner'

            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setBorder(new LineBorder(new Color(0x222831)));   //imposta il colore del bordo di 'quantitaLibroSpinner'
        }

        minutesSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);   //imposta le dimensioni dell'immagine

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), (screenHeight/48));   //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getPreviousValue()); //imposta il valore dello JSpinner 'spinner' con il suo valore precedente
                    }
                });

                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JButton 'button'
                return button;
            }

            @Override
            protected Component createNextButton() {
                JButton button = new JButton(new ImageIcon(uA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.3333), (screenHeight/48));   //inizializza le dimensioni del JButton 'button'
                    }
                };

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getNextValue()); //imposta il valore dello JSpinner 'spinner' con il suo valore successivo
                    }
                });
                
                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JButton 'button'
                return button;
            }
        });
    }
}