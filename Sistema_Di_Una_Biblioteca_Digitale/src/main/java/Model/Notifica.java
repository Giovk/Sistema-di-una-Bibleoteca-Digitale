package Model;

import java.util.Date;

public class Notifica {
    public String testo;
    public int libreria;
    public Date dataInvio;
    public String oraInvio;
    public boolean lettura;
    public Utente utenteDestinatario;
    public Serie serieDisponibile;

    public Notifica(String t, int l, Date di, String oi, boolean le, Utente ud, Serie sd){
        testo = t;
        libreria = l;
        dataInvio = di;
        oraInvio = oi;
        lettura = le;
        utenteDestinatario = ud;
        serieDisponibile = sd;
    }
}
