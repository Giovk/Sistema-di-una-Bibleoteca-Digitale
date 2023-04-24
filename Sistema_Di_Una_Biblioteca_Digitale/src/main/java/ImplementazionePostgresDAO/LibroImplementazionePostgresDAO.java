package ImplementazionePostgresDAO;

import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroImplementazionePostgresDAO {

    private Connection connection;

    public LibroImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    public void printLibri(){
        try {
            PreparedStatement query = this.connection.prepareStatement("SELECT * FROM LIBRO");
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("Titolo"));
            }
            rs.close();
            this.connection.close();
        } catch (SQLException var6){
            var6.printStackTrace();
        }

    }
}
