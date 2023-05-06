package domain;

import java.util.*;
import java.awt.Color;

public class Tablero {

	private final int width;

	private final HashMap<String,Player> players;

	private final ArrayList<Snake> snakes;

	private final ArrayList<Stair> stairs;
	
	private Box[][] boxs;

	private ArrayList<Dado> dados ;
	
	public Tablero(ArrayList<String> nombres, ArrayList<Color> colores,int numSnakes,int numStairs, int tamano, int porC,int porM,boolean transformar) {
		players = new HashMap<>();
		snakes = new ArrayList<>();
		stairs = new ArrayList<>();
		width = tamano;
		makeBoxes(porC);
		makeSnakes(numSnakes,transformar);
		makeStairs(numStairs,transformar);
		makeDados(porM);
		makePlayers(nombres,colores);
		
	}

	public Player getJugador(String name) {
		return players.get(name);
	}

	private void makeDados(int porM){
		dados = new ArrayList<>();
		dados.add(new Dado(porM));
	}
	
	private void makePlayers(ArrayList<String> nombres,ArrayList<Color> colores) {
		for(int i=0; i<2; i++) {
			Player jugador = new Player(nombres.get(i), colores.get(i),dados);
			players.put(nombres.get(i), jugador);
			makeToken(jugador);
		}
	}

	private void makeToken(Player jugador) {
		Ficha ficha = new Ficha(jugador);
		ficha.setBox(boxs[width-1][0]);
	}
	
	private void makeSnakes(int numSnakes, boolean transformar) {
		for(int i=0; i<numSnakes; i++) {
			
			int[] ramBoxF = getRamdoms();
			int[] ramBoxI = getRamdoms();
			
			Box ibox = boxs[ramBoxF[0]][ramBoxF[1]];
			Box fbox = boxs[ramBoxI[1]][ramBoxI[1]];
			
			Snake snake = new Snake(ibox,fbox,transformar);
			snakes.add(snake);
		}
	}
	
	
	private void makeStairs(int numStairs, boolean transformar) {
		for(int i=0; i<numStairs; i++) {
			
			int[] ramBoxF = getRamdoms();
			int[] ramBoxI = getRamdoms();
			
			Box ibox = boxs[ramBoxF[0]][ramBoxF[1]];
			Box fbox = boxs[ramBoxI[1]][ramBoxI[1]];
			
			Stair stair = new Stair(ibox, fbox, transformar);
			stairs.add(stair);
		}
	}

	private int[] getRamdoms() {
		
		Random ramdom1 = new Random();
		int[] numeros = new int[2];
		
		int miNumero1 = ramdom1.nextInt(width);
		int miNumero2 = ramdom1.nextInt(width);
		numeros[0] = miNumero1;
		numeros[1] = miNumero2;
		
		return numeros;
	}

	private Box getRamdomBox() {
		Random random = new Random();
		int numero = random.nextInt(6)+1 ;

		return switch (numero) {
			case 1 -> new Jumper(this);
			case 2 -> new JumperInverse(this);
			case 3 -> new Death(this);
			case 4 -> new Movement(this);
			case 5 -> new Recoil(this);
			case 6 -> new Question(this);
			default -> null;
		};
	}
	
	private void makeBoxes(int porC) {
		boxs = new Box[width][width];
		for(int i = 0; i< width; i++) {
			for(int j = 0; j< width; j++) {
				boxs[i][j] = new Box(this);
			}
		}

		int numCasilla = 1;
		for(int i=width-1;i>=0;i--) {
			if(i%2 != 0) {
				for (int j = 0; j < width; j++) {
					boxs[i][j].setValue(numCasilla);
					numCasilla++;
				}
			}
			else {
				for (int k=width-1; k >= 0; k--) {
					boxs[i][k].setValue(numCasilla);
					numCasilla++;
				}
			}
		}
		castSpecials(porC);
	}

	private void castSpecials(int porC){

		for(int i=0; i<porC;i++) {
			int[] filaCol = getRamdoms();
			setBox(filaCol[0],filaCol[1],null);

			Box casilla = getRamdomBox();
			setBox(filaCol[0],filaCol[1],casilla);
		}
	}

	public void setBox(int fila,int columna, Box box) {
		boxs[fila][columna] = box;
	}

	public ArrayList<Dado> getDados(){
		return dados;
	}

	public Box searchBox(int cantidad){
		if (cantidad > 10*width ) {
			searchBox(10*width);
		}
		for (int i=0;i<width;i++) {
			for (Box b: boxs[i]) {
				if (b.getValue() == cantidad) {
					return b;
				}
			}
		}
		return null;
	}

	public Ficha searchOpponent(Color color) {
		Set<Map.Entry<String, Player>> entradas = players.entrySet();

		for (Map.Entry<String,Player> entrada: entradas) {
			String nombre = entrada.getKey();
			Player player = players.get(nombre);
			if (!player.getColor().equals(color)){
				Ficha ficha = player.getFichas().get(0);
				return ficha;
			}
		}
		return null;
	}
}

