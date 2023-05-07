package domain;

import java.awt.*;

public class MovementNerf extends Modifier {


	public MovementNerf(Valor dado) {
		super(dado);
	}
	
	@Override
	public void DoAction(Ficha ficha) {
		Box casilla = ficha.getBox();
		casilla.deleteToken(ficha);
		int newPosition = casilla.getValue() - 1;
		MovementBuff.changePositions(ficha, casilla, newPosition);
	}

	@Override
	public String toString() {
		return "Penalizacion";
	}

}
