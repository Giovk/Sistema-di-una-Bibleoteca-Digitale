package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe GestisciCollane implemeta l'interfaccia grafica del form che permette di creare delle nuove collane di libri e di mettere o togliere
 * il libro selezionato nelle collane.
 */
public class GestisciCollane {
    /**
     * Frame che si sta visualizzando.
     */
    public JFrame frame;
    private JPanel contentPane;
    private JPanel closePanel;
    private JLabel closeBT;
    private JButton creaCollanaButton;
    private JButton inviaButton;
    private JPanel collanePanel;
    private JPanel collaneEsistentiPanel;
    private JPanel collaneNuovePanel;
    private JScrollPane collaneScrollPane;
    private JButton annullaButton;
    private JTextField nomeField;
    private JTextField caratteristicaField;
    private JTextField issnField;
    private JLabel collanaLabel;
    private JLabel nomeLabel;
    private JLabel caratteristicaLabel;
    private JLabel issnLabel;
    private JLabel iCampiChePresentanoLabel;
    private int collaneLibroCount = 0;

    /**
     * Istanzia un nuovo GestisciCollane.
     *
     * @param frameC     il frame chiamante
     * @param controller il controller
     */
    public GestisciCollane(JFrame frameC, Controller controller){
        creaCollanaButton.setFont(controller.baseFontSize); //imposta il font del JButton del JButton 'creaCollanaButton'
        creaCollanaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/8.5333),-1));  //imposta la dimensione minima del JButton 'creaCollanaButton'
        annullaButton.setFont(controller.baseFontSize); //imposta il font del JButton 'annullaButton'
        annullaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/8.5333),-1));  //imposta la dimensione minima del JButton 'annullaButton'
        collanaLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'collanaLabel'
        nomeLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'nomeLabel'
        nomeField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'nomeField'
        caratteristicaLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'caratteristicaLabel'
        caratteristicaField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'caratteristicaField'
        issnLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'issnLabel'
        issnField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'issnField'
        iCampiChePresentanoLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'iCampiChePresentanoLabel'
        inviaButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'inviaButton'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/3.5555), (int) (controller.screenHeight/1.5));  //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPanel'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

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
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                creaCollanaButton.setBackground(Color.decode("#cf9e29"));   //imposta il colore dello sfondo del JButton 'creaCollanaButton'
            }
        });

        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (creaCollanaButton.isEnabled()){ //controlla se è stato abilitato il JButton 'creaCollanaButton'
                    creaCollanaButton.setBackground(Color.decode("#FFD369"));   //imposta il colore dello sfondo del JButton 'creaCollanaButton'
                }
            }
        });

        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                inviaButton.setVisible(true);   //rende visibile il JButton 'inviaButton'
                collaneEsistentiPanel.setVisible(false);    //rende invisibile il JPanel 'collaneEsistentiPanel'
                collaneNuovePanel.setVisible(true); //rende visibile il JPanel 'collaneNuovePanel'
                annullaButton.setVisible(true); //rende visibile il JButton 'annullaButton'
                creaCollanaButton.setEnabled(false);    //rende invisibile il JButton 'creaCollanaButton'
                contentPane.revalidate();   //aggiorna il contenuto del JPanel 'contentPane'
                contentPane.repaint();  //ridisegna il JPanel 'contentPane'
                creaCollanaButton.setBackground(Color.decode("#FFD369"));   //imposta il colore dello sfondo del JButton 'creaCollanaButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                annullaButton.setBackground(Color.decode("#cf9e29"));   //imposta il colore dello sfondo del JButton 'annullaButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                annullaButton.setBackground(Color.decode("#FFD369"));   //imposta il colore dello sfondo del JButton 'annullaButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                inviaButton.setVisible(false);  //rende invisibile il JButton 'inviaButton'
                collaneEsistentiPanel.setVisible(true); //rende invisibile il JPanel 'collaneEsistentiPanel'
                collaneNuovePanel.setVisible(false);    //rende invisibile il JPanel 'collaneNuovePanel'
                annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
                creaCollanaButton.setEnabled(true); //abilita il JButton 'creaCollanaButton'
                nomeField.setText("");  //svuota il testo del JTextField 'nomeField'
                issnField.setText("");  //svuota il testo del JTextField 'issnField'
                caratteristicaField.setText("");    //svuota il testo del JTextField 'caratteristicaField'
                contentPane.revalidate();   //aggiorna il contenuto del JPanel 'contentPane'
                contentPane.repaint();      //ridisegna il JPanel 'contentPane'
            }
        });

        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'inviaButton'
            }
        });

        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'inviaButton'
            }
        });

        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(!(controller.verificaISSN(issnField.getText())) && !(issnField.getText().isBlank())) {   //controlla se il formato dell'ISSN è errato
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "ISSN non valido");   //mostra un messaggio di errore
                }else{
                    if (caratteristicaField.getText().isBlank() || nomeField.getText().isBlank()) {  //controlla se non è stato inserito uno dei campi obbligatori
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obligatori.");   //mostra un messaggio di errore
                    } else {
                        if (controller.creaCollana(nomeField.getText().replace("'", "’"), caratteristicaField.getText().replace("'", "’"), issnField.getText()) == false) { //controlla se la collana già esiste
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa collana già esiste"); //mostra un messaggio di errore
                        } else {
                            inviaButton.setVisible(false);  //rende invisibile il JButton 'inviaButton'
                            collaneEsistentiPanel.setVisible(true); //rende invisibile il JPanel 'collaneEsistentiPanel'
                            collaneNuovePanel.setVisible(false);    //rende invisibile il JPanel 'collaneNuovePanel'
                            annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
                            creaCollanaButton.setEnabled(true); //abilita il JButton 'creaCollanaButton'
                            nomeField.setText("");  //svuota il testo del JTextField 'nomeField'
                            issnField.setText("");  //svuota il testo del JTextField 'issnField'
                            caratteristicaField.setText("");    //svuota il testo del JTextField 'caratteristicaField'

                            collaneEsistentiPanel.removeAll();  //elimina tutti i componenti del JPanel 'isbnSeriePanel'

                            for (int i = 0; i < controller.listaCollane.size(); i++) {  //scorre 'controller.listaCollane'
                                boolean presenza = false;   //segnala la presenza del libro selezionato nell'i-esima collana

                                for (int j = 0; j < controller.listaCollane.get(i).getLibri().size(); j++) { //scoore la lista dei libri dell'i-esima collana
                                    if (controller.listaCollane.get(i).getLibri().get(j).getISBN().equals(controller.isbn_selected)) {    //controlla il j-esimo libro dell'i-esima collana è il libro selezionato
                                        presenza = true;    //aggiorna 'presenza'
                                    }
                                }

                                collaneLibroCount++;    //incrementa 'collaneLibroCount'
                                initCollaneExist(controller.listaCollane.get(i).getNome(), presenza, controller);    //inizializza tutti i componenti necessari per gestire l'i-esima collana per il libro selezionato
                             }

                            contentPane.revalidate();   //aggiorna il contenuto del JPanel 'contentPane'
                            contentPane.repaint();  //ridisegna il JPanel 'contentPane'
                        }
                    }
                }
            }
        });

        inviaButton.setVisible(false);  //rende invisibile il JButton 'inviaButton'
        annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
        collaneNuovePanel.setVisible(false);    //rende invisibile il JPanel 'collaneNuovePanel'

        collaneScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);  //inizializza il colore della parte mobile della barra di scorrimento
                this.trackColor= new Color(0xFFD369);   //inizializza il colore della parte fissa della barra di scorrimento
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);    //inizializza il colore della parte più scura dell'ombra del lato inferiore della parte mobile della barra di scorrimento
                this.thumbLightShadowColor = new Color(0x323A48);   //inizializza il colore della parte piu chiara dell'ombra del lato superiore della parte mobile della barra di scorrimento
                this.thumbHighlightColor = new Color(0x323A48); //inizializza il colore della parte mobile della barra di scorrimento quando viene attivata
                this.trackHighlightColor = new Color(0xCF9E29); //inizializza il colore della parte fissa della barra di scorrimento quando viene attivata
                this.scrollBarWidth = (int)(controller.screenWidth/75); //imposta la larghezza della barra di scorrimento
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                    }
                };

                decreaseButton.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JButton 'decreaseButton'
                return decreaseButton;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton increaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                    }
                };

                increaseButton.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JButton 'increaseButton'
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;   //restituisce 'dA'
                    case SwingConstants.NORTH: return uA;   //restituisce 'uA'
                    case SwingConstants.EAST: return rA;    //restituisce 'rA'
                    default: return lA; //restituisce 'lA'
                }
            }
        });

        collaneScrollPane.setBackground(new Color(0x222831));   //imposta il colore dello sfondo del JScrollPane 'collaneScrollPane'
        collaneScrollPane.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JScrollPane 'collaneScrollPane'
        collaneScrollPane.getViewport().setBackground(new Color(0x222831)); //imposta il colore dello sfondo della parte visibile del JScrollPane 'collaneScrollPane'

        for (int i = 0; i < controller.listaCollane.size(); i++){    //scorre 'controller.listaCollane'
            boolean presenza = false;   //segnala la presenza del libro selezionato nell'i-esima collana

            for (int j = 0; j < controller.listaCollane.get(i).getLibri().size(); j++){  //scoore la lista dei libri dell'i-esima collana
                if (controller.listaCollane.get(i).getLibri().get(j).getISBN().equals(controller.isbn_selected)){ //controlla il j-esimo libro dell'i-esima collana è il libro selezionato
                    presenza = true;    //aggiorna 'presenza'
                }
            }

            collaneLibroCount++;    //incrementa 'collaneLibroCount'
            initCollaneExist(controller.listaCollane.get(i).getNome(), presenza, controller);    //inizializza tutti i componenti necessari per gestire l'i-esima collana per il libro selezionato
        }
    }

    private void initCollaneExist(String nomeCollana, boolean presenza, Controller controller) {    //inizializza tutti i componenti necessari per gestire la collana 'nomeCollana' per il libro selezionato
        JPanel panel1 = new JPanel();   //inizializza il JPanel 'panel1'
        JLabel label2 = new JLabel();   //inizializza il JPanel 'panel2'
        JButton button2 = new JButton();    //inizializza il JButton 'button2'

        {
            panel1.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JPanel 'panel1'
            panel1.setLayout(new GridBagLayout());  //imposta il layout del JPanel 'panel1'
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0}; //le larghezze delle colonne del layout di 'panel1' vengono impostate dinamicamente in base alle componenti contenute
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};  //le altezze delle righe del layout di 'panel1' vengono impostate dinamicamente in base alle componenti contenute
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};    //assegna le priorità delle colonne del layout di 'panel1' per impostare le loro larghezze
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};    //assegna le priorità delle righe del layout di 'panel1' impostare le loro altezze

            label2.setText(nomeCollana);    //imposta il testo della JLabel 'label2'
            label2.setFont(controller.baseFontSize);    //imposta il font testo di 'label2'
            label2.setMinimumSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));  //imposta la dimensione minima di 'label2'
            label2.setPreferredSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));    //imposta la dimensione preferita di 'label2'
            label2.setMaximumSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));  //imposta la dimensione massima di 'label2'
            label2.setForeground(new Color(0xeeeeee));  //imposta il colore del testo di 'label2'
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));   //inserisce 'label2' in 'panel1'

            if(presenza == true){   //controlla se la collana 'nomeCollana' contiene il libro selezionato
                button2.setText("-");   //imposta il testo del JButton 'button2'
            } else {
                button2.setText("+");   //imposta il testo del JButton 'button2'
            }

            button2.setMinimumSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));    //imposta la dimensione minima di 'button2'
            button2.setPreferredSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));  //imposta la dimensione preferita di 'button2'
            button2.setMaximumSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));    //imposta la dimensione massima di 'button2'
            button2.setFont(controller.baseFontSize);   //imposta il font testo di 'button2'
            button2.setBackground(new Color(0xffd369)); //imposta il colore dello sfondo di 'button2'
            button2.setForeground(new Color(0x222831)); //imposta il colore del testo di 'button2'
            button2.setBorderPainted(false);    //disabilita il bordo pulsante di 'button2'

            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button2.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'button2'
                }
            });

            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button2.setBackground(Color.decode("#FFD369"));//imposta il colore dello sfondo del JButton 'button2'
                }
            });

            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button2.getText().equals("-")){ //controlla se si sta eliminando il libro selezionato dalla collana 'nomeCollana'
                        controller.removeLibroFromCollana(nomeCollana); //rimuove il libro selezionato dalla collana 'nomeCollana'
                        button2.setText("+");   //imposta il testo del JButton 'button2'
                    } else {
                        controller.addLibroInCollana(nomeCollana);  //aggiunge il libro selezionato nella collana 'nomeCollana'
                        button2.setText("-");   //imposta il testo del JButton 'button2'
                    }
                }
            });

            panel1.add(button2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));  //inserisce 'button2' in 'panel1'
        }

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0; //imposta a 0 la colonna del layout in cui inserire 'panel1'
        constraints.gridy = (collaneLibroCount-1);  //imposta a collaneLibroCount-1 la riga del layout in cui inserire 'panel1'
        constraints.insets = new Insets(0, 0, 15, 0);   //inizializza i margini di 'panel1'
        collaneEsistentiPanel.add(panel1, constraints); //inserisce 'panel1' in 'collaneEsistentiPanel'
    }//fine initCollaneExist


    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        nomeField = new JTextField();   //inizializza il JTextField 'nomeField'
        nomeField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));   //imposta il colore del bordo del JTextField 'nomeField'

        issnField = new JTextField();   //inizializza il JTextField 'issnField'
        issnField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));   //imposta il colore del bordo del JTextField 'issnField'

        caratteristicaField = new JTextField(); //inizializza il JTextField 'caratteristicaField'
        caratteristicaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'caratteristicaField'

        collaneEsistentiPanel = new JPanel();   //inizializza il JPanel 'collaneEsistentiPanel'
        collaneEsistentiPanel.setLayout(new GridBagLayout());   //imposta il layout del JPanel 'collaneEsistentiPanel'
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).columnWidths = new int[] {0, 0}; //le larghezze delle colonne del layout di 'collaneEsistentiPanel' vengono impostate dinamicamente in base alle componenti contenute
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).rowHeights = new int[] {0, 0};   //le altezze delle righe del layout di 'collaneEsistentiPanel' vengono impostate dinamicamente in base alle componenti contenute
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};  //assegna le priorità delle colonne del layout di 'collaneEsistentiPanel' per impostare le loro larghezze
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).rowWeights = new double[] {0.0, 1.0E-4}; //assegna le priorità delle righe del layout di 'collaneEsistentiPanel' impostare le loro altezze
    }
}