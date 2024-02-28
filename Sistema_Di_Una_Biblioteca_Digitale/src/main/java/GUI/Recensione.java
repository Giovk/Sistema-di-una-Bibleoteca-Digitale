package GUI;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * La classe Recensione implemeta l'interfaccia grafica del form che permette di inserire una nuova recensione all'elemento selezionato.
 */
public class Recensione extends JDialog {
    /**
     * Frame che si sta visualizzando.
     */
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
    /**
     * icona della stella piena
     */
    public ImageIcon stellaPienaIco;
    /**
     * icona della stella vuota
     */
    public ImageIcon stellaVuotaIco;
    /**
     * icona della stella mezza
     */
    public ImageIcon stellaMezzaIco;
    /**
     * valore della valutazione fatta all'elemento selezionato.
     */
    public int valutazione = 0;
    /**
     * media delle valutazioni dell'elemento selelzionato.
     */
    public float valutazioneMedia;
    /**
     * ISBN dell'elemento selezionato.
     */
    public String isbn_selezionato;
    /**
     * Titolo dell'elemento selezionato.
     */
    public String titolo;
    /**
     * Numero del fascicolo selezionato.
     */
    int numero;

    /**
     * Istanzia un nuovo Recensione.
     *
     * @param frameC       il frame chiamante
     * @param controller   il controller
     * @param valutazioneC contiene la valutazione media dell'elemento selezionato
     * @param s1           la prima stella
     * @param s2           la seconda stella
     * @param s3           la terza stella
     * @param s4           la quarta stella
     * @param s5           la quinta stella
     * @param commenti     contiene tutti i commenti dell'elemento selezionato
     * @param elemento     tipo di elemento selezionato
     */
    public Recensione(JFrame frameC, Controller controller, JLabel valutazioneC, JLabel s1, JLabel s2, JLabel s3, JLabel s4, JLabel s5, JPanel commenti,int elemento) {
        if(controller.isbn_selected != null){   //controlla se è stato selezionato un libro
            isbn_selezionato = controller.isbn_selected;    //inizializza 'isbn_selezionato' con l'ISBN del libro selezionato
        }

        if(controller.fascicolo_selected != null){  //controlla se è stato selezionato un fascicolo
            titolo = controller.fascicolo_selected.getRivista().getTitolo();  //inizializza 'titolo' con il titolo della rivista del fascicolo selezionato
            numero = controller.fascicolo_selected.getNumero();  //inizializza 'numero' con il numero del fascicolo selezionato
        }

        valutazioneLabel.setFont(controller.baseFontSize);  //imposta il font della JLabel 'valutazioneLabel'
        editorPane1.setFont(controller.baseFontSize);   //imposta il font del JEditorPane 'editorPane1'
        editorPane1.setPreferredSize(new Dimension((int) (controller.screenWidth/8.5333), (int) (controller.screenHeight/7.2)));    //imposta la dimensione preferita del JEditorPane 'editorPane1'
        button1.setFont(controller.baseFontSize);   //imposta il font del JButton 'button1'

        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //rimuove la decorazione del frame
        frame.setContentPane(this.contentPane);

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        frame.setIconImage(icona);  //imposta la l'icona dell'applicazione
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //imposta la terminazione dell'applicazione come operazione predefinita da eseguire quando viene chiuso il frame
        frame.pack();
        frame.setSize((int) (controller.screenWidth/1.7777), controller.screenHeight/3);    //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true); //rende visibile il frame

        stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));   //carica l'immagine nel percorso /stella_vuota.png
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaVuotaIco = new ImageIcon(stellaVuotaImg); //reinizializza l'ImageIcon 'stellaVuotaIco' con l'Image 'stellaVuotaImg'

        stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));   //carica l'immagine nel percorso /stella_piena.png
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaPienaIco = new ImageIcon(stellaPienaImg); //reinizializza l'ImageIcon 'stellaPienaIco' con l'Image 'stellaPienaImg'

        stellaMezzaIco = new ImageIcon(this.getClass().getResource("/stella_mezza.png"));   //carica l'immagine nel percorso /stella_mezza.png
        Image stellaMezzaImg = stellaMezzaIco.getImage().getScaledInstance((int) (controller.screenWidth/51.2), (int) (controller.screenHeight/28.8), Image.SCALE_SMOOTH);  //imposta le dimensioni dell'immagine
        stellaMezzaIco = new ImageIcon(stellaMezzaImg); //reinizializza l'ImageIcon 'stellaMezzaIco' con l'Image 'stellaMezzaImg'

        stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
        stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
        stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
        stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
        stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'

        stella1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella1'
                    stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella1'
                    stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
                }
            }
        });

        stella2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if(active == false) {   //controlla se è stato disattivato la JLabel 'stella2'
                    stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                    stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella2'
                    stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
                    stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                }
            }
        });

        stella3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella3'
                    stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                    stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                    stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella3'
                    stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
                    stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                    stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                }
            }
        });

        stella4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e) {
                super.mouseEntered(e);

                if (active == false) {  //controlla se è stato disattivato la JLabel 'stella4'
                    stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                    stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                    stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                    stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                }
            }

            @Override
            public void mouseExited (MouseEvent e){
                super.mouseExited(e);

                if(active == false) {   //controlla se è stato disattivato la JLabel 'stella4'
                    stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
                    stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                    stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                    stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                }
            }
        });

        stella5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if(active == false){    //controlla se è stato disattivato la JLabel 'stella5'
                    stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                    stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                    stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                    stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                    stella5.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaPienaIco'
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if(active == false) {   //controlla se è stato disattivato la JLabel 'stella5'
                    stella1.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaVuotaIco'
                    stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                    stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                    stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                    stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                }
            }
        });

        stella1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 1;    //inizializza 'valutazione' a 1
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaVuotaIco'
                stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                active = true;  //segnala che è stata attivata la JLabel 'stella1'
            }
        });

        stella2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 2;    //inizializza 'valutazione' a 2
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaVuotaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                active = true;  //segnala che è stata attivata la JLabel 'stella2'
            }
        });

        stella3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 3;    //inizializza 'valutazione' a 3
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaVuotaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                active = true;  //segnala che è stata attivata la JLabel 'stella3'
            }
        });

        stella4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 4;    //inizializza 'valutazione' a 4
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                stella5.setIcon(stellaVuotaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaVuotaIco'
                active = true;  //segnala che è stata attivata la JLabel 'stella4'
            }
        });

        stella5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                valutazione = 5;    //inizializza 'valutazione' a 4
                stella1.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella1' con l'immagine 'stellaPienaIco'
                stella2.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella2' con l'immagine 'stellaPienaIco'
                stella3.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella3' con l'immagine 'stellaPienaIco'
                stella4.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella4' con l'immagine 'stellaPienaIco'
                stella5.setIcon(stellaPienaIco);    //imposta l'icona della JLabel 'stella5' con l'immagine 'stellaPienaIco'
                active = true;  //segnala che è stata attivata la JLabel 'stella5'
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
                frame.setVisible(false);    //rende invisibile il frame
                frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                frame.dispose();
                frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (elemento == 1){ //controlla se l'elemento recensito è un libro
                    if(valutazione == 0){   //controlla se non è stata inserita la valutazione dell'elemento
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");   //mostra un messaggio di errore
                    } else {
                        controller.addRecensioneLibro(valutazione, editorPane1.getText());  //aggiunge la nuova recensione

                        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato con una sola cifra decimale

                        valutazioneMedia = controller.valutazioneMediaLibro();  //media delle valutazioni del libro selezionato
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));  //imposta il testo della JLabel 'valutazioneC' con la media delle valutazioni del libro selezionato
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);    //aggiorna le stelle della valutazione media

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();   //rimuove tutti i componenti del JPanel 'commenti'
                                showComment(new Controller(), commenti, 1); //mostra tutti i commenti fatti al libro selezionato

                                frameC.removeWindowListener(this);  //rimuove il listener
                                commenti.revalidate();  //aggiorna il contenuto del JPanel 'commenti'
                            }
                        });
                        
                        frame.setVisible(false);    //rende invisibile il frame
                        frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                        frame.dispose();
                        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                    }
                }

                if (elemento == 2){ //controlla se l'elemento recensito è una serie
                    if(valutazione == 0){   //controlla se non è stata inserita la valutazione dell'elemento
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");   //mostra un messaggio di errore
                    } else {
                        controller.addRecensioneSerie(valutazione, editorPane1.getText());  //aggiunge la nuova recensione

                        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato con una sola cifra decimale

                        valutazioneMedia = controller.valutazioneMediaSerie();  //media delle valutazioni della serie selezionata
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));  //imposta il testo della JLabel 'valutazioneC' con la media delle valutazioni del libro selezionato
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);    //aggiorna le stelle della valutazione media

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();   //rimuove tutti i componenti del JPanel 'commenti'
                                showComment(new Controller(), commenti, 2); //mostra tutti i commenti fatti al libro selezionato

                                frameC.removeWindowListener(this);  //rimuove il listener
                                commenti.revalidate();  //aggiorna il contenuto del JPanel 'commenti'
                            }
                        });

                        frame.setVisible(false);    //rende invisibile il frame
                        frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                        frame.dispose();
                        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                    }
                }

                if (elemento == 3) {    //controlla se l'elemento recensito è un fascicolo
                    if (valutazione == 0) { //controlla se non è stata inserita la valutazione dell'elemento
                        NewShowMessageDialog dialog = new NewShowMessageDialog(2, "Inserire la valutazione");   //mostra un messaggio di errore
                    } else {
                        controller.addRecensioneFascicolo(valutazione, editorPane1.getText());  //aggiunge la nuova recensione

                        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato con una sola cifra decimale

                        valutazioneMedia = controller.valutazioneMediaFascicolo();  //media delle valutazioni della serie selezionata
                        valutazioneC.setText(valMedForm.format(valutazioneMedia));  //imposta il testo della JLabel 'valutazioneC' con la media delle valutazioni del libro selezionato
                        changeStars(s1, s2, s3, s4, s5, stellaPienaIco, stellaVuotaIco, stellaMezzaIco);    //aggiorna le stelle della valutazione media

                        frameC.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowActivated(WindowEvent e) {
                                super.windowActivated(e);
                                commenti.removeAll();       //rimuove tutti i componenti del JPanel 'commenti'
                                showComment(new Controller(), commenti, 3); //mostra tutti i commenti fatti al libro selezionato

                                frameC.removeWindowListener(this);  //rimuove il listener
                                commenti.revalidate();  //aggiorna il contenuto del JPanel 'commenti'
                            }
                        });

                        frame.setVisible(false);    //rende invisibile il frame
                        frameC.setEnabled(true);    //abilita il frame chiamante 'frameC'
                        frame.dispose();
                        frameC.toFront();   //porta il frame chiamante 'frameC' in primo piano
                    }
                }
            }
        });
    }

    /**
     * Cambia le icone delle stelle mostrate in base alla valutazione fatta dall'utente al libro selezionato.
     *
     * @param stella1     la prima stella
     * @param stella2     la seconda stella
     * @param stella3     la terza stella
     * @param stella4     la quarta stella
     * @param stella5     la quinta stella
     * @param valutazione la valutazione fatta dall'utente al libro selezionato
     * @param controller  il controller
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
     * Mostra i commenti fatti all'elemento selezionato.
     *
     * @param controller il controller
     * @param commenti   contiene i commenti del libro selezionato
     */
    public void showComment(Controller controller, JPanel commenti, int elemento){  //mostra tutti i commenti fatti all'elemento selezionato
        if (elemento == 1) {    //controlla se è stato selezionato un libro
            controller.isbn_selected = isbn_selezionato;    //inizializza 'controller.isbn_selected' con 'isbn_selezionato'
            controller.allRecWithCommentLibro();    //inizializza 'controller.recensioniConCommento'
        }

        if (elemento == 3) {    //controlla se è stato selezionato un fascicolo
            controller.selezionaFascicolo(numero,titolo);   //inizializza 'controller.fascicolo_selected'
            controller.allRecWithCommentFascicolo();    //inizializza 'controller.recensioniConCommento'
        }

        if (elemento == 2) {    //controlla se è stata selezionata una serie
            controller.isbn_selected = isbn_selezionato;    //inizializza 'controller.isbn_selected' con 'isbn_selezionato'
            controller.allRecWithCommentSerie();    //inizializza 'controller.recensioniConCommento'
        }

        for (int i = 0; i < controller.recensioniConCommento.size(); i++){  //scorre 'controller.recensioniConCommento'
            addComment(controller.recensioniConCommento.get(i).getUtenteRecensore().getUsername(), controller.recensioniConCommento.get(i).getValutazione(), controller.recensioniConCommento.get(i).getTesto(), commenti, i+1, controller);    //mostra l'i-esimo commento
        }
    }//fine showComment

    /**
     * Aggiunge in 'commenti' un commento fatto dall'utente che ha effettuato l'accesso al libro selezionato.
     *
     * @param utente       username dell'utente che ha fatto la recensione
     * @param val          la valutazione della recensione
     * @param commentoUser il commento della recensione
     * @param commenti     contiene le recensioni
     * @param n            numero di commenti inseriti
     * @param controller   il controller
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

        panel1.setPreferredSize(new Dimension ((int) (controller.screenWidth/2.044), (int) (controller.screenHeight/4.8)));    //imposta la dimensione preferita di 'panel1'
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

        panel1.setPreferredSize (new Dimension ((int) (controller.screenWidth/2.56), jcomp1.getHeight()+jcomp2.getHeight()+jcomp7.getHeight()+jcomp8.getHeight())); //imposta la dimensione preferita di 'panel1'

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;  //imposta a 0 la colonna del layout in cui inserire 'panel1'
        constraints.gridy = (n-1);  //imposta a n-1 la riga del layout in cui inserire 'panel1'
        commenti.add(panel1, constraints);  //inserisce nel 'panel1' nel JPanel 'commenti'
    }//fine addComment

    /**
     * Cambia le icone delle stelle mostrate in base alla valutazione fatta dall'utente al libro selezionato.
     *
     * @param s1             la prima stella
     * @param s2             la seconda stella
     * @param s3             la terza stella
     * @param s4             la quarta stella
     * @param s5             la quinta stella
     * @param stellaPienaIco l'icona della stella piena
     * @param stellaVuotaIco l'icona della stella vuota
     * @param stellaMezzaIco l'icona della stella mezza
     */
    public void changeStars(JLabel s1, JLabel s2, JLabel s3, JLabel s4, JLabel s5, ImageIcon stellaPienaIco, ImageIcon stellaVuotaIco, ImageIcon stellaMezzaIco){
        if(valutazioneMedia <= 0.25){   //controlla se la media è minore uguale a 0,25
            s1.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaVuotaIco'
            s2.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaVuotaIco'
            s3.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaVuotaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 0.75){    //controlla se la media è in [0.25,0.75[
            s1.setIcon(stellaMezzaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaMezzaIco'
            s2.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaVuotaIco'
            s3.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaVuotaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 1.25){    //controlla se la media è in [0.75,1.25[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaVuotaIco'
            s3.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaVuotaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 1.75){    //controlla se la media è in [1.25,1.75[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaMezzaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaMezzaIco'
            s3.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaVuotaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 2.25){    //controlla se la media è in [1.75,2.25[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaVuotaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 2.75){    //controlla se la media è in [2.25,2.75[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaMezzaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaMezzaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 3.25){    //controlla se la media è in [2.75,3.25[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaPienaIco'
            s4.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaVuotaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 3.75){    //controlla se la media è in [3.25,3.75[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaPienaIco'
            s4.setIcon(stellaMezzaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaMezzaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 4.25){    //controlla se la media è in [3.75,4.25[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaPienaIco'
            s4.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaPienaIco'
            s5.setIcon(stellaVuotaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaVuotaIco'
        } else if (valutazioneMedia < 4.75){    //controlla se la media è in [4.25,4.75[
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaPienaIco'
            s4.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaPienaIco'
            s5.setIcon(stellaMezzaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaMezzaIco'
        } else {
            s1.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's1' con l'immagine 'stellaPienaIco'
            s2.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's2' con l'immagine 'stellaPienaIco'
            s3.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's3' con l'immagine 'stellaPienaIco'
            s4.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's4' con l'immagine 'stellaPienaIco'
            s5.setIcon(stellaPienaIco); //imposta l'icona della JLabel 's5' con l'immagine 'stellaPienaIco'
        }
    }//fine changeStars

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo
        int screenWidth = screenSize.width; //larghezza dello schermo
        int screenHeight = screenSize.height;   //altezza dello schermo

        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));  //carica l'immagine nel percorso /close.png
        Image imagine = closeImg.getImage().getScaledInstance((int) (screenWidth/51.2), (int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine
        closeImg = new ImageIcon(imagine);  //reinizializza l'ImageIcon 'closeImg' con l'Image 'image'
        closeBT = new JLabel(closeImg); //inizializza la JLabel 'closeBT' con 'closeImg'

        editorPane1 = new JEditorPane();
        editorPane1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD369))); //imposta il colore del bordo del JEditorPane 'editorPane1'
    }
}
