package DAO;

import java.sql.ResultSet;

public interface LibreriaDAO {
    public ResultSet disponibilitaDB(String isbn);
    public void chiudiConnessione();
}
