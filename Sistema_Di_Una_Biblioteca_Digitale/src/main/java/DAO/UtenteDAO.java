package DAO;

import Model.Utente;

import java.sql.ResultSet;

public interface UtenteDAO {
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);
    public int validaUtenteDB(String userEmail, String password);
    public ResultSet getUtenteDB(String userEmail, String password);
    public void chiudiConnessione();
}
