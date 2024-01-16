package Model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Elemento{
    public String isbn;
    public String titolo;
    public int nLibri;
    public ArrayList<Libro> libri = new ArrayList<>();

    public Serie(String i, int nl, ArrayList<Libro> l, String t, Date dp){
        super(dp);
        titolo = t;
        isbn = i;
        nLibri = nl;

        for (Libro libroAttuale : l){
            libri.add(libroAttuale);
        }
    }

    public Serie(String i, int nl, String t, Date dp){
        super(dp);
        titolo = t;
        isbn = i;
        nLibri = nl;
    }

    public String getISBN() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getNLibri() {
        return nLibri;
    }
}