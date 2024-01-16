package Model;

import java.util.Date;

public class Elemento {
    public Date dataPubblicazione;

    public Elemento(Date dp){
        dataPubblicazione = dp;
    }

    public Date getDataPubblicazione()
    {
        return dataPubblicazione;
    }
}
