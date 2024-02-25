package Model;

import java.util.ArrayList;

/**
 * The type Rivista.
 */
public class Rivista {
    /**
     * The Issn.
     */
    public String issn;
    /**
     * The Titolo.
     */
    public String titolo;
    /**
     * The Editore.
     */
    public String editore;
    /**
     * The Anno pubblicazione.
     */
    public int annoPubblicazione;
    /**
     * The Responsabile.
     */
    public String responsabile;
    /**
     * The Argomento.
     */
    public String argomento;
    /**
     * The Fascicoli.
     */
    public ArrayList<Fascicolo> fascicoli;

    /**
     * Instantiates a new Rivista.
     *
     * @param i  the
     * @param t  the t
     * @param e  the e
     * @param ap the ap
     * @param r  the r
     * @param a  the a
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
     * Gets titolo.
     *
     * @return the titolo
     */
    public String getTitolo()
    {
        return titolo;
    }

    /**
     * Gets issn.
     *
     * @return the issn
     */
    public String getISSN()
    {
        return issn;
    }

    /**
     * Gets argomento.
     *
     * @return the argomento
     */
    public String getArgomento()
    {
        return argomento;
    }

    /**
     * Gets responsabile.
     *
     * @return the responsabile
     */
    public String getResponsabile()
    {
        return responsabile;
    }

    /**
     * Gets editore.
     *
     * @return the editore
     */
    public String getEditore()
    {
        return editore;
    }

    /**
     * Gets anno pubblicazione.
     *
     * @return the anno pubblicazione
     */
    public int getAnnoPubblicazione()
    {
        return annoPubblicazione;
    }
}
