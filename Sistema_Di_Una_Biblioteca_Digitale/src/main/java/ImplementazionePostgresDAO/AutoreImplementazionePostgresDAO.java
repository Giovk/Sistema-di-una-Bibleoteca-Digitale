package ImplementazionePostgresDAO;

import DAO.AutoreDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

/**
 * La classe AutoreImplementazionePostgresDAO implementa l'interfaccia AutoreDAO, quindi contiene le implementazioni dei metodi che interagiscono
 * con il database per implementare le funzionalità relative agli autori di libri e articoli scientifici.
 */
public class AutoreImplementazionePostgresDAO implements AutoreDAO{
    private Connection connection;

    /**
     * Istanzia un nuovo AutoreImplementazionePostgresDAO.
     */
    public AutoreImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getAutoriLibroDB(String isbn){  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'
        ResultSet rs = null;    //contiene gli autori del libro con ISBN 'isbn' trovati

        try {
            PreparedStatement getAutoriLibroPS = connection.prepareStatement(
                    "SELECT * FROM libro NATURAL JOIN scrittura_l NATURAL JOIN autore WHERE libro.isbn = '"+isbn+"';"   //prepara la query che cerca tutti gli autori del libro con ISBN 'isbn'
            );

            rs = getAutoriLibroPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getAutoriLibroDB

    @Override
    public ResultSet getAutoriArticoloDB(String doi){   //ritorna i dati di tutti gli autori nel DB dell'articolo con DOI 'doi'
        ResultSet rs = null;    //contiene tutti gli autori dell'articolo con DOI 'doi'

        try {
            PreparedStatement getAutoriArticoloPS = connection.prepareStatement(
            "SELECT * FROM articolo_scientifico NATURAL JOIN scrittura_a NATURAL JOIN autore WHERE articolo_scientifico.doi = '"+doi+"';"   //prepara la query che cerca tutti gli autori dell'articolo con DOI 'doi'
            );

            rs = getAutoriArticoloPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getAutoriArticoloDB

    @Override
    public void aggiungiAutoreLibroDB(String nome, String cognome, String nazionalita, String dn, String isbn){ //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore al libro con ISBN 'isbn'
        ResultSet rs = null;    //contiene il numero di autori con 'nome', 'cognome', 'dn' e 'nazionalita' e il codice del nuovo autore

        try {
            String query = "SELECT COUNT(*) AS contatore FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //inizializza la query che conta gli autori con 'nome', 'cognome', 'dn' e 'nazionalita'

            if (nazionalita.isBlank()){ //controlla se non è stata inserita la nazionalità
                query = query + "AND nazionalita IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND nazionalita = '"+nazionalita+"' "; //aggiorna 'query'
            }

            if (dn.isBlank()){  //controlla se non è stata inserita la data di nascita
                query = query + "AND datanascita IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND datanascita = '"+dn+"' ";  //aggiorna 'query'
            }

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);    //prepara la query

            rs = aggiungiAutorePS.executeQuery(); //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di autori con 'nome', 'cognome', 'dn' e 'nazionalita'
                    if (rs.getInt("contatore") == 0){   //controlla se non ci sono autori con 'nome', 'cognome', 'dn' e 'nazionalita'
                        try{
                            query = "INSERT INTO autore(nome, cognome, nazionalita, datanascita) VALUES (?, ?, ?, ?)";  //inizializza la query che inserisce il nuovo autore
                            aggiungiAutorePS = connection.prepareStatement(query);  //prepara la query

                            aggiungiAutorePS.setString(1, nome);    //aggiunge 'nome' nella query
                            aggiungiAutorePS.setString(2, cognome); //aggiunge 'cognome' nella query

                            if(nazionalita.isBlank()){  //controlla se non è stata inserita la nazionalità
                                aggiungiAutorePS.setNull(3, Types.NULL);    //aggiorna la query
                            }else {
                                aggiungiAutorePS.setString(3, nazionalita); //aggiunge 'nazionalita' nella query
                            }

                            if(dn.isBlank()){   //controlla se non è stata inserita la nazionalità
                                aggiungiAutorePS.setNull(4, Types.NULL);    //aggiorna la query
                            } else{
                                aggiungiAutorePS.setDate(4, Date.valueOf(dn));  //aggiunge 'dn' nella query
                            }

                            aggiungiAutorePS.executeUpdate();    //esegue la query

                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }

                rs.close(); //chiude 'rs'
            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "SELECT coda FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //inizializza la query che cerca il codice del nuovo autore

            if (nazionalita.isBlank()){ //controlla se non è stata inserita la nazionalità
                query = query + "AND nazionalita IS NULL "; //aggiorna la query
            }else{
                query = query + "AND nazionalita = '"+nazionalita+"' "; //aggiorna la query
            }


            if (dn.isBlank()){  //controlla se non è stata inserita la data di nascita
                query = query + "AND datanascita IS NULL "; //aggiorna la query
            }else{
                query = query + "AND datanascita = '"+dn+"' ";  //aggiorna la query
            }

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);    //prepara la query

            rs = aggiungiAutorePS.executeQuery(); //esegue la query

            while (rs.next()){  //scorre il ResultSet 'rs' con il codice del nuovo autore
                aggiungiAutorePS = connection.prepareStatement(
                        "INSERT INTO scrittura_l(isbn, coda) VALUES ('"+isbn+"', '"+rs.getInt("coda")+"')"  //prepara la query che associa il libro con ISBN 'isbn' al nuovo autore
                );

                aggiungiAutorePS.executeUpdate();   //esegue la query
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine aggiungiAutoreLibroDB

    @Override
    public void aggiungiAutoreArticoloDB(String nome, String cognome, String nazionalita, String dn, String doi){   //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore all'articolo con DOI 'doi'
        ResultSet rs = null;    //contiene il numero di autori con 'nome', 'cognome', 'dn' e 'nazionalita' e il codice del nuovo autore

        try {
            String query = "SELECT COUNT(*) AS contatore FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //inizializza la query che conta gli autori con 'nome', 'cognome', 'dn' e 'nazionalita'

            if (nazionalita.isBlank()){ //controlla se non è stata inserita la nazionalità
                query = query + "AND nazionalita IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND nazionalita = '"+nazionalita+"' "; //aggiorna 'query'
            }

            if (dn.isBlank()){  //controlla se non è stata inserita la data di nascita
                query = query + "AND datanascita IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND datanascita = '"+dn+"' ";  //aggiorna 'query'
            }

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);    //prepara la query

            rs = aggiungiAutorePS.executeQuery();   //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di autori con 'nome', 'cognome', 'dn' e 'nazionalita'
                    if (rs.getInt("contatore") == 0){   //controlla se non ci sono autori con 'nome', 'cognome', 'dn' e 'nazionalita'
                        try{
                            query = "INSERT INTO autore(nome, cognome, nazionalita, datanascita) VALUES (?, ?, ?, ?)";  //inizializza la query che inserisce il nuovo autore
                            aggiungiAutorePS = connection.prepareStatement(query);  //prepara la query

                            aggiungiAutorePS.setString(1, nome);    //aggiunge 'nome' nella query
                            aggiungiAutorePS.setString(2, cognome); //aggiunge 'cognome' nella query

                            if(nazionalita.isBlank()){  //controlla se non è stata inserita la nazionalità
                                aggiungiAutorePS.setNull(3, Types.NULL);    //aggiorna la query
                            }else{
                                aggiungiAutorePS.setString(3, nazionalita); //aggiunge 'nazionalita' nella query
                            }

                            if(dn.isBlank()){   //controlla se non è stata inserita la nazionalità
                                aggiungiAutorePS.setNull(4, Types.NULL);    //aggiorna la query
                            }else{
                                aggiungiAutorePS.setDate(4, Date.valueOf(dn));  //aggiunge 'dn' nella query
                            }

                            aggiungiAutorePS.executeUpdate();    //esegue la query
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }

                rs.close(); //chiude 'rs'
            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String query = "SELECT coda FROM autore WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' "; //inizializza la query che cerca il codice del nuovo autore

            if (nazionalita.isBlank()){ //controlla se non è stata inserita la nazionalità
                query = query + "AND nazionalita IS NULL "; //aggiorna la query
            }else{
                query = query + "AND nazionalita = '"+nazionalita+"' "; //aggiorna la query
            }

            if (dn.isBlank()){  //controlla se non è stata inserita la data di nascita
                query = query + "AND datanascita IS NULL "; //aggiorna la query
            }else{
                query = query + "AND datanascita = '"+dn+"' ";  //aggiorna la query
            }

            PreparedStatement aggiungiAutorePS = connection.prepareStatement(query);    //prepara la query

            rs = aggiungiAutorePS.executeQuery(); //esegue la query

            while (rs.next()){
                aggiungiAutorePS = connection.prepareStatement(
                        "INSERT INTO scrittura_a(doi, coda) VALUES ('"+doi+"', '"+rs.getInt("coda")+"')"    //prepara la query che associa l'articolo con DOI 'doi' al nuovo autore
                );

                aggiungiAutorePS.executeUpdate();   //esegue la query
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine Customize Toolbar...

    /**
     * Chiude la connessione al database.
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