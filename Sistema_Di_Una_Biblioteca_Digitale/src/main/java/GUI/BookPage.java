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
    private JCheckBox disponibilitàCheckBox;
    private JCheckBox presentazioniCheckBox;
    private JPanel jpanel;
    private JLabel nomeIsbn;
    private JScrollPane jscroll2;
    private JScrollPane jscroll1;
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

        nomeIsbn.setText(controller.nome_l + " - (" + controller.isbn_selected + ")");

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

        // --------------------------------------------------------------------------- //

        DefaultTableModel model1 = new DefaultTableModel(new Object[][]{}, new String[]{"Libreria", "Quantità", "Fruizione", "Indirizzo", "Sito Web", "N. di Telefono"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };

        DefaultTableModel model2 = new DefaultTableModel(new Object[][]{}, new String[]{"Luogo", "Struttura", "Data", "Orario"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // or a condition at your choice with row and column
            }
        };

        ArrayList<String> libreria = controller.getDisponibilitaLibreria();
        ArrayList<Integer> quantita = controller.getDisponibilitaQuantita();
        ArrayList<String> fruizione = controller.getDisponibilitaFruizione();
        ArrayList<String> indirizzo = controller.getDisponibilitaIndirizzo();
        ArrayList<String> sitoWeb = controller.getDisponibilitaSitoWeb();
        ArrayList<String> nTel = controller.getDisponibilitaNumeroTelefono();


        table1.setModel(model1);
        for(int i = 0; i < libreria.size(); i++){
            if(fruizione.get(i).equals("Digitale") || fruizione.get(i).equals("AudioLibro")) model1.addRow(new Object[]{libreria.get(i), "∞", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else if(fruizione.get(i).equals("Cartaceo") && quantita.get(i) == 0) model1.addRow(new Object[]{libreria.get(i), "Non Disponibile", fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
            else model1.addRow(new Object[]{libreria.get(i), quantita.get(i), fruizione.get(i), indirizzo.get(i), sitoWeb.get(i), nTel.get(i)});
        }

        ArrayList<String> luogo = controller.getPresentazioneLuogo();
        ArrayList<String> struttura = controller.getPresentazioneStruttura();
        ArrayList<String> data = controller.getPresentazioneData();
        ArrayList<String> orario = controller.getPresentazioneOrario();

        table2.setModel(model2);
        for(int i = 0; i < luogo.size(); i++){
            model2.addRow(new Object[]{luogo.get(i), struttura.get(i), data.get(i), orario.get(i)});
        }

        disponibilitàCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(presentazioniCheckBox.isSelected()) {
                    if (!disponibilitàCheckBox.isSelected()){
                        jscroll1.setVisible(false);
                    }
                    else jscroll1.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    disponibilitàCheckBox.setSelected(true);
                    jscroll1.setVisible(true);
                }
            }
        });

        presentazioniCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(disponibilitàCheckBox.isSelected()){
                    if(!presentazioniCheckBox.isSelected()) jscroll2.setVisible(false);
                    else jscroll2.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(frame, "Non puoi rimuovere entrambe le tabelle.");
                    presentazioniCheckBox.setSelected(true);
                    jscroll2.setVisible(true);
                }
            }
        });
    }
}
