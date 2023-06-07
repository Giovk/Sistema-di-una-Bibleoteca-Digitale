package DAO;

import java.sql.ResultSet;

public interface LibroDAO {
    public ResultSet getLibriDB();
    public void chiudiConnessione(); //chiude la connessione al DB
}
