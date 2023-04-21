package Model;

import java.util.ArrayList;
import java.util.Date;

public class Conferenza {
    public String luogo;
    public Date dataInizio;
    public Date dataFine;
    public ArrayList<ArticoloScientifico> articoliEsposti;

    public Conferenza(String l, Date di, Date df, ArrayList<ArticoloScientifico> a) {
        luogo = l;
        dataInizio = di;
        dataFine = df;
        for (ArticoloScientifico articoloAttuale : a){
            articoliEsposti.add(articoloAttuale);
            articoloAttuale.conferenze.add(this);
        }
    }
}
