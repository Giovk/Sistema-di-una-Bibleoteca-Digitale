package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AutoreDAO {
    //public ResultSet getAutoriLibroDB();    //ritorna i dati di tutti gli autori di libri nel DB
    public ResultSet getAutoriDB(String isbn);  //ritorna i dati di tutti gli autori nel DB dell libro con ISBN 'isbn'
    public void chiudiConnessione();    //chiude la connessione al DB
}
