package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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

/**
 * The type Serie page.
 */
public class SeriePage {
    /**
     * The Frame.
     */
    public JFrame frame;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JTable table1;
    private JTable table2;
    private JCheckBox disponibilitaCheckBox;
    private JCheckBox presentazioniCheckBox;
    private JPanel jpanel;
    private JLabel nomeIsbn;
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
    /**
     * The Favourite vuoto ico.
     */
    ImageIcon favouriteVuotoIco;
    /**
     * The Favourite pieno ico.
     */
    ImageIcon favouritePienoIco;
    private float valutazioneMedia;
    private String isbnSelezionato;
    private int numeroNotifiche;

    /**
     * Instantiates a new Serie page.
     *
     * @param frameC     the frame c
     * @param controller the controller
     */
    public SeriePage (JFrame frameC, Controller controller) {
        isbnSelezionato = controller.isbn_selected;     //inizializza 'isbnSelezionato' con l'ISBN della serie selezionata

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

        nomeIsbn.setFont(controller.baseFontSize);  //imposta il font della JLabel 'nomeIsbn'
        valutaButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'valutaButton'

        disponibilitaCheckBox.setFont(controller.baseFontSize); //imposta il font del del JCheckBox 'disponibilitaCheckBox'
        presentazioniCheckBox.setFont(controller.baseFontSize); //imposta il font del del JCheckBox 'presentazioniCheckBox'

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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'increaseButton'

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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'increaseButton'

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
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'increaseButton'

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

        if (controller.utente.getPartitaIVA() == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
            utenteMenu.setPopupSize(new Dimension((int)(controller.screenWidth/15.24),(int) (controller.screenHeight/14.4))); //adatta le dimensioni di 'utenteMenu'
            utenteMenu.setMaximumSize(new Dimension((int)(controller.screenWidth/15.24), (int) (controller.screenHeight/14.4)));    //adatta le dimensioni minime di 'utenteMenu'
        }

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();
        frame.setSize(controller.screenWidth, controller.screenHeight);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        nomeIsbn.setText(controller.nome_selected + " - (" + controller.isbn_selected + ")");  //imposta il tetsto della JLabel 'nomeIsbn' con il nome e l'ISBN del libro selezionato

        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato da mostrare del numero decimale

        valutazioneMedia = controller.valutazioneMediaSerie();  //calcola la media delle valutazioni del libro selezionato
        valutazione.setText(valMedForm.format(valutazioneMedia));   //mostra la media delle valutazioni del libro selezionato

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

        controller.likeSerie(); //controlla se l'utente ha il libro selezionato nei preferiti e inizializza 'controller.likeLibroSelected'
        controller.allRecWithCommentSerie(); //inizializza 'recensioniConCommento'

        if (controller.likeElementSelected == false){   //controlla se l'utente non ha il libro selelzionato nei preferiti
            likeButton.setIcon(favouriteVuotoIco);   //controlla se l'utente ha il libro selelzionato nei preferiti
        }
        else{
            likeButton.setIcon(favouritePienoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouritePienoIco'
        }

        likeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controller.likeElementSelected == false){   //controlla se l'utente non ha il libro selelzionato nei preferiti
                    likeButton.setIcon(favouritePienoIco);  //imposta l'icona della JLabel 'likeButton' con l'immagine 'favouritePienoIco'
                }
                else{
                    likeButton.setIcon(favouriteVuotoIco);  //controlla se l'utente ha il libro selelzionato nei preferiti
                }

                controller.changeLikeSerie();    //aggiorna il valore di 'likeElementSelected' e aggiorna il contenuto del DB
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
                if (e.getButton() == MouseEvent.BUTTON1) {  //controlla se è stato cliccato con il tasto sinistro del mouse il JButton 'utenteButton'
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
                active = false; //aggiorna 'active'
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
                frameC.setVisible(true); //rende visibile il frame chiamante
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
                super.mouseReleased(e);;
                BooksPage bp = new BooksPage(frameC, controller);   //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                IssuesPage ip = new IssuesPage(frameC, controller);   //chiama il frame 'ip'
                ip.frame.setVisible(true);  //rende visibile il frame chiamato 'ip'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                SeriesPage sp = new SeriesPage(frameC, controller); //chiama il frame 'sp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'sp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoliButton.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'fascicoliButtonButton'
            }
        });

        fascicoliButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoliButton.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'fascicoliButtonButton'
            }
        });


        allScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   //permette di visualizzare sempre la barra di scorrimento del JScrollPane 'allScrollPanel'
        allScrollPanel.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JScrollPane 'allScrollPanel'
        allScrollPanel.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JScrollPane 'allScrollPanel'
        allScrollPanel.getViewport().setBackground(new Color(0x222831));    //imposta il colore dello sfondo della parte visibile del JScrollPane 'allScrollPanel'

        DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new String[]{"Libreria", "Quantità", "Fruizione", "Indirizzo", "Sito Web", "N. di Telefono"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableModel model2 = new DefaultTableModel(new Object[][]{}, new String[]{"ISBN", "Titolo", "Data Di Pubblicazione"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();   //DefaultTableCellRenderer per la formattazione dell'header della tabella

        headerRenderer.setBackground(new Color(0xCF9E29));  //imposta il colore dello sfondo dell'header della tabella
        headerRenderer.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo dell'header della tabella
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);   //centra orizzontalmente il contenuto dell'header della tabella

        Font headerFont = new Font("Impact", Font.PLAIN, 15);   //imposta il font Bebas Neue, grandezza 15 e stile Regular

        headerRenderer.setFont(headerFont); //inizializza il font Bebas Neue, grandezza 15 e stile Regular per i caratteri della tabella

        JTableHeader tableHeader1 = table1.getTableHeader();    //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'table1'

        tableHeader1.setDefaultRenderer(headerRenderer);    //imposta il render di default della tabella a 'headerRender'

        table1.getTableHeader().setResizingAllowed(false);  //impedisce il ridimensionamento delle colonne

        table1.getTableHeader().setReorderingAllowed(false);    //impedisce il riordinamento delle colonne

        tableHeader1.setBorder(null);   //rimuove il bordo dell'header della tabella

        tableHeader1.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader1.getDefaultRenderer()));    //inposta il render di default della tabella


        table1.setModel(model1); //imposta il modello dei dati della JTable 'table1'
        jscroll1.setBackground(new Color(0x222831));    //imposta il colore dello sfondo del JScrollPane 'jscroll1'
        jscroll1.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JScrollPane 'jscroll1'
        jscroll1.getViewport().setBackground(new Color(0x222831));  //imposta il colore dello sfondo della parte visibile del JScrollPane 'jscroll1'

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(table1.isColumnSelected(4)){ //controlla se è stata selezionata la 4 colonna dalla JTable 'table1'
                    try {
                        Desktop.getDesktop().browse(new URI(table1.getValueAt(table1.getSelectedRow(), 4).toString())); //inserisce un link nella cella (table1.getSelectedRow(), 4)
                    }catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }

                table1.clearSelection();    //deseleziona tutte le celle selezionate in 'table1'
            }
        });

        JTableHeader tableHeader2 = table2.getTableHeader();    //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'table2'

        tableHeader2.setDefaultRenderer(headerRenderer);    //imposta il render di default della tabella a 'headerRender'

        table2.getTableHeader().setResizingAllowed(false);  //impedisce il ridimensionamento delle colonne

        table2.getTableHeader().setReorderingAllowed(false);    //impedisce il riordinamento delle colonne

        tableHeader2.setBorder(null);   //rimuove il bordo dell'header della tabella

        tableHeader2.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader2.getDefaultRenderer()));    //imposta il render di default della tabella


        table2.setModel(model2); //imposta il modello dei dati della JTable 'table2'
        jscroll2.setBackground(new Color(0x222831));    //imposta il colore dello sfondo del JScrollPane 'jscroll2'
        jscroll2.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JScrollPane 'jscroll2'
        jscroll2.getViewport().setBackground(new Color(0x222831));  //imposta il colore dello sfondo della parte visibile del JScrollPane 'jscroll2'

        ArrayList<String> libreria = controller.getDisponibilitaLibreria(); //nomi delle librerie che possiedono la serie selezionata
        ArrayList<Integer> quantita = controller.getDisponibilitaQuantita();    //quantità disponibili della serie selezionata
        ArrayList<String> fruizione = controller.getDisponibilitaFruizione();   //modalità di fruizione disponibile della serie selezionata
        ArrayList<String> indirizzo = controller.getDisponibilitaIndirizzo();   //indirizzi delle librerie che possiedono illa serie selezionata
        ArrayList<String> sitoWeb = controller.getDisponibilitaSitoWeb();   //siti web delle librerie che possiedono la serie selezionata
        ArrayList<String> nTel = controller.getDisponibilitaNumeroTelefono();   //numeri telefonici delle librerie che possiedono la serie selezionata

        table1.setModel(model1);    //imposta il modello dei dati della JTable 'table1'

        for(int i = 0; i < libreria.size(); i++){   //scorre 'libreria'
            if(fruizione.get(i).equals("Digitale") || fruizione.get(i).equals("AudioLibro")){   //controlla se l'i-esima libreria possiede la serie selezionata
                model1.addRow(new Object[]{libreria.get(i), "∞", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)}); //aggiunge una nuova riga nella tabella
            } else if(fruizione.get(i).equals("Cartaceo") && quantita.get(i) == 0){ //controlla nell'i-esima libreria non è disponibile la serie selezionata
                model1.addRow(new Object[]{libreria.get(i), "Non Disponibile", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});   //aggiunge una nuova riga nella tabella
            } else{
                model1.addRow(new Object[]{libreria.get(i), quantita.get(i), fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)}); //aggiunge una nuova riga nella tabella
            }
        }

        controller.getLibriSerie(isbnSelezionato);  //inizializza 'controller.listaLibriSerie' con tutti i libri della serie
        table2.setModel(model2);    //imposta il modello dei dati della JTable 'table2'

        for(int i = 0; i < controller.listaLibriSerie.size(); i++){ //scorre 'controller.listaLibriSerie'
            model2.addRow(new Object[]{controller.listaLibriSerie.get(i).getISBN(), controller.listaLibriSerie.get(i).getTitolo(), controller.listaLibriSerie.get(i).getDataPubblicazione()}); //aggiunge una nuova riga nella tabella
        }

        disponibilitaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!presentazioniCheckBox.isSelected()) {    //controlla se non è stato selezionato il JCheckBox 'presentazioniCheckBox'
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Non puoi rimuovere entrambe le tabelle!");    //mostra un messaggio di errore
                    disponibilitaCheckBox.setSelected(true);    //seleziona 'disponibilitaCheckBox'
                    allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                    jscroll1.setVisible(true);  //rende visibile il JScrollPane 'jscroll1'
                } else {
                    if (!disponibilitaCheckBox.isSelected()){   //controlla se non è stato selezionato il JCheckBox 'disponibilitaCheckBox'
                        allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                        jscroll1.setVisible(false); //rende invisibile il JScrollPane 'jscroll1'
                    } else {
                        jscroll1.setVisible(true); //rende visibile il JScrollPane 'jscroll1'
                        allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                    }
                }
            }
        });

        presentazioniCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!disponibilitaCheckBox.isSelected()){ //controlla se non è stato selezionato il JCheckBox 'disponibilitaCheckBox'
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Non puoi rimuovere entrambe le tabelle!");    //mostra un messaggio di errore
                    presentazioniCheckBox.setSelected(true);  //seleziona 'presentazioniCheckBox'
                    allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                    jscroll2.setVisible(true);  //rende visibile il JScrollPane 'jscroll2'
                } else{
                    if(!presentazioniCheckBox.isSelected()){    //controlla se non è stato selezionato il JCheckBox 'presentazioniCheckBox'
                        jscroll2.setVisible(false); //rende invisibile il JScrollPane 'jscroll2'
                        allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                    } else {
                        jscroll2.setVisible(true); //rende visibile il JScrollPane 'jscroll2'
                        allScrollPanel.revalidate();    //aggiorna il contenuto del JScrollPane 'allScrollPanel'
                    }
                }
            }
        });

        valutaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Recensione recensioneSerie = new Recensione(frame, controller, valutazione, stella1, stella2, stella3, stella4, stella5, commenti, 2);  //chiama il frame 'recensioneSerie'
                frame.setEnabled(false); //disabilita il frame
            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.isbn_selected = table2.getValueAt(table2.getSelectedRow(), 0).toString();    //inizializza 'controller.isbn_selected' con l'ISBN del libro selezionato
                controller.nome_selected = table2.getValueAt(table2.getSelectedRow(), 1).toString();    //inizializza 'controller.nome_selected' con il nome del libro selezionato

                BookPage bp = new BookPage(frameC, controller); //chiama il frame 'bp'
                bp.frame.setVisible(true);  //rende visible il frame 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        numeroNotifiche = controller.getNumeroNotificheNonLette();  //inizializza 'numeroNotifiche' con il numero di notifiche dell'utente non lette
        setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumeroNotifiche(controller); //imposta il testo della JLabel 'notificheLabel'
            }
        });

        timer.start();  //avvia il timer
        timer.setRepeats(true); //fa ripetere il timer dopo che è scaduto
    }

    /**
     * Change stars.
     *
     * @param stellaPienaIco the stella piena ico
     * @param stellaVuotaIco the stella vuota ico
     * @param stellaMezzaIco the stella mezza ico
     */
    public void changeStars(ImageIcon stellaPienaIco, ImageIcon stellaVuotaIco, ImageIcon stellaMezzaIco){  //aggiorna le stelle della valutazione media
        if(valutazioneMedia <= 0.25){   //controlla se la media è minore uguale a 0,25
            stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
            stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
            stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 0.75){    //controlla se la media è in [0.25,0.75[
            stella1.setIcon(stellaMezzaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaMezzaIco'
            stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
            stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 1.25){    //controlla se la media è in [0.75,1.25[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
            stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 1.75){    //controlla se la media è in [1.25,1.75[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaMezzaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaMezzaIco'
            stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 2.25){    //controlla se la media è in [1.75,2.25[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 2.75){    //controlla se la media è in [2.25,2.75[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaMezzaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaMezzaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 3.25){    //controlla se la media è in [2.75,3.25[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
            stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 3.75){    //controlla se la media è in [3.25,3.75[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
            stella4.setIcon(stellaMezzaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaMezzaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 4.25){    //controlla se la media è in [3.75,4.25[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
            stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
            stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 4.75){    //controlla se la media è in [4.25,4.75[
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
            stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
            stella5.setIcon(stellaMezzaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaMezzaIco'
        } else {
            stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
            stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
            stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
            stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
            stella5.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaPienaIco'
        }
    }//fine changeStars

    private void setNumeroNotifiche(Controller controller){ //aggiorna il testo della JLabel 'notificheLabel' con in base al numero di notifiche dell'utente non lette
        numeroNotifiche = controller.getNumeroNotificheNonLette();  //inizializza 'numeroNotifiche' con il numero di notifiche dell'utente non lette

        if (numeroNotifiche <= 0){  //controlla se non ci sono notifiche non lette
            notificheLabel.setVisible(false);   //rende invisibile la JLabel 'notificheLabel'
        }else if (numeroNotifiche >= 100) { //controlla se ci sono almeno 100 notifiche non lette
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'
            notificheLabel.setText("99+");  //imposta il testo della JLabel 'notificheLabel'
        }else{
            notificheLabel.setVisible(true);    //rende visibile la JLabel 'notificheLabel'

            String numeroNotificheText = Integer.toString(numeroNotifiche); //inizializza numeroNotificheText con 'numeroNotifiche'

            notificheLabel.setText(numeroNotificheText);    //imposta il testo della JLabel 'notificheLabel' con 'numeroNotificheText'
        }
    }//fine setNumeroNotifiche

    /**
     * Change stars.
     *
     * @param stella1     the stella 1
     * @param stella2     the stella 2
     * @param stella3     the stella 3
     * @param stella4     the stella 4
     * @param stella5     the stella 5
     * @param valutazione the valutazione
     * @param controller  the controller
     */
    public void changeStars(JLabel stella1, JLabel stella2, JLabel stella3, JLabel stella4, JLabel stella5, int valutazione, Controller controller){   //aggiorna le stelle della recensione con valutazione 'valutazione'
        ImageIcon stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png")); //carica l'immagine nel percorso /stella_vuota.png
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaVuotaIco = new ImageIcon(stellaVuotaImg); //reinizializza l'ImageIcon 'stellaVuotaIco' con l'Image 'stellaVuotaImg'

        ImageIcon stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png")); //carica l'immagine nel percorso /stella_piena.png
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaPienaIco = new ImageIcon(stellaPienaImg); //reinizializza l'ImageIcon 'stellaPienaIco' con l'Image 'stellaPienaImg'

        switch (valutazione) {  //controlla 'valutazione' e aggiorna le stelle della recensione da mostrare
            case 1:
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                break;
            case 2:
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                break;
            case 3:
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                break;
            case 4:
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                break;
            case 5:
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                stella5.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaPienaIco'
                break;
            default:
                break;
        }
    }//fine changeStars

    /**
     * Show comment.
     *
     * @param controller the controller
     * @param commenti   the commenti
     */
    public void showComment(Controller controller, JPanel commenti){    //mostra tutti i commenti fatti al libro selezionato
        controller.isbn_selected = isbnSelezionato;    //inizializza 'controller.isbn_selected' con 'isbnSelezionato'
        controller.allRecWithCommentLibro();    //inizializza 'controller.recensioniConCommento'

        for (int i = 0; i < controller.recensioniConCommento.size(); i++){  //scorre 'controller.recensioniConCommento'
            addComment(controller.recensioniConCommento.get(i).getUtenteRecensore().getUsername(), controller.recensioniConCommento.get(i).getValutazione(), controller.recensioniConCommento.get(i).getTesto(), commenti, i+1, controller);    //mostra l'i-esimo commento
        }
    }//fine showComment

    /**
     * Add comment.
     *
     * @param utente       the utente
     * @param val          the val
     * @param commentoUser the commento user
     * @param commenti     the commenti
     * @param n            the n
     * @param controller   the controller
     */
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

        int maxCaratteriPerLinea = 50;  //numero massimo di caratteri per linea
        StringBuilder newText = new StringBuilder();
        int countCaratteri = 0; //numero di caratteri in una riga
        int nRighe = 1; //numero di righe

        panel1 = new JPanel();  //inizializza il JPanel 'panel1'
        panel1.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JPanel 'panel1'

        jcomp1 = new JLabel (utente + ":"); //inizialliza la JLabel 'jcomp1' impostando il testo
        jcomp1.setFont(controller.baseFontSize);    //imposta il font di 'jcomp1'
        jcomp1.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp1'
        jcomp1.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo di 'jcomp1'

        jcomp2 = new JLabel ("");   //inizialliza la JLabel 'jcomp2' impostando il testo
        jcomp2.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp2'

        jcomp3 = new JLabel ("");   //inizialliza la JLabel 'jcomp3' impostando il testo
        jcomp3.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp3'

        jcomp4 = new JLabel ("");   //inizialliza la JLabel 'jcomp4' impostando il testo
        jcomp4.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp4'

        jcomp5 = new JLabel ("");   //inizialliza la JLabel 'jcomp5' impostando il testo
        jcomp5.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp5'

        jcomp6 = new JLabel ("");   //inizialliza la JLabel 'jcomp6' impostando il testo
        jcomp6.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp6'

        jcomp7 = new JTextArea();   //inizialliza la JTextArea 'jcomp7'

        jcomp7.setFont(controller.baseFontSize);    //imposta il font di 'jcomp7'
        jcomp7.setBackground(new Color(0x222831));  //imposta il colore dello sfondo di 'jcomp7'
        jcomp7.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo di 'jcomp7'
        jcomp7.setLineWrap(true);   //permette di andare a capo automaticamente quando il testo raggiunge la fine della riga in 'jcomp7'
        jcomp7.setWrapStyleWord(true);  //evita che quando si va a capo venga interrotta l'ultima parola della riga non venga interrotta in 'jcomp7'
        jcomp7.setEditable(false);  //evita che l'utente inserisca del testo in 'jcomp7'

        for (char carattere : commentoUser.toCharArray()) { //scorre i caratteri del commento
            if (!(carattere == '\n')) { //controlla se il carattere corrente 'carattere' è "\n"
                newText.append(carattere);  //aggiunge 'carattere' alla fine di 'newText'
                countCaratteri++;   //incrementa il numero di caratteri nella riga corrente
            } else {
                newText.append('\n');   //aggiunge 'carattere' alla fine di 'newText'
                countCaratteri = 0; //azzere il numero di caratteri nella riga corrente
                nRighe++;  //incrementa il numero di righe
            }

            if (countCaratteri >= maxCaratteriPerLinea) {   //controlla se il numero di caratteri nella riga corrente  maggiore o uguale al numero massimo di caratteri per linea
                newText.append('\n');   //aggiunge 'carattere' alla fine di 'newText'
                countCaratteri = 0; //azzere il numero di caratteri nella riga corrente
                nRighe++;   //incrementa il numero di righe
            }
        }

        jcomp7.setText(newText.toString()); //imposta il testo di 'jcomp7' a 'newText'

        jcomp8 = new JSeparator();  //inizialliza il JSeparator 'jcomp8'
        jcomp8.setFont(controller.baseFontSize);    //imposta il font di 'jcomp8'
        jcomp8.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo di 'jcomp8'

        changeStars(jcomp2, jcomp3, jcomp4, jcomp5, jcomp6, val, controller);   //aggiorna le stelle della recensione con valutazione 'val'

        panel1.setPreferredSize(new Dimension ((int) (controller.screenWidth/2.044), (int) (controller.screenHeight/4.8))); //imposta la dimensione preferita di 'panel1'
        panel1.setLayout(null); //disattiva il layout manager di 'panel1', quindi il suo layout verrà gestito manualmente

        panel1.add(jcomp1); //inserisce 'label1' in 'panel1'
        panel1.add(jcomp2); //inserisce 'label2' in 'panel1'
        panel1.add(jcomp3); //inserisce 'label3' in 'panel1'
        panel1.add(jcomp4); //inserisce 'label4' in 'panel1'
        panel1.add(jcomp5); //inserisce 'label5' in 'panel1'
        panel1.add(jcomp6); //inserisce 'label6' in 'panel1'
        panel1.add(jcomp7); //inserisce 'label7' in 'panel1'
        panel1.add(jcomp8); //inserisce 'label8' in 'panel1'

        jcomp1.setBounds(0, 0, (int) (controller.screenWidth/12.8), (int) (controller.screenHeight/28.8));  //imposta la dimensione e la posizione di 'jcomps1' in 'panel1'
        jcomp2.setBounds(0, (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));   //imposta la dimensione e la posizione di 'jcomps2' in 'panel1'
        jcomp3.setBounds((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)); //imposta la dimensione e la posizione di 'jcomps3' in 'panel1'
        jcomp4.setBounds((int) (controller.screenWidth/25.6), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)); //imposta la dimensione e la posizione di 'jcomps4' in 'panel1'
        jcomp5.setBounds((int) (controller.screenWidth/17.0666), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8));  //imposta la dimensione e la posizione di 'jcomps5' in 'panel1'
        jcomp6.setBounds((int) (controller.screenWidth/12.8), (int) (controller.screenHeight/28.8), (int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)); //imposta la dimensione e la posizione di 'jcomps6' in 'panel1'
        jcomp7.setBounds(0, (int) (controller.screenHeight/14.4), (int) (controller.screenWidth/2.56), controller.calcolaAltezzaFont(controller.baseFontSize)*nRighe);  //imposta la dimensione e la posizione di 'jcomps7' in 'panel1'
        jcomp8.setBounds(0, (int) (controller.screenHeight/14.4)+(controller.calcolaAltezzaFont(controller.baseFontSize)*nRighe)+1, (int) (controller.screenWidth/2.56), (int) (controller.screenHeight/48));   //imposta la dimensione e la posizione di 'jcomps8' in 'panel1'


        panel1.setPreferredSize(new Dimension ((int) (controller.screenWidth/2.56), jcomp1.getHeight()+jcomp2.getHeight()+jcomp7.getHeight()+jcomp8.getHeight()));  //imposta la dimensione preferita di 'panel1'


        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;  //imposta a 0 la colonna del layout in cui inserire 'panel1'
        constraints.gridy = (n-1); //imposta a n-1 la riga del layout in cui inserire 'panel1'
        commenti.add(panel1, constraints);  //inserisce nel 'panel1' nel JPanel 'commenti'
    }//fine addComment

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        ImageIcon backIco = new ImageIcon(this.getClass().getResource("/back.png"));    //carica l'immagine nel percorso /back.png
        Image backImg = backIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

        backIco = new ImageIcon(backImg);   //reinizializza l'ImageIcon 'backIco' con l'Image 'backImg'
        backButton = new JLabel(backIco);   //inizializza la JLabel 'backButton' con 'backIco'

        commenti = new JPanel();    //inizializza il JPanel 'commenti'
        commenti.setLayout(new GridBagLayout());    //imposta il layout del JPanel 'commenti'
        ((GridBagLayout)commenti.getLayout()).columnWidths = new int[] {0, 0};  //le larghezze delle colonne del layout di 'commenti' vengono impostate dinamicamente in base alle componenti contenute
        ((GridBagLayout)commenti.getLayout()).rowHeights = new int[] {0, 0};    //le altezze delle righe del layout di 'commenti' vengono impostate dinamicamente in base alle componenti contenute
        ((GridBagLayout)commenti.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};   //assegna le priorità delle colonne del layout di 'commenti' per impostare le loro larghezze
        ((GridBagLayout)commenti.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};  //assegna le priorità delle righe del layout di 'commenti' impostare le loro altezze


        ImageIcon checkIco = new ImageIcon(this.getClass().getResource("/c2.png")); //carica l'immagine nel percorso /c2.png
        Image checkImg = checkIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

        checkIco = new ImageIcon(checkImg); //reinizializza l'ImageIcon 'checkIco' con l'Image 'checkImg'

        ImageIcon checkSelIco = new ImageIcon(this.getClass().getResource("/c1.png"));  //carica l'immagine nel percorso /c1.png
        Image checkSelImg = checkSelIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

        checkSelIco = new ImageIcon(checkSelImg);   //reinizializza l'ImageIcon 'checkSelIco' con l'Image 'checkSelImg'

        disponibilitaCheckBox = new JCheckBox();    //inizializza il JCheckBox 'disponibilitaCheckBox'
        disponibilitaCheckBox.setIcon(checkIco);    //imposta l'icona del JCheckBox 'disponibilitaCheckBox' con l'immagine 'checkIco'
        disponibilitaCheckBox.setSelectedIcon(checkSelIco); //imposta l'icona del JCheckBox 'disponibilitaCheckBox' quando una sua casella viene selzionata con l'immagine 'checkSelIco'
        presentazioniCheckBox = new JCheckBox();    //inizializza il JCheckBox 'presentazioniCheckBox'
        presentazioniCheckBox.setIcon(checkIco);    //imposta l'icona del JCheckBox 'presentazioniCheckBox' con l'immagine 'checkIco'
        presentazioniCheckBox.setSelectedIcon(checkSelIco); //imposta l'icona del JCheckBox 'presentazioniCheckBox' quando una sua casella viene selzionata con l'immagine 'checkSelIco'

        showComment(new Controller(), commenti);    //mostra tutti i commenti fatti al libro selezionato

        ImageIcon notificaIco = new ImageIcon(this.getClass().getResource("/notifica.png"));    //carica l'immagine nel percorso /notifica.png
        Image notificaImg = notificaIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

        notificaIco = new ImageIcon(notificaImg);   //reinizializza l'ImageIcon 'notificaIco' con l'Image 'notificaImg'

        notificheLabel = new JLabel();
        notificheLabel.setIcon(notificaIco);    //imposta l'icona della JLabel 'notificheLabel' con l'immagine 'notificaIco'
    }
}