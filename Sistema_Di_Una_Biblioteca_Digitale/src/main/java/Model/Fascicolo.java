package Model;

import java.util.ArrayList;
import java.util.Date;

public class Fascicolo extends Elemento{
    public int numero;
    public Rivista r;
    public ArrayList<ArticoloScientifico> articoli;

    public Fascicolo(int n, Rivista rivista, ArrayList<ArticoloScientifico> a, String t, Date dp){
        super(t, dp);
        numero = n;
        r = rivista;

        for (ArticoloScientifico articoloAttuale : a) {
            articoli.add(articoloAttuale);
        }

        rivista.fascicoli.add(this);
    }
}
