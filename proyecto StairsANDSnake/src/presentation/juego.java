package presentation;

import domain.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import domain.Box;


public class juego extends JFrame {
	private StairsAndSnakes stairsandsnakes;
	private JMenuItem nuevo, abrir, salvar, salir, cambiocolor;
	private JPanel tableroJuego, jugador1, jugador2;
	private JLabel nombre1, color1, numEsc1, numSer1, numCas1, maxCas1, numMod1;
	private JLabel nombre2, color2, numEsc2, numSer2, numCas2, maxCas2, numMod2;
	private JLabel nom1, col1, esca1, serp1, casEsp1, maxcas1, mod1;
	private JLabel nom2, col2, esca2, serp2, casEsp2, maxcas2, mod2;
	private JPanel[][] casillas;
	private JButton lanzar, finalizar, volver;
	private ImageIcon cara1, cara2, cara3, cara4, cara5, cara6;
	private JLabel lcaras;
	private JButton player1, player2;

	public juego(String nombre1, String nombre2, String coloruno, String colordos, String tipoRival, int casilla, int modificador, String tablero, String escalera, String serpiente, String transformar, String dificultad) throws StairsAndSnakesException {
		nom1 = new JLabel(nombre1);
		nom2 = new JLabel(nombre2);
		col1 = new JLabel(coloruno);
		col2 = new JLabel(colordos);
		prepareStairsAndSnakes(nombre1, nombre2, casilla, modificador, tablero, escalera, serpiente, transformar, coloruno, colordos, tipoRival,dificultad);
		prepareElements();
		prepareActions();
		if (stairsandsnakes.getTablero().getJugador("Machine") != null) movePlayer();
	}

	private void prepareStairsAndSnakes(String nombre1, String nombre2, int casilla, int modificador, String tablero, String escalera, String serpiente, String transformar, String coloruno, String colordos, String tipoRival,String dificultad) throws StairsAndSnakesException {
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<Color> colores = new ArrayList<>();
		boolean trans;
		nombres.add(nombre1);
		nombres.add(nombre2);
		colores.add(verifyColor(coloruno));
		colores.add(verifyColor(colordos));
		trans = transformar.equals("Yes");
		if (tablero.equals("") || escalera.equals("") || serpiente.equals(""))
			throw new StairsAndSnakesException(StairsAndSnakesException.NOT_ALL_SPACES_FULL);

		int tab = Integer.parseInt(tablero);
		int ser = Integer.parseInt(serpiente);
		int esc = Integer.parseInt(escalera);
		stairsandsnakes = new StairsAndSnakes(nombres, colores, tipoRival, tab, ser, esc, trans, casilla, modificador,dificultad);
	}

	private void prepareElements() {
		setLayout(null);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		setMinimumSize(new Dimension(1045, 665));
		setSize(1045, 665);
		setLocationRelativeTo(null);
		setResizable(false);
		prepareElementsMenu();
		prepareElementstablero();
		prepareElementsjugadores();
		prepareElementsInformacion();
		prepareElementsTokens();
		prepareElementsDade();
		prepareSpecialBoxes();
	}

	private void prepareElementsMenu() {
		JMenuBar herramientas = new JMenuBar();
		JMenu Menu = new JMenu("Menu"), settings = new JMenu("configuracion");
		nuevo = new JMenuItem("New");
		salvar = new JMenuItem("Save");
		abrir = new JMenuItem("Open");
		salir = new JMenuItem("Exit");
		cambiocolor = new JMenuItem("Cambiar color");
		settings.add(cambiocolor);
		herramientas.add(Menu);
		herramientas.add(settings);
		Menu.add(salvar);
		Menu.add(new JSeparator());
		Menu.add(abrir);
		Menu.add(new JSeparator());
		Menu.add(salir);
		setJMenuBar(herramientas);
	}

	private void prepareElementstablero() {
		Tablero tablero = stairsandsnakes.getTablero();
		tableroJuego = new JPanel();
		int tamano = tablero.getWidth();
		tableroJuego.setLayout(new GridLayout(tamano, tamano));
		casillas = new JPanel[tamano][tamano];
		JPanel casilla;
		for (int i = 0; i < tamano; i++) {
			for (int j = 0; j < tamano; j++) {
				casilla = new JPanel(new BorderLayout());
				casilla.setBorder(new LineBorder(Color.black));
 				casillas[i][j] = casilla;
				tableroJuego.add(casilla);
			}
		}
		prepareNumbers(tamano);
		add(tableroJuego);
		tableroJuego.setBounds(10, 10, (5 * getWidth()) / 7, getHeight() - getHeight() / 8);
	}

