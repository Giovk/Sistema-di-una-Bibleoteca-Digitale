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
    public ArrayList<String> getSerieGenereDB(){    //ritorna tutti i generi dei libri che sono inseriti in una serie
        ArrayList<String> genere = new ArrayList<>();   //contiene i generi
        ResultSet rs = null; //generi trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT l.genere FROM inserimento AS ins JOIN libro AS l" +
                    " ON ins.libro = l.isbn;"   //prepara la query che cerca tutti generi delle serie
            );

            rs = getSerieGenerePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente i generi
                genere.add(rs.getString("genere")); //inserisce un nuovo genere nell'ArrayList 'generi'
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return genere;
    }

    @Override
    public ArrayList<String> getSerieAutoriDB(){    //ritorna tutti gli autori dei libri che sono inseriti in una serie
        ArrayList<String> autori = new ArrayList<>();   //contiene i generi
        ResultSet rs = null;    //autori trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT au.nome, au.cognome"+
                    " FROM AUTORE AS AU NATURAL JOIN SCRITTURA_L NATURAL JOIN LIBRO AS L JOIN INSERIMENTO AS INS ON L.ISBN=INS.LIBRO"   //prepara la query che cerca tutti gli autori delle serie
            );

            rs = getSerieGenerePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente gli autori
                autori.add(rs.getString("nome") + " " + rs.getString("cognome"));   //inserisce un nuovo autore nell'ArrayList 'autori'
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return autori;
    }

    @Override
    public ResultSet getListaSerieGenereDB(String genere) {
            ResultSet rs = null;    //autori trovati

            try {
                PreparedStatement getListaSerieGenerePS = connection.prepareStatement(
                        "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri FROM (serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l" +
                                " ON ins.libro = l.isbn WHERE l.genere = '"+genere+"';"
                );
                rs = getListaSerieGenerePS.executeQuery(); //esegue la query
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

            return rs;
        }

    @Override
    public ResultSet getListaSerieAutoreDB(String autore) {
        ResultSet rs = null;    //autori trovati

        try {
            PreparedStatement getListaSerieAutorePS = connection.prepareStatement(
                    "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri " +
                            "FROM (((serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l ON ins.libro = l.isbn) " +
                            "JOIN scrittura_l AS sl ON l.isbn = sl.isbn) JOIN autore as au ON sl.coda = au.coda " +
                            "WHERE '"+autore+"' LIKE '%' || au.nome || '%' AND '"+autore+"' LIKE '%' || au.cognome || '%';"
            );
            rs = getListaSerieAutorePS.executeQuery(); //esegue la query
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
