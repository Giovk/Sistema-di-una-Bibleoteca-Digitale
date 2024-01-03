package ImplementazionePostgresDAO;

import DAO.CollanaDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollanaImplementazionePostgresDAO implements CollanaDAO {
    private Connection connection;

    public CollanaImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getCollanaNomeDB(){   //ritorna i nomi di tutte le collane nel DB
        ArrayList<String> nomi = new ArrayList<>(); //nomi delle collane
        ResultSet rs = null;    //ccontiene i nomi trovati

        try {
            PreparedStatement getCollanaPS = connection.prepareStatement(
                    "SELECT nome FROM collana"  //prepara la query che cerca tutti nomi delle collane
            );

            rs = getCollanaPS.executeQuery();   //esegue la query

            while(rs.next()){    //scorre il ResultSet 'rs' con i nomi delle collane
                nomi.add(rs.getString("nome")); //inserisce il nuovo nome in 'nomi'
            }

            rs.close(); //chiude 'rs'
            connection.close(); //chiude la connessione al DB
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return nomi;
    }//fine getCollanaNomeDB

    @Override
    public ResultSet getCollaneDB(){    //cerca tutte le collane nel DB
        ResultSet rs = null;    //contiene le collane

        try {
            PreparedStatement getCollanePS = connection.prepareStatement(
                    "SELECT * FROM collana" //prepara la query che cerca tutte le collane
            );

            rs = getCollanePS.executeQuery();   //esegue la query
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }//fine getCollaneDB

    @Override
    public void removeLibroFromCollanaDB(String isbn, String collana){  //rimuove il libro con ISBN 'isbn' dalla collana 'collana'
        ResultSet rs = null;    //contie il codice della collana 'collana'

        try {
            PreparedStatement removeLibroFromCollanaPS = connection.prepareStatement(
              "SELECT codc FROM collana WHERE nome = '"+collana+"'" //prepara la query che cerca il codice della collana 'collana'
            );

            rs = removeLibroFromCollanaPS.executeQuery();   //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il codice della collana 'collana'
                    removeLibroFromCollanaPS = connection.prepareStatement(
                            "DELETE FROM appartenenza WHERE codc = '"+rs.getInt("codc")+"' AND isbn = '"+isbn+"'"   //prepara la query che elimina il libro con ISBN 'isbn' dalla collana 'collana'
                    );

                    removeLibroFromCollanaPS.executeUpdate();   //esegue la query
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine removeLibroFromCollanaDB

    @Override
    public void addLibroInCollanaDB(String isbn, String collana){   //aggiunge il libro con ISBN 'isbn' nella collana 'collana'
        ResultSet rs = null;    //contie il codice della collana 'collana'

        try {
            PreparedStatement addLibroInCollanaPS = connection.prepareStatement(
                    "SELECT codc FROM collana WHERE nome = '"+collana+"'"   //prepara la query che cerca il codice della collana 'collana'
            );

            rs = addLibroInCollanaPS.executeQuery();    //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il codice della collana 'collana'
                    addLibroInCollanaPS = connection.prepareStatement(
                            "INSERT INTO appartenenza(codc, isbn) VALUES ('"+rs.getInt("codc")+"', '"+isbn+"')" //prepara la query che inserisce il libro con ISBN 'isbn' nella collana 'collana'
                    );

                    addLibroInCollanaPS.executeUpdate();    //esegue la query
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();    //chiude la connessione al DB
    }//fine addLibroInCollanaDB

    public boolean creaCollanaDB(String nome, String caratteristica, String issn){  //se non esiste già, inserisce una nuova collana nel DB e ritorna "true", altrimenti ritorna "false"
        ResultSet rs = null;    //contiene il numero di collane chiamate 'nome' e con ISSN 'issn'

        try {
            PreparedStatement creaCollanaPS = connection.prepareStatement(
                    "SELECT COUNT(*) AS contatore FROM collana WHERE nome = '"+nome+"'" //prepara la query che conta le collane chiamate 'nome'
            );

            rs = creaCollanaPS.executeQuery();  //esegue la query

            try {
                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di collane chiamate 'nome'
                    if (rs.getInt("contatore") == 0){   //controlla se non ci sono collane chiamate 'nome'
                        if(!issn.isBlank()){    //controlla se è stato inserito l'ISSN
                            creaCollanaPS = connection.prepareStatement(
                                    "SELECT COUNT(*) AS contatore FROM collana WHERE issn = '"+issn+"'" //prepara la query che conta le collane con ISSN 'issn'
                            );

                            rs = creaCollanaPS.executeQuery();  //esegue la query

                            try {
                                while (rs.next()){  //scorre il ResultSet 'rs' con il numero di collane con ISSN 'issn'
                                    if (rs.getInt("contatore") == 0){   //controlla se non ci sono collane con ISSN 'issn'
                                        creaCollanaPS = connection.prepareStatement(
                                                "INSERT INTO collana(nome, caratteristica, issn) VALUES('"+nome+"', '"+caratteristica+"', '"+issn+"')"  //prepara la query che inserisce la nuova collana
                                        );

                                        creaCollanaPS.executeUpdate();  //esegue la query
                                    } else{
                                        rs.close(); //chiude 'rs'
                                        chiudiConnessione();    //chiude la connessione al DB
                                        return false;
                                    }
                                }
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        } else {
                            creaCollanaPS = connection.prepareStatement(
                                    "INSERT INTO collana(nome, caratteristica) VALUES('"+nome+"', '"+caratteristica+"')"    //prepara la query che inserisce la nuova collana
                            );

                            creaCollanaPS.executeUpdate();  //esegue la query
                        }
                    } else {
                        rs.close(); //chiude 'rs'
                        chiudiConnessione();    //chiude la connessione al DB
                        return false;
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            rs.close(); //chiude 'rs'
        } catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }//fine creaCollanaDB

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