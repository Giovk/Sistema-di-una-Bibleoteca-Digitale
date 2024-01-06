package ImplementazionePostgresDAO;

import DAO.PossessoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PossessoImplementazionePostgresDAO implements PossessoDAO {
    private Connection connection;

    public PossessoImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user){    //cerca i possessi dei libri della libreria selezionata gestista dall'utente 'user'
        ResultSet rs = null;    //contiene tutti i possessi dei libri della libreria selezionata gestista dall'utente 'user'

        try {
            String query = "SELECT * FROM Libro AS l NATURAL JOIN possesso_l AS pl NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";  //inizializza la query che cerca i possessi dei libri della libreria selezionata gestista dall'utente 'user'

            if (sitoweb == null){   //controlla se non è stato inserito il sito web
                query = query + "AND lib.sitoweb IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND lib.sitoweb = '"+sitoweb+"' "; //aggiorna 'query'
            }

            if (indirizzo == null){ //controlla se non è stato inserito l'indirizzo
                query = query + "AND lib.indirizzo IS NULL ";   //aggiorna 'query'
            }else{
                query = query + "AND lib.indirizzo = '"+indirizzo+"' "; //aggiorna 'query'
            }

            query = query + "AND lib.gestore = '"+user+"'";     //aggiunge 'user' nella query

            PreparedStatement getPossessoLLibreriaPS = connection.prepareStatement(query);  //prepara la query

            rs = getPossessoLLibreriaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getPossessoLLibreriaDB

    @Override
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user){    //cerca i possessi delle serie della libreria selezionata gestista dall'utente 'user'
        ResultSet rs = null;    //contiene tutti i possessi delle serie della libreria selezionata gestista dall'utente 'user'

        try {
            String query = "SELECT * FROM Serie AS s NATURAL JOIN possesso_s AS ps NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";  //inizializza la query che cerca i possessi della serie della libreria selezionata gestista dall'utente 'user'

            if (sitoweb == null){   //controlla se non è stato inserito il sito web
                query = query + "AND lib.sitoweb IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND lib.sitoweb = '"+sitoweb+"' "; //aggiorna 'query'
            }

            if (indirizzo == null){ //controlla se non è stato inserito l'indirizzo
                query = query + "AND lib.indirizzo IS NULL ";   //aggiorna 'query'
            }else{
                query = query + "AND lib.indirizzo = '"+indirizzo+"' "; //aggiorna 'query'
            }

            query = query + "AND lib.gestore = '"+user+"'"; //aggiunge 'user' nella query

            PreparedStatement getPossessoSLibreriaPS = connection.prepareStatement(query);  //prepara la query

            rs = getPossessoSLibreriaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getPossessoSLibreriaDB

    @Override
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user){    //cerca i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'
        ResultSet rs = null;    //contiene tutti i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'

        try {
            String query = "SELECT * FROM Rivista AS riv NATURAL JOIN Fascicolo AS f NATURAL JOIN possesso_f AS pf NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";  //inizializza la query che cerca i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'

            if (sitoweb == null){   //controlla se non è stato inserito il sito web
                query = query + "AND lib.sitoweb IS NULL "; //aggiorna 'query'
            }else{
                query = query + "AND lib.sitoweb = '"+sitoweb+"' "; //aggiorna 'query'
            }

            if (indirizzo == null){ //controlla se non è stato inserito l'indirizzo
                query = query + "AND lib.indirizzo IS NULL ";   //aggiorna 'query'
            }else{
                query = query + "AND lib.indirizzo = '"+indirizzo+"' "; //aggiorna 'query'
            }

            query = query + "AND lib.gestore = '"+user+"'"; //aggiunge 'user' nella query

            PreparedStatement getPossessoFLibreriaPS = connection.prepareStatement(query);  //prepara la query

            rs = getPossessoFLibreriaPS.executeQuery(); //esegue la query
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }//fine getPossessoFLibreriaDB

    @Override
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value){    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del libro con ISBN 'isbn'
        int codl = 0;   //codice della libreria selezionata
        ResultSet rs = null;    //contiene il codice della libreria selezionata

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"   //prepara la query che cerca il codice della libreria selezionata
            );

            rs = modQuantitaLibroPS.executeQuery(); //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il codice della libreria selezionata
                    codl = rs.getInt("codl");   //aggiorna 'codl'
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "UPDATE possesso_l SET quantita = '"+value+"' WHERE codl = '"+codl+"' AND isbn = '"+isbn+"' AND fruizione = '"+fruizione+"'"    //prepara la query che modifica la la quantità disponibile nella libreria con codice 'codl' in modalità di fruizione 'fruizione' del libro con ISBN 'isbn'
            );

            modQuantitaLibroPS.executeUpdate(); //esegue la query
        } catch (SQLException var){
            var.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine modQuantitaLibroDB

    @Override
    public void modQuantitaFascicoloDB(String issn, int numero ,String nt, String fruizione, int value){    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del fascicolo numero 'numero' della rivista con ISSN 'issn'
        int codl = 0;   //codice della libreria selezionata
        int codf = 0;   //codice del fascicolo numero 'numero' della rivista con ISSN 'issn'
        ResultSet rs = null;    //contiene il codice della libreria selezionata

        try {
            PreparedStatement modQuantitaFascicoloPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"   //prepara la query che cerca il codice della libreria selezionata
            );

            rs = modQuantitaFascicoloPS.executeQuery(); //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il codice della libreria selezionata
                    codl = rs.getInt("codl");   //aggiorna 'codl'
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaFascicoloPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE numero = '"+numero+"' AND issn = '"+issn+"'"  //prepara la query che cerca il codice del fascicolo numero 'numero' della rivista con ISSN 'issn'
            );

            rs = modQuantitaFascicoloPS.executeQuery(); //esegue la query

            try {
                while(rs.next()){   //scorre il ResultSet 'rs' con il codice del fascicolo numero 'numero' della rivista con ISSN 'issn'
                    codf = rs.getInt("codf");   //aggiorna 'codl'
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "UPDATE possesso_f SET quantita = '"+value+"' WHERE codl = '"+codl+"' AND codf = '"+codf+"' AND fruizione = '"+fruizione+"'"    //prepara la query che modifica la la quantità disponibile nella libreria con codice 'codl' in modalità di fruizione 'fruizione' del fascicolo numero 'numero' della rivista con ISSN 'issn'
            );

            modQuantitaLibroPS.executeUpdate(); //esegue la query
        } catch (SQLException var){
            var.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine modQuantitaFascicoloDB

    @Override
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione){
        ResultSet rs = null;
        int codl = 0;

        try {
            PreparedStatement insertPossessoLPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = insertPossessoLPS.executeQuery();

            try {
                while (rs.next()){
                    try {
                        codl = rs.getInt("codl");
                        insertPossessoLPS = connection.prepareStatement(
                                "SELECT COUNT(*) AS contatore FROM possesso_l WHERE isbn = '"+isbn+"' AND codl = '"+codl+"' AND fruizione = '"+fruizione+"'"
                        );

                        rs = insertPossessoLPS.executeQuery();
                        try {
                            while (rs.next()){
                                if (rs.getInt("contatore") == 0){
                                    if (!fruizione.equals("Cartaceo")){
                                        try {
                                            insertPossessoLPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_l(codl, isbn, fruizione) " +
                                                            "VALUES ('"+codl+"', '"+isbn+"', '"+fruizione+"')"
                                            );

                                            insertPossessoLPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            insertPossessoLPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_l(codl, isbn, fruizione, quantita) " +
                                                            "VALUES ('"+codl+"', '"+isbn+"', '"+fruizione+"', '"+quantita+"')"
                                            );

                                            insertPossessoLPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    rs.close();
                                    chiudiConnessione();
                                    return false;
                                }
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
    }

    @Override
    public boolean insertPossessoFDB(int numero, String issn, String nt, int quantita, String fruizione){
        ResultSet rs = null;
        int codl = 0;
        int codf = 0;

        try {
            PreparedStatement insertPossessoFPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = insertPossessoFPS.executeQuery();

            try {
                while (rs.next()){
                    try {
                        codl = rs.getInt("codl");

                        try{
                            insertPossessoFPS = connection.prepareStatement(
                                    "SELECT codf FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"
                            );

                            ResultSet rs2 = insertPossessoFPS.executeQuery();

                            try {
                                while(rs2.next()) {
                                    codf = rs2.getInt("codf");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            rs2.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        insertPossessoFPS = connection.prepareStatement(
                                "SELECT COUNT(*) AS contatore FROM possesso_f WHERE codf = '"+codf+"' AND codl = '"+codl+"' AND fruizione = '"+fruizione+"'"
                        );

                        rs = insertPossessoFPS.executeQuery();

                        try {
                            while (rs.next()){
                                if (rs.getInt("contatore") == 0){
                                    if (!fruizione.equals("Cartaceo")){
                                        try {
                                            insertPossessoFPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_f(codl, codf, fruizione) " +
                                                            "VALUES ('"+codl+"', '"+codf+"', '"+fruizione+"')"
                                            );

                                            insertPossessoFPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            insertPossessoFPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_f(codl, codf, fruizione, quantita) " +
                                                            "VALUES ('"+codl+"', '"+codf+"', '"+fruizione+"', '"+quantita+"')"
                                            );

                                            insertPossessoFPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    rs.close();
                                    chiudiConnessione();
                                    return false;
                                }
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
    }

    @Override
    public void eliminaPossessoLDB(String isbn, String nt, String fruizione){
        ResultSet rs = null;

        try {
            int codl = 0;
            PreparedStatement eliminaPossessoLPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = eliminaPossessoLPS.executeQuery();

            try {
                while (rs.next()) {
                    codl = rs.getInt("codl");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoLPS = connection.prepareStatement(
                    "DELETE FROM possesso_l WHERE codl = '"+codl+"' AND isbn = '"+isbn+"' AND fruizione = '"+fruizione+"'"
            );

            eliminaPossessoLPS.executeUpdate();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void eliminaPossessoFDB(String issn, int numero, String nt, String fruizione){
        ResultSet rs = null;

        try {
            int codl = 0;
            int codf = 0;
            PreparedStatement eliminaPossessoFPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = eliminaPossessoFPS.executeQuery();

            try {
                while (rs.next()) {
                    codl = rs.getInt("codl");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoFPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"
            );

            rs = eliminaPossessoFPS.executeQuery();

            try {
                while (rs.next()) {
                    codf = rs.getInt("codf");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoFPS = connection.prepareStatement(
                    "DELETE FROM possesso_f WHERE codl = '"+codl+"' AND codf = '"+codf+"' AND fruizione = '"+fruizione+"'"
            );

            eliminaPossessoFPS.executeUpdate();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
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
