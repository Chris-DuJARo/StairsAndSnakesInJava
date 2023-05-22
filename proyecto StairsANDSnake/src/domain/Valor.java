package domain;

public class Valor {
    private final int numero;
    private Modifier modificador = null;
    public Valor(int value){
        this.numero = value;
    }

    public int getNumero() {
        return numero;
    }

    public Modifier getModifier(){
        return modificador;
    }

    public void setModifier(Modifier modificador) {
        this.modificador = modificador;
    }
}
