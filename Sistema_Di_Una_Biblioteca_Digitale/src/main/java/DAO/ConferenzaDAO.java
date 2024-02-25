package DAO;

import java.sql.ResultSet;

/**
 * The interface Conferenza dao.
 */
public interface ConferenzaDAO {
    /**
     * Gets conferenze articolo db.
     *
     * @param doi the doi
     * @return the conferenze articolo db
     */
    public ResultSet getConferenzeArticoloDB(String doi);   //cerca tutte le conferenze del articolo con DOI 'doi' nel DB

    /**
     * Gets conferenze db.
     *
     * @return the conferenze db
     */
    public ResultSet getConferenzeDB(); //cerca tutte le conferenze nel DB

    /**
     * Add conferenza db boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param datai     the datai
     * @param dataf     the dataf
     * @return the boolean
     */
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf); //se non esiste già, inserisce una nuova confereza nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Add esposizione db boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param datai     the datai
     * @param dataf     the dataf
     * @param doi       the doi
     * @return the boolean
     */
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi);    //se non esiste già, inserisce una nuova esposizione nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}