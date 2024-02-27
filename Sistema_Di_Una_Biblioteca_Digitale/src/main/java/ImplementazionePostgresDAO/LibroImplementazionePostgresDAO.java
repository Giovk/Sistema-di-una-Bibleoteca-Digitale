package ImplementazionePostgresDAO;

import DAO.LibroDAO;
import Database.ConnessioneDatabase;


import java.sql.*;

/**
 * La classe LibroImplementazionePostgresDAO implementa l'interfaccia LibroDAO, quindi contiene le implementazioni dei metodi che interagiscono
 * con il database per implementare le funzionalità relative ai libri.
 */
public class LibroImplementazionePostgresDAO implements LibroDAO {

    private Connection connection;

    /**
     * Istanzia un nuovo LibroImplementazionePostgresDAO.
     */
    public LibroImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getLibriDB(){  //cerca i dati di tutti i libri nel DB
        ResultSet rs = null;    //contiene tutti i libri trovati

        try {
            PreparedStatement getLibriPS = connection.prepareStatement(
                    "SELECT * FROM libro"   //prepara la query che cerca tutti i libri
            );

            rs = getLibriPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getLibriDB

    public ResultSet getLibriDB(String collana) {   //cerca i dati di tutti i libri della collana 'collana' nel DB
        ResultSet rs = null;    //contiene tutti i libri della collana 'collana' trovati

        try {
            PreparedStatement getLibriPS = connection.prepareStatement(
                    "SELECT * FROM libro NATURAL JOIN appartenenza NATURAL JOIN collana WHERE collana.nome = '"+collana+"';"   //prepara la query che cerca tutti i libri della collana 'collana'
            );

            rs = getLibriPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getLibriDB

    @Override
    public ResultSet getLibriSerieDB(String s){ //cerca i dati di tutti i libri della serie con ISBN 's' nel DB
        ResultSet rs = null;    //contiene tutti i libri della serie con ISBN 's' nel DB

        try {
            PreparedStatement getLibriSeriePS = connection.prepareStatement(
                    "SELECT * FROM inserimento WHERE serie = '"+s+"';"   //prepara la query che cerca tutti i libri della serie con ISBN 's'
            );

            rs = getLibriSeriePS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getLibriSerieDB

    @Override
    public ResultSet getInfoLibriPreferitiDB(String isbn){  //cerca i dati del libro con ISBN 'isbn' e delle librerie che lo possiedono
        ResultSet rs = null;    //contiene il libro con ISBN 'isbn' e le librerie che lo possiedono

        try {
            PreparedStatement getInfoLibriPreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Libro AS l NATURAL JOIN possesso_l AS pl NATURAL JOIN Libreria AS lib WHERE isbn = '"+isbn+"';"  //prepara la query che cerca il libro con ISBN 'isbn' e le librerie che lo possiedono
            );

            rs = getInfoLibriPreferitiPS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getInfoLibriPreferitiDB

    @Override
    public boolean creaLibroDB(String isbn, String titolo, String genere,String lingua, String editore,String dp){  //se non esiste già, inserisce un nuovo libro nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di libri con ISBN 'isbn'

        try {
            PreparedStatement creaLibroPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM libro WHERE isbn = '"+isbn+"'"   //prepara la query che conta i libri con ISBN 'isbn'
            );

            rs = creaLibroPS.executeQuery();    //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il numero di libri con ISBN 'isbn'
                    if(rs.getInt("contatore") == 0){    //controlla se non ci sono libri con ISBN 'isbn'
                        try{
                            creaLibroPS = connection.prepareStatement(
                                    "INSERT INTO libro(isbn, titolo, genere, lingua, editore, datapubblicazione) " +
                                            "VALUES ('"+isbn+"', '"+titolo+"', '"+genere+"', '"+lingua+"', '"+editore+"', '"+dp+"')"    //prepara la query che inserisce il nuovo libro
                            );

                            creaLibroPS.executeUpdate();    //esgue la query
                            chiudiConnessione();    //chiude la connessione al DB
                            return true;
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } else {
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }

                rs.close(); //chiude 'rs'
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return false;
    }//fine creaLibroDB

    @Override
    public Date getDataLibroDB(String isbn){    //cerca la data di pubblicazione del libro con ISBN 'isbn'
        Date data = null;   //data di pubblicazione del libro con ISBN 'isbn'
        ResultSet rs = null;    //contiene la data di pubblicazione del libro con ISBN 'isbn'

        try{
            PreparedStatement getDataLibroPS = connection.prepareStatement(
                    "SELECT datapubblicazione FROM libro WHERE isbn = '"+isbn+"'"   //prepara la query
            );

            rs = getDataLibroPS.executeQuery(); //esgue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con la data di pubblicazione del libro con ISBN 'isbn'
                    data = rs.getDate("datapubblicazione"); //aggiorna 'data'
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return data;
    }//fine getDataLibroDB

    @Override
    public void eliminaLibroDB(String isbn){    //elimina dal DB il libro con ISBN 'isbn'
        try{
            PreparedStatement eliminaLibroPS = connection.prepareStatement(
                    "DELETE FROM libro WHERE isbn = '"+isbn+"'" //prepara la query che elimina il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'
            );

            eliminaLibroPS.executeUpdate(); //esegue la query
        }catch (SQLException e){
            e.printStackTrace();
        }
    }//fine eliminaLibroDB

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