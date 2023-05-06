package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import domain.*;

public class StairsAndSnakesGUI extends JFrame {
    private JButton nuevo;
    private JButton abrir;
    private JButton salir;
    private StairsAndSnakes sas;

    private StairsAndSnakesGUI() {
        super("Inicio StairsAndSnakes");
        this.prepararElementos();
        this.prepararAcciones();
    }

    private void prepararElementos() {
        this.setSize(1045, 665);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        ImagenesStairsAndSnakes ImagenesStairsAndSnakes = new ImagenesStairsAndSnakes("Inicio");
        Font fuente = new Font("Perpetua", 0, 30);
        Cursor cursor = new Cursor(12);
        LineBorder borde = new LineBorder(Color.black, 2);
        ImagenesStairsAndSnakes.setLayout((LayoutManager)null);
        this.nuevo = new JButton("Nuevo Juego");
        this.nuevo.setBounds(390, 264, 260, 45);
        this.nuevo.setCursor(cursor);
        this.nuevo.setBackground(Color.white);
        this.nuevo.setBorder(borde);
        this.nuevo.setFont(fuente);
        this.abrir = new JButton("Abrir Juego");
        this.abrir.setBounds(390, 318, 260, 45);
        this.abrir.setCursor(cursor);
        this.abrir.setBackground(Color.white);
        this.abrir.setBorder(borde);
        this.abrir.setFont(fuente);
        this.salir = new JButton("Salir");
        this.salir.setBounds(390, 372, 260, 45);
        this.salir.setCursor(cursor);
        this.salir.setBackground(Color.white);
        this.salir.setBorder(borde);
        this.salir.setFont(fuente);
        ImagenesStairsAndSnakes.add(this.nuevo);
        ImagenesStairsAndSnakes.add(this.abrir);
        ImagenesStairsAndSnakes.add(this.salir);
        this.add(ImagenesStairsAndSnakes);
    }

    private void prepararAcciones() {
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StairsAndSnakesGUI.this.accionSalir();
            }
        });
        this.nuevo.addActionListener((e) -> {
            this.accionNuevo();
        });
        this.salir.addActionListener((e) -> {
            this.accionSalir();
        });
        this.abrir.addActionListener((e) -> {
            this.accionAbrir();
        });
    }

    private void accionSalir() {
        int respuesta = JOptionPane.showConfirmDialog((Component)null, "¿Seguro que quieres salir del juego?", "Salir del juego", 2);
        if (respuesta == 0) {
            this.setVisible(false);
            System.exit(0);
        }

    }

    private void accionNuevo() {
        throw new Error("Unresolved compilation problem: \n\tOpciones cannot be resolved to a type\n");
    }

    private void accionAbrir() {
        JFileChooser chooser = new JFileChooser();
        int open = chooser.showOpenDialog((Component)null);
        if (open == 0) {
            File file = chooser.getSelectedFile();
            JOptionPane.showMessageDialog((Component)null, file.getName());
        }

    }

    public static void main(String[] args) {
        StairsAndSnakesGUI gui = new StairsAndSnakesGUI();
        gui.setVisible(true);
    }
}