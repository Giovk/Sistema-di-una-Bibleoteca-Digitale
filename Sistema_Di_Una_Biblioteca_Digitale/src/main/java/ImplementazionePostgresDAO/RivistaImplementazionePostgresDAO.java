package ImplementazionePostgresDAO;

import DAO.RivistaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RivistaImplementazionePostgresDAO implements RivistaDAO {
    private Connection connection;

    public RivistaImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getRivisteDB(){    //cerca i dati di tutti le riviste nel DB
        ResultSet rs = null;    //contiene tutte le riviste trovate

        try {
            PreparedStatement getRivistePS = connection.prepareStatement(
                    "SELECT * FROM rivista" //prepara la query che cerca tutte le riviste
            );

            rs = getRivistePS.executeQuery();   //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getRivisteDB

    @Override
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap){  //se non esiste già, inserisce una nuova rivista nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di riviste con ISSN 'issn'

        try {
            PreparedStatement creaRivistaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM rivista WHERE issn = '"+issn+"'" //prepara la query che conta le riviste con ISSN 'issn'
            );

            rs = creaRivistaPS.executeQuery();  //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di riviste con ISSN 'issn'
                    if(rs.getInt("contatore") != 0){    //controlla se ci sono riviste con ISSN 'issn'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    } else {
                        try{
                            creaRivistaPS = connection.prepareStatement(
                                    "INSERT INTO rivista(issn, titolo, argomento, editore, nomer, cognomer, annopubblicazione) " +
                                            "VALUES ('"+issn+"', '"+titolo+"', '"+argomento+"', '"+editore+"', '"+nomeR+"', '"+cognomeR+"', "+ap+")"    //prepara la query che inserisce la nuova rivista
                            );

                            creaRivistaPS.executeUpdate();  //esegue la query
                            chiudiConnessione();    //chiude la connessione al DB
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
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
    }//fine creaRivistaDB

    public void eliminaRivistaDB(String issn){  //elimina dal DB la rivista con ISSN 'issn'
        try{
            PreparedStatement eliminaRivistaPS = connection.prepareStatement(
                    "DELETE FROM rivista WHERE issn = '"+issn+"'"   //prepara laquery che elimina la rivista con ISSN 'issn'
            );

            eliminaRivistaPS.executeUpdate();   //esegue la query
        }catch (SQLException e){
            e.printStackTrace();
        }
    }//fine eliminaRivistaDB

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