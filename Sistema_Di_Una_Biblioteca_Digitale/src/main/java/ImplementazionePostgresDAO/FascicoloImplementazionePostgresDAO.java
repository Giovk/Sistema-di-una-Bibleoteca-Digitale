package ImplementazionePostgresDAO;

import DAO.FascicoloDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

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
    public boolean creaFascicoloDB(int numero, String data, String issn){
        ResultSet rs = null;
        try {
            PreparedStatement creaFascicoloPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"
            );
            rs = creaFascicoloPS.executeQuery();

            try {
                while(rs.next()){
                    if(rs.getInt("contatore") == 0){
                        try{
                            creaFascicoloPS = connection.prepareStatement(
                                    "INSERT INTO fascicolo(numero, datapubblicazione, issn) " +
                                            "VALUES ('"+numero+"', '"+data+"', '"+issn+"')"
                            );

                            creaFascicoloPS.executeUpdate();
                            chiudiConnessione();
                            return true;
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } else {
                        chiudiConnessione();
                        return false;
                    }
                }
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
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
