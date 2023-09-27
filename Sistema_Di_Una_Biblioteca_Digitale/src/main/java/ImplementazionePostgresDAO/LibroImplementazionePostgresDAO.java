package ImplementazionePostgresDAO;

import DAO.LibroDAO;
import Database.ConnessioneDatabase;

import javax.swing.*;
import java.net.URL;
import java.sql.*;

public class LibroImplementazionePostgresDAO implements LibroDAO {

    private Connection connection;

    public LibroImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getLibriDB() { //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getLibriPS = connection.prepareStatement(
                    "SELECT * FROM libro"   //prepara la query che cerca tutti i libri
            );

            rs = getLibriPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    public ResultSet getLibriDB(String collana) { //ritorna i dati di tutti i libri nel DB
        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getLibriPS = connection.prepareStatement(
                    "SELECT * FROM libro NATURAL JOIN appartenenza NATURAL JOIN collana WHERE collana.nome = '"+collana+"';"   //prepara la query che cerca tutti i libri della collana 'collana'
            );

            rs = getLibriPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getLibriSerieDB(String s){ //ritorna i dati di tutti i libri della serie con ISBN 's' nel DB
        ResultSet rs = null;

        try {
            PreparedStatement getLibriSeriePS = connection.prepareStatement(
                    "SELECT * FROM inserimento WHERE serie = '"+s+"';"   //prepara la query che cerca tutti i libri della serie
            );

            rs = getLibriSeriePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getInfoLibriPreferitiDB(String isbn){
        ResultSet rs = null;

        try {
            PreparedStatement getInfoLibriPreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Libro AS l NATURAL JOIN possesso_l AS pl NATURAL JOIN Libreria AS lib WHERE isbn = '"+isbn+"';"   //prepara la query che cerca tutti i libri della serie
            );

            rs = getInfoLibriPreferitiPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean creaLibroDB(String isbn, String titolo, String genere,String lingua, String editore,String dp){
        ResultSet rs = null;
        try {
            PreparedStatement creaLibroPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM libro WHERE isbn = '"+isbn+"'"
            );
            rs = creaLibroPS.executeQuery();

            try {
                while(rs.next()){
                    if(rs.getInt("contatore") == 0){
                        try{
                            creaLibroPS = connection.prepareStatement(
                                    "INSERT INTO libro(isbn, titolo, genere, lingua, editore, datapubblicazione) " +
                                            "VALUES ('"+isbn+"', '"+titolo+"', '"+genere+"', '"+lingua+"', '"+editore+"', '"+dp+"')"
                            );

                            creaLibroPS.executeUpdate();
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
