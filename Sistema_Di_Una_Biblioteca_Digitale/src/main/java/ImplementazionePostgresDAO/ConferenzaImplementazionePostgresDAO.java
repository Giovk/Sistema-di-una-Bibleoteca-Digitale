package ImplementazionePostgresDAO;

import DAO.ConferenzaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Conferenza implementazione postgres dao.
 */
public class ConferenzaImplementazionePostgresDAO implements ConferenzaDAO {
    private Connection connection;

    /**
     * Instantiates a new Conferenza implementazione postgres dao.
     */
    public ConferenzaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getConferenzeArticoloDB(String doi){   //cerca tutte le conferenze del articolo con DOI 'doi' nel DB
        ResultSet rs = null;    //contiene le conferenze del articolo con DOI 'doi' trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM conferenza NATURAL JOIN esposizione WHERE doi = '"+doi+"';"  //prepara la query che cerca tutte le conferenze del articolo con DOI 'doi'
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine     getConferenzeArticoloDB

    @Override
    public ResultSet getConferenzeDB(){   //cerca tutte le conferenze nel DB
        ResultSet rs = null;    //contiene le conferenze trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM conferenza;" //prepara la query che cerca tutte le conferenze
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getConferenzeDB

    @Override
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf){ //se non esiste già, inserisce una nuova confereza nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di conferenze organizzate dalla 'struttura' a 'luogo' dal 'datai' a 'dataf'

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM conferenza WHERE strutturaorganizzatrice = '"+struttura+"' AND luogo = '"+luogo+"' AND datainizio = '"+datai+"' AND" +
                            " datafine = '"+dataf+"'"   //prepara la query che conta le conferenze organizzate dalla 'struttura' a 'luogo' dal 'datai' a 'dataf'
            );

            rs = addConferenzaPS.executeQuery();    //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di conferenze organizzate dalla 'struttura' a 'luogo' dal 'datai' a 'dataf'
                    if (rs.getInt("contatore") >= 1){   //controlla se ci sono conferenze organizzate dalla 'struttura' a 'luogo' dal 'datai' a 'dataf'
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
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "INSERT INTO conferenza(strutturaorganizzatrice, luogo, datainizio, datafine) " +
                            "VALUES('"+struttura+"', '"+luogo+"', '"+datai+"', '"+dataf+"')"    //prepara la query che inserisce la nuova conferenza
            );

            addConferenzaPS.executeUpdate();    //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine addConferenzaDB

    @Override
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi){    //se non esiste già, inserisce una nuova esposizione nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il codice della conferenza della nuova esposizione e il numero di esposizioni con l'articolo con DOI 'doi' e la conferenza della nuova esposizione
        int codc = 0;   //codice della conferenza della nuova esposizione

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT codc FROM conferenza WHERE strutturaorganizzatrice = '"+struttura+"' AND luogo = '"+luogo+"' AND datainizio = '"+datai+"' AND" +
                            " datafine = '"+dataf+"'"   //prepara la query che cerca il codice della conferenza della nuova esposizione
            );

            rs = addConferenzaPS.executeQuery();    //esegue la query

            try{
                while (rs.next()){  //scorre il ResultSet 'rs' con il codice della conferenza della nuova esposizione
                    codc = rs.getInt("codc");   //aggiorna 'codc'
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM esposizione WHERE doi = '"+doi+"' AND codc = '"+codc+"'" //prepara la query che conta l'esposizioni con l'articolo con DOI 'doi' e la conferenza con codice 'codc'
            );

            rs = addConferenzaPS.executeQuery();    //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di esposizioni con l'articolo con DOI 'doi' e la conferenza con codice 'codc'
                    if (rs.getInt("contatore") >= 1){   //controlla se ci sono esposizioni con l'articolo con DOI 'doi' e la conferenza con codice 'codc'
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "INSERT INTO esposizione(doi, codc) " +
                            "VALUES('"+doi+"', '"+codc+"')" //prepara la query che inserisce la nuova esposizione
            );

            addConferenzaPS.executeUpdate();    //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine addEsposizioneDB

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