	private void prepareNumbers(int tamano) {
		if (tamano % 2 != 0) prepareNumbersOdd(tamano);
		else {
			int numCasilla = 1;
			for (int i = tamano - 1; i >= 0; i--) {
				if (i % 2 != 0) {
					for (int j = 0; j <= tamano - 1; j++) {
						casillas[i][j].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
						if (numCasilla % 2 != 0) {
							casillas[i][j].setBackground(Color.gray);
						} else {
							casillas[i][j].setBackground(Color.white);
						}
						numCasilla++;
					}
				} else {
					for (int k = tamano - 1; k >= 0; k--) {
						casillas[i][k].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
						if (numCasilla % 2 != 0) {
							casillas[i][k].setBackground(Color.gray);
						} else {
							casillas[i][k].setBackground(Color.white);
						}
						numCasilla++;
					}
				}
			}
		}
	}

	private void prepareNumbersOdd(int tamano) {
		int numCasilla = 1;
		for (int i = tamano - 1; i >= 0; i--) {
			if (i % 2 == 0) {
				for (int j = 0; j <= tamano - 1; j++) {
					casillas[i][j].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					if (numCasilla % 2 != 0) {
						casillas[i][j].setBackground(Color.gray);
					} else {
						casillas[i][j].setBackground(Color.white);
					}
					numCasilla++;
				}
			} else {
				for (int k = tamano - 1; k >= 0; k--) {
					casillas[i][k].add(new JLabel(Integer.toString(numCasilla)), BorderLayout.SOUTH);
					if (numCasilla % 2 != 0) {
						casillas[i][k].setBackground(Color.gray);
					} else {
						casillas[i][k].setBackground(Color.white);
					}
					numCasilla++;
				}
			}
		}
	}

	private void prepareSpecialBoxes() {
		Tablero tablero = stairsandsnakes.getTablero();
		for (int i = 0; i < tablero.getWidth(); i++) {
			for (Box b : tablero.getBoxs()[i]) {
				if (b.hasApower()) {
					int v = b.getValue();
					int[] posiciones = tablero.searchRowAndColumn(v);
					changeImagen(posiciones, b);
				}
			}
		}
	}

