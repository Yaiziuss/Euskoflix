/**
 * 
 */
package Euskoflix;

import java.util.ArrayList;

/**
 * @author yaiza
 *
 */
public class Operaciones {
	
	public Operaciones() {}
	
	//Modificar para poder usar tanto para peliculas como usuario
	
	public float media(int pIdUsuario) {
		float m=0;
		ArrayList<Float> lp=CatalogoUsuarios.getMiCU().obtenerPuntuaciones(pIdUsuario);
		int numVal=lp.size();
		for (int i=0; i<numVal;i++) {
			m=m+lp.get(i);
		}
		m=m/numVal;
		return m;
	}

	public double desviacionTipica(int pIdUsuario) {
		double destip=0;
		double m=media(pIdUsuario)*1.0;
		ArrayList<Float> lp=CatalogoUsuarios.getMiCU().obtenerPuntuaciones(pIdUsuario);
		int numVal=lp.size();
		for (int i=0; i<numVal;i++) {
			destip= destip+Math.pow(m-lp.get(i), 2);
		}
		destip=destip/numVal;
		return Math.sqrt(destip);
	}
}
