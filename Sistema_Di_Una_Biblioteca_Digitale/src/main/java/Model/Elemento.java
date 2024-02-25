package Model;

import java.util.Date;

/**
 * La classe Elemento crea nuove istanze di elementi posseduti dalle librerie e fornisce la loro data di ubblicazione.
 */
public class Elemento {
    /**
     * Data di pubblicazione dell'elemento.
     */
    public Date dataPubblicazione;

    /**
     * Istanzia un nuovo Elemento.
     *
     * @param dp la data di pubblicazione dell'elemento
     */
    public Elemento(Date dp){
        dataPubblicazione = dp;
    }

    /**
     * Ritorna la data di pubblicazione dell'elemento.
     *
     * @return la data di pubblicazione dell'elemento
     */
    public Date getDataPubblicazione()
    {
        return dataPubblicazione;
    }
}
