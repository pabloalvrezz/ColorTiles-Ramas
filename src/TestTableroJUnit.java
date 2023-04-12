import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Clase en la que haremos una serie de pruebas para comprobar
 * los diferentes valores que el usuario puede introducir dentro del constrcutor
 * @author Pablo Alvarez
 * @since 27/03/2023
 * @version 1.0
 */
class TestTableroJUnit 
{
	/**
	 * Metodo al cual le meteremos una serie de valores, todos ellos correctos
	 * @return vacio
	 * @param ninguno
	 */

	@Test
	public void testConstructor() 
	{
		// Given
		int col = 10;
		int fil = 20;
		int vid = 3;
		int por = 70;

		// Creamos el objeto
		Tablero t = new Tablero(col, fil, por, vid);

		// Probamos getters
		Assertions.assertEquals(col, t.getColumnas());
		Assertions.assertEquals(fil, t.getFilas());
		Assertions.assertEquals(vid, t.getVidas());
		Assertions.assertEquals(por, t.getPorcentaje());
	}

	/**
	 * Metodo al cual le meteremos una serie de valores, las columnas serán negativas
	 * y tendrá que dar error puesto que no podrá dibujar el cuadrado
	 * @return vacio
	 * @param ninguno
	 */
	@Test
	public void testConstructor2() 
	{
		// Given
		int col = -4;
		int fil = 10;
		int vid = 1;
		int por = 30;

		// Creamos el objeto
		Tablero t = new Tablero(col, fil, por, vid);

		// Probamos los getters
		Assertions.assertEquals(col, t.getColumnas());
		Assertions.assertEquals(fil, t.getFilas());
		Assertions.assertEquals(vid, t.getVidas());
		Assertions.assertEquals(por, t.getPorcentaje());
	}
	/**
	 * Metodo al cual le meteremos una serie de valores, las columnas serán negativas
	 * y tendrá que dar error puesto que no podrá dibujar el cuadrado
	 * @return vacio
	 * @param col, número de columnas
	 * @param col, número de filas
	 * @param col, número de vidas
	 * @param col, porcentaje de casillas
	 */
	@ParameterizedTest 
	@CsvSource({"j,4,5,70", "5,-6,1,0"})
	public void testConstructor3(int col, int fil, int vid, int por)
	{
		// Creamos el objeto
		Tablero t = new Tablero(col, fil, por, vid);

		// Probamos los getters
		Assertions.assertEquals(col, t.getColumnas());
		Assertions.assertEquals(fil, t.getFilas());
		Assertions.assertEquals(vid, t.getVidas());
		Assertions.assertEquals(por, t.getPorcentaje());
	}

}
