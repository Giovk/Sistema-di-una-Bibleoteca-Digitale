package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Fascicolo.
 */
public class Fascicolo extends Elemento{
    /**
     * The Numero.
     */
    public int numero;
    /**
     * The Rivista.
     */
    public Rivista rivista;
    /**
     * The Articoli.
     */
    public ArrayList<ArticoloScientifico> articoli;

    /**
     * Instantiates a new Fascicolo.
     *
     * @param n  the n
     * @param r  the r
     * @param a  the a
     * @param dp the dp
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
     * Instantiates a new Fascicolo.
     *
     * @param n  the n
     * @param r  the r
     * @param dp the dp
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
     * Gets rivista.
     *
     * @return the rivista
     */
    public Rivista getRivista() {
        return rivista;
    }

    /**
     * Get numero int.
     *
     * @return the int
     */
    public int getNumero(){
        return numero;
    }

    /**
     * Get articoli array list.
     *
     * @return the array list
     */
    public ArrayList<ArticoloScientifico> getArticoli(){
        return articoli;
    }

    /**
     * Sets articoli.
     *
     * @param articoli the articoli
     */
    public void setArticoli(ArrayList<ArticoloScientifico> articoli) {
        this.articoli = articoli;
    }
}
