package presentation;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagenesStairsAndSnakes extends JPanel {
    String nombreImagen;

    public ImagenesStairsAndSnakes(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public void paint(Graphics g) {
        Image fondo = (new ImageIcon(this.getClass().getResource("Imagenes/" + this.nombreImagen + ".png"))).getImage();
        g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
        this.setOpaque(false);
        super.paint(g);
    }
}