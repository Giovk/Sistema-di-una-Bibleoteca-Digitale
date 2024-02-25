package DAO;

import java.sql.ResultSet;

/**
 * The interface Fascicolo dao.
 */
public interface FascicoloDAO {
    /**
     * Gets fascicoli db.
     *
     * @return the fascicoli db
     */
    public ResultSet getFascicoliDB();  //cerca tutti i fascicoli nel DB

    /**
     * Gets fascicoli db.
     *
     * @param user the user
     * @return the fascicoli db
     */
    public ResultSet getFascicoliDB(String user);   //cerca tutti i fascicoli prefertiti dell'utente nel DB

    /**
     * Gets info fascicoli preferiti db.
     *
     * @param rivista the rivista
     * @param numero  the numero
     * @return the info fascicoli preferiti db
     */
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero);   //cerca nel DB il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'

    /**
     * Crea fascicolo db boolean.
     *
     * @param numero the numero
     * @param data   the data
     * @param issn   the issn
     * @return the boolean
     */
    public boolean creaFascicoloDB(int numero, String data, String issn);   //se non esiste già, inserisce un nuovo fascicolo nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina fascicolo db.
     *
     * @param issn   the issn
     * @param numero the numero
     */
    public void eliminaFascicoloDB(String issn, int numero);    //elimina dal DB il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}