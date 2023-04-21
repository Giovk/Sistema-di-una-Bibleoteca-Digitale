package Model;

import java.util.ArrayList;

public class ArticoloScientifico {
    public String doi;
    public String titolo;
    public int annoPubblicazione;
    public ArrayList<Autore> autori;
    public ArrayList<Conferenza> conferenze;

    public ArticoloScientifico(String d, String t, int ap, ArrayList<Autore> a){
        doi = d;
        titolo = t;
        annoPubblicazione = ap;

        for (Autore autoreAttuale : a){
            autori.add(autoreAttuale);
            autoreAttuale.articoliScritti.add(this);
        }
    }
}
