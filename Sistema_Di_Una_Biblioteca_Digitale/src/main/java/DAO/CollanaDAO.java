package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CollanaDAO {
    public ArrayList<String> getCollanaNomeDB();    //ritorna i nomi di tutte le collane nel DB
    public ResultSet getCollaneDB();
    public void removeLibroFromCollanaDB(String isbn, String collana);
    public void addLibroInCollanaDB(String isbn, String collana);
    public boolean creaCollanaDB(String nome, String caratteristica, String issn);
    public void chiudiConnessione();    //chiude la connessione al DB
}
