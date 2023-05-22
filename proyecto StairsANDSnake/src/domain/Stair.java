package domain;
public class Stair extends Tramp {

	public Stair(Box startBox, Box finalBox, boolean transformar) {
		super(startBox, finalBox, transformar);
	}

	@Override
	public void DoAction(Ficha ficha){
		super.DoAction(ficha);
		ficha.setStairs();
		ficha.setMaxCas(casillafin.getValue());
	}

}
