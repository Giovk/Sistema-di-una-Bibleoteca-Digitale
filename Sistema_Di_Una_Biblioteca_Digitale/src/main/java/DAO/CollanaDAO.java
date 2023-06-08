package DAO;

import java.sql.ResultSet;

public interface CollanaDAO {
    public ResultSet getCollanaDB();    //ritorna i dati di tutte le collane nel DB
    public void chiudiConnessione();    //chiude la connessione al DB
}
