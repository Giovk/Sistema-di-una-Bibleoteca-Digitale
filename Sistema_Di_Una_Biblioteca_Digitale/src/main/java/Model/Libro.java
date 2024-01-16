package Model;

import java.util.ArrayList;
import java.util.Date;

public class Libro extends Elemento {
    public String isbn;
    public String genere;
    public String editore;
    public String lingua;
    public ArrayList<Autore> autori = new ArrayList<Autore>();
    public String titolo;

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

    public Libro(String i, String g, String e, String l, String t, Date dp) {
        super(dp);

        titolo = t;
        isbn = i;
        genere = g;
        editore = e;
        lingua = l;
    }

    public String getISBN()
    {
        return isbn;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public String getGenere()
    {
        return genere;
    }

    public String getLingua()
    {
        return lingua;
    }

    public String getEditore() {
        return editore;
    }

    public ArrayList<Autore> getAutori() {
        return autori;
    }
}
