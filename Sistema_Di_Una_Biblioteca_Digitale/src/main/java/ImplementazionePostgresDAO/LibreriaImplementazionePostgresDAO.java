package ImplementazionePostgresDAO;

import DAO.LibreriaDAO;
import Database.ConnessioneDatabase;
import Model.Libreria;

import java.sql.*;
import java.util.ArrayList;

public class LibreriaImplementazionePostgresDAO implements LibreriaDAO {
    private Connection connection;

    public LibreriaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet disponibilitaDB(String isbn){  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
        ResultSet rs = null;    //disponibilità trovate

        try {
            PreparedStatement getDisponibilitaPS = connection.prepareStatement(
                    "SELECT * FROM libreria NATURAL JOIN possesso_l WHERE isbn = '"+isbn+"'" + //prepara la query che cerca le disponibilità del libro
                    " UNION " +
                            "SELECT * FROM libreria NATURAL JOIN possesso_s WHERE isbn = '"+isbn+"';"//prepara la query che cerca le disponibilità del libro




            );

            rs = getDisponibilitaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }


        return rs;
    }

    @Override
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo){  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
        ResultSet rs = null;    //disponibilità trovate

        try {
            PreparedStatement getDisponibilitaPS = connection.prepareStatement(
                    "SELECT * FROM libreria NATURAL JOIN possesso_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE fascicolo.numero = '"+numero+"' AND rivista.titolo = '"+titolo+"';"//prepara la query che cerca le disponibilità del libro
            );

            rs = getDisponibilitaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }


        return rs;
    }

    @Override
    public ResultSet getLibrerieUtenteDB(String user){
        ResultSet rs = null;

        try {
            PreparedStatement getLibrerieUtentePS = connection.prepareStatement(
                    "SELECT * FROM libreria WHERE gestore = '"+user+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = getLibrerieUtentePS.executeQuery(); //esegue la query

        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt){
        ResultSet rs = null;

        try {
            PreparedStatement presenzaNumeroTelefonicoLibreriaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS conteggio FROM libreria WHERE numerotelefonico = '"+nt+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = presenzaNumeroTelefonicoLibreriaPS.executeQuery(); //esegue la query
            try {
                while (rs.next()){
                    if(rs.getInt("conteggio") >= 1){
                        rs.close();
                        chiudiConnessione();
                        return false;
                    }
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();
        return true;
    }

    @Override
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo){
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) AS conteggio FROM libreria WHERE nome = '"+nome+"' "; //prepara la query che calcola il valore medio delle valutazioni del libro
            if (sw.isBlank()) query = query + "AND sitoweb IS NULL ";
            else query = query + "AND sitoweb = '"+sw+"' ";


            if (indirizzo.isBlank()) query = query + "AND indirizzo IS NULL ";
            else query = query + "AND indirizzo = '"+indirizzo+"' ";

            PreparedStatement presenzaLibreriaPS = connection.prepareStatement(query);

            rs = presenzaLibreriaPS.executeQuery(); //esegue la query
            try {
                while (rs.next()){
                    if(rs.getInt("conteggio") >= 1){
                        rs.close();
                        chiudiConnessione();
                        return false;
                    }
                }
            } catch (SQLException var){
                var.printStackTrace();
            }
            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();
        return true;
    }

    @Override
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user){
        try {
            String query = "INSERT INTO libreria(nome, numerotelefonico, sitoweb, indirizzo, gestore) VALUES (?, ?, ?, ?, ?)"; //prepara la query che calcola il valore medio delle valutazioni del libro
            PreparedStatement addLibreriaPS = connection.prepareStatement(query);

            addLibreriaPS.setString(1, nome);
            addLibreriaPS.setString(2, nt);

            if(sw.isBlank()) addLibreriaPS.setNull(3, Types.NULL);
            else addLibreriaPS.setString(3, sw);

            if(indirizzo.isBlank()) addLibreriaPS.setNull(4, Types.NULL);
            else addLibreriaPS.setString(4, indirizzo);

            addLibreriaPS.setString(5, user);

            addLibreriaPS.executeUpdate();    //esegue la query
            connection.close(); //chiude la connessione
        }  catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public void removeLibreriaDB(String nt){
        try {
            PreparedStatement removeLibreriaPS = connection.prepareStatement(
                    "DELETE FROM libreria WHERE numerotelefonico = '"+nt+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );

            removeLibreriaPS.executeUpdate();
            chiudiConnessione();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
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
