package DAO;

import java.sql.ResultSet;

public interface RecensioneDAO {
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'
    public boolean likeLibroDB(String isbn, String user);
    public boolean changeLikeDB(boolean like, String isbn, String user);
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user);
    public ResultSet allRecWithCommentDB(String isbn);
    public void chiudiConnessione();    //chiude la connessione al DB
}
