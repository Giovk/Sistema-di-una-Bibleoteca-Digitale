package DAO;

import java.sql.ResultSet;

public interface PossessoDAO {
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei libri della libreria selezionata gestista dall'utente 'user'
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi delle serie della libreria selezionata gestista dall'utente 'user'
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del libro con ISBN 'isbn'
    public void modQuantitaFascicoloDB(String issn, int numero, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del fascicolo numero 'numero' della rivista con ISSN 'issn'
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del libro con ISBN 'isbn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"
    public boolean insertPossessoFDB(int numero, String issn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del fascicolo numero 'numero' della rivista con ISSN 'issn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"
    public void eliminaPossessoLDB(String isbn, String nt, String fruizione);   //elimina dal DB il possesso del libro con ISBN 'isbn'
    public void eliminaPossessoFDB(String issn, int numero, String nt, String fruizione);   //elimina dal DB il possesso del fascicolo numero 'numero' della rivista con ISSN 'issn'
    public void chiudiConnessione();    //chiude la connessione al DB
}