package domain;

import java.awt.*;
import java.util.ArrayList;

public class StairsAndSnakes {
	private final Tablero tablero;

	public StairsAndSnakes (ArrayList<String> nombres, ArrayList<Color> colores, int tamano, int numE, int numS, boolean transformar, int porC, int porM) throws StairsAndSnakesException{
		if(porC > 50) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_PORC_ALLOW);
		if(2*(numE + numS) > (10*tamano) - porC) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ENOUGHT_SPACE);

		tablero = new Tablero(nombres,colores,numS,numE,tamano,porC,porM,transformar);
	}
	public StairsAndSnakes(String nombre, Color color, int tamano, int numE, int numS, boolean transformar, int porC, int porM) throws StairsAndSnakesException{
		if(porC > 50) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_PORC_ALLOW);
		if(numE + numS > 10* tamano) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ENOUGHT_SPACE);

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

	public void changeDados(String name){
		Player jugador = tablero.getJugador(name);
		jugador.playAturn();
	}

	public void movePlayer(String name) {
		Player jugador = tablero.getJugador(name);
		jugador.moveToken();
	}
}