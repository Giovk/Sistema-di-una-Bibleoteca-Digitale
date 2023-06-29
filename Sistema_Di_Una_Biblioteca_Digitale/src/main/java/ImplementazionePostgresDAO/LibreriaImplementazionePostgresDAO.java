package ImplementazionePostgresDAO;

import DAO.LibreriaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibreriaImplementazionePostgresDAO implements LibreriaDAO {
    private Connection connection;

    public LibreriaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet disponibilitaDB(String isbn){  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
        ResultSet rs = null;    //disponibilità trovate

        try {
            PreparedStatement getDisponibilitaPS = connection.prepareStatement(
                    "SELECT * FROM libreria NATURAL JOIN possesso_l WHERE isbn = '"+isbn+"';" //prepara la query che cerca le disponibilità del libro
            );

            rs = getDisponibilitaPS.executeQuery(); //esegue la query
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
