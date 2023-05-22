package domain;
public class Snake extends Tramp {
	
	public Snake (Box startBox, Box finalBox, boolean transformar) {
		super(startBox, finalBox,transformar);
	}

	@Override
	public void DoAction(Ficha ficha){
		super.DoAction(ficha);
		ficha.setSnakes();
		ficha.setMaxCas(casillainicial.getValue());
	}

}
