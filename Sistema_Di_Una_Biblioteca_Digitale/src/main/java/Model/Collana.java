package Model;

import java.util.ArrayList;

/**
 * The type Collana.
 */
public class Collana {
    /**
     * The Caratteristica.
     */
    public String caratteristica;
    /**
     * The Nome.
     */
    public String nome;
    /**
     * The Issn.
     */
    public String issn;
    /**
     * The Libri.
     */
    public ArrayList<Libro> libri = new ArrayList<>();

    /**
     * Instantiates a new Collana.
     *
     * @param c the c
     * @param n the n
     * @param i the
     * @param l the l
     */
    public Collana(String c, String n, String i, ArrayList<Libro> l){
        caratteristica = c;
        nome = n;
        issn = i;

        for (Libro libroAttuale : l){
            libri.add(libroAttuale);
        }
    }

    /**
     * Gets libri.
     *
     * @return the libri
     */
    public ArrayList<Libro> getLibri() {
        return libri;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
}
