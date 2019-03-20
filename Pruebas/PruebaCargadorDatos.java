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
class PruebaCargadorDatos {
	private static CargadorDatos cd;
	private static CatalogoPeliculas cp;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cp= CatalogoPeliculas.getMiCPeli();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		cd=null;
		cp.getListaPeliculas().clear();
	}

	@Test
	public static void PruebaCargarDatos() {
		assertNull(CatalogoPeliculas.getMiCPeli());
		CargadorDatos.cargarDatos();
		assertNotNull(CatalogoPeliculas.getMiCPeli().getListaPeliculas());
	}

}
