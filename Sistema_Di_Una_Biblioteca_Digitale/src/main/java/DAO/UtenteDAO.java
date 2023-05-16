package DAO;

public interface UtenteDAO {
    public void addUtenteDB(String email, String nome, String cognome, String Username, String password, String dataNascita, String partitaIVA);
    public int validaUtenteDB(String userEmail, String password);
}
