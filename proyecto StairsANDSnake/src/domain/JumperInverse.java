package domain;

import java.util.Random;

public class JumperInverse extends Box{

    public JumperInverse(Tablero tbl) {
        super(tbl);
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
        cantidad = random.nextInt(1,getValue()+1);
        VerifyReNew(ficha, tablero.searchBox(cantidad));
    }

    static void VerifyReNew(Ficha ficha, Box box) {
        Box renew = box;
        Box casilla = ficha.getBox();

        casilla.deleteToken(ficha);
        renew.addToken(ficha.getColor(),ficha);

        if (renew.hasAnyTramp()) {
            Item trampa = renew.getItem();
            trampa.DoAction(ficha);
        }else if(renew.hasApower()){
            renew.moveTokenWithPower(ficha);
        }
    }
}
