package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Recensione extends JDialog {
    public JFrame frame;
    private JPanel contentPane;
    private JLabel stella1;
    private JLabel closeBT;
    private JLabel stella2;
    private JLabel stella3;
    private JLabel stella4;
    private JLabel stella5;
    private JTextField textField1;
    private JButton button1;
    private JEditorPane editorPane1;
    private JLabel valutazioneLabel;
    private JButton buttonOK;
    private JButton buttonCancel;
    private boolean active = false;
    public ImageIcon stellaPienaIco;
    public ImageIcon stellaVuotaIco;
    public ImageIcon stellaMezzaIco;
    public int valutazione = 0;
    public float valutazioneMedia;

    public String isbn_selezionato;
    public String titolo;
    int numero;

    public Recensione(JFrame frameC, Controller controller, JLabel valutazioneC, JLabel s1, JLabel s2, JLabel s3, JLabel s4, JLabel s5, JPanel commenti,int elemento) {
       if(controller.isbn_selected != null) isbn_selezionato = controller.isbn_selected;
       if(controller.fascicolo_selected != null){
           titolo = controller.fascicolo_selected.rivista.titolo;
           numero = controller.fascicolo_selected.numero;
       }

       valutazioneLabel.setFont(controller.baseFontSize);
       editorPane1.setFont(controller.baseFontSize);
       editorPane1.setPreferredSize(new Dimension((int) (controller.screenWidth/8.5333), (int) (controller.screenHeight/7.2)));
       button1.setFont(controller.baseFontSize);

        frame = new JFrame("Valutazione");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize((int) (controller.screenWidth/1.7777), controller.screenHeight/3);
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

        stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
        stellaVuotaIco = new ImageIcon(stellaVuotaImg);

        stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
        stellaPienaIco = new ImageIcon(stellaPienaImg);

        stellaMezzaIco = new ImageIcon(this.getClass().getResource("/stella_mezza.png"));
        Image stellaMezzaImg = stellaMezzaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);
        stellaMezzaIco = new ImageIcon(stellaMezzaImg);

        stella1.setIcon(stellaVuotaIco);
        stella2.setIcon(stellaVuotaIco);
        stella3.setIcon(stellaVuotaIco);
        stella4.setIcon(stellaVuotaIco);
        stella5.setIcon(stellaVuotaIco);


            stella1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (active == false) {
                        stella1.setIcon(stellaPienaIco);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (active == false) {
                        stella1.setIcon(stellaVuotaIco);
                    }
                }
            });

            stella2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if(active == false) {
                        stella1.setIcon(stellaPienaIco);
                        stella2.setIcon(stellaPienaIco);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (active == false) {
                        stella1.setIcon(stellaVuotaIco);
                        stella2.setIcon(stellaVuotaIco);
                    }
                }
            });

            stella3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (active == false) {
                        stella1.setIcon(stellaPienaIco);
                        stella2.setIcon(stellaPienaIco);
                        stella3.setIcon(stellaPienaIco);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if (active == false) {
                        stella1.setIcon(stellaVuotaIco);
                        stella2.setIcon(stellaVuotaIco);
                        stella3.setIcon(stellaVuotaIco);
                    }
                }
            });

            stella4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered (MouseEvent e) {
                        super.mouseEntered(e);
                        if (active == false) {
                            stella1.setIcon(stellaPienaIco);
                            stella2.setIcon(stellaPienaIco);
                            stella3.setIcon(stellaPienaIco);
                            stella4.setIcon(stellaPienaIco);
                        }
                    }

                    @Override
                    public void mouseExited (MouseEvent e){
                    super.mouseExited(e);
                    if(active == false) {
                        stella1.setIcon(stellaVuotaIco);
                        stella2.setIcon(stellaVuotaIco);
                        stella3.setIcon(stellaVuotaIco);
                        stella4.setIcon(stellaVuotaIco);
                    }
                 }
            });

            stella5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if(active == false){
                    stella1.setIcon(stellaPienaIco);
                    stella2.setIcon(stellaPienaIco);
                    stella3.setIcon(stellaPienaIco);
                    stella4.setIcon(stellaPienaIco);
                    stella5.setIcon(stellaPienaIco);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    if(active == false) {
                        stella1.setIcon(stellaVuotaIco);
                        stella2.setIcon(stellaVuotaIco);
                        stella3.setIcon(stellaVuotaIco);
                        stella4.setIcon(stellaVuotaIco);
                        stella5.setIcon(stellaVuotaIco);
                    }
                }
            });


        stella1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 1;
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaVuotaIco);
                stella3.setIcon(stellaVuotaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                active = true;
            }
        });

        stella2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 2;
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaVuotaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                active = true;
            }
        });

        stella3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 3;
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaVuotaIco);
                stella5.setIcon(stellaVuotaIco);
                active = true;
            }
        });

        stella4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 4;
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaPienaIco);
                stella5.setIcon(stellaVuotaIco);
                active = true;
            }
        });

        stella5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 5;
                stella1.setIcon(stellaPienaIco);
                stella2.setIcon(stellaPienaIco);
                stella3.setIcon(stellaPienaIco);
                stella4.setIcon(stellaPienaIco);
                stella5.setIcon(stellaPienaIco);
                active = true;
            }
        });


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

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (elemento == 1){
                    if(valutazione == 0){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");
                    } else {
                        controller.addRecensioneLibro(valutazione, editorPane1.getText());
                        DecimalFormat valMedForm = new DecimalFormat("#.#");
                        valutazioneMedia = controller.valutazioneMediaLibro();
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();
                                showComment(new Controller(), commenti, 1);

                                frameC.removeWindowListener(this);
                                commenti.revalidate();
                            }
                        });


                        frame.setVisible(false);
                        frameC.setEnabled(true);
                        frame.dispose();
                        frameC.toFront();
                    }
                }

                if (elemento == 2){
                    if(valutazione == 0){
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");
                    } else {
                        controller.addRecensioneSerie(valutazione, editorPane1.getText());
                        DecimalFormat valMedForm = new DecimalFormat("#.#");
                        valutazioneMedia = controller.valutazioneMediaSerie();
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();
                                showComment(new Controller(), commenti, 2);

                                frameC.removeWindowListener(this);
                                commenti.revalidate();
                            }
                        });

                        frame.setVisible(false);
                        frameC.setEnabled(true);
                        frame.dispose();
                        frameC.toFront();
                    }
                }

                if (elemento == 3) {
                    if (valutazione == 0) {
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");
                    } else {
                        controller.addRecensioneFascicolo(valutazione, editorPane1.getText());
                        DecimalFormat valMedForm = new DecimalFormat("#.#");
                        valutazioneMedia = controller.valutazioneMediaFascicolo();
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();
                                showComment(new Controller(), commenti, 3);

                                frameC.removeWindowListener(this);
                                commenti.revalidate();
                            }
                        });


                        frame.setVisible(false);
                        frameC.setEnabled(true);
                        frame.dispose();
                        frameC.toFront();
                    }
                }
            }
        });
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


    public void showComment(Controller controller, JPanel commenti, int elemento){
        if (elemento == 1) {
            controller.isbn_selected = isbn_selezionato;    //isbn del ibro selezionato
            controller.allRecWithCommentLibro();
        }

        if (elemento == 3) {
            controller.selezionaFascicolo(numero,titolo);    //isbn del ibro selezionato
            controller.allRecWithCommentFascicolo();
        }

        if (elemento == 2) {
            controller.isbn_selected = isbn_selezionato;    //isbn del ibro selezionato
            controller.allRecWithCommentSerie();
        }

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

    public void changeStars(JLabel s1, JLabel s2, JLabel s3, JLabel s4, JLabel s5, ImageIcon stellaPienaIco, ImageIcon stellaVuotaIco, ImageIcon stellaMezzaIco){
        if(valutazioneMedia <= 0.25){   //controlla se la media è minore uguale a 0,25
            s1.setIcon(stellaVuotaIco);
            s2.setIcon(stellaVuotaIco);
            s3.setIcon(stellaVuotaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 0.75){    //controlla se la media è in [0.25,0.75[
            s1.setIcon(stellaMezzaIco);
            s2.setIcon(stellaVuotaIco);
            s3.setIcon(stellaVuotaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.25){    //controlla se la media è in [0.75,1.25[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaVuotaIco);
            s3.setIcon(stellaVuotaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.75){    //controlla se la media è in [1.25,1.75[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaMezzaIco);
            s3.setIcon(stellaVuotaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.25){    //controlla se la media è in [1.75,2.25[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaVuotaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.75){    //controlla se la media è in [2.25,2.75[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaMezzaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.25){    //controlla se la media è in [2.75,3.25[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaPienaIco);
            s4.setIcon(stellaVuotaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.75){    //controlla se la media è in [3.25,3.75[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaPienaIco);
            s4.setIcon(stellaMezzaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.25){    //controlla se la media è in [3.75,4.25[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaPienaIco);
            s4.setIcon(stellaPienaIco);
            s5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.75){    //controlla se la media è in [4.25,4.75[
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaPienaIco);
            s4.setIcon(stellaPienaIco);
            s5.setIcon(stellaMezzaIco);
        } else {
            s1.setIcon(stellaPienaIco);
            s2.setIcon(stellaPienaIco);
            s3.setIcon(stellaPienaIco);
            s4.setIcon(stellaPienaIco);
            s5.setIcon(stellaPienaIco);
        }
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

        editorPane1 = new JEditorPane();
        editorPane1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
    }
}
