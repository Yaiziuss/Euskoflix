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
class PruebaCatalogoEtiquetas {

	/**
	 * @throws java.lang.Exception
	 */
private static CatalogoEtiquetas ce;
    
    
	@BeforeEach
    public void setUp() {
		ce=CatalogoEtiquetas.getMiCEti();
    }
    
    @AfterEach
    public void tearDown() {
    	ce=null;
    }
   
    @Test
    /**
     * En este test tnedremos en cuenta los siguientes casos:
     * La etiqueta existe y se compara con su número correcto de apariciones
     * La etiqueta existe pero el número con el que se compara es incorrecto
     * La etiqueta no existe (En este caso peta)
     */
    public void PruebaAnnadirPeliAEtiqueta() {
            ce.annadirPeliAEtiqueta("Hp1");
            assertEquals(ce.getApariciones("Hp1"),1);
            assertNotEquals(ce.getApariciones("Hp1"),7);
            assertEquals(ce.getApariciones("hola"),0);
    }

}
