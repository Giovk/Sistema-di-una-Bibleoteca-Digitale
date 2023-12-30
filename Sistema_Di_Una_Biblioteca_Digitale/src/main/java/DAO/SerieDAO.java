package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface SerieDAO {
    public ResultSet getSerieDB();  //cerca i dati di tutte le serie nel DB
    public ArrayList<String> getSerieGenereDB();    //ritorna tutti i generi dei libri che sono inseriti in qualche serie
    public ArrayList<String> getSerieAutoriDB();    //ritorna tutti gli autori dei libri che sono inseriti in qualche serie
    public ResultSet getListaSerieGenereDB(String genere);  //cerca i dati delle serie con libri del genere 'genere'
    public ResultSet getListaSerieAutoreDB(String autore);  //cerca i dati delle serie con libri dell'autore 'autore'
    public ResultSet getInfoSeriePreferitiDB(String isbn);  //cerca i dati della serie con ISBN 'isbn' e delle librerie che lo possiedono
    public boolean creaSerieDB(ArrayList<String> isbnList, String isbn, String titolo, String dp);  //se non esiste già, inserisce una nuova serie nel DB e ritorna "true", altrimenti ritorna "false"
    public void chiudiConnessione();    //chiude la connessione al DB
}