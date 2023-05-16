package Controller;

import DAO.UtenteDAO;
import ImplementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int validaUtente(String userEmail, String password){
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        return u.validaUtenteDB(userEmail, password);
    }

    public Utente getUtente(String userEmail, String password) {
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        ResultSet rs = u.getUtenteDB(userEmail, password);
        String us = null;
        String p = null;
        String e = null;
        String n = null;
        String c = null;
        String dn = null;
        String pIVA = null;

        try {
            while(rs.next()){
                us = rs.getString("username");
                p = rs.getString("passwordu");
                e = rs.getString("email");
                n = rs.getString("nome");
                c = rs.getString("cognome");
                dn = rs.getString("datanascita");
                pIVA = rs.getString("partitaiva");
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        u.chiudiConnessione();

        Utente user = new Utente(us, p, e, n, c, dn, pIVA);
        return user;
    }
}
