package Controller;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.Autore;
import Model.Libro;
import Model.Serie;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    private  Utente utente;
    public  ArrayList<Libro> listaLibri = getLibri();
    public ArrayList<Serie> listaSerie = getSerie();
    public String isbn_selected = "";
    public String nome_l = "";
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
    //public String getDataNascita(){return utente.getDataNascita();} //ritorna la data di nascita dell'utente

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

    public ArrayList<String> getLibriISBN(){    //ritorna gli ISBN di tutti i libri
        ArrayList<String> isbn = new ArrayList<String>();   //contiene tutti gli ISBN dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            isbn.add(l.isbn);   //aggiunge l'isbn del libro 'l' in 'isbn'
        }

        return isbn;
    }

    public ArrayList<String> getLibriTitolo(){  //ritorna i titoli di tutti i libri
        ArrayList<String> titolo = new ArrayList<String>();   //contiene tutti i titoli dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            titolo.add(l.titolo);   //aggiunge il titolo del libro 'l' in 'titolo'
        }

        return titolo;
    }

    public ArrayList<String> getLibriGenere(){  //ritorna i generi di tutti i libri
        ArrayList<String> genere = new ArrayList<String>();   //contiene tutti i generi dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            genere.add(l.genere);   //aggiunge il genere del libro 'l' in 'genere'
        }

        return genere;
    }

    public ArrayList<String> getLibriLingua(){  //ritorna le linge di tutti i libri
        ArrayList<String> lingua = new ArrayList<String>();   //contiene tutte le lingue dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            lingua.add(l.lingua);   //aggiunge la lingua del libro 'l' in 'lingua'
        }

        return lingua;
    }

    public ArrayList<String> getLibriEditore(){ //ritorna gli editori di tutti i libri
        ArrayList<String> editore = new ArrayList<String>();   //contiene tutti gli editori dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            editore.add(l.editore); //aggiunge l'editore del libro 'l' in 'editore'
        }

        return editore;
    }

    public ArrayList<String> getLibriDataPubblicazione(){   //ritorna le date di pubblicazione di tutti i libri
        ArrayList<String> dp = new ArrayList<String>();   //contiene tutte le date di pubblicazione dei libri

        for(Libro l: listaLibri){   //scorre la lista dei libri
            dp.add(l.dataPubblicazione.toString()); //aggiunge la data di pubblicazione del libro 'l' in 'dp'
        }

        return dp;
    }

    public ArrayList<Libro> getLibriSerie(String isbnSerie){
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ArrayList<Libro> libri = new ArrayList<>();
        ResultSet rs = l.getLibriSerieDB(isbnSerie);
        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                for(Libro libro: listaLibri){
                    if (libro.isbn.equals(rs.getString("libro"))){
                        libri.add(libro);
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }
        return libri;
    }

    /*public ArrayList<String> getAutoriLibroNome(){  //ritorna i nomi tutti gli autori di libri
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
    }*/

    /*public ArrayList<String> getAutoriLibroCognome(){   //ritorna i cognomi tutti gli autori di libri
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
    }*/

    // COLLANA //
    public ArrayList<String> getCollanaNome(){  //ritorna tutti i nomi delle collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        ResultSet collane = c.getCollanaDB();   //contiene tutte le collane
        ArrayList<String> nome = new ArrayList<String>();   //contiene tutti i nomi delle collane

        try {
            while(collane.next()){  //scorre il ResultSet 'collane' contenente le collane
                nome.add(collane.getString("nome"));    //aggiunge i nomi delle collane in 'nome'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione con il DB

        return nome;
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

    /*public ResultSet getAutoriLibro() {
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet rs = a.getAutoriLibroDB();

        return rs;
    }*/

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

    public ArrayList<String> getDisponibilitaIndirizzo(){
        ArrayList<String> indirizzo = new ArrayList<>();
        ResultSet rs = getDisponibilita();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                indirizzo.add(rs.getString("indirizzo"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return indirizzo;
    }


    public ArrayList<String> getDisponibilitaSitoWeb(){
        ArrayList<String> sitoWeb = new ArrayList<>();
        ResultSet rs = getDisponibilita();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                sitoWeb.add(rs.getString("sitoweb"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return sitoWeb;
    }

    public ArrayList<String> getDisponibilitaNumeroTelefono(){
        ArrayList<String> nTel = new ArrayList<>();
        ResultSet rs = getDisponibilita();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                nTel.add(rs.getString("numerotelefonico"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return nTel;
    }

    // PRESENTAZIONE //

    public ResultSet getPresentazione(){
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        ResultSet rs = p.getPresentazioneDB(isbn_selected);
        return rs;
    }

    public ArrayList<String> getPresentazioneLuogo(){
        ArrayList<String> luogo = new ArrayList<>();
        ResultSet rs = getPresentazione();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                luogo.add(rs.getString("luogo"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return luogo;
    }

    public ArrayList<String> getPresentazioneStruttura(){
        ArrayList<String> struttura = new ArrayList<>();
        ResultSet rs = getPresentazione();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                struttura.add(rs.getString("struttura"));
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return struttura;
    }

    public ArrayList<String> getPresentazioneData(){
        ArrayList<String> data = new ArrayList<>();
        ResultSet rs = getPresentazione();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                data.add(rs.getDate("datap").toString());
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return data;
    }

    public ArrayList<String> getPresentazioneOrario(){
        ArrayList<String> orario = new ArrayList<>();
        ResultSet rs = getPresentazione();

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contente i libri
                orario.add(rs.getTime("ora").toString());
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return orario;
    }

    // SERIE //

    public ArrayList<Serie> getSerie() {   //ritorna i dati di tutti i libri nel DB
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ResultSet rs = s.getSerieDB();  //cerca i dati di tutti i libri nel DB
        ArrayList<Serie> serie = new ArrayList<Serie>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri
                ArrayList<Libro> libri = getLibriSerie(rs.getString("isbn")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), libri, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();  //chiude la connessione al DB

        return serie;
    }


}
