package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Conferenza.
 */
public class Conferenza {
    /**
     * The Struttura.
     */
    public String struttura;
    /**
     * The Luogo.
     */
    public String luogo;
    /**
     * The Data inizio.
     */
    public Date dataInizio;
    /**
     * The Data fine.
     */
    public Date dataFine;
    /**
     * The Articoli esposti.
     */
    public ArrayList<ArticoloScientifico> articoliEsposti;

    /**
     * Instantiates a new Conferenza.
     *
     * @param s  the s
     * @param l  the l
     * @param di the di
     * @param df the df
     * @param a  the a
     */
    public Conferenza(String s, String l, Date di, Date df, ArrayList<ArticoloScientifico> a) {
        struttura = s;
        luogo = l;
        dataInizio = di;
        dataFine = df;

        for (ArticoloScientifico articoloAttuale : a){
            articoliEsposti.add(articoloAttuale);
            articoloAttuale.conferenze.add(this);
        }
    }

    /**
     * Instantiates a new Conferenza.
     *
     * @param s  the s
     * @param l  the l
     * @param di the di
     * @param df the df
     */
    public Conferenza(String s, String l, Date di, Date df) {
        struttura = s;
        luogo = l;
        dataInizio = di;
        dataFine = df;
    }

    /**
     * Gets luogo.
     *
     * @return the luogo
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Gets struttura.
     *
     * @return the struttura
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Gets data inizio.
     *
     * @return the data inizio
     */
    public Date getDataInizio() {
        return dataInizio;
    }

    /**
     * Gets data fine.
     *
     * @return the data fine
     */
    public Date getDataFine() {
        return dataFine;
    }
}
