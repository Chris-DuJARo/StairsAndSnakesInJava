package domain;

public class Recoil extends Box{
    public Recoil(Tablero tbl) {
        super(tbl);
    }
    @Override
    public boolean hasApower(){
        return true;
    }

    @Override
    public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException {
        Snake snake = tablero.searchSnake(ficha);
        if (snake == null) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_FOUND_SNAKE);
        Box casilla = ficha.getBox();
        super.moveTokenWithPower(ficha);
        Box reNew = snake.getCasillainicial();
        casilla.deleteToken(ficha);
        reNew.addToken(ficha.getColor(), ficha);
        ficha.setMaxCas(reNew.getValue());

        if (reNew.hasAnyTramp()) {
            Item trampa = reNew.getItem();
            trampa.DoAction(ficha);
        }
    }
}
