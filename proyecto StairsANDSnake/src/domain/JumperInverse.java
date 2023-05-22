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
    public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException {
        super.moveTokenWithPower(ficha);
        int cantidad;
        Random random;
        random = new Random();
        cantidad = random.nextInt(getValue()-6,getValue()-1);
        VerifyReNew(ficha, tablero.searchBox(cantidad));
    }

    static void VerifyReNew(Ficha ficha, Box box) throws StairsAndSnakesException{
        Box renew = box;
        Box casilla = ficha.getBox();

        casilla.deleteToken(ficha);
        renew.addToken(ficha.getColor(),ficha);
        ficha.setMaxCas(renew.getValue());

        if (renew.hasAnyTramp()) {
            Item trampa = renew.getItem();
            trampa.DoAction(ficha);
        }else if(renew.hasApower()){
            renew.moveTokenWithPower(ficha);
        }
    }
}
