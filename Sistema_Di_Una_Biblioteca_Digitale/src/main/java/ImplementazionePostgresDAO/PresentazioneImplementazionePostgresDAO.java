package ImplementazionePostgresDAO;

import DAO.PresentazioneDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PresentazioneImplementazionePostgresDAO implements PresentazioneDAO {
    private Connection connection;

    public PresentazioneImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getPresentazioneDB(String isbn){
        ResultSet rs = null;    //autori di libri trovati

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM presentazione WHERE isbn = '"+isbn+"';" //prepara la query che cerca tutti gli autori di libri
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
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
