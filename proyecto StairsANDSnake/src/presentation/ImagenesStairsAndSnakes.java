package presentation;

import java.awt.*;
import javax.swing.*;

public class ImagenesStairsAndSnakes extends JPanel {
	
		String nombreImagen;
		
		public ImagenesStairsAndSnakes(String nombreImagen) {
			this.nombreImagen = nombreImagen;
		}
		
		@Override
		public void paint(Graphics g) {
			Image fondo = new ImageIcon(getClass().getResource("Imagenes/"+nombreImagen+".png")).getImage();
			g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	

}
