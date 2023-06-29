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

    /*@Override
    public ResultSet getLibroAutoriDB(String s){    //ritorna i dati del libro con ISBN 's'
        ResultSet rs = null;    //libro trovato

        /*String connectionURL = "jdbc:postgresql://localhost:5432/Biblioteca";
        try {
            connection = DriverManager.getConnection(connectionURL, "postgres", "giovk");
        }catch (SQLException e){
            e.printStackTrace();
        }*/

        /*try{
            PreparedStatement getLibroAutoriPS = connection.prepareStatement(
                    "SELECT * FROM  libro NATURAL JOIN scrittura_l NATURAL JOIN autore WHERE libro.isbn = '"+s+"';" //prepara la query che cerca il libro
            );
            rs = getLibroAutoriPS.executeQuery();   //esegue la query
        }catch (SQLException var2){
            var2.printStackTrace();
        }

        return rs;
    }*/

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
