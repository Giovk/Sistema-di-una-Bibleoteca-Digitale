package ImplementazionePostgresDAO;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

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
    public void addUtenteDB(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA) {   //inserisce un nuovo utente nel DB
        try {
            String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?, ?, ?)";    //prepara la query di inserimento
            PreparedStatement addUtentePS = connection.prepareStatement(query);

            addUtentePS.setString(1, username); //inserisce l'userame nella query
            addUtentePS.setString(2, email);    //inserisce l'email nella query
            addUtentePS.setString(3, password); //inserisce la password nella query

            if(partitaIVA.isBlank()){   //controlla se la partita IVA è vuota
                addUtentePS.setNull(4, Types.NULL); //inserisce NULL nella query
            }else addUtentePS.setString(4, partitaIVA); //inserisce la partita IVA nella query

            addUtentePS.setString(5, nome); //inserisce il nome nella query
            addUtentePS.setString(6, cognome);  //inserisce il cognome nella query

            if(dataNascita == null){    //controlla se la partita IVA è nulla
                addUtentePS.setNull(7, Types.NULL); //inserisce NULL nella query
            } else {
                java.sql.Date dNascita = Date.valueOf(dataNascita);
                addUtentePS.setDate(7, dNascita);   //inserisce la data di nascita nella query
            }

            addUtentePS.executeUpdate();    //esegue la query
            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public int validaUtenteDB(String userEmail, String password) { //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
        ResultSet rs;
        int n = 0;  //numero di utenti nel DB con 'userEmail' e 'password'

        try{
            PreparedStatement validaUtentePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE passwordu = '"+password+"' AND (username = '"+userEmail+"'" +
                            "OR email = '"+userEmail+"');"  //prepara la query che conta il numero di utenti con 'userEmail' e 'password'
            );

            rs = validaUtentePS.executeQuery(); //esegue la query

            while(rs.next()){
                n = rs.getInt("total"); //inserisce in 'n' il risltato della query
            }

            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public ResultSet getUtenteDB(String userEmail, String password) {   //ritorna i dati trovati nel DB dell'utente con 'userEmail' e 'password'
        ResultSet rs = null; //utente con 'userEmail' e 'password' trovato

        try{
            PreparedStatement validaUtentePS = connection.prepareStatement(
                    "SELECT * FROM utente WHERE passwordu = '"+password+"' AND (username = '"+userEmail+"'" +
                            "OR email = '"+userEmail+"');"  //prepara la query che cerca l'utente con userEmail' e 'password'
            );

            rs = validaUtentePS.executeQuery(); //esegue la query
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getUtenteDB(String username) {   //ritorna i dati trovati nel DB dell'utente con 'userEmail' e 'password'
        ResultSet rs = null; //utente con 'userEmail' e 'password' trovato

        try{
            PreparedStatement getUtentePS = connection.prepareStatement(
                    "SELECT * FROM utente WHERE username = '"+username+"';"  //prepara la query che cerca l'utente con userEmail' e 'password'
            );

            rs = getUtentePS.executeQuery(); //esegue la query
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public int validaModEmailDB(String emailM){ //ritorna il numero di utenti con 'emailM' presenti nel DB
        ResultSet rs;
        int n = 0;  //numero di utenti trovati con 'emailM'

        try{
            PreparedStatement validaEmailPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE email = '"+emailM+"';" //prepara la query che conta gli utenti con 'emailM'
            );

            rs = validaEmailPS.executeQuery();  //esegue la query

            while(rs.next()){
                n = rs.getInt("total"); //inserisce in 'n' il risultato della query
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public  int validaModUsernameDB(String usernameM){  //ritorna il numero di utenti con 'usernameM' presenti nel DB
        ResultSet rs;
        int n = 0;  //numero di utenti trovati con 'emailM'

        try{
            PreparedStatement validaUsernamePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE username = '"+usernameM+"';"   //prepara la query che conta gli utenti con 'usernameM'
            );

            rs = validaUsernamePS.executeQuery();   //esegue la query

            while(rs.next()){
                n = rs.getInt("total"); //inserisce in 'n' il risultato della query
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public  int validaModPIVADB(String pIVAM){  //ritorna il numero di utenti con 'pIVAM' presenti nel DB
        ResultSet rs;
        int n = 0;  //numero di utenti trovati con 'emailM'

        try{
            PreparedStatement validaPIVAPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE partitaiva = '"+pIVAM+"';" //prepara la query che conta gli utenti con 'pIVAM'
            );

            rs = validaPIVAPS.executeQuery();   //esegue la query

            while(rs.next()){
                n = rs.getInt("total"); //inserisce in 'n' il risultato della query
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }

    @Override
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername){    //modifica i dati nel DB dell'utente con l'username 'oldUsername'
        try {
            PreparedStatement modUtentePS = connection.prepareStatement(
                 "UPDATE utente SET email = '"+email+"', nome = '"+nome+"', cognome = '"+cognome+"', username = '"+username+"', passwordu = '"+password+"', partitaiva = '"+partitaIVA+"' WHERE username = '"+oldUsername+"';"  //prepara la query di modific dell'utente con 'oldUsername' come utente
            );

            modUtentePS.executeUpdate();    //esegue la query
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2){
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