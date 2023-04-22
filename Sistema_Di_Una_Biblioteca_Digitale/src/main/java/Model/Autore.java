package Model;

import java.util.ArrayList;
import java.util.Date;

public class Autore {
    public String nome;
    public String cognome;
    public String nazionalita;
    public Date dataNascita;
    public ArrayList<ArticoloScientifico> articoliScritti;
    public ArrayList<Libro> libriScritti;

    public Autore(String no, String c, String na, Date dn){
        nome = no;
        cognome = c;
        nazionalita = na;
        dataNascita = dn;
    }
}
