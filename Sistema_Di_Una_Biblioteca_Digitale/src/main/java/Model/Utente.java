package Model;

import java.util.Date;

public class Utente {
    public String username;
    public String password;
    public String email;
    public String nome;
    public String cognome;
    public String dataNascita;
    public String partitaIVA;

    public Utente (String u, String p, String e, String n, String c, String dn, String pIVA) {
        username = u;
        password = p;
        email = e;
        nome = n;
        cognome = c;
        dataNascita = dn;
        partitaIVA = pIVA;
    }

    public void regUtente(String email, String nome, String cognome, String username, String password, String dataNascita, String partitaIVA){
        Utente u = new Utente(username, password, email, nome, cognome, dataNascita, partitaIVA);
    }
    public void setEmail(String nEmail){
        email = nEmail;
    }   //imposta l'email dell'utente

    public void setNome(String nNome){nome = nNome;}    //imposta il nome dell'utente

    public void setCognome(String nCognome){
        cognome = nCognome;
    }   //imposta il cognome dell'utente

    public void setUsername(String nUsername){
        username = nUsername;
    }   //imposta l'username dell'utente

    public void setPassword(String nPassword){
        password = nPassword;
    }   //imposta la password dell'utente

    public void setPartitaIva(String nPartitaIVA){
        partitaIVA = nPartitaIVA;
    }   //imposta la partita IVA dell'utente

    public void  setDataNascita(String nDataNascita){dataNascita = nDataNascita;}   //imposra la data di nascita dell'utente

    public String getUsername(){return username;}   //ritorna l'username dell'utente

    public String getNome(){return nome;}   //ritorna il nome dell'utente

    public String getCognome(){return cognome;} //ritorna il cognome dell'utente

    public String getEmail(){return email;} //ritorna l'email dell'utente

    public String getPartitaIVA(){return partitaIVA;}   //ritorna la partita IVA dell'utente

    public String getPassword(){return password;}   //ritorna la password dell'utente

    public String getDataNascita(){return dataNascita;} //ritorna la data di nascita dell'utente
}
