package DAO;

import java.sql.ResultSet;

public interface LibreriaDAO {
    public ResultSet disponibilitaDB(String isbn);  //cerca le disponibilità del libro con ISBN 'isbn' nelle librerie
    public ResultSet disponibilitaFascicoloDB(int numero, String titolo);  //cerca le disponibilità del fascicolo numero 'numero' della rivista 'titolo' nelle librerie
    public ResultSet getLibrerieUtenteDB(String user);  //cerca tutte le librerie dell'utente 'user'
    public boolean presenzaNumeroTelefonicoLibreriaDB(String nt);   //controlla se il numero telefonico 'nt' è già presente nel DB
    public boolean presenzaLibreriaDB(String nome, String sw, String indirizzo);    //controlla se la libreria 'nome' con sito web 'sw' e indirizzo 'indirizzo' è già presente nel DB
    public void addLibreriaDB(String nome, String nt, String sw, String indirizzo, String user);    //aggiunge la libreria 'nome' dell'utente 'user' con numero telefoico 'nt', sito web 'sw' e indirizzo 'indirizzo'
    public void removeLibreriaDB(String nt);    //rimuove dal DB la libreria con il numero telefonico 'nt'
}