package domain;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private final Color color;

	private final String name;

	protected final ArrayList<Dado> dados ;

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
		int cantidad = decideBox(ficha);
		if (ficha.canMove) {
			Box casilla = ficha.getBox();
			casilla.moveToken(ficha,cantidad);
		} else {
			boolean nowCan;
			Box casilla = ficha.getBox();
			if (casilla instanceof Question) {
				nowCan = ((Question) casilla).doQuestion(ficha);
				if (nowCan) moveToken();
			} else {
				ficha.canMove = true;
			}
		}
	}

	public int decideBox(Ficha ficha) {
		int cantidad;
		Box casilla = ficha.getBox();
		Tablero tablero = casilla.getTablero();
		Dado dado = tablero.getDados().get(0);
		int valueElecion;
		cantidad = casilla.getValue() + dado.getDado().getNumero();

		if (searchSpecialBoxs(ficha) > 0){
			ArrayList<Box> specials = tablero.getSpecials(ficha);
			ArrayList<Integer> opciones = new ArrayList<>();
			int cont = 0;
			while (cont < specials.size()) {
				opciones.add(specials.get(cont).getValue());
				cont++;
			}
			opciones.add(cantidad);
			if (ficha.getOwner() instanceof  Machine){
				valueElecion = ((Machine)ficha.getOwner()).DecideBox(opciones);
			}else {
				valueElecion = (int) JOptionPane.showInputDialog(null, "Que casilla desea? ", "Elegir",
						JOptionPane.QUESTION_MESSAGE, null, opciones.toArray(), opciones.get(0));
			}
			cantidad = valueElecion;
		}
		return cantidad;

	}

	private int searchSpecialBoxs(Ficha ficha){
		int range = dados.get(0).getDado().getNumero(),longitud;
		int especials = 0;
		Tablero tablero = ficha.getBox().getTablero();
		int position = ficha.getBox().getValue();
		if (position + 6 >= tablero.getWidth() - 6) {
			longitud = tablero.getWidth() - position;
			while (range >= longitud) {
				range--;
			}
		}
		for (int i=1;i <range; i++){
			if (tablero.searchBox(position+i).hasApower()){
				especials++;
			}
		}
		return especials;
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
