package DAO;

import java.sql.ResultSet;

public interface PossessoDAO {
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user);
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user);
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user);
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value);
    public void modQuantitaFascicoloDB(String issn, int numero, String nt, String fruizione, int value);
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione);
    public void chiudiConnessione();    //chiude la connessione al DB
}
