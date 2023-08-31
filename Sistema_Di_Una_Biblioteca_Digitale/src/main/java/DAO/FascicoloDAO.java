package DAO;

import java.sql.ResultSet;

public interface FascicoloDAO {
    public ResultSet getFascicoliDB();
    public void chiudiConnessione(); //chiude la connessione al DB
}
