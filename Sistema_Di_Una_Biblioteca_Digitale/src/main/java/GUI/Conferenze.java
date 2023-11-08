package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
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
    private JComboBox conferenzeCB;
    private DatePickerMoreDay datePicker;
    private DatePickerMoreDay datePicker2;
    private DefaultTableModel model;
    private boolean active = true;

    public Conferenze(JFrame frameC, Controller controller){
        datePicker = new DatePickerMoreDay(calendarIMG);
        datePicker2 = new DatePickerMoreDay(calendarIMG2);

        if (controller.utente.partitaIVA == null) {   //controlla se la partita IVA dell'utente è nulla
            aggiungiButton.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
        }

        UIManager.put("ScrollPane.background\n", new Color(0x222831));

        conferenzaLabel.setText("("+ controller.doi_selected + ") " + controller.nome_articolo.substring(0,15) + "... - CONFERENZE:");
        conferenzaLabel.setToolTipText(controller.nome_articolo);

        conferenzeScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(25, 15);
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
                        return new Dimension(25, 15);
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

        scrollpage.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
            Image uA = upArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
            Image rA = rightArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
            ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
            Image lA = leftArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x222831);
                this.trackColor= new Color(0xFFD369);
                this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                this.thumbLightShadowColor = new Color(0x323A48);
                this.thumbHighlightColor = new Color(0x323A48);
                this.trackHighlightColor = new Color(0xCF9E29);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(25, 15);
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
                        return new Dimension(25, 15);
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

        scrollpage.setBackground(new Color(0x222831));
        scrollpage.setBorder(BorderFactory.createEmptyBorder());
        scrollpage.getViewport().setBackground(new Color(0x222831));

        frame = new JFrame("Conferenze");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(820, 500);
        frame.setLocationRelativeTo(null);
        contentPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setVisible(false);
                frame.dispose();
                frameC.setEnabled(true);
                frameC.toFront();
            }
        });

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frame.dispose();
                frameC.setEnabled(true);
                frameC.toFront();
            }
        });

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Luogo", "Struttura", "Data d'inizio", "Data di fine"}) {
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
        Font headerFont = new Font("Impact", Font.PLAIN, 15); // Imposta il font Bebas Neue, grandezza 15 e stile Regular
        headerRenderer.setFont(headerFont);
        JTableHeader tableHeader = conferenzeTable.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);

        // Impedire il ridimensionamento delle colonne
        conferenzeTable.getTableHeader().setResizingAllowed(false);

        // Impedire il riordinamento delle colonne
        conferenzeTable.getTableHeader().setReorderingAllowed(false);

        // Rimuovere il bordo dell'header della tabella
        tableHeader.setBorder(null);

        tableHeader.setDefaultRenderer(new SeparatorHeaderRenderer(tableHeader.getDefaultRenderer()));


        conferenzeTable.setModel(model); //imposta il modello dei dati della JTable 'booksTable'
        conferenzeScroll.setBackground(new Color(0x222831));
        conferenzeScroll.setBorder(BorderFactory.createEmptyBorder());
        conferenzeScroll.getViewport().setBackground(new Color(0x222831));

        controller.getConferenzeArticolo();

        for(int i = 0; i < controller.listaConferenze.size(); i++){
            model.addRow(new Object[]{controller.listaConferenze.get(i).luogo, controller.listaConferenze.get(i).struttura, controller.listaConferenze.get(i).dataInizio, controller.listaConferenze.get(i).dataFine});
        }



        conferenzeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Point point = e.getPoint();
                int row = conferenzeTable.rowAtPoint(point);
                int column = 0;

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
            public void mouseClicked(MouseEvent e) {
                datePicker.d.setVisible(true);
                dataInizioField.setText(datePicker.setPickedDate());
            }
        });

        calendarIMG2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annulla_action();
            }
        });

        controller.getConferenze();

        for (int i = 0; i < controller.listaAllConferenze.size(); i++){
                conferenzeCB.addItem(controller.listaAllConferenze.get(i).struttura + " " + controller.listaAllConferenze.get(i).dataInizio.toString() + "/" + controller.listaAllConferenze.get(i).dataFine.toString());
        }

        Object comp = conferenzeCB.getUI().getAccessibleChild(conferenzeCB, 0);
        if(comp instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                ImageIcon upArrow = new ImageIcon(this.getClass().getResource("/up.png"));
                Image uA = upArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
                Image dA = downArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                ImageIcon rightArrow = new ImageIcon(this.getClass().getResource("/right.png"));
                Image rA = rightArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                ImageIcon leftArrow = new ImageIcon(this.getClass().getResource("/left.png"));
                Image lA = leftArrow.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0x222831);
                    this.trackColor= new Color(0xFFD369);
                    this.thumbDarkShadowColor = new Color(0xFF1A1E25, true);
                    this.thumbLightShadowColor = new Color(0x323A48);
                    this.thumbHighlightColor = new Color(0x323A48);
                    this.trackHighlightColor = new Color(0xCF9E29);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton decreaseButton = new JButton(new ImageIcon(getAppropriateIcon(orientation))){
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(25, 15);
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
                            return new Dimension(25, 15);
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

        Component editorComp = conferenzeCB.getEditor().getEditorComponent();
        if (editorComp instanceof JTextField) {
            JTextField textField = (JTextField) editorComp;
            textField.setBackground(new Color(0xFFD369));
            textField.setForeground(new Color(0x222831));
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
        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (strutturaField.getText().isBlank() || viaField.getText().isBlank() || ncField.getText().isBlank() || comuneField.getText().isBlank() || capField.getText().isBlank() || nazioneField.getText().isBlank() || dataInizioField.getText().isBlank() || dataFineField.getText().isBlank()){
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obbligatori");
                } else {
                    if(Date.valueOf(dataInizioField.getText()).after(Date.valueOf(dataFineField.getText())) == true || controller.anno_pubblicazione > Date.valueOf(dataInizioField.getText()).getYear()+1900){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "L'evento non può finire prima che inizi");
                    } else {
                        String indirizzo = "";
                        if(strutturaField.isEnabled() == true){
                            if(provinciaField.getText().isBlank() || provinciaField.getText().equals("(Opzionale)")) indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + nazioneField.getText();
                            else indirizzo = viaField.getText() + " " + ncField.getText() + ", " + capField.getText() + ", " + comuneField.getText() + ", " + provinciaField.getText() + ", " + nazioneField.getText();

                            if(controller.addConferenza(strutturaField.getText(), indirizzo, dataInizioField.getText(), dataFineField.getText()) == false) {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Questa struttura è gia occupata!");
                            } else {
                                    reload_table_cb(controller, indirizzo);
                                    annulla_action();
                            }
                        } else {
                            reload_table_cb(controller,controller.listaAllConferenze.get(conferenzeCB.getSelectedIndex()).luogo);
                            annulla_action();
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
        if (controller.addEsposizione(strutturaField.getText(), indirizzo, dataInizioField.getText(), dataFineField.getText()) == false){
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
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);

        conferenzeCB = new JComboBox<>();
        conferenzeCB.setBackground(Color.decode("#FFD369"));
        conferenzeCB.setForeground(Color.decode("#222831"));
        conferenzeCB.setBorder(new LineBorder(Color.decode("#222831")));

        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));
        Image calendarIm = calendarIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
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
