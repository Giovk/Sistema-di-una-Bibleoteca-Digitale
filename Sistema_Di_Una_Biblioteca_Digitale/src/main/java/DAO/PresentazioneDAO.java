package DAO;

import java.sql.ResultSet;

public interface PresentazioneDAO {
    public ResultSet getPresentazioneDB(String isbn);   //cerca i dati di tutte le presentazioni del libro on ISBN 'isbn' nel DB
    public boolean addPresentazioneDB(String struttura, String luogo, String data, String orario, String isbn); //se non esiste già, inserisce una nuova presentazione nel DB e ritorna "true", altrimenti ritorna "false"
    public void chiudiConnessione();    //chiude la connessione al DB
}