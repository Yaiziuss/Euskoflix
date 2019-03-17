/**
 * 
 */
package Pruebas;
import Euskoflix.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * @author yaiza
 *
 */
class PruebaCatalogoPeliculas {

	/**
	 * @throws java.lang.Exception
	 */
	private static CatalogoPeliculas ct1;
	//private static Pelicula p1, p2, p3, p4;
	
	static void setUpBeforeClass() throws Exception {
		ct1= CatalogoPeliculas.getMiCPeli();
		/*p2= new Pelicula("Your Name");
		p3= new Pelicula("La Bella y la Bestia 2017");*/
	}
	@AfterEach
	void tearDown() throws Exception {
		ct1= null;
		/*p1= null;
		p2= null;
		p3= null;*/
	}
	
	/**
	 * Vamos a probar si añade la pelicula correctamente o no
	 */
	@Test
	public void PruebaAnnadirPeli() {
		ct1.annadirPelicula(1,"Capitana Marvel");
		assertNotNull(ct1);
		assertSame(ct1.getTotalPelis(), 1);
	}

	/**
	 * Vamos a probar si la lista de peliculas es vacia o no
	 */
	@Test
	public void PruebaGetPelis() {
		assertNull(ct1.getListaPeliculas());
		assertSame(ct1.getTotalPelis(),0);
		ct1.annadirPelicula(1,"Capitana Marvel");
		assertNotNull(ct1.getListaPeliculas());
	}
	
	/**
	 * Vamos a probar si añade correctamente los pares
	 */
	@Test
	public void PruebaPares() {
		ct1.annadirPelicula(1,"Capitana Marvel");
		assertSame(ct1.getParejitasFelices(),"1,Capitana Marvel");
	}
	
	/**
	 * Vamos a probar si añade el número total de peliculas es correcto
	 */
	@Test
	public void PruebaTotalPelis() {
		assertEquals(ct1.getTotalPelis(), 0);
		ct1.annadirPelicula(1,"Capitana Marvel");
		assertSame(ct1.getTotalPelis(),1);
	}

	//TODO Falta getPelicula y annadirEtiqueta
}
