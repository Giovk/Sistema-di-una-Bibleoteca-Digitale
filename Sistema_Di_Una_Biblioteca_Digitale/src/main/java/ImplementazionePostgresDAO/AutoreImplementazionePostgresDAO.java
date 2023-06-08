package ImplementazionePostgresDAO;

import DAO.AutoreDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public ResultSet getAutoriLibroDB(){    //ritorna i dati di tutti gli autori di libri nel DB
        ResultSet rs = null;    //autori di libri trovati

        try {
            PreparedStatement getAutoriLibroPS = connection.prepareStatement(
                    "SELECT * FROM autore NATURAL JOIN scrittura_l" //prepara la query che cerca tutti gli autori di libri
            );

            rs = getAutoriLibroPS.executeQuery(); //esegue la query
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
