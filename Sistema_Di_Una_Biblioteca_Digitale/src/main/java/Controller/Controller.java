package Controller;

import DAO.*;
import GUI.RecensioneLibro;
import ImplementazionePostgresDAO.*;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private Utente utente;
    public ArrayList<Libro> listaLibri = getLibri();
    public ArrayList<Serie> listaSerie = getSerie();
    public ArrayList<Rivista> listaRiviste = getRiviste();
    public String isbn_selected = "";
    public String nome_selected = "";
    public boolean likeElementSelected;
    public ArrayList<Recensione> recensioniConCommento = new ArrayList<>();
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
            while(rs.next()){   //scorre il ResultSet 'rs' contenente l'utente con 'userEmail' e 'password'
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

    public Utente getUtente(String username){   //ritorna un Utente con i dati dell'utente 'username'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        Utente user = null;
        ResultSet rs = u.getUtenteDB(username); //cerca i dati dell'utente 'username' nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente l'utente
                user = new Utente(rs.getString("username"), rs.getString("passwordu"), rs.getString("email"), rs.getString("nome"), rs.getString("Cognome"), rs.getString("datanascita"), rs.getString("partitaiva"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        return user;
    }

    // LIBRO //
    public ArrayList<Libro> getLibri() {   //ritorna i dati di tutti i libri nel DB
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = l.getLibriDB();  //cerca i dati di tutti i libri nel DB
        ArrayList<Libro> libri = new ArrayList<Libro>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri
                ArrayList<Autore> autori = getAutori(rs.getString("isbn")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'
                libri.add(new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), autori ,rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione al DB

        return libri;
    }

    public ArrayList<Libro> getLibri(String collana) {   //ritorna i dati di tutti i libri della collana 'collana' nel DB
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = l.getLibriDB(collana);  //cerca i dati di tutti i libri della collana 'collana' nel DB
        ArrayList<Libro> libri = new ArrayList<Libro>();    //contiene tutti i libri


        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                ArrayList<Autore> autori = getAutori(rs.getString("isbn")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'
                libri.add(new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), autori ,rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione al DB

        return libri;
    }

    public ArrayList<Libro> getLibriSerie(String isbnSerie){    //ritorna tutti i libri della serie con ISBN 'isbnSerie'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ArrayList<Libro> libri = new ArrayList<>(); //contiene i libri della serie
        ResultSet rs = l.getLibriSerieDB(isbnSerie);    //contiene i libri della serie trovati nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri della serie
                for(Libro libro: listaLibri){   //scorre la lista dei libri
                    if (libro.isbn.equals(rs.getString("libro"))){  //controlla se 'libro' appartiene alla serie
                        libri.add(libro);   //aggiunge 'libro' in 'liobri'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return libri;
    }

    // COLLANA //
    public ArrayList<String> getCollanaNome(){  //ritorna tutti i nomi delle collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        return c.getCollanaNomeDB();   //contiene tutte le collane
    }

    // AUTORE //
    public ArrayList<Autore> getAutori(String isbn){    //ritorna gli autori del libro con ISBN 'isbn'
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet rs = a.getAutoriDB(isbn); //ResultSet contenente tutti gli autori del libro con ISBN 'isbn'
        ArrayList<Autore> autori = new ArrayList<Autore>(); //contiene tutti gli autori del libro con ISBN 'isbn'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente gli autori
                autori.add(new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("nazionalita"), rs.getDate("datanascita")));  //aggiunge un nuovo autore in 'autori'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return autori;
    }

    // LIBRERIA //
    public ResultSet getDisponibilita(){    //ritorna un ResultSet con le disponibilita del libro con ISBN 'isbn_selected'
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ResultSet rs = l.disponibilitaDB(isbn_selected);    //ritorna tutte le dipsonibilità del libro selezionato

        return rs;
    }

    public ArrayList<String> getDisponibilitaLibreria(){    //ritorna i nomi di tutte le librerie che possiedono il libro selezionato
        ArrayList<String> libreria = new ArrayList<>(); //contiene i nomi delle librerie
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                libreria.add(rs.getString("nome")); //aggiunge un nuovo nome in 'libreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return libreria;
    }

    public ArrayList<Integer> getDisponibilitaQuantita(){   //ritorna le quantità disponibili del libro selezionato
        ArrayList<Integer> quantita = new ArrayList<>();    //contiene le quantità disponibili
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                quantita.add(rs.getInt("quantita"));    //aggiunge una nuova quantità in 'quantita'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return quantita;
    }

    public ArrayList<String> getDisponibilitaFruizione(){  //ritorna le modalità di fruizione disponibili del libro selezionato
        ArrayList<String> fruizione = new ArrayList<>();    //contiene le modalità di fruizione disponibili
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                fruizione.add(rs.getString("fruizione")); //aggiunge una nuova modalità di fruizione in 'fruizione'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return fruizione;
    }

    public ArrayList<String> getDisponibilitaIndirizzo(){   //ritorna gli indirizzi delle librerie che possiedono il libro selezionato
        ArrayList<String> indirizzo = new ArrayList<>();    //contiene gli indirizzi delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                indirizzo.add(rs.getString("indirizzo"));   //aggiunge un nuovo indirizzo in 'indirizzo'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return indirizzo;
    }

    public ArrayList<String> getDisponibilitaSitoWeb(){ //ritorna i siti web delle librerie che possiedono il libro selezionato
        ArrayList<String> sitoWeb = new ArrayList<>();  //contiene i siti web delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                sitoWeb.add(rs.getString("sitoweb"));   //aggiunge un nuovo sito web in 'sitoWeb'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return sitoWeb;
    }

    public ArrayList<String> getDisponibilitaNumeroTelefono(){  //ritorna i numeri telefonici delle librerie che possiedono il libro selezionato
        ArrayList<String> nTel = new ArrayList<>(); //contiene i numeri telefonici delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //contiene le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                nTel.add(rs.getString("numerotelefonico")); //aggiunge un nuovo numero telefonico in 'nTel'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return nTel;
    }

    // PRESENTAZIONE //

    public ArrayList<Presentazione> getPresentazione(){    //ritorna i dati di tutte le presentazioni del libro con ISBN 'isbn_selected'
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        ArrayList<Presentazione> presentazioni = new ArrayList<>(); //contiene tutte le presentazioni del libro selezionato
        ResultSet rs = p.getPresentazioneDB(isbn_selected); //cerca i dati di tutte le presentazioni del libro selezionato
        Libro libroSelezionato = null;  //libro selezionato

        for (Libro l: listaLibri){  //scorre la lista dei libri
            if(l.isbn.equals(isbn_selected)) libroSelezionato = l;  //se 'l' è il libro selezionato, allora assegna 'l' a 'libroSelezionato'
        }

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le presentazioni del libro selezionato
                presentazioni.add(new Presentazione(rs.getString("luogo"), rs.getString("struttura"), rs.getDate("datap"), rs.getTime("ora").toString(), libroSelezionato));   //inserisce una nuova presentazione in 'Presentazioni'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        p.chiudiConnessione();  //chiude la connessione al DB

        return presentazioni;
    }


    // SERIE //

    public ArrayList<Serie> getSerie() {   //ritorna i dati di tutte le serie nel DB
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ResultSet rs = s.getSerieDB();  //cerca i dati di tutte le serie nel DB
        ArrayList<Serie> serie = new ArrayList<Serie>();    //contiene tutte le serie

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                ArrayList<Libro> libri = getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' nell'ArrayList 'libri'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), libri, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();  //chiude la connessione al DB

        return serie;
    }

    public ArrayList<String> getSerieGeneri(){  //ritorna tutti i generi dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieGenereDB();
    }

    public ArrayList<String> getSerieAutori(){  //ritorna tutti gli autori dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieAutoriDB();
    }

    public ArrayList<Serie> getListaSerieGenere(String genere){ //ritorna una lista delle serie con libri del genere 'genere'
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ArrayList<Serie> serie = new ArrayList<>(); //contiene le serie con libri del genere 'genere'
        ResultSet rs = s.getListaSerieGenereDB(genere); //cerca le serie con libri del genere 'genere'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                ArrayList<Libro> libri = getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' nell'ArrayList 'libri'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), libri, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();  //chiude la connessione al DB

        return serie;
    }

    public ArrayList<Serie> getListaSerieAutore(String autore){ //ritorna una lista delle serie con libri dell'autore 'autore'
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ArrayList<Serie> serie = new ArrayList<>(); //contiene le serie con libri dell'autore 'autore'
        ResultSet rs = s.getListaSerieAutoreDB(autore); //cerca le serie con libri dell'autore 'autore'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                ArrayList<Libro> libri = getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' nell'ArrayList 'libri'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), libri, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();   //chiude la connessione al DB

        return serie;
    }

    // RECENSIONE //

    public float valutazioneMediaLibro(){   //ritorna la media delle valutazioni del libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaLibroDB(isbn_selected);
    }

    public void likeLibro(){    //controlla se l'utente ha il libro selezionato tra i preferiti e pone il risultato in 'likeLibroSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        likeElementSelected = r.likeLibroDB(isbn_selected, utente.username);
        return;
    }

    public void changeLikeLibro(){   //cambia il valore di 'likeLibroSelected' e togli/mette nei preferiti dell'utente il libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        likeElementSelected = r.changeLikeLibroDB(likeElementSelected, isbn_selected, utente.username);
    }

    public void addRecensioneLibro(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        r.addRecensioneLibroDB(valutazione, text, isbn_selected, utente.username);
        return;
    }

    public void allRecWithCommentLibro(){    //ritorna tutte le recensioni con testo fatte al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentLibroDB(isbn_selected);    //contiene tutte le recensioni fatte al libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }

    public float valutazioneMediaSerie(){   //ritorna la media delle valutazioni del libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaSerieDB(isbn_selected);
    }

    public void likeSerie(){    //controlla se l'utente ha il libro selezionato tra i preferiti e pone il risultato in 'likeLibroSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        likeElementSelected = r.likeSerieDB(isbn_selected, utente.username);
        return;
    }

    public void changeLikeSerie(){   //cambia il valore di 'likeLibroSelected' e togli/mette nei preferiti dell'utente il libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        likeElementSelected = r.changeLikeSerieDB(likeElementSelected, isbn_selected, utente.username);
    }

    public void addRecensioneSerie(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        r.addRecensioneSerieDB(valutazione, text, isbn_selected, utente.username);
        return;
    }

    public void allRecWithCommentSerie(){    //ritorna tutte le recensioni con testo fatte al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentSerieDB(isbn_selected);    //contiene tutte le recensioni fatte al libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }

    // RIVISTA //

    public ArrayList<Rivista> getRiviste() {   //ritorna i dati di tutte le serie nel DB
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        ResultSet rs = r.getRivisteDB();  //cerca i dati di tutte le serie nel DB
        ArrayList<Rivista> riviste = new ArrayList<Rivista>();    //contiene tutte le serie

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                riviste.add(new Rivista(rs.getString("issn"), rs.getString("titolo"), rs.getString("editore"), rs.getInt("annoPubblicazione"), rs.getString("nomer") + " " + rs.getString("cognomer"), rs.getString("argomento")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB

        return riviste;
    }

}
