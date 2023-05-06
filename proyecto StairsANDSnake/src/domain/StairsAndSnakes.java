package domain;

import java.awt.*;
import java.util.ArrayList;

public class StairsAndSnakes {
	private final Tablero tablero;

	public StairsAndSnakes (ArrayList<String> nombres, ArrayList<Color> colores, int tamano, int numE, int numS, boolean transformar, int porC, int porM) {
		tablero = new Tablero(nombres,colores,numS,numE,tamano,porC,porM,transformar);
	}
	public StairsAndSnakes(String nombre, Color color, int tamano, int numE, int numS, boolean transformar, int porC, int porM){
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