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
     * @param v  la valutazione dell'elemento recensito
     * @param p  la flag che indica se l'elemento è tra i preferiti dell'utente recensore
     * @param ur l'utente recensore
     * @param er l'elemento recensito
     */
    public Recensione(String t, int v, boolean p, Utente ur, Elemento er){
        testo = t;
        valutazione = v;
        utenteRecensore = ur;
        elementoRecensito = er;
    }

    /**
     * Ritorna l'utente recensore.
     *
     * @return l'utente recensore
     */
    public Utente getUtenteRecensore() {
        return utenteRecensore;
    }

    /**
     * Ritorna la valutazione fatta dall'utente all'elemento recensito.
     *
     * @return la valutazione dell'elemento recensito
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * Ritorna il testo della recensione.
     *
     * @return il testo della recensione
     */
    public String getTesto() {
        return testo;
    }
}