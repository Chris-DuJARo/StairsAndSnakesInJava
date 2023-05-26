package domain;

import java.awt.*;

public class Ficha {

	private Player owner;
	private final Color color;
	private Box casilla;
	private int stairs = 0;

	private int snakes = 0;

	private int maxCas = 1;

	private int numSpecialBox = 0;

	private int numMod = 0;

	public boolean canMove = true;

	public Ficha(Player player) {
		owner = player;
		color = player.getColor();
		player.setToken(this);
	}

	public Box getBox() {
		return casilla;
	}

	public Player getOwner(){
		return owner;
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

	public int getNumMod(){
		return numMod;
	}

	public void setNumMod(){
		numMod++;
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
