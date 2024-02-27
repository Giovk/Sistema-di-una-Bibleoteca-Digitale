package ImplementazionePostgresDAO;

import DAO.UtenteDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

/**
 * La classe UtenteImplementazionePostgresDAO implementa l'interfaccia UtenteDAO, quindi contiene le implementazioni dei metodi che interagiscono
 * con il database per implementare le funzionalità relative agli utenti.
 */
public class UtenteImplementazionePostgresDAO implements UtenteDAO {
    private Connection connection;

    /**
     * Istanzia un nuovo UtenteImplementazionePostgresDAO.
     */
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
            String query = "INSERT INTO utente VALUES(?, ?, ?, ?, ?, ?, ?)";    //inizializza la query che inserisce un nuovo utente
            PreparedStatement addUtentePS = connection.prepareStatement(query); //prepara la query

            addUtentePS.setString(1, username); //aggiunge 'username' nella query
            addUtentePS.setString(2, email);    //aggiunge 'email' nella query
            addUtentePS.setString(3, password); //aggiunge 'password' nella query

            if(partitaIVA.isBlank()){   //controlla se non è stata inserita la partita IVA
                addUtentePS.setNull(4, Types.NULL); //aggiunge 'partitaIVA' nella query
            }else{
                addUtentePS.setString(4, partitaIVA);   //aggiunge 'partitaIVA' nella query
            }

            addUtentePS.setString(5, nome); //aggiunge 'nome' nella query
            addUtentePS.setString(6, cognome);  //aggiunge 'cognome' nella query

            if(dataNascita == null){    //controlla se non è stata inserita la data di nascita
                addUtentePS.setNull(7, Types.NULL); //inserisce NULL nella query
            } else {
                java.sql.Date dNascita = Date.valueOf(dataNascita); //data di nascita del nuovo utente
                addUtentePS.setDate(7, dNascita);   //inserisce 'dNascita' nella query
            }

            addUtentePS.executeUpdate();    //esegue la query
            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }//fine addUtenteDB

    @Override
    public int validaUtenteDB(String userEmail, String password) {  //ritorna il numero di utenti registrati con username o email 'userMail' e con password 'password'
        ResultSet rs;   //contiene il numero di utenti con 'userEmail' e 'password'
        int n = 0;  //utenti con 'userEmail' e 'password'

        try{
            PreparedStatement validaUtentePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE passwordu = '"+password+"' AND (username = '"+userEmail+"'" +
                            "OR email = '"+userEmail+"');"  //prepara la query che conta gli utenti con 'userEmail' e 'password'
            );

            rs = validaUtentePS.executeQuery(); //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' contenente il numero di utenti con 'userEmail' e 'password'
                n = rs.getInt("total"); //aggiorna 'n'
            }

            connection.close(); //chiude la connessione
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }//fine validaUtenteDB

    @Override
    public ResultSet getUtenteDB(String userEmail, String password){   //cerca i dati  nel DB dell'utente con 'userEmail' e 'password'
        ResultSet rs = null;    //contiene l'utente con 'userEmail' e 'password'

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
    }//fine getUtenteDB

    @Override
    public ResultSet getUtenteDB(String username) {   //cerca i dati trovati nel DB dell'utente con 'username'
        ResultSet rs = null; //contiene l'utente con username 'username'

        try{
            PreparedStatement getUtentePS = connection.prepareStatement(
                    "SELECT * FROM utente WHERE username = '"+username+"';" //prepara la query che cerca l'utente con username 'username'
            );

            rs = getUtentePS.executeQuery();    //esegue la query
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return rs;
    }//fine getUtenteDB

    @Override
    public int validaModEmailDB(String emailM){ //ritorna il numero di utenti con email 'emailM' presenti nel DB
        ResultSet rs;   //contiene il numero di utenti con 'emailM' presenti nel DB
        int n = 0;  //numero di utenti con email 'emailM'

        try{
            PreparedStatement validaEmailPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE email = '"+emailM+"';" //prepara la query che conta gli utenti con email 'emailM'
            );

            rs = validaEmailPS.executeQuery();  //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' contenente il numero di utenti con email 'emailM'
                n = rs.getInt("total"); //aggiorna 'n'
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }//fine validaModEmailDB

    @Override
    public  int validaModUsernameDB(String usernameM){  //ritorna il numero di utenti con username 'usernameM' presenti nel DB
        ResultSet rs;   //contiene il numero di utenti con username 'useranameM' presenti nel DB
        int n = 0;  //numero di utenti con username 'useranameM'

        try{
            PreparedStatement validaUsernamePS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE username = '"+usernameM+"';"   //prepara la query che conta gli utenti con username 'usernameM'
            );

            rs = validaUsernamePS.executeQuery();   //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' contenente il numero di utenti con username 'usernameM'
                n = rs.getInt("total"); //aggiorna 'n'
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }//fine validaModUsernameDB

    @Override
    public int validaModPIVADB(String pIVAM){  //ritorna il numero di utenti con partita IVA 'pIVAM' presenti nel DB
        ResultSet rs;   //contiene il numero di utenti con partita IVA 'pIVAM' presenti nel DB
        int n = 0;  //numero di utenti con partita IVA 'pIVAM'

        try{
            PreparedStatement validaPIVAPS = connection.prepareStatement(
                    "SELECT COUNT (*) AS total FROM utente WHERE partitaiva = '"+pIVAM+"';" //prepara la query che conta gli utenti con partita IVA 'pIVAM'
            );

            rs = validaPIVAPS.executeQuery();   //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' contenente il numero di utenti con partita IVA 'pIVAM'
                n = rs.getInt("total"); //aggiorna 'n'
            }
        } catch (SQLException var2){
            var2.printStackTrace();
        }

        return n;
    }//fine validaModPIVADB

    @Override
    public void modUtenteDB(String email, String nome, String cognome, String username, String password, String partitaIVA, String oldUsername){    //modifica i dati nel DB dell'utente con l'username 'oldUsername'
        try {
            if(partitaIVA != null) {    //controlla se non è stata inserita la partita IVA
                PreparedStatement modUtentePS = connection.prepareStatement(
                        "UPDATE utente SET email = '" + email + "', nome = '" + nome + "', cognome = '" + cognome + "', username = '" + username + "', passwordu = '" + password + "', partitaiva = '" + partitaIVA + "' WHERE username = '" + oldUsername + "';"   //prepara la query che modifica l'utente
                );

                modUtentePS.executeUpdate();    //esegue la query
            } else {
                PreparedStatement modUtentePS = connection.prepareStatement(
                        "UPDATE utente SET email = '" + email + "', nome = '" + nome + "', cognome = '" + cognome + "', username = '" + username + "', passwordu = '" + password + "', partitaiva = NULL WHERE username = '" + oldUsername + "';"   //prepara la query che modifica l'utente
                );

                modUtentePS.executeUpdate();    //esegue la query
            }

            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2){
            var2.printStackTrace();
        }
    }//fine modUtenteDB

    @Override
    public void chiudiConnessione(){    //chiude la connessione al DB
        try{
            if (connection != null && !connection.isClosed()){  //controlla se la connessione è chiusa
                connection.close(); //chiude la connessione
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//fine chiudiConnessione
}