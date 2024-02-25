package Model;

import java.util.Date;

/**
 * La classe Notifica crea nuove istanze di notifiche, fornisce le loro informazioni e segnala se una notifica è stata letta (o non è stata letta).
 */
public class Notifica {
    /**
     * Testo della notifica.
     */
    public String testo;
    /**
     * Libreria della notifica.
     */
    public int libreria;
    /**
     * Data di invio della notifica.
     */
    public Date dataInvio;
    /**
     * Ora di invio della notifica.
     */
    public String oraInvio;
    /**
     * Flag che segnala se la notifica è stata letta.
     */
    public boolean lettura;
    /**
     * Utente destinatario della notifica.
     */
    public Utente utenteDestinatario;
    /**
     * Serie disponibile della notifica.
     */
    public Serie serieDisponibile;

    /**
     * Istanzia una nuova Notifica.
     *
     * @param t  il testo della notifica
     * @param l  il codice identificativo della libreria della notifica
     * @param di la data di invio della notifica
     * @param oi l'ora di invio della notifica
     * @param le la flag di lettura della notifica
     * @param ud l'utente destinatario della notifica
     * @param sd la serie disponibile della notifica
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
     * Ritorna il testo della notifica.
     *
     * @return il testo della notifica
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Ritorna la data di invio della notifica.
     *
     * @return la data di invio della notifica
     */
    public Date getDataInvio() {
        return dataInvio;
    }

    /**
     * Ritorna l'ora di invio della notifica.
     *
     * @return l'ora di invio della notifica
     */
    public String getOraInvio() {
        return oraInvio;
    }

    /**
     * Pone il flag di lettura allo stessoo valore passato come parametro.
     *
     * @param lettura il nuovo valore da assegnare al flag di lettura dalla notifica
     */
    public void setLettura(boolean lettura) {
        this.lettura = lettura;
    }

    /**
     * Ritorna il valore del flag di lettura della notifica.
     *
     * @return il flag di lettura della notifica
     */
    public boolean getLettura() {
        return lettura;
    }
}
