package DAO;

import java.sql.ResultSet;

/**
 * L'interfaccia PossessoDAO contiene i metodi (da implementare) che interagiscono con il database per implementare le funzionalità relative ai
 * possessi delle librerie e degli elementi.
 */
public interface PossessoDAO {
    /**
     * Ritorna tutte le informazioni dei libri posseduti dalla libreria con nome, sitoweb, indirizzo, gestore uguali a quelli passati come parametri,
     * e inoltre ritorna anche le quantità e le modalità di fruizione disponibili in tale libreria.
     *
     * @param nome      il nome della libreria
     * @param sitoweb   il sitoweb della libreria
     * @param indirizzo l'indirizzo della libreria
     * @param user      l'usernama del gestore della libreria
     * @return il ResultSet con le quantità e le modalità di fruizione disponibili e le informazioni trovate nel database dei libri posseduti dalla
     * libreria
     */
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei libri della libreria selezionata gestista dall'utente 'user'

    /**
     * Ritorna tutte le informazioni delle serie possedute dalla libreria con nome, sitoweb, indirizzo, gestore uguali a quelli passati come parametri,
     * e inoltre ritorna anche le quantità e le modalità di fruizione disponibili in tale libreria.
     *
     * @param nome      il nome della libreria
     * @param sitoweb   il sitoweb della libreria
     * @param indirizzo l'indirizzo della libreria
     * @param user      l'usernama del gestore della libreria
     * @return il ResultSet con le quantità e le modalità di fruizione disponibili e le informazioni trovate nel database delle serie possedute dalla
     * libreria
     */
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi delle serie della libreria selezionata gestista dall'utente 'user'

    /**
     * Ritorna tutte le informazioni dei fascicoli posseduti dalla libreria con nome, sitoweb, indirizzo, gestore uguali a quelli passati come
     * parametri, e inoltre ritorna anche le quantità e le modalità di fruizione disponibili in tale libreria.
     *
     * @param nome      il nome della libreria
     * @param sitoweb   il sitoweb della libreria
     * @param indirizzo l'indirizzo della libreria
     * @param user      l'usernama del gestore della libreria
     * @return il ResultSet con le quantità e le modalità di fruizione disponibili e le informazioni trovate nel database dei fascicoli posseduti
     * dalla libreria
     */
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user);    //cerca i possessi dei fascicoli della libreria selezionata gestista dall'utente 'user'

    /**
     * Prende come parametro la nuova quantità disponibile nella libreria con il numero telefonico uguale a quello passato come parametro
     * del libro con ISBN e modalità di fruizione uguali a quelli presi come parametri e aggiorna tale quantità nel database.
     *
     * @param isbn      l'ISBN del libro
     * @param nt        il numero telefonico della libreria
     * @param fruizione la modalità di fruizione del libro
     * @param value     il nuovo valore della quantità disponibile del libro nella libreria
     */
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del libro con ISBN 'isbn'

    /**
     * Prende come parametro la nuova quantità disponibile nella libreria con il numero telefonico uguale a quello passato come parametro
     * del fascicolo con numero, ISSN della rivista e modalità di fruizione uguali a quelli presi come parametri e aggiorna tale quantità nel
     * database.
     *
     * @param issn      l'ISSN della rivista del fascicolo
     * @param numero    il numero del fascicolo
     * @param nt        il numero telefonico della libreria
     * @param fruizione la modalità di fruizione del fascicolo
     * @param value     il nuovo valore della quantità disponibile del libro nella libreria
     */
    public void modQuantitaFascicoloDB(String issn, int numero, String nt, String fruizione, int value);    //aggiorna a 'value' la quantità disponibile nella libreria selezionata con numero telefonico 'nt' in modalità di fruizione 'fruizione' del fascicolo numero 'numero' della rivista con ISSN 'issn'

    /**
     * Se non è già presente, aggiunge nel database il libro, che ha come ISBN quello passato come parametro, tra quelli posseduti dalla libreria
     * che il numero telefonico passato come parametro con quantità e modalità di fruizione presi come parametri e ritorna "true", altrimenti
     * ritorna "false"
     *
     * @param isbn      l'ISBN del libro
     * @param nt        il numero telefonico della libreria
     * @param quantita  la quantità disponibile del libro nella libreria
     * @param fruizione la modalità di fruizione del libro nella libreria
     * @return "true" se è stato aggiunto il libro nella libreria, altrimenti "false"
     */
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del libro con ISBN 'isbn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Se non è già presente, aggiunge nel database il fascicolo, che ha il numero e l'ISSN passati come parametri, tra quelli posseduti dalla
     * libreria che il numero telefonico passato come parametro con quantità e modalità di fruizione presi come parametri e ritorna "true",
     * altrimenti ritorna "false"
     *
     * @param numero    il numero del fascicolo
     * @param issn      l'ISSN del fascicolo
     * @param nt        il numero telefonico della libreria
     * @param quantita  la quantità disponibile del fascicolo nella libreria
     * @param fruizione la modalità di fruizione del fascicolo nella libreria
     * @return "true" se è stato aggiunto il fascicolo nella libreria, altrimenti "false"
     */
    public boolean insertPossessoFDB(int numero, String issn, String nt, int quantita, String fruizione);   //se non esiste già, inserisce un nuovo possesso del fascicolo numero 'numero' della rivista con ISSN 'issn' e della libreria selezionata nel DB e ritorna "true", altrimenti ritorna "false"

    /**
     * Elimina nel database il libro con ISBN uguale a quello passato come parametro tra quelli pesseduti dalla libreria con il numero telefonico
     * uguale a quello pasato come parametro in modalità di fruizione uguale a quella passata come parametro.
     *
     * @param isbn      l'ISBN del libro
     * @param nt        il numero telefonico della libreria
     * @param fruizione la modalità di fruizione del libro
     */
    public void eliminaPossessoLDB(String isbn, String nt, String fruizione);   //elimina dal DB il possesso del libro con ISBN 'isbn'

    /**
     * Elimina nel database il fascicolo con numero e ISSN della rivista uguali a quelli passati come parametri tra quelli pesseduti dalla libreria
     * con il numero telefonico uguale a quello pasato come parametro in modalità di fruizione uguale a quella passata come parametro.
     *
     * @param issn      l'ISSN della rivista del fascicolo
     * @param numero    il numero del fascicolo
     * @param nt        il numero telefonico della libreria
     * @param fruizione  la modalità di fruizione del fascicolo
     */
    public void eliminaPossessoFDB(String issn, int numero, String nt, String fruizione);   //elimina dal DB il possesso del fascicolo numero 'numero' della rivista con ISSN 'issn'

    /**
     * Chiude la connessione al database.
     */
    public void chiudiConnessione();    //chiude la connessione al DB
}