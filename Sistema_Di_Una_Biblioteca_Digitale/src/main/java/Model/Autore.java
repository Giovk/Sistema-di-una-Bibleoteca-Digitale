package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * La classe Autore crea nuove istanze di autori e fornisce i loro nomi e cognomi
 */
public class Autore {
    /**
     * Nome dell'autore.
     */
    public String nome;
    /**
     * Cognome dell'autore.
     */
    public String cognome;
    /**
     * Nazionalita dell'autore.
     */
    public String nazionalita;
    /**
     * Data di nascita dell'autore.
     */
    public java.sql.Date dataNascita;
    /**
     * lista degli Articoli scritti dall'autore.
     */
    public ArrayList<ArticoloScientifico> articoliScritti;

    /**
     * Istanzia un nuovo autore con la data di nascita.
     *
     * @param no il nome dell'autore che si sta istanziando
     * @param c  il cognome dell'autore che si sta istanziando
     * @param na la nazioanalità dell'autore che si sta istanziando
     * @param dn la data di nascita dell'autore che si sta istanziando
     */
    public Autore(String no, String c, String na, Date dn){
        nome = no;
        cognome = c;
        nazionalita = na;
        dataNascita = dn;
    }

    /**
     * Istanzia un nuovo Autore senza la data di nascita.
     *
     * @param no il nome dell'autore che si sta istanziando
     * @param c  il cognome dell'autore che si sta istanziando
     * @param na la nazioanalità dell'autore che si sta istanziando
     */
    public Autore(String no, String c, String na){
        nome = no;
        cognome = c;
        nazionalita = na;
    }

    /**
     * Ritorna il nome dell'autore.
     *
     * @return il nome dell'autore
     */
    public String getNome() {
        return nome;
    }

    /**
     * Ritorna il cognome dell'autore.
     *
     * @return il cognome dell'autore
     */
    public String getCognome() {
        return cognome;
    }
}
