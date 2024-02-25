package DAO;

import java.sql.ResultSet;

/**
 * The interface Possesso dao.
 */
public interface PossessoDAO {
    /**
     * Gets possesso l libreria db.
     *
     * @param nome      the nome
     * @param sitoweb   the sitoweb
     * @param indirizzo the indirizzo
     * @param user      the user
     * @return the possesso l libreria db
     */
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei libri della libreria selezionata gestista dall'utente 'user'

    /**
     * Gets possesso s libreria db.
     *
     * @param nome      the nome
     * @param sitoweb   the sitoweb
     * @param indirizzo the indirizzo
     * @param user      the user
     * @return the possesso s libreria db
     */
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi delle serie della libreria selezionata gestista dall'utente 'user'

    /**
     * Gets possesso f libreria db.
     *
     * @param nome      the nome
     * @param sitoweb   the sitoweb
     * @param indirizzo the indirizzo
     * @param user      the user
     * @return the possesso f libreria db
     */
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'

    /**
     * Mod quantita libro db.
     *
     * @param isbn      the isbn
     * @param nt        the nt
     * @param fruizione the fruizione
     * @param value     the value
     */
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del libro con ISBN 'isbn'

    /**
     * Mod quantita fascicolo db.
     *
     * @param issn      the issn
     * @param numero    the numero
     * @param nt        the nt
     * @param fruizione the fruizione
     * @param value     the value
     */
    public void modQuantitaFascicoloDB(String issn, int numero, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del fascicolo numero 'numero' della rivista con ISSN 'issn'

    /**
     * Insert possesso ldb boolean.
     *
     * @param isbn      the isbn
     * @param nt        the nt
     * @param quantita  the quantita
     * @param fruizione the fruizione
     * @return the boolean
     */
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del libro con ISBN 'isbn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Insert possesso fdb boolean.
     *
     * @param numero    the numero
     * @param issn      the issn
     * @param nt        the nt
     * @param quantita  the quantita
     * @param fruizione the fruizione
     * @return the boolean
     */
    public boolean insertPossessoFDB(int numero, String issn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del fascicolo numero 'numero' della rivista con ISSN 'issn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina possesso ldb.
     *
     * @param isbn      the isbn
     * @param nt        the nt
     * @param fruizione the fruizione
     */
    public void eliminaPossessoLDB(String isbn, String nt, String fruizione);   //elimina dal DB il possesso del libro con ISBN 'isbn'

    /**
     * Elimina possesso fdb.
     *
     * @param issn      the issn
     * @param numero    the numero
     * @param nt        the nt
     * @param fruizione the fruizione
     */
    public void eliminaPossessoFDB(String issn, int numero, String nt, String fruizione);   //elimina dal DB il possesso del fascicolo numero 'numero' della rivista con ISSN 'issn'

    /**
     * Chiudi connessione.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}