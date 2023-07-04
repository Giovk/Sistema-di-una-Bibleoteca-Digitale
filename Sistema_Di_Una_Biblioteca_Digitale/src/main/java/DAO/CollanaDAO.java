package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CollanaDAO {
    public ArrayList<String> getCollanaNomeDB();    //ritorna i nomi di tutte le collane nel DB
    public void chiudiConnessione();    //chiude la connessione al DB
}
