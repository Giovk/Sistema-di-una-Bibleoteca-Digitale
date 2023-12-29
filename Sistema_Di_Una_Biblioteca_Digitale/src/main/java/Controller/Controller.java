package Controller;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    public Utente utente;
    public ArrayList<Libro> listaLibri = getLibri();
    public Libro nuovoLibro;
    public Serie nuovoSerie;
    public Rivista nuovaRivista;
    public Fascicolo nuovoFascicolo;
    public ArticoloScientifico nuovoArticoloScientifico;
    public ArrayList<Libro> listaLibriCollana = new ArrayList<>();
    public ArrayList<Libro> listaLibriSerie = new ArrayList<>();
    public ArrayList<Serie> listaSerie = getSerie();
    public ArrayList<Serie> listaSerieAutore = new ArrayList<>();
    public ArrayList<Serie> listaSerieGenere = new ArrayList<>();
    public ArrayList<Rivista> listaRiviste = getRiviste();
    public ArrayList<Fascicolo> listaFascicoli = getFascicoli();
    public ArrayList<Presentazione> listaPresentazioni = new ArrayList<>();
    public ArrayList<Conferenza> listaConferenze = new ArrayList<>();
    public ArrayList<Conferenza> listaAllConferenze = new ArrayList<>();
    public ArrayList<Notifica> listaNotifiche = new ArrayList<>();
    public ArrayList<Fascicolo> listaFascicoliRivista = new ArrayList<>();
    public ArrayList<String> libriISBNPreferiti = new ArrayList<>();
    public ArrayList<String> libriTitoloPreferiti = new ArrayList<>();
    public ArrayList<Possesso> possessolPreferiti = new ArrayList<>();
    public ArrayList<Libreria> librerieLibriPreferiti = new ArrayList<>();
    public ArrayList<String> serieISBNPreferiti = new ArrayList<>();
    public ArrayList<String> serieTitoloPreferiti = new ArrayList<>();
    public ArrayList<Possesso> possessosPreferiti = new ArrayList<>();
    public ArrayList<Libreria> librerieSeriePreferiti = new ArrayList<>();
    public ArrayList<String> fascicoliTitoloPreferiti = new ArrayList<>();
    public ArrayList<Fascicolo> fascicoliPreferiti = new ArrayList<>();
    public ArrayList<Possesso> possessofPreferiti = new ArrayList<>();
    public ArrayList<ArticoloScientifico> listaArticoli = new ArrayList<>();
    public ArrayList<Libreria> librerieFascicoliPreferiti = new ArrayList<>();
    public ArrayList<Libreria> librerieUtente = new ArrayList<>();
    public String isbn_selected = "";
    public String nome_selected = "";
    public Libreria libreria_selected = null;
    public Fascicolo fascicolo_selected = null;
    public boolean likeElementSelected;
    public ArrayList<Recensione> recensioniConCommento = new ArrayList<>();
    public ArrayList<Possesso> possessoLLibreria = new ArrayList<>();
    public ArrayList<Possesso> possessoSLibreria = new ArrayList<>();
    public ArrayList<Possesso> possessoFLibreria = new ArrayList<>();
    public ArrayList<String> titoloLibriLibreria = new ArrayList<>();
    public ArrayList<String> titoloSerieLibreria = new ArrayList<>();
    public ArrayList<Fascicolo> fascicoliLibreria = new ArrayList<>();
    public ArrayList<Collana> listaCollane = getCollane();
    public String doi_selected = "";
    public int anno_pubblicazione = 0;
    public String nome_articolo = "";

    public int screenWidth = 0;
    public int screenHeight = initDimension();
    public int titleImpactSize = getFontTitleImpactSize();
    public Font titleImpact = new Font("Impact", Font.PLAIN, titleImpactSize);
    public int fontSize = getFontSize();
    public Font baseFontSize = new Font("Segoe UI", Font.PLAIN, fontSize);
    public Font impactFontSize = new Font("Impact", Font.PLAIN, fontSize);
    public Font textFieldFont = new Font("Berlin Sans FB", Font.PLAIN, fontSize-2);
    public Controller(){
    }

    // UTENTE //
    public void aggiungiUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){ //aggiunge un nuovo utente nel DB e in memoria
        utente = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA); //registra il nuovo utente in memoria

        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.addUtenteDB(email, nome, cognome, username, password, dataNascita, partitaIVA);   //registra il nuovo utente nel DB
    }//fine aggiungiUtente

    public int validaUtente(String userEmail, String password){ //conta il numero di utenti registrati con username o email 'userMail' e con password 'password'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        return u.validaUtenteDB(userEmail, password);   //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
    }//fine validaUtente

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
    }//fine setUtente

    public int[] validaModUtente(String email, String username, String pIva){   //controlla se delle modifiche effettuate dall'utente sono corrette, verificando che l'email 'email', l'username 'uername' e/o la partita IVA 'pIva' non siano già state utilizzate da altri utenti
        int[] error = {0, 0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        if(!email.equals(utente.email)){    //controlla se l'email è stata modificata
            error[0] = u.validaModEmailDB(email);   //mette in 'error[0]' il numero di utenti con 'email' trovati nel DB
        }

        if(!username.equals(utente.username)){  //controlla se l'username è stato modificato
            error[1] = u.validaModUsernameDB(username); //mette in 'error[1]' il numero di utenti con 'username' trovati nel DB
        }

        if(!pIva.equals(utente.partitaIVA)){    //controlla se la partita IVA è stata modificata
            if(utente.verifyPartitaIVA(pIva) == false){ //controlla se il formaro di 'pIva' è corretto
                error[3] = 1;   //mette in 'error[3]' 1
            } else {
                error[2] = u.validaModPIVADB(pIva); //mette in 'error[2]' il numero di utenti con 'pIva' trovati nel DB
            }
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        return error;
    }//fine validaModUtente

    public int[] validaInsUtente(String email, String username, String pIva){   //controlla se l'email 'email', l'username 'uername' e/o la partita IVA 'pIva' non siano già state utilizzate da altri utenti
        int[] error = {0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        error[0] = u.validaModEmailDB(email);   //mette in 'error[0]' il numero di utenti con 'email' trovati nel DB

        error[1] = u.validaModUsernameDB(username); //mette in 'error[1]' il numero di utenti con 'username' trovati nel DB

        error[2] = u.validaModPIVADB(pIva); //mette in 'error[2]' il numero di utenti con pIva' trovati nel DB

        u.chiudiConnessione();  //chiude la connessione al DB

        return error;
    }//fine validaInsUtente

    public boolean verificaPartitaIVA(String pIva){ //verifica se il formato della partita IVA 'pIva' è corretto
        if (pIva.length() == 0){    //controlla se 'pIva' è lunga 0
            return true;
        }

        if (pIva.length() != 11){   //controlla se 'pIva' non è lunga 11
            return false;
        }

        for(int i = 0; i < pIva.length(); i++){ //scorre 'pIva'
            if(pIva.charAt(i) < '0' || pIva.charAt(i) > '9'){   //controlla se l'i-esimo carattere non è numerico
                return false;
            }
        }

        return  true;
    }//fine verificaPartitaIVA

    public boolean verificaNomeCognome(String nc){  //verifica se il formato del nome o cognome 'nc' è corretto
        if (nc.length() == 0){  //controlla se 'nc' è lungo 0
            return false;
        }

        for(int i = 0; i < nc.length(); i++){   //scorre 'nc'
            if(String.valueOf(nc.charAt(i)) == "'"){    //controlla se l'i-esimo carattere è "'"
                while(String.valueOf(nc.charAt(i)) == "'"){ //scorre 'nc' fino a quando l'i-esimo carattere non è "'"
                    i++;    //incrementa il contatore
                }
            }

            if(i < nc.length()) {   //controlla se non è stato scorso 'nc'
                if (!((nc.charAt(i) >= 'a' && nc.charAt(i) <= 'z') || (nc.charAt(i) >= 'A' && nc.charAt(i) <= 'Z'))){   //controlla se l'i-esimo carattere non è alfabetico
                    return false;
                }
            }
        }

        return  true;
    }//fine verificaNomeCognome

    public String changeEmail (String email){   //convete i caratteri maiuscoli di 'email' in minuscoli
        for(int i = 0; i < email.length(); i++){    //scorre 'email'
            if(email.charAt(i) < 'A' || email.charAt(i) > 'Z'){ //controlla se l'i-esimo carattere è maiuscolo
                email.replace(email.charAt(i), (char) (email.charAt(i) + 32));  //converte l'i-esimo carattere in minuscolo
            }
        }

        return email;
    }   //fine changeEmail

    public boolean verificaEmail(String email){ //verifica se il formato dell'email 'email' è corretto
        if (email.length() <= 2){   //controlla se 'email' è lunga meno di 3
            return false;
        }

        if (!email.contains("@") || !email.contains(".")){  //controlla se 'mail' i caratteri "@" e "."
            return false;
        }

        int index1 = 0, index2 = 0; //indici di "@" e '.'

        index1 = email.indexOf("@");    //inizializza 'index1' con la posizione del primo "@" in 'email'
        index2 = email.lastIndexOf(".");    //inizializza 'index2' con la posizione dell'ultimo "." in 'email'

        if (index1 > index2){   //controlla se 'email' non ha un "." dopo il "@"
            return false;
        }

        if (email.substring(0, index1) == null || email.substring(0, index1) == ""){    //controlla se 'email' non ha caratteri prima di "@"
            return false;
        }

        if (email.length() < index1+2){ //controlla se 'email' non ha più di un carattere dopo il "@"
            return false;
        }

        if (email.substring(index1+1, index2) == null || email.substring(index1+1, index2) == ""){  //controlla se 'email' contiene la sottostringa "@."
            return false;
        }

        if (email.length() < index2+2){ //controlla se 'email' non ha più di un carattere dopo il "."
            return false;
        }

        if (email.substring(index2+2, email.length()) == null || email.substring(index2+1, email.length()) == ""){  //controlla se 'email' non ha caratteri dopo il "."
            return false;
        }

        for(int i = 0; i < email.length(); i++){    //scorre 'email'
            if(!((email.charAt(i) >= '0' && email.charAt(i) <= '9') || (email.charAt(i) >= '@' && email.charAt(i) <= 'Z') || (email.charAt(i) >= 'a' && email.charAt(i) <= 'z') || (email.charAt(i) == '.'))){  //controlla se l'i-esimo carattere non nè alfanumerico e nè "@" o "."
                return false;
            }
        }

        return  true;
    }//fine verificaEmail

    public boolean verificaPassword(String password){   //verifica se il formato della password 'password' è corretto
        if (password.length() < 8){ //controlla se 'password' è lunga meno di 8
            return false;
        }

        int numeri = 0; //numero di caratteri numerici contenuti in 'password'
        int specialChar = 0;    //numero di caratteri speciali contenuti in 'password'
        int capsChar = 0;   //numero di caratteri maiuscoli contenuti in 'password'

        for(int i = 0; i < password.length(); i++){ //scorre 'password'
            if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){ //verifica se l'i-esimo carattere è numerico
                numeri++;   //incrementa 'numeri'
            }

            if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'){ //verifica se l'i-esimo carattere è alfabetico maiuscolo
                capsChar++; //incrementa 'capsChar'
            }

            if(!((password.charAt(i) >= '0' && password.charAt(i) <= '9') || (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') || (password.charAt(i) >= 'a' && password.charAt(i) <= 'z'))){    //controlla se l'i-esimo carattere è speciale
                specialChar++;  //incrementa 'specialChar'
            }
        }

        if (numeri == 0 || specialChar == 0 || capsChar == 0){  //controlla se non sono stati inseriti caratteri numerici, maiuscoli o speciali
            return false;
        }

        return  true;
    }//fine verificaPassword

    public boolean verificaISBN(String isbn){   //verifica se il formato dell'ISBN 'isbn' è corretto
        if (isbn.length() != 17){   //conotrolla se 'isbn' non è lungo 17
            return false;
        }

        for(int i = 0; i < isbn.length(); i++){ //scorre 'isbn'
            if((isbn.charAt(i) < '0' || isbn.charAt(i) > '9') && (isbn.charAt(i) != '-')){  //controlla se l'i-esimo carattere non nè numerico e nè un "-"
                return false;
            }
        }

        return  true;
    }//fine verificaISBN

    public boolean verificaISSN(String issn){   //verifica se il formato dell'ISSN 'issn' è corretto
        if (issn.length() < 9){ //controlla se 'issn' è lunga meno di 9
            return false;
        }

        for(int i = 0; i < issn.length(); i++){ //scorre 'issn'
            if((issn.charAt(i) < '0' || issn.charAt(i) > '9') && (issn.charAt(i) != '-')){  //controlla se l'i-esimo carattere non nè numerico e nè un "-"
                return false;
            }
        }

        return  true;
    }//fine verificaISSN

    public void modUtente(String email, String nome, String cognome, String username, String password, String partitaIVA){  //modifica i dati dell'utente
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.modUtenteDB(email, nome, cognome, username, password, partitaIVA, utente.username);   //modifica i dati dell'utente nel DB

        utente.setEmail(email); //imposta l'email dell'utente
        utente.setNome(nome);   //imposta il nome dell'utente
        utente.setCognome(cognome); //imposta il cognome dell'utente
        utente.setUsername(username);   //imposta l'username dell'utente
        utente.setPassword(password);   //imposta la password dell'utente
        utente.setPartitaIva(partitaIVA);   //imposta la partita IVA dell'utente
    }//fine modUtente

    public String getUsername(){    //ritorna l'username dell'utente
        return utente.getUsername();
    }//fine getUsername

    public String getNome(){    //ritorna il nome dell'utente
        return utente.getNome();
    }//fine getNome

    public String getCognome(){ //ritorna il cognome dell'utente
        return utente.getCognome();
    }//fine getCognome

    public String getEmail(){   //ritorna l'email dell'utente
        return utente.getEmail();
    }//fine getEmail

    public String getPassword(){    //ritorna la password dell'utente
        return utente.getPassword();
    }//fine getPassword

    public String getPartitaIva(){  //ritorna la partita IVA dell'utente
        return utente.getPartitaIVA();
    }//fine getPartitaIva

    public Utente getUtente(String username){   //ritorna un Utente con i dati dell'utente 'username'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        Utente user = null;
        ResultSet rs = u.getUtenteDB(username); //cerca i dati dell'utente 'username' nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i dati dell'utente
                user = new Utente(rs.getString("username"), rs.getString("passwordu"), rs.getString("email"), rs.getString("nome"), rs.getString("Cognome"), rs.getString("datanascita"), rs.getString("partitaiva"));  //inizializza 'user'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        return user;
    }//fine getUtente

    // LIBRO //
    public boolean creaLibro(String isbn, String titolo, String genere, String lingua, String editore,String dp){   //aggiunge un nuovo libro nel DB e in memoria
        LibroDAO l = new LibroImplementazionePostgresDAO();
        boolean presenzaLibro = l.creaLibroDB(isbn, titolo, genere, lingua, editore, dp);   //se non esiste già, inserisce un nuovo libro nel DB

        nuovoLibro = new Libro(isbn, genere, editore, lingua, titolo, Date.valueOf(dp));    //inizializza 'nuovoLibro'
        return presenzaLibro;
    }//fine creaLibro

    public ArrayList<Libro> getLibri() {   //ritorna i dati di tutti i libri nel DB
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = l.getLibriDB();  //cerca i dati di tutti i libri nel DB
        ArrayList<Libro> libri = new ArrayList<Libro>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri
                ArrayList<Autore> autori = getAutoriLibro(rs.getString("isbn")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'

                libri.add(new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), autori ,rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione al DB

        return libri;
    }//fine getLibri

    public void getLibri(String collana) {   //inserisce tutti i libri della collana 'collana' in 'listaLibriCollana'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = l.getLibriDB(collana);  //cerca i dati di tutti i libri della collana 'collana' nel DB
        ArrayList<Libro> libri = new ArrayList<Libro>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                ArrayList<Autore> autori = getAutoriLibro(rs.getString("isbn")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'
                libri.add(new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), autori ,rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione al DB

        listaLibriCollana = libri;
    }//fine getLibri

    public void getLibriSerie(String isbnSerie){    //ritorna tutti i libri della serie con ISBN 'isbnSerie'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ArrayList<Libro> libri = new ArrayList<>(); //contiene i libri della serie
        ResultSet rs = l.getLibriSerieDB(isbnSerie);    //cerca i libri della serie trovati nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri della serie
                for(Libro libro: listaLibri){   //scorre la lista dei libri
                    if (libro.isbn.equals(rs.getString("libro"))){  //controlla se 'libro' appartiene alla serie
                        libri.add(libro);   //aggiunge 'libro' in 'libbri'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        l.chiudiConnessione();  //chiude la connessione al DB

        listaLibriSerie = libri;    //aggiorna 'listaLibriSerie'
    }//fine getLibriSerie

    public void getInfoLibriPreferiti(){    //inserisce in 'possessolPreferiti' i dati dei libri preferiti dell'utente e in 'librerieLibriPreferiti' i dati delle librerie che li possiedono
        getLibriISBNPreferiti();    //inizializza 'libriISBNPreferiti' con gli ISBN dei libri preferiti dell'utente

        LibroDAO l = new LibroImplementazionePostgresDAO();
        ResultSet rs = null;

        libriTitoloPreferiti.clear();   //svuota 'libriTitoloPreferiti'
        possessolPreferiti.clear(); //svuota 'possessolPreferiti'
        librerieLibriPreferiti.clear(); //svuota 'librerieLibriPreferiti'

        for (int i = 0; i< libriISBNPreferiti.size(); i++){ //scorre 'libriISBNPreferiti'
            rs = l.getInfoLibriPreferitiDB(libriISBNPreferiti.get(i));  //cerca i dati dell'i-esimo libro e delle librerie che lo possiedono

            try {
                while(rs.next()){    //scorre il ResultSet 'rs'
                    libriTitoloPreferiti.add(libriISBNPreferiti.get(i) + " - " + rs.getString("titolo"));   //aggiunge il titolo dell'i-esimo libro in 'libriTitoloPreferiti'

                    Libro libro = new Libro(rs.getString("isbn"),rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), rs.getString("titolo") ,rs.getDate("datapubblicazione"));
                    Libreria libreria = new Libreria(rs.getString("nome"),rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"));

                    possessolPreferiti.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), libro, libreria));    //aggiunge un nuovo possesso in 'possessolPreferiti'
                    librerieLibriPreferiti.add(libreria);   //aggiunge l'i-esima libreria in 'librerieLibriPreferiti'
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
        }

        l.chiudiConnessione();  //chiude la connessione al DB
    }

    public Date getDataLibro(){ //ritorna la data del libro selezionato
        LibroDAO l = new LibroImplementazionePostgresDAO();
        return l.getDataLibroDB(isbn_selected);
    }//fine getDataLibro

    public Date getDataLibro(String isbn){  //ritorna la data del libro selezionato con ISBN 'isbn'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        return l.getDataLibroDB(isbn);
    }//fine getDataLibro

    public void eliminaLibro(){ //elimina il nuovo libro
        LibroDAO l = new LibroImplementazionePostgresDAO();
        l.eliminaLibroDB(nuovoLibro.isbn);
    }//fine eliminaLibro

    // COLLANA //
    public ArrayList<String> getCollanaNome(){  //ritorna tutti i nomi delle collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        return c.getCollanaNomeDB();
    }//fine getCollanaNome

    public ArrayList<Collana> getCollane(){ //ritorna tutte le collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        ArrayList<Collana> collane = new ArrayList<>(); //contiene le collane
        ResultSet rs = c.getCollaneDB();    //cerca i dati di tutte le collane

        try {
            while (rs.next()){  //scorre il ResultSet 'rs'
                getLibri(rs.getString("nome")); //inserisce tutti i libri della collana corrente in 'listaLibriCollana'
                collane.add(new Collana(rs.getString("caratteristica"), rs.getString("nome"), rs.getString("issn"), listaLibriCollana));    //inserisce un nuova collana in 'collane'
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione al DB

        return collane;
    }//fine getCollane

    public void removeLibroFromCollana(String nomeCollana){ //rimuove il libro selezionato dalla collana 'nomeCollana'
        CollanaDAO c = new CollanaImplementazionePostgresDAO();

        c.removeLibroFromCollanaDB(isbn_selected, nomeCollana); //rimuove il libro con ISBN 'isbn_selected' dalla collana 'nomeCollana'

        listaCollane = getCollane();    //aggiorna 'listacollane'
    }//fine removeLibroFromCollana

    public void addLibroInCollana(String nomeCollana){  //aggiunge il libro selezionato nella collana 'nomeCollana'
        CollanaDAO c = new CollanaImplementazionePostgresDAO();

        c.addLibroInCollanaDB(isbn_selected, nomeCollana);  //aggiunge il libro con ISBN 'isbn_selected' dalla collana 'nomeCollana'

        listaCollane = getCollane();    //aggiorna 'listacollane'
    }//fine addLibroFromCollana

    public boolean creaCollana(String nome, String caratteristica, String issn){    // ritorna "true" se crea una nuova collana 'nome' con 'caratteristica' e 'issn', altrimenti ritorna "false"
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        boolean creazione = c.creaCollanaDB(nome, caratteristica, issn);    //segnala se è stata creata la nuova collana

        if (creazione == false){    //controlla se non
            c.chiudiConnessione();  //chiude la connessione al DB
            return false;
        }

        addLibroInCollana(nome);    //inserisce il libro selezionato nella nuova collana

        return creazione;
    }//fine creaCollana

    // AUTORE //
    public void aggiungiAutoreLibro(String nome, String cognome, String nazionalita, String dn){    //aggiunge un nuovo autore al libro selezionato e li associa nel DB
        AutoreDAO a = new AutoreImplementazionePostgresDAO();

        a.aggiungiAutoreLibroDB(nome, cognome, nazionalita, dn, nuovoLibro.isbn);   //aggiunge un nuovo autore al nuovo libro nel DB

        if(!dn.isBlank()){  //controlla se non è stata inserita la data di nascita del nuovo autore
            nuovoLibro.autori.add(new Autore(nome, cognome, nazionalita, Date.valueOf(dn)));    //aggiunge il nuovo autore al nuovo libro
        } else{
            nuovoLibro.autori.add(new Autore(nome, cognome, nazionalita, null));    //aggiunge il nuovo autore al nuovo libro
        }
    }//fine aggiungiAutoreLibro

    public void aggiungiAutoreArticolo(String nome, String cognome, String nazionalita, String dn){ //aggiunge un nuovo autore al nuovo articolo e li associa nel DB
        AutoreDAO a = new AutoreImplementazionePostgresDAO();

        a.aggiungiAutoreArticoloDB(nome, cognome, nazionalita, dn, nuovoArticoloScientifico.doi);   //aggiunge un nuovo autore al nuovo articolo nel DB

        if(!dn.isBlank()){  //controlla se non è stata inserita la data di nascita del nuovo autore
            nuovoArticoloScientifico.autori.add(new Autore(nome, cognome, nazionalita, Date.valueOf(dn)));  //aggiunge il nuovo autore al nuovo articolo
        } else {
            nuovoArticoloScientifico.autori.add(new Autore(nome, cognome, nazionalita, null));  //aggiunge il nuovo autore al nuovo articolo
        }
    }//fine aggiungiAutoreArticolo

    public ArrayList<Autore> getAutoriLibro(String isbn){    //ritorna gli autori del libro con ISBN 'isbn'
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet rs = a.getAutoriLibroDB(isbn); //ResultSet contenente tutti gli autori del libro con ISBN 'isbn'
        ArrayList<Autore> autori = new ArrayList<Autore>(); //cerca tutti gli autori del libro con ISBN 'isbn'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente gli autori
                autori.add(new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("nazionalita"), rs.getDate("datanascita")));  //aggiunge un nuovo autore in 'autori'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return autori;
    }//fine getAutoriLibro

    public ArrayList<Autore> getAutoriArticolo(String doi){    //ritorna gli autori dell'articolo con DOI 'doi'
        AutoreDAO a = new AutoreImplementazionePostgresDAO();
        ResultSet rs = a.getAutoriArticoloDB(doi); //cerca tutti gli autori dell'articolo con DOI 'doi'
        ArrayList<Autore> autori = new ArrayList<Autore>(); //contiene tutti gli autori dell'articolo con DOI 'doi'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente gli autori
                autori.add(new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("nazionalita"), rs.getDate("datanascita")));  //aggiunge un nuovo autore in 'autori'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return autori;
    }//fine getAutoriArticolo

    public ArrayList<String> allAutoriLibri(){  //ritorna tutti gli autori di ogni libro
        int aut = 0;    //numero di autori del libro corrente
        String linkString = ""; //nomi e cognomi di tutti gli autori del libro corrente
        ArrayList<String> totAutoreList = new ArrayList<>();    //contie gli autori

        for (Libro l: listaLibri) {    //scorre la lista dei libri
            aut = 0;    //azzera 'aut'

            for (Autore a: l.autori) {  //scorre tutti gli autori del libro 'l'
                if (aut == 0){  //controlla se si sta inserendo il primo autore
                    linkString = a.nome + " " + a.cognome;    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                } else{
                    linkString = linkString + " \n" + a.nome + " " + a.cognome; //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo
                }

                aut++;  //incrementa il numero di autori di 'l'
            }

            totAutoreList.add(linkString);  //inserisce 'linkString' in 'totAutoreList'
        }

        return  totAutoreList;
    }//fine allAutoriLibri

    public ArrayList<String> allAutoriDistintiLibri(){  //ritorna tutti gli autori di tutti i libri evitando duplicati
        ArrayList<String> distinctAutoreList = new ArrayList<>();   //contiene gli autori
        String linkString = ""; //nome e cognome dell'autore corrente del libro corrente

        for (Libro l: listaLibri) {    //scorre la lista dei libri
            for (Autore a : l.autori) { //scorre gli autori del libro 'l'
                linkString = a.nome + " " + a.cognome;  //concatena in 'linkString' il nome e il cognome dell'autore 'a'

                if (!distinctAutoreList.contains(linkString)) { //controlla se 'distinctAutoreList' non contiene 'linkString'
                    distinctAutoreList.add(linkString);  //inserisce 'linkString' in 'distinctAutoreList'
                }
            }
        }

        return distinctAutoreList;
    }//fine allAutoriuDistintiLibri

    public String allAutoriArticolo(ArrayList<Autore> autori){  //ritorna una stringa contenente i nomi e i cognomi di tutti gli autori in 'aUTORI'
        String a = "";  //contiene gli autori

        for (Autore au: autori){    //scorre 'autori'
            a = a + au.nome + " " + au.cognome + " ";   //concatena il nome e il cognome di 'au' in 'a'
        }

        return a;
    }//fine allAutoriArticolo

    // LIBRERIA //
    public ResultSet getDisponibilita(){    //ritorna un ResultSet con le disponibilita del libro con ISBN 'isbn_selected'
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ResultSet rs = l.disponibilitaDB(isbn_selected);    //cerca tutte le dipsonibilità del libro selezionato

        return rs;
    }//fine getDisonibilita

    public ArrayList<String> getDisponibilitaLibreria(){    //ritorna i nomi di tutte le librerie che possiedono il libro selezionato
        ArrayList<String> libreria = new ArrayList<>(); //contiene i nomi delle librerie
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                libreria.add(rs.getString("nome")); //aggiunge un nuovo nome in 'libreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return libreria;
    }//fine getDisponibilitaLibreria

    public ArrayList<Integer> getDisponibilitaQuantita(){   //ritorna le quantità disponibili del libro selezionato
        ArrayList<Integer> quantita = new ArrayList<>();    //contiene le quantità disponibili
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                quantita.add(rs.getInt("quantita"));    //aggiunge una nuova quantità in 'quantita'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return quantita;
    }//fine getDisponibilitaQuantita

    public ArrayList<String> getDisponibilitaFruizione(){  //ritorna le modalità di fruizione disponibili del libro selezionato
        ArrayList<String> fruizione = new ArrayList<>();    //contiene le modalità di fruizione disponibili
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                fruizione.add(rs.getString("fruizione")); //aggiunge una nuova modalità di fruizione in 'fruizione'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return fruizione;
    }//fine getDisponibilitaFruizione

    public ArrayList<String> getDisponibilitaIndirizzo(){   //ritorna gli indirizzi delle librerie che possiedono il libro selezionato
        ArrayList<String> indirizzo = new ArrayList<>();    //contiene gli indirizzi delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                indirizzo.add(rs.getString("indirizzo"));   //aggiunge un nuovo indirizzo in 'indirizzo'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return indirizzo;
    }//fine getDisponibilitaIndirizzo

    public ArrayList<String> getDisponibilitaSitoWeb(){ //ritorna i siti web delle librerie che possiedono il libro selezionato
        ArrayList<String> sitoWeb = new ArrayList<>();  //contiene i siti web delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                sitoWeb.add(rs.getString("sitoweb"));   //aggiunge un nuovo sito web in 'sitoWeb'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return sitoWeb;
    }//fine getDisponibilitaSitoWeb

    public ArrayList<String> getDisponibilitaNumeroTelefono(){  //ritorna i numeri telefonici delle librerie che possiedono il libro selezionato
        ArrayList<String> nTel = new ArrayList<>(); //contiene i numeri telefonici delle librerie che possiedono il libro selezionato
        ResultSet rs = getDisponibilita();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                nTel.add(rs.getString("numerotelefonico")); //aggiunge un nuovo numero telefonico in 'nTel'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return nTel;
    }//fine getDisponibilitaNumeroTelefono

    public ResultSet getDisponibilitaFascicolo(){    //ritorna un ResultSet con le disponibilita del fascicolo selezionato
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ResultSet rs = l.disponibilitaFascicoloDB(fascicolo_selected.numero, fascicolo_selected.rivista.titolo);    //cerca tutte le dipsonibilità del fascicolo selezionato

        return rs;
    }//fine getDisponibilitaFascicolo

    public ArrayList<String> getDisponibilitaLibreriaFascicolo(){    //ritorna i nomi di tutte le librerie che possiedono il fascicolo selezionato
        ArrayList<String> libreria = new ArrayList<>(); //contiene i nomi delle librerie
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                libreria.add(rs.getString("nome")); //aggiunge un nuovo nome in 'libreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return libreria;
    }//fine getDisponibilitaLibreriaFascicolo

    public ArrayList<Integer> getDisponibilitaQuantitaFascicolo(){   //ritorna le quantità disponibili del fascicolo selezionato
        ArrayList<Integer> quantita = new ArrayList<>();    //contiene le quantità disponibili
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                quantita.add(rs.getInt("quantita"));    //aggiunge una nuova quantità in 'quantita'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return quantita;
    }//fine getDisponibilitaQuantitaFascicolo

    public ArrayList<String> getDisponibilitaFruizioneFascicolo(){  //ritorna le modalità di fruizione disponibili del fascicolo selezionato
        ArrayList<String> fruizione = new ArrayList<>();    //contiene le modalità di fruizione disponibili
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                fruizione.add(rs.getString("fruizione")); //aggiunge una nuova modalità di fruizione in 'fruizione'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return fruizione;
    }//fine getDisponibilitaFruizioneFascicolo

    public ArrayList<String> getDisponibilitaIndirizzoFascicolo(){   //ritorna gli indirizzi delle librerie che possiedono il fascicolo selezionato
        ArrayList<String> indirizzo = new ArrayList<>();    //contiene gli indirizzi delle librerie che possiedono il fascicolo selezionato
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                indirizzo.add(rs.getString("indirizzo"));   //aggiunge un nuovo indirizzo in 'indirizzo'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return indirizzo;
    }//fine getDisponibilitaIndirizzoFascicolo

    public ArrayList<String> getDisponibilitaSitoWebFascicolo(){ //ritorna i siti web delle librerie che possiedono il fascicolo selezionato
        ArrayList<String> sitoWeb = new ArrayList<>();  //contiene i siti web delle librerie che possiedono il fascicolo selezionato
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                sitoWeb.add(rs.getString("sitoweb"));   //aggiunge un nuovo sito web in 'sitoWeb'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return sitoWeb;
    }//fine getDisponibilitaSitoWebFascicolo

    public ArrayList<String> getDisponibilitaNumeroTelefonoFascicolo(){  //ritorna i numeri telefonici delle librerie che possiedono il fascicolo selezionato
        ArrayList<String> nTel = new ArrayList<>(); //contiene i numeri telefonici delle librerie che possiedono il fascicolo selezionato
        ResultSet rs = getDisponibilitaFascicolo();  //cerca le disponibilità trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs'
                nTel.add(rs.getString("numerotelefonico")); //aggiunge un nuovo numero telefonico in 'nTel'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return nTel;
    }//fine getDisponibilitaNumeroTelefonoFascicolo

    public void getLibrerieUtente(){    //ritorna tutte le librerie dell'utente
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ArrayList<Libreria> librerie = new ArrayList<>();   //contiene le librerie
        ResultSet rs = l.getLibrerieUtenteDB(utente.username);  //cerca le libreri dell'utente trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                librerie.add(new Libreria(rs.getString("nome"), rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"), utente)); //aggiunge una nuova libreria in 'librerie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        librerieUtente = librerie;  //inizializza 'librerieUtente'
    }//fine getLibrerieUtente

    public boolean verificaNumeroTelefonicoLibreria(String nt){ //verifica se il formato del numero telefonico 'nt' è corretto
        if (nt.length() != 10){ //controlla se 'nt' non è lungo 10
            return false;
        }

        for(int i = 0; i < nt.length(); i++){   //scorre 'nt'
            if(nt.charAt(i) < '0' || nt.charAt(i) > '9'){   //controlla se l'i-esimo carattere non è numerico
                return false;
            }
        }

        return true;
    }//fine verificaNumeroTelefonicoLibreria

    public boolean presenzaNumeroTelefonicoLibreria(String nt){ //controlla se il numero telefonico 'nt' è già presente nel DB
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        return l.presenzaNumeroTelefonicoLibreriaDB(nt);
    }//fine presenzaNumeroTelefonicoLibreria

    public boolean presenzaLibreria(String nome, String sw, String indirizzo){  //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        return l.presenzaLibreriaDB(nome, sw, indirizzo);
    }//fine presenzaLibreria

    public void addLibreria(String nome, String nt, String sw, String indirizzo){   //aggiunge una nuova libreria
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();

        l.addLibreriaDB(nome, nt, sw, indirizzo, utente.username);  //aggiunge la nuova libreria nel DB
        librerieUtente.add(new Libreria(nome, nt, indirizzo, sw, utente));  //aggiunge la nuova libreria in 'librerieUtente'
    }//fine addLibreria

    public void removeLibreria(int index){  //rimuove l'index-esima libreria di 'librerieUtente'
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();

        l.removeLibreriaDB(librerieUtente.get(index).numeroTelefonico); //rimuove la libreria dal DB
        librerieUtente.remove(index);   //rimuove la libreria da 'librerieUtente'
    }//fine removeLibreria

    // PRESENTAZIONE //
    public void getPresentazione(){    //ritorna i dati di tutte le presentazioni del libro con ISBN 'isbn_selected'
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        ArrayList<Presentazione> presentazioni = new ArrayList<>(); //contiene tutte le presentazioni del libro selezionato
        ResultSet rs = p.getPresentazioneDB(isbn_selected); //cerca i dati di tutte le presentazioni del libro selezionato
        Libro libroSelezionato = null;  //libro selezionato

        for (Libro l: listaLibri){  //scorre la lista dei libri
            if(l.isbn.equals(isbn_selected)){   //controlla se 'l' è il libro selezionato
                libroSelezionato = l;  //assegna 'l' a 'libroSelezionato'
            }
        }

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le presentazioni del libro selezionato
                presentazioni.add(new Presentazione(rs.getString("luogo"), rs.getString("struttura"), rs.getDate("datap"), rs.getTime("ora").toString(), libroSelezionato));   //inserisce una nuova presentazione in 'presentazioni'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        p.chiudiConnessione();  //chiude la connessione al DB

        listaPresentazioni = presentazioni; //aggiorna 'listaPresentazioni'
    }//fine getPresentazione

    public boolean addPresentazione(String struttura, String luogo, String data, String orario){    //se aggiunge una nuova presentazione ritorna "true", altrimenti ritorna "false"
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        return p.addPresentazioneDB(struttura, luogo, data, orario, isbn_selected);
    }//fine addPresentazione


    // SERIE //
    public ArrayList<Serie> getSerie() {   //ritorna i dati di tutte le serie nel DB
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ResultSet rs = s.getSerieDB();  //cerca i dati di tutte le serie nel DB
        ArrayList<Serie> serie = new ArrayList<Serie>();    //contiene tutte le serie

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' in 'listaLibriSerie'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), listaLibriSerie, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();  //chiude la connessione al DB

        return serie;
    }//fine getSerie

    public ArrayList<String> getSerieGeneri(){  //ritorna tutti i generi dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieGenereDB();
    }//fine getSerieGeneri

    public ArrayList<String> getSerieAutori(){  //ritorna tutti gli autori dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieAutoriDB();
    }//fine getSerieAutori

    public void getListaSerieGenere(String genere){ //ritorna una lista delle serie con libri del genere 'genere'
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ArrayList<Serie> serie = new ArrayList<>(); //contiene le serie con libri del genere 'genere'
        ResultSet rs = s.getListaSerieGenereDB(genere); //cerca le serie con libri del genere 'genere'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' nell'ArrayList 'listaLibriSerie'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), listaLibriSerie, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();  //chiude la connessione al DB

        listaSerieGenere = serie;   //inizializza 'listaSerieGenere'
    }//fine getListaSerieGenere

    public void getListaSerieAutore(String autore){ //ritorna una lista delle serie con libri dell'autore 'autore'
        SerieDAO s = new SerieImplementazionePostgresDAO();
        ArrayList<Serie> serie = new ArrayList<>(); //contiene le serie con libri dell'autore 'autore'
        ResultSet rs = s.getListaSerieAutoreDB(autore); //cerca le serie con libri dell'autore 'autore'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                getLibriSerie(rs.getString("isbn")); //inserisce i libri della serie corrente di 'rs' nell'ArrayList 'listaLibriSerie'
                serie.add(new Serie(rs.getString("isbn"), rs.getInt("nlibri"), listaLibriSerie, rs.getString("titolo"), rs.getDate("datapubblicazione")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        s.chiudiConnessione();   //chiude la connessione al DB

        listaSerieAutore = serie;   //inizializza 'listaSerieAutore'
    }//fine getListaSerieAutore

    public void getInfoSeriePreferiti(){    //inserisce in 'possessosPreferiti' i dati delle serie preferite dell'utente e in 'librerieSeriePreferiti' i dati delle librerie che le possiedono
        getSerieISBNPreferiti();    //inizializza 'serieISBNPreferiti' con gli ISBN delle serie preferite dell'utente

        SerieDAO s = new SerieImplementazionePostgresDAO();
        ResultSet rs = null;

        serieTitoloPreferiti.clear();   //svuota 'serieTitoloPreferiti'
        possessosPreferiti.clear(); //svuota 'serieTitoloPreferiti'
        librerieSeriePreferiti.clear(); //svuota 'serieTitoloPreferiti'

        for (int i = 0; i< serieISBNPreferiti.size(); i++){ //scorre 'serieISBNPreferiti'
            rs = s.getInfoSeriePreferitiDB(serieISBNPreferiti.get(i));  //cerca i dati dell'i-esima serie e delle librerie che la possiedono

            try {
                while(rs.next()){    //scorre il ResultSet 'rs'
                    serieTitoloPreferiti.add(serieISBNPreferiti.get(i) + " - " + rs.getString("titolo"));   //aggiunge il titolo dell'i-esima serie in 'serieTitoloPreferiti'

                    Serie serie = new Serie(rs.getString("isbn"),rs.getInt("nlibri") ,rs.getString("titolo"), rs.getDate("datapubblicazione"));
                    Libreria libreria = new Libreria(rs.getString("nome"),rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"));

                    possessosPreferiti.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), serie, libreria));    //aggiunge un nuovo possesso in 'possessosPreferiti'
                    librerieSeriePreferiti.add(libreria);   //aggiunge l'i-esima libreria in 'librerieLibriPreferiti'
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
        }

        s.chiudiConnessione();  //chiude la connessione al DB
    }//fine getInfoSeriePreferiti

    public boolean creaSerie(ArrayList<String> isbnList, String isbn, String titolo, String dp){    //se crea una nuova serie, ritorna "true", altrimenti ritorna "false"
        SerieDAO s = new SerieImplementazionePostgresDAO();
        boolean presenzaSerie = s.creaSerieDB(isbnList, isbn, titolo, dp);

        nuovoSerie = new Serie(isbn, isbnList.size(), titolo, Date.valueOf(dp));    //inizializza 'nuovaSerie'
        return presenzaSerie;
    }//fine creaSerie

    // RECENSIONE //
    public float valutazioneMediaLibro(){   //ritorna la media delle valutazioni del libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaLibroDB(isbn_selected);
    }//fine valutazioneMediaLibro

    public void likeLibro(){    //controlla se l'utente ha il libro selezionato tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeLibroDB(isbn_selected, utente.username);    //aggiorna 'likeElementSelected'
        return;
    }//fine likeLibro

    public void changeLikeLibro(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente il libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeLibroDB(likeElementSelected, isbn_selected, utente.username); //aggiorna 'likeElementSelected'
    }//fine changeLikeLibro

    public void addRecensioneLibro(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        r.addRecensioneLibroDB(valutazione, text, isbn_selected, utente.username);  //aggiunge la nuova recensione nel DB
        return;
    }//fine addRecensioneLibro

    public void allRecWithCommentLibro(){    //ritorna tutte le recensioni con testo fatte al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentLibroDB(isbn_selected);    //cerca tutte le recensioni fatte al libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }//fine allRecWithCommentLibro

    public float valutazioneMediaSerie(){   //ritorna la media delle valutazioni della serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaSerieDB(isbn_selected);
    }//fine valutazioneMediaSerie

    public void likeSerie(){    //controlla se l'utente ha la serie selezionata tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeSerieDB(isbn_selected, utente.username);    //aggiorna 'likeElementSelected'
        return;
    }//fine likeSerie

    public void changeLikeSerie(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente la serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeSerieDB(likeElementSelected, isbn_selected, utente.username); //aggiorna 'likeElementSelected'
    }//fine changeLikeSerie

    public void addRecensioneSerie(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente alla serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        r.addRecensioneSerieDB(valutazione, text, isbn_selected, utente.username);  //aggiunge la nuova recensione nel DB
        return;
    }//fine addRecensioneSerie

    public void allRecWithCommentSerie(){    //ritorna tutte le recensioni con testo fatte alla serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentSerieDB(isbn_selected);    //contiene tutte le recensioni fatte alla serie selezionata

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }//fine allRecWithCommentSerie

    public float valutazioneMediaFascicolo(){   //ritorna la media delle valutazioni del fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaFascicoloDB(fascicolo_selected.numero, fascicolo_selected.rivista.titolo);
    }//fine valutazioneMediaFascicolo

    public void likeFascicolo(){    //controlla se l'utente ha il fascicolo selezionato tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeFascicoloDB(fascicolo_selected.numero, fascicolo_selected.rivista.titolo, utente.username); //aggiorna 'likeElementSelected'
    }//fine likeFascicolo

    public void changeLikeFascicolo(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente il fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeFascicoloDB(likeElementSelected, fascicolo_selected.numero, fascicolo_selected.rivista.titolo, utente.username);  //aggiorna 'likeElementSelected'
    }//fine changeLikeFascicolo

    public void addRecensioneFascicolo(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        r.addRecensioneFascicoloDB(valutazione, text, fascicolo_selected.numero, fascicolo_selected.rivista.titolo, utente.username);
        return;
    }

    public void allRecWithCommentFascicolo(){    //ritorna tutte le recensioni con testo fatte al fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentFascicoloDB(fascicolo_selected.numero, fascicolo_selected.rivista.titolo);    //contiene tutte le recensioni fatte al libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }

    public void getLibriISBNPreferiti(){
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        libriISBNPreferiti = r.getLibriISBNPreferitiDB(utente.username);
    }

    public void getSerieISBNPreferiti(){
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        serieISBNPreferiti = r.getSerieISBNPreferitiDB(utente.username);
    }

    // RIVISTA //
    public ArrayList<Rivista> getRiviste() {   //ritorna i dati di tutte le serie nel DB
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        ResultSet rs = r.getRivisteDB();  //cerca i dati di tutte le serie nel DB
        ArrayList<Rivista> riviste = new ArrayList<Rivista>();    //contiene tutte le serie

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                riviste.add(new Rivista(rs.getString("issn"), rs.getString("titolo"), rs.getString("editore"), rs.getInt("annoPubblicazione"), rs.getString("nomer") + "#" + rs.getString("cognomer"), rs.getString("argomento")));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB

        return riviste;
    }

    public boolean creaRivista(String titolo, String issn, String argomento ,String nomeR, String cognomeR, String editore,int ap){
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        boolean presenzaRivista = r.creaRivistaDB(issn, titolo, argomento, nomeR, cognomeR, editore, ap);

        nuovaRivista = new Rivista(issn, titolo, editore, ap, nomeR +"#"+ cognomeR, argomento);
        return presenzaRivista;
    }

    public void eliminaRivista(){
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        r.eliminaRivistaDB(nuovaRivista.issn);
    }

    // FASCICOLO //
    public ArrayList<Fascicolo> getFascicoli(){
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = f.getFascicoliDB();  //cerca i dati di tutti i libri nel DB
        ArrayList<Fascicolo> fascicoli = new ArrayList<Fascicolo>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri
                for (int i = 0; i < listaRiviste.size(); i++){
                    if(listaRiviste.get(i).issn.equals(rs.getString("issn"))){
                        Rivista rivistaFascicolo = listaRiviste.get(i);

                        i = listaRiviste.size();

                        ArrayList<ArticoloScientifico> articoloScientifici = getArticoliScientifici(rivistaFascicolo.issn, rs.getInt("numero")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'

                        fascicoli.add(new Fascicolo(rs.getInt("numero"), rivistaFascicolo, articoloScientifici, rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        f.chiudiConnessione();  //chiude la connessione al DB

        return fascicoli;
    }

    public void getFascicoliRivista(String issn){
        ArrayList<Fascicolo> fascicoli = new ArrayList<>();

        listaFascicoliRivista.clear();

        for (Fascicolo f: listaFascicoli){
            if(f.rivista.issn.equals(issn)){
                listaFascicoliRivista.add(f);
            }
        }
    }

    public void getFascicoliPreferiti(){
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = f.getFascicoliDB(utente.username);  //cerca i dati di tutti i libri nel DB
        ArrayList<Fascicolo> fascicoli = new ArrayList<Fascicolo>();    //contiene tutti i libri

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri
                for (int i = 0; i < listaRiviste.size(); i++){
                    if(listaRiviste.get(i).issn.equals(rs.getString("issn"))){
                        Rivista rivistaFascicolo = listaRiviste.get(i);

                        i = listaRiviste.size();

                        ArrayList<ArticoloScientifico> articoloScientifici = getArticoliScientifici(rivistaFascicolo.issn, rs.getInt("numero")); //inserisce gli autori del libro corrente di 'rs' nell'ArrayList 'autori'

                        fascicoli.add(new Fascicolo(rs.getInt("numero"), rivistaFascicolo, articoloScientifici, rs.getDate("datapubblicazione")));   //inserisce un nuovo libro in 'libri'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        f.chiudiConnessione();  //chiude la connessione al DB

        fascicoliPreferiti = fascicoli;
    }

    public void selezionaFascicolo(int numero, String titolo){
        for(int i = 0; i < listaFascicoli.size(); i++){
            if(listaFascicoli.get(i).rivista.titolo.equals(titolo) && listaFascicoli.get(i).numero == numero) {
                fascicolo_selected = listaFascicoli.get(i);
                i = listaFascicoli.size();
            }

        }
    }

    public void getInfoFascicoliPreferiti(){
        getFascicoliPreferiti();
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = null;

        fascicoliTitoloPreferiti.clear();
        possessofPreferiti.clear();
        librerieFascicoliPreferiti.clear();

        for (int i = 0; i< fascicoliPreferiti.size(); i++){
            rs = f.getInfoFascicoliPreferitiDB(fascicoliPreferiti.get(i).rivista.issn, fascicoliPreferiti.get(i).numero);

            try {
                while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri della serie
                    fascicoliTitoloPreferiti.add(fascicoliPreferiti.get(i).rivista.titolo + " N°" + fascicoliPreferiti.get(i).numero);

                    Libreria libreria = new Libreria(rs.getString("nome"),rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"));

                    possessofPreferiti.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), fascicoliPreferiti.get(i), libreria));
                    librerieFascicoliPreferiti.add(libreria);
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
        }

        f.chiudiConnessione();
    }

    public boolean creaFascicolo(int numero, String data){
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        boolean presenzaFascicolo = f.creaFascicoloDB(numero, data, nuovaRivista.issn);

        nuovoFascicolo = new Fascicolo(numero, nuovaRivista, Date.valueOf(data));
        nuovoFascicolo.articoli = new ArrayList<ArticoloScientifico>();
        return presenzaFascicolo;
    }

    public void eliminaFascicolo(){
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        f.eliminaFascicoloDB(nuovoFascicolo.rivista.issn, nuovoFascicolo.numero);
    }

    // ARTICOLO_SCIENTIFICO //
    public ArrayList<ArticoloScientifico> getArticoliScientifici(String issn, int n){
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();
        ArrayList<ArticoloScientifico> articoloScientifici = new ArrayList<ArticoloScientifico>();
        ResultSet rs = as.getArticoliScientificiDB(issn, n);

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                articoloScientifici.add(new ArticoloScientifico(rs.getString("doi"), rs.getString("titolo"), rs.getInt("annoPubblicazione"), getAutoriArticolo(rs.getString("doi"))));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return  articoloScientifici;
    }

    public void getArticoliScientifici(){
        listaArticoli.clear();

        if(!listaFascicoli.isEmpty()) {
            for (int i = 0; i < listaFascicoli.size(); i++) {
                for (int j = 0; j < listaFascicoli.get(i).articoli.size(); j++) {
                    if (!listaArticoli.contains(listaFascicoli.get(i).articoli.get(j))) {
                        listaArticoli.add(listaFascicoli.get(i).articoli.get(j));
                    }
                }
            }
        }
    }

    public boolean creaArticolo(String titolo, int anno){
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();
        boolean presenzaArticolo = as.creaArticoloDB(titolo, anno, nuovoFascicolo.numero, nuovoFascicolo.rivista.issn);

        nuovoArticoloScientifico = new ArticoloScientifico(as.getDoiDB(titolo), titolo, anno);

        as.chiudiConnessione();

        return presenzaArticolo;
    }

    public void getAParticolo(){
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();

        anno_pubblicazione = as.getAPDB(doi_selected);
    }

    public void eliminaArticolo(){
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();

        as.eliminaArticoloDB(nuovoArticoloScientifico.doi);
    }

    // NOTIFICA //
    public int getNumeroNotificheNonLette(){
        NotificaDAO n = new NotificaImplementazionePostgresDAO();
        return n.getNumeroNotificheNonLetteDB(utente.username);
    }

    public void getNotificheUtente(){
        NotificaDAO n = new NotificaImplementazionePostgresDAO();
        ArrayList<Notifica> notifiche = new ArrayList<>();
        ResultSet rs = n.getNotificheUtenteDB(utente.username);

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                Serie serie = null;

                for (int i = 0; i < listaSerie.size(); i++){
                    if(listaSerie.get(i).isbn.equals(rs.getString("isbn"))){
                        serie = listaSerie.get(i);
                        i = listaSerie.size();
                    }
                }

                notifiche.add(new Notifica(rs.getString("testo"), rs.getInt("libreria"), rs.getDate("datainvio"), rs.getTime("orainvio").toString(), rs.getBoolean("lettura"), utente, serie));   //inserisce una nuova serie in 'serie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        listaNotifiche = notifiche;
    }

    public void rimuoviNotifica(String testo, String data, String ora){
        NotificaDAO n = new NotificaImplementazionePostgresDAO();

        n.rimuoviNotificaDB(testo, data, ora, utente.username);

        for (int i = 0; i < listaNotifiche.size(); i++){
            Notifica notifica = listaNotifiche.get(i);

            if (notifica.testo.equals(testo) && notifica.dataInvio.toString().equals(data) && notifica.oraInvio.toString().equals(ora)){
                listaNotifiche.remove(i);
                i = listaNotifiche.size();
            }
        }
    }

    public void visualizzaNotifica(String testo, String data, String ora){
        NotificaDAO n = new NotificaImplementazionePostgresDAO();

        n.visualizzaNotificaDB(testo, data, ora, utente.username);

        for (int i = 0; i < listaNotifiche.size(); i++){
            Notifica notifica = listaNotifiche.get(i);

            if (notifica.testo.equals(testo) && notifica.dataInvio.toString().equals(data) && notifica.oraInvio.toString().equals(ora)){
                listaNotifiche.get(i).lettura = true;
                i = listaNotifiche.size();
            }
        }
    }

    public boolean getLetturaNotifica(String testo, String data, String ora){
        for(Notifica n: listaNotifiche){
            if(n.testo.equals(testo) && n.dataInvio.toString().equals(data) && n.oraInvio.toString().equals(ora)){
                return n.lettura;
            }
        }

        return true;
    }

    // POSSESSO //
    public void getPossessoLibreria(){
        possessoLLibreria.clear();
        possessoSLibreria.clear();
        possessoFLibreria.clear();
        titoloLibriLibreria.clear();
        titoloSerieLibreria.clear();
        fascicoliLibreria.clear();

        getPossessoLLibreria();
        getPossessoSLibreria();
        getPossessoFLibreria();
    }

    public void getPossessoLLibreria(){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoLLibreriaDB(libreria_selected.nome, libreria_selected.sitoWeb, libreria_selected.indirizzo, utente.username);

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                Libro l = new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), rs.getString("titolo"), rs.getDate("datapubblicazione"));

                titoloLibriLibreria.add(l.isbn + " - " + l.titolo);
                possessoLLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), l, libreria_selected));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }
    }

    public void getPossessoSLibreria(){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoSLibreriaDB(libreria_selected.nome, libreria_selected.sitoWeb, libreria_selected.indirizzo, utente.username);

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                Serie s = new Serie(rs.getString("isbn"), rs.getInt("nlibri"), rs.getString("titolo"), rs.getDate("datapubblicazione"));

                titoloSerieLibreria.add(s.isbn + " - " + s.titolo);
                possessoSLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), s, libreria_selected));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }
    }

    public void getPossessoFLibreria(){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoFLibreriaDB(libreria_selected.nome, libreria_selected.sitoWeb, libreria_selected.indirizzo, utente.username);

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                Fascicolo f = new Fascicolo(rs.getInt("numero"), new Rivista(rs.getString("issn"), rs.getString("titolo"), rs.getString("editore"), rs.getInt("annopubblicazione"), rs.getString("nomer") + " " + rs.getString("cognomer"), rs.getString("argomento")) ,rs.getDate("datapubblicazione"));

                fascicoliLibreria.add(f);
                possessoFLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), f, libreria_selected));   //inserisce un nuovo libro in 'libri'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        p.chiudiConnessione();
    }

    public void modQuantitaLibro(String fruizione, int value){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.modQuantitaLibroDB(isbn_selected, libreria_selected.numeroTelefonico, fruizione, value);
    }

    public void modQuantitaFascicolo(String fruizione, int value){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.modQuantitaFascicoloDB(fascicolo_selected.rivista.issn, fascicolo_selected.numero,libreria_selected.numeroTelefonico, fruizione, value);
    }

    public boolean insertPossessoL(int quantita, String fruizione){
        boolean presenzaPossessoL = false;
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        return presenzaPossessoL = p.insertPossessoLDB(nuovoLibro.isbn, libreria_selected.numeroTelefonico, quantita, fruizione);
    }

    public boolean insertPossessoF(int quantita, String fruizione){
        boolean presenzaPossessoF = false;
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        return presenzaPossessoF = p.insertPossessoFDB(nuovoFascicolo.numero, nuovoFascicolo.rivista.issn, libreria_selected.numeroTelefonico, quantita, fruizione);
    }

    public void eliminaPossessoL(String fruizione){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.eliminaPossessoLDB(isbn_selected, libreria_selected.numeroTelefonico, fruizione);
    }

    public void eliminaPossessoF(String fruizione){
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.eliminaPossessoFDB(fascicolo_selected.rivista.issn, fascicolo_selected.numero, libreria_selected.numeroTelefonico, fruizione);
    }

    // CONFERENZA //
    public void getConferenzeArticolo(){    //ritorna i dati di tutte le presentazioni del libro con ISBN 'isbn_selected'
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        ArrayList<Conferenza> conferenze = new ArrayList<>(); //contiene tutte le presentazioni del libro selezionato
        ResultSet rs = c.getConferenzeArticoloDB(doi_selected); //cerca i dati di tutte le presentazioni del libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le presentazioni del libro selezionato
                conferenze.add(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine")));   //inserisce una nuova presentazione in 'Presentazioni'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione al DB

        listaConferenze = conferenze;
    }

    public void getConferenze(){
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        ArrayList<Conferenza> conferenze = new ArrayList<>(); //contiene tutte le presentazioni del libro selezionato
        ResultSet rs = c.getConferenzeDB(); //cerca i dati di tutte le presentazioni del libro selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le presentazioni del libro selezionato
                if (!conferenze.contains(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine"))) && anno_pubblicazione <= rs.getDate("datainizio").getYear()+1900) {
                    conferenze.add(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine")));   //inserisce una nuova presentazione in 'Presentazioni'
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione al DB

        listaAllConferenze = conferenze;
    }

    public boolean addConferenza(String struttura, String luogo, String datai, String dataf){
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        return c.addConferenzaDB(struttura, luogo, datai, dataf);
    }

    public boolean addEsposizione(String struttura, String luogo, String datai, String dataf){
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        return c.addEsposizioneDB(struttura, luogo, datai, dataf, doi_selected);
    }

    // FUNZIONI AGGIUNTIVE //
    public int initDimension(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.width;
        return (int) screenSize.getHeight();
    }

    public int getFontTitleImpactSize(){
        // Calcola la dimensione del font in base alle dimensioni dello schermo
        int fontSize = Math.min(screenWidth, screenHeight) / 33; // Modifica il coefficiente a seconda delle tue preferenze
        return fontSize;
    }

    public int getFontSize(){
        // Calcola la dimensione del font in base alle dimensioni dello schermo
        int fontSize = Math.min(screenWidth, screenHeight) / 50; // Modifica il coefficiente a seconda delle tue preferenze
        return fontSize;
    }

    public int calcolaAltezzaFont(Font font){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();
        int fontHeight = fm.getHeight();

        g2d.dispose();

        return fontHeight;
    }
}