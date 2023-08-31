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
