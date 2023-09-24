package Model;

import java.util.ArrayList;
import java.util.Date;

public class Fascicolo extends Elemento{
    public int numero;
    public Rivista rivista;
    public ArrayList<ArticoloScientifico> articoli;

    public Fascicolo(int n, Rivista r, ArrayList<ArticoloScientifico> a, Date dp){
        super(dp);
        numero = n;
        rivista = r;

        for (ArticoloScientifico articoloAttuale : a) {
            if(articoli == null) articoli = new ArrayList<>();
            articoli.add(articoloAttuale);
        }

        if (rivista.fascicoli == null) rivista.fascicoli = new ArrayList<>();
        rivista.fascicoli.add(this);
    }

    public Fascicolo(int n, Rivista r, Date dp){
        super(dp);
        numero = n;
        rivista = r;


        if (rivista.fascicoli == null) rivista.fascicoli = new ArrayList<>();
        rivista.fascicoli.add(this);
    }
}
