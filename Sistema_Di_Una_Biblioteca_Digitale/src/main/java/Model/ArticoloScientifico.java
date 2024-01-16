package Model;

import java.util.ArrayList;

public class ArticoloScientifico {
    public String doi;
    public String titolo;
    public int annoPubblicazione;
    public ArrayList<Autore> autori = new ArrayList<>();
    public ArrayList<Conferenza> conferenze;

    public ArticoloScientifico(String d, String t, int ap, ArrayList<Autore> a){
        doi = d;
        titolo = t;
        annoPubblicazione = ap;

        for (Autore autoreAttuale : a){
            autori.add(autoreAttuale);

            if(autoreAttuale.articoliScritti == null) {
                autoreAttuale.articoliScritti = new ArrayList<>();
            }

            autoreAttuale.articoliScritti.add(this);
        }
    }

    public ArticoloScientifico(String d, String t, int ap){
        doi = d;
        titolo = t;
        annoPubblicazione = ap;
    }

    public String getTitolo(){
        return titolo;
    }

    public String getDoi() {
        return doi;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public ArrayList<Autore> getAutori() {
        return autori;
    }
}