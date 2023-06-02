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
    }

    public void setNome(String nNome){
        nome = nNome;
    }

    public void setCognome(String nCognome){
        cognome = nCognome;
    }

    public void setUsername(String nUsername){
        username = nUsername;
    }

    public void setPassword(String nPassword){
        password = nPassword;
    }

    public void setPartitaIva(String nPartitaIVA){
        partitaIVA = nPartitaIVA;
    }

    public void  setDataNascita(String nDataNascita){dataNascita = nDataNascita;}

    public String getUsername(){return username;}

    public String getNome(){return nome;}

    public String getCognome(){return cognome;}

    public String getEmail(){return email;}

    public String getPartitaIVA(){return partitaIVA;}

    public String getPassword(){return password;}

    public String getDataNascita(){return dataNascita;}
}
