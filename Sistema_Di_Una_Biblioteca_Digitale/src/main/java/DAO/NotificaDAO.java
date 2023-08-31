package DAO;

import java.sql.ResultSet;

public interface NotificaDAO {
    public int getNumeroNotificheNonLetteDB(String user);
    public ResultSet getNotificheUtenteDB(String user);
    public void rimuoviNotificaDB(String testo, String data, String ora, String user);
    public void visualizzaNotificaDB(String testo, String data, String ora, String user);
    public void chiudiConnessione();    //chiude la connessione al DB
}
