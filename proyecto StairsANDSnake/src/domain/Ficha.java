package domain;

import java.awt.*;

public class Ficha {
	private final Color color;
	private Box casilla;
	private int stairs = 0;

	private int snakes = 0;

	private int maxCas = 1;

	private int numSpecialBox = 0;

	public boolean canMove = true;



	public Ficha(Player player) {
		color = player.getColor();
		player.setToken(this);
	}

	public Box getBox() {
		return casilla;
	}

	public int getStairs(){
		return stairs;
	}

	public void setStairs(){
		stairs++;
	}

	public int getSnakes(){
		return snakes;
	}

	public void setSnakes(){
		snakes++;
	}

	public int getMaxCas(){
		return maxCas;
	}

	public void setMaxCas(int value){
		if (maxCas < value) maxCas = value;
	}

	public int getNumSpecialBox(){
		return numSpecialBox;
	}

	public void setNumSpecialBox(){
		numSpecialBox++;
	}

	public void setBox(Box casilla) {
		this.casilla = casilla;
	}
	public Color getColor() {
		return color;
	}

}
