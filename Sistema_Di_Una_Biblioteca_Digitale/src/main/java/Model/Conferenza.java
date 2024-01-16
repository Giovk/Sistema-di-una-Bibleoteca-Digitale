package Model;

import java.util.ArrayList;
import java.util.Date;

public class Conferenza {
    public String struttura;
    public String luogo;
    public Date dataInizio;
    public Date dataFine;
    public ArrayList<ArticoloScientifico> articoliEsposti;

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

    public Conferenza(String s, String l, Date di, Date df) {
        struttura = s;
        luogo = l;
        dataInizio = di;
        dataFine = df;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getStruttura() {
        return struttura;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }
}
