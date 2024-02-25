package ImplementazionePostgresDAO;

import DAO.RecensioneDAO;
import Database.ConnessioneDatabase;

import java.sql.*;
import java.util.ArrayList;

/**
 * The type Recensione implementazione postgres dao.
 */
public class RecensioneImplementazionePostgresDAO implements RecensioneDAO {
    private Connection connection;

    /**
     * Instantiates a new Recensione implementazione postgres dao.
     */
    public  RecensioneImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        }
        catch (SQLException var2){
            var2.printStackTrace();
        }
    }

    @Override
    public float valutazioneMediaLibroDB(String isbn) { //ritorna la media delle valutazioni del libro con isbn 'isbn'
        ResultSet rs = null;    //contiene la media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaLibroPS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_l WHERE isbn = '"+isbn+"'" //prepara la query che calcola il valore medio delle valutazioni del libro con isbn 'isbn'
            );

            rs = valutazioneMediaLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);    //aggiorna 'vm'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
    }//fine valutazioneMediaLibroDB

    @Override
    public boolean likeLibroDB(String isbn, String user) { //controlla se l'utente 'user' ha il libro con ISBN 'isbn' tra i preferiti
        ResultSet rs = null;    //valore trovato
        boolean like = false;   //risultato

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che controlla se l'utente ha il libro nei preferiti
            );

            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                like = rs.getBoolean(1);    //aggiorna 'like'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }//fine likeLibroDB

    @Override
    public boolean changeLikeLibroDB(boolean like, String isbn, String user) {  //toglie/mette nei preferiti dell'utente 'user' il libro con ISBN 'isbn' e ritorna l'opposto di 'like'
        ResultSet rs = null;    //contiene il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
        int item = 1;   //numero di recensioni fatte da 'user' al libro con ISBN 'isbn'

        if (like == true){  //controlla se 'user' ha il libro con ISBN 'isbn' nei preferiti
            like = false;   //aggiorna 'like'
        }else{
            like = true;    //aggiorna 'like'
        }

        try {
            PreparedStatement changeLikeLibroPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
            );

            rs = changeLikeLibroPS.executeQuery();  //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item < 1) { //controlla se non c'è una recensione del libro con ISBN 'isbn' fatta da 'user'
            try {
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "INSERT INTO recensione_l(username, isbn, preferito) " +
                                "VALUES ('"+user+"', '"+isbn+"', '"+like+"')"   //prepara la query che aggiuge una nuova recensione del libro con ISBN 'isbn' fatta da 'user'
                );

                changeLikePS.executeUpdate(); //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            if(like == false) { //controlla se 'user' non ha messo il libro con ISBN 'isbn' nei preferiti
                try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "DELETE from recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"' AND valutazione IS NULL AND testo IS NULL"  //elimina la recensione del libro con ISBN 'isbn' fatta da 'user'
                    );

                    changeLikePS.executeUpdate(); //esegue la query
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }

            try {
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "UPDATE recensione_l SET preferito = '" + like + "' WHERE isbn = '" + isbn + "' AND username = '" + user + "'" //prepara la query che aggiunge il libro con ISBN 'isbn' nei preferiti di 'user'
                );

                changeLikePS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return like;
    }//fine changeLikeLibroDB

    @Override
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user){   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro con ISBN 'isbn'
        ResultSet rs = null;    //contiene il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
        int item = 1;   //numero di recensioni fatte da 'user' al libro con ISBN 'isbn'

        try {
            PreparedStatement addRecensioneLibro1PS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
            );

            rs = addRecensioneLibro1PS.executeQuery();    //esegue la query

            while(rs.next()){   //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' al libro con ISBN 'isbn'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è una recensione del libro con ISBN 'isbn' fatta da 'user'
            try {
                String query = "UPDATE recensione_l SET valutazione = ?, testo = ? WHERE isbn = ? AND username = ?";    //inizializza la query che aggiorna la recensione del libro con ISBN 'isbn' fatta da 'user'
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);    //prepara la query

                addRecensioneLibroPS.setInt(1, valutazione);    //aggiunge 'valutazione' nella query

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneLibroPS.setNull(2, Types.NULL);    //aggiunge 'text' nella query
                } else {
                    addRecensioneLibroPS.setString(2, text);   //aggiunge 'text' nella query
                }

                addRecensioneLibroPS.setString(3, isbn);    //aggiunge 'isbn' nella query
                addRecensioneLibroPS.setString(4, user);    //aggiunge 'user' nella query

                addRecensioneLibroPS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_l(username, isbn, valutazione, testo) VALUES (?, ?, ?, ?)";  //inizializza la query che inserisce la nuova recensione
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);    //prepara la query

                addRecensioneLibroPS.setInt(3, valutazione);    //aggiunge 'valutazione'

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneLibroPS.setNull(4, Types.NULL); //aggiunge 'text'
                }else{
                    addRecensioneLibroPS.setString(4, text);    //aggiunge 'text'
                }

                addRecensioneLibroPS.setString(2, isbn);     //aggiunge 'isbn'
                addRecensioneLibroPS.setString(1, user);     //aggiunge 'user'

                addRecensioneLibroPS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }//fine addRecensioneLibroDB

    public ResultSet allRecWithCommentLibroDB(String isbn){  //ritorna tutte le recensioni con un testo fatte al libro con ISBN 'isbn'
        ResultSet rs = null;    //contiene tutte le recensioni con un testo fatte al libro con ISBN 'isbn'

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_l WHERE isbn = '"+isbn+"' AND testo IS NOT NULL AND VALUTAZIONE IS NOT NULL"   //prepara la query che cerca tutte le recensioni con un testo fatte al libro con ISBN 'isbn'
            );

            rs = allRecWithCommentPS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine allRecWithCommentLibroDB

    @Override
    public float valutazioneMediaSerieDB(String isbn) { //ritorna la media delle valutazioni della serie con ISBN 'isbn'
        ResultSet rs = null;    //contiene la media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaSeriePS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_s WHERE isbn = '"+isbn+"'" //prepara la query che calcola il valore medio delle valutazioni della serie con ISBN 'isbn'
            );

            rs = valutazioneMediaSeriePS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);    //aggiorna 'vm'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
    }//fine valutazioneMediaSerieDB

    @Override
    public boolean likeSerieDB(String isbn, String user) {  //controlla se l'utente 'user' ha la serie con ISBN 'isbn' tra i preferiti
        ResultSet rs = null;    //valore trovato
        boolean like = false;   //risultato

        try {
            PreparedStatement likeSeriePS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'"  //prepara la query che controlla se l'utente ha la serie nei preferiti
            );

            rs = likeSeriePS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                like = rs.getBoolean(1);    //aggiorna 'like'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }//fine likeSerieDB

    @Override
    public boolean changeLikeSerieDB(boolean like, String isbn, String user) {  //toglie/mette nei preferiti dell'utente 'user' la serie con ISBN 'isbn' e ritorna l'opposto di 'like'
        ResultSet rs = null;    //contiene il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
        int item = 1;   //numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'

        if (like == true){  //controlla se 'user' ha la serie con ISBN 'isbn' nei preferiti
            like = false;   //aggiorna 'like'
        }else{
            like = true;    //aggiorna 'like'
        }

        try {
            PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
            );

            rs = changeLikeSeriePS.executeQuery();  //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item < 1) {  //controlla se non c'è una recensione della serie con ISBN 'isbn' fatta da 'user'
            try {
                PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                        "INSERT INTO recensione_s(username, isbn, preferito) " +
                                "VALUES ('"+user+"', '"+isbn+"', '"+like+"')"   //prepara la query che aggiuge una nuova recensione della serie con ISBN 'isbn' fatta da 'user'
                );

                changeLikeSeriePS.executeUpdate(); //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            if(like == false) { //controlla se 'user' non ha messo la serie con ISBN 'isbn' nei preferiti
                try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "DELETE from recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"' AND valutazione IS NULL AND testo IS NULL"  //elimina la recensione della serie con ISBN 'isbn' fatta da 'user'
                    );

                    changeLikePS.executeUpdate();   //esegue la query
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }

            try {
                PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                        "UPDATE recensione_s SET preferito = '" + like + "' WHERE isbn = '" + isbn + "' AND username = '" + user + "'"  //prepara la query che aggiunge il libro con ISBN 'isbn' nei preferiti di 'user'
                );

                changeLikeSeriePS.executeUpdate();  //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return like;
    }//fine changeLikeSerieDB

    @Override
    public void addRecensioneSerieDB(int valutazione, String text, String isbn, String user){   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' la serie con ISBN 'isbn'
        ResultSet rs = null;    //contiene il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
        int item = 1;   //numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'

        try {
            PreparedStatement addRecensioneSeriePS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
            );

            rs = addRecensioneSeriePS.executeQuery();   //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' alla serie con ISBN 'isbn'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è una recensione della serie con ISBN 'isbn' fatta da 'user'
            try {
                String query = "UPDATE recensione_s SET valutazione = ?, testo = ? WHERE isbn = ? AND username = ?";    //inizializza la query che aggiorna la recensione della serie con ISBN 'isbn' fatta da 'user'
                PreparedStatement addRecensioneSeriePS = connection.prepareStatement(query);    //prepara la query

                addRecensioneSeriePS.setInt(1, valutazione);    //aggiunge 'valutazione' nella query

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneSeriePS.setNull(2, Types.NULL);    //aggiunge 'text' nella query
                }else{
                    addRecensioneSeriePS.setString(2, text);    //aggiunge 'text' nella query
                }

                addRecensioneSeriePS.setString(3, isbn);    ///aggiunge 'isbn' nella query
                addRecensioneSeriePS.setString(4, user);    ///aggiunge 'user' nella query

                addRecensioneSeriePS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_s(username, isbn, valutazione, testo) VALUES (?, ?, ?, ?)";  //inizializza la query che inserisce la nuova recensione
                PreparedStatement addRecensioneSeriePS = connection.prepareStatement(query);    //prepara la query

                addRecensioneSeriePS.setInt(3, valutazione);    //aggiunge 'valutazione' nella query

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneSeriePS.setNull(4, Types.NULL);    //aggiunge 'text' nella query
                }else{
                    addRecensioneSeriePS.setString(4, text);    //aggiunge 'text' nella query
                }

                addRecensioneSeriePS.setString(2, isbn);     ///aggiunge 'isbn' nella query
                addRecensioneSeriePS.setString(1, user);     ///aggiunge 'user' nella query

                addRecensioneSeriePS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet allRecWithCommentSerieDB(String isbn){ //ritorna tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'
        ResultSet rs = null;    //contiene tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_s WHERE isbn = '"+isbn+"' AND testo IS NOT NULL AND VALUTAZIONE IS NOT NULL"  //prepara la query che cerca tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'
            );

            rs = allRecWithCommentPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine allRecWithCommentSerieDB

    @Override
    public float valutazioneMediaFascicoloDB(int numero, String titolo) {   //ritorna la media delle valutazioni del fascicolo numero 'numero' della rivista 'titolo'
        ResultSet rs = null;    //contiene la media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaFascicoloPS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"';"    //prepara la query che calcola il valore medio delle valutazioni del fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = valutazioneMediaFascicoloPS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);    //aggiorna 'vm'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
    }//fine valutazioneMediaFascicoloDB

    @Override
    public boolean likeFascicoloDB(int numero, String titolo, String user) {    //controlla se l'utente 'user' ha il fascicolo numero 'numero' della rivista 'titolo' tra i preferiti
        ResultSet rs = null;    //valore trovato
        boolean like = false;   //risultato

        try {
            PreparedStatement likeFascicoloPS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"' AND username = '"+user+"'"  //prepara la query che controlla se l'utente ha il fascicolo nei preferiti
            );

            rs = likeFascicoloPS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                like = rs.getBoolean(1);    //aggiorna 'like'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }//fine likeFascicoloDB

    @Override
    public boolean changeLikeFascicoloDB(boolean like, int numero, String titolo, String user) {    //toglie/mette nei preferiti dell'utente 'user' il fascicolo numero 'numero' della rivista 'titolo' e ritorna l'opposto di 'like'
        ResultSet rs = null;    //contiene il fascicolo numero 'numero' della rivista 'titolo' e il numero di recensioni fatte ad esso da 'user'
        int item = 1;   //numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
        int codF = -1;  //codice del fascicolo numero 'numero' della rivista 'titolo'

        if (like == true){  //controlla se 'user' ha il fascicolo numero 'numero' della rivista 'titolo' nei preferiti
            like = false;   //aggiorna 'like'
        }else{
            like = true;    //aggiorna 'like'
        }

        try {
            PreparedStatement changeLikeFascicoloPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = changeLikeFascicoloPS.executeQuery();  //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        try {
            PreparedStatement changeLikeFascicoloPS = connection.prepareStatement(
                    "SELECT * FROM fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"'"  //prepara la query che cerca il fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = changeLikeFascicoloPS.executeQuery();  //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con il fascicolo numero 'numero' della rivista 'titolo'
                codF = rs.getInt("codf");   //aggiorna 'codF'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item < 1) {  //controlla se non c'è una recensione del fascicolo numero 'numero' della rivista 'titolo' fatta da 'user'
            try {
                PreparedStatement changeLikeFascicoloPS = connection.prepareStatement(
                        "INSERT INTO recensione_f(username, codf, preferito) " +
                                "VALUES ('"+user+"', '"+codF+"', '"+like+"')"   //prepara la query che aggiuge una nuova recensione del fascicolo numero 'numero' della rivista 'titolo' fatta da 'user'
                );

                changeLikeFascicoloPS.executeUpdate();  //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            if(like == false) { //controlla se 'user' non ha messo il fascicolo numero 'numero' della rivista 'titolo' nei preferiti
                try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "DELETE FROM recensione_f WHERE codf = '"+codF+"' AND username = '"+user+"' AND valutazione IS NULL AND testo IS NULL"  //elimina la recensione del fascicolo numero 'numero' della rivista 'titolo' fatta da 'user'
                    );

                    changeLikePS.executeUpdate();   //esegue la query
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }

            try {
                PreparedStatement changeLikeFascicoloPS = connection.prepareStatement(
                        "UPDATE recensione_f SET preferito = '" + like + "' WHERE codf = '" + codF + "' AND username = '" + user + "'"  //prepara la query che aggiunge il fascicolo numero 'numero' della rivista 'titolo' nei preferiti di 'user'
                );

                changeLikeFascicoloPS.executeUpdate();  //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return like;
    }//fine changeLikeFascicoloDB

    @Override
    public void addRecensioneFascicoloDB(int valutazione, String text, int numero, String titolo, String user){ //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al fascicolo numero 'numero' della rivista 'titolo'
        ResultSet rs = null;    //contiene il fascicolo numero 'numero' della rivista 'titolo' e il numero di recensioni fatte ad esso da 'user'
        int item = 1;       //numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo' e il numero di recensioni fatte ad esso da 'user'
        int codF = -1;  //codice del fascicolo numero 'numero' della rivista 'titolo'

        try {
            PreparedStatement addRecensioneFascicoloPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"' AND username = '"+user+"'"  //prepara la query che conta il numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = addRecensioneFascicoloPS.executeQuery();   //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        try {
            PreparedStatement addRecensioneFascicoloPS = connection.prepareStatement(
                    "SELECT * FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE rivista.titolo = '"+titolo+"' AND fascicolo.numero = '"+numero+"' AND username = '"+user+"'" //prepara la query che cerca le recensioni fatte da 'user' al fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = addRecensioneFascicoloPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con il codice del fascicolo numero 'numero' della rivista 'titolo'
                codF = rs.getInt("codF");    //aggiorna 'codF'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è una recensione del fascicolo numero 'numero' della rivista 'titolo' fatta da 'user'
            try {
                String query = "UPDATE recensione_f SET valutazione = ?, testo = ? WHERE codf = ? AND username = ?";    //inizializza la query che aggiorna la recensione del fascicolo numero 'numero' della rivista 'titolo' fatta da 'user'
                PreparedStatement addRecensioneFascicoloPS = connection.prepareStatement(query);    //prepara la query

                addRecensioneFascicoloPS.setInt(1, valutazione);    //aggiunge 'valutazione' nella query

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneFascicoloPS.setNull(2, Types.NULL);    //aggiunge 'text' nella query
                }else{
                    addRecensioneFascicoloPS.setString(2, text);   //aggiunge 'text' nella query
                }

                addRecensioneFascicoloPS.setInt(3, codF);    //aggiunge 'codF' nella query
                addRecensioneFascicoloPS.setString(4, user);    //aggiunge 'user' nella query

                addRecensioneFascicoloPS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_f(username, codF, valutazione, testo) VALUES (?, ?, ?, ?)";  //inizializza la query che inserisce la nuova recensione
                PreparedStatement addRecensioneFascicoloPS = connection.prepareStatement(query);    //prepara la query

                addRecensioneFascicoloPS.setInt(3, valutazione);    //aggiunge 'valutazione' nella query

                if(text.isBlank()){ //controlla se non è stato inserito il testo
                    addRecensioneFascicoloPS.setNull(4, Types.NULL);    //aggiunge 'text' nella query
                }else{
                    addRecensioneFascicoloPS.setString(4, text);    //aggiunge 'text' nella query
                }

                addRecensioneFascicoloPS.setInt(2, codF);     //aggiunge 'codF' nella query
                addRecensioneFascicoloPS.setString(1, user);     //aggiunge 'user' nella query

                addRecensioneFascicoloPS.executeUpdate();   //esegue la query
                connection.close(); //chiude la connessione al DB
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }//fine addRecensioneFascicoloDB

    public ResultSet allRecWithCommentFascicoloDB(int numero, String titolo){   //ritorna tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'
        ResultSet rs = null;    //contiene tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_f NATURAL JOIN fascicolo NATURAL JOIN rivista WHERE fascicolo.numero = '"+numero+"' AND rivista.titolo = '"+titolo+"' AND recensione_f.testo IS NOT NULL AND recensione_f.valutazione IS NOT NULL"   //prepara la query che cerca tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'
            );

            rs = allRecWithCommentPS.executeQuery();    //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine allRecWithCommentFascicoloDB

    @Override
    public ArrayList<String> getLibriISBNPreferitiDB(String user){  //ritorna gli ISBN dei libri preferiti dell'utente 'user'
        ArrayList<String> isbn = new ArrayList<>(); //ISBN dei libri preferiti dell'utente 'user'
        ResultSet rs = null;    //contiene gli ISBN dei libri preferiti dell'utente 'user'

        try {
            PreparedStatement getLibriISBNPreferitiPS = connection.prepareStatement(
                    "SELECT isbn FROM recensione_l WHERE username = '"+user+"' AND preferito = true"    //prepara la query che cerce gli ISBN dei libri preferiti dell'utente 'user'
            );

            rs = getLibriISBNPreferitiPS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con gli ISBN dei libri preferiti dell'utente 'user'
                isbn.add(rs.getString("isbn")); //aggiunge l'ISBN in 'isbn'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB

        return isbn;
    }//fine  getLibriISBNPreferitiDB

    @Override
    public ArrayList<String> getSerieISBNPreferitiDB(String user){  //ritorna gli ISBN delle serie preferite dell'utente 'user'
        ArrayList<String> isbn = new ArrayList<>(); //ISBN delle serie preferite dell'utente 'user'
        ResultSet rs = null;    //contiene gli ISBN delle serie preferite dell'utente 'user'

        try {
            PreparedStatement getLibriISBNPreferitiPS = connection.prepareStatement(
                    "SELECT isbn FROM recensione_s WHERE username = '"+user+"' AND preferito = true"    //prepara la query che cerce gli ISBN dei libri preferiti dell'utente 'user'
            );

            rs = getLibriISBNPreferitiPS.executeQuery();    //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con gli ISBN dei libri preferiti dell'utente 'user'
                isbn.add(rs.getString("isbn")); //aggiunge l'ISBN in 'isbn'
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB

        return isbn;
    }//fine getSerieISBNPreferitiDB

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