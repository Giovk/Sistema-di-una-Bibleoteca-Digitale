package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * The interface Collana dao.
 */
public interface CollanaDAO {
    /**
     * Gets collana nome db.
     *
     * @return the collana nome db
     */
    public ArrayList<String> getCollanaNomeDB();    //ritorna i nomi di tutte le collane nel DB

    /**
     * Gets collane db.
     *
     * @return the collane db
     */
    public ResultSet getCollaneDB();    //cerca tutte le collane nel DB

    /**
     * Remove libro from collana db.
     *
     * @param isbn    the isbn
     * @param collana the collana
     */
    public void removeLibroFromCollanaDB(String isbn, String collana);  //rimuove il libro con ISBN 'isbn' dalla collana 'collana'

    /**
     * Add libro in collana db.
     *
     * @param isbn    the isbn
     * @param collana the collana
     */
    public void addLibroInCollanaDB(String isbn, String collana);   //aggiunge il libro con ISBN 'isbn' nella collana 'collana'

    /**
     * Crea collana db boolean.
     *
     * @param nome           the nome
     * @param caratteristica the caratteristica
     * @param issn           the issn
     * @return the boolean
     */
    public boolean creaCollanaDB(String nome, String caratteristica, String issn);  //se non esiste già, inserisce una nuova collana nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}