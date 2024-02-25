package DAO;

import java.sql.ResultSet;

/**
 * The interface Utente dao.
 */
public interface UtenteDAO {
    /**
     * Add utente db.
     *
     * @param email       the email
     * @param nome        the nome
     * @param cognome     the cognome
     * @param Username    the username
     * @param password    the password
     * @param dataNascita the data nascita
     * @param partitaIVA  the partita iva
     */
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);    //inserisce un nuovo utente nel DB

    /**
     * Valida utente db int.
     *
     * @param userEmail the user email
     * @param password  the password
     * @return the int
     */
    public int validaUtenteDB(String userEmail, String password);   //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'

    /**
     * Gets utente db.
     *
     * @param userEmail the user email
     * @param password  the password
     * @return the utente db
     */
    public ResultSet getUtenteDB(String userEmail, String password);    //cerca i dati  nel DB dell'utente con 'userEmail' e 'password'

    /**
     * Gets utente db.
     *
     * @param username the username
     * @return the utente db
     */
    public ResultSet getUtenteDB(String username);    //cerca i dati trovati nel DB dell'utente con 'username'

    /**
     * Valida mod email db int.
     *
     * @param emailM the email m
     * @return the int
     */
    public int validaModEmailDB(String emailM); //ritorna il numero di utenti con email 'emailM' presenti nel DB

    /**
     * Valida mod username db int.
     *
     * @param usernameM the username m
     * @return the int
     */
    public  int validaModUsernameDB(String usernameM);  //ritorna il numero di utenti con username 'usernameM' presenti nel DB

    /**
     * Valida mod pivadb int.
     *
     * @param pIVAM the p ivam
     * @return the int
     */
    public  int validaModPIVADB(String pIVAM);  //ritorna il numero di utenti con partita IVA 'pIVAM' presenti nel DB

    /**
     * Mod utente db.
     *
     * @param email       the email
     * @param nome        the nome
     * @param cognome     the cognome
     * @param username    the username
     * @param password    the password
     * @param partitaIVA  the partita iva
     * @param oldUsername the old username
     */
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername); //modifica i dati nel DB dell'utente con l'username 'oldUsername'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}