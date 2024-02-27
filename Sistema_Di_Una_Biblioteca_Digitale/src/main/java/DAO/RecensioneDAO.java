package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * L'interfaccia RecensioneDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative
 * alle recensioni degli elementi posseduti dalle librerie.
 */
public interface RecensioneDAO {
    /**
     * Calcola la media delle valutazioni fatte al libro con ISBN uguale a quello passato come parametro.
     *
     * @param isbn l'ISBN del libro
     * @return la media delle valutazioni fatte al libro
     */
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'

    /**
     * Se nel database l'utente con username uguale a quello passato come parametro ha il libro con ISBN uguale a quello passato come parametro,
     * allora ritorna "true", altrimenti ritorna "false".
     *
     * @param isbn l'ISBN del libro
     * @param user l'username dell'utente
     * @return "true" se l'utente ha nei preferiti il libro, altrimenti "false"
     */
    public boolean likeLibroDB(String isbn, String user);   //controlla se l'utente 'user' ha il libro con ISBN 'isbn' tra i preferiti

    /**
     * Se il parametro 'like' è "true", allora toglie il libro con ISBN uguale a quello passato come parametro dai preferiti dell'utente che ha
     * l'username uguale a quello passato come parametro e ritorna "false", altrimenti mette il libro nei preferiti dell'utente e ritorna "true".
     *
     * @param like la flag che indica se l'utente ha il libro nei preferiti
     * @param isbn l'ISBN del libro
     * @param user l'username dell'utente
     * @return "true" se mette il libro nei preferiti dell'utente, altrimenti "false"
     */
    public boolean changeLikeLibroDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' il libro con ISBN 'isbn' e ritorna l'opposto di 'like'

    /**
     * Aggiunge una nuova recensione, con la valutazione e il testo presi come parametri, fatta dall'utente che ha l'username uguale a quello
     * passato come parametro al libro con ISBN uguale a quello passato come parametro.
     *
     * @param valutazione la valutazione fatta dall'utente al libro
     * @param text        il testo della recensione fatta dall'utente al libro
     * @param isbn        l'ISBN del libro recensito
     * @param user        l'username dell'utente recensore
     */
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro con ISBN 'isbn'

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le recensioni con un testo fatte al libro con ISBN uguale a quello passato come
     * parametro.
     *
     * @param isbn l'ISBN del libro recensito
     * @return il ResultSet con le informazioni trovate nel database delle recensioni
     */
    public ResultSet allRecWithCommentLibroDB(String isbn);  //ritorna tutte le recensioni con un testo fatte al libro con ISBN 'isbn'

    /**
     * Calcola la media delle valutazioni fatte alla serie con ISBN uguale a quello passato come parametro.
     *
     * @param isbn l'ISBN della serie
     * @return la media delle valutazioni fatte alla serie
     */
    public float valutazioneMediaSerieDB(String isbn);  //ritorna la media delle valutazioni della serie con ISBN 'isbn'

    /**
     * Se nel database l'utente con username uguale a quello passato come parametro ha la serie con ISBN uguale a quello passato come parametro,
     * allora ritorna "true", altrimenti ritorna "false".
     *
     * @param isbn l'ISBN della serie
     * @param user l'username dell'utente
     * @return "true" se l'utente ha nei preferiti la serie, altrimenti "false"
     */
    public boolean likeSerieDB(String isbn, String user);   //controlla se l'utente 'user' ha la serie con ISBN 'isbn' tra i preferiti

    /**
     * Se il parametro 'like' è "true", allora toglie la serie con ISBN uguale a quello passato come parametro dai preferiti dell'utente che ha
     * l'username uguale a quello passato come parametro e ritorna "false", altrimenti mette la serie nei preferiti dell'utente e ritorna "true".
     *
     * @param like la flag che indica se l'utente ha la serie nei preferiti
     * @param isbn l'ISBN della serie
     * @param user l'username dell'utente
     * @return "true" se mette la serie nei preferiti dell'utente, altrimenti "false"
     */
    public boolean changeLikeSerieDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' la serie con ISBN 'isbn' e ritorna l'opposto di 'like'

