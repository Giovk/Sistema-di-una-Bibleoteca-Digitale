package DAO;

import java.sql.ResultSet;

/**
 * The interface Autore dao.
 */
public interface AutoreDAO {
    /**
     * Gets autori libro db.
     *
     * @param isbn the isbn
     * @return the autori libro db
     */
    public ResultSet getAutoriLibroDB(String isbn);  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'

    /**
     * Gets autori articolo db.
     *
     * @param doi the doi
     * @return the autori articolo db
     */
    public ResultSet getAutoriArticoloDB(String doi);   //ritorna i dati di tutti gli autori nel DB dell'articolo con DOI 'doi'

    /**
     * Aggiungi autore libro db.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param nazionalita the nazionalita
     * @param dn          the dn
     * @param isbn        the isbn
     */
    public void aggiungiAutoreLibroDB(String nome, String cognome, String nazionalita, String dn, String isbn); //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore al libro con ISBN 'isbn'

    /**
     * Aggiungi autore articolo db.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param nazionalita the nazionalita
     * @param dn          the dn
     * @param doi         the doi
     */
    public void aggiungiAutoreArticoloDB(String nome, String cognome, String nazionalita, String dn, String doi);   //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore all'articolo con DOI 'doi'
}