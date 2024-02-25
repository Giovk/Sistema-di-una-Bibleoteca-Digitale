package Model;

import java.util.Date;

/**
 * The type Elemento.
 */
public class Elemento {
    /**
     * The Data pubblicazione.
     */
    public Date dataPubblicazione;

    /**
     * Instantiates a new Elemento.
     *
     * @param dp the dp
     */
    public Elemento(Date dp){
        dataPubblicazione = dp;
    }

    /**
     * Gets data pubblicazione.
     *
     * @return the data pubblicazione
     */
    public Date getDataPubblicazione()
    {
        return dataPubblicazione;
    }
}
