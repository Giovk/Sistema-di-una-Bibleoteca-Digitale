package DAO;

import java.sql.ResultSet;

public interface UtenteDAO {
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);    //inserisce un nuovo utente nel DB
    public int validaUtenteDB(String userEmail, String password);   //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
    public ResultSet getUtenteDB(String userEmail, String password);    //ritorna i dati trovati nel DB dell'utente con 'userEmail' e 'password'
    public ResultSet getUtenteDB(String username);    //ritorna i dati trovati nel DB dell'utente con 'username'
    public int validaModEmailDB(String emailM); //ritorna il numero di utenti con 'emailM' presenti nel DB
    public  int validaModUsernameDB(String usernameM);  //ritorna il numero di utenti con 'usernameM' presenti nel DB
    public  int validaModPIVADB(String pIVAM);  //ritorna il numero di utenti con 'pIVAM' presenti nel DB
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername); //modifica i dati nel DB dell'utente con l'username 'oldUsername'
    public void chiudiConnessione(); //chiude la connessione al DB
}
