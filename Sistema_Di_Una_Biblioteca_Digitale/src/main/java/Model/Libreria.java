package Model;

public class Libreria {
    public String nome;
    public String numeroTelefonico;
    public String indirizzo;
    public String sitoWeb;
    public Utente gestore;

    public Libreria(String n, String nt, String i, String sw, Utente g){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
        gestore = g;
    }

    public Libreria(String n, String nt, String i, String sw){
        nome = n;
        numeroTelefonico = nt;
        indirizzo = i;
        sitoWeb = sw;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
}
