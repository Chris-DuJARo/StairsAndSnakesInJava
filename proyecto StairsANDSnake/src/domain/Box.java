package domain;

import javax.swing.*;
import java.awt.Color;
import java.util.HashMap;

public  class Box {
	private Tablero tablero;
	private int value ;
	private Item item;
	private HashMap<Color,Ficha> tokens;
	
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
	
	public void deleteToken(Ficha ficha) {
		tokens.remove(ficha);
		ficha.setBox(null);
	}
	
	public void addToken(Color color, Ficha ficha) {
		tokens.put(color, ficha);
		ficha.setBox(this);
	}
	
	public final boolean hasAnyTramp() {
		return item != null;
	}

	public boolean hasAnyToken() {
		return tokens.isEmpty();
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void moveToken(Ficha ficha) {
		Dado dado = tablero.getDados().get(0);
		Valor cara = dado.getDado();
		int cantidad = value + cara.getNumero();
		deleteToken(ficha);
		Box reNew = tablero.searchBox(cantidad);
		reNew.addToken(ficha.getColor(),ficha);

		if (cara.getModifier() != null) {
			int res = JOptionPane.showConfirmDialog(null,"Ha atrapado un modificador de "+
					cara.getModifier().toString() +" Desea usarlo? ");
			if(JOptionPane.OK_OPTION == res) {
				moveTokenWithModifer(ficha,cara);
			}
		}
	}
	private void moveTokenWithModifer(Ficha ficha,Valor movimiento) {
		Modifier modifier = movimiento.getModifier();
		modifier.DoAction(ficha);

	}
}