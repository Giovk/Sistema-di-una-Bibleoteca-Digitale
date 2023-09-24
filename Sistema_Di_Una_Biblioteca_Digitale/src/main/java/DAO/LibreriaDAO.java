package DAO;

import java.sql.ResultSet;

public interface LibreriaDAO {
    public ResultSet disponibilitaDB(String isbn);  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo);  //ritorna le disponibilità del libro con ISBN 'isbn' nelle librerie
    public ResultSet getLibrerieUtenteDB(String user);
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt);
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo);
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user);
    public void removeLibreriaDB(String nt);
    public void chiudiConnessione();
}
