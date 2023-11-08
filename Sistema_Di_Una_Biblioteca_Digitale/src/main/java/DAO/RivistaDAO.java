package DAO;

import java.sql.ResultSet;

public interface RivistaDAO {
    public ResultSet getRivisteDB();  //ritorna i dati di tutti le riviste nel DB
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap);
    public void eliminaRivistaDB(String issn);
    public void chiudiConnessione(); //chiude la connessione al DB
}
