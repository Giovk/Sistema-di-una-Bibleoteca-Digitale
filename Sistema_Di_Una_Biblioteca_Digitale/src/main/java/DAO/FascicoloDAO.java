package DAO;

import java.sql.ResultSet;

public interface FascicoloDAO {
    public ResultSet getFascicoliDB();  //cerca tutti i fascicoli nel DB
    public ResultSet getFascicoliDB(String user);   //cerca tutti i fascicoli prefertiti dell'utente nel DB
    public ResultSet getInfoFascicoliPreferitiDB(String rivista, int numero);   //cerca nel DB il fascicolo numero 'numero' della rivista che ha come ISSN 'rivista'
    public boolean creaFascicoloDB(int numero, String data, String issn);   //se non esiste già, inserisce un nuovo fascicolo nel DB e ritorna "true", altrimenti ritorna "false"
    public void eliminaFascicoloDB(String issn, int numero);    //elimina dal DB il fascicolo numero 'numero' della rivista che ha come ISSN 'issn'
    public void chiudiConnessione(); //chiude la connessione al DB
}