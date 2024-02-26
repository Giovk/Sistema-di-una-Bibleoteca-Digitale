package Model;

import java.util.ArrayList;

/**
 * La classe Rivista crea nuove istanze di riviste e fornisce le loro informazioni.
 */
public class Rivista {
    /**
     * ISSN della rivista.
     */
    public String issn;
    /**
     * Titolo della rivista.
     */
    public String titolo;
    /**
     * Editore della rivista.
     */
    public String editore;
    /**
     * Anno di pubblicazione della rivista.
     */
    public int annoPubblicazione;
    /**
     * Responsabile della rivista.
     */
    public String responsabile;
    /**
     * Argomento della rivista.
     */
    public String argomento;
    /**
     * Lista dei Fascicoli che formano la rivista.
     */
    public ArrayList<Fascicolo> fascicoli;

    /**
     * Istanzia una nuova Rivista.
     *
     * @param i  l'ISSN della rivista
     * @param t  il titolo della rivista
     * @param e  l'editore della rivista
     * @param ap l'anno di pubblicazione della rivista
     * @param r  il responsabile della rivista
     * @param a  l'argomento della rivista
     */
    public Rivista(String i, String t, String e, int ap, String r, String a) {
        issn = i;
        titolo = t;
        editore = e;
        annoPubblicazione = ap;
        responsabile = r;
        argomento = a;
    }

    /**
     * Ritorna il titolo della rivista.
     *
     * @return il titolo della rivista
     */
    public String getTitolo()
    {
        return titolo;
    }

    /**
     * Ritorna l'ISSN della rivista.
     *
     * @return l'ISSN della rivista
     */
    public String getISSN()
    {
        return issn;
    }

    /**
     * Ritorna l'argomento della rivista.
     *
     * @return l'argomento della rivista
     */
    public String getArgomento()
    {
        return argomento;
    }

    /**
     * Ritorna il responsabile della rivista.
     *
     * @return il responsabile della rivista
     */
    public String getResponsabile()
    {
        return responsabile;
    }

    /**
     * Ritorna l'editore della rivista.
     *
     * @return l'editore della rivista
     */
    public String getEditore()
    {
        return editore;
    }

    /**
     * Ritorna l'anno di pubblicazione della rivista.
     *
     * @return l'anno di pubblicazione della rivista
     */
    public int getAnnoPubblicazione()
    {
        return annoPubblicazione;
    }
}