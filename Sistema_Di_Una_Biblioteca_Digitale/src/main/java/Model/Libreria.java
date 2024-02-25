package Model;

/**
 * The type Libreria.
 */
public class Libreria {
    /**
     * The Nome.
     */
    public String nome;
    /**
     * The Numero telefonico.
     */
    public String numeroTelefonico;
    /**
     * The Indirizzo.
     */
    public String indirizzo;
    /**
     * The Sito web.
     */
    public String sitoWeb;
    /**
     * The Gestore.
     */
    public Utente gestore;

    /**
     * Instantiates a new Libreria.
     *
     * @param n  the n
     * @param nt the nt
     * @param i  the
     * @param sw the sw
     * @param g  the g
     */
    public Libreria(String n, String nt, String i, String sw, Utente g){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
        gestore = g;
    }

    /**
     * Instantiates a new Libreria.
     *
     * @param n  the n
     * @param nt the nt
     * @param i  the
     * @param sw the sw
     */
    public Libreria(String n, String nt, String i, String sw){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets numero telefonico.
     *
     * @return the numero telefonico
     */
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * Gets sito web.
     *
     * @return the sito web
     */
    public String getSitoWeb() {
        return sitoWeb;
    }

    /**
     * Gets indirizzo.
     *
     * @return the indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }
}
