package Model;

/**
 * The type Recensione.
 */
public class Recensione {
    /**
     * The Testo.
     */
    public String testo;
    /**
     * The Valutazione.
     */
    public int valutazione;
    /**
     * The Utente recensore.
     */
    public Utente utenteRecensore;
    /**
     * The Elemento recensito.
     */
    public Elemento elementoRecensito;

    /**
     * Instantiates a new Recensione.
     *
     * @param t  the t
     * @param v  the v
     * @param p  the p
     * @param ur the ur
     * @param er the er
     */
    public Recensione(String t, int v, boolean p, Utente ur, Elemento er){
        testo = t;
        valutazione = v;
        utenteRecensore = ur;
        elementoRecensito = er;
    }

    /**
     * Gets utente recensore.
     *
     * @return the utente recensore
     */
    public Utente getUtenteRecensore() {
        return utenteRecensore;
    }

    /**
     * Gets valutazione.
     *
     * @return the valutazione
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * Gets testo.
     *
     * @return the testo
     */
    public String getTesto() {
        return testo;
    }
}