    /**
     * Aggiunge una nuova recensione, con la valutazione e il testo presi come parametri, fatta dall'utente che ha l'username uguale a quello
     * passato come parametro alla serie con ISBN uguale a quello passato come parametro.
     *
     * @param valutazione la valutazione fatta dall'utente alla serie
     * @param text        il testo della recensione fatta dall'utente alla serie
     * @param isbn        l'ISBN della serie recensita
     * @param user        l'username dell'utente recensore
     */
    public void addRecensioneSerieDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' la serie con ISBN 'isbn'

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le recensioni con un testo fatte alla serie con ISBN uguale a quello passato
     * come parametro.
     *
     * @param isbn l'ISBN della serie recensita
     * @return il ResultSet con le informazioni trovate nel database delle recensioni
     */
    public ResultSet allRecWithCommentSerieDB(String isbn);  //ritorna tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'

    /**
     * Calcola la media delle valutazioni fatte al fascicolo con ISSN e titolo della rivista uguali a quelli passati come parametri.
     *
     * @param numero il numero del fascicolo
     * @param titolo il titolo della rivista del fascicolo
     * @return la media delle valutazioni fatte al fascicolo
     */
    public float valutazioneMediaFascicoloDB(int numero, String titolo);    //ritorna la media delle valutazioni del fascicolo numero 'numero' della rivista 'titolo'

    /**
     * Se nel database l'utente con username uguale a quello passato come parametro ha il fascicolo con ISSN e titolo della rivista uguali a quelli
     * passati come parametri, allora ritorna "true", altrimenti ritorna "false".
     *
     * @param numero il numero del fascicolo
     * @param titolo il titolo della rivista del fascicolo
     * @param user   l'username dell'utente
     * @return "true" se l'utente ha nei preferiti il fascicolo, altrimenti "false"
     */
    public boolean likeFascicoloDB(int numero, String titolo, String user); //controlla se l'utente 'user' ha il fascicolo numero 'numero' della rivista 'titolo' tra i preferiti

    /**
     * Se il parametro 'like' è "true", allora toglie il fascicolo con ISSN e titolo della rivista uguali a quelli passati come parametri dai
     * preferiti dell'utente che ha l'username uguale a quello passato come parametro e ritorna "false", altrimenti mette il fascicolo nei preferiti
     * dell'utente e ritorna "true".
     *
     * @param like   la flag che indica se l'utente ha la serie nei preferiti
     * @param numero il numero del fascicolo
     * @param titolo il titolo della rivista del fascicolo
     * @param user   l'username dell'utente
     * @return "true" se mette la serie nei preferiti dell'utente, altrimenti "false"
     */
    public boolean changeLikeFascicoloDB(boolean like, int numero, String titolo, String user); //toglie/mette nei preferiti dell'utente 'user' il fascicolo numero 'numero' della rivista 'titolo' e ritorna l'opposto di 'like'

    /**
     * Aggiunge una nuova recensione, con la valutazione e il testo presi come parametri, fatta dall'utente che ha l'username uguale a quello
     * passato come parametro al fascicolo con ISSN e titolo della rivista uguali a quelli passati come parametri.
     *
     * @param valutazione la valutazione fatta dall'utente alla serie
     * @param text        il testo della recensione fatta dall'utente alla serie
     * @param numero      il numero del fascicolo recensito
     * @param titolo      il titolo della rivista del fascicolo recensito
     * @param user        l'username dell'utente recensore
     */
    public void addRecensioneFascicoloDB(int valutazione, String text, int numero, String titolo, String user);  //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al fascicolo numero 'numero' della rivista 'titolo'

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le recensioni con un testo fatte al fascicolo con ISSN e titolo della rivista
     * uguali a quelli passati come parametri.
     *
     * @param numero il numero del fascicolo recensito
     * @param titolo il titolo della rivista del fascicolo recensito
     * @return il ResultSet con le informazioni trovate nel database delle recensioni
     */
    public ResultSet allRecWithCommentFascicoloDB(int numero, String titolo);  //ritorna tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'

    /**
     * Ritorna la lista di tutti gli ISBN dei libri presenti nel database preferiti dell'utente con username uguale a quello passato come parametro.
     *
     * @param user l'username dell'utente
     * @return la lista degli ISBN dei libri referiti dell'utente
     */
    public ArrayList<String> getLibriISBNPreferitiDB(String user);  //ritorna gli ISBN dei libri preferiti dell'utente 'user'

    /**
     * Ritorna la lista di tutti gli ISBN delle serie presenti nel database preferiti dell'utente con username uguale a quello passato come
     * parametro.
     *
     * @param user l'username dell'utente
     * @return la lista degli ISBN delle serie preferite dell'utente
     */
    public ArrayList<String> getSerieISBNPreferitiDB(String user);  //ritorna gli ISBN delle serie preferite dell'utente 'user'

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}