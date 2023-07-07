package DAO;

import Model.Serie;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface SerieDAO {
    public ResultSet getSerieDB();  //ritorna i dati di tutte le serie nel DB
    public ArrayList<String> getSerieGenereDB();    //ritorna tutti i generi dei libri che sono inseriti in una serie
    public ArrayList<String> getSerieAutoriDB();    //ritorna tutti gli autori dei libri che sono inseriti in una serie
    public ResultSet getListaSerieGenereDB(String genere);
    public ResultSet getListaSerieAutoreDB(String autore);

    public void chiudiConnessione();    //chiude la connessione al DB
}
