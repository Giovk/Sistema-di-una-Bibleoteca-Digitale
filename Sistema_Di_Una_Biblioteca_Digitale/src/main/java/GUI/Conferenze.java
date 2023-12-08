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
            aggiungiButton.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
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
        contentPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));   //imposta il bordo del JPanel 'contentPane'
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

                if (row >= 0 && column >= 0) {
                    Object cellValue = conferenzeTable.getValueAt(row, column);
                    conferenzeTable.setToolTipText(cellValue.toString());
                }
            }
        });

        annullaButton.setVisible(false);
        addConferenzaPanel.setVisible(false);

        calendarIMG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                datePicker.d.setVisible(true);
                dataInizioField.setText(datePicker.setPickedDate());
            }
        });

        calendarIMG2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                datePicker2.d.setVisible(true);
                dataFineField.setText(datePicker2.setPickedDate());
            }
        });

        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (active == true)  aggiungiButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (active == true) aggiungiButton.setBackground(Color.decode("#FFD369"));
            }
        });
        aggiungiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                annullaButton.setVisible(true);
                aggiungiButton.setEnabled(false);
                active = false;
                addConferenzaPanel.setVisible(true);
                aggiungiButton.setBackground(Color.decode("#FFD369"));
            }
        });

        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                annullaButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                annullaButton.setBackground(Color.decode("#FFD369"));
            }
        });
        annullaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                annulla_action();
            }
        });

        controller.getConferenze();

        Dimension dim = new Dimension((int) (strutturaField.getSize().width), controller.screenHeight/24);

        conferenzeCB.setPreferredSize(dim);
        conferenzeCB.setFont(controller.textFieldFont);
        conferenzeCB.setFocusable(false);
        conferenzeCB.setEditable(false);
        conferenzePanel2.add(conferenzeCB);

        for (int i = 0; i < controller.listaAllConferenze.size(); i++){
                conferenzeCB.addItem(controller.listaAllConferenze.get(i).struttura + " " + controller.listaAllConferenze.get(i).dataInizio.toString() + "/" + controller.listaAllConferenze.get(i).dataFine.toString());
        }



        conferenzeCB.setSelectedIndex(-1);

        conferenzeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(conferenzeCB.getSelectedIndex() >= 0 && conferenzeCB.getSelectedIndex() <= (controller.listaAllConferenze.size()-1)){
                    strutturaField.setEnabled(false);
                    viaField.setEnabled(false);
                    ncField.setEnabled(false);
                    provinciaField.setEnabled(false);
                    dataInizioField.setEnabled(false);
                    dataFineField.setEnabled(false);
                    nazioneField.setEnabled(false);
                    comuneField.setEnabled(false);
                    capField.setEnabled(false);

                    strutturaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).struttura);
                    dataInizioField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).dataInizio.toString());
                    dataFineField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).dataFine.toString());

                    if(!controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.contains("(")){
                        int pos1 = findFirstNumberPosition(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);
                        int pos2 = 0;


                        String temp = "";
                        viaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(0, (pos1)-1));

                        pos2 = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.indexOf(",");

                        ncField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos1, pos2));
                        temp = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos2+2, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.length());
                        pos2 = temp.indexOf(",");
                        capField.setText(temp.substring(0, pos2));
                        temp = temp.substring(pos2+2, temp.length());
                        pos2 = temp.indexOf(",");
                        comuneField.setText(temp.substring(0, pos2));
                        temp = temp.substring(pos2+2, temp.length());
                        nazioneField.setText(temp.substring(0, temp.length()));
                    } else {
                        int pos1 = findFirstNumberPosition(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);
                        int pos2 = 0;


                        String temp = "";
                        viaField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(0, (pos1)-1));

                        pos2 = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.indexOf(",");

                        ncField.setText(controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos1, pos2));
                        temp = controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.substring(pos2+2, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo.length());
                        pos2 = temp.indexOf(",");
                        capField.setText(temp.substring(0, pos2));
                        temp = temp.substring(pos2+2, temp.length());
                        pos2 = temp.indexOf(",");
                        comuneField.setText(temp.substring(0, pos2));
                        temp = temp.substring(pos2+2, temp.length());
                        pos2 = temp.indexOf(",");
                        provinciaField.setText(temp.substring(0, pos2));
                        temp = temp.substring(pos2+2, temp.length());
                        nazioneField.setText(temp.substring(0, temp.length()));
                    }
                } else{
                    strutturaField.setEnabled(true);
                    viaField.setEnabled(true);
                    ncField.setEnabled(true);
                    provinciaField.setEnabled(true);
                    dataInizioField.setEnabled(true);
                    dataFineField.setEnabled(true);
                    nazioneField.setEnabled(true);
                    comuneField.setEnabled(true);
                    capField.setEnabled(true);

                    strutturaField.setText("");
                    viaField.setText("");
                    ncField.setText("");
                    provinciaField.setText("");
                    dataInizioField.setText("");
                    dataFineField.setText("");
                    nazioneField.setText("");
                    comuneField.setText("");
                    capField.setText("");
                }
            }
        });


        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inviaButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inviaButton.setBackground(Color.decode("#FFD369"));
            }
        });
        inviaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (strutturaField.getText().isBlank() || viaField.getText().isBlank() || ncField.getText().isBlank() || comuneField.getText().isBlank() || capField.getText().isBlank() || nazioneField.getText().isBlank() || dataInizioField.getText().isBlank() || dataFineField.getText().isBlank()){
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");
                } else {
                    if(Date.valueOf(dataInizioField.getText()).after(Date.valueOf(dataFineField.getText())) == true){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'evento non può finire prima che inizi!");
                    } else {
                        if (controller.anno_pubblicazione > Date.valueOf(dataInizioField.getText()).getYear() + 1900) {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'articolo è stato scritto dopo!");
                        } else {
                            String indirizzo = "";
                            if (strutturaField.isEnabled() == true) {
                                if (provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)"))
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();
                                else
                                    indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();

                                if (controller.addConferenza(strutturaField.getText().replace("'", "’"), indirizzo.replace("'", "’"), dataInizioField.getText(), dataFineField.getText()) == false) {
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa struttura è gia occupata!");
                                } else {
                                    reload_table_cb(controller, indirizzo.replace("'", "’"));
                                    annulla_action();
                                }
                            } else {
                                reload_table_cb(controller, controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);
                                annulla_action();
                            }
                        }
                    }
                }
            }
        });
    }

    public void annulla_action(){
        strutturaField.setText("");
        viaField.setText("");
        nazioneField.setText("");
        ncField.setText("");
        comuneField.setText("");
        provinciaField.setText("(Opzionale)");
        capField.setText("");
        dataInizioField.setText("");
        dataFineField.setText("");

        addConferenzaPanel.setVisible(false);
        annullaButton.setVisible(false);

        aggiungiButton.setEnabled(true);
        active = true;

        conferenzeCB.setSelectedIndex(-1);
    }

    public void reload_table_cb(Controller controller, String indirizzo){
        if (controller.addEsposizione(strutturaField.getText().replace("'", "’"), indirizzo.replace("'", "’"), dataInizioField.getText(), dataFineField.getText()) == false){
            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questo artico è gia esposto in questa conferenza!");
        } else {
            controller.getConferenzeArticolo();

            model.setRowCount(0);

            for (int i = 0; i < controller.listaConferenze.size(); i++) {
                model.addRow(new Object[]{controller.listaConferenze.get(i).luogo, controller.listaConferenze.get(i).struttura, controller.listaConferenze.get(i).dataInizio, controller.listaConferenze.get(i).dataFine});
            }

            controller.getConferenze();

            conferenzeCB.removeAllItems();

            for (int i = 0; i < controller.listaAllConferenze.size(); i++){
                    conferenzeCB.addItem(controller.listaAllConferenze.get(i).struttura + " " + controller.listaAllConferenze.get(i).dataInizio.toString() + "/" + controller.listaAllConferenze.get(i).dataFine.toString());
            }

            conferenzeCB.setSelectedIndex(-1);
        }
    }
    public static int findFirstNumberPosition(String input) {
        int length = input.length();

        for (int i = 0; i < length; i++) {
            if (Character.isDigit(input.charAt(i))) {
                return i;
            }
        }

        return -1; // Nessun numero trovato
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


        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));
        Image calendarIm = calendarIco.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH);
        calendarIco = new ImageIcon(calendarIm);
        calendarIMG = new JLabel(calendarIco);
        calendarIMG2 = new JLabel(calendarIco);

        strutturaField = new JTextField();
        strutturaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        viaField = new JTextField();
        viaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        ncField = new JTextField();
        ncField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        comuneField = new JTextField();
        comuneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        provinciaField = new JTextField();
        provinciaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        capField = new JTextField();
        capField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        nazioneField = new JTextField();
        nazioneField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        dataInizioField = new JTextField();
        dataInizioField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        dataFineField = new JTextField();
        dataFineField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
    }
}
