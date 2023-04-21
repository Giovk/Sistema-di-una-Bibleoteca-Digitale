package Model;

import java.util.Date;

public class Utente {
    public String username;
    public String password;
    public String email;
    public String nome;
    public String cognome;
    public Date dataNascita;
    public String partitaIVA;

    public Utente (String u, String p, String e, String n, String c, Date dn, String pIVA) {
        username = u;
        password = p;
        email = e;
        nome = n;
        cognome = c;
        dataNascita = dn;
        partitaIVA = pIVA;
    }
}
