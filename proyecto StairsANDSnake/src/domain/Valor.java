package domain;

public class Valor {
    int numero;
    Modifier modificador = null;
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
