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
		Tablero tablero = casilla.getTablero();
		Box renew = tablero.searchBox(newPosition);
		renew.addToken(ficha.getColor(),ficha);
	}

	@Override
	public String toString() {
		return "Bonificacion";
	}
}
