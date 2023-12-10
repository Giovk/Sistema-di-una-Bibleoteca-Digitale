package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class IssuePage {
    public JFrame frame;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JTable table1;
    private JTable table2;
    private JCheckBox disponibilitaCheckBox;
    private JCheckBox articoliCheckBox;
    private JPanel jpanel;
    private JLabel informazioniLabel;
    private JScrollPane jscroll2;
    private JScrollPane jscroll1;
    private JButton serieButton;
    private JLabel valutazione;
    private JLabel stella1;
    private JLabel stella2;
    private JLabel stella3;
    private JLabel stella4;
    private JLabel stella5;
    private JLabel likeButton;
    private JLabel valutaButton;
    private JPanel allPagePanel;
    private JScrollPane allScrollPanel;
    private JPanel commenti;
    private JLabel backButton;
    private JButton fascicoliButton;
    private JLabel notificheLabel;
    private JLabel valLabel;
    private boolean active = false;
    ImageIcon favouriteVuotoIco;
    ImageIcon favouritePienoIco;
    private float valutazioneMedia;
    private String titolo;
    private int numero;
    private int numeroNotifiche;


    public IssuePage (JFrame frameC, Controller controller) {
        titolo = controller.fascicolo_selected.rivista.titolo;  //inizializza 'titolo' con il titolo della rivista del fascicolo selezionato
        numero = controller.fascicolo_selected.numero;  //inizializza 'numero' con il numero del fascicolo selezionato

        homeButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'homeButton'
        homeButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight())); //imposta la dimensione minima del JButton 'homeButton'
        libriButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'libriButton'
        libriButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'libriButton'
        fascicoliButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'fascicoliButton'
        fascicoliButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'fascicoliButton'
        serieButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'serieButton'
        serieButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));    //imposta la dimensione minima del JButton 'serieButton'
        utenteButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'utenteButton'
        utenteButton.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), homeButton.getHeight()));   //imposta la dimensione minima del JButton 'utenteButton'
        informazioniLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'informazioniLabel'

        valutaButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'valutaButton'

        disponibilitaCheckBox.setFont(controller.baseFontSize); //imposta il font del del JCheckBox 'disponibilitaCheckBox'
        articoliCheckBox.setFont(controller.baseFontSize);  //imposta il font del del JCheckBox 'articoliCheckBox'

        table1.setFont(controller.impactFontSize);  //imposta il font della JTable 'table1'
        table1.setRowMargin(controller.screenWidth/640);    //imposta il margine tra le celle della JTable 'table1'
        table1.setRowHeight(controller.screenHeight/36);    //imposta l'altezza delle righe della JTable 'table1'

        table2.setFont(controller.impactFontSize);  //imposta il font della JTable 'table2'
        table2.setRowMargin(controller.screenWidth/640);    //imposta il margine tra le celle della JTable 'table2'
        table2.setRowHeight(controller.screenHeight/36);    //imposta l'altezza delle righe della JTable 'table2'

        valLabel.setFont(controller.impactFontSize);    //imposta il font della JLabel 'valLabel'

        notificheLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'notificheLabel'

        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29)); //imposta il colore dello sfondo di un elemento di menu quando viene selezionato
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831)); //imposta il colore del testo di un elemento di menu quando viene selezionato
        allScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        jscroll1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        jscroll2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        JPopupMenu utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem utenteExit = new JMenuItem("Logout");//crea la voce del menu "Logout"
        utenteExit.setFont(controller.baseFontSize);    //imposta il font del JMenuItem 'utenteExit'
        utenteExit.setHorizontalTextPosition(SwingConstants.CENTER);    //centra orizzontalmente il testo del JMenuItem 'utenteExit'
        utenteExit.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteExit'
        utenteExit.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteExit'
        utenteExit.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteExit'
        utenteExit.setMinimumSize((new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)))); //imposta la dimensione minima del JMenuItem 'utenteExit'
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setFont(controller.baseFontSize); //imposta il font del JMenuItem 'utenteProfilo'
        utenteProfilo.setHorizontalTextPosition(SwingConstants.CENTER); //centra orizzontalmente il testo del JMenuItem 'utenteProfilo'
        utenteProfilo.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusPainted(false);   //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteProfilo'
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder()); //toglie il bordo del JMenuItem 'utenteProfilo'
        utenteProfilo.setFocusable(false);  //impedisce all'utente di interagire con il JMenuItem 'utenteProfilo' tramite tastiera
        utenteProfilo.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));    //imposta la dimensione minima del JMenuItem 'utenteProfilo'
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteLibrerie.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setFont(controller.baseFontSize);    //imposta il font del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setHorizontalTextPosition(SwingConstants.CENTER);    //centra orizzontalmente il testo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setFocusPainted(false);  //evita che venga disegnato un rettangolo di focus attorno al JMenuItem 'utenteLibrerie'
        utenteLibrerie.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JMenuItem 'utenteLibrerie'
        utenteLibrerie.setMinimumSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/28.8)));   //imposta la dimensione minima del JMenuItem 'utenteLibrerie'
        utenteMenu.setPopupSize(new Dimension((int) (controller.screenWidth/15.24), (int) (controller.screenHeight/9.6))); //imposta le dimensioni del menu 'utenteMenu'
        utenteMenu.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del menu 'utenteMenu'
        utenteMenu.setBackground(new Color(0xFFD369));  //imposta il colore dello sfondo del menu 'utenteMenu'
        utenteMenu.add(utenteProfilo);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));    //adatta le dimensioni minime di 'utenteMenu'
        }

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        informazioniLabel.setText("(" + controller.fascicolo_selected.rivista.issn + ") - " + controller.fascicolo_selected.rivista.titolo + " N°" + controller.fascicolo_selected.numero);  //imposta il testo della JLabel 'informazioniLabel' con l'ISSN e il titolo della rivista e il numero del fascicolo selezionato

        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato da mostrare del numero decimale

        valutazioneMedia = controller.valutazioneMediaFascicolo();  //calcola la media delle valutazioni del fascicolo selezionato
        valutazione.setText(valMedForm.format(valutazioneMedia));   //mostra la media delle valutazioni del fascicolo selezionato

        ImageIcon stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png")); //carica l'immagine nel percorso /stella_vuota.png
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaVuotaIco = new ImageIcon(stellaVuotaImg); //reinizializza l'ImageIcon 'stellaVuotaIco' con l'Image 'stellaVuotaImg'

        ImageIcon stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png")); //carica l'immagine nel percorso /stella_piena.png
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaPienaIco = new ImageIcon(stellaPienaImg); //reinizializza l'ImageIcon 'stellaPienaIco' con l'Image 'stellaPienaImg'

        ImageIcon stellaMezzaIco = new ImageIcon(this.getClass().getResource("/stella_mezza.png")); //carica l'immagine nel percorso /stella_mezza.png
        Image stellaMezzaImg = stellaMezzaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaMezzaIco = new ImageIcon(stellaMezzaImg); //reinizializza l'ImageIcon 'stellaMezzaIco' con l'Image 'stellaMezzaImg'

        changeStars(stellaPienaIco, stellaVuotaIco, stellaMezzaIco);    //aggiorna le stelle della valutazione media

        favouriteVuotoIco = new ImageIcon(this.getClass().getResource("/favorite1.png"));   //carica l'immagine nel percorso /favorite1.png
        Image favouriteVuotoImg = favouriteVuotoIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
        favouriteVuotoIco = new ImageIcon(favouriteVuotoImg);   //reinizializza l'ImageIcon 'favouriteVuotoIco' con l'Image 'favouriteVuotoImg'

        favouritePienoIco = new ImageIcon(this.getClass().getResource("/favorite2.png"));   //carica l'immagine nel percorso /favorite2.png
        Image favouritePienoImg = favouritePienoIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
        favouritePienoIco = new ImageIcon(favouritePienoImg);   //reinizializza l'ImageIcon 'favouritePienoIco' con l'Image 'favouritePienoImg'

        controller.likeFascicolo(); //controlla se l'utente ha il fascicolo selezionato nei preferiti e inizializza 'controller.likeElementSelected'
        controller.allRecWithCommentFascicolo(); //inizializza 'recensioniConCommento'

        if (controller.likeElementSelected == false) {  //controlla se l'utente ha il fascicolo selelzionato nei preferiti
            likeButton.setIcon(favouriteVuotoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouriteVuotoIco'
        }else {
            likeButton.setIcon(favouritePienoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouritePienoIco'
        }

        likeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (controller.likeElementSelected == false){   //controlla se l'utente ha il fascicolo selelzionato nei preferiti
                    likeButton.setIcon(favouritePienoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouriteVuotoIco'
                } else{
                    likeButton.setIcon(favouriteVuotoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouritePienoIco'
                }

                controller.changeLikeFascicolo();    //aggiorna il valore di 'controller.likeElementSelected' e aggiorna il contenuto del DB
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                System.exit(0);
            }
        });


        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                hp.frame.setVisible(true);  //rende visibile il frame 'hp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton utenteButton
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - (int) (controller.screenWidth/15.24), utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
            }
        });

        utenteMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                active = true;  //aggiorna 'active'
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                active = false; //aggiorna 'active'
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false;
                utenteButton.setBackground(Color.decode("#FFD369"));    //imposta lo sfondo del JButton 'utenteButton'
            }
        });

        utenteProfilo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Profilo pf = new Profilo(frameC, controller); //chiama il frame 'pf'
                pf.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        utenteExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frameC.setVisible(true);    //rende visibile il frame chiamante 'frameC'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteLibrerie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                BookshopsPage bsp = new BookshopsPage(frameC, controller); //chiama il frame 'bsp'
                bsp.frame.setVisible(true);  //rende visible il frame 'bsp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                utenteButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'utenteButton'
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (active == false){   //controlla se è stato disattivato il menu "Utente"
                    super.mouseExited(e);
                    utenteButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'utenteButton'
                }
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeButton.setBackground(Color.decode("#cf9e29"));  //imposta il colore dello sfondo del JButton 'homeButton'
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homeButton.setBackground(Color.decode("#FFD369"));  //imposta il colore dello sfondo del JButton 'homeButton'
            }
        });

        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                libriButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'libriButton'
            }
        });
        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libriButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'libriButton'
            }
        });

        libriButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                BooksPage bp = new BooksPage(frameC, controller);   //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SeriesPage sp = new SeriesPage(frameC, controller);   //chiama il frame 'sp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'sp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                IssuesPage ip = new IssuesPage(frameC, controller); //chiama il frame 'ip'
                ip.frame.setVisible(true);  //rende visibile il frame chiamato 'ip'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'serieButton'
            }
        });
        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'serieButton'
            }
        });


        allScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        allScrollPanel.setBackground(new Color(0x222831));
        allScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        allScrollPanel.getViewport().setBackground(new Color(0x222831));

        DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new String[]{"Libreria", "Quantità", "Fruizione", "Indirizzo", "Sito Web", "N. di Telefono"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableModel model2 = new DefaultTableModel(new Object[][]{}, new String[]{"DOI", "Titolo", "Anno Di Pubblicazione", "Autori"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        // Renderer personalizzato per l'header della tabella
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0xCF9E29));
        headerRenderer.setForeground(new Color(0xEEEEEE));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        Font headerFont = new Font("Bebas Neue", Font.PLAIN, 15); // Imposta il font Bebas Neue, grandezza 15 e stile Regular
        headerRenderer.setFont(headerFont);
        JTableHeader tableHeader1 = table1.getTableHeader();
        tableHeader1.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        table1.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        table1.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader1.setBorder(null);

        tableHeader1.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader1.getDefaultRenderer()));


        table1.setModel(model1); //imposta il modello dei dati della JTable 'booksTable'
        jscroll1.setBackground(new Color(0x222831));
        jscroll1.setBorder(BorderFactory.createEmptyBorder());
        jscroll1.getViewport().setBackground(new Color(0x222831));

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(table1.isColumnSelected(4)){
                    try {
                        Desktop.getDesktop().browse(new URI(table1.getValueAt(table1.getSelectedRow(), 4).toString()));
                    }catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
                table1.clearSelection();
            }
        });

        JTableHeader tableHeader2 = table2.getTableHeader();
        tableHeader2.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        table2.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        table2.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader2.setBorder(null);

        tableHeader2.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader2.getDefaultRenderer()));


        table2.setModel(model2); //imposta il modello dei dati della JTable 'booksTable'
        jscroll2.setBackground(new Color(0x222831));
        jscroll2.setBorder(BorderFactory.createEmptyBorder());
        jscroll2.getViewport().setBackground(new Color(0x222831));

        ArrayList<String> libreria = controller.getDisponibilitaLibreriaFascicolo(); //nomi delle librerie che possiedono il libro selezionato
        ArrayList<Integer> quantita = controller.getDisponibilitaQuantitaFascicolo();    //quantità disponibili del libro selezionato
        ArrayList<String> fruizione = controller.getDisponibilitaFruizioneFascicolo();   //modalità di fruizione disponibile del libro selezionato
        ArrayList<String> indirizzo = controller.getDisponibilitaIndirizzoFascicolo();   //indirizzi delle librerie che possiedono il libro selezionato
        ArrayList<String> sitoWeb = controller.getDisponibilitaSitoWebFascicolo();   //siti web delle librerie che possiedono il libro selezionato
        ArrayList<String> nTel = controller.getDisponibilitaNumeroTelefonoFascicolo();   //numeri telefonici delle librerie che possiedono il libro selezionato

        table1.setModel(model1);    //imposta il modello dei dati della JTable 'table2'

        for(int i = 0; i < libreria.size(); i++){

            if(fruizione.get(i).equals("Digitale") || fruizione.get(i).equals("AudioLibro")) model1.addRow(new Object[]{libreria.get(i), "∞", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else if(fruizione.get(i).equals("Cartaceo") && quantita.get(i) == 0) model1.addRow(new Object[]{libreria.get(i), "Non Disponibile", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else model1.addRow(new Object[]{libreria.get(i), quantita.get(i), fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
        }

        table2.setModel(model2);    //imposta il modello dei dati della JTable 'table1'

        for(int i = 0; i < controller.fascicolo_selected.articoli.size(); i++){
            model2.addRow(new Object[]{controller.fascicolo_selected.articoli.get(i).doi, controller.fascicolo_selected.articoli.get(i).titolo, controller.fascicolo_selected.articoli.get(i).annoPubblicazione, controller.allAutoriArticolo(controller.fascicolo_selected.articoli.get(i).autori)});
        }

        disponibilitaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(articoliCheckBox.isSelected()) {    //controlla se è stato selezionato il JCheckBox 'presentazioniCheckBox'

                    if (!disponibilitaCheckBox.isSelected()){   //controlla se non è stato selezionato il JCheckBox 'disponibilitaCheckBox'
                        allScrollPanel.revalidate();
                        jscroll1.setVisible(false); //rende invisibile il JScrollPane 'jscroll1'
                    } else {
                        jscroll1.setVisible(true); //rende visibile il JScrollPane 'jscroll1'
                        allScrollPanel.revalidate();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    disponibilitaCheckBox.setSelected(true);    //seleziona 'disponibilitaCheckBox'
                    allScrollPanel.revalidate();
                    jscroll1.setVisible(true);  //rende visibile il JScrollPane 'jscroll1'
                }
            }
        });

        articoliCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(disponibilitaCheckBox.isSelected()){ //controlla se è stato selezionato il JCheckBox 'disponibilitaCheckBox'

                    if(!articoliCheckBox.isSelected()){    //controlla se non è stato selezionato il JCheckBox 'presentazioniCheckBox'
                        jscroll2.setVisible(false); //rende invisibile il JScrollPane 'jscroll2'
                        allScrollPanel.revalidate();
                    } else {
                        jscroll2.setVisible(true); //rende visibile il JScrollPane 'jscroll2'
                        allScrollPanel.revalidate();
                    }
                } else{
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    articoliCheckBox.setSelected(true);  //seleziona 'presentazioniCheckBox'
                    allScrollPanel.revalidate();
                    jscroll2.setVisible(true);  //rende visibile il JScrollPane 'jscroll2'
                }
            }
        });
        valutaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Recensione recensioneFascicolo = new Recensione(frame, controller, valutazione, stella1, stella2, stella3, stella4, stella5, commenti, 3);
                frame.setEnabled(false); //disabilita il frame
            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table2.getSelectedRow() != -1) {
                    controller.doi_selected = table2.getValueAt(table2.getSelectedRow(), 0).toString();
                    controller.nome_articolo = table2.getValueAt(table2.getSelectedRow(), 1).toString();
                    controller.getAParticolo();
                    Conferenze conferenze = new Conferenze(frame, controller);
                    frame.setEnabled(false); // disabilita il frame
                    table2.clearSelection();
                }
            }
        });

        numeroNotifiche = controller.getNumeroNotificheNonLette();

        setNumeroNotifiche(controller);

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller);
            }
        });

        timer.start();
        timer.setRepeats(true);
    }

    private void setNumeroNotifiche(Controller controller){
        numeroNotifiche = controller.getNumeroNotificheNonLette();

        if(numeroNotifiche < 100 && numeroNotifiche > 0){
            notificheLabel.setVisible(true);
            String numeroNotificheText = Integer.toString(numeroNotifiche);
            notificheLabel.setText(numeroNotificheText);
        }else if (numeroNotifiche >= 100) notificheLabel.setText("99+");
        else {
            notificheLabel.setVisible(false);
        }
    }

    public void changeStars(ImageIcon stellaPienaIco, ImageIcon stellaVuotaIco, ImageIcon stellaMezzaIco){  //aggiorna le stelle della valutazione media
        if(valutazioneMedia <= 0.25){   //controlla se la media è minore uguale a 0,25
            stella1.setIcon(stellaVuotaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 0.75){    //controlla se la media è in [0.25,0.75[
            stella1.setIcon(stellaMezzaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.25){    //controlla se la media è in [0.75,1.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.75){    //controlla se la media è in [1.25,1.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaMezzaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.25){    //controlla se la media è in [1.75,2.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.75){    //controlla se la media è in [2.25,2.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaMezzaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.25){    //controlla se la media è in [2.75,3.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.75){    //controlla se la media è in [3.25,3.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaMezzaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.25){    //controlla se la media è in [3.75,4.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.75){    //controlla se la media è in [4.25,4.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaMezzaIco);
        } else {
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaPienaIco);
        }
    }

    public void changeStars(JLabel stella1, JLabel stella2, JLabel stella3, JLabel stella4, JLabel stella5, int valutazione, Controller controller){   //aggiorna le stelle della recensione con valutazione 'valutazione'

        ImageIcon stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
        stellaVuotaIco = new ImageIcon(stellaVuotaImg);

        ImageIcon stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
        stellaPienaIco = new ImageIcon(stellaPienaImg);

        switch (valutazione) {  //controlla 'valutazione' e aggiorna le stelle della recensione da mostrare
            case 1:
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaVuotaIco);
                stella3.setIcon(stellaVuotaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                break;
            case 2:
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaVuotaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                break;
            case 3:
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                break;
            case 4:
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaPienaIco);
                stella5.setIcon(stellaVuotaIco);
                break;
            case 5:
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaPienaIco);
                stella5.setIcon(stellaPienaIco);
                break;
            default:
                break;
        }

    }

    public void showComment(Controller controller, JPanel commenti){
        controller.selezionaFascicolo(numero,titolo);    //isbn del ibro selezionato
        controller.allRecWithCommentFascicolo();
        for (int i = 0; i < controller.recensioniConCommento.size(); i++){
            addComment(controller.recensioniConCommento.get(i).utenteRecensore.username, controller.recensioniConCommento.get(i).valutazione, controller.recensioniConCommento.get(i).testo, commenti, i+1, controller);
        }
    }

    public void addComment(String utente, int val, String commentoUser, JPanel commenti, int n, Controller controller){
        JPanel panel1;
        JLabel jcomp1;
        JLabel jcomp2;
        JLabel jcomp3;
        JLabel jcomp4;
        JLabel jcomp5;
        JLabel jcomp6;
        JTextArea jcomp7;
        JSeparator jcomp8;

        int maxCaratteriPerLinea = 50;
        StringBuilder newText = new StringBuilder();
        int countCaratteri = 0;
        int n_righe = 1;

        //construct components
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x222831));

        jcomp1 = new JLabel (utente + ":");
        jcomp1.setFont(controller.baseFontSize);
        jcomp1.setBackground(new Color(0x222831));
        jcomp1.setForeground(new Color(0xEEEEEE));

        jcomp2 = new JLabel ("");
        jcomp2.setBackground(new Color(0x222831));

        jcomp3 = new JLabel ("");
        jcomp3.setBackground(new Color(0x222831));

        jcomp4 = new JLabel ("");
        jcomp4.setBackground(new Color(0x222831));

        jcomp5 = new JLabel ("");
        jcomp5.setBackground(new Color(0x222831));

        jcomp6 = new JLabel ("");
        jcomp6.setBackground(new Color(0x222831));

        // Creazione della JTextArea
        jcomp7 = new JTextArea();
        jcomp7.setFont(controller.baseFontSize);
        jcomp7.setBackground(new Color(0x222831));
        jcomp7.setForeground(new Color(0xEEEEEE));
        jcomp7.setLineWrap(true);
        jcomp7.setWrapStyleWord(true);
        jcomp7.setEditable(false);

        for (char carattere : commentoUser.toCharArray()) {
            if (!(carattere == '\n')) {
                newText.append(carattere);
                countCaratteri++;
            } else {
                newText.append('\n');
                countCaratteri = 0;
                n_righe++;
            }
            if (countCaratteri >= maxCaratteriPerLinea) {
                newText.append('\n');
                countCaratteri = 0;
                n_righe++;
            }
        }
        jcomp7.setText(newText.toString());

        jcomp8 = new JSeparator();
        jcomp8.setFont(controller.baseFontSize);
        jcomp8.setForeground(new Color(0xEEEEEE));

        changeStars(jcomp2, jcomp3, jcomp4, jcomp5, jcomp6, val, controller);

        //adjust size and set layout
        panel1.setPreferredSize (new Dimension ((int) (controller.screenWidth/2.044), (int) (controller.screenHeight/4.8)));
        panel1.setLayout (null);

        //add components
        panel1.add (jcomp1);
        panel1.add (jcomp2);
        panel1.add (jcomp3);
        panel1.add (jcomp4);
        panel1.add (jcomp5);
        panel1.add (jcomp6);
        panel1.add (jcomp7);
        panel1.add (jcomp8);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (0, 0, (int) (controller.screenWidth/12.8), (int) (controller.screenHeight/28.8));
        jcomp2.setBounds (0, (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));
        jcomp3.setBounds ((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));
        jcomp4.setBounds ((int) (controller.screenWidth/25.6), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));
        jcomp5.setBounds ((int) (controller.screenWidth/17.0666), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));
        jcomp6.setBounds ((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));
        jcomp7.setBounds (0, (int) (controller.screenHeight/14.4), (int) (controller.screenWidth/2.56), controller.calcolaAltezzaFont(controller.baseFontSize)*n_righe);
        jcomp8.setBounds (0, (int) (controller.screenHeight/14.4)+(controller.calcolaAltezzaFont(controller.baseFontSize)*n_righe)+1, (int) (controller.screenWidth/2.56), (int) (controller.screenHeight/48));


        panel1.setPreferredSize (new Dimension ((int) (controller.screenWidth/2.56), jcomp1.getHeight()+jcomp2.getHeight()+jcomp7.getHeight()+jcomp8.getHeight()));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Colonna 1
        constraints.gridy = (n-1); // Riga 0
        commenti.add(panel1, constraints);
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

        ImageIcon backIco = new ImageIcon(this.getClass().getResource("/back.png"));
        Image backImg = backIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        backIco = new ImageIcon(backImg);
        backButton = new JLabel(backIco);

        commenti = new JPanel();
        commenti.setLayout(new GridBagLayout());
        ((GridBagLayout)commenti.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)commenti.getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)commenti.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)commenti.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};;


        ImageIcon checkIco = new ImageIcon(this.getClass().getResource("/c2.png"));
        Image checkImg = checkIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        checkIco = new ImageIcon(checkImg);

        ImageIcon checkSelIco = new ImageIcon(this.getClass().getResource("/c1.png"));
        Image checkSelImg = checkSelIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        checkSelIco = new ImageIcon(checkSelImg);

        disponibilitaCheckBox = new JCheckBox();
        disponibilitaCheckBox.setIcon(checkIco);
        disponibilitaCheckBox.setSelectedIcon(checkSelIco);
        articoliCheckBox = new JCheckBox();
        articoliCheckBox.setIcon(checkIco);
        articoliCheckBox.setSelectedIcon(checkSelIco);

        showComment(new Controller(), commenti);

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        notificaIco = new ImageIcon(notificaImg);

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);
    }
}