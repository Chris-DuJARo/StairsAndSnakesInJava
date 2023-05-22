package domain;

import org.yaml.snakeyaml.tokens.Token;

import javax.swing.*;
import java.awt.Color;
import java.util.HashMap;

public class Box {
	protected final Tablero tablero;
	private int value ;
	protected Item item;
	private final HashMap<Color,Ficha> tokens;
	
	public  Box(Tablero tbl) {
		tokens = new HashMap<>();
		tablero = tbl;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public Tablero getTablero(){
		return tablero;
	}

	public Item getItem(){
		return item;
	}

	public HashMap<Color,Ficha>getTokens(){
		return tokens;
	}
	
	public void deleteToken(Ficha ficha) {
		Color color = ficha.getColor();
		tokens.remove(color);
		ficha.setBox(null);
	}
	
	public void addToken(Color color, Ficha ficha) {
		tokens.put(color, ficha);
		ficha.setBox(this);
	}
	
	public final boolean hasAnyTramp() {
		return item != null;
	}

	public boolean hasApower(){
		return false;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	public void moveToken(Ficha ficha) throws StairsAndSnakesException {
		Dado dado = tablero.getDados().get(0);
		Valor cara = dado.getDado();
		int cantidad = value + cara.getNumero();
		if (cantidad <= tablero.getWidth()*tablero.getWidth()) {
			ficha.setMaxCas(cantidad);
			deleteToken(ficha);
			Box reNew = tablero.searchBox(cantidad);
			reNew.addToken(ficha.getColor(), ficha);
			if (cara.getModifier() != null) {
				int res = JOptionPane.showConfirmDialog(null, "Ha atrapado un modificador de " +
						cara.getModifier().toString() + " Desea usarlo? ");
				if (JOptionPane.OK_OPTION == res) {
					moveTokenWithModifer(ficha, cara);
				} else {
					if (reNew.hasApower()) {
						reNew.moveTokenWithPower(ficha);

					} else if (reNew.hasAnyTramp()) {
						Item trampa = reNew.getItem();
						trampa.DoAction(ficha);
					}
				}
			} else if (cara.getModifier() == null) {
				if (reNew.hasApower()) {
					reNew.moveTokenWithPower(ficha);

				} else if (reNew.hasAnyTramp()) {
					Item trampa = reNew.getItem();
					trampa.DoAction(ficha);
				}
			}
		} else {
			throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ALLOW_MOVEMENT);
		}
	}
	private void moveTokenWithModifer(Ficha ficha,Valor movimiento)throws StairsAndSnakesException {
		Modifier modifier = movimiento.getModifier();
		modifier.DoAction(ficha);
	}

	public void moveTokenWithPower(Ficha ficha) throws StairsAndSnakesException {
		ficha.setNumSpecialBox();
	}

}