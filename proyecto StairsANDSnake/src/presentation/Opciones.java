package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import domain.StairsAndSnakesException;


public class Opciones extends JDialog{
	private JComboBox<String> selectRival,selectcolor1,selectcolor2, selectdificultad, selecttransformar;
	private static final String[] COLOR = {"Azul", "Verde", "Rojo","Amarillo"};
	private JSpinner selectcasillas;
	private JComboBox selectmodificador;
	private JButton Jugar, Cancelar;
	private Font fuente;
	private Border borde;
	private Players players;
	private JLabel dificultad;
	private Imagen imagen;
	private JTextField uno, dos, selectescaleras, selectserpientes, selecttablero;


	public Opciones() {
		prepararElementos();
		prepararAcciones();
	}

	public void prepararElementos() {
		setSize(1045,665);
		setTitle("Opciones Stairs and Snakes");
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1,1));
		setResizable(false);
		imagen = new Imagen("pantalla2");
		borde = new LineBorder(Color.black, 2);
		imagen.setLayout(null);
		TitledBorder titulo = new TitledBorder(borde, "Opciones Stairs and Snakes");
		fuente = new Font("oxford", Font.BOLD, 30);
		titulo.setTitleFont(fuente);
		imagen.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), titulo));
		fuente = new Font("oxford", Font.BOLD, 20);
		prepararElementosPlayers();
		prepararElementosJuego();
		Jugar = new JButton("jugar");
		Jugar.setBounds(340, 550, 150, 50);
		Jugar.setBackground(Color.white);
		Jugar.setBorder(borde);
		Jugar.setFont(fuente);
		Cancelar = new JButton("Cancelar");
		Cancelar.setBounds(510, 550, 150, 50);
		Cancelar.setBackground(Color.white);
		Cancelar.setBorder(borde);
		Cancelar.setFont(fuente);
		imagen.add(Jugar);
		imagen.add(Cancelar);
        add(imagen);
	}

	private void prepararElementosPlayers() {
		players = new Players(COLOR[0],COLOR[1]);
		TitledBorder titulo = new TitledBorder(borde,"Players");
		titulo.setTitleFont(fuente);
        players.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), titulo));
		players.setLayout(null);
		players.setOpaque(false);
		players.setBounds(40, 40, 910, 250);
		JLabel rival = new JLabel("Rival ");
		dificultad = new JLabel("Dificultad");
		rival.setBounds(65, 60, 300, 30);
		rival.setFont(fuente);
		dificultad.setBounds(430, 60, 300, 30);
		dificultad.setFont(fuente);
		String[] rivales = {"Player","Machine"},tiporivales = {"Principiante","Aprendiz"};
		selectRival = new JComboBox<String>(rivales);
		selectRival.setSelectedItem(rivales[0]);
		selectRival.setBounds(185, 55, 180, 40);
		selectRival.setFont(fuente);
		selectRival.setBackground(Color.white);
		selectRival.setBorder(borde);
		selectdificultad = new JComboBox<String>(tiporivales);
		selectdificultad.setSelectedItem(tiporivales[0]);
		selectdificultad.setBounds(550, 55, 180, 40);
		selectdificultad.setFont(fuente);
		selectdificultad.setBackground(Color.white);
		selectdificultad.setBorder(borde);
		selectdificultad.setVisible(false);
		dificultad.setVisible(false);
		players.add(rival);
		players.add(selectRival);
		players.add(dificultad);
		players.add(selectdificultad);
		prepararElementosPlayer1();
		prepararElementosPlayer2();
		imagen.add(players);

	}

	private void prepararElementosPlayer1() {
		JLabel player = new JLabel("Player 1");
		player.setBounds(230, 100, 200, 35);
		player.setFont(fuente);
		JLabel nombre = new JLabel("Name");
		nombre.setBounds(65, 145, 210, 35);
		nombre.setFont(fuente);
		uno = new JTextField();
		uno.setBounds(185, 145, 210, 35);
		uno.setFont(fuente);
		JLabel color = new JLabel("Color");
		color.setBounds(65, 190, 210, 35);
		color.setFont(fuente);
		selectcolor1 = new JComboBox<String>(COLOR);
		selectcolor1.setBounds(185, 190, 210, 35);
		selectcolor1.setFont(fuente);
		selectcolor1.setSelectedItem(COLOR[0]);
		selectcolor1.setBackground(Color.white);
		selectcolor1.setBorder(borde);
		players.add(player);
		players.add(uno);
		players.add(nombre);
		players.add(color);
		players.add(selectcolor1);
	}

	private void prepararElementosPlayer2() {
		JLabel player = new JLabel("Player 2");
		player.setBounds(640, 100, 200, 35);
		player.setFont(fuente);
		JLabel nombre = new JLabel("Name");
		nombre.setBounds(515, 145, 100, 35);
		nombre.setFont(fuente);
		dos = new JTextField();
		dos.setBounds(635, 145, 210, 35);
		dos.setFont(fuente);
		JLabel color = new JLabel("Color");
		color.setBounds(515, 190, 100, 35);
		color.setFont(fuente);
		selectcolor2 = new JComboBox<String>(COLOR);
		selectcolor2.setBounds(635, 190, 210, 35);
		selectcolor2.setFont(fuente);
		selectcolor2.setSelectedItem(COLOR[1]);
		selectcolor2.setBackground(Color.white);
		selectcolor2.setBorder(borde);
		players.add(player);
		players.add(dos);
		players.add(nombre);
		players.add(color);
		players.add(selectcolor2);
	}

	private void prepararElementosJuego() {
    	JLabel juego = new JLabel();
    	TitledBorder titulo = new TitledBorder(borde,"Juego");
    	titulo.setTitleFont(fuente);
    	juego.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),titulo));
    	juego.setLayout(null);
    	juego.setOpaque(false);
    	juego.setBounds(40, 290, 910, 250);
    	JLabel casillas = new JLabel("% Specials"), modificadores = new JLabel("% Modifiers"), escaleras = new JLabel("Number of Stairs"), serpientes = new JLabel ("Number of Snakes");
    	JLabel transformar = new JLabel("Transform"), tablero = new JLabel("Board Size");
    	casillas.setBounds(20, 55, 400, 30);
    	casillas.setFont(fuente);
    	modificadores.setBounds(20, 120, 400, 30);
    	modificadores.setFont(fuente);
    	tablero.setBounds(25, 190, 400, 30);
    	tablero.setFont(fuente);
    	escaleras.setBounds(400, 55, 400, 30);
    	escaleras.setFont(fuente);
    	serpientes.setBounds(400, 120, 400, 30);
    	serpientes.setFont(fuente);
    	transformar.setBounds(440, 190, 400, 30);
    	transformar.setFont(fuente);
    	String[] transformarse = {"Yes", "No"}, numbermod  = {"0","1","2","3","4","5","6"};
    	SpinnerNumberModel modi = new SpinnerNumberModel(0, 0, 100, 1);
		selectcasillas = new JSpinner(modi);
		//selectcasillas.setSelectedItem(numbermod[0]);
		selectcasillas.setBounds(150,  50, 210, 40);
		selectcasillas.setFont(fuente);
		selectcasillas.setBackground(Color.white);
		selectcasillas.setBorder(borde);

		selectmodificador = new JComboBox<>(numbermod);
		selectmodificador.setBounds(150, 120, 210, 40);
		selectmodificador.setFont(fuente);
		selectmodificador.setBackground(Color.white);
		selectmodificador.setBorder(borde);
		//selectmodificador.setValue(0);

    	selecttablero = new JTextField();
    	selecttablero.setBounds(150, 190, 210, 40);
    	selecttablero.setFont(fuente);
    	selecttablero.setBorder(borde);

		selectescaleras = new JTextField();
    	selectescaleras.setBounds(600, 50, 210, 40);
    	selectescaleras.setFont(fuente);
		selectescaleras.setBorder(borde);

		selectserpientes = new JTextField();
    	selectserpientes.setBounds(600, 120, 210, 40);
    	selectserpientes.setFont(fuente);
		selectserpientes.setBorder(borde);

    	selecttransformar = new JComboBox<String>(transformarse);
    	selecttransformar.setSelectedItem(transformarse[0]);
    	selecttransformar.setBounds(600, 190, 210, 40);
    	selecttransformar.setFont(fuente);
    	selecttransformar.setBackground(Color.white);
    	selecttransformar.setBorder(borde);
    	juego.add(casillas);
    	juego.add(selectcasillas);
    	juego.add(modificadores);
    	juego.add(selectmodificador);
    	juego.add(tablero);
    	juego.add(selecttablero);
    	juego.add(escaleras);
    	juego.add(selectescaleras);
    	juego.add(serpientes);
    	juego.add(selectserpientes);
    	juego.add(transformar);
    	juego.add(selecttransformar);
    	imagen.add(juego);
	}

	private void prepararAcciones(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                accionSalir();
            }
        });
        selectRival.addItemListener(e -> accionRival());
        Jugar.addActionListener(e -> accionJugar());
        Cancelar.addActionListener(e -> accionSalir());
    }

	private void accionRival() {
		if (selectRival.getSelectedItem().equals("Machine")) {
			dificultad.setVisible(true);
			selectdificultad.setVisible(true);
			uno.setText("Machine");
			uno.setEnabled(false);
		}else {
			dificultad.setVisible(false);
			selectdificultad.setVisible(false);
			if(uno.getText().equals("Machine"))uno.setText("");
			if(dos.getText().equals("Machine"))dos.setText("");
			dos.setEnabled(true);
			uno.setEnabled(true);
		}
	}

	private void accionSalir(){
    	dispose();
    }

	private void accionJugar() {
		String nombre1 = uno.getText();
		String nombre2 = dos.getText();
		String coloruno = (String) selectcolor1.getSelectedItem();
		String colordos = (String) selectcolor2.getSelectedItem();
		String tipoRival = (String)selectRival.getSelectedItem();
		int casilla = (int) selectcasillas.getValue();
		int modificador = Integer.parseInt((String) selectmodificador.getSelectedItem());
		String tablero = selecttablero.getText();
		String escalera = selectescaleras.getText();
		String serpiente = selectserpientes.getText();
		String transformar = (String)selecttransformar.getSelectedItem();
		String dificultad = (String)selectdificultad.getSelectedItem();
		try {
			juego eys;
			eys = new juego(nombre1, nombre2, coloruno, colordos, tipoRival, casilla, modificador, tablero, serpiente, escalera, transformar, dificultad);
			eys.setVisible(true);
			dispose();
		}catch (StairsAndSnakesException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

}

class Players extends JPanel{

	String color1;
	String color2;

	public Players(String color1,String color2) {
		this.color1 = color1;
		this.color2 = color2;
	}
}