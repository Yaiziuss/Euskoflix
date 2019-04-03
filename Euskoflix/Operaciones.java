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
	
	public float media(ArrayList<Float> pLista) {
		float m=0;
		int numVal=pLista.size();
		for (int i=0; i<numVal;i++) {
			m=m+pLista.get(i);
		}
		m=m/numVal;
		return m;
	}

	public double desviacionTipica(ArrayList<Float> pLista) {
		double destip=0;
		double m=media(pLista)*1.0;
		int numVal=pLista.size();
		for (int i=0; i<numVal;i++) {
			destip= destip+Math.pow(m-pLista.get(i), 2);
		}
		destip=destip/numVal;
		return Math.sqrt(destip);
	}
}
