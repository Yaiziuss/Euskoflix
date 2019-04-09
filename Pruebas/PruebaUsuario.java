package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Euskoflix.*;

import java.util.HashMap;
import java.util.Map;
class PruebaUsuario {
	private Usuario u1;
	private Pelicula p1, p2;
	@BeforeEach
	void setUp() throws Exception {
		u1= new Usuario();
		p1= new Pelicula("Capitana Marvel");
		p2= new Pelicula("Capitan America");
	}

	@AfterEach
	void tearDown() throws Exception {
		u1=null;
	}

	@Test
	public void pruebaAnnadirValoració() {
		assertEquals(u1.getPeliculaYValoracion().size(),0);
		u1.annadirValoracion(1, 4);
		assertEquals(u1.getPeliculaYValoracion().size(),1);
		assertNotEquals(u1.getPeliculaYValoracion().size(),0);
		u1.annadirValoracion(2, 4);
		assertEquals(u1.getPeliculaYValoracion().size(),2);
		assertNotEquals(u1.getPeliculaYValoracion().size(),1);
	}

	@Test
	public void pruebaGetValoracion() {
		u1.annadirValoracion(1, 4);
		assertEquals(u1.getValoracion(1),4);
		assertNotEquals(u1.getValoracion(1),3);
	}
}
