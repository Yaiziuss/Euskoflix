package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Euskoflix.Operaciones;

class OperacionesTest {
	private static Operaciones o= Operaciones.getOperacion();
	private ArrayList<Float> op;
	@BeforeEach
	void setUp() throws Exception {
		op= new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		op.clear();
	}

	@Test
	void testMedia() {
		op.add((float)4.0);
		op.add((float)3.5);
		op.add((float)2.9);
		op.add((float)2.3);
		op.add((float)4.5);
		assertEquals((float)o.media(op),(float)3.44,0.01);
		op.clear();
		op.add((float)5.0);
		op.add((float)4.5);
		op.add((float)0.9);
		op.add((float)0.3);
		op.add((float)1.5);
		assertEquals((float)o.media(op),(float)2.44,0.01);
	}
	void testDesv() {
		op.add((float)4.0);
		op.add((float)3.5);
		op.add((float)2.9);
		op.add((float)2.3);
		op.add((float)4.5);
		assertEquals(o.desviacionTipica(op),0.77,0.01);
		op.clear();
		op.add((float)5.0);
		op.add((float)4.5);
		op.add((float)0.9);
		op.add((float)0.3);
		op.add((float)1.5);
		assertEquals(o.desviacionTipica(op),1.93,0.01);
	}
}
