package ImplementazionePostgresDAO;

import DAO.SerieDAO;
import Database.ConnessioneDatabase;
import Model.Libro;
import Model.Serie;

import java.sql.*;
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
        ResultSet rs = null; //serie trovate

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
    public ResultSet getListaSerieGenereDB(String genere) { //ritorna i dati delle serie con libri del genere 'genere'
            ResultSet rs = null;    //serie trovate

            try {
                PreparedStatement getListaSerieGenerePS = connection.prepareStatement(
                        "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri FROM (serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l" +
                                " ON ins.libro = l.isbn WHERE l.genere = '"+genere+"';" //prepara la query di ricerca delle serie del genere 'genere'
                );

                rs = getListaSerieGenerePS.executeQuery(); //esegue la query
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

            return rs;
    }

    @Override
    public ResultSet getListaSerieAutoreDB(String autore) { //ritorna i dati delle serie con libri dell'autore 'autore'
        ResultSet rs = null;    //serie trovate

        try {
            PreparedStatement getListaSerieAutorePS = connection.prepareStatement(
                    "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri " +
                            "FROM (((serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l ON ins.libro = l.isbn) " +
                            "JOIN scrittura_l AS sl ON l.isbn = sl.isbn) JOIN autore as au ON sl.coda = au.coda " +
                            "WHERE '"+autore+"' LIKE '%' || au.nome || '%' AND '"+autore+"' LIKE '%' || au.cognome || '%';" //prepara la query di ricerca delle serie dell'autore 'autore'
            );

            rs = getListaSerieAutorePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getInfoSeriePreferitiDB(String isbn){
        ResultSet rs = null;

        try {
            PreparedStatement getInfoSeriePreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Serie AS s NATURAL JOIN possesso_s AS ps NATURAL JOIN Libreria AS lib WHERE isbn = '"+isbn+"';"   //prepara la query che cerca tutti i libri della serie
            );

            rs = getInfoSeriePreferitiPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean creaSerieDB(ArrayList<String> isbnList, String isbn, String titolo, String dp){
        ResultSet rs = null;
        int counter = 0;

        try {
            PreparedStatement creaSeriePS = connection.prepareStatement(
                    "SELECT COUNT(*) AS conteggio FROM serie WHERE isbn = '"+isbn+"'"
            );
            rs = creaSeriePS.executeQuery();
            try {
                while (rs.next()){
                    if (rs.getInt("conteggio") != 0){
                        rs.close();
                        chiudiConnessione();
                        return false;
                    } else {
                        try {
                            creaSeriePS = connection.prepareStatement(
                                    "SELECT DISTINCT serie.isbn FROM serie JOIN inserimento ON serie.isbn = inserimento.serie WHERE serie.nlibri = '"+isbnList.size()+"'"
                            );
                            rs = creaSeriePS.executeQuery();
                            try {
                                while (rs.next()){
                                    ResultSet rs2 = null;
                                    creaSeriePS = connection.prepareStatement(
                                            "SELECT libro FROM inserimento WHERE serie = '"+rs.getString(1)+"'"
                                    );
                                    rs2 = creaSeriePS.executeQuery();
                                    try {
                                        while (rs2.next()) {
                                            if (isbnList.contains(rs2.getString(1))) counter++;
                                            if (counter == isbnList.size()) { // Errore serie più piccole
                                                rs.close();
                                                rs2.close();
                                                chiudiConnessione();
                                                return false;
                                            }
                                        }
                                        counter = 0;
                                    } catch (SQLException e){
                                        e.printStackTrace();
                                    }
                                    rs2.close();
                                }

                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            System.out.println(isbn);
            System.out.println(titolo);
            System.out.println(dp);
            PreparedStatement creaSeriePS = connection.prepareStatement(
                    "INSERT INTO serie(isbn, titolo, datapubblicazione, nlibri) VALUES ('"+isbn+"', '"+titolo+"', '"+dp+"', "+isbnList.size()+")"
            );
            System.out.println("Mangatsu2");
            creaSeriePS.executeUpdate();
            System.out.println("Mangatsu3");
        } catch (SQLException e){
            e.printStackTrace();
        }

        for(String isbnSelected: isbnList){
            try {
                PreparedStatement creaSeriePS = connection.prepareStatement(
                        "INSERT INTO inserimento(serie, libro) VALUES ('"+isbn+"', '"+isbnSelected+"')"
                );
                creaSeriePS.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
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
