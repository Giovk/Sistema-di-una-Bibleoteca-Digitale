package DAO;

import Model.Libro;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface LibroDAO {
    public ResultSet getLibriDB();  //ritorna i dati di tutti i libri nel DB
    public ResultSet getLibriDB(String collana);  //ritorna i dati di tutti i libri della collana 'collana' nel DB
    //public ResultSet getLibroAutoriDB(String s);    //ritorna i dati del libro con ISBN 's'
    public ResultSet getLibriSerieDB(String s); //ritorna i dati di tutti i libri della serie con ISBN 's' nel DB
    public ResultSet getInfoLibriPreferitiDB(String isbn);
    public boolean creaLibroDB(String isbn, String titolo, String genere,String lingua, String editore,String dp);
    public void chiudiConnessione(); //chiude la connessione al DB
}
