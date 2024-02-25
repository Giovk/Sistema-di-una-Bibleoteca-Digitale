package Model;

import java.util.ArrayList;

/**
 * The type Articolo scientifico.
 */
public class ArticoloScientifico {
    /**
     * The Doi.
     */
    public String doi;
    /**
     * The Titolo.
     */
    public String titolo;
    /**
     * The Anno pubblicazione.
     */
    public int annoPubblicazione;
    /**
     * The Autori.
     */
    public ArrayList<Autore> autori = new ArrayList<>();
    /**
     * The Conferenze.
     */
    public ArrayList<Conferenza> conferenze;

    /**
     * Instantiates a new Articolo scientifico.
     *
     * @param d  the d
     * @param t  the t
     * @param ap the ap
     * @param a  the a
     */
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

    /**
     * Instantiates a new Articolo scientifico.
     *
     * @param d  the d
     * @param t  the t
     * @param ap the ap
     */
    public ArticoloScientifico(String d, String t, int ap){
        doi = d;
        titolo = t;
        annoPubblicazione = ap;
    }

    /**
     * Get titolo string.
     *
     * @return the string
     */
    public String getTitolo(){
        return titolo;
    }

    /**
     * Gets doi.
     *
     * @return the doi
     */
    public String getDoi() {
        return doi;
    }

    /**
     * Gets anno pubblicazione.
     *
     * @return the anno pubblicazione
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * Gets autori.
     *
     * @return the autori
     */
    public ArrayList<Autore> getAutori() {
        return autori;
    }
}