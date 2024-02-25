package DAO;

import java.sql.ResultSet;

/**
 * The interface Articolo scientifico dao.
 */
public interface ArticoloScientificoDAO {
    /**
     * Gets articoli scientifici db.
     *
     * @param issn the issn
     * @param n    the n
     * @return the articoli scientifici db
     */
    public ResultSet getArticoliScientificiDB(String issn, int n);  //cerca gli artioli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'

    /**
     * Crea articolo db boolean.
     *
     * @param titolo the titolo
     * @param anno   the anno
     * @param numero the numero
     * @param issn   the issn
     * @return the boolean
     */
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn);    //se non esiste già, inserisce un nuovo articolo nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Gets doi db.
     *
     * @param titolo the titolo
     * @return the doi db
     */
    public String getDoiDB(String titolo);  //ritorna il DOI dell'articolo scientifico 'titolo'

    /**
     * Gets apdb.
     *
     * @param doi the doi
     * @return the apdb
     */
    public int getAPDB(String doi); //ritorna l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'

    /**
     * Elimina articolo db.
     *
     * @param doi the doi
     */
    public void eliminaArticoloDB(String doi);  //elimina l'articolo scientifico con DOI 'doi'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}
