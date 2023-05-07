package domain;

public class Death extends Box{
    public Death(Tablero tbl) {
        super(tbl);
    }
    @Override
    public boolean hasApower(){
        return true;
    }

    @Override
    public void moveTokenWithPower(Ficha ficha) {
        Box reNew = tablero.searchBox(1);
        Box casilla = ficha.getBox();
        casilla.deleteToken(ficha);
        reNew.addToken(ficha.getColor(),ficha);
    }
}
