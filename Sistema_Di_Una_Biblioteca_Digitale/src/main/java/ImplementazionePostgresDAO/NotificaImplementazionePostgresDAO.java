package ImplementazionePostgresDAO;

import DAO.NotificaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe NotificaImplementazionePostgresDAO implementa l'interfaccia NotificaDAO, quindi contiene le implementazioni dei metodi che interagiscono
 * con il database per implementare le funzionalità relative alle notifiche
 */
public class NotificaImplementazionePostgresDAO implements NotificaDAO {
    private Connection connection;

    /**
     * Istanzia una nuova NotificaImplementazionePostgresDAO.
     */
    public NotificaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public int getNumeroNotificheNonLetteDB(String user){   //calcola il numero di notifiche non lette dall'utente 'user'
        ResultSet rs;   //contiene il numero di notifiche non lette dall'utente 'user'
        int n = 0;  //numero di notifiche non lette dall'utente 'user'

        try{
            PreparedStatement getNumeroNotificheNonLettePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM notifica WHERE username = '"+user+"' AND lettura = FALSE;"  //prepara la query che conta le notifiche non lette dall'utente 'user'
            );

            rs = getNumeroNotificheNonLettePS.executeQuery();   //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' con il numero di notifiche non lette dall'utente 'user'
                n = rs.getInt("total"); //aggiorna 'n'
            }

            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }//fine getNumeroNotificheNonLetteDB

    public ResultSet getNotificheUtenteDB(String user){ //cerca i dati di tutte le notifiche dell'utente 'user' nel DB
        ResultSet rs = null;    //contiene tutte le notifiche dell'utente 'user' nel DB

        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "SELECT * FROM notifica WHERE username = '"+user+"'"   //prepara la query che cerca tutti le notifiche dell'utente 'user' nel DB
            );

            rs = getNotificheUtenteDB.executeQuery();   //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getNotificheUtenteDB

    @Override
    public void rimuoviNotificaDB(String testo, String data, String ora, String user){  //elimina dal DB la notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "DELETE FROM notifica WHERE username = '"+user+"' AND datainvio = '"+data+"' AND orainvio = '"+ora+"' AND testo = '"+testo+"'"   //prepara che elimina la notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
            );

            getNotificheUtenteDB.executeUpdate();   //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine rimuoviNotificaDB

    @Override
    public void visualizzaNotificaDB(String testo, String data, String ora, String user){   //pone a "true" il campo "lettura" della notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "UPDATE notifica SET lettura = TRUE WHERE username = '"+user+"' AND datainvio = '"+data+"' AND orainvio = '"+ora+"' AND testo = '"+testo+"'"   //prepara la query che /pone a "true" il campo "lettura" della notifica inviata all'utente 'user' con 'testo' il 'data' alle 'ora'
            );

            getNotificheUtenteDB.executeUpdate();   //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine visualizzaNotificaDB

    /**
     * Chiudi la connessione al database.
     */
    public void chiudiConnessione(){    //chiude la connessione al DB
        try{
            if (connection != null && !connection.isClosed()){  //controlla se la connessione è chiusa
                connection.close(); //chiude la connessione
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//fine chiudiConnessione
}