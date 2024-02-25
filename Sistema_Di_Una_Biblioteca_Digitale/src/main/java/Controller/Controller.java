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

/**
 * The type Controller.
 */
public class Controller {
    /**
     * The Utente.
     */
    public Utente utente;
    /**
     * The Lista libri.
     */
    public ArrayList<Libro> listaLibri = getLibri();
    /**
     * The Nuovo libro.
     */
    public Libro nuovoLibro;
    /**
     * The Nuovo serie.
     */
    public Serie nuovoSerie;
    /**
     * The Nuova rivista.
     */
    public Rivista nuovaRivista;
    /**
     * The Nuovo fascicolo.
     */
    public Fascicolo nuovoFascicolo;
    /**
     * The Nuovo articolo scientifico.
     */
    public ArticoloScientifico nuovoArticoloScientifico;
    /**
     * The Lista libri collana.
     */
    public ArrayList<Libro> listaLibriCollana = new ArrayList<>();
    /**
     * The Lista libri serie.
     */
    public ArrayList<Libro> listaLibriSerie = new ArrayList<>();
    /**
     * The Lista serie.
     */
    public ArrayList<Serie> listaSerie = getSerie();
    /**
     * The Lista serie autore.
     */
    public ArrayList<Serie> listaSerieAutore = new ArrayList<>();
    /**
     * The Lista serie genere.
     */
    public ArrayList<Serie> listaSerieGenere = new ArrayList<>();
    /**
     * The Lista riviste.
     */
    public ArrayList<Rivista> listaRiviste = getRiviste();
    /**
     * The Lista fascicoli.
     */
    public ArrayList<Fascicolo> listaFascicoli = getFascicoli();
    /**
     * The Lista presentazioni.
     */
    public ArrayList<Presentazione> listaPresentazioni = new ArrayList<>();
    /**
     * The Lista conferenze.
     */
    public ArrayList<Conferenza> listaConferenze = new ArrayList<>();
    /**
     * The Lista all conferenze.
     */
    public ArrayList<Conferenza> listaAllConferenze = new ArrayList<>();
    /**
     * The Lista notifiche.
     */
    public ArrayList<Notifica> listaNotifiche = new ArrayList<>();
    /**
     * The Lista fascicoli rivista.
     */
    public ArrayList<Fascicolo> listaFascicoliRivista = new ArrayList<>();
    /**
     * The Libri isbn preferiti.
     */
    public ArrayList<String> libriISBNPreferiti = new ArrayList<>();
    /**
     * The Libri titolo preferiti.
     */
    public ArrayList<String> libriTitoloPreferiti = new ArrayList<>();
    /**
     * The Possessol preferiti.
     */
    public ArrayList<Possesso> possessolPreferiti = new ArrayList<>();
    /**
     * The Librerie libri preferiti.
     */
    public ArrayList<Libreria> librerieLibriPreferiti = new ArrayList<>();
    /**
     * The Serie isbn preferiti.
     */
    public ArrayList<String> serieISBNPreferiti = new ArrayList<>();
    /**
     * The Serie titolo preferiti.
     */
    public ArrayList<String> serieTitoloPreferiti = new ArrayList<>();
    /**
     * The Possessos preferiti.
     */
    public ArrayList<Possesso> possessosPreferiti = new ArrayList<>();
    /**
     * The Librerie serie preferiti.
     */
    public ArrayList<Libreria> librerieSeriePreferiti = new ArrayList<>();
    /**
     * The Fascicoli titolo preferiti.
     */
    public ArrayList<String> fascicoliTitoloPreferiti = new ArrayList<>();
    /**
     * The Fascicoli preferiti.
     */
    public ArrayList<Fascicolo> fascicoliPreferiti = new ArrayList<>();
    /**
     * The Possessof preferiti.
     */
    public ArrayList<Possesso> possessofPreferiti = new ArrayList<>();
    /**
     * The Lista articoli.
     */
    public ArrayList<ArticoloScientifico> listaArticoli = new ArrayList<>();
    /**
     * The Librerie fascicoli preferiti.
     */
    public ArrayList<Libreria> librerieFascicoliPreferiti = new ArrayList<>();
    /**
     * The Librerie utente.
     */
    public ArrayList<Libreria> librerieUtente = new ArrayList<>();
    /**
     * The Isbn selected.
     */
    public String isbn_selected = "";
    /**
     * The Nome selected.
     */
    public String nome_selected = "";
    /**
     * The Libreria selected.
     */
    public Libreria libreria_selected = null;
    /**
     * The Fascicolo selected.
     */
    public Fascicolo fascicolo_selected = null;
    /**
     * The Like element selected.
     */
    public boolean likeElementSelected;
    /**
     * The Recensioni con commento.
     */
    public ArrayList<Recensione> recensioniConCommento = new ArrayList<>();
    /**
     * The Possesso l libreria.
     */
    public ArrayList<Possesso> possessoLLibreria = new ArrayList<>();
    /**
     * The Possesso s libreria.
     */
    public ArrayList<Possesso> possessoSLibreria = new ArrayList<>();
    /**
     * The Possesso f libreria.
     */
    public ArrayList<Possesso> possessoFLibreria = new ArrayList<>();
    /**
     * The Titolo libri libreria.
     */
    public ArrayList<String> titoloLibriLibreria = new ArrayList<>();
    /**
     * The Titolo serie libreria.
     */
    public ArrayList<String> titoloSerieLibreria = new ArrayList<>();
    /**
     * The Fascicoli libreria.
     */
    public ArrayList<Fascicolo> fascicoliLibreria = new ArrayList<>();
    /**
     * The Lista collane.
     */
    public ArrayList<Collana> listaCollane = getCollane();
    /**
     * The Doi selected.
     */
    public String doi_selected = "";
    /**
     * The Anno pubblicazione.
     */
    public int anno_pubblicazione = 0;
    /**
     * The Nome articolo.
     */
    public String nome_articolo = "";

    /**
     * The Screen width.
     */
    public int screenWidth = 0;
    /**
     * The Screen height.
     */
    public int screenHeight = initDimension();
    /**
     * The Title impact size.
     */
    public int titleImpactSize = getFontTitleImpactSize();
    /**
     * The Title impact.
     */
    public Font titleImpact = new Font("Impact", Font.PLAIN, titleImpactSize);
    /**
     * The Font size.
     */
    public int fontSize = getFontSize();
    /**
     * The Base font size.
     */
    public Font baseFontSize = new Font("Segoe UI", Font.PLAIN, fontSize);
    /**
     * The Impact font size.
     */
    public Font impactFontSize = new Font("Impact", Font.PLAIN, fontSize);
    /**
     * The Text field font.
     */
    public Font textFieldFont = new Font("Berlin Sans FB", Font.PLAIN, fontSize-2);

    /**
     * Instantiates a new Controller.
     */
    public Controller(){
    }

