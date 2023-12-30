package DAO;

import java.sql.ResultSet;

public interface NotificaDAO {
    public int getNumeroNotificheNonLetteDB(String user);   //calcola il numero di notifiche non lette dall'utente 'user'
    public ResultSet getNotificheUtenteDB(String user); //cerca i dati di tutte le notifiche dell'utente 'user' nel DB
    public void rimuoviNotificaDB(String testo, String data, String ora, String user);  //elimina dal DB la notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
    public void visualizzaNotificaDB(String testo, String data, String ora, String user);   //pone a "true" il campo "lettura" della notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
}