package ImplementazionePostgresDAO;

import DAO.SerieDAO;
import Database.ConnessioneDatabase;

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
    public ResultSet getSerieDB() { //cerca i dati di tutte le serie nel DB
        ResultSet rs = null;    //contiene tutte le serie trovate

        try {
            PreparedStatement getSeriePS = connection.prepareStatement(
                    "SELECT * FROM serie"   //prepara la query che cerca tutte le serie
            );

            rs = getSeriePS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getSerieDB

    @Override
    public ArrayList<String> getSerieGenereDB(){    //ritorna tutti i generi dei libri che sono inseriti in qualche serie
        ArrayList<String> genere = new ArrayList<>();   //generi dei libri che sono inseriti in qualche serie
        ResultSet rs = null; //contiene tutti i generi trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT l.genere FROM inserimento AS ins JOIN libro AS l" +
                    " ON ins.libro = l.isbn;"   //prepara la query che cerca tutti i generi dei libri che sono inseriti in qualche serie
            );

            rs = getSerieGenerePS.executeQuery();   //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente i generi dei libri che sono inseriti in qualche serie
                genere.add(rs.getString("genere")); //inserisce un nuovo genere in 'generi'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return genere;
    }//fine getSerieGenereDB

    @Override
    public ArrayList<String> getSerieAutoriDB(){    //ritorna tutti gli autori dei libri che sono inseriti in qualche serie
        ArrayList<String> autori = new ArrayList<>();   //autori dei libri che sono inseriti in qualche serie
        ResultSet rs = null;    //contiene tutti gli autori trovati

        try {
            PreparedStatement getSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT au.nome, au.cognome"+
                    " FROM AUTORE AS AU NATURAL JOIN SCRITTURA_L NATURAL JOIN LIBRO AS L JOIN INSERIMENTO AS INS ON L.ISBN=INS.LIBRO"   //prepara la query che cerca tutti gli autori dei libri che sono inseriti in qualche serie
            );

            rs = getSerieGenerePS.executeQuery();   //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con gli autori dei libri che sono inseriti in qualche serie
                autori.add(rs.getString("nome") + " " + rs.getString("cognome"));   //inserisce un nuovo autore in 'autori'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return autori;
    }//fine getSerieAutoriDB

    @Override
    public ResultSet getListaSerieGenereDB(String genere) { //cerca i dati delle serie con libri del genere 'genere'
        ResultSet rs = null;    //contiene le serie con libri del genere 'genere' trovate

        try {
            PreparedStatement getListaSerieGenerePS = connection.prepareStatement(
                    "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri FROM (serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l" +
                            " ON ins.libro = l.isbn WHERE l.genere = '"+genere+"';" //prepara la query che cerca le serie con libri del genere 'genere'
            );

                rs = getListaSerieGenerePS.executeQuery();  //esegue la query
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

        return rs;
    }//fine getListaSerieGenereDB

    @Override
    public ResultSet getListaSerieAutoreDB(String autore) { //cerca i dati delle serie con libri dell'autore 'autore'
        ResultSet rs = null;    //contiene le serie con libri dell'autore 'autore' trovate

        try {
            PreparedStatement getListaSerieAutorePS = connection.prepareStatement(
                    "SELECT DISTINCT s.isbn, s.titolo, s.datapubblicazione, s.nlibri " +
                            "FROM (((serie AS s JOIN inserimento AS ins ON s.isbn = ins.serie) JOIN libro AS l ON ins.libro = l.isbn) " +
                            "JOIN scrittura_l AS sl ON l.isbn = sl.isbn) JOIN autore as au ON sl.coda = au.coda " +
                            "WHERE '"+autore+"' LIKE '%' || au.nome || '%' AND '"+autore+"' LIKE '%' || au.cognome || '%';" //prepara la query che cerca le serie con libri dell'autore 'autore'
            );

            rs = getListaSerieAutorePS.executeQuery();  //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getListaSerieAutoreDB

    @Override
    public ResultSet getInfoSeriePreferitiDB(String isbn){  //cerca i dati della serie con ISBN 'isbn' e delle librerie che lo possiedono
        ResultSet rs = null;    //contiene la serie con ISBN 'isbn' e le librerie che lo possiedono

        try {
            PreparedStatement getInfoSeriePreferitiPS = connection.prepareStatement(
                    "SELECT * FROM Serie AS s NATURAL JOIN possesso_s AS ps NATURAL JOIN Libreria AS lib WHERE isbn = '"+isbn+"';"  //prepara la query che cerca la serie con ISBN 'isbn' e le librerie che lo possiedono
            );

            rs = getInfoSeriePreferitiPS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getInfoSeriePreferitiDB

    @Override
    public boolean creaSerieDB(ArrayList<String> isbnList, String isbn, String titolo, String dp){  //se non esiste già, inserisce una nuova serie nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di serie con ISBN 'isbn', gli ISBN delle serie con lo stesso numero di libri con ISBN 'isbn'
        int counter = 0;    //contatore

        try {
            PreparedStatement creaSeriePS = connection.prepareStatement(
                    "SELECT COUNT(*) AS conteggio FROM serie WHERE isbn = '"+isbn+"'"   //prepara la query che conta le serie con ISBN 'isbn'
            );

            rs = creaSeriePS.executeQuery();    //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di serie con ISBN 'isbn'
                    if (rs.getInt("conteggio") != 0){   //controlla se ci sono serie con ISBN 'isbn'
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    } else {
                        try {
                            creaSeriePS = connection.prepareStatement(
                                    "SELECT DISTINCT serie.isbn FROM serie JOIN inserimento ON serie.isbn = inserimento.serie WHERE serie.nlibri = '"+isbnList.size()+"'"   //prepara la query che cerca gli ISBN delle serie con lo stesso numero di libri dei libri in isbnList da inserire nella nuova serie
                            );

                            rs = creaSeriePS.executeQuery();    //esegue la query

                            try {
                                while (rs.next()){  //scorre il ResultSet 'rs' con gli ISBN delle serie con lo stesso numero di libri dei libri in isbnList da inserire nella nuova serie
                                    ResultSet rs2 = null;   //contiene gli ISBN dei libri di ogni serie con ISBN nel ResultSet 'rs'

                                    creaSeriePS = connection.prepareStatement(
                                            "SELECT libro FROM inserimento WHERE serie = '"+rs.getString(1)+"'" //prepara la query che cerca gli ISBN dei libri nella query corente del ResultSet 'rs'
                                    );
                                    rs2 = creaSeriePS.executeQuery();   //esegue la query

                                    try {
                                        while (rs2.next()) {    //scorre il ResultSet 'rs2' con gli ISBN dei libri nella serie con l'ISBN corrente del ResultSet 'rs'
                                            if (isbnList.contains(rs2.getString(1))){   //controlla se 'isbnList' contiene l'ISBN corrente del ResultSet 'rs'
                                                counter++;  //incrementa il contatore
                                            }

                                            if (counter == isbnList.size()) {   //controlla se gli ISBN dei libri della serie con l'ISBN ccorrente del ResultSet 'rs' contiene gli stessi ISBN di 'isbnList'
                                                rs.close(); //chiude 'rs'
                                                rs2.close();    //chiude 'rs2'
                                                chiudiConnessione();    //chiude la connessione al DB
                                                return false;
                                            }
                                        }

                                        counter = 0;    //azzera il contatore
                                    } catch (SQLException e){
                                        e.printStackTrace();
                                    }

                                    rs2.close();    //chiude 'rs2'
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

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement creaSeriePS = connection.prepareStatement(
                    "INSERT INTO serie(isbn, titolo, datapubblicazione, nlibri) VALUES ('"+isbn+"', '"+titolo+"', '"+dp+"', "+isbnList.size()+")"   //prepara la query che inserisce la nuova serie
            );

            creaSeriePS.executeUpdate();    //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        for(String isbnSelected: isbnList){ //scorre 'isbnList'
            try {
                PreparedStatement creaSeriePS = connection.prepareStatement(
                        "INSERT INTO inserimento(serie, libro) VALUES ('"+isbn+"', '"+isbnSelected+"')" //inserisce il libro con ISBN 'isbnSelected' nella serie con ISBN 'isbn'
                );

                creaSeriePS.executeUpdate();    //esegue la query
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine creaSerieDB

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