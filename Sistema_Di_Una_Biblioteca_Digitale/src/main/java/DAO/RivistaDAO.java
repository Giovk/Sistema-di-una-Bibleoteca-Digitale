package DAO;

import java.sql.ResultSet;

/**
 * The interface Rivista dao.
 */
public interface RivistaDAO {
    /**
     * Gets riviste db.
     *
     * @return the riviste db
     */
    public ResultSet getRivisteDB();  //cerca i dati di tutti le riviste nel DB

    /**
     * Crea rivista db boolean.
     *
     * @param issn      the issn
     * @param titolo    the titolo
     * @param argomento the argomento
     * @param nomeR     the nome r
     * @param cognomeR  the cognome r
     * @param editore   the editore
     * @param ap        the ap
     * @return the boolean
     */
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap);  //se non esiste già, inserisce una nuova rivista nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina rivista db.
     *
     * @param issn the issn
     */
    public void eliminaRivistaDB(String issn);  //elimina dal DB la rivista con ISSN 'issn'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}