    /**
     * Aggiungi utente.
     *
     * @param email       the email
     * @param nome        the nome
     * @param cognome     the cognome
     * @param username    the username
     * @param password    the password
     * @param dataNascita the data nascita
     * @param partitaIVA  the partita iva
     */
// UTENTE //
    public void aggiungiUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){ //aggiunge un nuovo utente nel DB e in memoria
        utente = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA); //registra il nuovo utente in memoria

        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.addUtenteDB(email, nome, cognome, username, password, dataNascita, partitaIVA);   //registra il nuovo utente nel DB
    }//fine aggiungiUtente

    /**
     * Valida utente int.
     *
     * @param userEmail the user email
     * @param password  the password
     * @return the int
     */
    public int validaUtente(String userEmail, String password){ //conta il numero di utenti registrati con username o email 'userMail' e con password 'password'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        return u.validaUtenteDB(userEmail, password);   //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
    }//fine validaUtente

    /**
     * Sets utente.
     *
     * @param userEmail the user email
     * @param password  the password
     */
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

    /**
     * Valida mod utente int [ ].
     *
     * @param email    the email
     * @param username the username
     * @param pIva     the p iva
     * @return the int [ ]
     */
    public int[] validaModUtente(String email, String username, String pIva){   //controlla se delle modifiche effettuate dall'utente sono corrette, verificando che l'email 'email', l'username 'uername' e/o la partita IVA 'pIva' non siano già state utilizzate da altri utenti
        int[] error = {0, 0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        if(!email.equals(utente.getEmail())){    //controlla se l'email è stata modificata
            error[0] = u.validaModEmailDB(email);   //mette in 'error[0]' il numero di utenti con 'email' trovati nel DB
        }

        if(!username.equals(utente.getUsername())){  //controlla se l'username è stato modificato
            error[1] = u.validaModUsernameDB(username); //mette in 'error[1]' il numero di utenti con 'username' trovati nel DB
        }

        if(!pIva.equals(utente.getPartitaIVA())){    //controlla se la partita IVA è stata modificata
            if(utente.verifyPartitaIVA(pIva) == false){ //controlla se il formaro di 'pIva' è corretto
                error[3] = 1;   //mette in 'error[3]' 1
            } else {
                error[2] = u.validaModPIVADB(pIva); //mette in 'error[2]' il numero di utenti con 'pIva' trovati nel DB
            }
        }

        u.chiudiConnessione();  //chiude la connessione al DB

        return error;
    }//fine validaModUtente

    /**
     * Valida ins utente int [ ].
     *
     * @param email    the email
     * @param username the username
     * @param pIva     the p iva
     * @return the int [ ]
     */
    public int[] validaInsUtente(String email, String username, String pIva){   //controlla se l'email 'email', l'username 'uername' e/o la partita IVA 'pIva' non siano già state utilizzate da altri utenti
        int[] error = {0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        error[0] = u.validaModEmailDB(email);   //mette in 'error[0]' il numero di utenti con 'email' trovati nel DB

        error[1] = u.validaModUsernameDB(username); //mette in 'error[1]' il numero di utenti con 'username' trovati nel DB

        error[2] = u.validaModPIVADB(pIva); //mette in 'error[2]' il numero di utenti con pIva' trovati nel DB

        u.chiudiConnessione();  //chiude la connessione al DB

        return error;
    }//fine validaInsUtente

    /**
     * Verifica partita iva boolean.
     *
     * @param pIva the p iva
     * @return the boolean
     */
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

    /**
     * Verifica nome cognome boolean.
     *
     * @param nc the nc
     * @return the boolean
     */
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

    /**
     * Change email string.
     *
     * @param email the email
     * @return the string
     */
    public String changeEmail (String email){   //convete i caratteri maiuscoli di 'email' in minuscoli
        for(int i = 0; i < email.length(); i++){    //scorre 'email'
            if(email.charAt(i) < 'A' || email.charAt(i) > 'Z'){ //controlla se l'i-esimo carattere è maiuscolo
                email.replace(email.charAt(i), (char) (email.charAt(i) + 32));  //converte l'i-esimo carattere in minuscolo
            }
        }

        return email;
    }   //fine changeEmail

    /**
     * Verifica email boolean.
     *
     * @param email the email
     * @return the boolean
     */
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

    /**
     * Verifica password boolean.
     *
     * @param password the password
     * @return the boolean
     */
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

    /**
     * Verifica isbn boolean.
     *
     * @param isbn the isbn
     * @return the boolean
     */
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

    /**
     * Verifica issn boolean.
     *
     * @param issn the issn
     * @return the boolean
     */
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

    /**
     * Mod utente.
     *
     * @param email      the email
     * @param nome       the nome
     * @param cognome    the cognome
     * @param username   the username
     * @param password   the password
     * @param partitaIVA the partita iva
     */
    public void modUtente(String email, String nome, String cognome, String username, String password, String partitaIVA){  //modifica i dati dell'utente
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.modUtenteDB(email, nome, cognome, username, password, partitaIVA, utente.getUsername());   //modifica i dati dell'utente nel DB

        utente.setEmail(email); //imposta l'email dell'utente
        utente.setNome(nome);   //imposta il nome dell'utente
        utente.setCognome(cognome); //imposta il cognome dell'utente
        utente.setUsername(username);   //imposta l'username dell'utente
        utente.setPassword(password);   //imposta la password dell'utente
        utente.setPartitaIva(partitaIVA);   //imposta la partita IVA dell'utente
    }//fine modUtente

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername(){    //ritorna l'username dell'utente
        return utente.getUsername();
    }//fine getUsername

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome(){    //ritorna il nome dell'utente
        return utente.getNome();
    }//fine getNome

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome(){ //ritorna il cognome dell'utente
        return utente.getCognome();
    }//fine getCognome

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail(){   //ritorna l'email dell'utente
        return utente.getEmail();
    }//fine getEmail

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword(){    //ritorna la password dell'utente
        return utente.getPassword();
    }//fine getPassword

    /**
     * Gets partita iva.
     *
     * @return the partita iva
     */
    public String getPartitaIva(){  //ritorna la partita IVA dell'utente
        return utente.getPartitaIVA();
    }//fine getPartitaIva

    /**
     * Gets utente.
     *
     * @param username the username
     * @return the utente
     */
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

    /**
     * Crea libro boolean.
     *
     * @param isbn    the isbn
     * @param titolo  the titolo
     * @param genere  the genere
     * @param lingua  the lingua
     * @param editore the editore
     * @param dp      the dp
     * @return the boolean
     */
// LIBRO //
    public boolean creaLibro(String isbn, String titolo, String genere, String lingua, String editore,String dp){   //aggiunge un nuovo libro nel DB e in memoria
        LibroDAO l = new LibroImplementazionePostgresDAO();
        boolean presenzaLibro = l.creaLibroDB(isbn, titolo, genere, lingua, editore, dp);   //se non esiste già, inserisce un nuovo libro nel DB

        nuovoLibro = new Libro(isbn, genere, editore, lingua, titolo, Date.valueOf(dp));    //inizializza 'nuovoLibro'
        return presenzaLibro;
    }//fine creaLibro

    /**
     * Gets libri.
     *
     * @return the libri
     */
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

    /**
     * Gets libri.
     *
     * @param collana the collana
     */
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

    /**
     * Gets libri serie.
     *
     * @param isbnSerie the isbn serie
     */
    public void getLibriSerie(String isbnSerie){    //ritorna tutti i libri della serie con ISBN 'isbnSerie'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        ArrayList<Libro> libri = new ArrayList<>(); //contiene i libri della serie
        ResultSet rs = l.getLibriSerieDB(isbnSerie);    //cerca i libri della serie trovati nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i libri della serie
                for(Libro libro: listaLibri){   //scorre la lista dei libri
                    if (libro.getISBN().equals(rs.getString("libro"))){  //controlla se 'libro' appartiene alla serie
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

    /**
     * Get info libri preferiti.
     */
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

    /**
     * Gets data libro.
     *
     * @return the data libro
     */
    public Date getDataLibro(){ //ritorna la data di pubblicazione del libro selezionato
        LibroDAO l = new LibroImplementazionePostgresDAO();
        return l.getDataLibroDB(isbn_selected);
    }//fine getDataLibro

    /**
     * Gets data libro.
     *
     * @param isbn the isbn
     * @return the data libro
     */
    public Date getDataLibro(String isbn){  //ritorna la data di pubblicazione del libro selezionato con ISBN 'isbn'
        LibroDAO l = new LibroImplementazionePostgresDAO();
        return l.getDataLibroDB(isbn);
    }//fine getDataLibro

    /**
     * Elimina libro.
     */
    public void eliminaLibro(){ //elimina il nuovo libro
        LibroDAO l = new LibroImplementazionePostgresDAO();

        l.eliminaLibroDB(nuovoLibro.isbn);
    }//fine eliminaLibro

    /**
     * Gets collana nome.
     *
     * @return the collana nome
     */
// COLLANA //
    public ArrayList<String> getCollanaNome(){  //ritorna tutti i nomi delle collane
        CollanaDAO c = new CollanaImplementazionePostgresDAO();
        return c.getCollanaNomeDB();
    }//fine getCollanaNome

    /**
     * Gets collane.
     *
     * @return the collane
     */
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

    /**
     * Remove libro from collana.
     *
     * @param nomeCollana the nome collana
     */
    public void removeLibroFromCollana(String nomeCollana){ //rimuove il libro selezionato dalla collana 'nomeCollana'
        CollanaDAO c = new CollanaImplementazionePostgresDAO();

        c.removeLibroFromCollanaDB(isbn_selected, nomeCollana); //rimuove il libro con ISBN 'isbn_selected' dalla collana 'nomeCollana'

        listaCollane = getCollane();    //aggiorna 'listacollane'
    }//fine removeLibroFromCollana

    /**
     * Add libro in collana.
     *
     * @param nomeCollana the nome collana
     */
    public void addLibroInCollana(String nomeCollana){  //aggiunge il libro selezionato nella collana 'nomeCollana'
        CollanaDAO c = new CollanaImplementazionePostgresDAO();

        c.addLibroInCollanaDB(isbn_selected, nomeCollana);  //aggiunge il libro con ISBN 'isbn_selected' dalla collana 'nomeCollana'

        listaCollane = getCollane();    //aggiorna 'listacollane'
    }//fine addLibroFromCollana

    /**
     * Crea collana boolean.
     *
     * @param nome           the nome
     * @param caratteristica the caratteristica
     * @param issn           the issn
     * @return the boolean
     */
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

    /**
     * Aggiungi autore libro.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param nazionalita the nazionalita
     * @param dn          the dn
     */
// AUTORE //
    public void aggiungiAutoreLibro(String nome, String cognome, String nazionalita, String dn){    //aggiunge un nuovo autore al libro selezionato e li associa nel DB
        AutoreDAO a = new AutoreImplementazionePostgresDAO();

        a.aggiungiAutoreLibroDB(nome, cognome, nazionalita, dn, nuovoLibro.getISBN());   //aggiunge un nuovo autore al nuovo libro nel DB

        if(!dn.isBlank()){  //controlla se non è stata inserita la data di nascita del nuovo autore
            nuovoLibro.getAutori().add(new Autore(nome, cognome, nazionalita, Date.valueOf(dn)));    //aggiunge il nuovo autore al nuovo libro
        } else{
            nuovoLibro.getAutori().add(new Autore(nome, cognome, nazionalita, null));    //aggiunge il nuovo autore al nuovo libro
        }
    }//fine aggiungiAutoreLibro

    /**
     * Aggiungi autore articolo.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param nazionalita the nazionalita
     * @param dn          the dn
     */
    public void aggiungiAutoreArticolo(String nome, String cognome, String nazionalita, String dn){ //aggiunge un nuovo autore al nuovo articolo e li associa nel DB
        AutoreDAO a = new AutoreImplementazionePostgresDAO();

        a.aggiungiAutoreArticoloDB(nome, cognome, nazionalita, dn, nuovoArticoloScientifico.getDoi());   //aggiunge un nuovo autore al nuovo articolo nel DB

        if(!dn.isBlank()){  //controlla se non è stata inserita la data di nascita del nuovo autore
            nuovoArticoloScientifico.autori.add(new Autore(nome, cognome, nazionalita, Date.valueOf(dn)));  //aggiunge il nuovo autore al nuovo articolo
        } else {
            nuovoArticoloScientifico.autori.add(new Autore(nome, cognome, nazionalita, null));  //aggiunge il nuovo autore al nuovo articolo
        }
    }//fine aggiungiAutoreArticolo

    /**
     * Gets autori libro.
     *
     * @param isbn the isbn
     * @return the autori libro
     */
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

    /**
     * Gets autori articolo.
     *
     * @param doi the doi
     * @return the autori articolo
     */
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

    /**
     * All autori libri array list.
     *
     * @return the array list
     */
    public ArrayList<String> allAutoriLibri(){  //ritorna tutti gli autori di ogni libro
        int aut = 0;    //numero di autori del libro corrente
        String linkString = ""; //nomi e cognomi di tutti gli autori del libro corrente
        ArrayList<String> totAutoreList = new ArrayList<>();    //contie gli autori

        for (Libro l: listaLibri) {    //scorre la lista dei libri
            aut = 0;    //azzera 'aut'

            for (Autore a: l.getAutori()) {  //scorre tutti gli autori del libro 'l'
                if (aut == 0){  //controlla se si sta inserendo il primo autore
                    linkString = a.getNome() + " " + a.getCognome();    //se non ci sono altri autori concatena il nome e il cognome dell'autore 'a' in 'linkString'
                } else{
                    linkString = linkString + " \n" + a.getNome() + " " + a.getCognome(); //concatena il nome e il cognome dell'autore 'a' in 'linkString' andando a capo
                }

                aut++;  //incrementa il numero di autori di 'l'
            }

            totAutoreList.add(linkString);  //inserisce 'linkString' in 'totAutoreList'
        }

        return  totAutoreList;
    }//fine allAutoriLibri

    /**
     * All autori distinti libri array list.
     *
     * @return the array list
     */
    public ArrayList<String> allAutoriDistintiLibri(){  //ritorna tutti gli autori di tutti i libri evitando duplicati
        ArrayList<String> distinctAutoreList = new ArrayList<>();   //contiene gli autori
        String linkString = ""; //nome e cognome dell'autore corrente del libro corrente

        for (Libro l: listaLibri) {    //scorre la lista dei libri
            for (Autore a : l.getAutori()) { //scorre gli autori del libro 'l'
                linkString = a.getNome() + " " + a.getCognome();  //concatena in 'linkString' il nome e il cognome dell'autore 'a'

                if (!distinctAutoreList.contains(linkString)) { //controlla se 'distinctAutoreList' non contiene 'linkString'
                    distinctAutoreList.add(linkString);  //inserisce 'linkString' in 'distinctAutoreList'
                }
            }
        }

        return distinctAutoreList;
    }//fine allAutoriuDistintiLibri

    /**
     * All autori articolo string.
     *
     * @param autori the autori
     * @return the string
     */
    public String allAutoriArticolo(ArrayList<Autore> autori){  //ritorna una stringa contenente i nomi e i cognomi di tutti gli autori in 'aUTORI'
        String a = "";  //contiene gli autori

        for (Autore au: autori){    //scorre 'autori'
            a = a + au.getNome() + " " + au.getCognome() + " ";   //concatena il nome e il cognome di 'au' in 'a'
        }

        return a;
    }//fine allAutoriArticolo

    /**
     * Gets disponibilita.
     *
     * @return the disponibilita
     */
// LIBRERIA //
    public ResultSet getDisponibilita(){    //ritorna un ResultSet con le disponibilita del libro con ISBN 'isbn_selected'
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ResultSet rs = l.disponibilitaDB(isbn_selected);    //cerca tutte le dipsonibilità del libro selezionato

        return rs;
    }//fine getDisonibilita

    /**
     * Gets disponibilita libreria.
     *
     * @return the disponibilita libreria
     */
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

    /**
     * Gets disponibilita quantita.
     *
     * @return the disponibilita quantita
     */
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

    /**
     * Gets disponibilita fruizione.
     *
     * @return the disponibilita fruizione
     */
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

    /**
     * Gets disponibilita indirizzo.
     *
     * @return the disponibilita indirizzo
     */
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

    /**
     * Gets disponibilita sito web.
     *
     * @return the disponibilita sito web
     */
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

    /**
     * Gets disponibilita numero telefono.
     *
     * @return the disponibilita numero telefono
     */
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

    /**
     * Gets disponibilita fascicolo.
     *
     * @return the disponibilita fascicolo
     */
    public ResultSet getDisponibilitaFascicolo(){    //ritorna un ResultSet con le disponibilita del fascicolo selezionato
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ResultSet rs = l.disponibilitaFascicoloDB(fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo());    //cerca tutte le dipsonibilità del fascicolo selezionato

        return rs;
    }//fine getDisponibilitaFascicolo

    /**
     * Gets disponibilita libreria fascicolo.
     *
     * @return the disponibilita libreria fascicolo
     */
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

    /**
     * Gets disponibilita quantita fascicolo.
     *
     * @return the disponibilita quantita fascicolo
     */
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

    /**
     * Gets disponibilita fruizione fascicolo.
     *
     * @return the disponibilita fruizione fascicolo
     */
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

    /**
     * Gets disponibilita indirizzo fascicolo.
     *
     * @return the disponibilita indirizzo fascicolo
     */
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

    /**
     * Gets disponibilita sito web fascicolo.
     *
     * @return the disponibilita sito web fascicolo
     */
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

    /**
     * Gets disponibilita numero telefono fascicolo.
     *
     * @return the disponibilita numero telefono fascicolo
     */
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

    /**
     * Gets librerie utente.
     */
    public void getLibrerieUtente(){    //ritorna tutte le librerie dell'utente
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        ArrayList<Libreria> librerie = new ArrayList<>();   //contiene le librerie
        ResultSet rs = l.getLibrerieUtenteDB(utente.getUsername());  //cerca le libreri dell'utente trovate nel DB

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                librerie.add(new Libreria(rs.getString("nome"), rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"), utente)); //aggiunge una nuova libreria in 'librerie'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        librerieUtente = librerie;  //inizializza 'librerieUtente'
    }//fine getLibrerieUtente

    /**
     * Verifica numero telefonico libreria boolean.
     *
     * @param nt the nt
     * @return the boolean
     */
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

    /**
     * Presenza numero telefonico libreria boolean.
     *
     * @param nt the nt
     * @return the boolean
     */
    public boolean presenzaNumeroTelefonicoLibreria(String nt){ //controlla se il numero telefonico 'nt' è già presente nel DB
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        return l.presenzaNumeroTelefonicoLibreriaDB(nt);
    }//fine presenzaNumeroTelefonicoLibreria

    /**
     * Presenza libreria boolean.
     *
     * @param nome      the nome
     * @param sw        the sw
     * @param indirizzo the indirizzo
     * @return the boolean
     */
    public boolean presenzaLibreria(String nome, String sw, String indirizzo){  //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();
        return l.presenzaLibreriaDB(nome, sw, indirizzo);
    }//fine presenzaLibreria

    /**
     * Add libreria.
     *
     * @param nome      the nome
     * @param nt        the nt
     * @param sw        the sw
     * @param indirizzo the indirizzo
     */
    public void addLibreria(String nome, String nt, String sw, String indirizzo){   //aggiunge una nuova libreria
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();

        l.addLibreriaDB(nome, nt, sw, indirizzo, utente.getUsername());  //aggiunge la nuova libreria nel DB
        librerieUtente.add(new Libreria(nome, nt, indirizzo, sw, utente));  //aggiunge la nuova libreria in 'librerieUtente'
    }//fine addLibreria

    /**
     * Remove libreria.
     *
     * @param index the index
     */
    public void removeLibreria(int index){  //rimuove l'index-esima libreria di 'librerieUtente'
        LibreriaDAO l = new LibreriaImplementazionePostgresDAO();

        l.removeLibreriaDB(librerieUtente.get(index).getNumeroTelefonico()); //rimuove la libreria dal DB
        librerieUtente.remove(index);   //rimuove la libreria da 'librerieUtente'
    }//fine removeLibreria

    /**
     * Gets presentazione.
     */
// PRESENTAZIONE //
    public void getPresentazione(){    //ritorna i dati di tutte le presentazioni del libro con ISBN 'isbn_selected'
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        ArrayList<Presentazione> presentazioni = new ArrayList<>(); //contiene tutte le presentazioni del libro selezionato
        ResultSet rs = p.getPresentazioneDB(isbn_selected); //cerca i dati di tutte le presentazioni del libro selezionato
        Libro libroSelezionato = null;  //libro selezionato

        for (Libro l: listaLibri){  //scorre la lista dei libri
            if(l.getISBN().equals(isbn_selected)){   //controlla se 'l' è il libro selezionato
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

    /**
     * Add presentazione boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param data      the data
     * @param orario    the orario
     * @return the boolean
     */
    public boolean addPresentazione(String struttura, String luogo, String data, String orario){    //se aggiunge una nuova presentazione ritorna "true", altrimenti ritorna "false"
        PresentazioneDAO p = new PresentazioneImplementazionePostgresDAO();
        return p.addPresentazioneDB(struttura, luogo, data, orario, isbn_selected);
    }//fine addPresentazione

    /**
     * Gets serie.
     *
     * @return the serie
     */
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

    /**
     * Gets serie generi.
     *
     * @return the serie generi
     */
    public ArrayList<String> getSerieGeneri(){  //ritorna tutti i generi dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieGenereDB();
    }//fine getSerieGeneri

    /**
     * Gets serie autori.
     *
     * @return the serie autori
     */
    public ArrayList<String> getSerieAutori(){  //ritorna tutti gli autori dei libri che sono inseriti in una serie
        SerieDAO s = new SerieImplementazionePostgresDAO();
        return s.getSerieAutoriDB();
    }//fine getSerieAutori

    /**
     * Gets lista serie genere.
     *
     * @param genere the genere
     */
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

    /**
     * Gets lista serie autore.
     *
     * @param autore the autore
     */
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

    /**
     * Gets info serie preferiti.
     */
    public void getInfoSeriePreferiti(){    //inserisce in 'possessosPreferiti' i dati delle serie preferite dell'utente e in 'librerieSeriePreferiti' i dati delle librerie che le possiedono
        getSerieISBNPreferiti();    //inizializza 'serieISBNPreferiti' con gli ISBN delle serie preferite dell'utente

        SerieDAO s = new SerieImplementazionePostgresDAO();
        ResultSet rs = null;

        serieTitoloPreferiti.clear();   //svuota 'serieTitoloPreferiti'
        possessosPreferiti.clear(); //svuota 'possessosPreferiti'
        librerieSeriePreferiti.clear(); //svuota 'librerieSeriePreferiti'

        for (int i = 0; i< serieISBNPreferiti.size(); i++){ //scorre 'serieISBNPreferiti'
            rs = s.getInfoSeriePreferitiDB(serieISBNPreferiti.get(i));  //cerca i dati dell'i-esima serie e delle librerie che la possiedono

            try {
                while(rs.next()){    //scorre il ResultSet 'rs'
                    serieTitoloPreferiti.add(serieISBNPreferiti.get(i) + " - " + rs.getString("titolo"));   //aggiunge il titolo dell'i-esima serie in 'serieTitoloPreferiti'

                    Serie serie = new Serie(rs.getString("isbn"),rs.getInt("nlibri") ,rs.getString("titolo"), rs.getDate("datapubblicazione"));
                    Libreria libreria = new Libreria(rs.getString("nome"),rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"));

                    possessosPreferiti.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), serie, libreria));    //aggiunge un nuovo possesso in 'possessosPreferiti'
                    librerieSeriePreferiti.add(libreria);   //aggiunge l'i-esima libreria in 'librerieSeriePreferiti'
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
        }

        s.chiudiConnessione();  //chiude la connessione al DB
    }//fine getInfoSeriePreferiti

    /**
     * Crea serie boolean.
     *
     * @param isbnList the isbn list
     * @param isbn     the isbn
     * @param titolo   the titolo
     * @param dp       the dp
     * @return the boolean
     */
    public boolean creaSerie(ArrayList<String> isbnList, String isbn, String titolo, String dp){    //se crea una nuova serie, ritorna "true", altrimenti ritorna "false"
        SerieDAO s = new SerieImplementazionePostgresDAO();
        boolean presenzaSerie = s.creaSerieDB(isbnList, isbn, titolo, dp);  //se non esiste già, inserisce un nuova serie nel DB

        nuovoSerie = new Serie(isbn, isbnList.size(), titolo, Date.valueOf(dp));    //inizializza 'nuovaSerie'
        return presenzaSerie;
    }//fine creaSerie

    /**
     * Valutazione media libro float.
     *
     * @return the float
     */
// RECENSIONE //
    public float valutazioneMediaLibro(){   //ritorna la media delle valutazioni del libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaLibroDB(isbn_selected);
    }//fine valutazioneMediaLibro

    /**
     * Like libro.
     */
    public void likeLibro(){    //controlla se l'utente ha il libro selezionato tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeLibroDB(isbn_selected, utente.getUsername());    //aggiorna 'likeElementSelected'
        return;
    }//fine likeLibro

    /**
     * Change like libro.
     */
    public void changeLikeLibro(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente il libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeLibroDB(likeElementSelected, isbn_selected, utente.getUsername()); //aggiorna 'likeElementSelected'
    }//fine changeLikeLibro

    /**
     * Add recensione libro.
     *
     * @param valutazione the valutazione
     * @param text        the text
     */
    public void addRecensioneLibro(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al libro selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        r.addRecensioneLibroDB(valutazione, text, isbn_selected, utente.getUsername());  //aggiunge la nuova recensione nel DB
        return;
    }//fine addRecensioneLibro

    /**
     * All rec with comment libro.
     */
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

    /**
     * Valutazione media serie float.
     *
     * @return the float
     */
    public float valutazioneMediaSerie(){   //ritorna la media delle valutazioni della serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaSerieDB(isbn_selected);
    }//fine valutazioneMediaSerie

    /**
     * Like serie.
     */
    public void likeSerie(){    //controlla se l'utente ha la serie selezionata tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeSerieDB(isbn_selected, utente.getUsername());    //aggiorna 'likeElementSelected'
        return;
    }//fine likeSerie

    /**
     * Change like serie.
     */
    public void changeLikeSerie(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente la serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeSerieDB(likeElementSelected, isbn_selected, utente.getUsername()); //aggiorna 'likeElementSelected'
    }//fine changeLikeSerie

    /**
     * Add recensione serie.
     *
     * @param valutazione the valutazione
     * @param text        the text
     */
    public void addRecensioneSerie(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente alla serie selezionata
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        r.addRecensioneSerieDB(valutazione, text, isbn_selected, utente.getUsername());  //aggiunge la nuova recensione nel DB
        return;
    }//fine addRecensioneSerie

    /**
     * All rec with comment serie.
     */
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

    /**
     * Valutazione media fascicolo float.
     *
     * @return the float
     */
    public float valutazioneMediaFascicolo(){   //ritorna la media delle valutazioni del fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        return r.valutazioneMediaFascicoloDB(fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo());
    }//fine valutazioneMediaFascicolo

    /**
     * Like fascicolo.
     */
    public void likeFascicolo(){    //controlla se l'utente ha il fascicolo selezionato tra i preferiti e pone il risultato in 'likeElementSelected'
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.likeFascicoloDB(fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo(), utente.getUsername()); //aggiorna 'likeElementSelected'
    }//fine likeFascicolo

    /**
     * Change like fascicolo.
     */
    public void changeLikeFascicolo(){   //cambia il valore di 'likeElementSelected' e toglie/mette nei preferiti dell'utente il fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        likeElementSelected = r.changeLikeFascicoloDB(likeElementSelected, fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo(), utente.getUsername());  //aggiorna 'likeElementSelected'
    }//fine changeLikeFascicolo

    /**
     * Add recensione fascicolo.
     *
     * @param valutazione the valutazione
     * @param text        the text
     */
    public void addRecensioneFascicolo(int valutazione, String text){   //aggiunge una nuova recensione con 'valutazione' e 'testo' fatta dall'utente al fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        r.addRecensioneFascicoloDB(valutazione, text, fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo(), utente.getUsername());   //aggiunge la nuova recensione nel DB
        return;
    }

    /**
     * All rec with comment fascicolo.
     */
    public void allRecWithCommentFascicolo(){    //ritorna tutte le recensioni con testo fatte al fascicolo selezionato
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();
        ResultSet rs = r.allRecWithCommentFascicoloDB(fascicolo_selected.getNumero(), fascicolo_selected.getRivista().getTitolo());    //cerca tutte le recensioni fatte al fascicolo selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le recensioni
                recensioniConCommento.add(new Recensione(rs.getString("testo"), rs.getInt("valutazione"), rs.getBoolean("preferito"), getUtente(rs.getString("username")), null));  //aggiunge una nuova recensione in 'recensioneConCommento'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB
    }//fine allRecWithCommentFascicolo

    /**
     * Gets libri isbn preferiti.
     */
    public void getLibriISBNPreferiti(){    //ritorna gli ISBN dei libri preferiti dell'utente
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        libriISBNPreferiti = r.getLibriISBNPreferitiDB(utente.getUsername());    //inizializza 'libriISBNPreferiti'
    }//fine getLibriISBNPreferiti

    /**
     * Gets serie isbn preferiti.
     */
    public void getSerieISBNPreferiti(){    //ritorna gli ISBN delle serie preferite dell'utente
        RecensioneDAO r = new RecensioneImplementazionePostgresDAO();

        serieISBNPreferiti = r.getSerieISBNPreferitiDB(utente.getUsername());    //inizializza 'serieISBNPreferiti'
    }//fine getSerieISBNPreferiti

    /**
     * Gets riviste.
     *
     * @return the riviste
     */
// RIVISTA //
    public ArrayList<Rivista> getRiviste() {   //ritorna i dati di tutte le riviste nel DB
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        ResultSet rs = r.getRivisteDB();  //cerca i dati di tutte le riviste nel DB
        ArrayList<Rivista> riviste = new ArrayList<Rivista>();    //contiene tutte le rkiviste

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le riviste
                riviste.add(new Rivista(rs.getString("issn"), rs.getString("titolo"), rs.getString("editore"), rs.getInt("annoPubblicazione"), rs.getString("nomer") + "#" + rs.getString("cognomer"), rs.getString("argomento")));   //inserisce una nuova rivista in 'riviste'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        r.chiudiConnessione();  //chiude la connessione al DB

        return riviste;
    }//fine getRiviste

    /**
     * Crea rivista boolean.
     *
     * @param titolo    the titolo
     * @param issn      the issn
     * @param argomento the argomento
     * @param nomeR     the nome r
     * @param cognomeR  the cognome r
     * @param editore   the editore
     * @param ap        the ap
     * @return the boolean
     */
    public boolean creaRivista(String titolo, String issn, String argomento ,String nomeR, String cognomeR, String editore, int ap){    //se crea una nuova rivista, ritorna "true", altrimenti ritorna "false"
        RivistaDAO r = new RivistaImplementazionePostgresDAO();
        boolean presenzaRivista = r.creaRivistaDB(issn, titolo, argomento, nomeR, cognomeR, editore, ap);      //se non esiste già, inserisce una nuova rivista nel DB

        nuovaRivista = new Rivista(issn, titolo, editore, ap, nomeR +"#"+ cognomeR, argomento); //inizializza 'nuovaRivista'
        return presenzaRivista;
    }//fine creaRivista

    /**
     * Elimina rivista.
     */
    public void eliminaRivista(){   //elimina la nuova rivista
        RivistaDAO r = new RivistaImplementazionePostgresDAO();

        r.eliminaRivistaDB(nuovaRivista.getISSN());
    }//eliminaRivista

    /**
     * Gets fascicoli.
     *
     * @return the fascicoli
     */
// FASCICOLO //
    public ArrayList<Fascicolo> getFascicoli(){ //ritorna i dati di tutti i fascicoli nel DB
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = f.getFascicoliDB();  //cerca i dati di tutti i fascicoli nel DB
        ArrayList<Fascicolo> fascicoli = new ArrayList<Fascicolo>();    //contiene tutti i fascicoli

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i fascicoli
                for (int i = 0; i < listaRiviste.size(); i++){  //scorre 'listaRiviste'
                    if(listaRiviste.get(i).getISSN().equals(rs.getString("issn"))){  //controlla se l'i-esima rivista è quella del fascicolo corrente
                        Rivista rivistaFascicolo = listaRiviste.get(i); //rivista del fascicolo corrente

                        i = listaRiviste.size();    //assegna a 'i' la dimensione di 'listaRiviste'

                        ArrayList<ArticoloScientifico> articoliScientifici = getArticoliScientifici(rivistaFascicolo.getISSN(), rs.getInt("numero")); //inserisce gli articoli scientifici del fascicolo corrente di 'rs' nell'ArrayList 'articoliScientifici'

                        fascicoli.add(new Fascicolo(rs.getInt("numero"), rivistaFascicolo, articoliScientifici, rs.getDate("datapubblicazione")));   //inserisce un nuovo fascicolo in 'fascicoli'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        f.chiudiConnessione();  //chiude la connessione al DB

        return fascicoli;
    }//fine getFascicoli

    /**
     * Gets fascicoli rivista.
     *
     * @param issn the issn
     */
    public void getFascicoliRivista(String issn){   //ritorna tutti i fascicoli della rivista con ISSN 'issn'
        listaFascicoliRivista.clear();  //svuota 'listaFascicoliRivista'

        for (Fascicolo f: listaFascicoli){  //scorre 'listaFascicoli'
            if(f.getRivista().getISSN().equals(issn)){    //controlla se il fascicolo 'f' è nella rivsta con ISSN 'issn'
                listaFascicoliRivista.add(f);   //aggiunge 'f' in 'listaFascicoliRivista'
            }
        }
    }//fine getFascicoliRivista

    /**
     * Gets fascicoli preferiti.
     */
    public void getFascicoliPreferiti(){    //ritorna i fascicoli preferiti dell'utente
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = f.getFascicoliDB(utente.getUsername());  //cerca i dati di tutti i fascicoli preferiti dall'utente nel DB
        ArrayList<Fascicolo> fascicoli = new ArrayList<Fascicolo>();    //contiene tutti i fascicoli

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente i fascicoli
                for (int i = 0; i < listaRiviste.size(); i++){  //scorre 'listaRiviste'
                    if(listaRiviste.get(i).getISSN().equals(rs.getString("issn"))){  //controlla se l'i-esima rivista è quella del fascicolo corrente
                        Rivista rivistaFascicolo = listaRiviste.get(i); //rivista del fascicolo corrente

                        i = listaRiviste.size();    //assegna a 'i' la dimensione di 'listaRiviste'

                        ArrayList<ArticoloScientifico> articoliScientifici = getArticoliScientifici(rivistaFascicolo.getISSN(), rs.getInt("numero")); //inserisce gli articoli scientifici del fascicolo corrente di 'rs' nell'ArrayList 'articoliScientifici'

                        fascicoli.add(new Fascicolo(rs.getInt("numero"), rivistaFascicolo, articoliScientifici, rs.getDate("datapubblicazione")));   //inserisce un nuovo fascicolo in 'fascicoli'
                    }
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        f.chiudiConnessione();  //chiude la connessione al DB

        fascicoliPreferiti = fascicoli; //inizializza 'fascicoliPreferiti'
    }//fine getFascicoliPreferiti

    /**
     * Seleziona fascicolo.
     *
     * @param numero the numero
     * @param titolo the titolo
     */
    public void selezionaFascicolo(int numero, String titolo){  //inserisce in 'fascicolo_selected' con il fascicolo numero 'numero' della rivista 'titolo'
        for(int i = 0; i < listaFascicoli.size(); i++){ //scorre 'listaFascicoli'
            if(listaFascicoli.get(i).getRivista().getTitolo().equals(titolo) && listaFascicoli.get(i).getNumero() == numero) { //contolla se l'i-esimo fascicolo è quello selezionato
                fascicolo_selected = listaFascicoli.get(i); //aggiorna 'fascicolo_selected'
                i = listaFascicoli.size();  //assegna a 'i' la dimensione di 'listaRiviste'
            }
        }
    }//fine selezionaFascicolo

    /**
     * Gets info fascicoli preferiti.
     */
    public void getInfoFascicoliPreferiti(){    //inserisce in 'possessofPreferiti' i dati dei fascicoli preferiti dell'utente e in 'librerieFascicoliPreferiti' i dati delle librerie che li possiedono
        getFascicoliPreferiti();    //inizializza 'fascicoliPreferiti' con i fascicoli preferiti dell'utente

        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        ResultSet rs = null;

        fascicoliTitoloPreferiti.clear();   //svuota 'fascicoliTitoloPreferiti'
        possessofPreferiti.clear(); //svuota 'possessofPreferiti'
        librerieFascicoliPreferiti.clear(); //svuota 'librerieFascicoliPreferiti'

        for (int i = 0; i< fascicoliPreferiti.size(); i++){ //scorre 'fascicoliPreferiti'
            rs = f.getInfoFascicoliPreferitiDB(fascicoliPreferiti.get(i).getRivista().getISSN(), fascicoliPreferiti.get(i).getNumero());   //cerca i dati dell'i-esimo fasciolo e delle librerie che lo possiedono

            try {
                while(rs.next()){    //scorre il ResultSet 'rs'
                    fascicoliTitoloPreferiti.add(fascicoliPreferiti.get(i).getRivista().getTitolo() + " N°" + fascicoliPreferiti.get(i).getNumero());  //aggiunge il titolo della rivista e il numero dell'i-esimo fasciolo in 'fascicoliTitoloPreferiti'

                    Libreria libreria = new Libreria(rs.getString("nome"),rs.getString("numerotelefonico"), rs.getString("indirizzo"), rs.getString("sitoweb"));

                    possessofPreferiti.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), fascicoliPreferiti.get(i), libreria));    //aggiunge un nuovo possesso in 'possessofPreferiti'
                    librerieFascicoliPreferiti.add(libreria);   //aggiunge l'i-esima libreria in 'librerieFascicoliPreferiti'
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
        }

        f.chiudiConnessione();  //chiude la connessione al DB
    }//fine getInfoFascicoliPreferiti

    /**
     * Crea fascicolo boolean.
     *
     * @param numero the numero
     * @param data   the data
     * @return the boolean
     */
    public boolean creaFascicolo(int numero, String data){  //se crea una nuovo fascicolo, ritorna "true", altrimenti ritorna "false"
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();
        boolean presenzaFascicolo = f.creaFascicoloDB(numero, data, nuovaRivista.getISSN()); //se non esiste già, inserisce un nuovo fascicolo nel DB

        nuovoFascicolo = new Fascicolo(numero, nuovaRivista, Date.valueOf(data));   //inizializza 'nuovoFascicolo'
        nuovoFascicolo.setArticoli(new ArrayList<ArticoloScientifico>()); //inizializza 'nuovoFascicolo.articoli'
        return presenzaFascicolo;
    }//fine creaFascicolo

    /**
     * Elimina fascicolo.
     */
    public void eliminaFascicolo(){ //elimina la nuova rivista
        FascicoloDAO f = new FascicoloImplementazionePostgresDAO();

        f.eliminaFascicoloDB(nuovoFascicolo.getRivista().getISSN(), nuovoFascicolo.getNumero());
    }//fine eliminaFascicolo

    /**
     * Gets articoli scientifici.
     *
     * @param issn the issn
     * @param n    the n
     * @return the articoli scientifici
     */
// ARTICOLO_SCIENTIFICO //
    public ArrayList<ArticoloScientifico> getArticoliScientifici(String issn, int n){   //ritorna i dati di tutti gli artioli scientifici nel DB del fascicolo numero 'n' della rivista con ISSN 'issn'
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();
        ArrayList<ArticoloScientifico> articoliScientifici = new ArrayList<ArticoloScientifico>();  //contiene gli artioli scientifici
        ResultSet rs = as.getArticoliScientificiDB(issn, n);    //cerca gli artioli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente gli articoli
                articoliScientifici.add(new ArticoloScientifico(rs.getString("doi"), rs.getString("titolo"), rs.getInt("annoPubblicazione"), getAutoriArticolo(rs.getString("doi"))));   //inserisce un nuovo articolo in 'articoliScientifici'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        return  articoliScientifici;
    }//fine getArticoliScientifici

    /**
     * Gets articoli scientifici.
     */
    public void getArticoliScientifici(){   //ritorna tutti gli articoli scientifici senza duplicati
        listaArticoli.clear();  //svuota 'listaArtioli'

        if(!listaFascicoli.isEmpty()) { //controlla se 'listaFascicoli non è vuota'
            for (int i = 0; i < listaFascicoli.size(); i++) {   //scorre 'listaFascicoli'
                for (int j = 0; j < listaFascicoli.get(i).getArticoli().size(); j++) {   //scorre gli articoli dell'i-esimo fascicolo di 'listaFascicoli'
                    if (!listaArticoli.contains(listaFascicoli.get(i).getArticoli().get(j))) {   //controlla se il j-esimo articolo dell'i-esimo fascicolo non è contenuto in 'listaArtioli'
                        listaArticoli.add(listaFascicoli.get(i).getArticoli().get(j));   //inserise il j-esimo articolo dell'i-esimo fascicolo in 'listaArtioli'
                    }
                }
            }
        }
    }//fine getArticoliScientifici

    /**
     * Crea articolo boolean.
     *
     * @param titolo the titolo
     * @param anno   the anno
     * @return the boolean
     */
    public boolean creaArticolo(String titolo, int anno){   //se crea una nuovo articolo, ritorna "true", altrimenti ritorna "false"
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();
        boolean presenzaArticolo = as.creaArticoloDB(titolo, anno, nuovoFascicolo.getNumero(), nuovoFascicolo.getRivista().getISSN()); //se non esiste già, inserisce un nuovo articolo nel DB

        nuovoArticoloScientifico = new ArticoloScientifico(as.getDoiDB(titolo), titolo, anno);  //inizializza 'nuovoArticoloScientifico'

        as.chiudiConnessione(); //chiude la connessione al DB

        return presenzaArticolo;
    }//fine creaArticolo

    /**
     * Gets a particolo.
     */
    public void getAParticolo(){    //ritorna l'anno di pubblicazione dell'articolo scientifico selezionato
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();

        anno_pubblicazione = as.getAPDB(doi_selected);
    }//fine getAParticolo

    /**
     * Elimina articolo.
     */
    public void eliminaArticolo(){  //elimina l'articolo scientifico nuovo
        ArticoloScientificoDAO as = new ArticoloScientificoImplementazionePostgresDAO();

        as.eliminaArticoloDB(nuovoArticoloScientifico.getDoi());
    }//fine eliminaArticolo

    /**
     * Gets numero notifiche non lette.
     *
     * @return the numero notifiche non lette
     */
// NOTIFICA //
    public int getNumeroNotificheNonLette(){    //ritorna il numero di notifiche dell'utente non lette
        NotificaDAO n = new NotificaImplementazionePostgresDAO();
        return n.getNumeroNotificheNonLetteDB(utente.getUsername());
    }//fine getNumeroNotifichNonLette

    /**
     * Gets notifiche utente.
     */
    public void getNotificheUtente(){   //ritorna le notifiche dell'utente
        NotificaDAO n = new NotificaImplementazionePostgresDAO();
        ArrayList<Notifica> notifiche = new ArrayList<>();  //contiene le notifiche
        ResultSet rs = n.getNotificheUtenteDB(utente.getUsername()); //cerca le notifiche dell'utente

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le notifiche
                Serie serie = null; //serie corrente

                for (int i = 0; i < listaSerie.size(); i++){    //scorre 'listaSerie'
                    if(listaSerie.get(i).getISBN().equals(rs.getString("isbn"))){    //controlla se l'i-esiam serie di 'listaSerie' è quella corrente del ReseultSet 'rs'
                        serie = listaSerie.get(i);  //aggiorna 'serie'
                        i = listaSerie.size();  //assegna a 'i' la dimensione di 'listaSerie'
                    }
                }

                notifiche.add(new Notifica(rs.getString("testo"), rs.getInt("libreria"), rs.getDate("datainvio"), rs.getTime("orainvio").toString(), rs.getBoolean("lettura"), utente, serie));   //inserisce una nuova notifica in 'notifiche'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        listaNotifiche = notifiche; //aggiorna 'listaNotifiche'
    }//fine getNotificheUtente

    /**
     * Rimuovi notifica.
     *
     * @param testo the testo
     * @param data  the data
     * @param ora   the ora
     */
    public void rimuoviNotifica(String testo, String data, String ora){ //rimuove la notifica con 'testo' inviata all'utente il 'data' alle 'ora'
        NotificaDAO n = new NotificaImplementazionePostgresDAO();

        n.rimuoviNotificaDB(testo, data, ora, utente.getUsername()); //rimuove la notifica nel DB

        for (int i = 0; i < listaNotifiche.size(); i++){    //scorre 'listaNotifica'
            Notifica notifica = listaNotifiche.get(i);  //i-esima notifica in ''listaNotifica'

            if (notifica.getTesto().equals(testo) && notifica.getDataInvio().toString().equals(data) && notifica.getOraInvio().toString().equals(ora)){    //controlla se 'notifica' è la notifica da eliminare
                listaNotifiche.remove(i);   //elimina la notifica 'notifica' da 'listaNotifiche'
                i = listaNotifiche.size();  //assegna a 'i' la dimensione di 'listaNotifiche'
            }
        }
    }//fine rimuoviNotifica

    /**
     * Visualizza notifica.
     *
     * @param testo the testo
     * @param data  the data
     * @param ora   the ora
     */
    public void visualizzaNotifica(String testo, String data, String ora){  //pone a "true" il campo lettura della notifica con 'testo' inviata all'utente il 'data' alle 'ora'
        NotificaDAO n = new NotificaImplementazionePostgresDAO();

        n.visualizzaNotificaDB(testo, data, ora, utente.getUsername());  //pone a "true" il campo lettura della notifica nel DB

        for (int i = 0; i < listaNotifiche.size(); i++){    //scorre 'listaNotifiche'
            Notifica notifica = listaNotifiche.get(i);  //i-esima notifica in 'listaNotifica'

            if (notifica.getTesto().equals(testo) && notifica.getDataInvio().toString().equals(data) && notifica.getOraInvio().toString().equals(ora)){    //controlla se 'notifica' è la notifica da aggiornare
                listaNotifiche.get(i).setLettura(true);   //pone a "true" l'attributo lettura della notifica 'notifica' in 'listaNotifiche'
                i = listaNotifiche.size();  //assegna a 'i' la dimensione di 'listaNotifiche'
            }
        }
    }//fine visualizzaNotifiche

    /**
     * Gets lettura notifica.
     *
     * @param testo the testo
     * @param data  the data
     * @param ora   the ora
     * @return the lettura notifica
     */
    public boolean getLetturaNotifica(String testo, String data, String ora){   //ritorna il valore della lettura della notifica con 'testo' inviata all'utente il 'data' alle 'ora'
        for(Notifica n: listaNotifiche){    //scorre 'listaNotifiche'
            if(n.getTesto().equals(testo) && n.getDataInvio().toString().equals(data) && n.getOraInvio().toString().equals(ora)){  //controlla se la notifica attuale 'n' è la notifica con 'testo' inviata all'utente il 'data' alle 'ora'
                return n.getLettura();
            }
        }

        return true;
    }//fine getLetturaNotifica

    /**
     * Gets possesso libreria.
     */
// POSSESSO //
    public void getPossessoLibreria(){  //aggiorna 'possessoLLibreria','possessoSLibreria','possessoFLibreria','titoloLibriLibreria','titoloSerieLibreria' e 'fascicoliLibreria'
        possessoLLibreria.clear();  //svuota 'possessoLLibreria'
        possessoSLibreria.clear();  //svuota 'possessoSLibreria'
        possessoFLibreria.clear();  //svuota 'possessoFLibreria'
        titoloLibriLibreria.clear();    //svuota 'titoloLibriLibreria'
        titoloSerieLibreria.clear();    //svuota 'titoloSerieLibreria'
        fascicoliLibreria.clear();  //svuota 'fascicoliLibreria'

        getPossessoLLibreria(); //aggiorna 'titoloLibriLibreria' e 'possessoLLibreria'
        getPossessoSLibreria(); //aggiorna 'titoloSerieLibreria' e 'possessoSLibreria'
        getPossessoFLibreria(); //aggiorna  'fascicoliLibreria' e 'possessoFLibreria'
    }//fine getPossessoLibreria

    /**
     * Gets possesso l libreria.
     */
    public void getPossessoLLibreria(){ //aggiorna 'titoloLibriLibreria' con gli ISBN e i titoli dei libri posseduti e 'possessoLLibreria' con i possessi dei libri della libreria selezionata
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoLLibreriaDB(libreria_selected.getNome(), libreria_selected.getSitoWeb(), libreria_selected.getIndirizzo(), utente.getUsername());   //cerca i possessi dei libri della libreria selezionata gestista dall'utente

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i possesi
                Libro l = new Libro(rs.getString("isbn"), rs.getString("genere"), rs.getString("editore"), rs.getString("lingua"), rs.getString("titolo"), rs.getDate("datapubblicazione"));    //libro corrente

                titoloLibriLibreria.add(l.getISBN() + " - " + l.getTitolo()); //aggiunge l'ISBN e il titolo di 'l' in 'titoloLibriLibreria'
                possessoLLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), l, libreria_selected));   //inserisce un nuovo possesso in 'possessoLLibreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }
    }//fine getPossessoLLibreria

    /**
     * Gets possesso s libreria.
     */
    public void getPossessoSLibreria(){ //aggiorna 'titoloSerieLibreria' con gli ISBN e i titoli delle serie possedute e 'possessoSLibreria' con i possessi delle serie della libreria selezionata
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoSLibreriaDB(libreria_selected.getNome(), libreria_selected.getSitoWeb(), libreria_selected.getIndirizzo(), utente.getUsername());   //cerca i possessi delle serie della libreria selezionata gestista dall'utente

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i possessi
                Serie s = new Serie(rs.getString("isbn"), rs.getInt("nlibri"), rs.getString("titolo"), rs.getDate("datapubblicazione"));    //serie corrente

                titoloSerieLibreria.add(s.getISBN() + " - " + s.getTitolo()); //aggiunge l'ISBN e il titolo di 's' in 'titoloSerieLibreria'
                possessoSLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), s, libreria_selected));   //inserisce un nuovo possesso in 'possessoSLibreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }
    }//fine getPossessoSLibreria

    /**
     * Gets possesso f libreria.
     */
    public void getPossessoFLibreria(){ //aggiorna 'fascicoliLibreria' con i fascicoli posseduti e 'possessoSLibreria' con i possessi dei fascicoli della libreria selezionata
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        ResultSet rs = p.getPossessoFLibreriaDB(libreria_selected.getNome(), libreria_selected.getSitoWeb(), libreria_selected.getIndirizzo(), utente.getUsername());   //cerca i possessi delle serie della libreria selezionata gestista dall'utente

        try {
            while(rs.next()){    //scorre il ResultSet 'libri' contenente i libri
                Fascicolo f = new Fascicolo(rs.getInt("numero"), new Rivista(rs.getString("issn"), rs.getString("titolo"), rs.getString("editore"), rs.getInt("annopubblicazione"), rs.getString("nomer") + " " + rs.getString("cognomer"), rs.getString("argomento")) ,rs.getDate("datapubblicazione"));   //libro corrente

                fascicoliLibreria.add(f);   //aggiunge 'f' in 'fascicoliLibreria'
                possessoFLibreria.add(new Possesso(rs.getString("fruizione"), rs.getInt("quantita"), f, libreria_selected));   //inserisce un nuovo possesso in 'possessoFLibreria'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        p.chiudiConnessione();  //chiude la connessione al DB
    }//fine getPossessoFLibreria

    /**
     * Mod quantita libro.
     *
     * @param fruizione the fruizione
     * @param value     the value
     */
    public void modQuantitaLibro(String fruizione, int value){  //aggiorna a 'value' la quantita disponibile nella libreria selezionata in modalità di fruizione 'fruizione' del libro selezionato
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.modQuantitaLibroDB(isbn_selected, libreria_selected.getNumeroTelefonico(), fruizione, value);
    }//fine modQuantitaLibro

    /**
     * Mod quantita fascicolo.
     *
     * @param fruizione the fruizione
     * @param value     the value
     */
    public void modQuantitaFascicolo(String fruizione, int value){  //aggiorna a 'value' la quantita disponibile nella libreria selezionata in modalità di fruizione 'fruizione' del fascicolo selezionato
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.modQuantitaFascicoloDB(fascicolo_selected.getRivista().getISSN(), fascicolo_selected.getNumero(), libreria_selected.getNumeroTelefonico(), fruizione, value);
    }//fine modQuantitaFascicolo

    /**
     * Insert possesso l boolean.
     *
     * @param quantita  the quantita
     * @param fruizione the fruizione
     * @return the boolean
     */
    public boolean insertPossessoL(int quantita, String fruizione){ //aggiunge un nuovo possesso con il nuovo libro della libreria selezionata
        boolean presenzaPossessoL = false;
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        return presenzaPossessoL = p.insertPossessoLDB(nuovoLibro.getISBN(), libreria_selected.getNumeroTelefonico(), quantita, fruizione);
    }//fine insertPossessoL

    /**
     * Insert possesso f boolean.
     *
     * @param quantita  the quantita
     * @param fruizione the fruizione
     * @return the boolean
     */
    public boolean insertPossessoF(int quantita, String fruizione){ //aggiunge un nuovo possesso con il nuovo fascicolo della libreria selezionata
        boolean presenzaPossessoF = false;
        PossessoDAO p = new PossessoImplementazionePostgresDAO();
        return presenzaPossessoF = p.insertPossessoFDB(nuovoFascicolo.getNumero(), nuovoFascicolo.getRivista().getISSN(), libreria_selected.getNumeroTelefonico(), quantita, fruizione);
    }//fine insertPossessoF

    /**
     * Elimina possesso l.
     *
     * @param fruizione the fruizione
     */
    public void eliminaPossessoL(String fruizione){ //elimina il possesso con il libro selezionato in modalità di fruizione 'fruizione' della libreria selezionata
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.eliminaPossessoLDB(isbn_selected, libreria_selected.getNumeroTelefonico(), fruizione);
    }//fine eliminaPossessoL

    /**
     * Elimina possesso f.
     *
     * @param fruizione the fruizione
     */
    public void eliminaPossessoF(String fruizione){ //elimina il possesso con il fascicolo selezionato in modalità di fruizione 'fruizione' della libreria selezionata
        PossessoDAO p = new PossessoImplementazionePostgresDAO();

        p.eliminaPossessoFDB(fascicolo_selected.getRivista().getISSN(), fascicolo_selected.getNumero(), libreria_selected.getNumeroTelefonico(), fruizione);
    }//fine eliminaPossessoF

    /**
     * Gets conferenze articolo.
     */
// CONFERENZA //
    public void getConferenzeArticolo(){    //ritorna i dati di tutte le conferenze dell'articolo selezionato
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        ArrayList<Conferenza> conferenze = new ArrayList<>(); //contiene tutte le conferenze dell'articolo selezionato
        ResultSet rs = c.getConferenzeArticoloDB(doi_selected); //cerca i dati di tutte le conferenze dell'articolo selezionato

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le conferenze dell'articolo selezionato
                conferenze.add(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine")));   //inserisce una nuova conferenza in 'conferenza'
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione al DB

        listaConferenze = conferenze;   //aggiorna getConferenzeArticolo
    }//fine getConferenzeArticolo

    /**
     * Gets conferenze.
     */
    public void getConferenze(){    //ritorna i dati di tutte le conferenze evitando duplicati
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        ArrayList<Conferenza> conferenze = new ArrayList<>(); //contiene le conferenze
        ResultSet rs = c.getConferenzeDB(); //cerca i dati di tutte le conferenze

        try {
            while(rs.next()){    //scorre il ResultSet 'rs' contenente le conferenze
                if (!conferenze.contains(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine"))) && anno_pubblicazione <= rs.getDate("datainizio").getYear()+1900) {  //controlla se 'conferenze' non contiene la cinferenza attuale del ResultSet 'rs'
                    conferenze.add(new Conferenza(rs.getString("strutturaorganizzatrice"), rs.getString("luogo"), rs.getDate("datainizio"), rs.getDate("datafine")));   //inserisce una nuova presentazione in 'Presentazioni'
                }
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        c.chiudiConnessione();  //chiude la connessione al DB

        listaAllConferenze = conferenze;    //aggiorna 'listaAllConfereze'
    }//fine getConfereze

    /**
     * Add conferenza boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param datai     the datai
     * @param dataf     the dataf
     * @return the boolean
     */
    public boolean addConferenza(String struttura, String luogo, String datai, String dataf){   //se aggiunge una nuova conferenza ritorna "true", altrimenti ritorna "false"
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        return c.addConferenzaDB(struttura, luogo, datai, dataf);
    }//fine addConferenza

    /**
     * Add esposizione boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param datai     the datai
     * @param dataf     the dataf
     * @return the boolean
     */
    public boolean addEsposizione(String struttura, String luogo, String datai, String dataf){  //se aggiunge una nuova esposizione ritorna "true", altrimenti ritorna "false"
        ConferenzaDAO c = new ConferenzaImplementazionePostgresDAO();
        return c.addEsposizioneDB(struttura, luogo, datai, dataf, doi_selected);
    }//fine addEsposizione

    /**
     * Init dimension int.
     *
     * @return the int
     */
// FUNZIONI AGGIUNTIVE //
    public int initDimension(){ //inizializza 'screenWidth' con la larghezza dello schermo e ritorna l'altezza
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //dimensioni dello schermo

        screenWidth = (int) screenSize.width;   //inizializza 'screenWidth'
        return (int) screenSize.getHeight();
    }//fine initDimension

    /**
     * Gets font title impact size.
     *
     * @return the font title impact size
     */
    public int getFontTitleImpactSize(){    //calcola la dimensione del font "TitleImpact" in base alle dimensioni dello schermo
        int fontSize = Math.min(screenWidth, screenHeight) / 33;    //dimensione del font
        return fontSize;
    }//fine getFontTitleImpactSize

    /**
     * Gets font size.
     *
     * @return the font size
     */
    public int getFontSize(){   //calcola la dimensione del font in base alle dimensioni dello schermo
        int fontSize = Math.min(screenWidth, screenHeight) / 50;    //dimensione del font
        return fontSize;
    }//fine getFontSize

    /**
     * Calcola altezza font int.
     *
     * @param font the font
     * @return the int
     */
    public int calcolaAltezzaFont(Font font){   //calcola l'altezza del font 'font'
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB); //crea un'immagine
        Graphics2D g2d = image.createGraphics();    //Graphics assocciata alla BufferedImage 'image'

        g2d.setFont(font);  //imposta il font di g2d

        FontMetrics fm = g2d.getFontMetrics();  //informazioni sulle metriche del font del Graphics g2d
        int fontHeight = fm.getHeight();    //altezza del font del Graphics g2d

        g2d.dispose();
        return fontHeight;
    }//fine calcolaAltezzaFont
}