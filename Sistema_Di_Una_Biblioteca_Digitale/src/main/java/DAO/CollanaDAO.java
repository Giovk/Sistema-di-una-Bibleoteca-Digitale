package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CollanaDAO {
    public ArrayList<String> getCollanaNomeDB();    //ritorna i nomi di tutte le collane nel DB
    public ResultSet getCollaneDB();    //cerca tutte le collane nel DB
    public void removeLibroFromCollanaDB(String isbn, String collana);  //rimuove il libro con ISBN 'isbn' dalla collana 'collana'
    public void addLibroInCollanaDB(String isbn, String collana);   //aggiunge il libro con ISBN 'isbn' nella collana 'collana'
    public boolean creaCollanaDB(String nome, String caratteristica, String issn);  //se non esiste già, inserisce una nuova collana nel DB e ritorna "true", altrimenti ritorna "false"
    public void chiudiConnessione();    //chiude la connessione al DB
}