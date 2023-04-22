package Model;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Elemento{
    public String isbn;
    public int nLibri;
    public ArrayList<Libro> libri;

    public Serie(String i, int nl, ArrayList<Libro> l, String t, Date dp){
        super(t, dp);

        isbn = i;
        nLibri = nl;

        for (Libro libroAttuale : l){
            libri.add(libroAttuale);
        }
    }
}
