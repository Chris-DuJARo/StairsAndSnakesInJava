package domain;

import java.util.Random;

public class Jumper extends Box {
    public int salto;
    public Jumper(Tablero tbl) {
        super(tbl);
    }

    @Override
    public boolean hasApower(){
        return true;
    }
    @Override
    public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException{
        super.moveTokenWithPower(ficha);
        int cantidad;
        Random random;
        random = new Random();
        cantidad = random.nextInt(getValue()+ 1,getValue() + 6);
        JumperInverse.VerifyReNew(ficha, tablero.searchBox(cantidad));
    }
}
