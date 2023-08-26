package DAO;

import java.sql.ResultSet;

public interface RivistaDAO {
    public ResultSet getRivisteDB();  //ritorna i dati di tutti le riviste nel DB
    public void chiudiConnessione(); //chiude la connessione al DB
}
