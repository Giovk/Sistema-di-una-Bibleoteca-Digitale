package Controller;

import DAO.UtenteDAO;
import ImplementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import Model.Utente;

public class Controller {
    private  Utente utente;
    public Controller(){

    }

    public void aggiungiUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){
        utente = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA);
        utente.regUtente(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente in memoria;
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        u.addUtenteDB(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente nel DB;
    }
}
