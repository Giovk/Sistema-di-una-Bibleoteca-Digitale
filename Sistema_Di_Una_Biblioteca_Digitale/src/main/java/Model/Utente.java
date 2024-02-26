package Model;

/**
 * La classe Utente crea nuove istanze di utenti, fornisce e gestisce le loro informazioni.
 */
public class Utente {
    /**
     * Username dell'utente.
     */
    public String username;
    /**
     * Password dell'utente.
     */
    public String password;
    /**
     * Email dell'utente.
     */
    public String email;
    /**
     * Nome dell'utente.
     */
    public String nome;
    /**
     * Cognome dell'utente.
     */
    public String cognome;
    /**
     * Data di nascita dell'utente.
     */
    public String dataNascita;
    /**
     * Partita IVA dell'utente.
     */
    public String partitaIVA;

    /**
     * Istanzia un nuovo Utente.
     *
     * @param u    l'username dell'utente
     * @param p    la password dell'utente
     * @param e    l'email dell'utente
     * @param n    il nome dell'utente
     * @param c    il cognome dell'utente
     * @param dn   la data di nascita dell'utente
     * @param pIVA la partita IVA dell'utente
     */
    public Utente (String u, String p, String e, String n, String c, String dn, String pIVA) {
        username = u;
        password = p;
        email = e;
        nome = n;
        cognome = c;
        dataNascita = dn;
        partitaIVA = pIVA;
    }

    /**
     * Imposta l'email dell'utente con quella presa come parametro.
     *
     * @param nEmail nuova email dell'utente
     */
    public void setEmail(String nEmail){
        email = nEmail;
    }   //imposta l'email dell'utente

    /**
     * Imposta il nome dell'utente con quello preso come parametro.
     *
     * @param nNome il nuovo nome dell'utente
     */
    public void setNome(String nNome){nome = nNome;}    //imposta il nome dell'utente

    /**
     * Imposta il cognome dell'utente con quello preso come parametro.
     *
     * @param nCognome il nuovo cognome dell'utente
     */
    public void setCognome(String nCognome){
        cognome = nCognome;
    }   //imposta il cognome dell'utente

    /**
     * Imposta l'username dell'utente con quello preso come parametro.
     *
     * @param nUsername il nuovo username dell'utente
     */
    public void setUsername(String nUsername){
        username = nUsername;
    }   //imposta l'username dell'utente

    /**
     * Imposta la password dell'utente con quella presa come parametro.
     *
     * @param nPassword la nuova password dell'utente
     */
    public void setPassword(String nPassword){
        password = nPassword;
    }   //imposta la password dell'utente

    /**
     * Imposta la partita IVA dell'utente con quella presa come parametro.
     *
     * @param nPartitaIVA la nuova partita IVA dell'utente
     */
    public void setPartitaIva(String nPartitaIVA){
        partitaIVA = nPartitaIVA;
    }   //imposta la partita IVA dell'utente

    /**
     * Ritorna l'username dell'utente.
     *
     * @return l'username dell'utente
     */
    public String getUsername(){return username;}   //ritorna l'username dell'utente

    /**
     * Ritorna il nome dell'utente.
     *
     * @return il nome dell'utente
     */
    public String getNome(){return nome;}   //ritorna il nome dell'utente

    /**
     * Ritorna il cognome dell'utente.
     *
     * @return il cognome dell'utente
     */
    public String getCognome(){return cognome;} //ritorna il cognome dell'utente

    /**
     * Ritorna l'email dell'utente.
     *
     * @return l'email dell'utente
     */
    public String getEmail(){return email;} //ritorna l'email dell'utente

    /**
     * Ritorna la partita IVA dell'utente.
     *
     * @return la partita IVA dell'utente
     */
    public String getPartitaIVA(){return partitaIVA;}   //ritorna la partita IVA dell'utente

    /**
     * Ritorna la password dell'utente.
     *
     * @return la password dell'utente
     */
    public String getPassword(){return password;}   //ritorna la password dell'utente

    /**
     * Verifica se la partita IVA presa come parametro è scritta in un formato valido, cioè, se non è vuota, deve essere formata da 11 caratteri
     * numerici.
     *
     * @param piva la partita IVA da verificare
     * @return un valore booleano che indica se la partita IVA è scritta correttamente
     */
    public boolean verifyPartitaIVA(String piva){
        if (piva.length() == 0){
            return true;
        }

        if (piva.length() != 11){
            return false;
        }

        for(int i = 0; i < piva.length(); i++){
            if(piva.charAt(i) < '0' || piva.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }
}