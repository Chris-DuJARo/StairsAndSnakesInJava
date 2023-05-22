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
    public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException {
        Stair stair = tablero.searchStair(ficha);
        if (stair == null) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_FOUND_STAIR);
        super.moveTokenWithPower(ficha);
        Box casilla = ficha.getBox();
        Box reNew = stair.getCasillainicial();
        casilla.deleteToken(ficha);
        reNew.addToken(ficha.getColor(), ficha);
        ficha.setMaxCas(reNew.getValue());

        if (reNew.hasAnyTramp()) {
            Item trampa = reNew.getItem();
            trampa.DoAction(ficha);
        }
    }
}
