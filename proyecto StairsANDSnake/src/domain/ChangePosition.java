package domain;

public class ChangePosition extends Modifier{

	public ChangePosition(Valor dado) {
		super(dado);
	}

	@Override
	public void DoAction(Ficha ficha) {
		Box MyCasilla = ficha.getBox();
		Box casillaCambio;
		Tablero tablero =MyCasilla.getTablero();
		Ficha ficheOpponent = tablero.searchOpponent(ficha.getColor());
		casillaCambio = ficheOpponent.getBox();

		MyCasilla.deleteToken(ficha);
		casillaCambio.deleteToken(ficheOpponent);
		MyCasilla.addToken(ficheOpponent.getColor(),ficheOpponent);
		casillaCambio.addToken(ficha.getColor(),ficha);

		if (casillaCambio.hasApower()) {
			casillaCambio.moveTokenWithPower(ficha);

		} else if (casillaCambio.hasAnyTramp()) {
			Item trampa = casillaCambio.getItem();
			trampa.DoAction(ficha);
		}
	}

    @Override
    public String toString() {
        return "Cambio de Posicion";
    }

}
