package domain;

import java.util.ArrayList;
import java.util.Random;

public class Dado{

	private final ArrayList<Valor> values;
	private  int probabilidad;
	private int state;

	public Dado(int porM) {
		this.state = -1;
		this.probabilidad = porM;
		values = new ArrayList<>();
		for (int i=0;i<6;i++) {
			values.add(new Valor(i+1));
		}
	}
	
	public void lanzarDado() {
		Random random = new Random();
		int cara;
		int index =  random.nextInt(5);
		state = values.get(index).getNumero();

		for(Valor v: values) {
			v.setModifier(null);
		}

		int contador = 0;
		while (contador < probabilidad){

			cara = random.nextInt(6);
			Valor valor = values.get(cara);
			if(valor.getModifier() == null) {
				setRandomModifierToSomeFace(valor, random);
				contador++;
			}
		}
	}

	private void setRandomModifierToSomeFace(Valor cara,Random random) {
		int mdf = random.nextInt(3)+1;

		switch (mdf) {
			case 1 -> new MovementNerf(cara);
			case 2 -> new MovementBuff(cara);
			case 3 -> new ChangePosition(cara);
		}
	}
	public Valor getDado() {
		if(state == -1) {
			return new Valor(0);
		}else {
			return values.get(state);
		}
	}

	public ArrayList<Valor> getValues() {
		return  values;
	}
}