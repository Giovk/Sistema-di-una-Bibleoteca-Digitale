package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia LibreriaDAO contiene i metodi (da implementare) che intergiscono con il database per implementare le funzionalità relative alle
 * librerie.
 */
public interface LibreriaDAO {
    /**
     * Ritorna tutte le informazioni nel database riguardanti le disponibilità nelle librerie del libro o della serie con ISBN uguale a quello
     * passato come parametro.
     *
     * @return il ResultSet con le informazioni trovate nel database delle disponibilità nelle librerie del libro o della serie
     */
    public ResultSet disponibilitaDB(String isbn);  //cerca le disponibilità del libro o della serie con ISBN 'isbn' nelle librerie

    /**
     * Ritorna tutte le informazioni nel database riguardanti le disponibilità nelle librerie del fascicolo con numero e titolo della rivista
     * passati come parametri.
     *
     * @param numero il numero del fascicolo
     * @param titolo il titolo della rivista del fascicolo
     * @return il ResultSet con le informazioni trovate nel database delle disponibilità nelle librerie del fascicolo
     */
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo);  //cerca le disponibilità del fascicolo numero 'numero' della rivista 'titolo' nelle librerie

    /**
     * Ritorna tutte le informazioni delle librerie nel database gestite dall'utente con username uguale a quello passato come arametro.
     *
     * @param user l'username dell'utente
     * @return il ResultSet con le informazioni trovate nel database delle librerie gestite dall'utente
     */
    public ResultSet getLibrerieUtenteDB(String user);  //cerca tutte le librerie dell'utente 'user'

    /**
     * Controlla nel database se il numero telefonico passato come parametro non è stato già utilizzato per altre librerie.
     *
     * @param nt il numero telefonico da verificare
     * @return ritorna "true" se il numero telefonico non è stato già utilizzato, altrimenti ritorna "false"
     */
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt);   //controlla se il numero telefonico 'nt' è già presente nel DB

    /**
     * Controlla nel database se non esiste già una libreria con il nome, il sito web e l'indirizzo uguali a quelli passati come parametri.
     *
     * @param nome      il nome da verificare
     * @param sw        il sito web da verificare
     * @param indirizzo l'indirizzo da verificare
     * @return ritorna "true" se non è esiste nel database già una libreria con il nome, il sito web e l'indirizzo uguali a quelli passati come
     * parametri, altrimenti ritorna "false"
     */
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo);    //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB

    /**
     * Aggiunge nel database una nuova libreria gestista dall'utente con l'username uguale a quello passato come parametro.
     *
     * @param nome      il nome della nuova libreria gestita dall'utente
     * @param nt        il numero telefonico della nuova libreria gestita dall'utente
     * @param sw        il link al sito web della nuova libreria gestita dall'utente
     * @param indirizzo l'indirizzo della nuova libreria gestita dall'utente
     * @param user      l'username dell'utente che gestisce la nuova libreria
     */
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user);    //aggiunge la libreria 'nome' dell'utente 'user' con numero telefoico 'nt', sito web 'sw' e indirizzo 'indirizzo'

    /**
     * Rimuove dal database la libreria che ha come numero telefonico quello passato come parametro.
     *
     * @param nt il numero telefonico della libreria da rimuovere dal database
     */
    public void removeLibreriaDB(String nt);    //rimuove dal DB la libreria con il numero telefonico 'nt'
}