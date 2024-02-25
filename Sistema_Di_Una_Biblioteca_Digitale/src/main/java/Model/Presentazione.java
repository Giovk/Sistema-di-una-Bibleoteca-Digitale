package Model;

import java.util.Date;

/**
 * La classe Presentazione crea nuove istanze di presentazioni e fornisce le loro informazioni.
 */
public class Presentazione {
    /**
     * Luogo della presentazione.
     */
    public String luogo;
    /**
     * Data della presentazione.
     */
    public Date data;
    /**
     * Ora  della presentazione.
     */
    public String ora;
    /**
     * Libro presentato nella presentazione.
     */
    public Libro libro;
    /**
     * Struttura della presentazione.
     */
    public String struttura;

    /**
     * Istanzia una nuova Presentazione.
     *
     * @param l  il luogo della presentazione
     * @param s  la struttura della presentazione
     * @param d  la data della presentazione
     * @param o  l'ora della presentazione
     * @param li il libro presentato nella presentazione
     */
    public Presentazione(String l, String s, Date d, String o, Libro li){
        luogo = l;
        struttura = s;
        data = d;
        ora = o;
        libro = li;
    }

    /**
     * Ritorna il luogo della presentazione.
     *
     * @return il luogo della presentazione
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Ritorna la struttura della presentazione.
     *
     * @return la struttura della presentazione
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Ritorna la data della presentazione.
     *
     * @return la data della presentazione
     */
    public Date getData() {
        return data;
    }

    /**
     * Ritorna l'ora della presentazione.
     *
     * @return l'ora della presentazione
     */
    public String getOra() {
        return ora;
    }
}