package Model;

import java.util.Date;

public class Presentazione {
    public String luogo;
    public Date data;
    public String ora;
    public Libro libro;
    public String struttura;

    public Presentazione(String l, String s, Date d, String o, Libro li){
        luogo = l;
        struttura = s;
        data = d;
        ora = o;
        libro = li;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getStruttura() {
        return struttura;
    }

    public Date getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }
}
