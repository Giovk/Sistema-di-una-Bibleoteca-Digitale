package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia AutoreDAO contiene i metodi (da implementare) che intergiscono con il database per implementare le funzionalità relative agli
 * autori di libri e articoli scientifici.
 */
public interface AutoreDAO {
    /**
     * Ritorna le informazioni riguardanti tutti gli autori nel database del libro con ISBN uguale a quello passato come parametro.
     *
     * @param isbn L'ISBN del libro
     * @return il ResultSet con le informazioni degli autori trovate nel database
     */
    public ResultSet getAutoriLibroDB(String isbn);  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'

    /**
     * Ritorna le informazioni riguardanti tutti gli autori nel database dell'articolo scientifico con DOI uguale a quello passato come parametro.
     *
     * @param doi il DOI dell'articolo scientifico
     * @return il ResultSet con le informazioni degli autori trovate nel database
     */
    public ResultSet getAutoriArticoloDB(String doi);   //ritorna i dati di tutti gli autori nel DB dell'articolo con DOI 'doi'

    /**
     * Aggiunge nel database un nuovo autore con le informazioni assate come parametro al libro con ISBN uguale a quello passasto come parametro.
     *
     * @param nome        il nome del nuovo autore
     * @param cognome     il cognome del nuovo autore
     * @param nazionalita la nazionalita del nuovo autore
     * @param dn          la data di nascita del nuovo autore
     * @param isbn        l'ISBN del libro del nuovo autore
     */
    public void aggiungiAutoreLibroDB(String nome, String cognome, String nazionalita, String dn, String isbn); //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore al libro con ISBN 'isbn'

    /**
     * Aggiunge nel database un nuovo autore con le informazioni assate come parametro all'articolo con DOI uguale a quello passasto come parametro.
     *
     * @param nome        il nome del nuovo autore
     * @param cognome     il cognome del nuovo autore
     * @param nazionalita la nazionalita del nuovo autore
     * @param dn          la data di nascita del nuovo autore
     * @param doi         il DOI dell'articolo del nuovo autore
     */
    public void aggiungiAutoreArticoloDB(String nome, String cognome, String nazionalita, String dn, String doi);   //se l'autore 'nome' 'cognome' nato il 'dn' di nazionalità 'nazionalita' non già presente lo aggiunge al DB, e associa l'autore all'articolo con DOI 'doi'
}