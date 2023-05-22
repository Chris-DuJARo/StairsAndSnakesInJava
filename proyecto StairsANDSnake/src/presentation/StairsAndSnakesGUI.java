package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class StairsAndSnakesGUI extends JFrame{
JButton nuevo,abrir,salir;
	
	private StairsAndSnakesGUI() {
		super("Inicio StairsAndSnakes");
		prepararElementos();
		prepararAcciones();
	}
	
    private void prepararElementos() {   
    	setSize(1045, 665);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        ImagenesStairsAndSnakes ImagenesStairsAndSnakes = new ImagenesStairsAndSnakes("pantalla1");
        Font fuente = new Font("oxford", 0, 20);
        LineBorder borde = new LineBorder(Color.black, 2);
        ImagenesStairsAndSnakes.setLayout(null);
        nuevo = new JButton("Nuevo Juego");
        nuevo.setBounds(390, 318, 260, 45);
        nuevo.setBackground(Color.white);
        nuevo.setBorder(borde);
        nuevo.setFont(fuente);
        abrir = new JButton("Abrir Juego");
        abrir.setBounds(390, 372, 260, 45);
        abrir.setBackground(Color.white);
        abrir.setBorder(borde);
        abrir.setFont(fuente);
        salir = new JButton("Salir");
        salir.setBounds(390, 426, 260, 45);
        salir.setBackground(Color.white);
        salir.setBorder(borde);
        salir.setFont(fuente);
        ImagenesStairsAndSnakes.add(nuevo);
        ImagenesStairsAndSnakes.add(abrir);
        ImagenesStairsAndSnakes.add(salir);
        add(ImagenesStairsAndSnakes);
    } 
    

    private void prepararAcciones(){   
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            
            public void windowClosing(WindowEvent e){
                accionSalir();
            }
        });
        nuevo.addActionListener(e -> accionNuevo());
        salir.addActionListener(e -> accionSalir());
        abrir.addActionListener(e -> accionAbrir());
        
    } 
    
    private void accionSalir() {   
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir del juego?", "Salir del juego", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.OK_OPTION){
            setVisible(false);
            System.exit(0);
        }
    } 
    
    private void accionNuevo() {
        dispose();
        Opciones op = new Opciones();
        op.setVisible(true);
    }
    
    private void accionAbrir() {   
        JFileChooser chooser = new JFileChooser();
        int open = chooser.showOpenDialog(null);
        if (open == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, file.getName());
        }
    }
    

	public static void main(String[] args) {
		StairsAndSnakesGUI gui = new StairsAndSnakesGUI();
	    gui.setVisible(true);
	} 
}