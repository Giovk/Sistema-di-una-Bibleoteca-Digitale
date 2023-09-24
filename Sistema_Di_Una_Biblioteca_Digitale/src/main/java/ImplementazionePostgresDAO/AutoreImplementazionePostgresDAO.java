package ImplementazionePostgresDAO;

import DAO.AutoreDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

public class AutoreImplementazionePostgresDAO implements AutoreDAO{
    private Connection connection;

    public AutoreImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getAutoriLibroDB(String isbn){  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'
        ResultSet rs = null;    //autori del libro trovati

        try {
            PreparedStatement getAutoriLibroPS = connection.prepareStatement(
                    "SELECT * FROM libro NATURAL JOIN scrittura_l NATURAL JOIN autore WHERE libro.isbn = '"+isbn+"';"//prepara la query che cerca tutti gli autori del libro

            );

            rs = getAutoriLibroPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getAutoriArticoloDB(String doi){
        ResultSet rs = null;

        try {
            PreparedStatement getAutoriArticoloPS = connection.prepareStatement(
        "SELECT * FROM articolo_scientifico NATURAL JOIN scrittura_a NATURAL JOIN autore WHERE articolo_scientifico.doi = '"+doi+"';"
            );

            rs = getAutoriArticoloPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public void aggiungiAutoreDB(String nome, String cognome, String nazionalita, String dn, String isbn){
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) AS contatore FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //prepara la query che calcola il valore medio delle valutazioni del libro
            if (nazionalita.isBlank()) query = query + "AND nazionalita IS NULL ";
            else query = query + "AND nazionalita = '"+nazionalita+"' ";


            if (dn.isBlank()) query = query + "AND datanascita IS NULL ";
            else query = query + "AND datanascita = '"+dn+"' ";

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);

            rs = aggiungiAutorePS.executeQuery(); //esegue la query{
            try {
                while (rs.next()){
                    if (rs.getInt("contatore") == 0){
                        try{
                            query = "INSERT INTO autore(nome, cognome, nazionalita, datanascita) VALUES (?, ?, ?, ?)"; //prepara la query che calcola il valore medio delle valutazioni del libro
                            aggiungiAutorePS = connection.prepareStatement(query);

                            aggiungiAutorePS.setString(1, nome);
                            aggiungiAutorePS.setString(2, cognome);

                            if(nazionalita.isBlank()) aggiungiAutorePS.setNull(3, Types.NULL);
                            else aggiungiAutorePS.setString(3, nazionalita);

                            if(dn.isBlank()) aggiungiAutorePS.setNull(4, Types.NULL);
                            else aggiungiAutorePS.setDate(4, Date.valueOf(dn));

                            aggiungiAutorePS.executeUpdate();    //esegue la query

                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "SELECT coda FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //prepara la query che calcola il valore medio delle valutazioni del libro
            if (nazionalita.isBlank()) query = query + "AND nazionalita IS NULL ";
            else query = query + "AND nazionalita = '"+nazionalita+"' ";


            if (dn.isBlank()) query = query + "AND datanascita IS NULL ";
            else query = query + "AND datanascita = '"+dn+"' ";

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);

            rs = aggiungiAutorePS.executeQuery(); //esegue la query
            while (rs.next()){
                aggiungiAutorePS = connection.prepareStatement(
                        "INSERT INTO scrittura_l(isbn, coda) VALUES ('"+isbn+"', '"+rs.getInt("coda")+"')"
                );

                aggiungiAutorePS.executeUpdate();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
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
