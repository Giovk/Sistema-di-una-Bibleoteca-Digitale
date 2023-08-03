package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class RecensioneLibro extends JDialog {
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
    private JButton buttonOK;
    private JButton buttonCancel;
    private boolean active = false;
    public ImageIcon stellaPienaIco;
    public ImageIcon stellaVuotaIco;
    public ImageIcon stellaMezzaIco;
    public int valutazione = 0;
    public float valutazioneMedia;

    public String isbn_selezionato;

    public RecensioneLibro(JFrame frameC, Controller controller, JLabel valutazioneC, JLabel s1, JLabel s2, JLabel s3, JLabel s4, JLabel s5, JPanel commenti) {
       isbn_selezionato = controller.isbn_selected;

        frame = new JFrame("Valutazione");
        frame.setUndecorated(true);
        frame.setContentPane(this.contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 240);
        frame.setLocationRelativeTo(null);
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));
        frame.setResizable(false);
        frame.setVisible(true);

        stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaVuotaIco = new ImageIcon(stellaVuotaImg);

        stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaPienaIco = new ImageIcon(stellaPienaImg);

        stellaMezzaIco = new ImageIcon(this.getClass().getResource("/stella_mezza.png"));
        Image stellaMezzaImg = stellaMezzaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
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


        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

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

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(valutazione == 0){
                    JOptionPane.showMessageDialog(frame, "Inserire la valutazione.");
                } else {
                    controller.addRecensioneLibro(valutazione, editorPane1.getText());
                    DecimalFormat valMedForm = new DecimalFormat("#.#");
                    valutazioneMedia = controller.valutazioneMediaLibro();
                    valutazioneC.setText(valMedForm.format(valutazioneMedia));
                    changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);

                    frameC.addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {

                        }

                        @Override
                        public void windowClosing(WindowEvent e) {

                        }

                        @Override
                        public void windowClosed(WindowEvent e) {

                        }

                        @Override
                        public void windowIconified(WindowEvent e) {

                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {

                        }

                        @Override
                        public void windowActivated(WindowEvent e) {
                            commenti.removeAll();
                            showComment(new Controller(), commenti);
                            System.out.println("prova");

                            frameC.removeWindowListener(this);
                            commenti.revalidate();
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {

                        }
                    });


                    frame.setVisible(false);
                    frameC.setEnabled(true);
                    frame.dispose();
                    frameC.toFront();
                }
            }
        });
    }

    public void changeStars(JLabel stella1, JLabel stella2, JLabel stella3, JLabel stella4, JLabel stella5, int valutazione){   //aggiorna le stelle della recensione con valutazione 'valutazione'

        ImageIcon stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaVuotaIco = new ImageIcon(stellaVuotaImg);

        ImageIcon stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
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


    public void showComment(Controller controller, JPanel commenti){    //mostra i commenti del libro selezionato
        controller.isbn_selected = isbn_selezionato;    //isbn del ibro selezionato

        controller.allRecWithCommentLibro(); //inizializza 'controller.recensioniConCommento'

        int n = controller.recensioniConCommento.size();    //numero di recensioni con commento del libro selezionato

        JPanel[] valUser = new JPanel[n];
        JPanel[] commento = new JPanel[n];
        JLabel[] username = new JLabel[n];
        JLabel[] valutazione = new JLabel[n*5];
        JLabel[] commText = new JLabel[n];
        JSeparator[] separators = new JSeparator[n];

        for (int i = 0; i < n; i++){
            username[i] = new JLabel(controller.recensioniConCommento.get(i).utenteRecensore.username);
            username[i].setAlignmentX(Component.LEFT_ALIGNMENT);    //imposta l'allineamento orizzontale a sinistra della Jlabel con l'username dell'autore dell'i-esima recensione
            username[i].setForeground(new Color(0xEEEEEE));
            valutazione[i*5] = new JLabel();
            valutazione[i*5].setAlignmentX(Component.LEFT_ALIGNMENT);   //imposta l'allineamento orizzontale della Jlabel con la prima stella dell'i-esima recensione a sinistra
            valutazione[(i*5)+1] = new JLabel();
            valutazione[(i*5)+1].setAlignmentX(Component.LEFT_ALIGNMENT);   //imposta l'allineamento orizzontale della Jlabel con la seconda stella dell'i-esima recensione a sinistra
            valutazione[(i*5)+2] = new JLabel();
            valutazione[(i*5)+2].setAlignmentX(Component.LEFT_ALIGNMENT);   //imposta l'allineamento orizzontale della Jlabel con la terza stella dell'i-esima recensione a sinistra
            valutazione[(i*5)+3] = new JLabel();
            valutazione[(i*5)+3].setAlignmentX(Component.LEFT_ALIGNMENT);   //imposta l'allineamento orizzontale della Jlabel con la quarta stella dell'i-esima recensione a sinistra
            valutazione[(i*5)+4] = new JLabel();
            valutazione[(i*5)+4].setAlignmentX(Component.LEFT_ALIGNMENT);   //imposta l'allineamento orizzontale della Jlabel con la quinta stella dell'i-esima recensione a sinistra

            changeStars(valutazione[i*5], valutazione[(i*5)+1], valutazione[(i*5)+2], valutazione[(i*5)+3], valutazione[(i*5)+4], controller.recensioniConCommento.get(i).valutazione); //aggiorna le cinque stelle dell'i-esima recensione del libro selezionato in base alla sua valutazione

            valUser[i] = new JPanel();
            valUser[i].setBackground(new Color(0x222831));
            valUser[i].add(username[i]);    //aggiunge l'username dell'utente che ha fatto l'i-esima recensione in 'valUser'
            valUser[i].add(valutazione[i*5]);   //aggiunge la prima stella della i-esima recensione in 'valUser'
            valUser[i].add(valutazione[(i*5)+1]);   //aggiunge la seconda stella della i-esima recensione in 'valUser'
            valUser[i].add(valutazione[(i*5)+2]);   //aggiunge la terza stella della i-esima recensione in 'valUser'
            valUser[i].add(valutazione[(i*5)+3]);   //aggiunge la quarta stella della i-esima recensione in 'valUser'
            valUser[i].add(valutazione[(i*5)+4]);   //aggiunge la quinta stella della i-esima recensione in 'valUser'
            valUser[i].setAlignmentX(Component.LEFT_ALIGNMENT); //imposta l'allineamento orizzontale del Jpannel 'valUser' a sinistra
            commText[i] = new JLabel(controller.recensioniConCommento.get(i).testo);
            commText[i].setForeground(new Color(0xEEEEEE));
            commText[i].setAlignmentX(Component.LEFT_ALIGNMENT);    //imposta l'allineamento orizzontale della Jlabel con il testo del commento dell'i-esima recensione a sinistra
            commento[i] = new JPanel();
            commento[i].setLayout(new FlowLayout(FlowLayout.LEFT)); //imposta il layout del testo del commento dell'i-esima recensione, posizionandolo a sinistra
            commento[i].setBackground(new Color(0x222831));
            commento[i].add(commText[i]);   //aggiunge il testo del commento dell'i-esima recensione in 'commento'
            commento[i].setAlignmentX(Component.LEFT_ALIGNMENT);    //imposta l'allineamento orizzontale della Jpannel con il testo del commento dell'i-esima recensione a sinistra
            separators[i] = new JSeparator();
            separators[i].setForeground(new Color(0xEEEEEE));
            commenti.add(valUser[i]);   //aggiunge in 'commenti' l'username dell'autore e le stelle dell'i-esima recensione
            commenti.add(commento[i]);  // aggiunge in 'commenti' il testo dell'i-esima recensione
            commenti.add(separators[i]);    //aggiunge un 'Jseparator'
        }
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
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);

        editorPane1 = new JEditorPane();
        editorPane1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369)));
    }
}
