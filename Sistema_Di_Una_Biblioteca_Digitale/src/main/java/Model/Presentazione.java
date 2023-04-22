package Model;

import java.util.Date;

public class Presentazione {
    public String luogo;
    public Date data;
    public String ora;
    public Libro libro;

    public Presentazione(String l, Date d, String o, Libro li){
        luogo = l;
        data = d;
        ora = o;
        libro = li;
    }
}
