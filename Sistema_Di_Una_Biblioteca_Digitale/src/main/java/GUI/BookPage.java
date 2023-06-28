package GUI;

import Controller.Controller;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private Boolean active = false;


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

        ArrayList<String> luogo = controller.getPresentazioneLuogo();   //luogo delle presentazioni del libro selezionato
        ArrayList<String> struttura = controller.getPresentazioneStruttura();   //strutture delle presentazoni del libro selezionato
        ArrayList<String> data = controller.getPresentazioneData(); //date delle presentazioni del libro selezionato
        ArrayList<String> orario = controller.getPresentazioneOrario(); //orari delle presentazione del libro selezionato

        table2.setModel(model2);    //imposta il modello dei dati della JTable 'table1'

        for(int i = 0; i < luogo.size(); i++){
            model2.addRow(new Object[]{luogo.get(i), struttura.get(i), data.get(i), orario.get(i)});
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
}