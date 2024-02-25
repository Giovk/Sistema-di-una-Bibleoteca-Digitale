package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The interface Serie dao.
 */
public interface SerieDAO {
    /**
     * Gets serie db.
     *
     * @return the serie db
     */
    public ResultSet getSerieDB();  //cerca i dati di tutte le serie nel DB

    /**
     * Gets serie genere db.
     *
     * @return the serie genere db
     */
    public ArrayList<String> getSerieGenereDB();    //ritorna tutti i generi dei libri che sono inseriti in qualche serie

    /**
     * Gets serie autori db.
     *
     * @return the serie autori db
     */
    public ArrayList<String> getSerieAutoriDB();    //ritorna tutti gli autori dei libri che sono inseriti in qualche serie

    /**
     * Gets lista serie genere db.
     *
     * @param genere the genere
     * @return the lista serie genere db
     */
    public ResultSet getListaSerieGenereDB(String genere);  //cerca i dati delle serie con libri del genere 'genere'

    /**
     * Gets lista serie autore db.
     *
     * @param autore the autore
     * @return the lista serie autore db
     */
    public ResultSet getListaSerieAutoreDB(String autore);  //cerca i dati delle serie con libri dell'autore 'autore'

    /**
     * Gets info serie preferiti db.
     *
     * @param isbn the isbn
     * @return the info serie preferiti db
     */
    public ResultSet getInfoSeriePreferitiDB(String isbn);  //cerca i dati della serie con ISBN 'isbn' e delle librerie che lo possiedono

    /**
     * Crea serie db boolean.
     *
     * @param isbnList the isbn list
     * @param isbn     the isbn
     * @param titolo   the titolo
     * @param dp       the dp
     * @return the boolean
     */
    public boolean creaSerieDB(ArrayList<String> isbnList, String isbn, String titolo, String dp);  //se non esiste già, inserisce una nuova serie nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}