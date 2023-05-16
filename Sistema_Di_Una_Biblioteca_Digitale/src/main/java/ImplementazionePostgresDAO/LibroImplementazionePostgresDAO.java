package ImplementazionePostgresDAO;

import Database.ConnessioneDatabase;

import javax.swing.*;
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

    public void printLibri(JTextArea book){
        try {
            PreparedStatement query = this.connection.prepareStatement("SELECT * FROM LIBRO");
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                book.append(rs.getString("Titolo")+"\n");
            }
            rs.close();
            this.connection.close();
        } catch (SQLException var6){
            var6.printStackTrace();
        }

    }
}
