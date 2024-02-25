package Model;

import java.util.Date;

/**
 * The type Presentazione.
 */
public class Presentazione {
    /**
     * The Luogo.
     */
    public String luogo;
    /**
     * The Data.
     */
    public Date data;
    /**
     * The Ora.
     */
    public String ora;
    /**
     * The Libro.
     */
    public Libro libro;
    /**
     * The Struttura.
     */
    public String struttura;

    /**
     * Instantiates a new Presentazione.
     *
     * @param l  the l
     * @param s  the s
     * @param d  the d
     * @param o  the o
     * @param li the li
     */
    public Presentazione(String l, String s, Date d, String o, Libro li){
        luogo = l;
        struttura = s;
        data = d;
        ora = o;
        libro = li;
    }

    /**
     * Gets luogo.
     *
     * @return the luogo
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Gets struttura.
     *
     * @return the struttura
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * Gets ora.
     *
     * @return the ora
     */
    public String getOra() {
        return ora;
    }
}
