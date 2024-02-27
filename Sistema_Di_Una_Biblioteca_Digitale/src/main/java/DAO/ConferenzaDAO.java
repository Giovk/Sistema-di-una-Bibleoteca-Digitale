package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia ConferenzaDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative alle
 * conferenze .
 */
public interface ConferenzaDAO {
    /**
     * Ritorna tutte le informazioni presenti nel database riguardanti tutte le conferenze in cui è esposto l'articolo scientifico con il DOI uguale
     * a quello passato come parametro.
     *
     * @param doi il DOI dell'articolo
     * @return il ResultSet con le informazioni trovate nel database delle conferenze dell'articolo trovate
     */
    public ResultSet getConferenzeArticoloDB(String doi);   //cerca tutte le conferenze del articolo con DOI 'doi' nel DB

    /**
     * Ritorna tutte le informazioni riguardanti tutte le conferenze nel database.
     *
     * @return il ResultSet con le informazioni delle conferenze trovate nel database
     */
    public ResultSet getConferenzeDB(); //cerca tutte le conferenze nel DB

    /**
     * Se non è già presente, inserisce una nuova conferenza con le informazioni passate come parametro e ritorna "true", altrimenti ritorna "false".
     *
     * @param struttura la struttura organizzatrice della nuova conferenza
     * @param luogo     il luogo della nuova conferenza
     * @param datai     la data di inizio della nuova conferenza
     * @param dataf     la data di fine della nuova conferenza
     * @return "true" se crea la nuova conferenza, altrimenti "false"
     */
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf); //se non esiste già, inserisce una nuova confereza nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Se non è già presente, crea una nuova esposizione dell'articolo con DOI passato come parametro nella conferenza con le informazioni passate
     * come parametro e ritorna "true", altrimenti ritorna "false".
     *
     * @param struttura la struttura organizzatrice della conferenza della nuova esposizione
     * @param luogo     il luogo della conferenza della nuova esposizione
     * @param datai     la data di inizio della conferenza della nuova esposizione
     * @param dataf     la data di fine della conferenza della nuova esposizione
     * @param doi       il DOI dell'articolo esposto
     * @return "true" se crea la nuova esposizione, altrimenti "false"
     */
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi);    //se non esiste già, inserisce una nuova esposizione nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}