package GUI;

import Controller.Controller;
import Model.Presentazione;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BookPage {
    public JFrame frame;
    private JPanel buttonPanel;
    private JButton homeButton;
    private JButton libriButton;
    private JButton utenteButton;
    private JLabel closeBT;
    private JTable table1;
    private JTable table2;
    private JCheckBox disponibilitaCheckBox;
    private JCheckBox presentazioniCheckBox;
    private JPanel jpanel;
    private JLabel nomeIsbn;
    private JScrollPane jscroll2;
    private JScrollPane jscroll1;
    private JButton serieButton;
    private JLabel valutazione;
    private JLabel stella1;
    private JLabel stella2;
    private JLabel stella3;
    private JLabel stella4;
    private JLabel stella5;
    private Boolean active = false;

    private float valutazioneMedia;


    public BookPage(JFrame frameC, Controller controller) {
        UIManager.put("MenuItem.selectionBackground", new Color(0xCF9E29));
        UIManager.put("MenuItem.selectionForeground", new Color(0x222831));

        JPopupMenu utenteMenu = new JPopupMenu();  //crea il menu 'utenteMenu'
        JMenuItem utenteExit = new JMenuItem("Logout");//crea la voce del menu "Logout"
        utenteExit.setBackground(new Color(0xFFD369));
        utenteExit.setFocusPainted(false);
        utenteExit.setBorder(BorderFactory.createEmptyBorder());
        JMenuItem utenteProfilo = new JMenuItem("Profilo"); //crea la voce del menu "Profilo"
        utenteProfilo.setBackground(new Color(0xFFD369));
        utenteProfilo.setFocusPainted(false);
        utenteProfilo.setBorder(BorderFactory.createEmptyBorder());
        utenteProfilo.setFocusable(false);
        JMenuItem utenteLibrerie = new JMenuItem("Librerie");   //crea la voce del menu "Librerie"
        utenteLibrerie.setBackground(new Color(0xFFD369));
        utenteLibrerie.setFocusPainted(false);
        utenteLibrerie.setBorder(BorderFactory.createEmptyBorder());
        utenteMenu.setPopupSize(new Dimension(80, 75));
        utenteMenu.setBorder(BorderFactory.createEmptyBorder());
        utenteMenu.setBackground(new Color(0xFFD369));
        utenteMenu.add(utenteProfilo);  //aggiunge la voce 'utenteProfilo' al menu 'utenteMenu'
        utenteMenu.add(utenteLibrerie); //aggiunge la voce 'utenteLibrerie' al menu 'utenteMenu'
        utenteMenu.add(utenteExit); //aggiunge la voce 'utenteProfilo' al menu 'utenteExit'

        if (controller.getPartitaIva() == null) {   //controlla se la partita IVA dell'utente è nulla
            utenteLibrerie.setVisible(false);   //rende invisibile la voce di menu 'utenteLibrerie'
        }


        frame = new JFrame("Biblioteca Digitale");
        frame.setUndecorated(true); //abilita le decorazioni del frame
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(1280, 720);   //imposta larghezza e altezza del frame
        frame.setLocationRelativeTo(null);  //posiziona il frame al centro dello schermo
        frame.setResizable(false);  //evita che l'utente modifichi le dimensioni del frame
        frame.setVisible(true);

        nomeIsbn.setText(controller.nome_l + " - (" + controller.isbn_selected + ")");  //imposta il tetsto della JLabel 'nomeIsbn' con il nome e l'ISBN del libro selezionato

        DecimalFormat valMedForm = new DecimalFormat("#.#");    //formato da mostrare del numero decimale

        valutazioneMedia = controller.valutazioneMediaLibro();  //calcola la media delle valutazioni del libro selezionato
        valutazione.setText(valMedForm.format(valutazioneMedia));   //mostra la media delle valutazioni del libro selezionato

        ImageIcon stellaVuotaIco = new ImageIcon(this.getClass().getResource("/stella_vuota.png"));
        Image stellaVuotaImg = stellaVuotaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaVuotaIco = new ImageIcon(stellaVuotaImg);

        ImageIcon stellaPienaIco = new ImageIcon(this.getClass().getResource("/stella_piena.png"));
        Image stellaPienaImg = stellaPienaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaPienaIco = new ImageIcon(stellaPienaImg);

        ImageIcon stellaMezzaIco = new ImageIcon(this.getClass().getResource("/stella_mezza.png"));
        Image stellaMezzaImg = stellaMezzaIco.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        stellaMezzaIco = new ImageIcon(stellaMezzaImg);


        if(valutazioneMedia <= 0.25){   //controlla se la media è minore uguale a 0,25
            stella1.setIcon(stellaVuotaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 0.75){    //controlla se la media è in [0.25,0.75[
            stella1.setIcon(stellaMezzaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.25){    //controlla se la media è in [0.75,1.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaVuotaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 1.75){    //controlla se la media è in [1.25,1.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaMezzaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.25){    //controlla se la media è in [1.75,2.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaVuotaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 2.75){    //controlla se la media è in [2.25,2.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaMezzaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.25){    //controlla se la media è in [2.75,3.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaVuotaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 3.75){    //controlla se la media è in [3.25,3.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaMezzaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.25){    //controlla se la media è in [3.75,4.25[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaVuotaIco);
        } else if (valutazioneMedia < 4.75){    //controlla se la media è in [4.25,4.75[
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaMezzaIco);
        } else {
            stella1.setIcon(stellaPienaIco);
            stella2.setIcon(stellaPienaIco);
            stella3.setIcon(stellaPienaIco);
            stella4.setIcon(stellaPienaIco);
            stella5.setIcon(stellaPienaIco);
        }

        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frameC.setEnabled(true);
                frame.dispose();
                System.exit(0);
            }
        });

        // -------------------------------------------------------------------------------------- //
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(frameC, controller); //chiama il frame 'hp'
                hp.frame.setVisible(true);  //rende visibile il frame 'hp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    utenteMenu.show(utenteButton, utenteButton.getWidth() - 80, utenteButton.getHeight()); //mostra le voci del menu 'utenteMenu'
                }
            }
        });

        utenteMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                active = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                active = false;
                utenteButton.setBackground(Color.decode("#FFD369"));
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                active = false;
                utenteButton.setBackground(Color.decode("#FFD369"));
            }
        });

        utenteProfilo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profilo pf = new Profilo(frameC, controller); //chiama il frame 'pf'
                pf.frame.setVisible(true);  //rende visible il frame 'pf'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();

            }
        });

        utenteExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameC.setVisible(true); //rende visibile il frame chiamante
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                utenteButton.setBackground(Color.decode("#cf9e29"));
            }
        });

        utenteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (active == false){
                    super.mouseExited(e);
                    utenteButton.setBackground(Color.decode("#FFD369"));
                }
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homeButton.setBackground(Color.decode("#cf9e29"));
            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homeButton.setBackground(Color.decode("#FFD369"));
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                serieButton.setBackground(Color.decode("#cf9e29"));
            }
        });
        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                serieButton.setBackground(Color.decode("#FFD369"));
            }
        });

        serieButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SeriesPage sp = new SeriesPage(frameC, controller);   //chiama il frame 'bp'
                sp.frame.setVisible(true);  //rende visibile il frame chiamato 'bp'
                frame.setVisible(false);    //rende invisibile il frame
                frame.dispose();
            }
        });

        // --------------------------------------------------------------------------- //

        DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new String[]{"Libreria", "Quantità", "Fruizione", "Indirizzo", "Sito Web", "N. di Telefono"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   //permette di rendere non editabile la cella (row,column)
            }
        };

        DefaultTableModel model2 = new DefaultTableModel(new Object[][]{}, new String[]{"Luogo", "Struttura", "Data", "Orario"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //permette di rendere non editabile la cella (row,column)
            }
        };

        ArrayList<String> libreria = controller.getDisponibilitaLibreria(); //nomi delle librerie che possiedono il libro selezionato
        ArrayList<Integer> quantita = controller.getDisponibilitaQuantita();    //quantità disponibili del libro selezionato
        ArrayList<String> fruizione = controller.getDisponibilitaFruizione();   //modalità di fruizione disponibile del libro selezionato
        ArrayList<String> indirizzo = controller.getDisponibilitaIndirizzo();   //indirizzi delle librerie che possiedono il libro selezionato
        ArrayList<String> sitoWeb = controller.getDisponibilitaSitoWeb();   //siti web delle librerie che possiedono il libro selezionato
        ArrayList<String> nTel = controller.getDisponibilitaNumeroTelefono();   //numeri telefonici delle librerie che possiedono il libro selezionato

        table1.setModel(model1);    //imposta il modello dei dati della JTable 'table2'

        for(int i = 0; i < libreria.size(); i++){

            if(fruizione.get(i).equals("Digitale") || fruizione.get(i).equals("AudioLibro")) model1.addRow(new Object[]{libreria.get(i), "∞", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else if(fruizione.get(i).equals("Cartaceo") && quantita.get(i) == 0) model1.addRow(new Object[]{libreria.get(i), "Non Disponibile", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else model1.addRow(new Object[]{libreria.get(i), quantita.get(i), fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
        }

        ArrayList<Presentazione> listaPresentazioni = controller.getPresentazione();
        table2.setModel(model2);    //imposta il modello dei dati della JTable 'table1'

        for(int i = 0; i < listaPresentazioni.size(); i++){
            model2.addRow(new Object[]{listaPresentazioni.get(i).luogo, listaPresentazioni.get(i).struttura, listaPresentazioni.get(i).data, listaPresentazioni.get(i).ora});
        }

        disponibilitaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(presentazioniCheckBox.isSelected()) {    //controlla se è stato selezionato il JCheckBox 'presentazioniCheckBox'

                    if (!disponibilitaCheckBox.isSelected()){   //controlla se non è stato selezionato il JCheckBox 'disponibilitaCheckBox'
                        jscroll1.setVisible(false); //rende invisibile il JScrollPane 'jscroll1'
                    }
                    else jscroll1.setVisible(true); //rende visibile il JScrollPane 'jscroll1'
                } else {
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    disponibilitaCheckBox.setSelected(true);    //seleziona 'disponibilitaCheckBox'
                    jscroll1.setVisible(true);  //rende visibile il JScrollPane 'jscroll1'
                }
            }
        });

        presentazioniCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(disponibilitaCheckBox.isSelected()){ //controlla se è stato selezionato il JCheckBox 'disponibilitaCheckBox'

                    if(!presentazioniCheckBox.isSelected()){    //controlla se non è stato selezionato il JCheckBox 'presentazioniCheckBox'
                        jscroll2.setVisible(false); //rende invisibile il JScrollPane 'jscroll2'
                    }
                    else jscroll2.setVisible(true); //rende visibile il JScrollPane 'jscroll2'
                } else{
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    presentazioniCheckBox.setSelected(true);  //seleziona 'presentazioniCheckBox'
                    jscroll2.setVisible(true);  //rende visibile il JScrollPane 'jscroll2'
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ImageIcon closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine = closeImg.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine);
        closeBT = new JLabel(closeImg);
    }
}