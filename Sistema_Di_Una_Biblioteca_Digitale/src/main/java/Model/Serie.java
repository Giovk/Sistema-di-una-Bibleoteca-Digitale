package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Serie crea nuove istanze di serie e fornisce le loro informazioni.
 */
public class Serie extends Elemento{
    /**
     * ISBN della serie.
     */
    public String isbn;
    /**
     * Titolo della serie.
     */
    public String titolo;
    /**
     * Numero di libri che formano la serie.
     */
    public int nLibri;
    /**
     * Lista dei Libri che formano la serie
     */
    public ArrayList<Libro> libri = new ArrayList<>();

    /**
     * Istanzia una nuova Serie con i suoi libri.
     *
     * @param i  l'ISBN della serie
     * @param nl il numero dei libri della serie
     * @param l  la lista dei libri della serie
     * @param t  il titolo della serie
     * @param dp la data di pubblicazione della serie
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
     * Istanzia una nuova Serie.
     *
     * @param i  l'ISBN della serie
     * @param nl il numero dei libri della serie
     * @param t  il titolo della serie
     * @param dp la data di pubblicazione della serie
     */
    public Serie(String i, int nl, String t, Date dp){
        super(dp);
        titolo = t;
        isbn = i;
        nLibri = nl;
    }

    /**
     * Ritorna l'ISBN della serie.
     *
     * @return l'ISBN della serie
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Ritorna il titolo della serie.
     *
     * @return il titolo della serie
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Ritorna il numero dei libri della serie.
     *
     * @return il numero dei libri della serie
     */
    public int getNLibri() {
        return nLibri;
    }
}