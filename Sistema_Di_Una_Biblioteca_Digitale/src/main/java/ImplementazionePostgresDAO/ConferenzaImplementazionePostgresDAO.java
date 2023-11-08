package ImplementazionePostgresDAO;

import DAO.ConferenzaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenzaImplementazionePostgresDAO implements ConferenzaDAO {
    private Connection connection;

    public ConferenzaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getConferenzeArticoloDB(String doi){   //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null;    //presentazionni trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM conferenza NATURAL JOIN esposizione WHERE doi = '"+doi+"';" //prepara la query che cerca tutte le presentazioni del libro
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getConferenzeDB(){   //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null;    //presentazionni trovate

        try {
            PreparedStatement getPresentazionePS = connection.prepareStatement(
                    "SELECT * FROM conferenza;" //prepara la query che cerca tutte le presentazioni del libro
            );

            rs = getPresentazionePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf){
        ResultSet rs = null;
        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM conferenza WHERE strutturaorganizzatrice = '"+struttura+"' AND luogo = '"+luogo+"' AND datainizio = '"+datai+"' AND" +
                            " datafine = '"+dataf+"'"
            );

            rs = addConferenzaPS.executeQuery();
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
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "INSERT INTO conferenza(strutturaorganizzatrice, luogo, datainizio, datafine) " +
                            "VALUES('"+struttura+"', '"+luogo+"', '"+datai+"', '"+dataf+"')"
            );

            addConferenzaPS.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return true;
    }

    @Override
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi){
        ResultSet rs = null;
        int codc = 0;
        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT codc FROM conferenza WHERE strutturaorganizzatrice = '"+struttura+"' AND luogo = '"+luogo+"' AND datainizio = '"+datai+"' AND" +
                            " datafine = '"+dataf+"'"
            );

            rs = addConferenzaPS.executeQuery();

            try{
                while (rs.next()){
                    codc = rs.getInt("codc");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM esposizione WHERE doi = '"+doi+"' AND codc = '"+codc+"'"
            );

            rs = addConferenzaPS.executeQuery();
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
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement addConferenzaPS = connection.prepareStatement(
                    "INSERT INTO esposizione(doi, codc) " +
                            "VALUES('"+doi+"', '"+codc+"')"
            );

            addConferenzaPS.executeUpdate();
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
