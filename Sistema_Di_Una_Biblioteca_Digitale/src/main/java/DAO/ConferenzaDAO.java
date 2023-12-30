package DAO;

import java.sql.ResultSet;

public interface ConferenzaDAO {
    public ResultSet getConferenzeArticoloDB(String doi);   //cerca tutte le conferenze del articolo con DOI 'doi' nel DB
    public ResultSet getConferenzeDB(); //cerca tutte le conferenze nel DB
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf); //se non esiste già, inserisce una nuova confereza nel DB e ritorna "true", altrimenti ritorna "false"
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi);    //se non esiste già, inserisce una nuova esposizione nel DB e ritorna "true", altrimenti ritorna "false"
    public void chiudiConnessione();    //chiude la connessione al DB
}