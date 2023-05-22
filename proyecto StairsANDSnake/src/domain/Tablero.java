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
		makeBoxes(porC,numStairs,numSnakes);
		makeSnakes(numSnakes,transformar);
		makeStairs(numStairs,transformar);
		makeDados(porM);
		makePlayers(nombres,colores);
		
	}

	public int getWidth(){
		return width;
	}

	public Player getJugador(String name) {
		return players.get(name);
	}

	public Box[][] getBoxs(){
		return boxs;
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
		int contador = 0;
		while (contador < numSnakes){
			int[] ramBoxF = getRamdoms();
			int[] ramBoxI = getRamdoms();
			Box ibox = boxs[ramBoxF[0]][ramBoxF[1]];
			Box fbox = boxs[ramBoxI[0]][ramBoxI[1]];

			if(ibox.getValue() > fbox.getValue() && fbox.getValue() > 1 && ibox.getValue() <width*width & fbox.getValue() <width*width) {
				if(ibox.getValue() - fbox.getValue() >= width) {
					if (!ibox.hasApower() && !fbox.hasApower()) {
						if (!ibox.hasAnyTramp() && !fbox.hasAnyTramp()) {
							Snake snake = new Snake(ibox, fbox, transformar);
							snakes.add(snake);
							contador++;
						}
					}
				}
			}
		}
	}
	
	private void makeStairs(int numStairs, boolean transformar)  {
		int contador = 0;
		while (contador < numStairs){
			int[] ramBoxF = getRamdoms();
			int[] ramBoxI = getRamdoms();
			Box ibox = boxs[ramBoxF[0]][ramBoxF[1]];
			Box fbox = boxs[ramBoxI[0]][ramBoxI[1]];

			if(ibox.getValue() < fbox.getValue() && ibox.getValue() > 1 && ibox.getValue() <width*width & fbox.getValue() <width*width) {
				if(fbox.getValue() - ibox.getValue() >= width) {
					if (!ibox.hasApower() && !fbox.hasApower()) {
						if (!ibox.hasAnyTramp() && !fbox.hasAnyTramp()) {
							Stair stair = new Stair(ibox, fbox, transformar);
							stairs.add(stair);
							contador++;
						}
					}
				}
			}
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

	private Box getRamdomBoxNoStairs() {
		Random random = new Random();
		int numero = random.nextInt(5)+1 ;

		return switch (numero) {
			case 1 -> new Jumper(this);
			case 2 -> new JumperInverse(this);
			case 3 -> new Death(this);
			case 4 -> new Recoil(this);
			case 5 -> new Question(this);
			default -> null;
		};
	}

	private Box getRamdomBoxNoSnakes() {
		Random random = new Random();
		int numero = random.nextInt(5)+1 ;

		return switch (numero) {
			case 1 -> new Jumper(this);
			case 2 -> new JumperInverse(this);
			case 3 -> new Death(this);
			case 4 -> new Movement(this);
			case 5 -> new Question(this);
			default -> null;
		};
	}

	private Box getRamdomBoxNoItems() {
		Random random = new Random();
		int numero = random.nextInt(4)+1 ;

		return switch (numero) {
			case 1 -> new Jumper(this);
			case 2 -> new JumperInverse(this);
			case 3 -> new Death(this);
			case 4 -> new Question(this);
			default -> null;
		};
	}
	
	private void makeBoxes(int porC,int numStairs,int numSnakes) {
		boxs = new Box[width][width];
		for(int i = 0; i< width; i++) {
			for(int j = 0; j< width; j++) {
				boxs[i][j] = new Box(this);
			}
		}
		if(width % 2 !=0) makeBoxesOdd(porC,numStairs,numSnakes);
		else {
			int numCasilla = 1;
			for (int i = width - 1; i >= 0; i--) {
				if (i % 2 != 0) {
					for (int j = 0; j <= width - 1; j++) {
						boxs[i][j].setValue(numCasilla);
						numCasilla++;
					}
				} else {
					for (int k = width - 1; k >= 0; k--) {
						boxs[i][k].setValue(numCasilla);
						numCasilla++;
					}
				}
			}
			castSpecials(porC, numStairs, numSnakes);
		}
	}

	private void makeBoxesOdd(int porC,int numStairs,int numSnakes) {
		int numCasilla = 1;
		for (int i = width - 1; i >= 0; i--) {
			if (i % 2 == 0) {
				for (int j = 0; j <= width - 1; j++) {
					boxs[i][j].setValue(numCasilla);
					numCasilla++;
				}
			} else {
				for (int k = width - 1; k >= 0; k--) {
					boxs[i][k].setValue(numCasilla);
					numCasilla++;
				}
			}
		}
		castSpecials(porC, numStairs, numSnakes);
	}

	private void castSpecials(int porC,int numStairs,int numSnakes){
		int contador = 0;
		int fila,columna;

		if(numSnakes>0 && numStairs>0) {
			while (contador < porC) {
				int[] filaCol = getRamdoms();
				fila = filaCol[0];
				columna = filaCol[1];

				Box boxWithOutPower = boxs[fila][columna];
				int value = boxWithOutPower.getValue();
				Box BoxWithPower = getRamdomBox();

				if (!boxWithOutPower.hasApower() && value > 1 && value <width*10) {
					setBox(fila, columna, BoxWithPower, value);
					contador++;
				}
			}
		}
		else if (numSnakes >0){
			while (contador < porC) {
				int[] filaCol = getRamdoms();
				fila = filaCol[0];
				columna = filaCol[1];

				Box boxWithOutPower = boxs[fila][columna];
				int value = boxWithOutPower.getValue();
				Box BoxWithPower = getRamdomBoxNoStairs();

				if (!boxWithOutPower.hasApower() && value > 1 & value < width*10) {
					setBox(fila, columna, BoxWithPower, value);
					contador++;
				}
			}
		}else if(numStairs >0 ){
			while (contador < porC) {
				int[] filaCol = getRamdoms();
				fila = filaCol[0];
				columna = filaCol[1];

				Box boxWithOutPower = boxs[fila][columna];
				int value = boxWithOutPower.getValue();
				Box BoxWithPower = getRamdomBoxNoSnakes();

				if (!boxWithOutPower.hasApower() && value > 1 && value < width*10) {
					setBox(fila, columna, BoxWithPower, value);
					contador++;
				}
			}
		}else {
			while (contador < porC) {
				int[] filaCol = getRamdoms();
				fila = filaCol[0];
				columna = filaCol[1];

				Box boxWithOutPower = boxs[fila][columna];
				int value = boxWithOutPower.getValue();
				Box BoxWithPower = getRamdomBoxNoItems();

				if (!boxWithOutPower.hasApower() && value > 1 && value<width*10) {
					setBox(fila, columna, BoxWithPower, value);
					contador++;
				}
			}
		}
	}

	private void setBox(int fila,int columna, Box box,int value) {
		boxs[fila][columna] = box;
		boxs[fila][columna].setValue(value);
	}

	public ArrayList<Dado> getDados(){
		return dados;
	}

	public int[] searchRowAndColumn(int cantidad) {
		int[] posiciones = new int[2];
		if(cantidad <= 1) {
			posiciones[0] = 0;
			posiciones[1] = 1;
		}
		for (int i=0;i< width;i++){
			for (int j=0;j<width;j++){
				if (boxs[i][j].getValue() == cantidad){
					posiciones[0] = i;
					posiciones[1] = j;
				}
			}
		}
		return posiciones;
	}
	public Box searchBox(int cantidad){
		if(cantidad <= 1) {
			return boxs[width-1][0];
		}
		for (int i = 0; i < width; i++) {
			for (Box b : boxs[i]) {
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
				return player.getFichas().get(0);
			}
		}
		return null;
	}

	public Snake searchSnake(Ficha ficha) throws StairsAndSnakesException{
		int value = ficha.getBox().getValue();
		int diferencia, menorSerpiente = width*width;
		int index = width;
		for (int i=0; i<width;i++){
			for(Box b: boxs[i]){
				if(b.hasAnyTramp() && b.getItem() instanceof Snake){
					Tramp tramp = (Snake) b.getItem();
					if (tramp.getCasillaInicial().getValue() < value) {
						Box inicio = tramp.getCasillaInicial();
						diferencia = value - inicio.getValue();
						if (diferencia < menorSerpiente){
							menorSerpiente = diferencia;
							index = inicio.getValue();
						}
					}
				}
			}
		}
		if (menorSerpiente == width*width) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_FOUND_SNAKE);
		Item snake = searchBox(index).getItem();
		return (Snake) snake;
	}

	public Stair searchStair(Ficha ficha) throws StairsAndSnakesException{
		int value = ficha.getBox().getValue();
		int diferencia, menorEscalera = width*width;
		int index = width;
		for (int i=0; i<width;i++){
			for(Box b: boxs[i]){
				if(b.hasAnyTramp() && b.getItem() instanceof Stair){
					Tramp tramp = (Stair) b.getItem();
					if (tramp.getCasillaInicial().getValue() > value) {
						Box inicio = tramp.getCasillaInicial();
						diferencia = inicio.getValue() - value;
						if (diferencia < menorEscalera){
							menorEscalera = diferencia;
							index = inicio.getValue();
						}
					}
				}
			}
		}
		if (menorEscalera == width*width) throw new StairsAndSnakesException(StairsAndSnakesException.NOT_FOUND_STAIR);
		Item stair = searchBox(index).getItem();
		return (Stair) stair;
	}

}

