package ImplementazionePostgresDAO;

import DAO.LibreriaDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

/**
 * The type Libreria implementazione postgres dao.
 */
public class LibreriaImplementazionePostgresDAO implements LibreriaDAO {
    private Connection connection;

    /**
     * Instantiates a new Libreria implementazione postgres dao.
     */
    public LibreriaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet disponibilitaDB(String isbn){  //ritorna le disponibilità del libro o della serie con ISBN 'isbn' nelle librerie
        ResultSet rs = null;    //contiene disponibilità trovate

        try {
            PreparedStatement getDisponibilitaPS = connection.prepareStatement(
                    "SELECT * FROM libreria NATURAL JOIN possesso_l WHERE isbn = '"+isbn+"'" +  //prepara la query che cerca le disponibilità del libro
                    " UNION " +
                            "SELECT * FROM libreria NATURAL JOIN possesso_s WHERE isbn = '"+isbn+"';"   //prepara la query che cerca le disponibilità della serie
            );

            rs = getDisponibilitaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine disponibilitaDB

    @Override
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo){   //cerca le disponibilità del fascicolo numero 'numero' della rivista 'titolo' nelle librerie
        ResultSet rs = null;    //contiene disponibilità trovate

        try {
            PreparedStatement getDisponibilitaPS = connection.prepareStatement(
                    "SELECT * FROM libreria NATURAL JOIN possesso_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE fascicolo.numero = '"+numero+"' AND rivista.titolo = '"+titolo+"';"//prepara la query che cerca le disponibilità del fascicolo
            );

            rs = getDisponibilitaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine disponibilitaFascicoloDB

    @Override
    public ResultSet getLibrerieUtenteDB(String user){  //cerca tutte le librerie dell'utente 'user'
        ResultSet rs = null;    //contiene le librerie dell'utente 'user'

        try {
            PreparedStatement getLibrerieUtentePS = connection.prepareStatement(
                    "SELECT * FROM libreria WHERE gestore = '"+user+"'" //prepara la query che cerca le librerie dell'utente 'user'
            );

            rs = getLibrerieUtentePS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getLibrerieUtenteDB

    @Override
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt){   //controlla se il numero telefonico 'nt' è già presente nel DB
        ResultSet rs = null;    //contiene il numero di librerie con numero telefonico 'nt'

        try {
            PreparedStatement presenzaNumeroTelefonicoLibreriaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS conteggio FROM libreria WHERE numerotelefonico = '"+nt+"'"  //prepara la query che conta le librerie con numero telefonico 'nt'
            );

            rs = presenzaNumeroTelefonicoLibreriaPS.executeQuery(); //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di librerie con numero telefonico 'nt'
                    if(rs.getInt("conteggio") >= 1){    //controlla se ci sono librerie con numero telefonico 'nt'
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }
            } catch (SQLException var){
                var.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine presenzaNumeroTelefonicoLibreriaDB

    @Override
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo){    //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB
        ResultSet rs = null;    //contiene il numero di librerie chiamate 'nome' con sito web 'sw' e indirizzo 'indirizzo'

        try {
            String query = "SELECT COUNT(*) AS conteggio FROM libreria WHERE nome = '"+nome+"' ";   //inizializza la query che conta le librerie chiamate 'nome' con sito web 'sw' e indirizzo 'indirizzo'

            if (sw.isBlank()){  //controlla se non è stato inserito il sito web
                query = query + "AND sitoweb IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND sitoweb = '"+sw+"' ";  //aggiorna 'query'
            }

            if (indirizzo.isBlank()){   //controlla se non è stato inserito l'indirizzo
                query = query + "AND indirizzo IS NULL ";   //aggiorna 'query'
            }else{
                query = query + "AND indirizzo = '"+indirizzo+"' "; //aggiorna 'query'
            }

            PreparedStatement presenzaLibreriaPS = connection.prepareStatement(query);  //prepara la query

            rs = presenzaLibreriaPS.executeQuery(); //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di librerie chiamate 'nome' con sito web 'sw' e indirizzo 'indirizzo'
                    if(rs.getInt("conteggio") >= 1){    //controlla se ci sono librerie chiamate 'nome' con sito web 'sw' e indirizzo 'indirizzo'
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }
            } catch (SQLException var){
                var.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
        return true;
    }//fine presenzaLibreriaDB

    @Override
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user){    //aggiunge la libreria 'nome' dell'utente 'user' con numero telefoico 'nt', sito web 'sw' e indirizzo 'indirizzo'
        try {
            String query = "INSERT INTO libreria(nome, numerotelefonico, sitoweb, indirizzo, gestore) VALUES (?, ?, ?, ?, ?)";  //inizializza la query che inserisce la nuova libreria
            PreparedStatement addLibreriaPS = connection.prepareStatement(query);   //prepara la query

            addLibreriaPS.setString(1, nome);   //aggiunge 'nome' nella query
            addLibreriaPS.setString(2, nt); //aggiunge 'nt' nella query

            if(sw.isBlank()){   //controlla se non è stato inserito il sito web
                addLibreriaPS.setNull(3, Types.NULL);   //aggiorna la query
            }else{
                addLibreriaPS.setString(3, sw); //aggiorna la query
            }

            if(indirizzo.isBlank()){    //controlla se non è stato inserito l'indirizzo
                addLibreriaPS.setNull(4, Types.NULL);   //aggiorna la query
            }else{
                addLibreriaPS.setString(4, indirizzo);  //aggiorna la query
            }

            addLibreriaPS.setString(5, user);   //aggiunge 'user' nella query

            addLibreriaPS.executeUpdate();  //esegue la query
            connection.close(); //chiude la connessione al DB
        }  catch (SQLException var2){
            var2.printStackTrace();
        }
    }//fine addLibreriaDB

    @Override
    public void removeLibreriaDB(String nt){    //rimuove dal DB la libreria con il numero telefonico 'nt'
        try {
            PreparedStatement removeLibreriaPS = connection.prepareStatement(
                    "DELETE FROM libreria WHERE numerotelefonico = '"+nt+"'"    //prepara la query che rimuove il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'
            );

            removeLibreriaPS.executeUpdate();  //esegue la query
            chiudiConnessione();    //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }//fine removeLibreriaDB

    /**
     * Chiudi connessione.
     */
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