package ImplementazionePostgresDAO;

import DAO.FascicoloDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

/**
 * The type Fascicolo implementazione postgres dao.
 */
public class FascicoloImplementazionePostgresDAO implements FascicoloDAO {
    private Connection connection;

    /**
     * Instantiates a new Fascicolo implementazione postgres dao.
     */
    public FascicoloImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getFascicoliDB(){  //cerca tutti i fascicoli nel DB
        ResultSet rs = null;    //contiene tutti i fascicoli

        try {
            PreparedStatement getFascicoliPS = connection.prepareStatement(
                    "SELECT * FROM fascicolo"   //prepara la query che cerca tutti i fascicoli
            );

            rs = getFascicoliPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getFascicoliDB

    @Override
    public ResultSet getFascicoliDB(String user){   //cerca tutti i fascicoli prefertiti dell'utente nel DB
        ResultSet rs = null;    //contiene tutti i fascicoli preferiti dell'utente

        try {
            PreparedStatement getFascicoliPS = connection.prepareStatement(
                    "SELECT * FROM fascicolo NATURAL JOIN recensione_f WHERE username = '"+user+"' AND preferito = true"   //prepara la query che cerca tutti i fascicoli preferiti dell'utente
            );

            rs = getFascicoliPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getFascicoliDB

    @Override
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero){   //cerca nel DB il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'
        ResultSet rs = null;    //contiene il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'

        try {
            PreparedStatement getInfoLibriPreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Libreria NATURAL JOIN possesso_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE issn = '"+rivista+"' AND numero = "+numero+";"   //prepara la query che cerca il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'
            );

            rs = getInfoLibriPreferitiPS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getInfoFascicoliPreferitiDB

    @Override
    public boolean creaFascicoloDB(int numero, String data, String issn){   //se non esiste già, inserisce un nuovo fascicolo nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di fascicoli numero 'numero' della rivista con ISSN 'issn'

        try {
            PreparedStatement creaFascicoloPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'" //prepara la query che conta i fascicoli numero 'numero' della rivista con ISSN 'issn'
            );

            rs = creaFascicoloPS.executeQuery();    //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di fascicoli numero 'numero' della rivista con ISSN 'issn'
                    if(rs.getInt("contatore") == 0){    //controlla se non ci sono fascicoli numero 'numero' della rivista con ISSN 'issn'
                        try{
                            creaFascicoloPS = connection.prepareStatement(
                                    "INSERT INTO fascicolo(numero, datapubblicazione, issn) " +
                                            "VALUES ('"+numero+"', '"+data+"', '"+issn+"')" //preara la query che inserisce il nuovo fascicolo
                            );

                            creaFascicoloPS.executeUpdate();    //esegue la query
                            chiudiConnessione();    //chiude la connessione al DB
                            return true;
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } else {
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }

                rs.close(); //chiude 'rs'
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return false;
    }//fine creaFascicoloDB

    @Override
    public void eliminaFascicoloDB(String issn, int numero){    //elimina dal DB il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'
        try{
            PreparedStatement eliminaFascicoloPS = connection.prepareStatement(
                    "DELETE FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"   //prepara la query che elimina il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'
            );

            eliminaFascicoloPS.executeUpdate(); //esegue la query
        }catch (SQLException e){
            e.printStackTrace();
        }
    }//fine eliminaFascicoloDB

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