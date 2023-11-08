package DAO;

import java.sql.ResultSet;

public interface ConferenzaDAO {
    public ResultSet getConferenzeArticoloDB(String isbn);
    public ResultSet getConferenzeDB();
    public boolean addConferenzaDB(String struttura, String luogo, String datai, String dataf);
    public boolean addEsposizioneDB(String struttura, String luogo, String datai, String dataf, String doi);
    public void chiudiConnessione();    //chiude la connessione al DB
}
