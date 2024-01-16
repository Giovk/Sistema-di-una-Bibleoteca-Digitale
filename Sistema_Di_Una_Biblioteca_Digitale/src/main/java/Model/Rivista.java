package Model;

import java.util.ArrayList;

public class Rivista {
    public String issn;
    public String titolo;
    public String editore;
    public int annoPubblicazione;
    public String responsabile;
    public String argomento;
    public ArrayList<Fascicolo> fascicoli;

    public Rivista(String i, String t, String e, int ap, String r, String a) {
        issn = i;
        titolo = t;
        editore = e;
        annoPubblicazione = ap;
        responsabile = r;
        argomento = a;
    }

    public String getTitolo()
    {
        return titolo;
    }

    public String getISSN()
    {
        return issn;
    }

    public String getArgomento()
    {
        return argomento;
    }

    public String getResponsabile()
    {
        return responsabile;
    }

    public String getEditore()
    {
        return editore;
    }

    public int getAnnoPubblicazione()
    {
        return annoPubblicazione;
    }
}
