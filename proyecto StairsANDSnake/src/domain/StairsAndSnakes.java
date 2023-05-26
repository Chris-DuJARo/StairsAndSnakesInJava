package domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StairsAndSnakes {
	private Tablero tablero;

	private int turno = 0;

	protected  boolean win = false;
	public StairsAndSnakes (ArrayList<String> nombres, ArrayList<Color> colores,String rival, int tamano, int numE, int numS, boolean transformar, int porC, int porM,String dificultad) throws StairsAndSnakesException{
		if(colores.get(0).equals(colores.get(1))) throw  new StairsAndSnakesException(StairsAndSnakesException.SAME_COLORS);
		if(nombres.get(0).equals(nombres.get(1))) throw new StairsAndSnakesException(StairsAndSnakesException.SAME_NAMES);
		if (nombres.get(0).length() < 1 || nombres.get(1).length() < 1) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ENOUGHT_PLAYERS);
		if (porM > 6) throw  new StairsAndSnakesException(StairsAndSnakesException.NOT_ALLOW_MODIFIERS);
		if(porC > (10*tamano) /4) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_PORC_ALLOW);
		if((numE + numS)*2 > ((10*tamano)/2) - (1 + porC)) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ENOUGHT_SPACE);
		if (rival.equals("Player")) tablero = new Tablero(nombres,colores,numS,numE,tamano,porC,porM,transformar);
		else {
			tablero = new Tablero(nombres.get(1),colores,numS,numE,tamano,porC,porM,transformar,dificultad);
		}
	}
	public StairsAndSnakes(String nombre, Color color,String rival, int tamano, int numE, int numS, boolean transformar, int porC, int porM) throws StairsAndSnakesException{
		if(porC > 50) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_PORC_ALLOW);
		if(numE + numS > 10* tamano) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ENOUGHT_SPACE);
		if (porM > 6) throw  new StairsAndSnakesException(StairsAndSnakesException.NOT_ALLOW_MODIFIERS);

		ArrayList<String> jugadores = new ArrayList<>();
		ArrayList<Color> colores = new ArrayList<>();
		colores.add(color);
		colores.add(Color.gray);
		jugadores.add(nombre);
		jugadores.add("Maquina");
		tablero = new Tablero(jugadores,colores,numS,numE,tamano,porC,porM,transformar);
	}

	public Tablero getTablero() {
		return tablero;
	}

	public int getTurno() {
		return turno;
	}

	public boolean getWin(){
		return win;
	}

	public void changeDados(String name){
		Player jugador = tablero.getJugador(name);
		jugador.playAturn();
		turno ++;
	}

	public void movePlayer(String name) throws StairsAndSnakesException{
		Player jugador = tablero.getJugador(name);
		jugador.moveToken();
		verifyWin(name);
	}

	public void verifyWin(String name) {
		Player player = tablero.getJugador(name);
		Ficha ficha = player.getFichas().get(0);
		int posicion = ficha.getBox().getValue();
		if (posicion == tablero.getWidth() * tablero.getWidth()){
			win = true;
			JOptionPane.showMessageDialog(null,"Felicidades el jugador: "+ player + " Ha ganado!!!");
		}
	}

}