package Model;

import java.util.ArrayList;

/**
 * La classe Collana crea nuove istanze di collane di libri e fornisce i loro nomi e i libri di cui sono formate.
 */
public class Collana {
    /**
     * Caratteristica della collana.
     */
    public String caratteristica;
    /**
     * Nome della collana.
     */
    public String nome;
    /**
     * ISSN della collana.
     */
    public String issn;
    /**
     * Lista dei Libri che formano la collana.
     */
    public ArrayList<Libro> libri = new ArrayList<>();

    /**
     * Istanzia una nuova Collana.
     *
     * @param c la caratteristica della collana di libri
     * @param n il nome della collana di libri
     * @param i l'ISSN della collana di libri
     * @param l lista dei libri della collana di libri
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
     * Ritorna tutti i libri della collana.
     *
     * @return la lista dei libri della collana
     */
    public ArrayList<Libro> getLibri() {
        return libri;
    }

    /**
     * Ritorna il nome della collana.
     *
     * @return il nome della collana
     */
    public String getNome() {
        return nome;
    }
}
