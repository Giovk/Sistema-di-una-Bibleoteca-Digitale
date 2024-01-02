package ImplementazionePostgresDAO;

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
    public ResultSet getArticoliScientificiDB(String issn, int n){  //cerca gli artioli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'
        ResultSet rs = null;    //articoli scientifici trovati

        try {
            PreparedStatement getArticoliScientificiPS = connection.prepareStatement(
                    "SELECT * FROM articolo_scientifico NATURAL JOIN introduzione NATURAL JOIN fascicolo WHERE fascicolo.issn = '"+issn+"' AND fascicolo.numero = '"+n+"';"   //prepara la query che cerca tutti gli articoli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'
            );

            rs = getArticoliScientificiPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getArticoliScientificiDB

    @Override
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn){    //se non esiste già, inserisce un nuovo articolo nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di articoli con titolo 'titolo', il DOI e il codice del fascicolo del nuovo articolo
        boolean enter = false;  //segnala se è stato inserito il nuovo articolo

        try {
            PreparedStatement creaArticoloPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM articolo_scientifico WHERE titolo = '"+titolo+"'"    //prepara la query che conta gli articoli con titolo 'titolo'
            );

            rs = creaArticoloPS.executeQuery(); //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di articoli con titolo 'titolo'
                    if(rs.getInt("contatore") == 0){    //controlla se non ci sono articoli con titolo 'titolo'
                        try{
                            creaArticoloPS = connection.prepareStatement(
                                    "INSERT INTO articolo_scientifico(titolo, annopubblicazione) " +
                                            "VALUES ('"+titolo+"', "+anno+")"   //prepara la query che inserisce il nuovo articolo
                            );

                            creaArticoloPS.executeUpdate(); //esegue la query
                            enter = true;   //aggiorna 'enter'
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }

                rs.close(); //chiude 'rs'
            } catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chide 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        try{
            String doi = "";    //DOI del nuovo articolo
            int codf = 0;   //codice del fascicolo del nuovo articolo
            PreparedStatement creaArticoloPS = connection.prepareStatement(
                    "SELECT doi FROM articolo_scientifico WHERE titolo = '"+titolo+"'"  //prepara la query che cerca il DOI del nuovo articolo
            );

            rs = creaArticoloPS.executeQuery(); //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il DOI del nuovo articolo
                    doi = rs.getString("doi");  //aggiorna 'doi'
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            creaArticoloPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE numero = "+numero+" AND issn = '"+issn+"'"    //prepara la query che cerca il codice del fascicolo del nuovo articolo
            );

            rs = creaArticoloPS.executeQuery(); //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il codice del fascicolo del nuovo articolo
                    codf = rs.getInt("codf");   //aggiorna 'codf'
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            creaArticoloPS = connection.prepareStatement(
                    "INSERT INTO introduzione(codf, doi) VALUES('"+codf+"', '"+doi+"')" //prepara la query che introduce il nuovo articolo nel suo fascicolo
            );

            creaArticoloPS.executeUpdate(); //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        return enter;
    }//fine creaArticoloDB

    @Override
    public String getDoiDB(String titolo){  //ritorna il DOI dell'articolo scientifico 'titolo'
       ResultSet rs = null; //contiene il DOI dell'articolo scientifico 'titolo'
       String doi = ""; //DOI dell'articolo scientifico 'titolo'

       try {
           PreparedStatement getDoiPS = connection.prepareStatement(
                   "SELECT doi FROM articolo_scientifico WHERE titolo = '"+titolo+"'"   //prepara la query che cerca il DOI dell'articolo scientifico 'titolo'
           );

           rs = getDoiPS.executeQuery();    //esegue la query

           try {
               while (rs.next()){   //scorre il ResultSet 'rs' con il DOI dell'articolo scientifico 'titolo'
                   doi = rs.getString("doi");   //aggiorna doi

               }
           } catch (SQLException e){
               e.printStackTrace();
           }

           rs.close();  //chide 'rs'
       }catch (SQLException e){
           e.printStackTrace();
       }

       return doi;
    }//fine getDoiDB

    @Override
    public int getAPDB(String doi){ //ritorna l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'
        ResultSet rs = null;    //contiene l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'
        int anno = 0;   //anno di pubblicazione dell'articolo scientifico con DOI 'doi'

        try {
            PreparedStatement getAPPS = connection.prepareStatement(
              "SELECT annopubblicazione FROM articolo_scientifico WHERE doi = '"+doi+"'"    //prepara la query che cerca l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'
            );

            rs = getAPPS.executeQuery();    //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'
                    anno = rs.getInt("annopubblicazione");  //aggiorna 'anno'
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return anno;
    }//fine getAPDB

    @Override
    public void eliminaArticoloDB(String doi){  //elimina l'articolo scientifico con DOI 'doi'
        try{
            PreparedStatement eliminaArticoloPS = connection.prepareStatement(
                    "DELETE FROM articolo_scientifico WHERE doi = '"+doi+"'"    //prepara la query che elimina l'articolo scientifico con DOI 'doi'
            );

            eliminaArticoloPS.executeUpdate();  //esegue la query
        }catch (SQLException e){
            e.printStackTrace();
        }
    }//fine eliminaArticoloDB

    @Override
    public void chiudiConnessione(){    //chiude la connessione al DB
        try{
            if (connection != null && !connection.isClosed()){  //controlla se la connessione è chiusa
                connection.close(); //chiude la connessione
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//fine chiudiConnessione
}
