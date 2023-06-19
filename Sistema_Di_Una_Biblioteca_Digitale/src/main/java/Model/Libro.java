package Model;

import java.util.ArrayList;
import java.util.Date;

public class Libro extends Elemento{
    public String isbn;
    public String genere;
    public String editore;
    public String lingua;
    public ArrayList<Autore> autori = new ArrayList<Autore>();

    public Libro(String i, String g, String e, String l, ArrayList<Autore> a, String t, Date dp){
        super(t, dp);

        isbn = i;
        genere = g;
        editore = e;
        lingua = l;


        for (Autore autoreAttuale : a){
            autori.add(autoreAttuale);
            //autoreAttuale.libriScritti.add(this);
        }
    }

    public Libro(String i, String g, String e, String l, String t, Date dp){
        super(t, dp);

        isbn = i;
        genere = g;
        editore = e;
        lingua = l;
    }
}
