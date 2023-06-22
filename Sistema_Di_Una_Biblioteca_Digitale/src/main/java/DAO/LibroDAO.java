package DAO;

import java.sql.ResultSet;

public interface LibroDAO {
    public ResultSet getLibriDB();  //ritorna i dati di tutti i libri nel DB
    public ResultSet getLibriDB(String collana);  //ritorna i dati di tutti i libri nel DB
    public ResultSet getLibroAutoriDB(String s);    //ritorna i dati del libro con ISBN 's'
    public void chiudiConnessione(); //chiude la connessione al DB
}
