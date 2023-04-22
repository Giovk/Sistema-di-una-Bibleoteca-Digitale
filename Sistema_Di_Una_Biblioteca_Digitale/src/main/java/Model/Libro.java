package Model;

import java.util.ArrayList;
import java.util.Date;

public class Libro extends Elemento{
    public String isbn;
    public String genere;
    public String editore;
    public String libro;
    public ArrayList<Autore> autori;

    public Libro(String i, String g, String e, String l, ArrayList<Autore> a, String t, Date dp){
        super(t, dp);

        isbn = i;
        genere = g;
        editore = e;
        libro = l;

        for (Autore autoreAttuale : a){
            autori.add(autoreAttuale);
            autoreAttuale.libriScritti.add(this);
        }
    }
}
