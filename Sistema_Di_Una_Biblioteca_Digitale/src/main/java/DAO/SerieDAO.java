package DAO;

import java.sql.ResultSet;

public interface SerieDAO {
    public ResultSet getSerieDB();  //ritorna i dati di tutte le serie nel DB
    public void chiudiConnessione();    //chiude la connessione al DB
}
