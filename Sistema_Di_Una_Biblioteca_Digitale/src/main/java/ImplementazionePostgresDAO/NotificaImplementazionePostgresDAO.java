package ImplementazionePostgresDAO;

import DAO.NotificaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificaImplementazionePostgresDAO implements NotificaDAO {
    private Connection connection;

    public NotificaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public int getNumeroNotificheNonLetteDB(String user){
        ResultSet rs;
        int n = 0;  //numero di utenti nel DB con 'userEmail' e 'password'

        try{
            PreparedStatement getNumeroNotificheNonLettePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM notifica WHERE username = '"+user+"' AND lettura = FALSE;"  //prepara la query che conta il numero di utenti con 'userEmail' e 'password'
            );

            rs = getNumeroNotificheNonLettePS.executeQuery(); //esegue la query

            while(rs.next()){
                n = rs.getInt("total"); //inserisce in 'n' il risltato della query
            }

            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }
        return n;
    }

    public ResultSet getNotificheUtenteDB(String user){
        ResultSet rs = null;

        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "SELECT * FROM notifica WHERE username = '"+user+"'"   //prepara la query che cerca tutti i libri
            );

            rs = getNotificheUtenteDB.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public void rimuoviNotificaDB(String testo, String data, String ora, String user){
        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "DELETE FROM notifica WHERE username = '"+user+"' AND datainvio = '"+data+"' AND orainvio = '"+ora+"' AND testo = '"+testo+"'"   //prepara la query che cerca tutti i libri
            );

            getNotificheUtenteDB.executeUpdate(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void visualizzaNotificaDB(String testo, String data, String ora, String user){
        try {
            PreparedStatement getNotificheUtenteDB = connection.prepareStatement(
                    "UPDATE notifica SET lettura = TRUE WHERE username = '"+user+"' AND datainvio = '"+data+"' AND orainvio = '"+ora+"' AND testo = '"+testo+"'"   //prepara la query che cerca tutti i libri
            );

            getNotificheUtenteDB.executeUpdate(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void chiudiConnessione(){    //chiude la connessione al DB
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
