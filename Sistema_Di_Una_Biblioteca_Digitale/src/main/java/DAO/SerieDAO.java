package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * L'interfaccia RivistaDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative alle
 * serie di libri.
 */
public interface SerieDAO {
    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le serie di libri.
     *
     * @return il ResultSet con le informazioni delle serie trovate nel database
     */
    public ResultSet getSerieDB();  //cerca i dati di tutte le serie nel DB

    /**
     * Ritorna la lista di tutti i generi dei libri delle serie presenti nel database.
     *
     * @return la lista dei generi dei libri delle serie
     */
    public ArrayList<String> getSerieGenereDB();    //ritorna tutti i generi dei libri che sono inseriti in qualche serie

    /**
     * Ritorna la lista di tutti gli autori (nomi e cognomi) dei libri delle serie presenti nel database.
     *
     * @return la lista degli autori dei libri delle serie
     */
    public ArrayList<String> getSerieAutoriDB();    //ritorna tutti gli autori dei libri che sono inseriti in qualche serie

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le serie con libri del genere passato come parametro.
     *
     * @param genere il genere dei libri
     * @return il ResultSet con le informazioni delle serie trovate nel database
     */
    public ResultSet getListaSerieGenereDB(String genere);  //cerca i dati delle serie con libri del genere 'genere'

    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le serie con libri dell'autore passato come parametro.
     *
     * @param autore l'autore dei libri
     * @return il ResultSet con le informazioni delle serie trovate nel database
     */
    public ResultSet getListaSerieAutoreDB(String autore);  //cerca i dati delle serie con libri dell'autore 'autore'

    /**
     * Ritorna tutte le informazioni nel database riguardanti le librerie che possiedono la serie con l'ISBN uguale a quello passato come parametro,
     * la quatità e la modalità di fruizione disponibile.
     *
     * @param isbn l'ISBN della serie
     * @return il ResultSet con: le informazioni trovate nel database delle librerie che possiedono la serie; la quantità e la modalità di fruizione
     * disponibile della serie
     */
    public ResultSet getInfoSeriePreferitiDB(String isbn);  //cerca i dati della serie con ISBN 'isbn' e delle librerie che lo possiedono

    /**
     * Se non è già presente, inserisce nel database una nuova serie con le informazioni passate come parametro e ritorna "true", altrimenti
     * ritorna "false".
     *
     * @param isbnList la lista degli ISBN dei libri della nuova serie
     * @param isbn     l'ISBN della nuova serie
     * @param titolo   il titolo della nuova serie
     * @param dp       la data di pubblicazione della nuova serie
     * @return true" se crea la nuova serie, altrimenti "false"
     */
    public boolean creaSerieDB(ArrayList<String> isbnList, String isbn, String titolo, String dp);  //se non esiste già, inserisce una nuova serie nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiudi la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}