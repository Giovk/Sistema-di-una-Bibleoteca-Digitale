package Model;

/**
 * La classe Possesso crea nuove istanze di possessi e fornisce le loro informazioni.
 */
public class Possesso {
    /**
     * modalità di Fruizione in cui è disponibile l'elemento posseduto dalla libreia del possesso.
     */
    public String fruizione;
    /**
     * Quantita disponibile dell'elemento posseduto dalla libreia del possesso.
     */
    public int quantita;
    /**
     * Elemento posseduto dalla libreia del possesso.
     */
    public Elemento elementoPosseduto;
    /**
     * Libreria che possiede l'elemento del possesso.
     */
    public Libreria libreriaPossedente;

    /**
     * Istanzia un nuovo Possesso.
     *
     * @param f  la modalità di fruizione in cui è disponibile l'elemento posseduto dalla libreia del possesso
     * @param q  la quantità disponibile dell'elemento posseduto dalla libreia del possesso
     * @param ep l'elemento posseduto dalla libreia del possesso
     * @param l  la libreria che possiede l'elemento del possesso
     */
    public Possesso(String f, int q, Elemento ep, Libreria l){
        fruizione = f;
        quantita = q;
        elementoPosseduto = ep;
        libreriaPossedente = l;
    }

    /**
     * Ritorna una stringa contenente la modalità di fruizione in cui è disponibile l'elemento posseduto dalla libreia del possesso.
     *
     * @return la stringa con la fruizione dell'elemento posseduto
     */
    public String getFruizione(){
        return  fruizione;
    }

    /**
     * Ritorna il valore della quantità disponibile dell'elemento posseduto dalla libreia del possesso.
     *
     * @return la quantità disponibile dell'elemento posseduto dalla libreia del possesso.
     */
    public int getQuantita(){
        return quantita;
    }
}
