package DAO;

import java.sql.ResultSet;

public interface LibreriaDAO {
    public ResultSet disponibilitaDB(String isbn);  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo);  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie

    public void chiudiConnessione();
}
