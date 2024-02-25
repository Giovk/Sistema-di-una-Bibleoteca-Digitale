package Model;

import java.util.Date;

/**
 * The type Notifica.
 */
public class Notifica {
    /**
     * The Testo.
     */
    public String testo;
    /**
     * The Libreria.
     */
    public int libreria;
    /**
     * The Data invio.
     */
    public Date dataInvio;
    /**
     * The Ora invio.
     */
    public String oraInvio;
    /**
     * The Lettura.
     */
    public boolean lettura;
    /**
     * The Utente destinatario.
     */
    public Utente utenteDestinatario;
    /**
     * The Serie disponibile.
     */
    public Serie serieDisponibile;

    /**
     * Instantiates a new Notifica.
     *
     * @param t  the t
     * @param l  the l
     * @param di the di
     * @param oi the oi
     * @param le the le
     * @param ud the ud
     * @param sd the sd
     */
    public Notifica(String t, int l, Date di, String oi, boolean le, Utente ud, Serie sd){
        testo = t;
        libreria = l;
        dataInvio = di;
        oraInvio = oi;
        lettura = le;
        utenteDestinatario = ud;
        serieDisponibile = sd;
    }

    /**
     * Gets testo.
     *
     * @return the testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Gets data invio.
     *
     * @return the data invio
     */
    public Date getDataInvio() {
        return dataInvio;
    }

    /**
     * Gets ora invio.
     *
     * @return the ora invio
     */
    public String getOraInvio() {
        return oraInvio;
    }

    /**
     * Sets lettura.
     *
     * @param lettura the lettura
     */
    public void setLettura(boolean lettura) {
        this.lettura = lettura;
    }

    /**
     * Gets lettura.
     *
     * @return the lettura
     */
    public boolean getLettura() {
        return lettura;
    }
}
