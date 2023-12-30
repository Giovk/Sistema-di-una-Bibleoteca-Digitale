package DAO;

import java.sql.ResultSet;

public interface AutoreDAO {
    public ResultSet getAutoriLibroDB(String isbn);  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'
    public ResultSet getAutoriArticoloDB(String doi);   //ritorna i dati di tutti gli autori nel DB dell'articolo con DOI 'doi'
    public void aggiungiAutoreLibroDB(String nome, String cognome, String nazionalita, String dn, String isbn); //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore al libro con ISBN 'isbn'
    public void aggiungiAutoreArticoloDB(String nome, String cognome, String nazionalita, String dn, String doi);   //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore all'articolo con DOI 'doi'
}