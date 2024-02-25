package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * The type Autore.
 */
public class Autore {
    /**
     * The Nome.
     */
    public String nome;
    /**
     * The Cognome.
     */
    public String cognome;
    /**
     * The Nazionalita.
     */
    public String nazionalita;
    /**
     * The Data nascita.
     */
    public java.sql.Date dataNascita;
    /**
     * The Articoli scritti.
     */
    public ArrayList<ArticoloScientifico> articoliScritti;

    /**
     * Instantiates a new Autore.
     *
     * @param no the no
     * @param c  the c
     * @param na the na
     * @param dn the dn
     */
    public Autore(String no, String c, String na, Date dn){
        nome = no;
        cognome = c;
        nazionalita = na;
        dataNascita = dn;
    }

    /**
     * Instantiates a new Autore.
     *
     * @param no the no
     * @param c  the c
     * @param na the na
     */
    public Autore(String no, String c, String na){
        nome = no;
        cognome = c;
        nazionalita = na;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }
}
