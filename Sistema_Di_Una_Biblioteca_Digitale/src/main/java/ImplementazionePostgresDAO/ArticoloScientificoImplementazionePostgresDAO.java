package ImplementazionePostgresDAO;

import Controller.Controller;
import DAO.ArticoloScientificoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloScientificoImplementazionePostgresDAO implements ArticoloScientificoDAO {
    private Connection connection;

    public ArticoloScientificoImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getArticoliScientificiDB(String issn, int n){
        ResultSet rs = null;

        try {
            PreparedStatement getArticoliScientificiPS = connection.prepareStatement(
                    "SELECT * FROM articolo_scientifico NATURAL JOIN introduzione NATURAL JOIN fascicolo WHERE fascicolo.issn = '"+issn+"' AND fascicolo.numero = '"+n+"';"   //prepara la query che cerca tutte le serie
            );

            rs = getArticoliScientificiPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn){
        ResultSet rs = null;
        boolean enter = false;
        try {
            PreparedStatement creaArticoloPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM articolo_scientifico WHERE titolo = '"+titolo+"'"
            );
            rs = creaArticoloPS.executeQuery();

            try {
                while(rs.next()){
                    if(rs.getInt("contatore") == 0){
                        try{
                            creaArticoloPS = connection.prepareStatement(
                                    "INSERT INTO articolo_scientifico(titolo, annopubblicazione) " +
                                            "VALUES ('"+titolo+"', "+anno+")"
                            );

                            creaArticoloPS.executeUpdate();
                            enter = true;
                        } catch (SQLException e){
                            e.printStackTrace();
                        }

                    }

                }
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try{
            String doi = "";
            int codf = 0;
            PreparedStatement creaArticoloPS = connection.prepareStatement(
                    "SELECT doi FROM articolo_scientifico WHERE titolo = '"+titolo+"'"
            );

            rs = creaArticoloPS.executeQuery();
            try {
                while (rs.next()){
                    doi = rs.getString("doi");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            creaArticoloPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE numero = "+numero+" AND issn = '"+issn+"'"
            );

            rs = creaArticoloPS.executeQuery();

            try {
                while (rs.next()){
                    codf = rs.getInt("codf");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            System.out.println(codf + " - " + doi);

            creaArticoloPS = connection.prepareStatement(
                    "INSERT INTO introduzione(codf, doi) VALUES('"+codf+"', '"+doi+"')"
            );

            creaArticoloPS.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return enter;
    }

    @Override
    public String getDoiDB(String titolo){
       ResultSet rs = null;
       String doi = "";
       try {
           PreparedStatement getDoiPS = connection.prepareStatement(
                   "SELECT doi FROM articolo_scientifico WHERE titolo = '"+titolo+"'"
           );

           rs = getDoiPS.executeQuery();
           try {
               while (rs.next()){
                   doi = rs.getString("doi");

               }
           } catch (SQLException e){
               e.printStackTrace();
           }
           rs.close();
       }catch (SQLException e){
           e.printStackTrace();
       }

       return doi;
    }

    @Override
    public int getAPDB(String doi){
        ResultSet rs = null;
        int anno = 0;
        try {
            PreparedStatement getAPPS = connection.prepareStatement(
              "SELECT annopubblicazione FROM articolo_scientifico WHERE doi = '"+doi+"'"
            );

            rs = getAPPS.executeQuery();

            try {
                while (rs.next()){
                    anno = rs.getInt("annopubblicazione");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return anno;
    }

    @Override
    public void eliminaArticoloDB(String doi){
        try{
            System.out.println(doi);
            PreparedStatement eliminaArticoloPS = connection.prepareStatement(
                    "DELETE FROM articolo_scientifico WHERE doi = '"+doi+"'"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
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
