package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The interface Recensione dao.
 */
public interface RecensioneDAO {
    /**
     * Valutazione media libro db float.
     *
     * @param isbn the isbn
     * @return the float
     */
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'

    /**
     * Like libro db boolean.
     *
     * @param isbn the isbn
     * @param user the user
     * @return the boolean
     */
    public boolean likeLibroDB(String isbn, String user);   //controlla se l'utente 'user' ha il libro con ISBN 'isbn' tra i preferiti

    /**
     * Change like libro db boolean.
     *
     * @param like the like
     * @param isbn the isbn
     * @param user the user
     * @return the boolean
     */
    public boolean changeLikeLibroDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' il libro con ISBN 'isbn' e ritorna l'opposto di 'like'

    /**
     * Add recensione libro db.
     *
     * @param valutazione the valutazione
     * @param text        the text
     * @param isbn        the isbn
     * @param user        the user
     */
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro con ISBN 'isbn'

    /**
     * All rec with comment libro db result set.
     *
     * @param isbn the isbn
     * @return the result set
     */
    public ResultSet allRecWithCommentLibroDB(String isbn);  //ritorna tutte le recensioni con un testo fatte al libro con ISBN 'isbn'

    /**
     * Valutazione media serie db float.
     *
     * @param isbn the isbn
     * @return the float
     */
    public float valutazioneMediaSerieDB(String isbn);  //ritorna la media delle valutazioni della serie con ISBN 'isbn'

    /**
     * Like serie db boolean.
     *
     * @param isbn the isbn
     * @param user the user
     * @return the boolean
     */
    public boolean likeSerieDB(String isbn, String user);   //controlla se l'utente 'user' ha la serie con ISBN 'isbn' tra i preferiti

    /**
     * Change like serie db boolean.
     *
     * @param like the like
     * @param isbn the isbn
     * @param user the user
     * @return the boolean
     */
    public boolean changeLikeSerieDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' la serie con ISBN 'isbn' e ritorna l'opposto di 'like'

    /**
     * Add recensione serie db.
     *
     * @param valutazione the valutazione
     * @param text        the text
     * @param isbn        the isbn
     * @param user        the user
     */
    public void addRecensioneSerieDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' la serie con ISBN 'isbn'

    /**
     * All rec with comment serie db result set.
     *
     * @param isbn the isbn
     * @return the result set
     */
    public ResultSet allRecWithCommentSerieDB(String isbn);  //ritorna tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'

    /**
     * Valutazione media fascicolo db float.
     *
     * @param numero the numero
     * @param titolo the titolo
     * @return the float
     */
    public float valutazioneMediaFascicoloDB(int numero, String titolo);    //ritorna la media delle valutazioni del fascicolo numero 'numero' della rivista 'titolo'

    /**
     * Like fascicolo db boolean.
     *
     * @param numero the numero
     * @param titolo the titolo
     * @param user   the user
     * @return the boolean
     */
    public boolean likeFascicoloDB(int numero, String titolo, String user); //controlla se l'utente 'user' ha il fascicolo numero 'numero' della rivista 'titolo' tra i preferiti

    /**
     * Change like fascicolo db boolean.
     *
     * @param like   the like
     * @param numero the numero
     * @param titolo the titolo
     * @param user   the user
     * @return the boolean
     */
    public boolean changeLikeFascicoloDB(boolean like, int numero, String titolo, String user); //toglie/mette nei preferiti dell'utente 'user' il fascicolo numero 'numero' della rivista 'titolo' e ritorna l'opposto di 'like'

    /**
     * Add recensione fascicolo db.
     *
     * @param valutazione the valutazione
     * @param text        the text
     * @param numero      the numero
     * @param titolo      the titolo
     * @param user        the user
     */
    public void addRecensioneFascicoloDB(int valutazione, String text, int numero, String titolo, String user);  //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al fascicolo numero 'numero' della rivista 'titolo'

    /**
     * All rec with comment fascicolo db result set.
     *
     * @param numero the numero
     * @param titolo the titolo
     * @return the result set
     */
    public ResultSet allRecWithCommentFascicoloDB(int numero, String titolo);  //ritorna tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'

    /**
     * Gets libri isbn preferiti db.
     *
     * @param user the user
     * @return the libri isbn preferiti db
     */
    public ArrayList<String> getLibriISBNPreferitiDB(String user);  //ritorna gli ISBN dei libri preferiti dell'utente 'user'

    /**
     * Gets serie isbn preferiti db.
     *
     * @param user the user
     * @return the serie isbn preferiti db
     */
    public ArrayList<String> getSerieISBNPreferitiDB(String user);  //ritorna gli ISBN delle serie preferite dell'utente 'user'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}