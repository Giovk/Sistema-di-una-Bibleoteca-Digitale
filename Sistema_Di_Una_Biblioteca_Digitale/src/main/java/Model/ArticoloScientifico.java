package Model;

import java.util.ArrayList;

/**
 * La classe ArticoloScientifico crea nuove istanze di articoli scientifici e fornisce le loro informazioni.
 */
public class ArticoloScientifico {
    /**
     *  DOI identificativo dell'articolo scientifico.
     */
    public String doi;
    /**
     * Titolo dell'articolo scientifico.
     */
    public String titolo;
    /**
     * Anno di pubblicazione dell'articolo scientifico.
     */
    public int annoPubblicazione;
    /**
     * Lista degli Autori dell'articolo scientifico.
     */
    public ArrayList<Autore> autori = new ArrayList<>();
    /**
     * Lista delle Conferenze nelle quali è esposto l'articolo .
     */
    public ArrayList<Conferenza> conferenze;

    /**
     * Istanzia un nuovo ArticoloScientifico con i suoi autori.
     *
     * @param d  il DOI dell'articolo scientifico che si sta istanziando
     * @param t  il titolo dell'articolo scientifico che si sta istanziando
     * @param ap l'anno di pubblicazione dell'articolo scientifico che si sta istanziando
     * @param a  la lista di autori dell'articolo scientifico che si sta istanziando
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
     * Istanzia un nuovo Articolo scientifico.
     *
     * @param d  il DOI dell'articolo scientifico che si sta istanziando
     * @param t  il titolo dell'articolo scientifico che si sta istanziando
     * @param ap l'anno di pubblicazione dell'articolo scientifico che si sta istanziando
     */
    public ArticoloScientifico(String d, String t, int ap){
        doi = d;
        titolo = t;
        annoPubblicazione = ap;
    }

    /**
     * Ritorna una stringa contenente il titolo dell'articolo scientifico.
     *
     * @return la stringa con il titolo dell'articolo scientifico
     */
    public String getTitolo(){
        return titolo;
    }

    /**
     * Ritorna il DOI dell'articolo scientifico.
     *
     * @return il DOI dell'articolo scientifico
     */
    public String getDoi() {
        return doi;
    }

    /**
     * Ritorna l'anno di pubblicazione dell'articolo scientifico.
     *
     * @return l'anno di pubblicazione dell'articolo scientifico
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * ritorna tutti gli autori dell'articolo scientifico.
     *
     * @return la lista degli autori dell'articolo scientifico
     */
    public ArrayList<Autore> getAutori() {
        return autori;
    }
}