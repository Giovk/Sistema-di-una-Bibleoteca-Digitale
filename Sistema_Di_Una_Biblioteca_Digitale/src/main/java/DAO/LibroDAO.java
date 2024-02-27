package DAO;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * L'interfaccia LibroDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative ai libri.
 */
public interface LibroDAO {
    /**
     * Ritorna tutte le informazioni nel database riguardanti tutti i libri.
     *
     * @return il ResultSet con le informazioni dei libri trovate nel database
     */
    public ResultSet getLibriDB();  //cerca i dati di tutti i libri nel DB

    /**
     * Ritorna tutte le informazioni nel database riguardanti i libri della collana con il nome uguale a quello passato come parametro.
     *
     * @param collana il nome della collana
     * @return il ResultSet con le informazioni trovate nel database dei libri della collana
     */
    public ResultSet getLibriDB(String collana);  //cerca i dati di tutti i libri della collana 'collana' nel DB

    /**
     * Ritorna tutte le informazioni nel database riguardanti i libri della serie con l'ISBN uguale a quello passato come parametro.
     *
     * @param s l'ISBN della serie
     * @return il ResultSet con le informazioni trovate nel database dei libri della serie
     */
    public ResultSet getLibriSerieDB(String s); //cerca i dati di tutti i libri della serie con ISBN 's' nel DB

    /**
     * Ritorna tutte le informazioni nel database riguardanti le librerie che possiedono il libro con l'ISBN uguale a quello passato come parametro,
     * la quatità e la modalità di fruizione disponibile.
     *
     * @param isbn l'ISBN del libro
     * @return il ResultSet con: le informazioni trovate nel database delle librerie che possiedono il libro; la quantità e la modalità di fruizione
     * disponibile del libro
     */
    public ResultSet getInfoLibriPreferitiDB(String isbn);  //cerca i dati del libro con ISBN 'isbn' e delle librerie che lo possiedono

    /**
     * Se non è già presente, inserisce nel database un nuovo libro con le informazioni passate come parametro e ritorna "true", altrimenti
     * ritorna "false".
     *
     * @param isbn    l'ISBN del nuovo libro
     * @param titolo  il titolo del nuovo libro
     * @param genere  il genere del nuovo libro
     * @param lingua  la lingua del nuovo libro
     * @param editore l'editore del nuovo libro
     * @param dp      la data di pubblicazione del nuovo libro
     * @return "true" se crea il nuovo libro, altrimenti "false"
     */
    public boolean creaLibroDB(String isbn, String titolo, String genere, String lingua, String editore, String dp);    //se non esiste già, inserisce un nuovo libro nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Cerca nel database la data di pubblicazione del libro con ISBN uguale a quello passato come parametro.
     *
     * @param isbn l'ISBN del libro
     * @return la data di pubblicazione del libro
     */
    public Date getDataLibroDB(String isbn);    //cerca la data di pubblicazione del libro con ISBN 'isbn'

    /**
     * Elimina dal database il libro con ISBN uguale a quello passato come parametro..
     *
     * @param isbn l'ISBN del libro
     */
    public void eliminaLibroDB(String isbn);    //elimina dal DB il libro con ISBN 'isbn'

    /**
     * Chiudi la connessione al database.
     */
    public void chiudiConnessione(); //chiude la connessione al DB
}