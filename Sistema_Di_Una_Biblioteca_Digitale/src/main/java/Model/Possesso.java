package Model;

/**
 * The type Possesso.
 */
public class Possesso {
    /**
     * The Fruizione.
     */
    public String fruizione;
    /**
     * The Quantita.
     */
    public int quantita;
    /**
     * The Elemento posseduto.
     */
    public Elemento elementoPosseduto;
    /**
     * The Libreria possedente.
     */
    public Libreria libreriaPossedente;

    /**
     * Instantiates a new Possesso.
     *
     * @param f  the f
     * @param q  the q
     * @param ep the ep
     * @param l  the l
     */
    public Possesso(String f, int q, Elemento ep, Libreria l){
        fruizione = f;
        quantita = q;
        elementoPosseduto = ep;
        libreriaPossedente = l;
    }

    /**
     * Get fruizione string.
     *
     * @return the string
     */
    public String getFruizione(){
        return  fruizione;
    }

    /**
     * Get quantita int.
     *
     * @return the int
     */
    public int getQuantita(){
        return quantita;
    }
}
