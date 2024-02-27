package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia NotificaDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative alle
 * notifiche.
 */
public interface NotificaDAO {
    /**
     * Calcola il numero di notifiche presenti nel database, che non sono state lette dell'utente con l'username uguale a quello passato come
     * parametro.
     *
     * @param user l'username dell'utente
     * @return il numero di notifiche non lette dall'utente
     */
    public int getNumeroNotificheNonLetteDB(String user);   //calcola il numero di notifiche non lette dall'utente 'user'

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le notifiche dell'utente con l'username uguale a quello passato come parametro
     *
     * @param user l'username dell'utente
     * @return il ResultSet con le informazioni trovate nel database delle notifiche dell'utente
     */
    public ResultSet getNotificheUtenteDB(String user); //cerca i dati di tutte le notifiche dell'utente 'user' nel DB

    /**
     * Rimuve dal database la notifica con il testo, la data e l'ora di invio presi come parametri, dell'utente che ha come username quello passato
     * come parametro.
     *
     * @param testo il testo della notifica da eliminare
     * @param data  la data della notifica da eliminare
     * @param ora   l'ora della notifica da eliminare
     * @param user  l'username della notifica da eliminare
     */
    public void rimuoviNotificaDB(String testo, String data, String ora, String user);  //elimina dal DB la notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'

    /**
     * Segna nel database come letta la notifica con il testo, la data e l'ora di invio presi come parametri, dell'utente che ha come username quello
     * passato come parametro.
     *
     * @param testo il testo della notifica
     * @param data  la data della notifica
     * @param ora   l'ora della notifica
     * @param user  l'username della notifica
     */
    public void visualizzaNotificaDB(String testo, String data, String ora, String user);   //pone a "true" il campo "lettura" della notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
}