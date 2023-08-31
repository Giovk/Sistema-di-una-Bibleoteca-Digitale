package ImplementazionePostgresDAO;

import DAO.ArticoloScientificoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloScientificoImplementazionePostgresDAO implements ArticoloScientificoDAO {
    private Connection connection;

    public ArticoloScientificoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getArticoliScientificiDB(String issn, int n){
        ResultSet rs = null;

        try {
            PreparedStatement getArticoliScientificiPS = connection.prepareStatement(
                    "SELECT * FROM articolo_scientifico NATURAL JOIN introduzione NATURAL JOIN fascicolo WHERE fascicolo.issn = '"+issn+"' AND fascicolo.numero = '"+n+"';"   //prepara la query che cerca tutte le serie
            );

            rs = getArticoliScientificiPS.executeQuery(); //esegue la query
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
