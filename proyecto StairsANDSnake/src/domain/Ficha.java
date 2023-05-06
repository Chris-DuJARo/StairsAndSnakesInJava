package domain;

import java.awt.*;


public class Ficha {
	private Color color;
	private Box casilla;

	public Ficha(Player player) {
		color = player.getColor();
		player.setToken(this);
	}

	public Box getBox() {
		return casilla;
	}

	public void setBox(Box casilla) {
		this.casilla = casilla;
	}
	public Color getColor() {
		return color;
	}
}
