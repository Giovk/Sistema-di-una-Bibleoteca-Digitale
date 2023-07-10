package DAO;

public interface RecensioneDAO {
    public float valutazioneMediaLibroDB(String isbn);
    public void chiudiConnessione();    //ritorna i dati di tutti i libri nel DB
}
