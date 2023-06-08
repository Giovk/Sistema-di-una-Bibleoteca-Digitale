package DAO;

import java.sql.ResultSet;

public interface LibroDAO {
    public ResultSet getLibriDB();  //ritorna i dati di tutti i libri nel DB
    public void chiudiConnessione(); //chiude la connessione al DB
}
