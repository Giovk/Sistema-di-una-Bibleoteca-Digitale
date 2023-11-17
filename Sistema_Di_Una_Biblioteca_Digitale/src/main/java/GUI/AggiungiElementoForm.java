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
    private JTextField isbnLibroField;
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
    private JPanel vSpacerForm;
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
        screenHeight = controller.screenHeight;
        screenWidth = controller.screenWidth;

        // Inizio //
        libroBT.setFont(controller.baseFontSize);
        libroBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));
        serieBT.setFont(controller.baseFontSize);
        serieBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));
        fascicoloBT.setFont(controller.baseFontSize);
        fascicoloBT.setMinimumSize(new Dimension((int)(controller.screenWidth/6.4),-1));

        scegliElementoLabel.setFont(controller.impactFontSize);

        // Libro //
        libroLabel.setFont(controller.impactFontSize);

        isbnLibroLabel.setFont(controller.baseFontSize);
        isbnLibroCB.setFont(controller.textFieldFont);
        isbnLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        titoloLibroLabel.setFont(controller.baseFontSize);
        titoloLibroField.setFont(controller.textFieldFont);
        titoloLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        genereLibroLabel.setFont(controller.baseFontSize);
        genereLibroField.setFont(controller.textFieldFont);
        genereLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        linguaLibroLabel.setFont(controller.baseFontSize);
        linguaLibroCB.setFont(controller.textFieldFont);
        linguaLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        editoreLabel.setFont(controller.baseFontSize);
        editoreLibroField.setFont(controller.textFieldFont);
        editoreLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        dataPubblicazioneLabel.setFont(controller.baseFontSize);
        dataLibroField.setFont(controller.textFieldFont);
        dataLibroField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        calendarLibroIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));
        calendarLibroIMG.setIcon(new ImageIcon(calendarImg));
        quantitaLabel.setFont(controller.baseFontSize);
        quantitaLibroSpinner.setFont(controller.textFieldFont);
        quantitaLibroSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        fruizioneLabel.setFont(controller.baseFontSize);
        fruizioneLibroCB.setFont(controller.textFieldFont);
        fruizioneLibroCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));


        autoriLibroLabel.setFont(controller.baseFontSize);
        autoreLibroButton.setFont(controller.baseFontSize);



        inviaLibroButton.setFont(controller.baseFontSize);

        // Fascicolo //
        fascicoloLabel.setFont(controller.impactFontSize);

        titoloLabel1.setFont(controller.baseFontSize);
        titoloRivistaCB.setFont(controller.textFieldFont);
        titoloRivistaCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        issnLabel.setFont(controller.baseFontSize);
        issnRivistaField.setFont(controller.textFieldFont);
        issnRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        argomentoLabel.setFont(controller.baseFontSize);
        argomentoRivistaField.setFont(controller.textFieldFont);
        argomentoRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        nomeResponsabileLabel.setFont(controller.baseFontSize);
        nomeRField.setFont(controller.textFieldFont);
        nomeRField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        cognomeResponsabileLabel.setFont(controller.baseFontSize);
        cognomeRField.setFont(controller.textFieldFont);
        cognomeRField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        editoreLabel1.setFont(controller.baseFontSize);
        editoreRivistaField.setFont(controller.textFieldFont);
        editoreRivistaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        annoPubblicazioneLabel.setFont(controller.baseFontSize);
        annoPrivistaSpinner.setFont(controller.textFieldFont);
        annoPrivistaSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        numeroLabel.setFont(controller.baseFontSize);
        numeroFascicoloCB.setFont(controller.textFieldFont);
        numeroFascicoloCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        dataLabel.setFont(controller.baseFontSize);
        dpFascicoloField.setFont(controller.textFieldFont);
        dpFascicoloField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        calendarIssueIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));
        calendarIssueIMG.setIcon(new ImageIcon(calendarImg));
        fruizioneLabel1.setFont(controller.baseFontSize);
        fruizoneFascicoloCB.setFont(controller.textFieldFont);
        fruizoneFascicoloCB.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        quantitaLabel1.setFont(controller.baseFontSize);
        quantitaFascicoloSpinner.setFont(controller.textFieldFont);
        quantitaFascicoloSpinner.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));

        articoliFascicoloLabel.setFont(controller.baseFontSize);
        aggiungiArticoloBT.setFont(controller.baseFontSize);

        inviaFascicoloBT.setFont(controller.baseFontSize);

        // Serie//
        serieLabel.setFont(controller.impactFontSize);

        isbnSerieLabel.setFont(controller.baseFontSize);
        isbnSerieField.setFont(controller.textFieldFont);
        isbnSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        titoloLabel.setFont(controller.baseFontSize);
        titoloSerieField.setFont(controller.textFieldFont);
        titoloSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        dataPubblicazioneLabel1.setFont(controller.baseFontSize);
        dataSerieField.setFont(controller.textFieldFont);
        dataSerieField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));
        calendarSerieIMG.setMinimumSize(new Dimension((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8)));
        calendarSerieIMG.setIcon(new ImageIcon(calendarImg));
        libriLabel.setFont(controller.baseFontSize);
        spinnerLibriSerie.setFont(controller.textFieldFont);
        spinnerLibriSerie.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));

        okSerieBT.setFont(controller.baseFontSize);

        inviaSerieButton.setFont(controller.baseFontSize);
        // FINE //


        frame = new JFrame("Valutazione");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int)(controller.screenWidth/1.7777), (int) (controller.screenHeight/1.1612));
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

        bookPanel.setVisible(false);
        seriePanel.setVisible(false);
        issuePanel.setVisible(false);

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

        libroBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                libroBT.setBackground(Color.decode("#cf9e29"));
            }
        });
        libroBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                libroBT.setBackground(Color.decode("#FFD369"));
            }
        });
        libroBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookPanel.setVisible(true);
                seriePanel.setVisible(false);
                issuePanel.setVisible(false);
                questionPanel.setVisible(false);

                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        serieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieBT.setBackground(Color.decode("#cf9e29"));
            }
        });
        serieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieBT.setBackground(Color.decode("#FFD369"));
            }
        });
        serieBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isbnSeriePanel.removeAll();
                isbnLibroCount = 0;
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue(), controller.baseFontSize, controller.textFieldFont);
                seriePanel.setVisible(true);
                bookPanel.setVisible(false);
                issuePanel.setVisible(false);
                questionPanel.setVisible(false);
            }
        });

        fascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                fascicoloBT.setBackground(Color.decode("#cf9e29"));
            }
        });
        fascicoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                fascicoloBT.setBackground(Color.decode("#FFD369"));
            }
        });
        fascicoloBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                articoliFascicoliCount = 0;
                issuePanel.setVisible(true);
                seriePanel.setVisible(false);
                bookPanel.setVisible(false);
                questionPanel.setVisible(false);
            }
        });

        // LIBRO //

        for (int i = 0; i < controller.listaLibri.size(); i++){
            isbnLibroCB.addItem(controller.listaLibri.get(i).isbn);
        }

        isbnLibroCB.setSelectedIndex(-1);

        bookScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine.
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
                this.scrollBarWidth = (int)(controller.screenWidth/75);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                    }
                };

                decreaseButton.setBackground(new Color(0x222831));
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

                increaseButton.setBackground(new Color(0x222831));
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;
                    case SwingConstants.NORTH: return uA;
                    case SwingConstants.EAST: return rA;
                    default: return lA;
                }
            }
        });

        Object comp1 = linguaLibroCB.getUI().getAccessibleChild(linguaLibroCB, 0);
        if(comp1 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp1;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0x222831);
                    this.trackColor= new Color(0xFFD369);
                    this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                    this.thumbLightShadowColor = new Color(0x323A48);
                    this.thumbHighlightColor = new Color(0x323A48);
                    this.trackHighlightColor = new Color(0xCF9E29);
                    this.scrollBarWidth = (int)(controller.screenWidth/75);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                        }
                    };

                    decreaseButton.setBackground(new Color(0x222831));
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

                    increaseButton.setBackground(new Color(0x222831));
                    return increaseButton;
                }

                private Image getAppropriateIcon(int orientation){
                    switch(orientation){
                        case SwingConstants.SOUTH: return dA;
                        case SwingConstants.NORTH: return uA;
                        case SwingConstants.EAST: return rA;
                        default: return lA;
                    }
                }
            });
        }

        Object comp2 = isbnLibroCB.getUI().getAccessibleChild(isbnLibroCB, 0);
        if(comp2 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp2;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0x222831);
                    this.trackColor= new Color(0xFFD369);
                    this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                    this.thumbLightShadowColor = new Color(0x323A48);
                    this.thumbHighlightColor = new Color(0x323A48);
                    this.trackHighlightColor = new Color(0xCF9E29);
                    this.scrollBarWidth = (int)(controller.screenWidth/75);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                        }
                    };

                    decreaseButton.setBackground(new Color(0x222831));
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

                    increaseButton.setBackground(new Color(0x222831));
                    return increaseButton;
                }

                private Image getAppropriateIcon(int orientation){
                    switch(orientation){
                        case SwingConstants.SOUTH: return dA;
                        case SwingConstants.NORTH: return uA;
                        case SwingConstants.EAST: return rA;
                        default: return lA;
                    }
                }
            });
        }

        Component editorComp = isbnLibroCB.getEditor().getEditorComponent();
        if (editorComp instanceof JTextField) {
            JTextField textField = (JTextField) editorComp;
            textField.setBackground(new Color(0xFFD369));
            textField.setForeground(new Color(0x222831));
        }

        isbnLibroCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isbnLibroCB.getSelectedIndex();
                if(isbnLibroCB.getSelectedIndex() >= 0 && isbnLibroCB.getSelectedIndex() <= (controller.listaLibri.size()-1)){
                    titoloLibroField.setEnabled(false);
                    genereLibroField.setEnabled(false);
                    linguaLibroCB.setEnabled(false);
                    editoreLibroField.setEnabled(false);
                    dataLibroField.setEnabled(false);
                    calendarLibroIMG.setEnabled(false);
                    autoriLibroLabel.setVisible(false);
                    autoreLibroButton.setVisible(false);
                    autoriLibroPanel.removeAll();
                    autoreLibroCount = 0;

                    titoloLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).titolo);
                    genereLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).genere);
                    linguaLibroCB.setSelectedItem((controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).lingua));
                    editoreLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).editore);
                    dataLibroField.setText(controller.listaLibri.get(isbnLibroCB.getSelectedIndex()).dataPubblicazione.toString());
                } else{
                    titoloLibroField.setEnabled(true);
                    genereLibroField.setEnabled(true);
                    linguaLibroCB.setEnabled(true);
                    editoreLibroField.setEnabled(true);
                    dataLibroField.setEnabled(true);
                    calendarLibroIMG.setEnabled(true);
                    autoriLibroLabel.setVisible(true);
                    autoreLibroButton.setVisible(true);

                    titoloLibroField.setText("");
                    genereLibroField.setText("");
                    linguaLibroCB.setSelectedItem(1);
                    editoreLibroField.setText("");
                    dataLibroField.setText("");
                }
            }
        });


        bookScrollPanel.setBackground(new Color(0x222831));
        bookScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        bookScrollPanel.getViewport().setBackground(new Color(0x222831));


        datePickerLibro = new DatePicker(calendarLibroIMG);

        calendarLibroIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarLibroIMG.isEnabled() == true) {
                    datePickerLibro.d.setVisible(true);
                    dataLibroField.setText(datePickerLibro.setPickedDate());
                }
            }
        });

        quantitaLibroSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaLibroSpinner.getValue() > 9999) quantitaLibroSpinner.setValue(9999);
                if((int)quantitaLibroSpinner.getValue() < 0) quantitaLibroSpinner.setValue(0);
            }
        });

        autoreLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                autoreLibroButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        autoreLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                autoreLibroButton.setBackground(Color.decode("#FFD369"));
            }
        });

        autoreLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoreLibroCount++;
                initComponentsAutoreLibro(controller.baseFontSize, controller.textFieldFont);
                autoriLibroPanel.revalidate();
                autoriLibroPanel.repaint();
            }
        });

        inviaLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaLibroButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        inviaLibroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaLibroButton.setBackground(Color.decode("#FFD369"));
            }
        });
        inviaLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isbnLibroCB.getSelectedItem() == null || isbnLibroCB.getSelectedItem().equals("")){
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Seleziona o crea un nuovo libro!");
                } else {
                    if (titoloLibroField.getText().isBlank() || linguaLibroCB.getSelectedItem() == null || linguaLibroCB.getSelectedItem().equals("") || editoreLibroField.getText().isBlank() || genereLibroField.getText().isBlank() || dataLibroField.getText().isBlank()){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi!");
                    } else {
                        if (titoloLibroField.isEnabled() == true) {
                            if (controller.creaLibro(isbnLibroCB.getSelectedItem().toString(), titoloLibroField.getText().replace("'", "’"), genereLibroField.getText().replace("'", "’"), linguaLibroCB.getSelectedItem().toString(), editoreLibroField.getText().replace("'", "’"), dataLibroField.getText()) == false) {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro già esiste");
                            } else {
                                if (takeAutoriLibro(controller) == false) {
                                    controller.eliminaLibro();
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro non ha autori");
                                } else {
                                    controller.listaLibri.add(controller.nuovoLibro);

                                    if(controller.insertPossessoL((int) quantitaLibroSpinner.getValue(), fruizioneLibroCB.getSelectedItem().toString()) == false){
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro è già presente in Libreria");
                                    } else {
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
                                }
                            }
                        } else {
                            controller.nuovoLibro = controller.listaLibri.get(isbnLibroCB.getSelectedIndex());

                            if(controller.insertPossessoL((int) quantitaLibroSpinner.getValue(), fruizioneLibroCB.getSelectedItem().toString()) == false){
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa libro è già presente in Libreria");
                            } else {
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
                        }
                    }
                }
            }
        });

        // SERIE //
        isbnLibri = new ArrayList<>();

        for (String isbnLibro: controller.titoloLibriLibreria){
            if(!isbnLibri.contains(isbnLibro.substring(0, 17))) isbnLibri.add(isbnLibro.substring(0, 17));
        }
        serieScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagineALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
                this.scrollBarWidth = (int)(controller.screenWidth/75);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                    }
                };

                decreaseButton.setBackground(new Color(0x222831));
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

                increaseButton.setBackground(new Color(0x222831));
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;
                    case SwingConstants.NORTH: return uA;
                    case SwingConstants.EAST: return rA;
                    default: return lA;
                }
            }
        });

        serieScrollPanel.setBackground(new Color(0x222831));
        serieScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        serieScrollPanel.getViewport().setBackground(new Color(0x222831));

        datePickerSerie = new DatePicker(calendarLibroIMG);

        calendarSerieIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarSerieIMG.isEnabled() == true) {
                    datePickerSerie.d.setVisible(true);
                    dataSerieField.setText(datePickerSerie.setPickedDate());
                }
            }
        });

        spinnerLibriSerie.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)spinnerLibriSerie.getValue() > 999) spinnerLibriSerie.setValue(999);
                if((int)spinnerLibriSerie.getValue() < 0) spinnerLibriSerie.setValue(0);
            }
        });

        okSerieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                okSerieBT.setBackground(Color.decode("#cf9e29"));
            }
        });
        okSerieBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                okSerieBT.setBackground(Color.decode("#FFD369"));
            }
        });

        okSerieBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isbnSeriePanel.removeAll();
                isbnLibroCount = 0;
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue(),controller.baseFontSize, controller.textFieldFont);
                isbnSeriePanel.revalidate();
                isbnSeriePanel.repaint();
            }
        });

        inviaSerieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaSerieButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        inviaSerieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaSerieButton.setBackground(Color.decode("#FFD369"));
            }
        });

        inviaSerieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isbnSerieField.getText().isBlank()) {
                    if(!titoloSerieField.getText().isBlank()) {
                        if(!dataSerieField.getText().isBlank()) {
                            ArrayList<String> libriISBN = takeISBN();
                            if (libriISBN != null) {
                                boolean error = false;
                                int h = 0;
                                while (error == false && h < libriISBN.size()) {
                                    if (controller.getDataLibro(libriISBN.get(h)).after(Date.valueOf(dataSerieField.getText())) == true) {
                                        error = true;
                                    }
                                    h++;
                                }
                                if (error == true) {
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Uno o più libri sono pubblicati dopo la serie");
                                } else {
                                    if (controller.creaSerie(libriISBN, isbnSerieField.getText().toString(), titoloSerieField.getText().toString().replace("'", "’"), dataSerieField.getText().toString()) == false) {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Serie già presente nel Database");
                                    } else {
                                        controller.listaSerie.add(controller.nuovoSerie);

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
                            }
                        }else {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi");
                        }
                    } else {
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi");
                    }
                } else {
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi");
                }
            }
        });

        // FASCICOLI //
        titoloRivistaCB.setSelectedIndex(-1);
        issueScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
                this.scrollBarWidth = (int)(controller.screenWidth/75);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
                    }
                };

                decreaseButton.setBackground(new Color(0x222831));
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

                increaseButton.setBackground(new Color(0x222831));
                return increaseButton;
            }

            private Image getAppropriateIcon(int orientation){
                switch(orientation){
                    case SwingConstants.SOUTH: return dA;
                    case SwingConstants.NORTH: return uA;
                    case SwingConstants.EAST: return rA;
                    default: return lA;
                }
            }
        });

        Object comp3 = titoloRivistaCB.getUI().getAccessibleChild(titoloRivistaCB, 0);
        if(comp3 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp3;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0x222831);
                    this.trackColor= new Color(0xFFD369);
                    this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                    this.thumbLightShadowColor = new Color(0x323A48);
                    this.thumbHighlightColor = new Color(0x323A48);
                    this.trackHighlightColor = new Color(0xCF9E29);
                    this.scrollBarWidth = (int)(controller.screenWidth/75);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                        }
                    };

                    decreaseButton.setBackground(new Color(0x222831));
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

                    increaseButton.setBackground(new Color(0x222831));
                    return increaseButton;
                }

                private Image getAppropriateIcon(int orientation){
                    switch(orientation){
                        case SwingConstants.SOUTH: return dA;
                        case SwingConstants.NORTH: return uA;
                        case SwingConstants.EAST: return rA;
                        default: return lA;
                    }
                }
            });
        }

        Component editorComp2 = titoloRivistaCB.getEditor().getEditorComponent();
        if (editorComp2 instanceof JTextField) {
            JTextField textField = (JTextField) editorComp2;
            textField.setBackground(new Color(0xFFD369));
            textField.setForeground(new Color(0x222831));
        }

        Object comp4 = numeroFascicoloCB.getUI().getAccessibleChild(numeroFascicoloCB, 0);
        if(comp4 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp4;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0x222831);
                    this.trackColor= new Color(0xFFD369);
                    this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                    this.thumbLightShadowColor = new Color(0x323A48);
                    this.thumbHighlightColor = new Color(0x323A48);
                    this.trackHighlightColor = new Color(0xCF9E29);
                    this.scrollBarWidth = (int)(controller.screenWidth/75);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension((int)(controller.screenWidth/51.2),(controller.screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                        }
                    };

                    decreaseButton.setBackground(new Color(0x222831));
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

                    increaseButton.setBackground(new Color(0x222831));
                    return increaseButton;
                }

                private Image getAppropriateIcon(int orientation){
                    switch(orientation){
                        case SwingConstants.SOUTH: return dA;
                        case SwingConstants.NORTH: return uA;
                        case SwingConstants.EAST: return rA;
                        default: return lA;
                    }
                }
            });
        }

        Component editorComp3 = numeroFascicoloCB.getEditor().getEditorComponent();
        if (editorComp3 instanceof JTextField) {
            JTextField textField = (JTextField) editorComp3;
            textField.setBackground(new Color(0xFFD369));
            textField.setForeground(new Color(0x222831));
        }

        issueScrollPanel.setBackground(new Color(0x222831));
        issueScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        issueScrollPanel.getViewport().setBackground(new Color(0x222831));


        datePickerFascicoli = new DatePicker(calendarIssueIMG);

        calendarIssueIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(calendarIssueIMG.isEnabled() == true) {
                    datePickerFascicoli.d.setVisible(true);
                    dpFascicoloField.setText(datePickerFascicoli.setPickedDate());
                }
            }
        });

        annoPrivistaSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)annoPrivistaSpinner.getValue() > Year.now().getValue()) annoPrivistaSpinner.setValue(Year.now().getValue());
                if((int)annoPrivistaSpinner.getValue() < 1900) annoPrivistaSpinner.setValue(1900);
            }
        });

        quantitaFascicoloSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)quantitaFascicoloSpinner.getValue() > 9999) quantitaFascicoloSpinner.setValue(9999);
                if((int)quantitaFascicoloSpinner.getValue() < 0) quantitaFascicoloSpinner.setValue(0);
            }
        });

        aggiungiArticoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                aggiungiArticoloBT.setBackground(Color.decode("#cf9e29"));
            }
        });
        aggiungiArticoloBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                aggiungiArticoloBT.setBackground(Color.decode("#FFD369"));
            }
        });

        aggiungiArticoloBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                articoliFascicoliCount++;
                initComponentsArticoli(controller);
                articoliPanel.revalidate();
                articoliPanel.repaint();
            }
        });

        for (int i = 0; i < controller.listaRiviste.size(); i++) {
            titoloRivistaCB.addItem(controller.listaRiviste.get(i).titolo);
        }
        titoloRivistaCB.setSelectedIndex(-1);

        titoloRivistaCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titoloRivistaCB.getSelectedIndex();
                if(titoloRivistaCB.getSelectedIndex() >= 0 && titoloRivistaCB.getSelectedIndex() <= controller.listaRiviste.size()){
                    issnRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).issn);
                    issnRivistaField.setEnabled(false);

                    argomentoRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).argomento);
                    argomentoRivistaField.setEnabled(false);

                    nomeRField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.substring(0,controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.indexOf('#')));
                    nomeRField.setEnabled(false);

                    cognomeRField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.substring(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.indexOf('#')+1, controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).responsabile.length()));
                    cognomeRField.setEnabled(false);

                    editoreRivistaField.setText(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).editore);
                    editoreRivistaField.setEnabled(false);

                    annoPrivistaSpinner.setValue(controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).annoPubblicazione);
                    annoPrivistaSpinner.setEnabled(false);
                    numeroFascicoloCB.removeAllItems();

                    controller.getFascicoliRivista((controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex()).issn));

                    for (int i = 0; i < controller.listaFascicoliRivista.size(); i++){
                        numeroFascicoloCB.addItem(controller.listaFascicoliRivista.get(i).numero);
                    }
                    numeroFascicoloCB.setSelectedIndex(-1);
                } else {
                    issnRivistaField.setText("");
                    issnRivistaField.setEnabled(true);

                    argomentoRivistaField.setText("");
                    argomentoRivistaField.setEnabled(true);

                    nomeRField.setText("");
                    nomeRField.setEnabled(true);

                    cognomeRField.setText("");
                    cognomeRField.setEnabled(true);

                    editoreRivistaField.setText("");
                    editoreRivistaField.setEnabled(true);

                    annoPrivistaSpinner.setValue(Year.now().getValue());
                    annoPrivistaSpinner.setEnabled(true);
                }
            }
        });

        numeroFascicoloCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroFascicoloCB.getSelectedIndex();
                if(numeroFascicoloCB.getSelectedIndex() >= 0 && numeroFascicoloCB.getSelectedIndex() <= controller.listaFascicoliRivista.size()){
                    dpFascicoloField.setText(String.valueOf(controller.listaFascicoliRivista.get(numeroFascicoloCB.getSelectedIndex()).dataPubblicazione));
                    dpFascicoloField.setEnabled(false);

                    calendarIssueIMG.setEnabled(false);
                    articoliFascicoloLabel.setVisible(false);
                    aggiungiArticoloBT.setVisible(false);
                } else {
                    dpFascicoloField.setText("");
                    dpFascicoloField.setEnabled(true);

                    calendarIssueIMG.setEnabled(true);
                    articoliFascicoloLabel.setVisible(true);
                    aggiungiArticoloBT.setVisible(true);
                }
            }
        });

        inviaFascicoloBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titoloRivistaCB.getSelectedItem() == null || titoloRivistaCB.getSelectedItem().equals("")) {
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Seleziona o crea una Rivista!");
                } else {
                    if (issnRivistaField.getText().isBlank() || nomeRField.getText().isBlank() || cognomeRField.getText().isBlank() || editoreRivistaField.getText().isBlank() || argomentoRivistaField.getText().isBlank()) {
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi!");
                    } else {
                        if (issnRivistaField.isEnabled() == true) {
                            if (controller.creaRivista(titoloRivistaCB.getSelectedItem().toString().replace("'", "’"), issnRivistaField.getText(), argomentoRivistaField.getText().replace("'", "’"), nomeRField.getText().replace("'", "’"), cognomeRField.getText().replace("'", "’"), editoreRivistaField.getText().replace("'", "’"), (int) annoPrivistaSpinner.getValue()) == false) {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa rivista è già presente");
                            } else {
                                controller.listaRiviste.add(controller.nuovaRivista);

                                if (dpFascicoloField.isEnabled() == true) {
                                    if (articoliFascicoliCount <= 0 || numeroFascicoloCB.getSelectedItem() == null || dpFascicoloField.getText().isBlank() == true) {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo non ha articoli o non è presente il numero o la data!");
                                        controller.eliminaRivista();
                                    } else {
                                        if ((int) annoPrivistaSpinner.getValue() <= Date.valueOf(dpFascicoloField.getText()).getYear()+1900) {
                                            if (controller.creaFascicolo(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()), dpFascicoloField.getText()) == false) {
                                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è gia presente!!");
                                                controller.eliminaRivista();
                                            } else {
                                                if (takeArticoli(controller, true) == true) {
                                                    controller.listaFascicoli.add(controller.nuovoFascicolo);

                                                    inserimentoEaggiornamentoF(controller, model, true, frameC);
                                                }
                                            }
                                        }else {
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è pubblicato prima della rivista!");
                                        }
                                    }

                                } else {
                                    controller.nuovoFascicolo = controller.listaFascicoliRivista.get(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()));

                                    inserimentoEaggiornamentoF(controller, model, false, frameC);
                                }
                            }
                        } else {
                            controller.nuovaRivista = controller.listaRiviste.get(titoloRivistaCB.getSelectedIndex());

                            if (dpFascicoloField.isEnabled() == true) {
                                if (articoliFascicoliCount <= 0 || numeroFascicoloCB.getSelectedItem() == null || dpFascicoloField.getText().isBlank() == true) {
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo non ha articoli o non è presente il numero o la data!");

                                } else {
                                    if ((int) annoPrivistaSpinner.getValue() <= Date.valueOf(dpFascicoloField.getText()).getYear()+1900) {
                                        if (controller.creaFascicolo(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()), dpFascicoloField.getText()) == false) {
                                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è gia presente!!");
                                        } else {
                                            if (takeArticoli(controller, false) == true) {
                                                controller.listaFascicoli.add(controller.nuovoFascicolo);

                                                inserimentoEaggiornamentoF(controller, model, false, frameC);
                                            }
                                        }
                                    }
                                    else {
                                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo fascicolo è pubblicato prima della rivista!");
                                    }
                                }

                            } else {
                                controller.nuovoFascicolo = controller.listaFascicoliRivista.get(Integer.valueOf(numeroFascicoloCB.getSelectedItem().toString()));

                                inserimentoEaggiornamentoF(controller, model, false, frameC);
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
                                            if(nuova_rivista == true) controller.eliminaRivista();
                                            return false;
                                        } else {
                                            if (Date.valueOf(dpFascicoloField.getText()).getYear()+1900 >= (int) spinner.getValue()) {
                                                if (controller.creaArticolo(comboBox.getSelectedItem().toString().replace("'", "’"), (int) spinner.getValue())) {
                                                    if (takeAutoriArticolo(controller, jpanel2) == false) {
                                                        controller.eliminaArticolo();
                                                        controller.eliminaFascicolo();
                                                        if (nuova_rivista == true) controller.eliminaRivista();
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
                                                if (nuova_rivista == true) controller.eliminaRivista();
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
        if(ricorsive == 0) return;
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
                scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
                scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                    ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                    Image uA = upArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                    Image dA = downArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                    Image rA = rightArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                    Image lA = leftArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = new Color(0x222831);
                        this.trackColor= new Color(0xFFD369);
                        this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                        this.thumbLightShadowColor = new Color(0x323A48);
                        this.thumbHighlightColor = new Color(0x323A48);
                        this.trackHighlightColor = new Color(0xCF9E29);
                        this.scrollBarWidth = (int)(screenWidth/75);
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension((int)(screenWidth/51.2),(screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
                            }
                        };

                        decreaseButton.setBackground(new Color(0x222831));
                        return decreaseButton;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        JButton increaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension((int)(screenWidth/51.2),(screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
                            }
                        };

                        increaseButton.setBackground(new Color(0x222831));
                        return increaseButton;
                    }

                    private Image getAppropriateIcon(int orientation){
                        switch(orientation){
                            case SwingConstants.SOUTH: return dA;
                            case SwingConstants.NORTH: return uA;
                            case SwingConstants.EAST: return rA;
                            default: return lA;
                        }
                    }
                });
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
                scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
                scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                    ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                    Image uA = upArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                    Image dA = downArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                    Image rA = rightArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                    Image lA = leftArrow.getImage().getScaledInstance((screenWidth/128),(screenHeight/72) ,Image.SCALE_SMOOTH);    //imposta le dimensioni dell'immagine
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = new Color(0x222831);
                        this.trackColor= new Color(0xFFD369);
                        this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                        this.thumbLightShadowColor = new Color(0x323A48);
                        this.thumbHighlightColor = new Color(0x323A48);
                        this.trackHighlightColor = new Color(0xCF9E29);
                        this.scrollBarWidth = (int)(controller.screenWidth/75);
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension((int)(screenWidth/51.2),(screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'
                            }
                        };

                        decreaseButton.setBackground(new Color(0x222831));
                        return decreaseButton;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        JButton increaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension((int)(screenWidth/51.2),(screenHeight/20));   //inizializza le dimensioni del JButton 'decreaseButton'

                            }
                        };

                        increaseButton.setBackground(new Color(0x222831));
                        return increaseButton;
                    }

                    private Image getAppropriateIcon(int orientation){
                        switch(orientation){
                            case SwingConstants.SOUTH: return dA;
                            case SwingConstants.NORTH: return uA;
                            case SwingConstants.EAST: return rA;
                            default: return lA;
                        }
                    }
                });
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
