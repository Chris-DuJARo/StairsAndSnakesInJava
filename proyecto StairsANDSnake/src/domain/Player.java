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

	public void moveToken() {
		Ficha ficha = fichas.get(0);
		Box casilla = ficha.getBox();
		casilla.moveToken(ficha);

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

}
