package DAO;

import java.sql.ResultSet;

/**
 * The interface Libreria dao.
 */
public interface LibreriaDAO {
    /**
     * Disponibilita db result set.
     *
     * @param isbn the isbn
     * @return the result set
     */
    public ResultSet disponibilitaDB(String isbn);  //cerca le disponibilità del libro o della serie con ISBN 'isbn' nelle librerie

    /**
     * Disponibilita fascicolo db result set.
     *
     * @param numero the numero
     * @param titolo the titolo
     * @return the result set
     */
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo);  //cerca le disponibilità del fascicolo numero 'numero' della rivista 'titolo' nelle librerie

    /**
     * Gets librerie utente db.
     *
     * @param user the user
     * @return the librerie utente db
     */
    public ResultSet getLibrerieUtenteDB(String user);  //cerca tutte le librerie dell'utente 'user'

    /**
     * Presenza numero telefonico libreria db boolean.
     *
     * @param nt the nt
     * @return the boolean
     */
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt);   //controlla se il numero telefonico 'nt' è già presente nel DB

    /**
     * Presenza libreria db boolean.
     *
     * @param nome      the nome
     * @param sw        the sw
     * @param indirizzo the indirizzo
     * @return the boolean
     */
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo);    //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB

    /**
     * Add libreria db.
     *
     * @param nome      the nome
     * @param nt        the nt
     * @param sw        the sw
     * @param indirizzo the indirizzo
     * @param user      the user
     */
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user);    //aggiunge la libreria 'nome' dell'utente 'user' con numero telefoico 'nt', sito web 'sw' e indirizzo 'indirizzo'

    /**
     * Remove libreria db.
     *
     * @param nt the nt
     */
    public void removeLibreriaDB(String nt);    //rimuove dal DB la libreria con il numero telefonico 'nt'
}