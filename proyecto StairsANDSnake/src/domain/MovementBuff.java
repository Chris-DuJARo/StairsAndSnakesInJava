package domain;

public class MovementBuff extends Modifier{

	public MovementBuff(Valor dado) {
		super(dado);
	}

	@Override
	public void DoAction(Ficha ficha) {
		Box casilla = ficha.getBox();
		casilla.deleteToken(ficha);
		int newPosition = casilla.getValue() + 1;
		changePositions(ficha, casilla, newPosition);
	}

	public static void changePositions(Ficha ficha, Box casilla, int newPosition) {
		Tablero tablero = casilla.getTablero();
		Box renew = tablero.searchBox(newPosition);
		renew.addToken(ficha.getColor(),ficha);

		if (renew.hasApower()) {
			renew.moveTokenWithPower(ficha);

		} else if (renew.hasAnyTramp()) {
			Item trampa = renew.getItem();
			trampa.DoAction(ficha);
		}
	}

	@Override
	public String toString() {
		return "Bonificacion";
	}
}
