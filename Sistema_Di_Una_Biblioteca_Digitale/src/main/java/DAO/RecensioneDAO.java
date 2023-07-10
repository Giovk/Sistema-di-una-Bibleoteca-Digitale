package DAO;

public interface RecensioneDAO {
    public float valutazioneMediaLibroDB(String isbn);  //ritorna la media delle valutazioni del libro con isbn 'isbn'
    public void chiudiConnessione();    //chiude la connessione al DB
}
