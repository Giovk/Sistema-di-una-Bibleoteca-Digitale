package Model;

/**
 * La classe Recensione crea nuove istanze di recensioni e fornisce le loro informazioni.
 */
public class Recensione {
    /**
     * Testo della recensione.
     */
    public String testo;
    /**
     * Valutazione dell'elemento recensito.
     */
    public int valutazione;
    /**
     * Utente recensore.
     */
    public Utente utenteRecensore;
    /**
     * Elemento recensito.
     */
    public Elemento elementoRecensito;

    /**
     * Istanzia una nuova Recensione.
     *
     * @param t  il testo della recensione
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
