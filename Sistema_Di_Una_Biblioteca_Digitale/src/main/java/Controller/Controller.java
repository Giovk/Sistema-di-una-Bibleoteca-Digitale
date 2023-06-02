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

    public void aggiungiUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){ //aggiunge un nuovo utente nel database e in memoria
        utente = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA); // Registra il nuovo utente in memoria
        //utente.regUtente(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente in memoria
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        u.addUtenteDB(email, nome, cognome, username, password, dataNascita, partitaIVA); // Registra il nuovo utente nel DB
    }

    public int validaUtente(String userEmail, String password){//conta il numero di utenti registrati con username o email 'userMail' e con password 'password'
        UtenteDAO u = new UtenteImplementazionePostgresDAO();
        return u.validaUtenteDB(userEmail, password);
    }

    public void setUtente(String userEmail, String password) {
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

        utente = new Utente(us, p, e, n, c, dn, pIVA);
    }

    public int[] validaModUtente(String email, String username, String pIva){
        int[] error = {0, 0, 0, 0};
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        if(!email.equals(utente.email)){
            error[0] = u.validaModEmailDB(email);
        }

        if(!username.equals(utente.username)){
            error[1] = u.validaModUsernameDB(username);
        }

        if(!pIva.equals(utente.partitaIVA)){
            error[2] = u.validaModPIVADB(pIva);
        }

        u.chiudiConnessione();

        return error;
    }

    public void modUtente(String email, String nome, String cognome, String username, String password, String partitaIVA){
        UtenteDAO u = new UtenteImplementazionePostgresDAO();

        u.modUtenteDB(email, nome, cognome, username, password, partitaIVA, utente.username);

        utente.setEmail(email);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setPartitaIva(partitaIVA);
    }

    public String getUsername(){return utente.getUsername();}
    public String getNome(){return utente.getNome();}
    public String getCognome(){return utente.getCognome();}
    public String getEmail(){return utente.getEmail();}
    public String getPassword(){return utente.getPassword();}
    public String getPartitaIva(){return utente.getPartitaIVA();}
    public String getDataNascita(){return utente.getDataNascita();}
}
