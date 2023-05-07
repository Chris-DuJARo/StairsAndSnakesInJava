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
    public void moveTokenWithPower(Ficha ficha) {
        try {
            Snake snake = tablero.searchSnake(ficha);
            Box casilla = ficha.getBox();
            Box reNew = snake.getCasillainicial();
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
