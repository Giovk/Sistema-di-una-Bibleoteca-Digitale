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
