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
class PruebaCatalogoUsuarios {
	private static CatalogoUsuarios cu;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cu= CatalogoUsuarios.getMiCU();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void PruebaannadirValoracionAUsuario() {
		assertNotNull(cu.getMiCU());
		cu.annadirValoracionAUsuario(1, 1, 4);
		//assertEquals(cu.getMiCU());
		
	}

}
