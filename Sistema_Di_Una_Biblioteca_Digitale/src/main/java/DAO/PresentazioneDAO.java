package DAO;

import java.sql.ResultSet;

/**
 * The interface Presentazione dao.
 */
public interface PresentazioneDAO {
    /**
     * Gets presentazione db.
     *
     * @param isbn the isbn
     * @return the presentazione db
     */
    public ResultSet getPresentazioneDB(String isbn);   //cerca i dati di tutte le presentazioni del libro on ISBN 'isbn' nel DB

    /**
     * Add presentazione db boolean.
     *
     * @param struttura the struttura
     * @param luogo     the luogo
     * @param data      the data
     * @param orario    the orario
     * @param isbn      the isbn
     * @return the boolean
     */
    public boolean addPresentazioneDB(String struttura, String luogo, String data, String orario, String isbn); //se non esiste già, inserisce una nuova presentazione nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}