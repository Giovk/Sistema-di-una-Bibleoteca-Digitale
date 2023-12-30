package DAO;

import java.sql.Date;
import java.sql.ResultSet;

public interface LibroDAO {
    public ResultSet getLibriDB();  //cerca i dati di tutti i libri nel DB
    public ResultSet getLibriDB(String collana);  //cerca i dati di tutti i libri della collana 'collana' nel DB
    public ResultSet getLibriSerieDB(String s); //cerca i dati di tutti i libri della serie con ISBN 's' nel DB
    public ResultSet getInfoLibriPreferitiDB(String isbn);  //cerca i dati del libro con ISBN 'isbn' e delle librerie che lo possiedono
    public boolean creaLibroDB(String isbn, String titolo, String genere, String lingua, String editore, String dp);    //se non esiste già, inserisce un nuovo libro nel DB e ritorna "true", altrimenti ritorna "false"
    public Date getDataLibroDB(String isbn);    //cerca la data di pubblicazione del libro con ISBN 'isbn'
    public void eliminaLibroDB(String isbn);    //elimina dal DB il libro con ISBN 'isbn'
    public void chiudiConnessione(); //chiude la connessione al DB
}