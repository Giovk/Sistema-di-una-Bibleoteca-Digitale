package ImplementazionePostgresDAO;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement addUtentePS = connection.prepareStatement(query);
            addUtentePS.setString(1, username);
            addUtentePS.setString(2, email);
            addUtentePS.setString(3, password);
            if(partitaIVA.isBlank()){
                addUtentePS.setNull(4, Types.NULL);
            }else addUtentePS.setString(4, partitaIVA);
            addUtentePS.setString(5, nome);
            addUtentePS.setString(6, cognome);

            if(dataNascita == null){
                addUtentePS.setNull(7, Types.NULL);
            } else {
                java.sql.Date dNascita = Date.valueOf(dataNascita);
                addUtentePS.setDate(7, dNascita);
            }

            addUtentePS.executeUpdate();
            connection.close();
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public int validaUtenteDB(String userEmail, String password) {
        ResultSet rs;
        int n = 0;

        try{
            PreparedStatement validaUtentePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE passwordu = '"+password+"' AND (username = '"+userEmail+"'" +
                            "OR email = '"+userEmail+"');"
            );

            rs = validaUtentePS.executeQuery();
            while(rs.next()){
                n = rs.getInt("total");
            }
            connection.close();
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public ResultSet getUtenteDB(String userEmail, String password) {
        ResultSet rs = null;

        try{
            PreparedStatement validaUtentePS = connection.prepareStatement(
                    "SELECT * FROM utente WHERE passwordu = '"+password+"' AND (username = '"+userEmail+"'" +
                            "OR email = '"+userEmail+"');"
            );

            rs = validaUtentePS.executeQuery();
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return rs;
    }
    @Override
    public int validaModEmailDB(String emailM){
        ResultSet rs;
        int n = 0;

        try{
            PreparedStatement validaEmailPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE email = '"+emailM+"';"
            );

            rs = validaEmailPS.executeQuery();
            while(rs.next()){
                n = rs.getInt("total");
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public  int validaModUsernameDB(String usernameM){
        ResultSet rs;
        int n = 0;

        try{
            PreparedStatement validaUsernamePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE username = '"+usernameM+"';"
            );

            rs = validaUsernamePS.executeQuery();
            while(rs.next()){
                n = rs.getInt("total");
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }
    @Override
    public  int validaModPIVADB(String pIVAM){
        ResultSet rs;
        int n = 0;

        try{
            PreparedStatement validaPIVAPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE partitaiva = '"+pIVAM+"';"
            );

            rs = validaPIVAPS.executeQuery();
            while(rs.next()){
                n = rs.getInt("total");
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername){
        try {
            PreparedStatement modUtentePS = connection.prepareStatement(
                 "UPDATE utente SET email = '"+email+"', nome = '"+nome+"', cognome = '"+cognome+"', username = '"+username+"', passwordu = '"+password+"', partitaiva = '"+partitaIVA+"' WHERE username = '"+oldUsername+"';"
            );
            modUtentePS.executeUpdate();
            connection.close();
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public void chiudiConnessione(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
