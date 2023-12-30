package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface RecensioneDAO {
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'
    public boolean likeLibroDB(String isbn, String user);   //controlla se l'utente 'user' ha il libro con ISBN 'isbn' tra i preferiti
    public boolean changeLikeLibroDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' il libro con ISBN 'isbn' e ritorna l'opposto di 'like'
    public void addRecensioneLibroDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al libro con ISBN 'isbn'
    public ResultSet allRecWithCommentLibroDB(String isbn);  //ritorna tutte le recensioni con un testo fatte al libro con ISBN 'isbn'
    public float valutazioneMediaSerieDB(String isbn);  //ritorna la media delle valutazioni della serie con ISBN 'isbn'
    public boolean likeSerieDB(String isbn, String user);   //controlla se l'utente 'user' ha la serie con ISBN 'isbn' tra i preferiti
    public boolean changeLikeSerieDB(boolean like, String isbn, String user);    //toglie/mette nei preferiti dell'utente 'user' la serie con ISBN 'isbn' e ritorna l'opposto di 'like'
    public void addRecensioneSerieDB(int valutazione, String text, String isbn, String user);   //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' la serie con ISBN 'isbn'
    public ResultSet allRecWithCommentSerieDB(String isbn);  //ritorna tutte le recensioni con un testo fatte alla serie con ISBN 'isbn'
    public float valutazioneMediaFascicoloDB(int numero, String titolo);    //ritorna la media delle valutazioni del fascicolo numero 'numero' della rivista 'titolo'
    public boolean likeFascicoloDB(int numero, String titolo, String user); //controlla se l'utente 'user' ha il fascicolo numero 'numero' della rivista 'titolo' tra i preferiti
    public boolean changeLikeFascicoloDB(boolean like, int numero, String titolo, String user); //toglie/mette nei preferiti dell'utente 'user' il fascicolo numero 'numero' della rivista 'titolo' e ritorna l'opposto di 'like'
    public void addRecensioneFascicoloDB(int valutazione, String text, int numero, String titolo, String user);  //aggiunge/aggiorna una recensione con 'valutazione' e 'testo' fatta dall'utente 'user' al fascicolo numero 'numero' della rivista 'titolo'
    public ResultSet allRecWithCommentFascicoloDB(int numero, String titolo);  //ritorna tutte le recensioni con un testo fatte al fascicolo numero 'numero' della rivista 'titolo'
    public ArrayList<String> getLibriISBNPreferitiDB(String user);  //ritorna gli ISBN dei libri preferiti dell'utente 'user'
    public ArrayList<String> getSerieISBNPreferitiDB(String user);  //ritorna gli ISBN delle serie preferite dell'utente 'user'
    public void chiudiConnessione();    //chiude la connessione al DB
}