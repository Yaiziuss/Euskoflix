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
	private Usuario u1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cu= CatalogoUsuarios.getMiCU();
		u1= new Usuario();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		cu.getMiCU().listaEntriesDeUsuarios().clear();
	}

	@Test
	public void PruebaannadirValoracionAUsuario() {
		assertNotNull(cu.getMiCU());
		assertEquals(cu.getMiCU().listaEntriesDeUsuarios().size(),0);
		cu.annadirValoracionAUsuario(1, 1, 4);
		assertEquals(cu.getMiCU().listaEntriesDeUsuarios().size(),1);
	}

}
