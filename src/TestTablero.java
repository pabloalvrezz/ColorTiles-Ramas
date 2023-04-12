import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestTablero {

	public static void main(String[] args) {
		
		int col, fil, vid, por;
		
		//Se solicitan por pantalla los parametros necesarios para incializar el objeto Tablero
		String c = JOptionPane.showInputDialog(null,"Número de columnas:");
		String f = JOptionPane.showInputDialog(null,"Número de filas:");
		String v = JOptionPane.showInputDialog(null,"Número de vidas:");
		String p = JOptionPane.showInputDialog(null,"Porcentaje de casillas rellenas (0-100):");
		
		//Se dan valores por defecto en caso de dejar los parametros en blanco
		if (c.equals("")) col = 22;
		else col = Integer.parseInt(c);
		if (f.equals("")) fil = 23;
		else fil = Integer.parseInt(f);
		if (v.equals("")) vid = 5;
		else vid = Integer.parseInt(v);
		if (p.equals("")) por = 60;
		else por = Integer.parseInt(p);
		
		Tablero t = new Tablero(col,fil,por,vid);
		
		JFrame app = new JFrame("Tablero");
		
		
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setBounds(0, 0, 20*col+11, 20*fil+60);
		app.add(t);
		app.setVisible(true);
		
	}
}
