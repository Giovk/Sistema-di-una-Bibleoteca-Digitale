package Model;

/**
 * The type Utente.
 */
public class Utente {
    /**
     * The Username.
     */
    public String username;
    /**
     * The Password.
     */
    public String password;
    /**
     * The Email.
     */
    public String email;
    /**
     * The Nome.
     */
    public String nome;
    /**
     * The Cognome.
     */
    public String cognome;
    /**
     * The Data nascita.
     */
    public String dataNascita;
    /**
     * The Partita iva.
     */
    public String partitaIVA;

    /**
     * Instantiates a new Utente.
     *
     * @param u    the u
     * @param p    the p
     * @param e    the e
     * @param n    the n
     * @param c    the c
     * @param dn   the dn
     * @param pIVA the p iva
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
     * Sets email.
     *
     * @param nEmail the n email
     */
    public void setEmail(String nEmail){
        email = nEmail;
    }   //imposta l'email dell'utente

    /**
     * Sets nome.
     *
     * @param nNome the n nome
     */
    public void setNome(String nNome){nome = nNome;}    //imposta il nome dell'utente

    /**
     * Sets cognome.
     *
     * @param nCognome the n cognome
     */
    public void setCognome(String nCognome){
        cognome = nCognome;
    }   //imposta il cognome dell'utente

    /**
     * Sets username.
     *
     * @param nUsername the n username
     */
    public void setUsername(String nUsername){
        username = nUsername;
    }   //imposta l'username dell'utente

    /**
     * Sets password.
     *
     * @param nPassword the n password
     */
    public void setPassword(String nPassword){
        password = nPassword;
    }   //imposta la password dell'utente

    /**
     * Sets partita iva.
     *
     * @param nPartitaIVA the n partita iva
     */
    public void setPartitaIva(String nPartitaIVA){
        partitaIVA = nPartitaIVA;
    }   //imposta la partita IVA dell'utente

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername(){return username;}   //ritorna l'username dell'utente

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome(){return nome;}   //ritorna il nome dell'utente

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome(){return cognome;} //ritorna il cognome dell'utente

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail(){return email;} //ritorna l'email dell'utente

    /**
     * Gets partita iva.
     *
     * @return the partita iva
     */
    public String getPartitaIVA(){return partitaIVA;}   //ritorna la partita IVA dell'utente

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword(){return password;}   //ritorna la password dell'utente

    /**
     * Verify partita iva boolean.
     *
     * @param piva the piva
     * @return the boolean
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