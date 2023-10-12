package DAO;

import java.sql.Date;
import java.sql.ResultSet;

public interface FascicoloDAO {
    public ResultSet getFascicoliDB();
    public ResultSet getFascicoliDB(String user);
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero);
    public boolean creaFascicoloDB(int numero, String data, String issn);
    public void chiudiConnessione(); //chiude la connessione al DB
}