	private void changeImagen(int[] posiciones, Box casilla) {
		JLabel fondo = null;
		int fila = posiciones[0];
		int columna = posiciones[1];
		JPanel cas = casillas[fila][columna];

		if (casilla instanceof Death) {
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "muerte" + ".png")));
		} else if (casilla instanceof Jumper) {
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "saltarina" + ".png")));
		} else if (casilla instanceof JumperInverse) {
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "saltarinainversa" + ".png")));
		} else if (casilla instanceof Movement) {
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "movimiento" + ".png")));
		} else if (casilla instanceof Recoil) {
			System.out.println(casilla);
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "devolver" + ".png")));
		} else if (casilla instanceof Question) {
			fondo = new JLabel(new ImageIcon(getClass().getResource("Imagenes/" + "pregunta" + ".png")));
		}
		assert fondo != null;
		fondo.setPreferredSize(new Dimension(cas.getWidth(), cas.getHeight()));
		cas.add(fondo, BorderLayout.CENTER);
	}



	private void prepareElementsjugadores(){
		jugador1 = new JPanel();
		jugador1.setBackground(Color.white);
		jugador1.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Jugador 1")));
		jugador1.setLayout(new GridLayout(7,2));
		serp1 = new JLabel("0");
		serp2 = new JLabel("0");
		esca1 = new JLabel("0");
		esca2 = new JLabel("0");
		maxcas1 = new JLabel( "1");
		maxcas2 = new JLabel("1");
		casEsp1 = new JLabel("0");
		casEsp2 = new JLabel("0");
		mod1 = new JLabel("0");
		mod2 = new JLabel("0");
		numMod1 = new JLabel("Modificadores: ");
		nombre1 = new JLabel("Nombre: ");
		color1 = new JLabel("Color: ");
		numEsc1 = new JLabel("Escaleras: ");
		numSer1 = new JLabel("Serpientes: ");
		maxCas1 = new JLabel("Maxima Casilla: ");
		numCas1 = new JLabel("Especiales: ");
		numMod2 = new JLabel("Modificadores: ");
		nombre2 = new JLabel("Nombre: ");
		color2 = new JLabel("Color: ");
		numEsc2 = new JLabel("Escaleras: ");
		numSer2 = new JLabel("Serpientes: ");
		maxCas2 = new JLabel("Maxima Casilla: ");
		numCas2 = new JLabel("Especiales: ");
		jugador1.add(nombre1);
		jugador1.add(nom1);
		jugador1.add(color1);
		jugador1.add(col1);
		jugador1.add(numEsc1);
		jugador1.add(esca1);
		jugador1.add(numSer1);
		jugador1.add(serp1);
		jugador1.add(maxCas1);
		jugador1.add(maxcas1);
		jugador1.add(numMod1);
		jugador1.add(mod1);
		jugador1.add(numCas1);
		jugador1.add(casEsp1);
		add(jugador1);
		jugador1.setBounds(tableroJuego.getWidth()+20,10,getWidth()- tableroJuego.getWidth()-40,getHeight()/2 - getHeight()/4);

		jugador2 = new JPanel();
		jugador2.setBackground(Color.white);
		jugador2.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Jugador 2")));
		jugador2.setLayout(new GridLayout(7,2));
		jugador2.add(nombre2);
		jugador2.add(nom2);
		jugador2.add(color2);
		jugador2.add(col2);
		jugador2.add(numEsc2);
		jugador2.add(esca2);
		jugador2.add(numSer2);
		jugador2.add(serp2);
		jugador2.add(maxCas2);
		jugador2.add(maxcas2);
		jugador2.add(numMod2);
		jugador2.add(mod2);
		jugador2.add(numCas2);
		jugador2.add(casEsp2);
		add(jugador2);
		jugador2.setBounds(tableroJuego.getWidth()+20,jugador1.getHeight() + 20,getWidth()- tableroJuego.getWidth()-40,getHeight()/2 - getHeight()/4);
	}

	private void prepareElementsInformacion(){
		lanzar = new JButton("Lanzar Dado");
		lanzar.setBounds(tableroJuego.getWidth()+20,tableroJuego.getHeight()-50,jugador2.getWidth()/2 -20,25);
		add(lanzar);
		finalizar = new JButton("Finalizar Juego");
		finalizar.setBounds(lanzar.getX()+lanzar.getWidth()/2,lanzar.getY() + lanzar.getHeight() + 10,lanzar.getWidth()+ 40,25);
		add(finalizar);
		volver = new JButton("Volver");
		volver.setBounds(lanzar.getX() +jugador2.getWidth()-lanzar.getWidth(),lanzar.getY(),lanzar.getWidth(),25);
		add(volver);
	}

	private void prepareElementsDade(){
		cara1 = new ImageIcon(getClass().getResource("Imagenes/"+"cara1"+".png"));
		cara2 = new ImageIcon(getClass().getResource("Imagenes/"+"cara2"+".png"));
		cara3 = new ImageIcon(getClass().getResource("Imagenes/"+"cara3"+".png"));
		cara4 = new ImageIcon(getClass().getResource("Imagenes/"+"cara4"+".png"));
		cara5 = new ImageIcon(getClass().getResource("Imagenes/"+"cara5"+".png"));
		cara6 = new ImageIcon(getClass().getResource("Imagenes/"+"cara6"+".png"));

		lcaras = new JLabel(cara2);
		lcaras.setBounds(jugador2.getX() + lanzar.getWidth()/2 + 11,jugador2.getY() + jugador2.getHeight()+20,128,128);
		add(lcaras);
	}

	private void prepareElementsTokens(){
		Color mycolor;
		int tamano = stairsandsnakes.getTablero().getWidth();
		JPanel casillaInicial = casillas[tamano-1][0];
		player1 = new JButton();
		mycolor = verifyColor(col1.getText());
		player1.setBackground(mycolor);
		player1.setEnabled(false);

		player2 = new JButton();
		mycolor = verifyColor(col2.getText());
		player2.setBackground(mycolor);
		player2.setEnabled(false);
		casillaInicial.add(player1,BorderLayout.EAST);
		casillaInicial.add(player2,BorderLayout.WEST);
	}

	private Color verifyColor(String color){
		Color myColor = switch (color) {
			case "Azul" -> Color.blue;
			case "Verde" -> Color.GREEN;
			case "Rojo" -> Color.RED;
			case "Amarillo" -> Color.yellow;
			default -> Color.cyan;
		};
		return myColor;
	}

	private void prepareLines(Graphics g){
		Tablero tablero = stairsandsnakes.getTablero();
		for (int i=0; i<tablero.getWidth();i++){
			for (Box b: tablero.getBoxs()[i]){
				if (b.hasAnyTramp()){
					Tramp trampa = (Tramp) b.getItem();
					Box casIni = trampa.getCasillainicial();
					Box casFin = trampa.getCasillafin();
					paintLine(casIni,casFin,trampa,g);
				}
			}
		}
	}

	private void paintLine(Box casIni, Box CasFin, Tramp tramp, Graphics g){
		int xi, xf;
		int yi, yf;
		Tablero tablero = stairsandsnakes.getTablero();
		int[] posIni = tablero.searchRowAndColumn(casIni.getValue());
		int[] posFin = tablero.searchRowAndColumn(CasFin.getValue());
		JPanel head = casillas[posIni[0]][posIni[1]];
		JPanel tail = casillas[posFin[0]][posFin[1]];

		xi = head.getX() + head.getWidth()/2;
		yi = head.getY() + head.getWidth();
		xf = tail.getX() + tail.getWidth()/2;
		yf = tail.getY() + tail.getWidth();
		if (tramp instanceof Snake){
			g.setColor(Color.green);
		}else if(tramp instanceof Stair){
			g.setColor(Color.orange);
		}
		g.drawLine(xi,yi,xf,yf);
	}



	private void prepareActions(){
		ActionListener oyenteDeColor = e -> changeColor();
		cambiocolor.addActionListener(oyenteDeColor);
		ActionListener oyenteDeVolver = e -> createOption();
		volver.addActionListener(oyenteDeVolver);
		ActionListener oyenteDeFinalizar = e -> exit();
		finalizar.addActionListener(oyenteDeFinalizar);

		if (stairsandsnakes.getTablero().getJugador("Machine") == null) {
			ActionListener oyenteDeMover = e -> movePlayer();
			lanzar.addActionListener(oyenteDeMover);
		} else {
			ActionListener oyenteDeMover = e -> movePlayers();
			lanzar.addActionListener(oyenteDeMover);
		}
	}

	private void changeColor(){
		String[] opciones = {"Rojo","Verde","Gris"};
		int option = JOptionPane.showOptionDialog(this,"Que color desea? ", "Cambio Color",
				JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);

		if (option == 0){
			cambiarColor(Color.RED,Color.pink);
		}else if (option == 1){
			cambiarColor(Color.getHSBColor(0,200,55),Color.GREEN);
		}else {
			cambiarColor(Color.gray,Color.white);
		}
	}

	private void cambiarColor(Color oscuro,Color claro) {
		Tablero tablero = stairsandsnakes.getTablero();
		int tamano = tablero.getWidth();
		int value = 1;
		for (int i = 0; i < tamano; i++) {
			if(i%2!=0){
				for(int j=0;j<=tamano-1;j++){
					if(value%2!=0){
						casillas[i][j].setBackground(oscuro);
					}else {
						casillas[i][j].setBackground(claro);
					}
					value++;
				}
			}else {
				for(int k=tamano-1;k>=0;k--){
					if(value%2!=0){
						casillas[i][k].setBackground(oscuro);
					}else {
						casillas[i][k].setBackground(claro);
					}
					value++;
				}
			}
		}
	}

	private void createOption(){
		if(JOptionPane.showConfirmDialog(this,"Desea volver a empezar la partida? ")==0) {
			Opciones op = new Opciones();
			op.setVisible(true);
			dispose();
		}
	}

	private void exit(){
		if(JOptionPane.showConfirmDialog(this,"Desea acabar el juego? ")==0){
			System.exit(0);
		}
	}

	private void movePlayers(){
		String name;
		for (JLabel jLabel : Arrays.asList(nom2, nom1)) {
			name = jLabel.getText();
			stairsandsnakes.changeDados(name);
			if (name.equals(nom2.getText())){
				paintDados();
			}
			try {
				stairsandsnakes.movePlayer(name);
				if(stairsandsnakes.getWin()) System.exit(0);
			} catch (StairsAndSnakesException e){
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
		moveTokens();
		repaint();
		updateJLabels();
	}

	private void movePlayer() {
		String name;
		int turno = stairsandsnakes.getTurno();
		if (turno %2 == 0){
			name = nom1.getText();
			stairsandsnakes.changeDados(name);
			paintDados();
			try {
				stairsandsnakes.movePlayer(name);
				if(stairsandsnakes.getWin()) System.exit(0);
			} catch (StairsAndSnakesException e) {
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}else {
			name = nom2.getText();
			stairsandsnakes.changeDados(name);
			paintDados();
			try {
				stairsandsnakes.movePlayer(name);
				if(stairsandsnakes.getWin()) System.exit(0);
			} catch (StairsAndSnakesException e) {
				JOptionPane.showMessageDialog(this,e.getMessage());
			}

		}
		moveTokens();
		repaint();
		updateJLabels();
	}

	private void updateJLabels(){
		Tablero tablero = stairsandsnakes.getTablero();
		Ficha ficha1, ficha2;
		ficha1 = tablero.getJugador(nom1.getText()).getFichas().get(0);
		ficha2 = tablero.getJugador(nom2.getText()).getFichas().get(0);

		updateStairs(ficha1.getStairs(),ficha2.getStairs());
		updateSnakes(ficha1.getSnakes(),ficha2.getSnakes());
		updateMaxBox(ficha1.getMaxCas(),ficha2.getMaxCas());
		updateEspecialBox(ficha1.getNumSpecialBox(),ficha2.getNumSpecialBox());
		updateMods(ficha1.getNumMod(),ficha2.getNumMod());
	}

	private void updateStairs(int stairs1, int stairs2){
		esca1.setText(Integer.toString(stairs1));
		esca2.setText(Integer.toString(stairs2));
	}

	private void updateSnakes(int snakes1, int snakes2){
		serp1.setText(Integer.toString(snakes1));
		serp2.setText(Integer.toString(snakes2));
	}

	private void updateMaxBox(int maxCas1, int maxCas2){
		maxcas1.setText(Integer.toString(maxCas1));
		maxcas2.setText(Integer.toString(maxCas2));
	}

	private void updateEspecialBox(int specialCas1, int specialCas2){
		casEsp1.setText(Integer.toString(specialCas1));
		casEsp2.setText(Integer.toString(specialCas2));
	}

	private void updateMods(int modi1, int modi2){
		mod1.setText(Integer.toString(modi1));
		mod2.setText(Integer.toString(modi2));
	}

	private void paintDados(){
		Tablero tablero = stairsandsnakes.getTablero();
		Dado dado = tablero.getDados().get(0);
		int valor = dado.getDado().getNumero();
		switch (valor) {
			case 1 -> lcaras.setIcon(cara1);
			case 2 -> lcaras.setIcon(cara2);
			case 3 -> lcaras.setIcon(cara3);
			case 4 -> lcaras.setIcon(cara4);
			case 5 -> lcaras.setIcon(cara5);
			case 6 -> lcaras.setIcon(cara6);
		}
	}

	private void moveTokens(){
		Tablero tablero = stairsandsnakes.getTablero();

		for(int i=0;i<tablero.getWidth();i++){
			for(JPanel c: casillas[i]){
				if (c.contains(player1.getLocation())){
					c.remove(player1);
				}
				if (c.contains(player2.getLocation()) && stairsandsnakes.getTurno() > 1){
					c.remove(player2);
				}
			}
		}
		paintTokens();
	}

	private void paintTokens(){
		HashMap<Color,Ficha> tokens;
		Tablero tablero = stairsandsnakes.getTablero();
		Ficha ficha;
		JPanel casNueva;

		for (int i=0; i<tablero.getWidth();i++){
			for (Box b: tablero.getBoxs()[i]){
				tokens = b.getTokens();

				ficha = tokens.get(verifyColor(col1.getText()));
				if (ficha !=null) {
					int value = ficha.getBox().getValue();
					int[] rowAndCol = tablero.searchRowAndColumn(value);
					casNueva = casillas[rowAndCol[0]][rowAndCol[1]];
					casNueva.add(player1,BorderLayout.WEST);
				}

				ficha = tokens.get(verifyColor(col2.getText()));
				if(ficha != null) {
					int value = ficha.getBox().getValue();
					int[] rowAndCol = tablero.searchRowAndColumn(value);
					casNueva = casillas[rowAndCol[0]][rowAndCol[1]];
					casNueva.add(player2,BorderLayout.EAST);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		prepareLines(g);
	}
}