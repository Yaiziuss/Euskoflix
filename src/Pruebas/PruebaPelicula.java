/**
 * 
 */
package Pruebas;
import Euskoflix.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author yaiza
 *
 */
class PruebaPelicula {
	private Pelicula p1,p2,p3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		p1= new Pelicula("Capitana Marvel");
		p2= new Pelicula("Your Name");
		p3= new Pelicula("La Bella y la Bestia 2017");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		p1=null;
	}

	/**
	 * Con este método vamos aprobar tanto añadir etiqueta como get peso etiqueta
	 * Vamos a probar los siguientes casos:
	 * La etiqueta existe y se compara con su numero de apariciones
	 * La etiqueta existe y no se compara con su numero de apariciones
	 * La etiqueta no existe y no se compara con su número de apariciones
	 * La etiqueta no existe pero se compara con su número de apariciones
	 */
	@Test
	public void annadirEtiqueta() {
		p1.annadirEtiqueta("Marvel", 6);
		assertEquals(p1.getPesoEtiqueta("Marvel"),6);
		assertNotEquals(p1.getPesoEtiqueta("Marvel"),9);
		assertNotEquals(p1.getPesoEtiqueta("Magia"),1);
		assertEquals(p1.getPesoEtiqueta("Magia"),0);
		p1.annadirEtiqueta("Marvel", 3);
		assertEquals(p1.getPesoEtiqueta("Marvel"),9);
		assertNotEquals(p1.getPesoEtiqueta("Marvel"),3);
		p1.annadirEtiqueta("Superheroina", 20);
		assertEquals(p1.getPesoEtiqueta("Superheroina"),20);
		assertNotEquals(p1.getPesoEtiqueta("Superheroina"),200);
	}
	
	
	@Test
	public void getNombresEtiquetas() {
		assertTrue(p1.getNombresEtiquetas().isEmpty());
		p1.annadirEtiqueta("Marvel", 6);
		p1.annadirEtiqueta("Superheroina", 20);
		assertTrue(!p1.getNombresEtiquetas().isEmpty());
		assertEquals(p1.getNombresEtiquetas().toString(),"[Superheroina, Marvel]");
		assertNotEquals(p1.getNombresEtiquetas().toString(),"[Superheroe, Magia]");
	}
}
