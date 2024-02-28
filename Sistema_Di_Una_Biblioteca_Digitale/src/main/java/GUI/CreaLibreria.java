package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe CreaLibreria implemeta l'interfaccia grafica del form che permette di creare una nuova libreria gestita dall'utente che ha effettuato
 * l'accesso.
 */
public class CreaLibreria {
    /**
     * Frame che si sta visualizzando.
     */
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

    /**
     * Istanzia un nuovo CreaLibreria.
     *
     * @param frameC     il frame chiamante
     * @param controller il controller
     * @param model      il model della tabella
     */
    public CreaLibreria(JFrame frameC, Controller controller, DefaultTableModel model){
        notificheLabelText.setFont(controller.impactFontSize);  //imposta il font della JLabel 'notificheLabelText'
        nomeLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'nomeLabel'
        numeroTelefonicoLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'numeroTelefonicoLabel'
        sitoWebLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'sitoWebLabel'
        indirizzoLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'indirizzoLabel'
        viaLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'viaLabel'
        numeroCivicoLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'numeroCivicoLabel'
        comuneLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'comuneLabel'
        provinciaLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'provinciaLabel'
        nazioneLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'nazioneLabel'
        capLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'capLabel'

        nomeField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'nomeField'
        ntField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'ntField'
        swField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'swField'
        viaField.setFont(controller.textFieldFont); //imposta il font del JTextField 'viaField'
        viaField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1)); //imposta la dimensione minima del JTextField 'viaField'
        ncField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'ncField'
        ncField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));  //imposta la dimensione minima del JTextField 'ncField'
        comuneField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'comuneField'
        comuneField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));  //imposta la dimensione minima del JTextField 'comuneField'
        provinciaField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'provinciaField'
        provinciaField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1));   //imposta la dimensione minima del JTextField 'provinciaField'
        capField.setFont(controller.textFieldFont); //imposta il font del JTextField 'capField'
        capField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1)); //imposta la dimensione minima del JTextField 'capField'
        nazioneField.setFont(controller.textFieldFont); //imposta il font del JTextField 'nazioneField'
        nazioneField.setMinimumSize(new Dimension((int)(controller.screenWidth/12.8), -1)); //imposta la dimensione minima del JTextField 'nazioneField'

        aggiungiButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'aggiungiButton'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension((int)(controller.screenWidth/1.7777), (int)(controller.screenHeight/1.6744)));  //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'
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

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                aggiungiButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'aggiungiButton'
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aggiungiButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'aggiungiButton'
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(nomeField.getText().isBlank() || ntField.getText().isBlank()){   //controlla se non è stato inserito uno dei campi obbligatori
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");  //mostra un messaggio di errore
                } else {
                    if (controller.verificaNumeroTelefonicoLibreria(ntField.getText()) == false) {  //controlla se il formato del numero telefonico è errato
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il numero telefonico è errato!");    //mostra un messaggio di errore
                    } else {
                        if (controller.presenzaNumeroTelefonicoLibreria(ntField.getText()) == false) {  //controlla se il numero telefonico è stato già registrato
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Il numero telefonico è già presente");   //mostra un messaggio di errore
                        } else {
                            String indirizzo = "";  //inidirizzo della libreria

                            if (viaField.getText().isBlank() || ncField.getText().isBlank() || comuneField.getText().isBlank() || capField.getText().isBlank() || nazioneField.getText().isBlank()) {   //controlla se non è stato inserito uno dei campi dell'indirizzo
                                indirizzo = ""; //svuota l'indirizzo
                            } else {
                                if (provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)")) { //controlla se non è stata inserita la provincia
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();    //inserisce in 'indirizzo' l'indirizzo inserito
                                }else {
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();  //inserisce in 'indirizzo' l'indirizzo inserito
                                }
                            }

                            if (indirizzo.isBlank() && swField.getText().isBlank()) {   //controlla se l'indirizzo e il sito web non sono stati inseriti
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire l'indirizzo e/o il sito web");  //mostra un messaggio di errore
                            } else {
                                if (controller.presenzaLibreria(nomeField.getText().replace("'", "’"), swField.getText(), indirizzo.replace("'", "’")) == false) {  //controlla se la libreria è stata già registrata
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libreria è gia presente!");   //mostra un messaggio di errore
                                } else {
                                    controller.addLibreria(nomeField.getText().replace("'", "’"), ntField.getText(), swField.getText(), indirizzo.replace("'", "’"));   //aggiunge la nuova libreria

                                    model.setRowCount(0);   //rimuove tutte le righe della tabella

                                    if (controller.librerieUtente != null) {    //controlla se l'utente ha delle librerie
                                        for (int i = 0; i < controller.librerieUtente.size(); i++) {    //scorre 'controller.librerieUtente'
                                            model.addRow(new Object[]{controller.librerieUtente.get(i).getNome(), controller.librerieUtente.get(i).getNumeroTelefonico(), controller.librerieUtente.get(i).getSitoWeb(), controller.librerieUtente.get(i).getIndirizzo()});   //aggiunge una nuova riga nella tabella
                                        }
                                    }

                                    frame.setVisible(false);    //rende invisibile il frame
                                    frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                                    frame.dispose();
                                    frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        nomeField = new JTextField();    //inizializza il JTextField 'nomeField'
        nomeField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));   //imposta il colore del bordo del JTextField 'nomeField'

        ntField = new JTextField();  //inizializza il JTextField 'ntField'
        ntField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'ntField'

        swField = new JTextField();  //inizializza il JTextField 'swField'
        swField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'swField'

        viaField = new JTextField();     //inizializza il JTextField 'viaField'
        viaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'viaField'

        ncField = new JTextField();  //inizializza il JTextField 'ncField'
        ncField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'ncField'

        comuneField = new JTextField();  //inizializza il JTextField 'comuneField'
        comuneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'comuneField'

        provinciaField = new JTextField();   //inizializza il JTextField 'provinciaField'
        provinciaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'provinciaField'

        capField = new JTextField();     //inizializza il JTextField 'capField'
        capField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'capField'

        nazioneField = new JTextField();    //inizializza il JTextField 'nazioneField'
        nazioneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'nazioneField'
    }
}
