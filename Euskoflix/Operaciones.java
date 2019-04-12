/**
 * 
 */
package Euskoflix;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * @author yaiza
 *
 */
public class Operaciones {
	private static Operaciones miOperacion= new Operaciones();
	
	private Operaciones() {}
	
	public static Operaciones getOperacion(){ return miOperacion;}
	
	//Modificar para poder usar tanto para peliculas como usuario
	
	/*** Media
	 * @pre: La lista no puede ser vacia.
	 * @param pLista
	 * @return
	 */
	public float media(ArrayList<Float> pLista) {
		float m=0;
		int numVal=pLista.size();
		for (int i=0; i<numVal;i++) {
			m+=pLista.get(i);
		}
		m=m/numVal;
		return m;
	}

	/*** Desviacion Tipica
	 * @pre: La lista no puede ser vacia.
	 * @param pLista
	 * @return
	 */
	public double desviacionTipica(ArrayList<Float> pLista) {
		double destip=0;
		double m=media(pLista)*1.0;
		int numVal=pLista.size();
		for (int i=0; i<numVal;i++) {
			destip+= Math.pow(m-pLista.get(i), 2);
		}
		destip=destip/numVal;
		return Math.sqrt(destip);
	}
        
        public double cosenoVectorial(ArrayList<Entry<String,Float>> listaEntriesPeliculas, ArrayList<Entry<String,Float>> listaEntriesUsuario, int idUsuario){
            return productoEscalar(listaEntriesPeliculas, idUsuario)/(norma(listaEntriesPeliculas)*norma(listaEntriesUsuario));
        }
        
        public double norma(ArrayList<Entry<String,Float>> listaEntries){
            double modulo=0;
            for(Entry<String,Float> par: listaEntries){
                modulo+= Math.pow(par.getValue(),2);
            }
            return Math.sqrt(modulo);
        }
        
        private double productoEscalar(ArrayList<Entry<String,Float>> EntriesPelicula, int idUsuario){
            double dot=0;
            for(Entry<String,Float> par: EntriesPelicula){
                dot+= par.getValue()*FiltradoContenido.getMiFiltro().getPesoEtiquetaUsuario(idUsuario, par.getKey());
            }
            return dot;
        }
        
        
}
