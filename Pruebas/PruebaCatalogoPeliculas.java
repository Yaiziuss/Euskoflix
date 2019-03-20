/**
 * 
 */
package Pruebas;
import Euskoflix.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

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
	
	@BeforeEach
	public void setUp() throws Exception{
		ct1= CatalogoPeliculas.getMiCPeli();
		/*p2= new Pelicula("Your Name");
		p3= new Pelicula("La Bella y la Bestia 2017");*/
	}
	
	@AfterEach
	void tearDown() throws Exception {
		ct1.getListaPeliculas().clear();
		/*p1= null;
		p2= null;
		p3= null;*/
	}
	
	/**
	 * Vamos a probar si añade la pelicula correctamente o no
	 */
	@Test
	public void PruebaGetPelis() {
            assertNull(ct1.getPelicula(55));
            ct1.annadirPelicula(1,"Capitana Marvel");
            assertEquals(ct1.getPelicula(1).getNombre(),"Capitana Marvel");
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
	 * Vamos a probar si añade el número total de peliculas es correcto
	 */
	@Test
	public void PruebaTotalPelis() {
            assertEquals(0, ct1.getTotalPelis() );
            ct1.annadirPelicula(1,"Capitan America");
            assertEquals(ct1.getTotalPelis(),1);
	}

	@Test
	public void PruebaAnnadirEtiquetas() {
		//assertNull(ct1.getListaPeliculas().getClass().)
		ct1.annadirEtiqueta(1, 7, "Marvel");
		
	}
}
