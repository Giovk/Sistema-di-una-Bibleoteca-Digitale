package ImplementazionePostgresDAO;

import DAO.RecensioneDAO;
import Database.ConnessioneDatabase;

import java.sql.*;

public class RecensioneImplementazionePostgresDAO implements RecensioneDAO {
    private Connection connection;

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
        ResultSet rs = null;    //media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaLibroPS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_l WHERE isbn = '"+isbn+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = valutazioneMediaLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
    }

    @Override
    public boolean likeLibroDB(String isbn, String user) { //controlla se l'utente 'user' ha il libro 'isbn' tra i preferiti
        ResultSet rs = null;    //valore trovato
        boolean like = false;   //risultato

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che controlla se l'utente ha il libro nei preferiti
            );
            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                like = rs.getBoolean(1);    //pone il risultato in 'like'
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }

    @Override
    public boolean changeLikeLibroDB(boolean like, String isbn, String user) { //toglie/mette nei preferiti dell'utente 'user' il libro 'isbn' e ritone l'opposto di 'like'
        ResultSet rs = null;
        int item = 1;   //numero di tuple in "recensione_l" con 'user' e 'isbn'

        if (like == true) like = false; //se 'like' è true lo pone a false
        else like = true;   //altrimenti lo pone a true

        try {
            PreparedStatement changeLikeLibroPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che conta il numero di tuple con 'user' e 'isbn'
            );
            rs = changeLikeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di tuple trovate dalla query
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è già una tupla con 'user' e 'isbn' in "recensione_l"
<<<<<<< HEAD
            if(like==false)
            {
=======
            if(like == false) {
                try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "DELETE from recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"' AND valutazione IS NULL AND testo IS NULL"
                    );
                    changeLikePS.executeUpdate(); //esegue la query
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
            try {
>>>>>>> 566c1a7a56573b011a553533ddf41afab4627cd9
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "DELETE FROM recensione_l WHERE isbn = '" + isbn + "' AND username = '" + user + "' AND valutazione IS NULL AND testo IS NULL" //prepara la query che aggiorna la tupla con 'isbn' e 'user'
                );
            }
            try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "UPDATE recensione_l SET preferito = '" + like + "' WHERE isbn = '" + isbn + "' AND username = '" + user + "'" //prepara la query che aggiorna la tupla con 'isbn' e 'user'
                    );
                    changeLikePS.executeUpdate(); //esegue la query
                    connection.close();
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
        } else {
            try {
                PreparedStatement changeLikePS = connection.prepareStatement(
                        "INSERT INTO recensione_l(username, isbn, preferito) " +
                        "VALUES ('"+user+"', '"+isbn+"', '"+like+"')"   //prepara la query che aggiuge una nuova tupla in "recensione_l" con 'user', 'isbn' e 'like'
                );
                changeLikePS.executeUpdate(); //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return like;
    }

    @Override
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user){   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro 'isbn'
        ResultSet rs = null;
        int item = 1;   //numero di tuple in "recensione_l" con 'user' e 'isbn'

        try {
            PreparedStatement likeLibroPS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_l WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che conta il numero di tuple con 'user' e 'isbn'
            );
            rs = likeLibroPS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è già una tupla con 'user' e 'isbn' in "recensione_l"
            try {
                String query = "UPDATE recensione_l SET valutazione = ?, testo = ? WHERE isbn = ? AND username = ?"; //prepara la query di aggiornamento
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);

                addRecensioneLibroPS.setInt(1, valutazione);    //inserisce la valutazione nella query

                if(text.isBlank()) addRecensioneLibroPS.setNull(2, Types.NULL); //se il testo è vuoto inserisce NULL nella query
                else addRecensioneLibroPS.setString(2, text);   //altrimenti inserise 'text' nella query

                addRecensioneLibroPS.setString(3, isbn);    //inserisce l'isbn nella query
                addRecensioneLibroPS.setString(4, user);    //inserisce l'username nella query

                addRecensioneLibroPS.executeUpdate();   //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_l(username, isbn, valutazione, testo) VALUES (?, ?, ?, ?)"; //prepara la query di inserimento
                PreparedStatement addRecensioneLibroPS = connection.prepareStatement(query);

                addRecensioneLibroPS.setInt(3, valutazione);    //prepara la query di aggiornamento

                if(text.isBlank()) addRecensioneLibroPS.setNull(4, Types.NULL); //se il testo è vuoto inserisce NULL nella query
                else addRecensioneLibroPS.setString(4, text);    //altrimenti inserise 'text' nella query

                addRecensioneLibroPS.setString(2, isbn);     //inserisce l'isbn nella query
                addRecensioneLibroPS.setString(1, user);     //inserisce l'username nella query

                addRecensioneLibroPS.executeUpdate();   //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }

    public ResultSet allRecWithCommentLibroDB(String isbn){  //ritorna tutte le recensioni con un testo fatte al libro 'isbn'
        ResultSet rs = null;    //contiene le recensioni

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_l WHERE isbn = '"+isbn+"' AND testo IS NOT NULL AND VALUTAZIONE IS NOT NULL"   //prepara la query che cerca tutte le recensioni del libro
            );

            rs = allRecWithCommentPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public float valutazioneMediaSerieDB(String isbn) { //ritorna la media delle valutazioni del libro con isbn 'isbn'
        ResultSet rs = null;    //media trovata
        float vm = 0;   //valore medio delle valutazioni

        try {
            PreparedStatement valutazioneMediaSeriePS = connection.prepareStatement(
                    "SELECT AVG(valutazione) FROM recensione_s WHERE isbn = '"+isbn+"'" //prepara la query che calcola il valore medio delle valutazioni del libro
            );
            rs = valutazioneMediaSeriePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente la media
                vm = rs.getFloat(1);
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return vm;
    }

    @Override
    public boolean likeSerieDB(String isbn, String user) { //controlla se l'utente 'user' ha il libro 'isbn' tra i preferiti
        ResultSet rs = null;    //valore trovato
        boolean like = false;   //risultato

        try {
            PreparedStatement likeSeriePS = connection.prepareStatement(
                    "SELECT preferito FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che controlla se l'utente ha il libro nei preferiti
            );
            rs = likeSeriePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                like = rs.getBoolean(1);    //pone il risultato in 'like'
            }

            rs.close();
            connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return like;
    }

    @Override
    public boolean changeLikeSerieDB(boolean like, String isbn, String user) { //toglie/mette nei preferiti dell'utente 'user' il libro 'isbn' e ritone l'opposto di 'like'
        ResultSet rs = null;
        int item = 1;   //numero di tuple in "recensione_l" con 'user' e 'isbn'

        if (like == true) like = false; //se 'like' è true lo pone a false
        else like = true;   //altrimenti lo pone a true

        try {
            PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che conta il numero di tuple con 'user' e 'isbn'
            );
            rs = changeLikeSeriePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' contenente il numero di tuple trovate dalla query
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è già una tupla con 'user' e 'isbn' in "recensione_l"
            if(like == false) {
                try {
                    PreparedStatement changeLikePS = connection.prepareStatement(
                            "DELETE from recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"' AND valutazione IS NULL AND testo IS NULL"
                    );
                    changeLikePS.executeUpdate(); //esegue la query
                } catch (SQLException var2) {
                    var2.printStackTrace();
                }
            }
            try {
                PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                        "UPDATE recensione_s SET preferito = '" + like + "' WHERE isbn = '" + isbn + "' AND username = '" + user + "'" //prepara la query che aggiorna la tupla con 'isbn' e 'user'
                );
                changeLikeSeriePS.executeUpdate(); //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                PreparedStatement changeLikeSeriePS = connection.prepareStatement(
                        "INSERT INTO recensione_s(username, isbn, preferito) " +
                                "VALUES ('"+user+"', '"+isbn+"', '"+like+"')"   //prepara la query che aggiuge una nuova tupla in "recensione_l" con 'user', 'isbn' e 'like'
                );
                changeLikeSeriePS.executeUpdate(); //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return like;
    }

    @Override
    public void addRecensioneSerieDB(int valutazione, String text, String isbn, String user){   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro 'isbn'
        ResultSet rs = null;
        int item = 1;   //numero di tuple in "recensione_l" con 'user' e 'isbn'

        try {
            PreparedStatement addRecensioneSeriePS = connection.prepareStatement(
                    "SELECT COUNT (*) FROM recensione_s WHERE isbn = '"+isbn+"' AND username = '"+user+"'" //prepara la query che conta il numero di tuple con 'user' e 'isbn'
            );
            rs = addRecensioneSeriePS.executeQuery(); //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs'
                item = rs.getInt(1);    //aggiorna 'item'
            }

            rs.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        if(item >= 1) { //controlla se c'è già una tupla con 'user' e 'isbn' in "recensione_l"
            try {
                String query = "UPDATE recensione_s SET valutazione = ?, testo = ? WHERE isbn = ? AND username = ?"; //prepara la query di aggiornamento
                PreparedStatement addRecensioneSeriePS = connection.prepareStatement(query);

                addRecensioneSeriePS.setInt(1, valutazione);    //inserisce la valutazione nella query

                if(text.isBlank()) addRecensioneSeriePS.setNull(2, Types.NULL); //se il testo è vuoto inserisce NULL nella query
                else addRecensioneSeriePS.setString(2, text);   //altrimenti inserise 'text' nella query

                addRecensioneSeriePS.setString(3, isbn);    //inserisce l'isbn nella query
                addRecensioneSeriePS.setString(4, user);    //inserisce l'username nella query

                addRecensioneSeriePS.executeUpdate();   //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        } else {
            try {
                String query = "INSERT INTO recensione_s(username, isbn, valutazione, testo) VALUES (?, ?, ?, ?)"; //prepara la query di inserimento
                PreparedStatement addRecensioneSeriePS = connection.prepareStatement(query);

                addRecensioneSeriePS.setInt(3, valutazione);    //prepara la query di aggiornamento

                if(text.isBlank()) addRecensioneSeriePS.setNull(4, Types.NULL); //se il testo è vuoto inserisce NULL nella query
                else addRecensioneSeriePS.setString(4, text);    //altrimenti inserise 'text' nella query

                addRecensioneSeriePS.setString(2, isbn);     //inserisce l'isbn nella query
                addRecensioneSeriePS.setString(1, user);     //inserisce l'username nella query

                addRecensioneSeriePS.executeUpdate();   //esegue la query
                connection.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }
    }

    @Override
    public ResultSet allRecWithCommentSerieDB(String isbn){  //ritorna tutte le recensioni con un testo fatte al libro 'isbn'
        ResultSet rs = null;    //contiene le recensioni

        try {
            PreparedStatement allRecWithCommentPS = connection.prepareStatement(
                    "SELECT * FROM recensione_s WHERE isbn = '"+isbn+"' AND testo IS NOT NULL AND VALUTAZIONE IS NOT NULL"   //prepara la query che cerca tutte le recensioni del libro
            );

            rs = allRecWithCommentPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
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
