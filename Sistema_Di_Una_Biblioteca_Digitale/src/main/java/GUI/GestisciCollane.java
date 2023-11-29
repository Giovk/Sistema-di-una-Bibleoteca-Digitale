package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;

public class GestisciCollane {
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

    public GestisciCollane(JFrame frameC, Controller controller){
        creaCollanaButton.setFont(controller.baseFontSize);
        creaCollanaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/8.5333),-1));
        annullaButton.setFont(controller.baseFontSize);
        annullaButton.setMinimumSize(new Dimension((int) (controller.screenWidth/8.5333),-1));
        collanaLabel.setFont(controller.baseFontSize);
        nomeLabel.setFont(controller.baseFontSize);
        nomeField.setFont(controller.textFieldFont);
        caratteristicaLabel.setFont(controller.baseFontSize);
        caratteristicaField.setFont(controller.textFieldFont);
        issnLabel.setFont(controller.baseFontSize);
        issnField.setFont(controller.textFieldFont);
        iCampiChePresentanoLabel.setFont(controller.baseFontSize);
        inviaButton.setFont(controller.baseFontSize);


        frame = new JFrame("Biblioteca Digitale");
        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
        frame.setIconImage(icona);
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/3.5555), (int) (controller.screenHeight/1.5));
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

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
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                frameC.toFront();
            }
        });

        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                creaCollanaButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (creaCollanaButton.isEnabled()) creaCollanaButton.setBackground(Color.decode("#FFD369"));
            }
        });

        creaCollanaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                inviaButton.setVisible(true);
                collaneEsistentiPanel.setVisible(false);
                collaneNuovePanel.setVisible(true);
                annullaButton.setVisible(true);
                creaCollanaButton.setEnabled(false);
                contentPane.revalidate();
                contentPane.repaint();
                creaCollanaButton.setBackground(Color.decode("#FFD369"));
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
                inviaButton.setVisible(false);
                collaneEsistentiPanel.setVisible(true);
                collaneNuovePanel.setVisible(false);
                annullaButton.setVisible(false);
                creaCollanaButton.setEnabled(true);
                nomeField.setText("");
                issnField.setText("");
                caratteristicaField.setText("");
                contentPane.revalidate();
                contentPane.repaint();
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
                if(!(controller.verificaISSN(issnField.getText()))) {
                    NewShowMessageDialog dialog = new NewShowMessageDialog(2, "ISSN non valido");   //mostra un messaggio di errore
                }else{
                    if (!caratteristicaField.getText().isBlank()) {
                        if (!nomeField.getText().isBlank()) {
                            if (controller.creaCollana(nomeField.getText().replace("'", "’"), caratteristicaField.getText().replace("'", "’"), issnField.getText()) == true) {
                                inviaButton.setVisible(false);
                                collaneEsistentiPanel.setVisible(true);
                                collaneNuovePanel.setVisible(false);
                                annullaButton.setVisible(false);
                                creaCollanaButton.setEnabled(true);
                                nomeField.setText("");
                                issnField.setText("");
                                caratteristicaField.setText("");

                                collaneEsistentiPanel.removeAll();

                                for (int i = 0; i < controller.listaCollane.size(); i++) {
                                    boolean presenza = false;
                                    for (int j = 0; j < controller.listaCollane.get(i).libri.size(); j++) {
                                        if (controller.listaCollane.get(i).libri.get(j).isbn.equals(controller.isbn_selected)) {
                                            presenza = true;
                                        }
                                    }
                                    collaneLibroCount++;
                                    initCollaneExist(controller.listaCollane.get(i).nome, presenza, controller);
                                }

                                contentPane.revalidate();
                                contentPane.repaint();
                            } else {
                                NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Collana già presente nel Database.");
                            }
                        } else {
                            NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obligatori.");
                        }
                    } else {
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Compilare tutti i campi obligatori.");
                    }
                }
            }
        });

        inviaButton.setVisible(false);
        annullaButton.setVisible(false);
        collaneNuovePanel.setVisible(false);


        // ----------------------------------------------------------------- //

        collaneScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        collaneScrollPane.setBackground(new Color(0x222831));
        collaneScrollPane.setBorder(BorderFactory.createEmptyBorder());
        collaneScrollPane.getViewport().setBackground(new Color(0x222831));

        for (int i = 0; i < controller.listaCollane.size(); i++){
            boolean presenza = false;
            for (int j = 0; j < controller.listaCollane.get(i).libri.size(); j++){
                if (controller.listaCollane.get(i).libri.get(j).isbn.equals(controller.isbn_selected)){
                    presenza = true;
                }
            }
            collaneLibroCount++;
            initCollaneExist(controller.listaCollane.get(i).nome, presenza, controller);
        }

    }

    private void initCollaneExist(String nomeCollana, boolean presenza, Controller controller) {
        JPanel panel1 = new JPanel();
        JLabel label2 = new JLabel();
        JButton button2 = new JButton();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x222831));
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0};
            ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
            ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- label2 ----
            label2.setText(nomeCollana);
            label2.setFont(controller.baseFontSize);
            label2.setMinimumSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));
            label2.setPreferredSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));
            label2.setMaximumSize(new Dimension((int) (controller.screenWidth/8.53), controller.screenHeight/45));
            label2.setForeground(new Color(0xeeeeee));
            panel1.add(label2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 5), 0, 0));

            //---- button2 ----
            if(presenza == true){
                button2.setText("-");
            } else button2.setText("+");
            button2.setMinimumSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));
            button2.setPreferredSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));
            button2.setMaximumSize(new Dimension((int) (controller.screenWidth/28.4444), (int) (controller.screenHeight/28.8)));
            button2.setFont(controller.baseFontSize);
            button2.setBackground(new Color(0xffd369));
            button2.setForeground(new Color(0x222831));
            button2.setBorderPainted(false);

            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    button2.setBackground(Color.decode("#cf9e29"));
                }
            });
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    button2.setBackground(Color.decode("#FFD369"));
                }
            });

            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button2.getText().equals("-")){
                        controller.removeLibroFromCollana(nomeCollana);
                        button2.setText("+");
                    } else {
                        controller.addLibroInCollana(nomeCollana);
                        button2.setText("-");
                    }
                }
            });

            panel1.add(button2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));        }


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Colonna 1
        constraints.gridy = (collaneLibroCount-1); // Riga 0
        constraints.insets = new Insets(0, 0, 15, 0);
        collaneEsistentiPanel.add(panel1, constraints);
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

        nomeField = new JTextField();
        nomeField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        issnField = new JTextField();
        issnField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        caratteristicaField = new JTextField();
        caratteristicaField.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));

        collaneEsistentiPanel = new JPanel();
        collaneEsistentiPanel.setLayout(new GridBagLayout());
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)collaneEsistentiPanel.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};
    }
}
