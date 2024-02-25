package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Conferenza crea nuove istanze di conferenze e fornisce le loro informazioni.
 */
public class Conferenza {
    /**
     * Struttura organizzatrice della conferenza.
     */
    public String struttura;
    /**
     * Luogo della conferenza.
     */
    public String luogo;
    /**
     * Data di inizio della conferenza.
     */
    public Date dataInizio;
    /**
     * Data di fine della conferenza.
     */
    public Date dataFine;
    /**
     * Lista degli Articoli esposti nella conferenza.
     */
    public ArrayList<ArticoloScientifico> articoliEsposti;

    /**
     * Istanzia una nuova Conferenza con i suoi articoli scietifici.
     *
     * @param s  la struttura organizzatrice della conferenza
     * @param l  il luogo della conferenza
     * @param di la data di inizio della conferenza
     * @param df la data di fine della conferenza
     * @param a  la lista di articoli scientifici della conferenza
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
     * Istanzia una nuova Conferenza.
     *
     * @param s  la struttura organizzatrice della conferenza
     * @param l  il luogo della conferenza
     * @param di la data di inizio della conferenza
     * @param df la data di fine della conferenza
     */
    public Conferenza(String s, String l, Date di, Date df) {
        struttura = s;
        luogo = l;
        dataInizio = di;
        dataFine = df;
    }

    /**
     * Ritorna il luogo della Conferenza.
     *
     * @return il luogo della Conferenza
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Ritorna la struttura organizzatrice della conferenza.
     *
     * @return la struttura organizzatrice della conferenza
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Ritorna la data di inizio della conferenza.
     *
     * @return la data inizio della conferenza
     */
    public Date getDataInizio() {
        return dataInizio;
    }

    /**
     * Ritorna la data di fine della conferenza.
     *
     * @return la data fine della conferenza
     */
    public Date getDataFine() {
        return dataFine;
    }
}
