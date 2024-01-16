package Model;

public class Possesso {
    public String fruizione;
    public int quantita;
    public Elemento elementoPosseduto;
    public Libreria libreriaPossedente;

    public Possesso(String f, int q, Elemento ep, Libreria l){
        fruizione = f;
        quantita = q;
        elementoPosseduto = ep;
        libreriaPossedente = l;
    }

    public String getFruizione(){
        return  fruizione;
    }

    public int getQuantita(){
        return quantita;
    }
}
