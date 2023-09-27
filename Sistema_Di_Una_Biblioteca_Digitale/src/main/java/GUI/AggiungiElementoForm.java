package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
    private JPanel vSpacerForm;
    private DatePicker datePickerLibro;
    private int autoreLibroCount = 0;
    private int isbnLibroCount = 0;
    private DatePicker datePickerSerie;
    private int libriISBNCount = 0;
    ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));
    Image calendarImg = calendarIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    private String isbnOld;
    private ArrayList<String> isbnLibri;

    public AggiungiElementoForm(JFrame frameC, Controller controller, DefaultTableModel model){
        frame = new JFrame("Valutazione");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 620);
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
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue());
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

        Object comp1 = linguaLibroCB.getUI().getAccessibleChild(linguaLibroCB, 0);
        if(comp1 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp1;
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

        Object comp2 = isbnLibroCB.getUI().getAccessibleChild(isbnLibroCB, 0);
        if(comp2 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp2;
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
                initComponentsAutoreLibro();
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
                if (titoloLibroField.isEnabled() == true) {
                    if (controller.creaLibro(isbnLibroCB.getSelectedItem().toString(), titoloLibroField.getText(), genereLibroField.getText(), linguaLibroCB.getSelectedItem().toString(), editoreLibroField.getText(), dataLibroField.getText()) == true) {
                        takeAutori(controller);
                        controller.listaLibri.add(controller.nuovoLibro);
                    }
                } else {
                    controller.nuovoLibro = controller.listaLibri.get(isbnLibroCB.getSelectedIndex());
                }


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
        });

        // SERIE //
        isbnLibri = new ArrayList<>();

        for (String isbnLibro: controller.titoloLibriLibreria){
            if(!isbnLibri.contains(isbnLibro.substring(0, 17))) isbnLibri.add(isbnLibro.substring(0, 17));
        }
        serieScrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
                initComponentsISBNSerie(isbnLibri, (int) spinnerLibriSerie.getValue());
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
                                if(controller.creaSerie(libriISBN, isbnSerieField.getText().toString(), titoloSerieField.getText().toString(), dataSerieField.getText().toString()) == false){
                                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Serie già presente nel Database");
                                } else {
                                    frame.setVisible(false);
                                    frameC.setEnabled(true);
                                    frame.dispose();
                                    frameC.toFront();
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
    }

    private void takeAutori(Controller controller) {
        int fieldCount = 0;
        String nome = "";
        String cognome = "";
        String nazionalità = "";
        String dn = "";
        Component[] components = autoriLibroPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                System.out.println("sono nel pannello");
                JPanel jPanel1 = (JPanel) component;
                Component[] components2 = jPanel1.getComponents();

                for (Component component2: components2){
                    if(component2 instanceof JTextField){
                        System.out.println("sono nel TextField");
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
                                controller.aggiungiAutore(nome, cognome, nazionalità, dn);
                                break;
                            default:
                                fieldCount = 0;
                                break;
                        }
                    }
                }
            }
        }
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

    private void initComponentsISBNSerie(ArrayList<String> isbn, int ricorsive) {
        if(ricorsive == 0) return;
        isbnLibroCount++;
        DefaultComboBoxModel model;
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JComboBox comboBox1 = new JComboBox();
        String oldISBN = "";

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
            label2.setForeground(new Color(0xeeeeee));
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

            //---- comboBox1 ----
            model = new DefaultComboBoxModel(isbn.toArray(new String[isbn.size()]));
            comboBox1.setModel(model);
            comboBox1.setMinimumSize(new Dimension(150, 26));
            comboBox1.setPreferredSize(new Dimension(150, 26));
            comboBox1.setBackground(Color.decode("#FFD369"));
            comboBox1.setForeground(Color.decode("#222831"));
            Object comp = comboBox1.getUI().getAccessibleChild(comboBox1, 0);
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
        initComponentsISBNSerie(isbn, ricorsive-1);
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
                                System.out.println(comboBox1.getName() + " - " + comboBoxPanel.getName());
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

        private void initComponentsAutoreLibro() {
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
        label2.setForeground(new Color(0xeeeeee));
        panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 5), 0, 0));

        //---- label3 ----
        label3.setText("Nome:");
        label3.setForeground(new Color(0xeeeeee));
        panel1.add(label3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField3 ----
        textField3.setBackground(new Color(0xffd369));
        textField3.setBorder(new LineBorder(new Color(0xffd369)));
        textField3.setCaretColor(new Color(0x222831));
        textField3.setMinimumSize(new Dimension(150, 18));
        textField3.setPreferredSize(new Dimension(150, 18));
        panel1.add(textField3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label4 ----
        label4.setText("Cognome:");
        label4.setForeground(new Color(0xeeeeee));
        panel1.add(label4, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField4 ----
        textField4.setBackground(new Color(0xffd369));
        textField4.setCaretColor(new Color(0x222831));
        textField4.setBorder(new LineBorder(new Color(0xffd369)));
        textField4.setMinimumSize(new Dimension(150, 18));
        textField4.setPreferredSize(new Dimension(150, 18));
        panel1.add(textField4, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label5 ----
        label5.setText("Nazionalità:");
        label5.setForeground(new Color(0xeeeeee));
        panel1.add(label5, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- textField5 ----
        textField5.setBackground(new Color(0xffd369));
        textField5.setBorder(new LineBorder(new Color(0xffd369)));
        textField5.setCaretColor(new Color(0x222831));
        textField5.setMinimumSize(new Dimension(150, 18));
        textField5.setPreferredSize(new Dimension(150, 18));
        panel1.add(textField5, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));

        //---- label6 ----
        label6.setText("Data di Nascita:");
        label6.setForeground(new Color(0xeeeeee));
        panel1.add(label6, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

        //---- label7 ----
        label7.setIcon(new ImageIcon(calendarImg));

        datePickerLibro = new DatePicker(label7);
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
        textField6.setMinimumSize(new Dimension(150, 18));
        panel1.add(textField6, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }
        autoriLibroPanel.add(panel1);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);

        ImageIcon calendarIco = new ImageIcon(this.getClass().getResource("/Calendar2.png"));
        Image calendarImg = calendarIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        calendarIco = new ImageIcon(calendarImg);


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
        autoriLibroPanel.setLayout(new GridLayout(-1, 2));
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
            Image uA = upArrow.getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(15, 15);
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
                        return new Dimension(15, 15);
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
            Image uA = upArrow.getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH);
            ImageIcon downArrow = new ImageIcon(this.getClass().getResource("/down.png"));
            Image dA = downArrow.getImage().getScaledInstance(5, 5, Image.SCALE_SMOOTH);

            @Override
            protected Component createPreviousButton() {
                JButton button = new JButton(new ImageIcon(dA)){
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(15, 15);
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
                        return new Dimension(15, 15);
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
    }
}
