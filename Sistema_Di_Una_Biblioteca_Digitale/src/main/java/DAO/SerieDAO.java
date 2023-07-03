package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface SerieDAO {
    public ResultSet getSerieDB();  //ritorna i dati di tutte le serie nel DB
    public ArrayList<String> getSerieGenereDB();
    public ArrayList<String> getSerieAutoriDB();
    public void chiudiConnessione();    //chiude la connessione al DB
}
