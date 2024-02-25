package DAO;

import java.sql.ResultSet;

/**
 * The interface Notifica dao.
 */
public interface NotificaDAO {
    /**
     * Gets numero notifiche non lette db.
     *
     * @param user the user
     * @return the numero notifiche non lette db
     */
    public int getNumeroNotificheNonLetteDB(String user);   //calcola il numero di notifiche non lette dall'utente 'user'

    /**
     * Gets notifiche utente db.
     *
     * @param user the user
     * @return the notifiche utente db
     */
    public ResultSet getNotificheUtenteDB(String user); //cerca i dati di tutte le notifiche dell'utente 'user' nel DB

    /**
     * Rimuovi notifica db.
     *
     * @param testo the testo
     * @param data  the data
     * @param ora   the ora
     * @param user  the user
     */
    public void rimuoviNotificaDB(String testo, String data, String ora, String user);  //elimina dal DB la notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'

    /**
     * Visualizza notifica db.
     *
     * @param testo the testo
     * @param data  the data
     * @param ora   the ora
     * @param user  the user
     */
    public void visualizzaNotificaDB(String testo, String data, String ora, String user);   //pone a "true" il campo "lettura" della notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
}