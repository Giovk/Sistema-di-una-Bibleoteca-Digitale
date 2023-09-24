package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AutoreDAO {
    public ResultSet getAutoriLibroDB(String isbn);  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'
    public ResultSet getAutoriArticoloDB(String doi);
    public void aggiungiAutoreDB(String nome, String cognome, String nazionalita, String dn, String isbn);
    public void chiudiConnessione();    //chiude la connessione al DB
}
