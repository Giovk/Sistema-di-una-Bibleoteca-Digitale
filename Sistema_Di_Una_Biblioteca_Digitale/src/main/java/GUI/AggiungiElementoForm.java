package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.ArrayList;

public class AggiungiElementoForm {
    public JFrame frame;
    private JPanel contentPane;
    private JLabel closeBT;
    private JButton libroBT;
    private JButton serieBT;
    private JButton fascicoloBT;
    private JPanel buttonPanel;
    private JPanel questionPanel;
    private JPanel bookPanel;
    private JPanel seriePanel;
    private JPanel issuePanel;
    private JScrollPane bookScrollPanel;
    private JScrollPane serieScrollPanel;
    private JScrollPane issueScrollPanel;
    private JTextField titoloLibroField;
    private JPanel bookPanel2;
    private JTextField genereLibroField;
    private JComboBox linguaLibroCB;
    private JTextField editoreLibroField;
    private JLabel calendarLibroIMG;
    private JPanel autoriLibroPanel;
    private JButton autoreLibroButton;
    private JTextField dataLibroField;
    private JPanel closePanel;
    private JPanel elementPanel;
    private JButton inviaLibroButton;
    private JComboBox isbnLibroCB;
    private JLabel autoriLibroLabel;
    private JComboBox fruizioneLibroCB;
    private JSpinner quantitaLibroSpinner;
    private JPanel seriePanel2;
    private JTextField titoloSerieField;
    private JTextField dataSerieField;
    private JLabel calendarSerieIMG;
    private JSpinner spinnerLibriSerie;
    private JButton okSerieBT;
    private JButton inviaSerieButton;
    private JPanel isbnSeriePanel;
    private JTextField isbnSerieField;
    private JPanel issuePanel2;
    private JPanel articoliPanel;
    private JComboBox titoloRivistaCB;
    private JTextField issnRivistaField;
    private JTextField argomentoRivistaField;
    private JTextField nomeRField;
    private JTextField cognomeRField;
    private JTextField editoreRivistaField;
    private JSpinner annoPrivistaSpinner;
    private JComboBox numeroFascicoloCB;
    private JTextField dpFascicoloField;
    private JLabel calendarIssueIMG;
    private JComboBox fruizoneFascicoloCB;
    private JSpinner quantitaFascicoloSpinner;
    private JButton aggiungiArticoloBT;
    private JButton inviaFascicoloBT;
    private JLabel articoliFascicoloLabel;
    private JLabel scegliElementoLabel;
    private JLabel libroLabel;
    private JLabel isbnLibroLabel;
    private JLabel titoloLibroLabel;
    private JLabel genereLibroLabel;
    private JLabel linguaLibroLabel;
    private JLabel editoreLabel;
    private JLabel dataPubblicazioneLabel;
    private JLabel quantitaLabel;
    private JLabel fruizioneLabel;
    private JLabel isbnSerieLabel;
    private JLabel titoloLabel;
    private JLabel dataPubblicazioneLabel1;
    private JLabel libriLabel;
    private JLabel serieLabel;
    private JLabel fascicoloLabel;
    private JLabel titoloLabel1;
    private JLabel issnLabel;
    private JLabel argomentoLabel;
    private JLabel nomeResponsabileLabel;
    private JLabel cognomeResponsabileLabel;
    private JLabel editoreLabel1;
    private JLabel annoPubblicazioneLabel;
    private JLabel numeroLabel;
    private JLabel dataLabel;
    private JLabel fruizioneLabel1;
    private JLabel quantitaLabel1;
    private DatePicker datePickerLibro;
    private int autoreLibroCount = 0;
    private int isbnLibroCount = 0;
    private int articoliFascicoliCount = 0;
    private DatePicker datePickerSerie;
    private int screenWidth = 0;
    private int screenHeight = 0;
    ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));
    Image calendarImg = calendarIco.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width/51.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height/28.8), Image.SCALE_SMOOTH);
    private ArrayList<String> isbnLibri;
    private DatePicker datePickerFascicoli;

    public AggiungiElementoForm(JFrame frameC, Controller controller, DefaultTableModel model){
        screenHeight = controller.screenHeight; //inizializza l'altezza dello schermo
        screenWidth = controller.screenWidth;   //inizializza la larghezza dello schermo

        libroBT.setFont(controller.baseFontSize);   //imposta il font del JButton 'libroBT'
        libroBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));    //imposta la dimensione minima del JButton 'libroBT'
        serieBT.setFont(controller.baseFontSize);   //imposta il font del JButton 'serieBT'
        serieBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));    //imposta la dimensione minima del JButton 'serieBT'
        fascicoloBT.setFont(controller.baseFontSize);   //imposta il font del JButton 'fascicoloBT'
        fascicoloBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));    //imposta la dimensione minima del JButton 'fascicoloBT'

        scegliElementoLabel.setFont(controller.impactFontSize); //imposta il font della JLabel 'scegliElementoLabel'


        libroLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'libroLabel'

        isbnLibroLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'isbnLibroLabel'
        isbnLibroCB.setFont(controller.textFieldFont);  //imposta il font del JComboBox 'isbnLibroCB'
        isbnLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JComboBox 'isbnLibroCB'
        titoloLibroLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'titoloLibroLabel'
        titoloLibroField.setFont(controller.textFieldFont); //imposta il font del JTextField 'titoloLibroField'
        titoloLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'titoloLibroField'
        genereLibroLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'genereLibroLabel'
        genereLibroField.setFont(controller.textFieldFont); //imposta il font del JTextField 'genereLibroField'
        genereLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'genereLibroField'
        linguaLibroLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'linguaLibroLabel'
        linguaLibroCB.setFont(controller.textFieldFont);    //imposta il font del JComboBox 'linguaLibroCB'
        linguaLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JComboBox 'linguaLibroCB'
        editoreLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'editoreLabel'
        editoreLibroField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'editoreLibroField'
        editoreLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'editoreLibroField'
        dataPubblicazioneLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'dataPubblicazioneLabel'
        dataLibroField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'dataLibroField'
        dataLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'dataLibroField'
        calendarLibroIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));  //imposta la dimensione minima della JLabel 'calendarLibroIMG'
        calendarLibroIMG.setIcon(new ImageIcon(calendarImg));   //imposta l'icona della JLabel 'calendarLibroIMG' con l'immagine 'calendarImg'
        quantitaLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'quantitaLabel'
        quantitaLibroSpinner.setFont(controller.textFieldFont); //imposta il font dello JSpinner 'quantitaLibroSpinner'
        quantitaLibroSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima dello JSpinner 'quantitaLibroSpinner'
        fruizioneLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'fruizioneLabel'
        fruizioneLibroCB.setFont(controller.textFieldFont); //imposta il font del JComboBox 'fruizioneLibroCB'
        fruizioneLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JComboBox 'fruizioneLibroCB'

        autoriLibroLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'autoriLibroLabel'
        autoreLibroButton.setFont(controller.baseFontSize); //imposta il font del JButton 'autoreLibroButton'

        inviaLibroButton.setFont(controller.baseFontSize);  //imposta il font del JButton 'inviaLibroButton'


        fascicoloLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'libroLabel'

        titoloLabel1.setFont(controller.baseFontSize);  //imposta il font della JLabel 'titoloLabel1'
        titoloRivistaCB.setFont(controller.textFieldFont);  //imposta il font del JComboBox 'titoloRivistaCB'
        titoloRivistaCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JComboBox 'titoloRivistaCB'
        issnLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'issnLabel'
        issnRivistaField.setFont(controller.textFieldFont); //imposta il font del JTextField 'issnRivistaField'
        issnRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'issnRivistaField'
        argomentoLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'argomentoLabel'
        argomentoRivistaField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'argomentoRivistaField'
        argomentoRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'argomentoRivistaField'
        nomeResponsabileLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'nomeResonsabileLabel'
        nomeRField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'nomeRField'
        nomeRField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'nomeRField'
        cognomeResponsabileLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'cognomeResonsabileLabel'
        cognomeRField.setFont(controller.textFieldFont);    //imposta il font del JTextField 'cognomeRField'
        cognomeRField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'cognomeRField'
        editoreLabel1.setFont(controller.baseFontSize); //imposta il font della JLabel 'editoreLabel1'
        editoreRivistaField.setFont(controller.textFieldFont);  //imposta il font del JTextField 'editoreRivistaField'
        editoreRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JTextField 'editoreRivistaField'
        annoPubblicazioneLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'annoPubblicazioneLabel'
        annoPrivistaSpinner.setFont(controller.textFieldFont);  //imposta il font dello JSpinner 'annoPrivistaSpinner'
        annoPrivistaSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima dello JSpinner 'annoPrivistaSpinner'
        numeroLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'numeroLabel'
        numeroFascicoloCB.setFont(controller.textFieldFont);    //imposta il font del JComboBox 'numeroFascicoloCB'
        numeroFascicoloCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JComboBox 'numeroFascicoloCB'
        dataLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'dataLabel'
        dpFascicoloField.setFont(controller.textFieldFont); //imposta il font del JTextField 'dpFascicoloField'
        dpFascicoloField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'dpFascicoloField'
        calendarIssueIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));  //imposta la dimensione minima della JLabel 'calendarIssueIMG'
        calendarIssueIMG.setIcon(new ImageIcon(calendarImg));   //imposta l'icona della JLabel 'calendarIssueIMG' con l'immagine 'calendarImg'
        fruizioneLabel1.setFont(controller.baseFontSize);   //imposta il font della JLabel 'fruizioneLabel1'
        fruizoneFascicoloCB.setFont(controller.textFieldFont);  //imposta il font del JComboBox 'fruizioneFascicoloCB'
        fruizoneFascicoloCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JComboBox 'fruizioneFascicoloCB'
        quantitaLabel1.setFont(controller.baseFontSize);    //imposta il font della JLabel 'quantitaLabel1'
        quantitaFascicoloSpinner.setFont(controller.textFieldFont); //imposta il font dello JSpinner 'quantitaFascicoloSpinner'
        quantitaFascicoloSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima dello JSpinner 'quantitaFascicoloSpinner'

        articoliFascicoloLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'articoliFascicoloLabel'
        aggiungiArticoloBT.setFont(controller.baseFontSize);    //imposta il font del JButton 'aggiungiArticoloBT'

        inviaFascicoloBT.setFont(controller.baseFontSize);  //imposta il font del JButton 'inviaFascicoloBT'


        serieLabel.setFont(controller.impactFontSize);  //imposta il font della JLabel 'serieLabel'

        isbnSerieLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'isbnSerieLabel'
        isbnSerieField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'isbnSerieField'
        isbnSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'isbnSerieField'
        titoloLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'titoloLabel'
        titoloSerieField.setFont(controller.textFieldFont); //imposta il font del JTextField 'titoloSerieField'
        titoloSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'titoloSerieField'
        dataPubblicazioneLabel1.setFont(controller.baseFontSize);   //imposta il font della JLabel 'dataPubblicazioneLabel1'
        dataSerieField.setFont(controller.textFieldFont);   //imposta il font del JTextField 'dataSerieField'
        dataSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta del JTextField 'dataSerieField'
        calendarSerieIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));  //imposta la dimensione minima della JLabel 'calendarSerieIMG'
        calendarSerieIMG.setIcon(new ImageIcon(calendarImg));   //imposta l'icona della JLabel 'calendarSerieIMG' con l'immagine 'calendarImg'
        libriLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'libriLabel'
        spinnerLibriSerie.setFont(controller.textFieldFont);    //imposta il font dello JSpinner 'spinnerLibriSerie'
        spinnerLibriSerie.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta dello JSpinner 'spinnerLibriSerie'

        okSerieBT.setFont(controller.baseFontSize); //imposta il font del JButton 'okSerieBT'

        inviaSerieButton.setFont(controller.baseFontSize); //imposta il font del JButton 'inviaSerieBT'


        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //rimuove la decorazione del frame
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int)(controller.screenWidth/1.7777), (int) (controller.screenHeight/1.1612)); //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        bookPanel.setVisible(false);    //rende invisibile il JPanel 'bookPanel'
        seriePanel.setVisible(false);   //rende invisibile il JPanel 'seriePanel'
        issuePanel.setVisible(false);   //rende invisibile il JPanel 'issuePanel'

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

        libroBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                libroBT.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'libroBT'
            }
        });

        libroBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libroBT.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'libroBT'
            }
        });

        libroBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                bookPanel.setVisible(true); //rende visibile il JPannel 'bookPanel'
                seriePanel.setVisible(false);   //rende invisibile il JPannel 'seriePanel'
                issuePanel.setVisible(false);   //rende invisibile il JPannel 'issuePanel'
                questionPanel.setVisible(false);    //rende invisibile il JPannel 'questionPanel'

                contentPane.revalidate();   //aggiorna il contenuto del JPanel 'contentPane'
                contentPane.repaint();  //ridisegna il JPanel 'contentPane'
            }
        });

        serieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieBT.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'serieBT'
            }
        });

        serieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieBT.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'serieBT'
            }
        });

        serieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                isbnSeriePanel.removeAll(); //rimuove tutti i componenti del JPanel 'isbnSeriePanel'
                isbnLibroCount = 0; //azzera il numero di libri della serie che si sta inserendo
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue(), controller.baseFontSize, controller.textFieldFont);  //inizializza tutti i componenti necessari per inserire i libri della serie
                seriePanel.setVisible(true);    //rende visibile il JPanel 'seriePanel'
                bookPanel.setVisible(false);    //rende invisibile il JPanel 'bookPanel'
                issuePanel.setVisible(false);   //rende invisibile il JPanel 'issuePanel'
                questionPanel.setVisible(false);    //rende invisibile il JPanel 'questionPanel'
            }
        });

        fascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoloBT.setBackground(Color.decode("#cf9e29")); //imposta il colore dello sfondo del JButton 'fascicoloBT'
            }
        });

        fascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoloBT.setBackground(Color.decode("#FFD369")); //imposta il colore dello sfondo del JButton 'fascicoloBT'
            }
        });

        fascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                articoliFascicoliCount = 0; //azzera il numero di libri della serie che si sta inserendo
                issuePanel.setVisible(true);    //rende visibile il JPanel 'issuePanel'
                seriePanel.setVisible(false);   //rende invisibile il JPanel 'seriePanel'
                bookPanel.setVisible(false);    //rende invisibile il JPanel 'bookPanel'
                questionPanel.setVisible(false);    //rende invisibile il JPanel 'questionPanel'
            }
        });


        for (int i = 0; i < controller.listaLibri.size(); i++){ //scorre la lista di tutti i libri
            isbnLibroCB.addItem(controller.listaLibri.get(i).isbn); //aggiunge nel menu del JComboBox 'isbnLibroCB' l'ISBN del i-esimo libro
        }

        isbnLibroCB.setSelectedIndex(-1);   //deseleziona tutti gli elementi del menu del JComboBox 'isbnLibroCB'

        bookScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        Object comp1 = linguaLibroCB.getUI().getAccessibleChild(linguaLibroCB, 0);  //ComboBoxUI del JComboBox 'linguaLibroCB' per personalizzarlo

        if(comp1 instanceof JPopupMenu){    //controlla se 'comp1' è un JPopupMenu
            JPopupMenu popup = (JPopupMenu) comp1;  //JPopupMenu del JComboBox 'linguaLibroCB'
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);   //primo componente della barra di scorrimento di 'popup'

            scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());  //imposta lo stile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49)); //imposta il colore della parte mobile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));   //imposta il colore della parte fissa (cioè lo sfondo) della barra di scorrimento
        }

        Object comp2 = isbnLibroCB.getUI().getAccessibleChild(isbnLibroCB, 0);  //ComboBoxUI del JComboBox 'isbnLibroCB' per personalizzarlo

        if(comp2 instanceof JPopupMenu){    //controlla se 'comp2' è un JPopupMenu
            JPopupMenu popup = (JPopupMenu) comp2;  //JPopupMenu del JComboBox 'isbnLibroCB'
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);   //primo componente della barra di scorrimento di 'popup'

            scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());  //imposta lo stile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49)); //imposta il colore della parte mobile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));   //imposta il colore della parte fissa (cioè lo sfondo) della barra di scorrimento
        }

        Component editorComp = isbnLibroCB.getEditor().getEditorComponent();    //editor del JComboBox 'isbnLibroCB' per personalizzarlo

        if (editorComp instanceof JTextField) { //controlla se 'editorComp' è un JTextField
            JTextField textField = (JTextField) editorComp; //JtextField del JComboBox 'isbnLibroCB'

            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
        }

        isbnLibroCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isbnLibroCB.getSelectedIndex() < 0 || isbnLibroCB.getSelectedIndex() > (controller.listaLibri.size()-1)) //controlla se l'indice selezionato nel JComboBox 'isbnLibroCB' non è in [0, controller.listaLibri.size()-1]
                {
                    titoloLibroField.setEnabled(true);  //abilita il JTextField 'titoloLibroField'
                    genereLibroField.setEnabled(true);  //abilita il JTextField 'genereLibroField'
                    linguaLibroCB.setEnabled(true); //abilita il JComboBox 'linguaLibroCB'
                    editoreLibroField.setEnabled(true); //abilita il JTextField 'editoreLibroField'
                    dataLibroField.setEnabled(true);    //abilita il JTextField 'dataLibroField'
                    calendarLibroIMG.setEnabled(true);  //abilita la JLabel 'calendarLibroIMG'
                    autoriLibroLabel.setVisible(true);  //rende visibile la JLabel 'autoriLibroLabel'
                    autoreLibroButton.setVisible(true); //rende visibile il JButton 'autoreLibroButton'

                    titoloLibroField.setText("");   //svuota il testo del JTextField 'titoloLibroField'
                    genereLibroField.setText("");   //svuota il testo del JTextField 'genereLibroField'
                    linguaLibroCB.setSelectedItem(1);   //seleziona il primo elemento del JComboBox 'linguaLibroCB'
                    editoreLibroField.setText("");  //svuota il testo del JTextField 'editoreLibroField'
                    dataLibroField.setText(""); //svuota il testo del JTextField 'dataLibroField'
                }else {
                    titoloLibroField.setEnabled(false); //disabilita il JTextField 'titoloLibroField'
                    genereLibroField.setEnabled(false); //disabilita il JTextField 'genereLibroField'
                    linguaLibroCB.setEnabled(false);    //disabilita il JComboBox 'linguaLibroCB'
                    editoreLibroField.setEnabled(false);    //disabilita il JTextField 'editoreLibroField'
                    dataLibroField.setEnabled(false);   //disabilita il JTextField 'dataLibroField'
                    calendarLibroIMG.setEnabled(false); //disabilita la JLabel 'calendarLibroIMG'
                    autoriLibroLabel.setVisible(false); //rende invisibile la JLabel 'autoriLibroLabel
                    autoreLibroButton.setVisible(false);    //rende invisibile il JButton 'autoreLibroButton'
                    autoriLibroPanel.removeAll();   //elimina tutti i componenti del JPanel 'autoriLibroPanel'
                    autoreLibroCount = 0;   //azzera il numero di autori del libro che si sta inserendo

                    titoloLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).titolo); //imposta il testo del JTextField 'titoloLibroField' con il titolo del libro selezionato
                    genereLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).genere); //imposta il testo del JTextField 'genereLibroField' con il genere del libro selezionato
                    linguaLibroCB.setSelectedItem((controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).lingua));  //imposta il testo del JComboBox 'linguaLibroCB' con la lingua del libro selezionato
                    editoreLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).editore);   //imposta il testo JTextField 'editoreLibroField' con l'editore del libro selezionato
                    dataLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).dataPubblicazione.toString()); //imposta il testo JTextField 'dataLibroField' la data di pubblicazione del libro selezionato
                }
            }
        });

        bookScrollPanel.setBackground(new Color(0x222831)); //imposta il colore dello sfondo del JScrollPane 'bookScrollPanel'
        bookScrollPanel.setBorder(BorderFactory.createEmptyBorder());   //toglie il bordo del JScrollPane 'bookScrollPanel'
        bookScrollPanel.getViewport().setBackground(new Color(0x222831));   //imposta il colore dello sfondo della parte visibile del JScrollPane 'bookScrollPanel'

        datePickerLibro = new DatePicker(calendarLibroIMG);

        calendarLibroIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarLibroIMG.isEnabled() == true) {  //controlla se è stato disabilitato il calendario
                    datePickerLibro.d.setVisible(true); //rende visibile il calendario
                    dataLibroField.setText(datePickerLibro.setPickedDate());    //imposta il testo del JTextField 'dataLibroField' con la data scelta dall'utente
                }
            }
        });

        quantitaLibroSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaLibroSpinner.getValue() > 9999){    //controlla se il valore dello JSpinner 'quantitaLibroSpinner' è maggiore di 9999
                    quantitaLibroSpinner.setValue(9999);    //imposta il valore di 'quantitaLibroSpinner' a 9999
                }

                if((int)quantitaLibroSpinner.getValue() < 0) {  //controlla se il valore dello JSpinner 'quantitaLibroSpinner' è minore di  0
                    quantitaLibroSpinner.setValue(0);   //imposta il valore di 'quantitaLibroSpinner' a 0
                }
            }
        });

        autoreLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                autoreLibroButton.setBackground(Color.decode("#cf9e29"));   //imposta il colore dello sfondo del JButton 'autoreLibroButton'
            }
        });

        autoreLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                autoreLibroButton.setBackground(Color.decode("#FFD369"));   //imposta il colore dello sfondo del JButton 'autoreLibroButton'
            }
        });

        autoreLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                autoreLibroCount++; //incrementa il numero di autori del libro che si sta inserendo
                initComponentsAutoreLibro(controller.baseFontSize, controller.textFieldFont);   //inizializza tutti i componenti necessari per inserire gli autori del libro
                autoriLibroPanel.revalidate();  //aggiorna il contenuto del JPanel 'autoriLibroPanel'
                autoriLibroPanel.repaint(); //ridisegna il JPanel 'autoriLibroPanel'
            }
        });

        inviaLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaLibroButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'inviaLibroButton'
            }
        });

        inviaLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaLibroButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'inviaLibroButton'
            }
        });

        inviaLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (isbnLibroCB.getSelectedItem() == null || isbnLibroCB.getSelectedItem().equals("")){ //controlla se non è stato selezionato nessun libro oppure non è stato inserito nessun ISBN
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Seleziona o crea un nuovo libro!");  //mostra un messaggio di errore
                } else {
                    if (titoloLibroField.getText().isBlank() || linguaLibroCB.getSelectedItem() == null || linguaLibroCB.getSelectedItem().equals("") || editoreLibroField.getText().isBlank() || genereLibroField.getText().isBlank() || dataLibroField.getText().isBlank()){  //controlla se non è stato inserito uno dei campi obbligatori
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi!");  //mostra un messaggio di errore
                    } else {
                        if (titoloLibroField.isEnabled() == false) {    //controlla se è stato disabilitato il JTextField 'titoloLibroField'
                            controller.nuovoLibro = controller.listaLibri.get(isbnLibroCB.getSelectedIndex());  //inizializza 'controller.nuovoLibro' con il libro selezionato

                            if(controller.insertPossessoL((int) quantitaLibroSpinner.getValue(), fruizioneLibroCB.getSelectedItem().toString()) == false){  //controlla se il libro è già posseduto dalla libreria
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro è già presente in Libreria");   //mostra un messaggio di errore
                            } else {
                                model.setRowCount(0);   //rimuove tutte le righe della tabella

                                controller.getPossessoLibreria();   //aggiorna le ArrayList contenenti gli elementi posseduti dalla libreria

                                if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se la libreria possiede dei libri
                                    for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria
                                        if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) {    //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                        } else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0){   //controlla se l'i-esimo libro non è disponibile
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile" , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                        } else {
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita, controller.possessoLLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }
                                    }
                                }

                                if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se la libreria possiede delle serie
                                    for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria
                                        if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) {    //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                        } else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0){   //controlla se l'i-esima serie non è disponibile
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                        } else {
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita, controller.possessoSLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }
                                    }
                                }

                                if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se la libreria possiede dei fascicoli
                                    for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria
                                        if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                        } else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0){   //controlla se l'i-esimo fascicolo non è disponibile
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile" , controller.possessoFLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                        } else {
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita, controller.possessoFLibreria.get(i).fruizione});   //aggiunge una nuova riga nella tabella
                                        }
                                    }
                                }

                                frame.setVisible(false);    //rende invisibile il frame
                                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                                frame.dispose();
                                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                            }
                        } else {
                            if (controller.creaLibro(isbnLibroCB.getSelectedItem().toString(), titoloLibroField.getText().replace("'", "’"), genereLibroField.getText().replace("'", "’"), linguaLibroCB.getSelectedItem().toString(), editoreLibroField.getText().replace("'", "’"), dataLibroField.getText()) == false) { //controlla se il libro esiste già
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro già esiste");   //mostra un messaggio di errore
                            } else {
                                if (takeAutoriLibro(controller) == false) { //controlla se non sono stati inseriti degli autori al nuovo libro
                                    controller.eliminaLibro();  //elimina il nuovo libro
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro non ha autori");    //mostra un messaggio di errore
                                } else {
                                    controller.listaLibri.add(controller.nuovoLibro);   //aggiunge il nuovo libro nell'ArrayList dei libri

                                    if(controller.insertPossessoL((int) quantitaLibroSpinner.getValue(), fruizioneLibroCB.getSelectedItem().toString()) == false){  //controlla se il libro è già posseduto dalla libreria
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro è già presente in Libreria");   //mostra un messaggio di errore
                                    } else {
                                        model.setRowCount(0);   //rimuove tutte le righe della tabella

                                        controller.getPossessoLibreria();   //aggiorna le ArrayList contenenti gli elementi posseduti dalla libreria

                                        if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se la libreria possiede dei libri
                                            for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria
                                                if(controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                                                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                                } else if(controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0){   //controlla se l'i-esimo libro non è disponibile
                                                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile", controller.possessoLLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                                } else {
                                                    model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita , controller.possessoLLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                                }
                                            }
                                        }

                                        if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se la libreria possiede delle serie
                                            for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria
                                                if(controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) {    //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                                                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                                } else if(controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) {  //controlla se l'i-esima serie non è disponibile
                                                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile" , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                                } else{
                                                    model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita , controller.possessoSLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                                }
                                            }
                                        }

                                        if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se la libreria possiede dei fascicoli
                                            for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria
                                                if(controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")){ //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                                                    model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                                } else if(controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) {  //controlla se l'i-esimo fascicolo non è disponibile
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
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        isbnLibri = new ArrayList<>();  //ISBN dei libri della serie che si sta inserendo

        for (String isbnLibro: controller.titoloLibriLibreria){ //scorre 'controller.titoloLibriLibreria' che contiene i titoli (e gli ISBN) dei libri posseduti dalla libreria
            if(!isbnLibri.contains(isbnLibro.substring(0, 17))) {   //controlla se 'isbnLibri' non contiene l'ISBN del libro attuale
                isbnLibri.add(isbnLibro.substring(0, 17));  //aggiunge 'isbnLibro' in 'isbnLibri'
            }
        }

        serieScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine;
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

        serieScrollPanel.setBackground(new Color(0x222831));    //imposta il colore dello sfondo del JScrollPanel 'serieScrollPanel'
        serieScrollPanel.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JScrollPane 'serieScrollPanel'
        serieScrollPanel.getViewport().setBackground(new Color(0x222831));  //imposta il colore dello sfondo della parte visibile del JScrollPane 'serieScrollPanel'

        datePickerSerie = new DatePicker(calendarLibroIMG);

        calendarSerieIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarSerieIMG.isEnabled() == true) {  //controlla se è stato abilitato il calendario
                    datePickerSerie.d.setVisible(true); //rende visibile il calendario
                    dataSerieField.setText(datePickerSerie.setPickedDate());    //imposta il testo del JTextField 'dataSerieField' con la data scelta dall'utente
                }
            }
        });

        spinnerLibriSerie.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)spinnerLibriSerie.getValue() > 999) {   //controlla se il valore dello JSpinner 'spinnerLibriSerie' è maggiore di 9999
                    spinnerLibriSerie.setValue(999);    //imposta il valore di 'spinnerLibriSerie' a 9999
                }

                if((int)spinnerLibriSerie.getValue() < 0){  //controlla se il valore dello JSpinner 'spinnerLibriSerie' è minore di  9999
                    spinnerLibriSerie.setValue(0);  //imposta il valore di 'spinnerLibriSerie' a 0
                }
            }
        });

        okSerieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                okSerieBT.setBackground(Color.decode("#cf9e29"));   //imposta il colore dello sfondo del JButton 'okSerieBT'
            }
        });

        okSerieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                okSerieBT.setBackground(Color.decode("#FFD369"));   //imposta il colore dello sfondo del JButton 'okSerieBT'
            }
        });

        okSerieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                isbnSeriePanel.removeAll(); //elimina tutti i componenti del JPanel 'isbnSeriePanel'
                isbnLibroCount = 0; //azzera il numero di libri della serie che si sta inserendo
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue(),controller.baseFontSize, controller.textFieldFont);   //inizializza tutti i componenti necessari per inserire i libri della serie
                isbnSeriePanel.revalidate();    //aggiorna il contenuto del JPanel 'isbnSeriePanel'
                isbnSeriePanel.repaint();   //ridisegna il JPanel 'isbnSeriePanel'
            }
        });

        inviaSerieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaSerieButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'okSerieBT'
            }
        });

        inviaSerieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaSerieButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'okSerieBT'
            }
        });

        inviaSerieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(isbnSerieField.getText().isBlank() || titoloSerieField.getText().isBlank() || dataSerieField.getText().isBlank()) {  //controlla se almeno uno dei campi non è stato compilato
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi");   //mostra un messaggio di errore
                } else {
                    ArrayList<String> libriISBN = takeISBN();   //ISBN dei libri della serie che si sta inserendo

                    if (libriISBN != null) {    //controlla se è stata inizializzata 'libriISBN'
                        boolean error = false;  //flag di errore
                        int h = 0;  //contatore

                        while (error == false && h < libriISBN.size()) {    //scorre 'libriISBN'
                            if (controller.getDataLibro(libriISBN.get(h)).after(Date.valueOf(dataSerieField.getText())) == true) {  //controlla se il libro h-esimo è stato pubblicato dopo la serie
                                error = true;   //segnala l'errore
                            }

                            h++;    //incrementa il contatore
                        }

                        if (error == true) {    //controlla se c'è qualche libro pubblicato dopo la serie
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Uno o più libri sono pubblicati dopo la serie"); //mostra un messaggio d'errore
                        } else {
                            if (controller.creaSerie(libriISBN, isbnSerieField.getText().toString(), titoloSerieField.getText().toString().replace("'", "’"), dataSerieField.getText().toString()) == false) {  //controlla se la serie esiste già
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa serie già esiste");   //mostra un messaggio d'errore
                            } else {
                                controller.listaSerie.add(controller.nuovoSerie);   //aggiunge la nuova serue nell'ArrayList delle serie

                                model.setRowCount(0);   //rimuove tutte le righe della tabella

                                controller.getPossessoLibreria();   //aggiorna le ArrayList contenenti gli elementi posseduti dalla libreria

                                if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {   //controlla se la libreria possiede dei libri
                                    for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {   //scorre la lista dei titoli dei libri posseduti dalla libreria
                                        if (controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro")) {   //controlla se l'i-esimo libro è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                        }else if (controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0) {  //controlla se l'i-esimo libro non è disponibile
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile", controller.possessoLLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }else {
                                            model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita, controller.possessoLLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }
                                    }
                                }

                                if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {   //controlla se la libreria possiede delle serie
                                    for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {   //scorre la lista dei titoli delle serie possedute dalla libreria
                                        if (controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro")) { //controlla se l'i-esima serie è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});  //aggiunge una nuova riga nella tabella
                                        }else if (controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0) { //controlla se l'i-esima serie non è disponibile
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile", controller.possessoSLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }else {
                                            model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita, controller.possessoSLibreria.get(i).fruizione}); //aggiunge una nuova riga nella tabella
                                        }
                                    }
                                }

                                if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) { //controlla se la libreria possiede dei fascicoli
                                    for (int i = 0; i < controller.fascicoliLibreria.size(); i++) { //scorre la lista dei fascicoli posseduti dalla libreria
                                        if (controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro")) {   //controlla se l'i-esimo fascicolo è disponibile in modalità digitale o audiolibro
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});    //aggiunge una nuova riga nella tabella
                                        }else if (controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0) {  //controlla se l'i-esimo fascicolo non è disponibile
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile", controller.possessoFLibreria.get(i).fruizione});   //aggiunge una nuova riga nella tabella
                                        }else {
                                            model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita, controller.possessoFLibreria.get(i).fruizione});   //aggiunge una nuova riga nella tabella
                                        }
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
        });


        titoloRivistaCB.setSelectedIndex(-1);   //deseleziona tutti gli elementi del menu del JComboBox 'titoloRivistaCBB'
        issueScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorse /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorse /up.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorse /up.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorse /up.png
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

        Object comp3 = titoloRivistaCB.getUI().getAccessibleChild(titoloRivistaCB, 0);  //ComboBoxUI del JComboBox 'titoloRivistaCB' per personalizzarlo

        if(comp3 instanceof JPopupMenu){    //controlla se 'comp3' è un JPopupMenu
            JPopupMenu popup = (JPopupMenu) comp3;  //JPopupMenu del JComboBox 'titoloRivistaCB'
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);   //primo componente della barra di scorrimento di 'popup'

            scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());  //imposta lo stile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49)); //imposta il colore della parte mobile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));   //imposta il colore della parte fissa (cioè lo sfondo) della barra di scorrimento
        }

        Component editorComp2 = titoloRivistaCB.getEditor().getEditorComponent();   //editor del JComboBox 'titoloRivistaCB' per personalizzarlo

        if (editorComp2 instanceof JTextField) {    //controlla se 'editorComp' è un JTextField
            JTextField textField = (JTextField) editorComp2;    //JtextField del JComboBox 'titoloRivistaCB'

            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
        }

        Object comp4 = numeroFascicoloCB.getUI().getAccessibleChild(numeroFascicoloCB, 0);  //ComboBoxUI del JComboBox 'numeroFascicoloCB' per personalizzarlo

        if(comp4 instanceof JPopupMenu){    //controlla se 'comp4' è un JPopupMenu
            JPopupMenu popup = (JPopupMenu) comp4;  //JPopupMenu del JComboBox 'numeroFascicoloCB'
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);   //primo componente della barra di scorrimento di 'popup'

            scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());  //imposta lo stile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49)); //imposta il colore della parte mobile della barra di scorrimento
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));   //imposta il colore della parte fissa (cioè lo sfondo) della barra di scorrimento
        }

        Component editorComp3 = numeroFascicoloCB.getEditor().getEditorComponent(); //editor del JComboBox 'numeroFascicoloCB' per personalizzarlo

        if (editorComp3 instanceof JTextField) {    //controlla se 'editorComp' è un JTextField
            JTextField textField = (JTextField) editorComp3;    //JtextField del JComboBox 'numeroFascicoloCB'

            textField.setBackground(new Color(0xFFD369));   //imposta il colore dello sfondo di 'textField'
            textField.setForeground(new Color(0x222831));   //imposta il colore del testo di 'textField'
        }

        issueScrollPanel.setBackground(new Color(0x222831));    //imposta il colore dello sfondo del JScrollPanel 'issueScrollPanel'
        issueScrollPanel.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JScrollPane 'issueScrollPanel'
        issueScrollPanel.getViewport().setBackground(new Color(0x222831));  //imposta il colore dello sfondo della parte visibile del JScrollPane 'issueScrollPanel'

        datePickerFascicoli = new DatePicker(calendarIssueIMG);

        calendarIssueIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarIssueIMG.isEnabled() == true) {  //controlla se è stato abilitato il calendario
                    datePickerFascicoli.d.setVisible(true); //rende visibile il calendario
                    dpFascicoloField.setText(datePickerFascicoli.setPickedDate());  //imposta il testo del JTextField 'dpFascicoloField' con la data scelta dall'utente
                }
            }
        });

        annoPrivistaSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)annoPrivistaSpinner.getValue() > Year.now().getValue()) {   //controlla se l'anno selezionato nello JSpinner 'annoPrivistaSpinner' è maggiore dell'anno attuale
                    annoPrivistaSpinner.setValue(Year.now().getValue());    //imposta il valore di 'annoPrivistaSpinner' all'anno attuale
                }

                if((int)annoPrivistaSpinner.getValue() < 1900){ //controlla se l'anno selezionato nello JSpinner 'annoPrivistaSpinner' è minore del 1900
                    annoPrivistaSpinner.setValue(1900); //imposta il valore di 'annoPrivistaSpinner' al 1900
                }
            }
        });

        quantitaFascicoloSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaFascicoloSpinner.getValue() > 9999) {   //controlla se il valore dello JSpinner 'quantitaFascicoloSpinner' è maggiore di 9999
                    quantitaFascicoloSpinner.setValue(9999);    //imposta il valore di 'quantitaFascicoloSpinner' a 9999
                }

                if((int)quantitaFascicoloSpinner.getValue() < 0) {  //controlla se il valore dello JSpinner 'quantitaFascicoloSpinner' è minore di 0
                    quantitaFascicoloSpinner.setValue(0);   //imposta il valore di 'quantitaFascicoloSpinner' a 0
                }
            }
        });

        aggiungiArticoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aggiungiArticoloBT.setBackground(Color.decode("#cf9e29"));  //imposta il colore dello sfondo del JButton 'aggiungiArticoloBT'
            }
        });

        aggiungiArticoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                aggiungiArticoloBT.setBackground(Color.decode("#FFD369"));  //imposta il colore dello sfondo del JButton 'aggiungiArticoloBT'
            }
        });

        aggiungiArticoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                articoliFascicoliCount++;   //incrementa il numero di articoli nel fascicolo che si sta inserendo
                initComponentsArticoli(controller); //inizializza tutti i componenti necessari per inserire gli articoli del fascicolo
                articoliPanel.revalidate(); //aggiorna il contenuto del JPanel 'articoliPanel'
                articoliPanel.repaint();    //ridisegna il JPanel 'articoliPanel'
            }
        });

        for (int i = 0; i < controller.listaRiviste.size(); i++) {   //scorre la lista di tutte le riviste
            titoloRivistaCB.addItem(controller.listaRiviste.get(i).titolo); //aggiunge nel menu del JComboBox 'titoloRivistaCB' il titolo dell'i-esima rivista
        }

        titoloRivistaCB.setSelectedIndex(-1);   //deseleziona tutti gli elementi del menu del JComboBox 'titoloRivistaCB'

        titoloRivistaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(titoloRivistaCB.getSelectedIndex() < 0 || titoloRivistaCB.getSelectedIndex() > controller.listaRiviste.size()){  //controlla se l'indice selezionato nel JComboBox 'titoloRivistaCB' non è in [0, controller.listaRiviste.size()]
                    issnRivistaField.setText("");   //svuota il testo del JTextField 'issnRivistaField'
                    issnRivistaField.setEnabled(true);  //abilita il JTextField 'issnRivistaField'

                    argomentoRivistaField.setText("");  //svuota il testo del JTextField 'argomentoRivistaField'
                    argomentoRivistaField.setEnabled(true); //abilita il JTextField 'argomentoRivistaField'

                    nomeRField.setText(""); //svuota il testo del JTextField 'nomeRField'
                    nomeRField.setEnabled(true);    //abilita il JTextField 'nomeRField'

                    cognomeRField.setText("");  //svuota il testo del JTextField 'cognomeRField'
                    cognomeRField.setEnabled(true); //abilita il JTextField 'cognomeRField'

                    editoreRivistaField.setText("");    //svuota il testo del JTextField 'editoreRivistaField'
                    editoreRivistaField.setEnabled(true);   //abilita il JTextField 'editoreRivistaField'

                    annoPrivistaSpinner.setValue(Year.now().getValue());    //imposta il valore dello JSpinner 'annoPrivistaSpinner'
                    annoPrivistaSpinner.setEnabled(true);   //abilita lo JSpinner 'annoPrivistaSpinner'
                } else {
                    issnRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).issn); //imposta il testo del JTextField 'issnRivistaField' con il titolo della rivista selezionata
                    issnRivistaField.setEnabled(false); //disabilita il JTextField 'issnRivistaField'

                    argomentoRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).argomento);   //imposta il testo del JTextField 'argomentoRivistaField' con l'argomento della rivista selezionata
                    argomentoRivistaField.setEnabled(false);    //disabilita il JTextField 'argomentoRivistaField'

                    nomeRField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.substring(0,controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.indexOf('#')));    //imposta il testo del JTextField 'nomeRField' con il nome della rivista selezionata
                    nomeRField.setEnabled(false);   //disabilita il JTextField 'nomeRField'

                    cognomeRField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.substring(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.indexOf('#')+1, controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.length()));  //imposta il testo del JTextField 'cognomeRField' con il cognome della rivista selezionata
                    cognomeRField.setEnabled(false);    //disabilita il JTextField 'cognomeRField'

                    editoreRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).editore);   //imposta il testo del JTextField 'editoreRivistaField' con l'editore della rivista selezionata
                    editoreRivistaField.setEnabled(false);  //disabilita il JTextField 'editoreRivistaField'

                    annoPrivistaSpinner.setValue(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).annoPubblicazione);    //imposta il valore dello JSpinner 'annoPrivistaSpinner' con l'anno di pubblicazione della rivista selezionata
                    annoPrivistaSpinner.setEnabled(false);  //abilita lo JSpinner 'annoPrivistaSpinner'
                    numeroFascicoloCB.removeAllItems(); //rimuove tutti gli elementi nel JComboBox 'numeroFascicoloCB'

                    controller.getFascicoliRivista((controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).issn)); //inizializza 'controller.listaFascicoliRivista' contenente i fascicoli della rivista selezionata

                    for (int i = 0; i < controller.listaFascicoliRivista.size(); i++){  //scorre la lista di tutti i fascicoli della rivista selezionata
                         numeroFascicoloCB.addItem(controller.listaFascicoliRivista.get(i).numero); //aggiunge nel menu del JComboBox 'numeroFascicoloCB' il numero del i-esimo fscicolo  della rivista selezionata
                    }

                    numeroFascicoloCB.setSelectedIndex(-1); //deseleziona tutti gli elementi del menu del JComboBox 'numeroFascicoloCB'
                }
            }
        });

        numeroFascicoloCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numeroFascicoloCB.getSelectedIndex() >= 0 && numeroFascicoloCB.getSelectedIndex() <= controller.listaFascicoliRivista.size()){   //controlla se l'indice selezionato nel JComboBox 'numeroFascicoloCB'  è in [0,  controller.listaFascicoliRivista.size()]
                    dpFascicoloField.setText(String.valueOf(controller.listaFascicoliRivista.get(numeroFascicoloCB.getSelectedIndex()).dataPubblicazione)); //imposta il testo del JTextField 'dpFascicoloField' con la data di pubblicazione del fascicolo selezionato
                    dpFascicoloField.setEnabled(false); //disabilita il JTextField 'dpFascicoloField'

                    calendarIssueIMG.setEnabled(false); //disabilita la JLabel 'calendarIssueIMG'
                    articoliFascicoloLabel.setVisible(false);   //rende invisibile la JLabel 'articoliFascicoloLabel'
                    aggiungiArticoloBT.setVisible(false);   //rende invisibile il JButton 'aggiungiArticoloBT'
                } else {
                    dpFascicoloField.setText("");   //svuota il testo del JTextField 'dpFascicoloField'
                    dpFascicoloField.setEnabled(true);  //abilita il JTextField 'dpFascicoloField'

                    calendarIssueIMG.setEnabled(true);  //abilita la JLabel 'calendarIssueIMG'
                    articoliFascicoloLabel.setVisible(true);    //rende visibile la JLabel 'articoliFascicoloLabel'
                    aggiungiArticoloBT.setVisible(true);    //rende visibile il JButton 'aggiungiArticoloBT'
                }
            }
        });

        inviaFascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (titoloRivistaCB.getSelectedItem() == null || titoloRivistaCB.getSelectedItem().equals("")) {    //controlla se non è stato selezionato nessuna rivista oppure non è stato inserito nessun ISSN
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Seleziona o crea una Rivista!"); //mostra un messaggio di errore
                } else {
                    if (issnRivistaField.getText().isBlank() || nomeRField.getText().isBlank() || cognomeRField.getText().isBlank() || editoreRivistaField.getText().isBlank() || argomentoRivistaField.getText().isBlank()) {  //controlla se non è stato inserito uno dei campi obbligatori
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi!");  //mostra un messaggio di errore
                    } else {
                        if (issnRivistaField.isEnabled() == true) { //controlla se è stato abilitato il JTextField 'issnRivistaField'
                            if (controller.creaRivista(titoloRivistaCB.getSelectedItem().toString().replace("'", "’"), issnRivistaField.getText(), argomentoRivistaField.getText().replace("'", "’"), nomeRField.getText().replace("'", "’"), cognomeRField.getText().replace("'", "’"), editoreRivistaField.getText().replace("'", "’"), (int) annoPrivistaSpinner.getValue()) == false) { //controlla se la rivista esiste già
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa rivista già esiste"); //mostra un messaggio di errore
                            } else {
                                controller.listaRiviste.add(controller.nuovaRivista);   //aggiunge la nuova rivista in 'controller.listaRiviste'

                                if (dpFascicoloField.isEnabled() == true) { //controlla se è stato abilitato il JTextField 'dpFascicoloField'
                                    if (articoliFascicoliCount <= 0 || numeroFascicoloCB.getSelectedItem() == null || dpFascicoloField.getText().isBlank() == true) {   //controlla se il nuovo fascicolo non ha articoli oppure qualche campo non è stato inserito
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo non ha articoli o non è presente il numero o la data!");    //mostra un messaggio di errore
                                        controller.eliminaRivista();    //elimina la nuova rivista
                                    } else {
                                        if ((int) annoPrivistaSpinner.getValue() <= Date.valueOf(dpFascicoloField.getText()).getYear()+1900) {  //controlla se l'anno selezionato nello JSpinner 'annoPrivistaSpinner' è minore o uguale a quello del fasciciolo selezionato
                                            if (controller.creaFascicolo(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()), dpFascicoloField.getText()) == false) {   //controlla se il fascicolo esiste già
                                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo già esiste!");  //mostra un messaggio di errore
                                                controller.eliminaRivista();    //elimina la nuova rivista
                                            } else {
                                                if (takeArticoli(controller, true) == true) {   //controlla se l'artiolo è stato creato correttamente
                                                    controller.listaFascicoli.add(controller.nuovoFascicolo);   //aggiunge il nuovo fascicolo in 'controller.listaFascicoli'
                                                    inserimentoEaggiornamentoF(controller, model, true, frameC);    //inserisce il nuovo fascicolo e aggiorna gli elementi posseduti dalla libreria
                                                }
                                            }
                                        }else {
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è pubblicato prima della rivista!");    //mostra un messaggio di errore
                                        }
                                    }
                                } else {
                                    controller.nuovoFascicolo = controller.listaFascicoliRivista.get(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()));  //inizializza 'controller.nuovoFascicolo' con il fascicolo selezionato
                                    inserimentoEaggiornamentoF(controller, model, false, frameC);   //inserisce il nuovo fascicolo e aggiorna gli elementi posseduti dalla libreria
                                }
                            }
                        } else {
                            controller.nuovaRivista = controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex());  //inizializza 'controller.nuovoRivista' con la rivista selezionata

                            if (dpFascicoloField.isEnabled() == true) { //controlla se è stato abilitato il JTextField 'dpFascicoloField'
                                if (articoliFascicoliCount <= 0 || numeroFascicoloCB.getSelectedItem() == null || dpFascicoloField.getText().isBlank() == true) {   //controlla se il nuovo fascicolo non ha articoli oppure qualche campo non è stato inserito
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo non ha articoli o non è presente il numero o la data!");    //mostra un messaggio di errore
                                } else {
                                    if ((int) annoPrivistaSpinner.getValue() <= Date.valueOf(dpFascicoloField.getText()).getYear()+1900) {  //controlla se l'anno selezionato nello JSpinner 'annoPrivistaSpinner' è minore o uguale a quello del fasciciolo selezionato
                                        if (controller.creaFascicolo(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()), dpFascicoloField.getText()) == false) {   //controlla se il fascicolo esiste già
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo già esiste!");  //mostra un messaggio di errore
                                        } else {
                                            if (takeArticoli(controller, false) == true) {  //controlla se l'artiolo è stato creato correttamente
                                                controller.listaFascicoli.add(controller.nuovoFascicolo);   //aggiunge il nuovo fascicolo in 'controller.listaFascicoli'
                                                inserimentoEaggiornamentoF(controller, model, false, frameC);   //inserisce il nuovo fascicolo e aggiorna gli elementi posseduti dalla libreria
                                            }
                                        }
                                    }
                                    else {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è pubblicato prima della rivista!");    //mostra un messaggio di errore
                                    }
                                }
                            } else {
                                controller.nuovoFascicolo = controller.listaFascicoliRivista.get(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()));  //inizializza 'controller.nuovoFascicolo' con il fascicolo selezionato
                                inserimentoEaggiornamentoF(controller, model, false, frameC);   //inserisce il nuovo fascicolo e aggiorna gli elementi posseduti dalla libreria
                            }
                        }
                    }
                }
            }
        });
    }

    private void inserimentoEaggiornamentoF(Controller controller, DefaultTableModel model, boolean nuova_rivista, JFrame frameC){
        if (controller.insertPossessoF((int) quantitaFascicoloSpinner.getValue(), fruizoneFascicoloCB.getSelectedItem().toString()) == false) {
            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa Fascicolo è già presente in Libreria");

            if (nuova_rivista == true){
                controller.eliminaRivista();
            }
        } else {
            model.setRowCount(0);

            controller.getPossessoLibreria();

            if (controller.titoloLibriLibreria != null && controller.possessoLLibreria != null) {
                for (int i = 0; i < controller.titoloLibriLibreria.size(); i++) {

                    if (controller.possessoLLibreria.get(i).fruizione.equals("Digitale") || controller.possessoLLibreria.get(i).fruizione.equals("AudioLibro"))
                        model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "∞", controller.possessoLLibreria.get(i).fruizione});
                    else if (controller.possessoLLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoLLibreria.get(i).quantita == 0)
                        model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), "Non Diponibile", controller.possessoLLibreria.get(i).fruizione});
                    else
                        model.addRow(new Object[]{controller.titoloLibriLibreria.get(i), controller.possessoLLibreria.get(i).quantita, controller.possessoLLibreria.get(i).fruizione});
                }
            }

            if (controller.titoloSerieLibreria != null && controller.possessoSLibreria != null) {
                for (int i = 0; i < controller.titoloSerieLibreria.size(); i++) {

                    if (controller.possessoSLibreria.get(i).fruizione.equals("Digitale") || controller.possessoSLibreria.get(i).fruizione.equals("AudioLibro"))
                        model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "∞", controller.possessoSLibreria.get(i).fruizione});
                    else if (controller.possessoSLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoSLibreria.get(i).quantita == 0)
                        model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), "Non Diponibile", controller.possessoSLibreria.get(i).fruizione});
                    else
                        model.addRow(new Object[]{controller.titoloSerieLibreria.get(i), controller.possessoSLibreria.get(i).quantita, controller.possessoSLibreria.get(i).fruizione});
                }
            }

            if (controller.fascicoliLibreria != null && controller.possessoSLibreria != null) {
                for (int i = 0; i < controller.fascicoliLibreria.size(); i++) {

                    if (controller.possessoFLibreria.get(i).fruizione.equals("Digitale") || controller.possessoFLibreria.get(i).fruizione.equals("AudioLibro"))
                        model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "∞", controller.possessoFLibreria.get(i).fruizione});
                    else if (controller.possessoFLibreria.get(i).fruizione.equals("Cartaceo") && controller.possessoFLibreria.get(i).quantita == 0)
                        model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, "Non Diponibile", controller.possessoFLibreria.get(i).fruizione});
                    else
                        model.addRow(new Object[]{controller.fascicoliLibreria.get(i).rivista.titolo + " N°" + controller.fascicoliLibreria.get(i).numero, controller.possessoFLibreria.get(i).quantita, controller.possessoFLibreria.get(i).fruizione});
                }
            }

            frame.setVisible(false);
            frameC.setEnabled(true);
            frame.dispose();
            frameC.toFront();
        }
    }

    private boolean takeAutoriLibro(Controller controller) {
        int fieldCount = 0;
        int n_autori = 0;
        String nome = "";
        String cognome = "";
        String nazionalità = "";
        String dn = "";
        Component[] components = autoriLibroPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JTextField){

                        JTextField textField = (JTextField) component2;

                        switch (fieldCount){
                            case 0:
                                nome = textField.getText();
                                fieldCount++;
                                break;
                            case 1:
                                cognome = textField.getText();
                                fieldCount++;
                                break;
                            case 2:
                                nazionalità = textField.getText();
                                fieldCount++;
                                break;
                            case 3:
                                dn = textField.getText();
                                fieldCount = 0;
                                n_autori++;

                                if (nome.isBlank() || cognome.isBlank()){
                                    return false;
                                } else{
                                    controller.aggiungiAutoreLibro(nome.replace("'", "’"), cognome.replace("'", "’"), nazionalità, dn);
                                }

                                break;
                            default:
                                fieldCount = 0;
                                break;
                        }
                    }
                }
            }
        }

        if (n_autori >= 1){
            return true;
        }

        return false;
    }

    private boolean takeArticoli(Controller controller, boolean nuova_rivista) {

        Component[] components = articoliPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JComboBox){
                        JComboBox comboBox = (JComboBox) component2;

                        for (Component component3: components2){
                            if(component3 instanceof JSpinner){
                                JSpinner spinner = (JSpinner) component3;

                                for (Component component4: components2){
                                    if(component4 instanceof JPanel){

                                        JPanel jpanel2 = (JPanel) component4;

                                        if(comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("")){
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Devi selezionare o creare un articolo!");

                                            controller.eliminaFascicolo();

                                            if(nuova_rivista == true) {
                                                controller.eliminaRivista();
                                            }

                                            return false;
                                        } else {
                                            if (Date.valueOf(dpFascicoloField.getText()).getYear()+1900 >= (int) spinner.getValue()) {
                                                if (controller.creaArticolo(comboBox.getSelectedItem().toString().replace("'", "’"), (int) spinner.getValue())) {
                                                    if (takeAutoriArticolo(controller, jpanel2) == false) {
                                                        controller.eliminaArticolo();
                                                        controller.eliminaFascicolo();

                                                        if (nuova_rivista == true){
                                                            controller.eliminaRivista();
                                                        }

                                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo articolo non ha autori!");
                                                        return false;
                                                    } else {
                                                        controller.listaArticoli.add(controller.nuovoArticoloScientifico);
                                                        controller.nuovoFascicolo.articoli.add(controller.nuovoArticoloScientifico);
                                                    }
                                                } else {
                                                    controller.nuovoArticoloScientifico = controller.listaArticoli.get(comboBox.getSelectedIndex());
                                                    controller.nuovoFascicolo.articoli.add(controller.listaArticoli.get(comboBox.getSelectedIndex()));
                                                }
                                            } else {
                                                controller.eliminaFascicolo();

                                                if (nuova_rivista == true) {
                                                    controller.eliminaRivista();
                                                }

                                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo articolo è scritto dopo la pubblicazione del fascicolo!");
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean takeAutoriArticolo(Controller controller, JPanel panelArticolo) {
        int fieldCount = 0;
        int n_autori = 0;
        String nome = "";
        String cognome = "";
        String nazionalità = "";
        String dn = "";
        Component[] components = panelArticolo.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JTextField){
                        JTextField textField = (JTextField) component2;

                        switch (fieldCount){
                            case 0:
                                nome = textField.getText();
                                fieldCount++;
                                break;
                            case 1:
                                cognome = textField.getText();
                                fieldCount++;
                                break;
                            case 2:
                                nazionalità = textField.getText();
                                fieldCount++;
                                break;
                            case 3:
                                dn = textField.getText();
                                fieldCount = 0;
                                n_autori++;

                                if (nome.isBlank() || cognome.isBlank()){
                                    return false;
                                } else{
                                    controller.aggiungiAutoreArticolo(nome.replace("'", "’"), cognome.replace("'", "’"), nazionalità, dn);
                                }

                                break;
                            default:
                                fieldCount = 0;
                                break;
                        }
                    }
                }
            }
        }

        if (n_autori >= 1){
            return true;
        }

        return false;
    }

    private ArrayList<String> takeISBN() {
        ArrayList<String> isbn = new ArrayList<>();
        int fieldCount = 0;
        String nome = "";
        String cognome = "";
        String nazionalità = "";
        String dn = "";
        Component[] components = isbnSeriePanel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JComboBox){
                        JComboBox comboBox = (JComboBox) component2;

                        if(comboBox.getSelectedItem() == null) {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Aggiungere tutti gli isbn");
                            return null;
                        } else {
                            isbn.add(comboBox.getSelectedItem().toString());
                        }
                    }
                }
            }
        }
        return isbn;
    }

    private void initComponentsISBNSerie(ArrayList<String> isbn, int ricorsive, Font fontbase, Font fontfield) {
        if(ricorsive == 0) {
            return;
        }

        isbnLibroCount++;

        DefaultComboBoxModel model;
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JComboBox comboBox1 = new JComboBox();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x222831));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

            //---- label2 ----
            label2.setText("ISBN Libro n°" + isbnLibroCount + ":");
            label2.setFont(fontbase);
            label2.setForeground(new Color(0xeeeeee));
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- comboBox1 ----
            model = new DefaultComboBoxModel(isbn.toArray(new String[isbn.size()]));
            comboBox1.setModel(model);
            comboBox1.setFont(fontfield);
            comboBox1.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
            comboBox1.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
            comboBox1.setBackground(Color.decode("#FFD369"));
            comboBox1.setForeground(Color.decode("#222831"));

            Object comp = comboBox1.getUI().getAccessibleChild(comboBox1, 0);

            if(comp instanceof JPopupMenu){
                JPopupMenu popup = (JPopupMenu) comp;
                JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
                scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());
                scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49));
                scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));
            }

            Component editorComp = comboBox1.getEditor().getEditorComponent();

            if (editorComp instanceof JTextField) {
                JTextField textField = (JTextField) editorComp;
                textField.setBackground(new Color(0xFFD369));
                textField.setForeground(new Color(0x222831));
            }

            comboBox1.setBorder(new LineBorder(Color.decode("#222831")));
            comboBox1.setSelectedIndex(-1);

            comboBox1.setName(String.valueOf(isbnLibroCount));
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkDuplicateSelection(comboBox1.getSelectedIndex(), comboBox1);
                }
            });

            panel1.add(comboBox1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
        }

        isbnSeriePanel.add(panel1);
        initComponentsISBNSerie(isbn, ricorsive-1, fontbase, fontfield);
    }

    private void checkDuplicateSelection(int index, JComboBox comboBoxPanel) {
        int countCombobox = 1;

        Component[] components = isbnSeriePanel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JComboBox){
                        JComboBox comboBox1 = (JComboBox) component2;

                        if(!comboBox1.getName().equals(comboBoxPanel.getName())){
                            if(index == comboBox1.getSelectedIndex()){
                                comboBoxPanel.setSelectedIndex(-1);
                            }
                        }
                    }

                    countCombobox++;
                }
            }
        }
    }

    private void checkDuplicateSelectionFascicoli(int index, JComboBox comboBoxPanel) {
        int countCombobox = 1;

        Component[] components = articoliPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JComboBox){
                        JComboBox comboBox1 = (JComboBox) component2;

                        if(!comboBox1.getName().equals(comboBoxPanel.getName())){
                            if(index == comboBox1.getSelectedIndex()){
                                comboBoxPanel.setSelectedIndex(-1);
                            }
                        }
                    }

                    countCombobox++;
                }
            }
        }
    }

        private void initComponentsAutoreLibro(Font fontbase, Font fontfield) {
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JTextField textField3 = new JTextField();
        JLabel label4 = new JLabel();
        JTextField textField4 = new JTextField();
        JLabel label5 = new JLabel();
        JTextField textField5 = new JTextField();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JTextField textField6 = new JTextField();

        //======== panel1 ========
        {
        panel1.setBackground(new Color(0x222831));
        panel1.setMinimumSize(new Dimension(-1, -1));
        panel1.setPreferredSize(null);
        panel1.setLayout(new GridBagLayout());
        ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- label2 ----
        label2.setText("Autore " + autoreLibroCount +":");
        label2.setFont(fontbase);
        label2.setForeground(new Color(0xeeeeee));
        panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("Nome:");
        label3.setFont(fontbase);
        label3.setForeground(new Color(0xeeeeee));
        panel1.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField3 ----
        textField3.setBackground(new Color(0xffd369));
        textField3.setBorder(new LineBorder(new Color(0xffd369)));
        textField3.setCaretColor(new Color(0x222831));
        textField3.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
        textField3.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
        panel1.add(textField3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label4 ----
        label4.setText("Cognome:");
        label4.setFont(fontbase);
        label4.setForeground(new Color(0xeeeeee));
        panel1.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField4 ----
        textField4.setBackground(new Color(0xffd369));
        textField4.setCaretColor(new Color(0x222831));
        textField4.setBorder(new LineBorder(new Color(0xffd369)));
        textField4.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
        textField4.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
        textField4.setFont(fontfield);
        panel1.add(textField4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label5 ----
        label5.setText("Nazionalità:");
        label5.setFont(fontbase);
        label5.setForeground(new Color(0xeeeeee));
        panel1.add(label5, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField5 ----
        textField5.setBackground(new Color(0xffd369));
        textField5.setBorder(new LineBorder(new Color(0xffd369)));
        textField5.setCaretColor(new Color(0x222831));
        textField5.setFont(fontbase);
        textField5.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
        textField5.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
        panel1.add(textField5, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label6 ----
        label6.setText("Data di Nascita:");
        label6.setFont(fontbase);
        label6.setForeground(new Color(0xeeeeee));
        panel1.add(label6, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setIcon(new ImageIcon(calendarImg));

        datePickerLibro= new DatePicker(label7);
        label7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    datePickerLibro.d.setVisible(true);
                    textField6.setText(datePickerLibro.setPickedDate());
                }
            });

        panel1.add(label7, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- textField6 ----
        textField6.setBackground(new Color(0xffd369));
        textField6.setCaretColor(new Color(0x222831));
        textField6.setBorder(new LineBorder(new Color(0xffd369)));
        textField6.setFont(fontfield);
        textField6.setMinimumSize(new Dimension((int)(screenWidth/8.5), calcolaAltezzaFont(label6.getFont())));
        textField6.setPreferredSize(new Dimension((int)(screenWidth/8.5),calcolaAltezzaFont(label6.getFont())));
        panel1.add(textField6, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }
        autoriLibroPanel.add(panel1);
    }

    public int calcolaAltezzaFont(Font font){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int fontHeight = fm.getHeight();
        g2d.dispose();
        return fontHeight;
    }

    private void initComponentsArticoli(Controller controller) {
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JComboBox comboBox1 = new JComboBox<>();
        JLabel label6 = new JLabel();
        final JSpinner spinner1 = new JSpinner();
        JLabel label4 = new JLabel();
        JButton button1 = new JButton();
        JPanel panel2 = new JPanel();
        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x222831));
            panel1.setSize((int)(controller.screenWidth/2.1), -1);
            panel1.setMinimumSize(new Dimension(-1, -1));
            panel1.setPreferredSize(null);
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label2 ----
            label2.setText("Articolo " + articoliFascicoliCount + ":");
            label2.setForeground(new Color(0xeeeeee));
            label2.setFont(controller.baseFontSize);
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("Titolo:");
            label3.setFont(controller.baseFontSize);
            label3.setForeground(new Color(0xeeeeee));
            panel1.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- comboBox1 ----
            comboBox1.setFont(controller.textFieldFont);
            comboBox1.setBackground(new Color(0xffd369));
            Object comp = comboBox1.getUI().getAccessibleChild(comboBox1, 0);
            if(comp instanceof JPopupMenu){
                JPopupMenu popup = (JPopupMenu) comp;
                JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
                scrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());
                scrollPane.getVerticalScrollBar().setForeground(new Color(34, 40, 49));
                scrollPane.getVerticalScrollBar().setBackground(new Color(0xCF9E29));
            }

            Component editorComp = comboBox1.getEditor().getEditorComponent();
            if (editorComp instanceof JTextField) {
                JTextField textField = (JTextField) editorComp;
                textField.setBackground(new Color(0xFFD369));
                textField.setForeground(new Color(0x222831));
            }

            controller.listaFascicoli = controller.getFascicoli();
            controller.getArticoliScientifici();

            for (int i = 0; i < controller.listaArticoli.size(); i++){
                    comboBox1.addItem(controller.listaArticoli.get(i).titolo);
            }

            comboBox1.setSelectedIndex(-1);
            comboBox1.setEditable(true);
            comboBox1.setName(String.valueOf(articoliFascicoliCount));
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkDuplicateSelectionFascicoli(comboBox1.getSelectedIndex(), comboBox1);

                    if(comboBox1.getSelectedIndex() >= 0 && comboBox1.getSelectedIndex() <= controller.listaArticoli.size()){
                        spinner1.setEnabled(false);
                        spinner1.setValue(controller.listaArticoli.get(comboBox1.getSelectedIndex()).annoPubblicazione);
                        label4.setVisible(false);
                        button1.setVisible(false);
                        panel2.setVisible(false);
                        panel2.removeAll();
                    } else {
                        spinner1.setEnabled(true);
                        spinner1.setValue(Year.now().getValue());
                        label4.setVisible(true);
                        button1.setVisible(true);
                        panel2.setVisible(true);
                    }
                }
            });

            panel1.add(comboBox1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- label6 ----
            label6.setText("Anno di pubblicazione:");
            label6.setFont(controller.baseFontSize);
            label6.setForeground(new Color(0xeeeeee));
            panel1.add(label6, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- spinner1 ----
            spinner1.setBackground(new Color(0xffd369));
            spinner1.setFont(controller.textFieldFont);
            SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 9999, 1);
            spinner1.setModel(snm);

            spinner1.setBorder(new LineBorder(new Color(0xFFD369)));

            JComponent editor = spinner1.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
                textField.setForeground(new Color(0x222831));
                textField.setBackground(new Color(0xFFD369));
                textField.setBorder(new LineBorder(new Color(0x222831)));
            }
            spinner1.setUI(new BasicSpinnerUI(){
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

                @Override
                protected Component createPreviousButton() {
                    JButton button = new JButton(new ImageIcon(dA)){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                        }
                    };
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
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
                            return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                        }
                    };
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            spinner.setValue(spinner.getNextValue()); // Azione per incrementare il valore
                        }
                    });
                    button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                    button.setBorder(new LineBorder(new Color(0xFFD369)));
                    return button;
                }
            });

            spinner1.setValue(Year.now().getValue());

            panel1.add(spinner1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- label4 ----
            label4.setText("Autori:");
            label4.setFont(controller.baseFontSize);
            label4.setForeground(new Color(0xeeeeee));
            panel1.add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- button1 ----
            button1.setText("Aggiungi");
            button1.setFont(controller.baseFontSize);
            button1.setBackground(new Color(0xffd369));
            button1.setBorderPainted(false);
            panel1.add(button1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initComponentsAutoriArticoli(panel2, controller.baseFontSize, controller.textFieldFont);
                    panel2.revalidate();
                    panel2.repaint();
                }
            });

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x222831));
                panel2.setLayout(new GridLayout(1, 2, 15, 15));
                panel2.setName("pannello " + articoliFascicoliCount);

            }
            panel1.add(panel2, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Colonna 1
        constraints.gridy = (articoliFascicoliCount-1); // Riga 0
        articoliPanel.add(panel1, constraints);
    }

    private void initComponentsAutoriArticoli(JPanel panelC, Font fontbase, Font fontfield) {
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JTextField textField3 = new JTextField();
        JLabel label4 = new JLabel();
        JTextField textField4 = new JTextField();
        JLabel label5 = new JLabel();
        JTextField textField5 = new JTextField();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JTextField textField6 = new JTextField();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x222831));
            panel1.setMinimumSize(new Dimension(-1, -1));
            panel1.setPreferredSize(null);
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //---- label2 ----
            label2.setText("Autore:");
            label2.setFont(fontbase);
            label2.setForeground(new Color(0xeeeeee));
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- label3 ----
            label3.setText("Nome:");
            label3.setFont(fontbase);
            label3.setForeground(new Color(0xeeeeee));
            panel1.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- textField3 ----
            textField3.setFont(fontfield);
            textField3.setBackground(new Color(0xffd369));
            textField3.setBorder(new LineBorder(new Color(0xffd369)));
            textField3.setCaretColor(new Color(0x222831));
            textField3.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
            textField3.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
            panel1.add(textField3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- label4 ----
            label4.setText("Cognome:");
            label4.setFont(fontbase);
            label4.setForeground(new Color(0xeeeeee));
            panel1.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- textField4 ----
            textField4.setBackground(new Color(0xffd369));
            textField4.setFont(fontfield);
            textField4.setCaretColor(new Color(0x222831));
            textField4.setBorder(new LineBorder(new Color(0xffd369)));
            textField4.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
            textField4.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
            panel1.add(textField4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- label5 ----
            label5.setText("Nazionalit\u00e0:");
            label5.setFont(fontbase);
            label5.setForeground(new Color(0xeeeeee));
            panel1.add(label5, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- textField5 ----
            textField5.setBackground(new Color(0xffd369));
            textField5.setFont(fontfield);
            textField5.setBorder(new LineBorder(new Color(0xffd369)));
            textField5.setCaretColor(new Color(0x222831));
            textField5.setMinimumSize(new Dimension((int)(screenWidth/8.5),-1));
            textField5.setPreferredSize(new Dimension((int)(screenWidth/8.5),-1));
            panel1.add(textField5, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- label6 ----
            label6.setText("Data di Nascita:");
            label6.setFont(fontbase);
            label6.setForeground(new Color(0xeeeeee));
            panel1.add(label6, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- label7 ----
            label7.setIcon(new ImageIcon(calendarImg));

            DatePicker datePicker = new DatePicker(label7);

            label7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        datePicker.d.setVisible(true);
                        textField6.setText(datePicker.setPickedDate());
                }
            });

            panel1.add(label7, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

            //---- textField6 ----
            textField6.setBackground(new Color(0xffd369));
            textField6.setCaretColor(new Color(0x222831));
            textField6.setBorder(new LineBorder(new Color(0xffd369)));
            textField6.setFont(fontfield);
            textField6.setMinimumSize(new Dimension((int)(screenWidth/8.5), calcolaAltezzaFont(label6.getFont())));
            textField6.setPreferredSize(new Dimension((int)(screenWidth/8.5),calcolaAltezzaFont(label6.getFont())));
            panel1.add(textField6, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
        }
            panelC.setLayout(new GridLayout(-1, 2, 15, 15));
            panelC.add(panel1);
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


        // LIBRO //
        linguaLibroCB = new JComboBox<>();
        linguaLibroCB.setBackground(Color.decode("#FFD369"));
        linguaLibroCB.setForeground(Color.decode("#222831"));
        linguaLibroCB.setBorder(new LineBorder(Color.decode("#222831")));


        isbnLibroCB = new JComboBox<>();
        isbnLibroCB.setBackground(Color.decode("#FFD369"));
        isbnLibroCB.setForeground(Color.decode("#222831"));
        isbnLibroCB.setBorder(new LineBorder(Color.decode("#222831")));

        fruizioneLibroCB = new JComboBox<>();
        fruizioneLibroCB.setBackground(Color.decode("#FFD369"));
        fruizioneLibroCB.setForeground(Color.decode("#222831"));
        fruizioneLibroCB.setBorder(new LineBorder(Color.decode("#222831")));

        titoloLibroField = new JTextField();
        titoloLibroField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        editoreLibroField = new JTextField();
        editoreLibroField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        genereLibroField = new JTextField();
        genereLibroField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        dataLibroField = new JTextField();
        dataLibroField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        calendarLibroIMG = new JLabel(calendarIco);

        autoriLibroPanel = new JPanel();
        autoriLibroPanel.setLayout(new GridLayout(-1, 2, (int) (screenWidth/85.33), screenHeight/48));
        //autoriLibroPanel.setLayout(new BoxLayout(autoriLibroPanel, BoxLayout.PAGE_AXIS));

        SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 9999, 1);
        quantitaLibroSpinner = new JSpinner(snm);

        quantitaLibroSpinner.setBorder(new LineBorder(new Color(0xFFD369)));

        JComponent editor = quantitaLibroSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setForeground(new Color(0x222831));
            textField.setBackground(new Color(0xFFD369));
            textField.setBorder(new LineBorder(new Color(0x222831)));
        }

        quantitaLibroSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
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
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getNextValue()); // Azione per incrementare il valore
                    }
                });
                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }
        });

        // SERIE //
        isbnSerieField = new JTextField();
        isbnSerieField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        titoloSerieField = new JTextField();
        titoloSerieField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        editoreLibroField = new JTextField();
        editoreLibroField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        dataSerieField = new JTextField();
        dataSerieField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        calendarSerieIMG = new JLabel(calendarIco);

        isbnSeriePanel = new JPanel();
        isbnSeriePanel.setLayout(new GridLayout(-1, 2));

        SpinnerNumberModel snm2 = new SpinnerNumberModel(2, 2, 999, 1);
        spinnerLibriSerie = new JSpinner(snm2);

        spinnerLibriSerie.setBorder(new LineBorder(new Color(0xFFD369)));

        JComponent editor2 = spinnerLibriSerie.getEditor();
        if (editor2 instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor2).getTextField();
            textField.setForeground(new Color(0x222831));
            textField.setBackground(new Color(0xFFD369));
            textField.setBorder(new LineBorder(new Color(0x222831)));
        }

        spinnerLibriSerie.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
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
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getNextValue()); // Azione per incrementare il valore
                    }
                });
                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }
        });

        // RIVISTA //

        titoloRivistaCB = new JComboBox<>();
        titoloRivistaCB.setBackground(Color.decode("#FFD369"));
        titoloRivistaCB.setForeground(Color.decode("#222831"));
        titoloRivistaCB.setBorder(new LineBorder(Color.decode("#222831")));

        issnRivistaField = new JTextField();
        issnRivistaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        argomentoRivistaField = new JTextField();
        argomentoRivistaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        nomeRField = new JTextField();
        nomeRField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        cognomeRField = new JTextField();
        cognomeRField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        editoreRivistaField = new JTextField();
        editoreRivistaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        dpFascicoloField = new JTextField();
        dpFascicoloField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        DecimalFormat decimalFormat = new DecimalFormat("0");
        decimalFormat.setGroupingUsed(false); // Disabilita il separatore delle migliaia

        // Crea un NumberFormatter con il NumberFormat personalizzato
        NumberFormatter numberFormatter = new NumberFormatter(decimalFormat);

        // Crea un JFormattedTextField con il NumberFormatter
        JFormattedTextField formattedTextField = new JFormattedTextField(numberFormatter);

        // Crea un SpinnerNumberModel con il valore iniziale, il valore minimo, il valore massimo e l'incremento
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(Year.now().getValue(), 1900, Year.now().getValue(), 1);


        annoPrivistaSpinner = new JSpinner(spinnerModel);
        annoPrivistaSpinner.setEditor(new JSpinner.DefaultEditor(annoPrivistaSpinner));
        JSpinner.DefaultEditor editors = (JSpinner.DefaultEditor) annoPrivistaSpinner.getEditor();
        editors.getTextField().setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
                return numberFormatter;
            }
        });

        annoPrivistaSpinner.setBorder(new LineBorder(new Color(0xFFD369)));

        JComponent editor3 = annoPrivistaSpinner.getEditor();
        if (editor3 instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor3).getTextField();
            textField.setForeground(new Color(0x222831));
            textField.setBackground(new Color(0xFFD369));
            textField.setBorder(new LineBorder(new Color(0x222831)));
        }

        annoPrivistaSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
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
                        return new Dimension(15, 15);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (spinner.getNextValue() != null) {
                            spinner.setValue(spinner.getNextValue());
                        }
                    }
                });
                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }
        });

        numeroFascicoloCB = new JComboBox<>();
        numeroFascicoloCB.setBackground(Color.decode("#FFD369"));
        numeroFascicoloCB.setForeground(Color.decode("#222831"));
        numeroFascicoloCB.setBorder(new LineBorder(Color.decode("#222831")));

        fruizoneFascicoloCB = new JComboBox<>();
        fruizoneFascicoloCB.setBackground(Color.decode("#FFD369"));
        fruizoneFascicoloCB.setForeground(Color.decode("#222831"));
        fruizoneFascicoloCB.setBorder(new LineBorder(Color.decode("#222831")));

        SpinnerNumberModel snm4 = new SpinnerNumberModel(0, 0, 9999, 1);
        quantitaFascicoloSpinner = new JSpinner(snm4);

        quantitaFascicoloSpinner.setBorder(new LineBorder(new Color(0xFFD369)));

        JComponent editor4 = quantitaFascicoloSpinner.getEditor();
        if (editor4 instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor4).getTextField();
            textField.setForeground(new Color(0x222831));
            textField.setBackground(new Color(0xFFD369));
            textField.setBorder(new LineBorder(new Color(0x222831)));
        }

        quantitaFascicoloSpinner.setUI(new BasicSpinnerUI(){
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(screenWidth/256, screenHeight/144, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getPreviousValue()); // Azione per decrementare il valore
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
                        return new Dimension((int) (screenWidth/85.33), screenHeight/48);
                    }
                };
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        spinner.setValue(spinner.getNextValue()); // Azione per incrementare il valore
                    }
                });
                button.setBackground(new Color(0x222831)); // Imposta il colore di sfondo del bottone
                button.setBorder(new LineBorder(new Color(0xFFD369)));
                return button;
            }
        });

        calendarIssueIMG = new JLabel(calendarIco);

        articoliPanel = new JPanel();
        articoliPanel.setLayout(new GridBagLayout());
        ((GridBagLayout)articoliPanel.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)articoliPanel.getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)articoliPanel.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)articoliPanel.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
    }
}
