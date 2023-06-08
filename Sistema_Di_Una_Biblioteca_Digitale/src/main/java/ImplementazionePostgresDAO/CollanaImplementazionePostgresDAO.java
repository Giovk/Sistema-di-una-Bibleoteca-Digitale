package ImplementazionePostgresDAO;

import DAO.CollanaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollanaImplementazionePostgresDAO implements CollanaDAO {
    private Connection connection;

    public CollanaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getCollanaDB(){    //ritorna i dati di tutte le collane nel DB
        ResultSet rs = null;    //collane trovate

        try {
            PreparedStatement getCollanaPS = connection.prepareStatement(
                    "SELECT * FROM collana" //prepara la query che cerca tutte le collane
            );

            rs = getCollanaPS.executeQuery(); //esegue la query
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
