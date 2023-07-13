package ImplementazionePostgresDAO;

import DAO.RecensioneDAO;
import Database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

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
    public boolean likeLibroDB(String isbn, String user) { //ritorna la media delle valutazioni del libro con isbn 'isbn'
        ResultSet rs = null;    //media trovata
        boolean like = false;   //valore medio delle valutazioni

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                like = rs.getBoolean(1);
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }

    @Override
    public boolean changeLikeDB(boolean like, String isbn, String user) { //ritorna la media delle valutazioni del libro con isbn 'isbn'
        ResultSet rs = null;    //media trovata
        int item = 1;

        if (like == true) like = false;
        else like = true;

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                item = rs.getInt(1);
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) {
            try {
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "UPDATE recensione_l SET preferito = '" + like + "' WHERE isbn = '" + isbn + "' AND username = '" + user + "'" //prepara la query che calcola il valore medio delle valutazioni del libro
                );
                changeLikePS.executeUpdate(); //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "INSERT INTO recensione_l(username, isbn, preferito) " +
                        "VALUES ('"+user+"', '"+isbn+"', '"+like+"')"//prepara la query che calcola il valore medio delle valutazioni del libro
                );
                changeLikePS.executeUpdate(); //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }


        return like;
    }

    @Override
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user){
        ResultSet rs = null;    //media trovata
        int item = 1;

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                item = rs.getInt(1);
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) {
            try {
                String query = "UPDATE recensione_l SET valutazione = ?, testo = ? WHERE isbn = ? AND username = ?"; //prepara la query che calcola il valore medio delle valutazioni del libro
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);

                addRecensioneLibroPS.setInt(1, valutazione);
                if(text.isBlank()) addRecensioneLibroPS.setNull(2, Types.NULL);
                else addRecensioneLibroPS.setString(2, text);

                addRecensioneLibroPS.setString(3, isbn);
                addRecensioneLibroPS.setString(4, user);

                addRecensioneLibroPS.executeUpdate();
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_l(username, isbn, valutazione, testo) VALUES (?, ?, ?, ?)"; //prepara la query che calcola il valore medio delle valutazioni del libro
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);

                addRecensioneLibroPS.setInt(3, valutazione);
                if(text.isBlank()) addRecensioneLibroPS.setNull(4, Types.NULL);
                else addRecensioneLibroPS.setString(4, text);

                addRecensioneLibroPS.setString(2, isbn);
                addRecensioneLibroPS.setString(1, user);

                addRecensioneLibroPS.executeUpdate();
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }

    public ResultSet allRecWithCommentDB(String isbn){
        ResultSet rs = null;

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_l WHERE isbn = '"+isbn+"' AND testo IS NOT NULL AND VALUTAZIONE IS NOT NULL"   //prepara la query che cerca tutti i libri della serie
            );

            rs = allRecWithCommentPS.executeQuery(); //esegue la query
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
