package Model;

import java.util.ArrayList;

public class Collana {
    public String caratteristica;
    public String nome;
    public String isbn;
    public ArrayList<Libro> libri = new ArrayList<>();

    public Collana(String c, String n, String i, ArrayList<Libro> l){
        caratteristica = c;
        nome = n;
        isbn = i;

        for (Libro libroAttuale : l){
            libri.add(libroAttuale);
        }
    }
}
