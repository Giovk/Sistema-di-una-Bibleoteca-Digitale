package ImplementazionePostgresDAO;

import DAO.RecensioneDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecensioneImplementazionePostgresDAO implements RecensioneDAO {
    private Connection connection;

    public  RecensioneImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public float valutazioneMediaLibroDB(String isbn) { //ritorna la media delle valutazioni del libro con isbn 'isbn'
        ResultSet rs = null;    //media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaLibroPS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_l WHERE isbn = '"+isbn+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = valutazioneMediaLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
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
