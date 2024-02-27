package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia ArticoloScientificoDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative
 * agli articoli scientifici.
 */
public interface ArticoloScientificoDAO {
    /**
     * Ritorna tutte le informazioni riguardanti tutti gli articoli scientifici nel database del fascicolo con numero e la rivista con ISSN presi
     * come parametri.
     *
     * @param issn l'ISSN della rivista dell'articolo scientifico
     * @param n    il numero dell'articolo scientifico
     * @return il ResultSet con le informazioni dell'articolo scientifico trovate nel database
     */
    public ResultSet getArticoliScientificiDB(String issn, int n);  //cerca gli artioli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'

    /**
     * Se non è già presente, inserisce nel database un nuovo articolo scientifico con le informazioni passate come parametro nel fascicolo con
     * numero passato come parametro della rivista con ISSN uguale a quello passato come parametro e ritorna "true", altrimenti ritorna "false".
     *
     * @param titolo il titolo del nuovo articolo scientifico
     * @param anno   l'anno di pubblicazione del nuovo articolo scientifico
     * @param numero il numero del fascicolo del nuovo articolo scientifico
     * @param issn   l'ISSN della rivista del fascicolo del nuovo articolo scientifico
     * @return "true" se crea il nuovo articolo scientifico, altrimenti "false"
     */
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn);    //se non esiste già, inserisce un nuovo articolo nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Ritorna il DOI dell'articolo scientifico presente nel database con il titolo passato come parametro.
     *
     * @param titolo il titolo dell'articolo scientifico
     * @return il DOI dell'articolo scientifico
     */
    public String getDoiDB(String titolo);  //ritorna il DOI dell'articolo scientifico 'titolo'

    /**
     * Ritorna l'anno di pubblicazione dell'articolo scientifico presente nel database con il DOI passato come parametro.
     *
     * @param doi il DOI dell'articolo scientifico
     * @return l'anno di pubblicazione dell'articolo scientifico
     */
    public int getAPDB(String doi); //ritorna l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'

    /**
     * Elimina dal database l'articolo scientifico con DOI passato come parametro.
     *
     * @param doi il DOI dell'articolo scientifico
     */
    public void eliminaArticoloDB(String doi);  //elimina l'articolo scientifico con DOI 'doi'

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}