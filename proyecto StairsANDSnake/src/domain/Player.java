package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private final Color color;

	private final String name;

	private final ArrayList<Dado> dados ;

	private final ArrayList<Ficha> fichas;

	public Player(String name, Color color, ArrayList<Dado> dados) {
		fichas = new ArrayList<>();
		this.name = name;
		this.color = color;
		this.dados = dados;
	}

	public void playAturn() {
		for(Dado d: dados) {
			d.lanzarDado();
		}
	}

	public void moveToken() throws StairsAndSnakesException{
		Ficha ficha = fichas.get(0);

		if (ficha.canMove) {
			Box casilla = ficha.getBox();
			casilla.moveToken(ficha);
		} else {
			boolean nowCan;
			Box casilla = ficha.getBox();
			if (casilla instanceof Question){
				nowCan = ((Question) casilla).doQuestion(ficha);
				if(nowCan) moveToken();
			}else {
				ficha.canMove = true;
			}
		}
	}
	public Color getColor() {
		return color;
	}

	public String getName(){
		return name;
	}

	public ArrayList<Ficha> getFichas(){
		return fichas;
	}

	public void setToken(Ficha ficha) {
		fichas.add(ficha);
	}

	@Override
	public String toString() {
		return name;
	}

}
