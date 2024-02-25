package Model;

/**
 * La classe Libreria crea nuove istanze di librerie e fornisce le loro informazioni.
 */
public class Libreria {
    /**
     * Nome della libreria.
     */
    public String nome;
    /**
     * Numero telefonico della libreria.
     */
    public String numeroTelefonico;
    /**
     * Indirizzo della libreria.
     */
    public String indirizzo;
    /**
     * Link del Sito web della libreria.
     */
    public String sitoWeb;
    /**
     * Utente Gestore della libreria.
     */
    public Utente gestore;

    /**
     * Istanzia una nuova Libreria con il suo gestore.
     *
     * @param n  il nome della libreria
     * @param nt il numero telefonico della libreria
     * @param i  l'indirizzo della libreria
     * @param sw il link della libreria
     * @param g  l'utente che gestisce la libreria
     */
    public Libreria(String n, String nt, String i, String sw, Utente g){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
        gestore = g;
    }

    /**
     * Istanzia una nuova Libreria.
     *
     * @param n  il nome della libreria
     * @param nt il numero telefonico della libreria
     * @param i  l'indirizzo della libreria
     * @param sw il link della libreria
     */
    public Libreria(String n, String nt, String i, String sw){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
    }

    /**
     * Ritorna nome il nome della libreria.
     *
     * @return il nome della libreria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ritorna il numero telefonico della libreria.
     *
     * @return il numero telefonico della libreria
     */
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * Ritorna il link del sito web della libreria.
     *
     * @return il link del sito web della libreria
     */
    public String getSitoWeb() {
        return sitoWeb;
    }

    /**
     * Ritorna l'indirizzo del sito web della libreria.
     *
     * @return l'indirizzo del sito web della libreria
     */
    public String getIndirizzo() {
        return indirizzo;
    }
}
