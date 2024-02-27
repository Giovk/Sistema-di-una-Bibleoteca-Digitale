package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * L'interfaccia CollanaDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative alle
 * collane di libri.
 */
public interface CollanaDAO {
    /**
     * Ritorna la lista dei nomi di tutte le collane di libri.
     *
     * @return la lista dei nomi delle collane
     */
    public ArrayList<String> getCollanaNomeDB();    //ritorna i nomi di tutte le collane nel DB

    /**
     * Ritorna tutte le informazioni riguardanti tutte le collane di libri nel database.
     *
     * @return il ResultSet con le informazioni delle collane trovate nel database
     */
    public ResultSet getCollaneDB();    //cerca tutte le collane nel DB

    /**
     * Rimuove nel database il libro con ISBN ugaule a quello passato come parametro dalla collana con il nome uguale a quello passato come parametro.
     *
     * @param isbn    l'ISBN del libro
     * @param collana il nome della collana
     */
    public void removeLibroFromCollanaDB(String isbn, String collana);  //rimuove il libro con ISBN 'isbn' dalla collana 'collana'

    /**
     * Aggiunge nel database il libro con ISBN ugaule a quello passato come parametro nella collana con il nome uguale a quello passato come
     * parametro.
     *
     * @param isbn    l'ISBN del libro
     * @param collana il nome della collana
     */
    public void addLibroInCollanaDB(String isbn, String collana);   //aggiunge il libro con ISBN 'isbn' nella collana 'collana'

    /**
     * Se non è già presente, inserisce nel database una nuova collana di libri con le informazioni passate come parametro e ritorna "true",
     * altrimenti ritorna "false".
     *
     * @param nome           il nome della nuova collana
     * @param caratteristica la caratteristica della nuova collana
     * @param issn           l'ISSN della nuova collana
     * @return "true" se crea la nuova collana, altrimenti "false"
     */
    public boolean creaCollanaDB(String nome, String caratteristica, String issn);  //se non esiste già, inserisce una nuova collana nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}