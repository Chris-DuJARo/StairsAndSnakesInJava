package domain;

import java.awt.*;

public abstract class Tramp implements Behavior {
	
	protected Box casillainicial;
	protected Box casillafin;

	private String comportamiento;
	
	public Tramp (Box casillainicial, Box casillafin, boolean canChange) {
		this.casillainicial = casillainicial;
		this.casillafin = casillafin;
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

	@Override
	public void SetObject(){
		casillainicial.setItem(this);
		casillafin.setItem(this);
	}

	public abstract void DoAction(Color color);
}
