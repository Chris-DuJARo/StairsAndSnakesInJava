package domain;

public class Movement extends Box{
    public Movement(Tablero tbl){
        super(tbl);
    }
    @Override
    public boolean hasApower(){
        return true;
    }

    @Override
    public void moveTokenWithPower(Ficha ficha)  {
        try {

            Stair stair = tablero.searchStair(ficha);
            Box casilla = ficha.getBox();
            Box reNew = stair.getCasillainicial();
            casilla.deleteToken(ficha);
            reNew.addToken(ficha.getColor(), ficha);

            if (reNew.hasAnyTramp()) {
                Item trampa = reNew.getItem();
                trampa.DoAction(ficha);
            }
        } catch (StairsAndSnakesException e){
            e.getMessage();
        }
    }
}
