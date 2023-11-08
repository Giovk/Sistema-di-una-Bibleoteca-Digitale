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
    public ResultSet getPresentazioneDB(String isbn){   //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null;    //presentazionni trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM presentazione WHERE isbn = '"+isbn+"';" //prepara la query che cerca tutte le presentazioni del libro
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean addPresentazioneDB(String struttura, String luogo, String data, String orario, String isbn){
        ResultSet rs = null;
        try {
            PreparedStatement addPresentazionePS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM presentazione WHERE struttura = '"+struttura+"' AND luogo = '"+luogo+"' AND datap = '"+data+"' AND" +
                            " ora = '"+orario+"'"
            );

            rs = addPresentazionePS.executeQuery();
            try {
                while(rs.next()){
                    if (rs.getInt("contatore") >= 1){
                        rs.close();
                        chiudiConnessione();
                        return false;
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addPresentazionePS = connection.prepareStatement(
                    "INSERT INTO presentazione(isbn, struttura, luogo, datap, ora) " +
                            "VALUES('"+isbn+"', '"+struttura+"', '"+luogo+"', '"+data+"', '"+orario+"')"
            );

            addPresentazionePS.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return true;
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
