package ImplementazionePostgresDAO;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtenteImplementazionePostgresDAO implements UtenteDAO {
    private Connection connection;

    public  UtenteImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public void addUtenteDB(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA) {
        try {
            PreparedStatement addUtentePS = connection.prepareStatement(
                    "INSERT INTO utente" +
                            "(username, email, passwordu, partitaiva, nome, cognome, dataNascita)" +
                            "VALUES('"+username+"','"+email+"','"+password+"','"+partitaIVA+"','"+nome+"','"+cognome+"','"+dataNascita+"');"
            );
            addUtentePS.executeUpdate();
            connection.close();
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }
}
