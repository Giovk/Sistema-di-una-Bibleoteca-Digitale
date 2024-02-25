package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Fasciclolo crea nuove istanze di fascicoli, fornisce le loro informazioni e gestisce gli articoli scientifici dei quali sono formati.
 */
public class Fascicolo extends Elemento{
    /**
     * Numero del fascicolo.
     */
    public int numero;
    /**
     * Rivista del fascicolo.
     */
    public Rivista rivista;
    /**
     * Lista degli Articoli che formano il fascicolo.
     */
    public ArrayList<ArticoloScientifico> articoli;

    /**
     * Istanzia un nuovo Fascicolo con i suoi articoli.
     *
     * @param n  il numero del fascicolo
     * @param r  la rivista del fascicolo
     * @param a  la lista degli articolo scientifici del fascicolo
     * @param dp la data di pubblicazione del fascicolo
     */
    public Fascicolo(int n, Rivista r, ArrayList<ArticoloScientifico> a, Date dp){
        super(dp);
        numero = n;
        rivista = r;

        for (ArticoloScientifico articoloAttuale : a) {
            if(articoli == null){
                articoli = new ArrayList<>();
            }

            articoli.add(articoloAttuale);
        }

        if (rivista.fascicoli == null){
            rivista.fascicoli = new ArrayList<>();
        }

        rivista.fascicoli.add(this);
    }

    /**
     * Istanzia un nuovo Fascicolo.
     *
     * @param n  il numero del fascicolo
     * @param r  la rivista del fascicolo
     * @param dp la data di pubblicazione del fascicolo
     */
    public Fascicolo(int n, Rivista r, Date dp){
        super(dp);
        numero = n;
        rivista = r;


        if (rivista.fascicoli == null){
            rivista.fascicoli = new ArrayList<>();
        }

        rivista.fascicoli.add(this);
    }

    /**
     * Ritorna la rivista del fascicolo.
     *
     * @return la rivista del fascicolo
     */
    public Rivista getRivista() {
        return rivista;
    }

    /**
     * Ritorna il numero del fascicolo.
     *
     * @return il numero del fascicolo
     */
    public int getNumero(){
        return numero;
    }

    /**
     * Ritorna gli articoli del fascicolo.
     *
     * @return l'array list contenente gli articoli scientific del fascicolo
     */
    public ArrayList<ArticoloScientifico> getArticoli(){
        return articoli;
    }

    /**
     * Poone la lista degli articoli scientifici del fascicolo uguale a quella presa come parametro.
     *
     * @param articoli la nuova lista degli articoli
     */
    public void setArticoli(ArrayList<ArticoloScientifico> articoli) {
        this.articoli = articoli;
    }
}