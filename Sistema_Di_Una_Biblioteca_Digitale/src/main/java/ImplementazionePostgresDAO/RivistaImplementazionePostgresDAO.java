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
