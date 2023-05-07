package domain;

import java.util.Random;

public class Jumper extends Box {
    public int salto;
    public Jumper(Tablero tbl) {
        super(tbl);
        salto = 10*getTablero().getWidth() - getValue();
    }

    @Override
    public boolean hasApower(){
        return true;
    }
    @Override
    public void moveTokenWithPower(Ficha ficha) {
        int cantidad;
        Random random;
        random = new Random();
        cantidad = random.nextInt(getValue(),salto);
        JumperInverse.VerifyReNew(ficha, tablero.searchBox(cantidad));
    }
}
