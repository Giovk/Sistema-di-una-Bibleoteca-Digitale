package DAO;

import Model.Utente;

import java.sql.ResultSet;

public interface UtenteDAO {
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);//inserisce un nuovo utente nel DB
    public int validaUtenteDB(String userEmail, String password);//ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
    public ResultSet getUtenteDB(String userEmail, String password);
    public int validaModEmailDB(String emailM);
    public  int validaModUsernameDB(String usernameM);
    public  int validaModPIVADB(String pIVAM);
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername);
    public void chiudiConnessione();
}
