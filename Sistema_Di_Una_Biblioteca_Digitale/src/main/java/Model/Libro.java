package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Libro.
 */
public class Libro extends Elemento {
    /**
     * The Isbn.
     */
    public String isbn;
    /**
     * The Genere.
     */
    public String genere;
    /**
     * The Editore.
     */
    public String editore;
    /**
     * The Lingua.
     */
    public String lingua;
    /**
     * The Autori.
     */
    public ArrayList<Autore> autori = new ArrayList<Autore>();
    /**
     * The Titolo.
     */
    public String titolo;

    /**
     * Instantiates a new Libro.
     *
     * @param i  the
     * @param g  the g
     * @param e  the e
     * @param l  the l
     * @param a  the a
     * @param t  the t
     * @param dp the dp
     */
    public Libro(String i, String g, String e, String l, ArrayList<Autore> a, String t, Date dp) {
        super(dp);

        isbn = i;
        genere = g;
        editore = e;
        lingua = l;
        titolo = t;

        for (Autore autoreAttuale : a) {
            autori.add(autoreAttuale);
        }
    }

    /**
     * Instantiates a new Libro.
     *
     * @param i  the
     * @param g  the g
     * @param e  the e
     * @param l  the l
     * @param t  the t
     * @param dp the dp
     */
    public Libro(String i, String g, String e, String l, String t, Date dp) {
        super(dp);

        titolo = t;
        isbn = i;
        genere = g;
        editore = e;
        lingua = l;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getISBN()
    {
        return isbn;
    }

    /**
     * Gets titolo.
     *
     * @return the titolo
     */
    public String getTitolo()
    {
        return titolo;
    }

    /**
     * Gets genere.
     *
     * @return the genere
     */
    public String getGenere()
    {
        return genere;
    }

    /**
     * Gets lingua.
     *
     * @return the lingua
     */
    public String getLingua()
    {
        return lingua;
    }

    /**
     * Gets editore.
     *
     * @return the editore
     */
    public String getEditore() {
        return editore;
    }

    /**
     * Gets autori.
     *
     * @return the autori
     */
    public ArrayList<Autore> getAutori() {
        return autori;
    }
}
