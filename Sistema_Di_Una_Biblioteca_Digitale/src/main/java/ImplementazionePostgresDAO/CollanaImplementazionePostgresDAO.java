package ImplementazionePostgresDAO;

import DAO.CollanaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollanaImplementazionePostgresDAO implements CollanaDAO {
    private Connection connection;

    public CollanaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ArrayList<String>  getCollanaNomeDB(){    //ritorna i nomi di tutte le collane nel DB
        ArrayList<String> nomi = new ArrayList<>(); //contiene tutti i nomi delle collane
        ResultSet rs = null;    //nomi trovati

        try {
            PreparedStatement getCollanaPS = connection.prepareStatement(
                    "SELECT nome FROM collana" //prepara la query che cerca tutti nomi delle collane
            );

            rs = getCollanaPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente i nomi delle collane
                nomi.add(rs.getString("nome"));  //inserisce il nuovo nome in 'nomi'
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return nomi;
    }

    @Override
    public ResultSet getCollaneDB(){
        ResultSet rs = null;

        try {
            PreparedStatement getCollanePS = connection.prepareStatement(
                    "SELECT * FROM collana"
            );

            rs = getCollanePS.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public void removeLibroFromCollanaDB(String isbn, String collana){
        ResultSet rs = null;

        try {
            PreparedStatement removeLibroFromCollanaPS = connection.prepareStatement(
              "SELECT codc FROM collana WHERE nome = '"+collana+"'"
            );

            rs = removeLibroFromCollanaPS.executeQuery();
            try {
                while (rs.next()){
                    removeLibroFromCollanaPS = connection.prepareStatement(
                            "DELETE FROM appartenenza WHERE codc = '"+rs.getInt("codc")+"' AND isbn = '"+isbn+"'"
                    );

                    removeLibroFromCollanaPS.executeUpdate();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void addLibroInCollanaDB(String isbn, String collana){
        ResultSet rs = null;

        try {
            PreparedStatement addLibroInCollanaPS = connection.prepareStatement(
                    "SELECT codc FROM collana WHERE nome = '"+collana+"'"
            );

            rs = addLibroInCollanaPS.executeQuery();
            try {
                while (rs.next()){
                    addLibroInCollanaPS = connection.prepareStatement(
                            "INSERT INTO appartenenza(codc, isbn) VALUES ('"+rs.getInt("codc")+"', '"+isbn+"')"
                    );

                    addLibroInCollanaPS.executeUpdate();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
    }

    public boolean creaCollanaDB(String nome, String caratteristica, String issn){
        ResultSet rs = null;
        try {
            PreparedStatement creaCollanaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM collana WHERE nome = '"+nome+"'"
            );

            rs = creaCollanaPS.executeQuery();
            try {
                while (rs.next()){
                    if (rs.getInt("contatore") == 0){
                        if(!issn.isBlank()){
                            creaCollanaPS = connection.prepareStatement(
                                    "SELECT COUNT(*) AS contatore FROM collana WHERE issn = '"+issn+"'"
                            );

                            rs = creaCollanaPS.executeQuery();
                            try {
                                while (rs.next()){
                                    if (rs.getInt("contatore") == 0){
                                        creaCollanaPS = connection.prepareStatement(
                                                "INSERT INTO collana(nome, caratteristica, issn) VALUES('"+nome+"', '"+caratteristica+"', '"+issn+"')"
                                        );

                                        creaCollanaPS.executeUpdate();
                                    } else{
                                        rs.close();
                                        chiudiConnessione();
                                        return false;
                                    }
                                }
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        } else {
                            creaCollanaPS = connection.prepareStatement(
                                    "INSERT INTO collana(nome, caratteristica) VALUES('"+nome+"', '"+caratteristica+"')"
                            );

                            creaCollanaPS.executeUpdate();
                        }
                    } else {
                        rs.close();
                        chiudiConnessione();
                        return false;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

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