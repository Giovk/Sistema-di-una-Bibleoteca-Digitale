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
