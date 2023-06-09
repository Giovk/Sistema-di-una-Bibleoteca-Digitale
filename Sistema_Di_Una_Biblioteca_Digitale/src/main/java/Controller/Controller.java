package Controller;

import DAO.AutoreDAO;
import DAO.CollanaDAO;
import DAO.LibroDAO;
import DAO.UtenteDAO;
import ImplementazionePostgresDAO.AutoreImplementazionePostgresDAO;
import ImplementazionePostgresDAO.CollanaImplementazionePostgresDAO;
import ImplementazionePostgresDAO.LibroImplementazionePostgresDAO;
import ImplementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import Model.Utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private  Utente utente;
    //private  ArrayList<Libro> libri = new ArrayList<Libro>();
    public Controller(){

    }

    // UTENTE //
    public void aggiungiUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){ //aggiunge un nuovo utente nel database e in memoria
        utente = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA); // Registra il nuovo utente in memoria
        //utente.regUtente(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente in memoria
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        u.addUtenteDB(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente nel DB
    }

    public int validaUtente(String userEmail, String password){ //conta il numero di utenti registrati con username o email 'userMail' e con password 'password'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        return u.validaUtenteDB(userEmail, password);
    }

    public void setUtente(String userEmail, String password) { //prende in input un username o un'email e una password per cercare nel DB un utente con queste credenziali e poi lo carica in memoria
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        ResultSet rs = u.getUtenteDB(userEmail, password);  //cerca nel DB un utente con 'userEmail' e 'password'
        String us = null;
        String p = null;
        String e = null;
        String n = null;
        String c = null;
        String dn = null;
        String pIVA = null;

        try {
            while(rs.next()){   //scorre il ResultSet 'rs' contente l'utente con 'userEmail' e 'password'
                us = rs.getString("username");  //imposta l'username del utente
                p = rs.getString("passwordu");  //imposta la password del utente
                e = rs.getString("email");  //imposta l'email del utente
                n = rs.getString("nome");   //imposta il nome del utente
                c = rs.getString("cognome");    //imposta il cognome del utente
                dn = rs.getString("datanascita");   //imposta la data di nascita del utente
                pIVA = rs.getString("partitaiva");  //imposta la partita IVA del utente
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        utente = new Utente(us, p, e, n, c, dn, pIVA);
    }

    public int[] validaModUtente(String email, String username, String pIva){   //controlla se delle modifiche effettuate dallutente sono corrette, verificando che l'email 'email', l'username 'uername' e/o la partita IVA 'pIva' non siano già state utilizzate da altri utenti
        int[] error = {0, 0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        if(!email.equals(utente.email)){    //controlla se l'email è stata modificata
            error[0] = u.validaModEmailDB(email);   //mette in 'error[0]' il numero di utenti con 'email' trovati nel DB
        }

        if(!username.equals(utente.username)){  //controlla se l'username è stato modificato
            error[1] = u.validaModUsernameDB(username); //mette in 'error[1]' il numero di utenti con 'username' trovati nel DB
        }

        if(!pIva.equals(utente.partitaIVA)){    //controlla se l'username è stato modificato
            error[2] = u.validaModPIVADB(pIva); //mette in 'error[2]' il numero di utenti con pIva' trovati nel DB
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        return error;
    }

    public void modUtente(String email, String nome, String cognome, String username, String password, String partitaIVA){  //modifica i dati dell'utente
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.modUtenteDB(email, nome, cognome, username, password, partitaIVA, utente.username);   //modifica i dati dell'utente nel DB

        utente.setEmail(email); //imposta l'email dell'utente
        utente.setNome(nome);   //imposta il nome dell'utente
        utente.setCognome(cognome); //imposta il cognome dell'utente
        utente.setUsername(username);   //imposta l'username dell'utente
        utente.setPassword(password);   //imposta la password dell'utente
        utente.setPartitaIva(partitaIVA);   //imposta la partita IVA dell'utente
    }

    public String getUsername(){return utente.getUsername();}   //ritorna l'username dell'utente
    public String getNome(){return utente.getNome();}   //ritorna il nome dell'utente
    public String getCognome(){return utente.getCognome();} //ritorna il cognome dell'utente
    public String getEmail(){return utente.getEmail();} //ritorna l'email dell'utente
    public String getPassword(){return utente.getPassword();}   //ritorna la password dell'utente
    public String getPartitaIva(){return utente.getPartitaIVA();}   //ritorna la partita IVA dell'utente
    public String getDataNascita(){return utente.getDataNascita();} //ritorna la data di nascita dell'utente

    // LIBRO //
    public ResultSet getLibri() {   //ritorna i dati di tutti i libri nel DB
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = l.getLibriDB();  //cerca i dati di tutti i libri nel DB

        return rs;
    }

    public ArrayList<String> getLibriISBN(){    //ritorna gli ISBN di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> isbn = new ArrayList<String>();   //contiene tutti gli ISBN dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                isbn.add(libri.getString("isbn"));  //aggiunge gli isbn trovati in 'isbn'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return isbn;
    }

    public ArrayList<String> getLibroAutori() {   //ritorna i dati di tutti i libri nel DB
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ArrayList<String> isbn = getLibriISBN();
        ArrayList<String> autori = new ArrayList<String>();
        for (String s: isbn){
            int k = 0;
            ResultSet autors = l.getLibroAutoriDB(s);
            String newAutore = "";
            try {
                while (autors.next()){
                        if(k>=0) newAutore = newAutore +"\n";
                        newAutore = newAutore + autors.getString("nome") + " " + autors.getString("cognome");
                        k++;
                }
            }catch (SQLException var){
                var.printStackTrace();
            }
            l.chiudiConnessione();
            autori.add(newAutore);
        }
        return autori;
    }
    public ArrayList<String> getLibriTitolo(){  //ritorna i titoli di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> titolo = new ArrayList<String>(); //contiene tutti i titoli dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                titolo.add(libri.getString("titolo"));  //aggiunge i titoli trovati in 'titolo'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return titolo;
    }

    public ArrayList<String> getLibriGenere(){  //ritorna i generi di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> genere = new ArrayList<String>(); //contiene tutti i generi dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                genere.add(libri.getString("genere"));  //aggiunge i generi trovati in 'genere'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return genere;
    }

    public ArrayList<String> getLibriLingua(){  //ritorna le linge di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> lingua = new ArrayList<String>(); //contiene tutte le lingue dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                lingua.add(libri.getString("lingua"));  //aggiunge le lingue trovate in 'lingua'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return lingua;
    }

    public ArrayList<String> getLibriEditore(){ //ritorna gli editori di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> editore = new ArrayList<String>();    //contiene tutti gli editori dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                editore.add(libri.getString("editore"));   //aggiunge gli editori trovati in 'editore'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return editore;
    }

    public ArrayList<String> getLibriDataPubblicazione(){   //ritorna le date di pubblicazione di tutti i libri
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet libri = getLibri();   //contiene tutti i libri
        ArrayList<String> dp = new ArrayList<String>(); //contiene tutti gli editori dei libri

        try {
            while(libri.next()){    //scorre il ResultSet 'libri' contente i libri
                dp.add(libri.getString("datapubblicazione"));   //aggiunge le date di pubblicazione trovate in 'dp'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione con il DB
        return dp;
    }

    public ResultSet getAutoriLibro() {
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet rs = a.getAutoriLibroDB();

        return rs;
    }

    public ArrayList<String> getAutoriLibroNome(){  //ritorna i nomi tutti gli autori di libri
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet autori = a.getAutoriLibroDB();    //contiene tutti gli autori
        ArrayList<String> nome = new ArrayList<String>();   //contiene tutti i nomi degli autori di libri

        try {
            while(autori.next()){   //scorre il ResultSet 'autori' contente gli autori di libri
                nome.add(autori.getString("nome")); //aggiunge i nomi degli autori dei libri in 'nome'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        a.chiudiConnessione();  //chiude la connessione con il DB
        return nome;
    }

    public ArrayList<String> getAutoriLibroCognome(){   //ritorna i cognomi tutti gli autori di libri
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet autori = a.getAutoriLibroDB();    //contiene tutti gli autori
        ArrayList<String> cognome = new ArrayList<String>();    //contiene tutti i cognomi degli autori di libri

        try {
            while(autori.next()){   //scorre il ResultSet 'autori' contente gli autori di libri
                cognome.add(autori.getString("cognome"));   //aggiunge i cognomi degli autori dei libri in 'cognome'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        a.chiudiConnessione();  //chiude la connessione con il DB
        return cognome;
    }

    // COLLANA //
    public ArrayList<String> getCollanaNome(){  //ritorna tutti i nomi delle collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        ResultSet collane = c.getCollanaDB();   //contiene tutte le collane
        ArrayList<String> nome = new ArrayList<String>();   //contiene tutti i nomi delle collane

        try {
            while(collane.next()){  //scorre il ResultSet 'collane' contente le collane
                nome.add(collane.getString("nome"));    //aggiunge i nomi delle collane in 'nome'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione con il DB
        return nome;
    }
}
