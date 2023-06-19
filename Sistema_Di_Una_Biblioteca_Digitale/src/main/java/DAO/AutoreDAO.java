package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AutoreDAO {
    public ResultSet getAutoriLibroDB();    //ritorna i dati di tutti gli autori di libri nel DB
    public ResultSet getAutoriDB(String isbn);
    public void chiudiConnessione();    //chiude la connessione al DB
}
