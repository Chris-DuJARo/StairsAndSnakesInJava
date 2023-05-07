package domain;

public class Question extends Box{
    public Question(Tablero tbl) {
        super(tbl);
    }
    @Override
    public boolean hasApower(){
        return true;
    }
    @Override
    public void moveTokenWithPower(Ficha ficha) {

    }

}
