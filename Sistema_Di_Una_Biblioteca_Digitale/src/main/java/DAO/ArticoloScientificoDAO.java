package DAO;

import java.sql.ResultSet;

public interface ArticoloScientificoDAO {
    public ResultSet getArticoliScientificiDB(String issn, int n);  //cerca gli artioli scientifici del fascicolo numero 'n' della rivista con ISSN 'issn'
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn);    //se non esiste già, inserisce un nuovo articolo nel DB e ritorna "true", altrimenti ritorna "false"
    public String getDoiDB(String titolo);  //ritorna il DOI dell'articolo scientifico 'titolo'
    public int getAPDB(String doi); //ritorna l'anno di pubblicazione dell'articolo scientifico con DOI 'doi'
    public void eliminaArticoloDB(String doi);  //elimina l'articolo scientifico con DOI 'doi'
    public void chiudiConnessione(); //chiude la connessione al DB
}
