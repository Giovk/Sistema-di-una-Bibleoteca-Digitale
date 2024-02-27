package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia PresentazioneDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative
 * alle presentazioni dei libri.
 */
public interface PresentazioneDAO {
    /**
     * Ritorna tutte le informazioni nel database riguardanti tutte le presentazioni del libro con ISBN uguale a quello passato come parametro.
     *
     * @param isbn l'ISBN del libro
     * @return il ResultSet con le informazioni trovate nel database delle presentazioni
     */
    public ResultSet getPresentazioneDB(String isbn);   //cerca i dati di tutte le presentazioni del libro on ISBN 'isbn' nel DB

    /**
     * Se non è già presente, inserisce nel database una nuova presentazione con le informazioni passate come parametro del libro con ISBN uguale a
     * quello passato come parametro e ritorna "true", altrimenti ritorna "false".
     *
     * @param struttura la struttura organizzatrice della nuova resentazione
     * @param luogo     il luogo della nuova resentazione
     * @param data      la data della nuova resentazione
     * @param orario    l'orario della nuova resentazione
     * @param isbn      l'ISBN del libro presentato
     * @return "true" se crea la nuova presentazione, altrimenti "false"
     */
    public boolean addPresentazioneDB(String struttura, String luogo, String data, String orario, String isbn); //se non esiste già, inserisce una nuova presentazione nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}