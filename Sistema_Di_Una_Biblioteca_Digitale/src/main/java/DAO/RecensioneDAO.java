package DAO;

import java.sql.ResultSet;

public interface RecensioneDAO {
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'
    public boolean likeLibroDB(String isbn, String user);   //controlla se l'utente 'user' ha il libro 'isbn' tra i preferiti
    public boolean changeLikeDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' il libro 'isbn' e ritone l'opposto di 'like'
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro 'isbn'
    public ResultSet allRecWithCommentDB(String isbn);  //ritorna tutte le recensioni con un testo fatte al libro 'isbn'
    public void chiudiConnessione();    //chiude la connessione al DB
}
