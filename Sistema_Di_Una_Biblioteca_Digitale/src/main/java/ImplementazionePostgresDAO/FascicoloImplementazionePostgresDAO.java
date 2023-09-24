package ImplementazionePostgresDAO;

import DAO.FascicoloDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FascicoloImplementazionePostgresDAO implements FascicoloDAO {
    private Connection connection;

    public FascicoloImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getFascicoliDB(){
        ResultSet rs = null;

        try {
            PreparedStatement getFascicoliPS = connection.prepareStatement(
                    "SELECT * FROM fascicolo"   //prepara la query che cerca tutte le serie
            );

            rs = getFascicoliPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getFascicoliDB(String user){
        ResultSet rs = null;

        try {
            PreparedStatement getFascicoliPS = connection.prepareStatement(
                    "SELECT * FROM fascicolo NATURAL JOIN recensione_f WHERE username = '"+user+"' AND preferito = true"   //prepara la query che cerca tutte le serie
            );

            rs = getFascicoliPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }
    @Override
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero){
        ResultSet rs = null;

        try {
            PreparedStatement getInfoLibriPreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Libreria NATURAL JOIN possesso_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE issn = '"+rivista+"' AND numero = "+numero+";"   //prepara la query che cerca tutti i libri della serie
            );

            rs = getInfoLibriPreferitiPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
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
