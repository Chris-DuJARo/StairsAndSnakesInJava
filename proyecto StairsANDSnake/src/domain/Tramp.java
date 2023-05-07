package domain;
public abstract class Tramp implements Behavior {
	
	protected Box casillainicial;
	protected Box casillafin;

	private String comportamiento;

	public Box getCasillaInicial(){
		return casillainicial;
	}

	public Box getCasillafin(){
		return casillafin;
	}
	
	public Tramp (Box casillainicial, Box casillafin, boolean canChange) {
		this.casillainicial = casillainicial;
		this.casillafin = casillafin;
		SetObject();

		if (canChange){
			setBehavior(Behavior.normal);
		}else {
			setBehavior(null);
		}
	}

	@Override
	public void setBehavior(String comportamiento) {
		this.comportamiento = comportamiento;
	}

	public Box getCasillainicial() {
		return casillainicial;
	}

	@Override
	public void SetObject(){
		casillainicial.setItem(this);
		casillafin.setItem(this);
	}

	public void DoAction(Ficha ficha){
		casillainicial.deleteToken(ficha);
		casillafin.addToken(ficha.getColor(), ficha);
	}
}
