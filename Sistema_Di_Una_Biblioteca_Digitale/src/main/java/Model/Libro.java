package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Libro crea nuove istanze di libri e fornisce le loro informazioni.
 */
public class Libro extends Elemento {
    /**
     * ISBN del libro.
     */
    public String isbn;
    /**
     * Genere del libro.
     */
    public String genere;
    /**
     * Editore del libro.
     */
    public String editore;
    /**
     * Lingua del libro.
     */
    public String lingua;
    /**
     * Lista degli Autori del libto.
     */
    public ArrayList<Autore> autori = new ArrayList<Autore>();
    /**
     * Titolo del libro.
     */
    public String titolo;

    /**
     * Istanzia un nuovo Libro con i suoi autori.
     *
     * @param i  l'ISBN del libro
     * @param g  il genere del libro
     * @param e  l'editore del libro
     * @param l  la lingua del libro
     * @param a  la lista degli autori del libro
     * @param t  il titolo del libro
     * @param dp la data di pubblicazione del libro
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
     * Istanzia un nuovo Libro.
     *
     * @param i l'ISBN del libro
     * @param g il genere del libro
     * @param e l'editore del libro
     * @param l la lingua del libro
     * @param t il titolo del libro
     * @param dp la data di pubblicazione del libro
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
     * Ritorna l'ISBN del libro.
     *
     * @return l'ISBN del libro
     */
    public String getISBN()
    {
        return isbn;
    }

    /**
     * Ritorna il titolo del libro.
     *
     * @return il titolo del libro
     */
    public String getTitolo()
    {
        return titolo;
    }

    /**
     * Ritorna il genere del libro.
     *
     * @return il genere del libro
     */
    public String getGenere()
    {
        return genere;
    }

    /**
     * Ritorna la lingua del libro.
     *
     * @return la lingua del libro
     */
    public String getLingua()
    {
        return lingua;
    }

    /**
     * Ritorna l'editore del libro.
     *
     * @return l'editore del libro
     */
    public String getEditore() {
        return editore;
    }

    /**
     * ritorna tutti gli autori del libro.
     *
     * @return la lista degli autori del libro
     */
    public ArrayList<Autore> getAutori() {
        return autori;
    }
}
