package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia RivistaDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative alle
 * riviste.
 */
public interface RivistaDAO {
    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le riviste.
     *
     * @return il ResultSet con le informazioni delle riviste trovate nel database
     */
    public ResultSet getRivisteDB();  //cerca i dati di tutti le riviste nel DB

    /**
     * Se non è già presente, inserisce nel database una nuova rivista con le informazioni passate come parametro e ritorna "true", altrimenti
     * ritorna "false".
     *
     * @param issn      l'ISSN della nuova rivista
     * @param titolo    il titolo della nuova rivista
     * @param argomento l'argomento della nuova rivista
     * @param nomeR     il nome del responsabile della nuova rivista
     * @param cognomeR  il cognome del responsabile della nuova rivista
     * @param editore   l'editore della nuova rivista
     * @param ap        l'anno di pubblicazione della nuova rivista
     * @return "true" se crea la nuova rivista, altrimenti "false"
     */
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap);  //se non esiste già, inserisce una nuova rivista nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina dal database la rivista con ISSN uguale a quello passato come parametro.
     *
     * @param issn l'ISSN della rivista
     */
    public void eliminaRivistaDB(String issn);  //elimina dal DB la rivista con ISSN 'issn'

    /**
     * Chiudde la connessione al database.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}
