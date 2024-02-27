package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia UtenteDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relativi agli
 * utenti.
 */
public interface UtenteDAO {
    /**
     * Aggiunge un nuovo utente nel database con le informazioni passate come parametro
     *
     * @param email       l'email del nuovo utente
     * @param nome        il nome del nuovo utente
     * @param cognome     il cognome del nuovo utente
     * @param Username    l'username del nuovo utente
     * @param password    la password del nuovo utente
     * @param dataNascita la data di nascita del nuovo utente
     * @param partitaIVA  la partita IVA del nuovo utente
     */
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);    //inserisce un nuovo utente nel DB

    /**
     * Conta gli utenti nel database con le stesse credenziali passate come parametri.
     *
     * @param userEmail l'username o l'email dell'utente che sta effettuando l'accesso
     * @param password  la password dell'utente che sta effettuando l'accesso
     * @return il numero di utenti con le stesse credenziali inserite
     */
    public int validaUtenteDB(String userEmail, String password);   //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'

    /**
     * Ritorna tutte le informazioni nel database dell'utente con le credenziali passate come parametri.
     *
     * @param userEmail l'username o l'email dell'utente
     * @param password  la password dell'utente
     * @return il ResultSet con le informazioni dell'utente trovate nel database
     */
    public ResultSet getUtenteDB(String userEmail, String password);    //cerca i dati  nel DB dell'utente con 'userEmail' e 'password'

    /**
     * Ritorna tutte le informazioni nel database dell'utente con l'username passato come parametro.
     *
     * @param username l'username dell'utente
     * @return il ResultSet con le informazioni dell'utente trovate nel database
     */
    public ResultSet getUtenteDB(String username);    //cerca i dati trovati nel DB dell'utente con 'username'

    /**
     * Conta gli utenti nel database con l'email passata come parametro.
     *
     * @param emailM l'email inserita
     * @return il numero di utenti con l'email inserita
     */
    public int validaModEmailDB(String emailM); //ritorna il numero di utenti con email 'emailM' presenti nel DB

    /**
     * Conta gli utenti nel database con l'username passato come parametro.
     *
     * @param usernameM l'username inserito
     * @return il numero di utenti con l'username inserito
     */
    public  int validaModUsernameDB(String usernameM);  //ritorna il numero di utenti con username 'usernameM' presenti nel DB

    /**
     * Conta gli utenti nel database con la artita IVA passata come parametro.
     *
     * @param pIVAM la partita IVA inserita
     * @return il numero di utenti con la partita IVA inserita
     */
    public  int validaModPIVADB(String pIVAM);  //ritorna il numero di utenti con partita IVA 'pIVAM' presenti nel DB

    /**
     * Modifica nel database le informazioni dell'utente con l'username uguale al parametro 'oldUsername'.
     *
     * @param email       la nuova email
     * @param nome        il nome
     * @param cognome     il cognome
     * @param username    l'username
     * @param password    la password
     * @param partitaIVA  la partita IVA
     * @param oldUsername l'username dell'utente che sta modificando le proprie informazioni
     */
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername); //modifica i dati nel DB dell'utente con l'username 'oldUsername'

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}