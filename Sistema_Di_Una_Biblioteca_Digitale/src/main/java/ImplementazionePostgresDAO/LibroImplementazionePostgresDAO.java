package ImplementazionePostgresDAO;

import DAO.LibroDAO;
import Database.ConnessioneDatabase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    public ResultSet getLibroAutoriDB(String s){
        ResultSet rs = null;
        try{
            PreparedStatement getLibroAutoriPS = connection.prepareStatement(
                    "SELECT * FROM  libro NATURAL JOIN scrittura_l NATURAL JOIN autore WHERE libro.isbn = '"+s+"';"
            );
            rs = getLibroAutoriPS.executeQuery();
        }catch (SQLException var2){
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
