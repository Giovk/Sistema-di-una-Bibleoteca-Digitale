package ImplementazionePostgresDAO;

import DAO.SerieDAO;
import Database.ConnessioneDatabase;
import Model.Libro;
import Model.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieImplementazionePostgresDAO implements SerieDAO {

    private Connection connection;

    public  SerieImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getSerieDB() { //ritorna i dati di tutte le serie nel DB
        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getSeriePS = connection.prepareStatement(
                    "SELECT * FROM serie"   //prepara la query che cerca tutte le serie
            );

            rs = getSeriePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ArrayList<String> getSerieGenereDB(){
        ArrayList<String> genere = new ArrayList<>();
        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT l.genere FROM inserimento AS ins JOIN libro AS l" +
                    " ON ins.libro = l.isbn;"//prepara la query che cerca tutte le serie//
                    );

            rs = getSerieGenerePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                genere.add(rs.getString("genere"));//inserisce i libri della serie corrente di 'rs' nell'ArrayList 'libri'
            }

            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return genere;
    }
    public ArrayList<String> getSerieAutoriDB(){
        ArrayList<String> autori = new ArrayList<>();

        ResultSet rs = null; //libri trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT au.nome, au.cognome"+
                    " FROM AUTORE AS AU NATURAL JOIN SCRITTURA_L NATURAL JOIN LIBRO AS L JOIN INSERIMENTO AS INS ON L.ISBN=INS.LIBRO"
            );

            rs = getSerieGenerePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente le serie
                autori.add(rs.getString("nome") + " " + rs.getString("cognome"));//inserisce i libri della serie corrente di 'rs' nell'ArrayList 'libri'
            }

            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return autori;
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
