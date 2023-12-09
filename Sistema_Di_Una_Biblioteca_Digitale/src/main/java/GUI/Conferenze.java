package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class Conferenze {
    public JFrame frame;
    private JPanel contentPanel;
    private JPanel closePanel;
    private JLabel closeBT;
    private JScrollPane conferenzeScroll;
    private JPanel conferenzePanel;
    private JTable conferenzeTable;
    private JLabel conferenzaLabel;
    private JScrollPane scrollpage;
    private JTextField strutturaField;
    private JTextField viaField;
    private JTextField comuneField;
    private JTextField capField;
    private JTextField ncField;
    private JTextField provinciaField;
    private JTextField nazioneField;
    private JTextField dataInizioField;
    private JLabel calendarIMG;
    private JButton aggiungiButton;
    private JButton inviaButton;
    private JButton annullaButton;
    private JTextField dataFineField;
    private JLabel calendarIMG2;
    private JPanel addConferenzaPanel;
    private JLabel conferenzeLabel;
    private JLabel strutturaLabel;
    private JLabel indirizzoLabel;
    private JLabel viaLabel;
    private JLabel comuneLabel;
    private JLabel capLabel;
    private JLabel nazioneLabel;
    private JLabel provinciaLabel;
    private JLabel numeroCivicoLabel;
    private JLabel dataInizioLabel;
    private JLabel dataFineLabel;
    private JPanel conferenzePanel2;
    private DatePickerMoreDay datePicker;
    private DatePickerMoreDay datePicker2;
    private DefaultTableModel model;
    private NewComboBox conferenzeCB = new NewComboBox<>();
    private boolean active = true;

    public Conferenze(JFrame frameC, Controller controller){
        datePicker = new DatePickerMoreDay(calendarIMG);
        datePicker2 = new DatePickerMoreDay(calendarIMG2);

        conferenzaLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'conferenzaLabel'

        conferenzeTable.setFont(controller.impactFontSize); //imposta il font della JTable 'elementiTable'
        conferenzeTable.setRowMargin(controller.screenWidth/640);   //imposta il margine tra le celle della JTable 'conferenzeTable'
        conferenzeTable.setRowHeight(controller.screenHeight/36);   //imposta l'altezza delle righe della JTable 'conferenzeTable'

        aggiungiButton.setFont(controller.baseFontSize);    //imposta il font del JButton 'aggiungiButton'
        annullaButton.setFont(controller.baseFontSize); //imposta il font del JButton 'annullaButton'
        conferenzeLabel.setFont(controller.baseFontSize);   //imposta il font del JButton 'conferenzeButton'

        strutturaLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'strutturaLabel'
        strutturaField.setFont(controller.textFieldFont);   //imposta il font della JTextField 'strutturaField'
        strutturaField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'strutturaField'
        indirizzoLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'indirizzoLabel'
        viaLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'viaLabel'
        viaField.setFont(controller.textFieldFont); //imposta il font della JLabel 'viaLabel'
        viaField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'viaField'
        numeroCivicoLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'numeroCivicoLabel'
        ncField.setFont(controller.textFieldFont);  //imposta il font della JTextField 'ncField'
        ncField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'ncField'
        comuneLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'comuneLabel'
        comuneField.setFont(controller.textFieldFont);  //imposta il font della JTextField 'comuneField'
        comuneField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'comuneField'
        provinciaLabel.setFont(controller.baseFontSize);    //imposta il font della JLabel 'provinciaLabel'
        provinciaField.setFont(controller.textFieldFont);   //imposta il font della JTextField 'provinciaField'
        provinciaField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24)));  //imposta la dimensione minima del JTextField 'provinciaField'
        capLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'capLabel'
        capField.setFont(controller.textFieldFont); //imposta il font della JTextField 'capField'
        capField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'capField'
        nazioneLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'nazioneLabel'
        nazioneField.setFont(controller.textFieldFont); //imposta il font della JTextField 'nazioneField'
        nazioneField.setMinimumSize(new Dimension((int)(controller.screenHeight/5), (int)(controller.screenHeight/24)));    //imposta la dimensione minima del JTextField 'capField'

        dataInizioLabel.setFont(controller.baseFontSize);   //imposta il font della JLabel 'dataInizioLabel'
        dataInizioField.setFont(controller.textFieldFont);  //imposta il font della JTextField 'dataInizioField'
        dataInizioField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24)));   //imposta la dimensione minima del JTextField 'dataInizioField'
        dataFineLabel.setFont(controller.baseFontSize); //imposta il font della JLabel 'dataFineLabel'
        dataFineField.setFont(controller.textFieldFont);    //imposta il font della JTextField 'dataFineField'
        dataFineField.setMinimumSize(new Dimension(-1, (int)(controller.screenHeight/24))); //imposta la dimensione minima del JTextField 'dataFineField'

        inviaButton.setFont(controller.baseFontSize);   //imposta il font del JButton 'inviaButton'

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            aggiungiButton.setVisible(false);   //rende invisibile il JButton 'aggiungiButton'
        }

        UIManager.put("ScrollPane.background\n", new Color(0x222831));  //imposta il colore dello sfondo del JScrollPane

        conferenzaLabel.setText("("+ controller.doi_selected + ") " + controller.nome_articolo.substring(0,15) + "... - CONFERENZE:");  //imposta il testo della JLabel 'conferenzaLabel' con il DOI e il nome dell'articolo selezionato
        conferenzaLabel.setToolTipText(controller.nome_articolo);   //imposta a 'controller.nome_articolo' il testo da visualizzare quando il mouse si trova sopra 'conferenzaLabel'

        conferenzeScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine

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

        scrollpage.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));  //carica l'immagine nel percorso /up.png
            Image uA = upArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'imma
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));  //carica l'immagine nel percorso /down.png
            Image dA = downArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'imma
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));    //carica l'immagine nel percorso /right.png
            Image rA = rightArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'imma
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));  //carica l'immagine nel percorso /left.png
            Image lA = leftArrow.getImage().getScaledInstance((controller.screenWidth/128),(controller.screenHeight/72), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'imma

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

        scrollpage.setBackground(new Color(0x222831));  //imposta il colore dello sfondo del JScrollPane 'scrollpage'
        scrollpage.setBorder(BorderFactory.createEmptyBorder());    //toglie il bordo del JScrollPane 'scrollpage'
        scrollpage.getViewport().setBackground(new Color(0x222831));    //imposta il colore dello sfondo della parte visibile del JScrollPane 'scrollpage'

        frame = new JFrame("Biblioteca Digitale");

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(this.contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/1.5609), (int) (controller.screenHeight/1.44)); //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));   //imposta il bordo del JPanel 'contentPanel'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Luogo", "Struttura", "Data d'inizio", "Data di fine"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();   //DefaultTableCellRenderer per la formattazione dell'header della tabella

        headerRenderer.setBackground(new Color(0xCF9E29));  //imposta il colore dello sfondo dell'header della tabella
        headerRenderer.setForeground(new Color(0xEEEEEE));  //imposta il colore del testo dell'header della tabella
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);   //centra orizzontalmente il contenuto dell'header della tabella

        Font headerFont = new Font("Impact", Font.PLAIN, 15); //imposta il font Bebas Neue, grandezza 15 e stile Regular

        headerRenderer.setFont(headerFont); //inizializza il font Bebas Neue, grandezza 15 e stile Regular per i caratteri della tabella

        JTableHeader tableHeader = conferenzeTable.getTableHeader();    //inizializza il JTableHeader 'tableHeader' con l'header della JTable 'conferenzaTable'

        tableHeader.setDefaultRenderer(headerRenderer); //imposta il render di default della tabella a 'headerRender'

        conferenzeTable.getTableHeader().setResizingAllowed(false); //impedisce il ridimensionamento delle colonne

        conferenzeTable.getTableHeader().setReorderingAllowed(false);   //impedisce il ridimensionamento delle colonne

        tableHeader.setBorder(null);    //rimuove il bordo dell'header della tabella

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));  //imposta il render di default della tabella

        conferenzeTable.setModel(model); //imposta il modello dei dati della JTable 'conferenzeTable'
        conferenzeScroll.setBackground(new Color(0x222831));    //imposta il colore dello sfondo del JScrollPane 'conferenzeScroll'
        conferenzeScroll.setBorder(BorderFactory.createEmptyBorder());  //toglie il bordo del JScrollPane 'conferenzeScroll'
        conferenzeScroll.getViewport().setBackground(new Color(0x222831));  //imposta il colore dello sfondo della parte visibile del JScrollPane 'conferenzeScroll'

        controller.getConferenzeArticolo(); //inizializza 'controller.listaConferenze'

        for(int i = 0; i < controller.listaConferenze.size(); i++){ //scorre 'controller.listaConferenze'
            model.addRow(new Object[]{controller.listaConferenze.get(i).luogo, controller.listaConferenze.get(i).struttura, controller.listaConferenze.get(i).dataInizio, controller.listaConferenze.get(i).dataFine}); //aggiunge una nuova riga nella tabella
        }

        conferenzeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Point point = e.getPoint(); //punto cliccato
                int row = conferenzeTable.rowAtPoint(point);    //riga selezionata
                int column = 0; //inizializza la colonna selezionata a 0

                if (row >= 0 && column >= 0) {  //controlla se la riga e la colonna sono maggiore o uguale a 0
                    Object cellValue = conferenzeTable.getValueAt(row, column); //contenuto della cella selezionato
                    conferenzeTable.setToolTipText(cellValue.toString());   //imposta a 'controller.nome_articolo' il testo da visualizzare quando il mouse si trova sopra 'conferenzeTable'
                }
            }
        });

        annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'
        addConferenzaPanel.setVisible(false);   //rende invisibile il JPanel 'addConferenzaPanel'

        calendarIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                datePicker.d.setVisible(true);  //rende visibile il calendario
                dataInizioField.setText(datePicker.setPickedDate());    //imposta il testo del JTextField 'dataInizioField' con la data scelta dall'utente
            }
        });

        calendarIMG2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                datePicker2.d.setVisible(true); //rende visibile il calendario
                dataFineField.setText(datePicker2.setPickedDate()); //imposta il testo del JTextField 'dataFineField' con la data scelta dall'utente
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (active == true){    //controlla se bisogna attivare il JButton 'aggiungiButton'
                    aggiungiButton.setBackground(Color.decode("#cf9e29"));  //imposta lo sfondo del JButton 'aggiungiButton'
                }
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (active == true) {   //controlla se bisogna attivare il JButton 'aggiungiButton'
                    aggiungiButton.setBackground(Color.decode("#FFD369"));  //imposta lo sfondo del JButton 'aggiungiButton'
                }
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                annullaButton.setVisible(true); //rende visibile il JButton 'annullaButton'
                aggiungiButton.setEnabled(false);   //disabilita il JButton 'aggiungiButton'
                active = false; //pone a false 'active'
                addConferenzaPanel.setVisible(true);    //rende visibile il JPanel 'addConferenzaPanel'
                aggiungiButton.setBackground(Color.decode("#FFD369"));  //imposta lo sfondo del JButton 'aggiungiButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                annullaButton.setBackground(Color.decode("#cf9e29"));   //imposta lo sfondo del JButton 'annullaButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                annullaButton.setBackground(Color.decode("#FFD369"));   //imposta lo sfondo del JButton 'annullaButton'
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                annulla_action();   //annulla la creazione della nuova conferenza
            }
        });

        controller.getConferenze(); //inizializza 'controller.listaAllConferenze'

        Dimension dim = new Dimension((int) (strutturaField.getSize().width), controller.screenHeight/24);  //dimensione preferita dei combo box 'conferenzeCB'

        conferenzeCB.setPreferredSize(dim); //imposta a 'dim' la dimensione preferita del combo box 'conferenzeCB'
        conferenzeCB.setFont(controller.textFieldFont); //imposta il font del combo box 'conferenzeCB'
        conferenzeCB.setFocusable(false);   //impedisce all'utente di interagire con il combo box 'conferenzeCB' tramite tastiera
        conferenzeCB.setEditable(false);    //impedisce all'utente di inserire il testo nel combo box 'conferenzeCB'
        conferenzePanel2.add(conferenzeCB); //inserisce 'conferenzeCB' nel JPanel 'conferenzePanel2'

        for (int i = 0; i < controller.listaAllConferenze.size(); i++){ //scorre 'controller.listaAllConferenze'
                conferenzeCB.addItem(controller.listaAllConferenze.get(i).struttura + " " + controller.listaAllConferenze.get(i).dataInizio.toString() + "/" + controller.listaAllConferenze.get(i).dataFine.toString());   //aggiunge nel menu del JComboBox 'conferenzeCB' il luogo e le date di inizio e di fine dell'i-esima conferenza
        }

        conferenzeCB.setSelectedIndex(-1);  //deseleziona 'conferenzeCB'

        conferenzeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conferenzeCB.getSelectedIndex() < 0 || conferenzeCB.getSelectedIndex() > (controller.listaAllConferenze.size()-1)){  //controlla se l'indice selezionato nel JComboBox 'titoloRivistaCB' non è in [0, //controlla se l'indice selezionato nel JComboBox 'titoloRivistaCB' non è in [0, controller.listaRiviste.size().size()-1]
                    strutturaField.setEnabled(true);    //abilita il JTextField 'strutturaField'
                    viaField.setEnabled(true);  //abilita il JTextField 'viaField'
                    ncField.setEnabled(true);   //abilita il JTextField 'ncField'
                    provinciaField.setEnabled(true);    //abilita il JTextField 'provinciaField'
                    dataInizioField.setEnabled(true);   //abilita il JTextField 'dataInizioField'
                    dataFineField.setEnabled(true); //abilita il JTextField 'dataFineField'
                    nazioneField.setEnabled(true);  //abilita il JTextField 'nazioneField'
                    comuneField.setEnabled(true);   //abilita il JTextField 'comuneField'
                    capField.setEnabled(true);  //abilita il JTextField 'comuneField'

                    strutturaField.setText(""); //svuota il testo del JTextField 'strutturaField'
                    viaField.setText("");   //svuota il testo del JTextField 'viaField'
                    ncField.setText("");    //svuota il testo del JTextField 'ncField'
                    provinciaField.setText(""); //svuota il testo del JTextField 'provinciaField'
                    dataInizioField.setText("");    //svuota il testo del JTextField 'dataInizioField'
                    dataFineField.setText("");  //svuota il testo del JTextField 'dataFineField'
                    nazioneField.setText("");   //svuota il testo del JTextField 'nazioneField'
                    comuneField.setText("");    //svuota il testo del JTextField 'comuneField'
                    capField.setText("");   //svuota il testo del JTextField 'capField'
                } else{
                    strutturaField.setEnabled(false);   //disabilita il JTextField 'strutturaField'
                    viaField.setEnabled(false); //disabilita il JTextField 'viaField'
                    ncField.setEnabled(false);  //disabilita il JTextField 'ncField'
                    provinciaField.setEnabled(false);   //disabilita il JTextField 'provinciaField'
                    dataInizioField.setEnabled(false);  //disabilita il JTextField 'dataInizioField'
                    dataFineField.setEnabled(false);    //disabilita il JTextField 'dataFineField'
                    nazioneField.setEnabled(false); //disabilita il JTextField 'nazioneField'
                    comuneField.setEnabled(false);  //disabilita il JTextField 'comuneField'
                    capField.setEnabled(false); //disabilita il JTextField 'comuneField'

                    strutturaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).struttura);   //imposta il testo del JTextField 'strutturaField' con il nome della struttura organizzatrice della conferenza selelezionata
                    dataInizioField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).dataInizio.toString());  //imposta il testo del JTextField 'dataInizioField' con la data di inizio della conferenza selezionata
                    dataFineField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).dataFine.toString());  //imposta il testo del JTextField 'dataFineField' con la data di fine della conferenza selezionata

                    if(!controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.contains("(")){    //controlla se la provincia del luogo della conferenza selezionata non è stata inserita
                        int pos1 = findFirstNumberPosition(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);   //posizione della rima cifra del numero civico del luogo della conferenza selezionata
                        int pos2 = 0; //seconda posizione
                        String temp = "";   //stringa temporanea

                        viaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(0, (pos1)-1));  //imposta il testo del JTextField 'viaField' con la via del luogo della conferenza selezionata

                        pos2 = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.indexOf(",");   //inserise in 'pos2' l'indice successivo alla fine del numero civico del luogo della conferenza selezionata

                        ncField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos1, pos2));    //imposta il testo del JTextField 'viaField' con il numero civico del luogo della conferenza selezionata
                        temp = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos2+2, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.length());   //inserisce in 'temp' il CAP, il comune e la nazione del luogo della conferenza selezionata
                        pos2 = temp.indexOf(",");   //inserisce in 'pos2' l'indice successivo alla fine del CAP del luogo della conferenza selezionata
                        capField.setText(temp.substring(0, pos2));  //imposta il testo del JTextField 'capField' con il CAP del luogo della conferenza selezionata
                        temp = temp.substring(pos2+2, temp.length());   //toglie da 'temp' il CAP del luogo della conferenza selezionata
                        pos2 = temp.indexOf(",");   //inserisce in 'pos2' l'indice successivo alla fine del comune del luogo della conferenza selezionata
                        comuneField.setText(temp.substring(0, pos2));   //imposta il testo del JTextField 'comuneField' con il comune del luogo della conferenza selezionata
                        temp = temp.substring(pos2+2, temp.length());   //toglie da 'temp' il comune del luogo della conferenza selezionata
                        nazioneField.setText(temp.substring(0, temp.length())); //imposta il testo del JTextField 'nazioneField' con la nazione del luogo della conferenza selezionata
                    } else {
                        int pos1 = findFirstNumberPosition(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);   //posizione della rima cifra del numero civico del luogo della conferenza selezionata
                        int pos2 = 0;   //seconda posizione
                        String temp = "";   //stringa temporanea

                        viaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(0, (pos1)-1));  //imposta il testo del JTextField 'viaField' con la via del luogo della conferenza selezionata

                        pos2 = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.indexOf(",");   //inserise in 'pos2' l'indice successivo alla fine del numero civico del luogo della conferenza selezionata

                        ncField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos1, pos2));    //imposta il testo del JTextField 'viaField' con il numero civico del luogo della conferenza selezionata
                        temp = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos2+2, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.length());   //inserisce in 'temp' il CAP, il comune, la provincia e la nazione del luogo della conferenza selezionata
                        pos2 = temp.indexOf(",");   //inserisce in 'pos2' l'indice successivo alla fine del CAP del luogo della conferenza selezionata
                        capField.setText(temp.substring(0, pos2));  //imposta il testo del JTextField 'capField' con il CAP del luogo della conferenza selezionata
                        temp = temp.substring(pos2+2, temp.length());   //toglie da 'temp' il CAP del luogo della conferenza selezionata
                        pos2 = temp.indexOf(",");   //inserisce in 'pos2' l'indice successivo alla fine del comune del luogo della conferenza selezionata
                        comuneField.setText(temp.substring(0, pos2));   //imposta il testo del JTextField 'comuneField' con il comune del luogo della conferenza selezionata
                        temp = temp.substring(pos2+2, temp.length());   //toglie da 'temp' il comune del luogo della conferenza selezionata
                        pos2 = temp.indexOf(",");   //inserisce in 'pos2' l'indice successivo alla fine della provincia del luogo della conferenza selezionata
                        provinciaField.setText(temp.substring(0, pos2));    //imposta il testo del JTextField 'provinciaField' con la provincia del luogo della conferenza selezionata
                        temp = temp.substring(pos2+2, temp.length());   //toglie da 'temp' la provincia del luogo della conferenza selezionata
                        nazioneField.setText(temp.substring(0, temp.length())); //imposta il testo del JTextField 'nazioneField' con la nazione del luogo della conferenza selezionata
                    }
                }
            }
        });


        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaButton.setBackground(Color.decode("#cf9e29")); //imposta lo sfondo del JButton 'inviaButton'
            }
        });

        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaButton.setBackground(Color.decode("#FFD369")); //imposta lo sfondo del JButton 'inviaButton'
            }
        });

        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (strutturaField.getText().isBlank() || viaField.getText().isBlank() || ncField.getText().isBlank() || comuneField.getText().isBlank() || capField.getText().isBlank() || nazioneField.getText().isBlank() || dataInizioField.getText().isBlank() || dataFineField.getText().isBlank()){  //controlla se non è stato inserito uno dei campi obbligatori
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");   //mostra un messaggio di errore
                } else {
                    if(Date.valueOf(dataInizioField.getText()).after(Date.valueOf(dataFineField.getText())) == true){   //controlla se la data di fine della conferenza viene prima di quella di inizio
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'evento non può finire prima che inizi!");  //mostra un messaggio di errore
                    } else {
                        if (controller.anno_pubblicazione > Date.valueOf(dataInizioField.getText()).getYear() + 1900) { //controlla se l'articolo è stato pubblicato dopo l'inizio della conferenza
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'articolo è stato scritto dopo!");  //mostra un messaggio di errore
                        } else {
                            String indirizzo = "";  //inidirizzo della conferenza

                            if (strutturaField.isEnabled() == false) {  //controlla se è stato disabilitato il JTextField 'strutturaField'
                                reload_table_cb(controller, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);  //se l'articolo selezionato non è stato gia esposto nella conferenza selezionata, aggiorna il contenuto della tabella, altrimenti mostra un messaggio di errore
                                annulla_action();   //chiude il JPanel per aggiungere una nuova conferenza
                            } else {
                                if (provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)")) { //controlla se non è stata inserita la provincia
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();    //inserisce in 'indirizzo' l'indirizzo inserito
                                }else {
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();  //inserisce in 'indirizzo' l'indirizzo inserito
                                }

                                if (controller.addConferenza(strutturaField.getText().replace("'", "’"), indirizzo.replace("'", "’"), dataInizioField.getText(), dataFineField.getText()) == false) {   //controlla se questa struttura gia è occupata per un'altra conferenza
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa struttura è gia occupata!");  //mostra un messaggio di errore
                                } else {
                                    reload_table_cb(controller, indirizzo.replace("'", "’"));   //sostituisce i "'" in 'indirizzo' con "'"
                                    annulla_action();   //chiude il JPanel per aggiungere una nuova conferenza
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void annulla_action(){   //chiude il JPanel er inserire una nuova conferenza
        strutturaField.setText(""); //svuota il testo del JTextField 'strutturaField'
        viaField.setText("");   //svuota il testo del JTextField 'viaField'
        nazioneField.setText("");   //svuota il testo del JTextField 'nazioneField'
        ncField.setText("");    //svuota il testo del JTextField 'ncField'
        comuneField.setText("");    //svuota il testo del JTextField 'comuneField'
        provinciaField.setText("(Opzionale)");  //imposta il testo del JTextField 'provinciaField'
        capField.setText("");   //svuota il testo del JTextField 'capField'
        dataInizioField.setText("");    //svuota il testo del JTextField 'dataInizioField'
        dataFineField.setText("");  //svuota il testo del JTextField 'dataFineField'

        addConferenzaPanel.setVisible(false);   //rende invisibile il JPanel 'addConferenzaPanel'
        annullaButton.setVisible(false);    //rende invisibile il JButton 'annullaButton'

        aggiungiButton.setEnabled(true);    //abilita il JButton 'aggiungiButton'
        active = true;  //aggiorna 'active'

        conferenzeCB.setSelectedIndex(-1);  //deseleziona 'conferenzeCB'
    }//fine annulla_action

    public void reload_table_cb(Controller controller, String indirizzo){   //se l'articolo selezionato non è stato gia esposto nella conferenza selezionata, aggiorna il contenuto della tabella, altrimenti mostra un messaggio di errorea
        if (controller.addEsposizione(strutturaField.getText().replace("'", "’"), indirizzo.replace("'", "’"), dataInizioField.getText(), dataFineField.getText()) == false){   //controlla se l'articolo è gia stato inserito nella conferenza
            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo articolo è già esposto in questa conferenza!");   //mostra un messaggio di errore
        } else {
            controller.getConferenzeArticolo(); //inizializza 'controller.listaConferenze'

            model.setRowCount(0);   //rimuove tutte le righe della tabella

            for (int i = 0; i < controller.listaConferenze.size(); i++) {   //scorre la lista 'controller.listaConferenze'
                model.addRow(new Object[]{controller.listaConferenze.get(i).luogo, controller.listaConferenze.get(i).struttura, controller.listaConferenze.get(i).dataInizio, controller.listaConferenze.get(i).dataFine}); //aggiunge una nuova riga nella tabella
            }

            controller.getConferenze(); //inizializza 'controller.listaAllConferenze'

            conferenzeCB.removeAllItems();  //rimuove tutti gli elementi nel JComboBox 'conferenzeCB'

            for (int i = 0; i < controller.listaAllConferenze.size(); i++){ //scorre la lista 'controller.listaAllConferenze'
                    conferenzeCB.addItem(controller.listaAllConferenze.get(i).struttura + " " + controller.listaAllConferenze.get(i).dataInizio.toString() + "/" + controller.listaAllConferenze.get(i).dataFine.toString());   //aggiunge una nuova riga nella tabella
            }

            conferenzeCB.setSelectedIndex(-1);  //deseleziona 'conferenzeCB'
        }
    }//fine reload_table_cb

    public static int findFirstNumberPosition(String input) {   //se c'è, restituisce l'indice della prima cifra in 'input', altrimenti restituise -1
        int length = input.length();    //lunghezza di 'input'

        for (int i = 0; i < length; i++) {  //scorre 'input'
            if (Character.isDigit(input.charAt(i))) {   //controlla se l'i-esimo carattere di 'input' è una cifra
                return i;   //restituisce l'indice della prima cifra in 'input'
            }
        }

        return -1;  //restituisce -1
    }//fine findFirstNumberPosition

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
        calendarIMG2 = new JLabel(calendarIco); //inizializza la JLabel 'calendarIMG2' dell'icona del calendario

        strutturaField = new JTextField();  //inizializza il JTextField 'strutturaField'
        strutturaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'strutturaField'

        viaField = new JTextField();    //inizializza il JTextField 'viaField'
        viaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'viaField'

        ncField = new JTextField(); //inizializza il JTextField 'ncField'
        ncField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'ncField'

        comuneField = new JTextField(); //inizializza il JTextField 'comuneSerieField'
        comuneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'comuneField'

        provinciaField = new JTextField();  //inizializza il JTextField 'provinciaField'
        provinciaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));  //imposta il colore del bordo del JTextField 'provinciaField'

        capField = new JTextField();    //inizializza il JTextField 'capField'
        capField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'capField'

        nazioneField = new JTextField();    //inizializza il JTextField 'nazioneField'
        nazioneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));    //imposta il colore del bordo del JTextField 'nazioneField'

        dataInizioField = new JTextField(); //inizializza il JTextField 'isbnSerieField'
        dataInizioField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JTextField 'dataInizioField'

        dataFineField = new JTextField();   //inizializza il JTextField 'dataFineField'
        dataFineField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));   //imposta il colore del bordo del JTextField 'dataFineField'
    }
}