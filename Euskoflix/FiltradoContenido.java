/**
 * 
 */
package Euskoflix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @author yaiza
 *Este modelo se basa en el contenido para hacer las predicciones
 */
public class FiltradoContenido extends Filtrado {
    private Map<Integer,Map<String,Float>> modeloPersonas; //integer idUsuario, 
    
    private static FiltradoContenido miFiltro= new FiltradoContenido();
    
    private FiltradoContenido() {
        this.modeloPersonas = new HashMap<>();
    }
    
    public static FiltradoContenido getMiFiltro(){return miFiltro;}
    
    public void annadirModeloPersona(int idUsuario, ArrayList<Entry<Integer,Float>> valoracionesUsuario ){
        Map<String,Float> modeloPersona = new HashMap<>();
        for(Entry<Integer,Float> parPeliValoracion: valoracionesUsuario){
            float valoracion= parPeliValoracion.getValue();
            if(valoracion<3.5)continue;
            //recorre la lista de pesos de etiquetas de cada pelicula y multiplica la valoracion por el peso, si la etiqueta ya existe en el hashmap suma el nuevo valor al original
            for(Entry<String,Float> parEtiquetaPeso: CatalogoPeliculas.getMiCPeli().getPelicula(parPeliValoracion.getKey()).getEtiquetasYPesos()){
                float resultado= valoracion*parEtiquetaPeso.getValue();
                if(modeloPersona.containsKey(parEtiquetaPeso.getKey())){
                    resultado+=modeloPersona.get(parEtiquetaPeso.getKey());
                }
                modeloPersona.put(parEtiquetaPeso.getKey(),resultado);
            }
        }
        modeloPersonas.put(idUsuario, modeloPersona);
    }
    
    public float getPesoEtiquetaUsuario(int idUsuario, String etiqueta){
        return (modeloPersonas.get(idUsuario).get(etiqueta)==null)?0:modeloPersonas.get(idUsuario).get(etiqueta);
    }
    
    private ArrayList<Entry<String,Float>> etiquetasUsuario(int idUsuario ){
    	ArrayList<Entry<String,Float>> lista = new ArrayList<>();
    	for(Entry<String,Float> par : modeloPersonas.get(idUsuario).entrySet()) {
    		lista.add(par);
    	}
    	return lista;
    }
    
    public double calcularNotaUsuario(int idUsuario, int idPelicula) {
    	double coseno=Operaciones.getOperacion().cosenoVectorial(CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getEtiquetasYPesos(), 
    			etiquetasUsuario(idUsuario), idUsuario);
    	return Math.abs(coseno);
    	
    }
}
