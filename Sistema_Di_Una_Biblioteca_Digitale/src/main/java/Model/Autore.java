package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Autore {
    public String nome;
    public String cognome;
    public String nazionalita;
    public java.sql.Date dataNascita;
    public ArrayList<ArticoloScientifico> articoliScritti;

    public Autore(String no, String c, String na, Date dn){
        nome = no;
        cognome = c;
        nazionalita = na;
        dataNascita = dn;
    }

    public Autore(String no, String c, String na){
        nome = no;
        cognome = c;
        nazionalita = na;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}
