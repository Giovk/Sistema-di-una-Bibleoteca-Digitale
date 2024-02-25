package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Serie.
 */
public class Serie extends Elemento{
    /**
     * The Isbn.
     */
    public String isbn;
    /**
     * The Titolo.
     */
    public String titolo;
    /**
     * The N libri.
     */
    public int nLibri;
    /**
     * The Libri.
     */
    public ArrayList<Libro> libri = new ArrayList<>();

    /**
     * Instantiates a new Serie.
     *
     * @param i  the
     * @param nl the nl
     * @param l  the l
     * @param t  the t
     * @param dp the dp
     */
    public Serie(String i, int nl, ArrayList<Libro> l, String t, Date dp){
        super(dp);
        titolo = t;
        isbn = i;
        nLibri = nl;

        for (Libro libroAttuale : l){
            libri.add(libroAttuale);
        }
    }

    /**
     * Instantiates a new Serie.
     *
     * @param i  the
     * @param nl the nl
     * @param t  the t
     * @param dp the dp
     */
    public Serie(String i, int nl, String t, Date dp){
        super(dp);
        titolo = t;
        isbn = i;
        nLibri = nl;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Gets titolo.
     *
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Gets n libri.
     *
     * @return the n libri
     */
    public int getNLibri() {
        return nLibri;
    }
}