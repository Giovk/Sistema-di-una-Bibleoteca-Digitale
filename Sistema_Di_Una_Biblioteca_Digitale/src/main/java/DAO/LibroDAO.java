package DAO;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * The interface Libro dao.
 */
public interface LibroDAO {
    /**
     * Gets libri db.
     *
     * @return the libri db
     */
    public ResultSet getLibriDB();  //cerca i dati di tutti i libri nel DB

    /**
     * Gets libri db.
     *
     * @param collana the collana
     * @return the libri db
     */
    public ResultSet getLibriDB(String collana);  //cerca i dati di tutti i libri della collana 'collana' nel DB

    /**
     * Gets libri serie db.
     *
     * @param s the s
     * @return the libri serie db
     */
    public ResultSet getLibriSerieDB(String s); //cerca i dati di tutti i libri della serie con ISBN 's' nel DB

    /**
     * Gets info libri preferiti db.
     *
     * @param isbn the isbn
     * @return the info libri preferiti db
     */
    public ResultSet getInfoLibriPreferitiDB(String isbn);  //cerca i dati del libro con ISBN 'isbn' e delle librerie che lo possiedono

    /**
     * Crea libro db boolean.
     *
     * @param isbn    the isbn
     * @param titolo  the titolo
     * @param genere  the genere
     * @param lingua  the lingua
     * @param editore the editore
     * @param dp      the dp
     * @return the boolean
     */
    public boolean creaLibroDB(String isbn, String titolo, String genere, String lingua, String editore, String dp);    //se non esiste già, inserisce un nuovo libro nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Gets data libro db.
     *
     * @param isbn the isbn
     * @return the data libro db
     */
    public Date getDataLibroDB(String isbn);    //cerca la data di pubblicazione del libro con ISBN 'isbn'

    /**
     * Elimina libro db.
     *
     * @param isbn the isbn
     */
    public void eliminaLibroDB(String isbn);    //elimina dal DB il libro con ISBN 'isbn'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}