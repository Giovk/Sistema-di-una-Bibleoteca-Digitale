package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia FascicoloDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative ai
 * fascicoli.
 */
public interface FascicoloDAO {
    /**
     * Ritorna tutte le informazioni riguardanti tutti i fascicoli nel database.
     *
     * @return il ResultSet con le informazioni dei fascicoli trovati nel database
     */
    public ResultSet getFascicoliDB();  //cerca tutti i fascicoli nel DB

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutti i fascicoli preferiti dell'utente che ha effettuato l'accesso.
     *
     * @return il ResultSet con le informazioni dei fascicoli preferiti dell'utente che ha effettuato l'accesso trovate nel database
     */
    public ResultSet getFascicoliDB(String user);   //cerca tutti i fascicoli prefertiti dell'utente nel DB

    /**
     * Ritorna tutte le informazioni nel database riguardanti le librerie che possiedono il fascicolo con il numero uguale a quello preso come
     * parametro della rivista con ISSN uguale a quello preso come parametro
     *
     * @param rivista l'ISSN della rivista del fascicolo
     * @param numero  il numero del fascicolo
     * @return il ResultSet con: le informazioni trovate nel database delle librerie che possiedono il fascicolo; la quantità e la modalità di
     * fruizione disponibile del fascicolo
     */
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero);   //cerca nel DB il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'

    /**
     * Se non è già presente, inserisce nel database un nuovo fascicolo con le informazioni passate come parametro e ritorna "true", altrimenti
     * ritorna "false".
     *
     * @param numero il numero del nuovo fascicolo
     * @param data   la data di pubblicazione del nuovo fascicolo
     * @param issn   l'ISSN della rivista del nuovo fascicolo
     * @return "true" se crea il nuovo fascicolo, altrimenti "false"
     */
    public boolean creaFascicoloDB(int numero, String data, String issn);   //se non esiste già, inserisce un nuovo fascicolo nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina dal database il fascicolo con il numero uguale a quello passato come parametro della rivista con ISSN uguale a quello passato come
     * parametro .
     *
     * @param issn   L'ISSN della rivista del fascicolo da eliminare
     * @param numero il numero del fascicolo da eliminare
     */
    public void eliminaFascicoloDB(String issn, int numero);    //elimina dal DB il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}