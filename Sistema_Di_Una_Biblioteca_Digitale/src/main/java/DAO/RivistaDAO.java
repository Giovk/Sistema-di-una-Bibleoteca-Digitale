package DAO;

import java.sql.ResultSet;

public interface RivistaDAO {
    public ResultSet getRivisteDB();  //cerca i dati di tutti le riviste nel DB
    public boolean creaRivistaDB(String issn, String titolo, String argomento, String nomeR, String cognomeR, String editore, int ap);  //se non esiste già, inserisce una nuova rivista nel DB e ritorna "true", altrimenti ritorna "false"
    public void eliminaRivistaDB(String issn);  //elimina dal DB la rivista con ISSN 'issn'
    public void chiudiConnessione(); //chiude la connessione al DB
}
