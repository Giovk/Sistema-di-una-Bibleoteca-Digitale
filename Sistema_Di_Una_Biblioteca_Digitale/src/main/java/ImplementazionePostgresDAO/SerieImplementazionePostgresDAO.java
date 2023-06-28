package ImplementazionePostgresDAO;

import DAO.SerieDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SerieImplementazionePostgresDAO implements SerieDAO {

    private Connection connection;

    public  SerieImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getSerieDB() { //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getSeriePS = connection.prepareStatement(
                    "SELECT * FROM serie"   //prepara la query che cerca tutti i libri
            );

            rs = getSeriePS.executeQuery(); //esegue la query
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
