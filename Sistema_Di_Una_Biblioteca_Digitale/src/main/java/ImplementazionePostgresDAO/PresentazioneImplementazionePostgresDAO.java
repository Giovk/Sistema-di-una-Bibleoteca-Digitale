package ImplementazionePostgresDAO;

import DAO.PresentazioneDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe PresentazioneImplementazionePostgresDAO implementa l'interfaccia PresentazioneDAO, quindi contiene le implementazioni dei metodi che
 * interagiscono con il database per implementare le funzionalità relative alle presentazioni dei libri.
 */
public class PresentazioneImplementazionePostgresDAO implements PresentazioneDAO {
    private Connection connection;

    /**
     * Istanzia una nuova PresentazioneImplementazionePostgresDAO.
     */
    public PresentazioneImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getPresentazioneDB(String isbn){   //cerca i dati di tutte le presentazioni del libro con ISBN 'isbn' nel DB
        ResultSet rs = null;    //contiene tutte le presentazionni trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM presentazione WHERE isbn = '"+isbn+"';"  //prepara la query che cerca tutte le presentazioni del libro con ISBN 'isbn' nel DB
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getPresentazioneDB

    @Override
    public boolean addPresentazioneDB(String struttura, String luogo, String data, String orario, String isbn){ //se non esiste già, inserisce una nuova presentazione nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di presentazioni del libro con ISBN 'isbn' organizzate da 'struttura' il 'data' alle 'orario' a 'luogo'

        try {
            PreparedStatement addPresentazionePS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM presentazione WHERE struttura = '"+struttura+"' AND luogo = '"+luogo+"' AND datap = '"+data+"' AND" +
                            " ora = '"+orario+"'"   //prepara la query che conta le presentazioni del libro con ISBN 'isbn' organizzate da 'struttura' il 'data' alle 'orario' a 'luogo'
            );

            rs = addPresentazionePS.executeQuery(); //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di presentazioni del libro con ISBN 'isbn' organizzate da 'struttura' il 'data' alle 'orario' a 'luogo'
                    if (rs.getInt("contatore") >= 1){   //controlla se ci sono presentazioni del libro con ISBN 'isbn' organizzate da 'struttura' il 'data' alle 'orario' a 'luogo'
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addPresentazionePS = connection.prepareStatement(
                    "INSERT INTO presentazione(isbn, struttura, luogo, datap, ora) " +
                            "VALUES('"+isbn+"', '"+struttura+"', '"+luogo+"', '"+data+"', '"+orario+"')"    //prepara la query che inserisce la nuova presentazione
            );

            addPresentazionePS.executeUpdate(); //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine addPresentazioneDB

    @Override
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