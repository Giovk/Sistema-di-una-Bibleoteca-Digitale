package ImplementazionePostgresDAO;

import DAO.RivistaDAO;
import Database.ConnessioneDatabase;
import Model.Rivista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ResultSet getRivisteDB(){
        ResultSet rs = null; //serie trovate

        try {
            PreparedStatement getRivistePS = connection.prepareStatement(
                    "SELECT * FROM rivista"   //prepara la query che cerca tutte le serie
            );

            rs = getRivistePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap){
        ResultSet rs = null;
        try {
            PreparedStatement creaRivistaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM rivista WHERE issn = '"+issn+"'"
            );
            rs = creaRivistaPS.executeQuery();

            try {
                while(rs.next()){
                    if(rs.getInt("contatore") == 0){
                        try{
                            creaRivistaPS = connection.prepareStatement(
                                    "INSERT INTO rivista(issn, titolo, argomento, editore, nomer, cognomer, annopubblicazione) " +
                                            "VALUES ('"+issn+"', '"+titolo+"', '"+argomento+"', '"+editore+"', '"+nomeR+"', '"+cognomeR+"', "+ap+")"
                            );

                            creaRivistaPS.executeUpdate();
                            chiudiConnessione();
                            return true;
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } else {
                        chiudiConnessione();
                        return false;
                    }
                }
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
    }

    public void eliminaRivistaDB(String issn){
        try{
            PreparedStatement eliminaRivistaPS = connection.prepareStatement(
                    "DELETE FROM rivista WHERE issn = '"+issn+"'"
            );

            eliminaRivistaPS.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
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
