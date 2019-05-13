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
public class FiltradoContenido extends Filtrado{
    private Map<Integer,Map<String,Double>> modeloPersonas; //integer idUsuario, 
    
    private static FiltradoContenido miFiltro= new FiltradoContenido();
    
    private FiltradoContenido() {
        this.modeloPersonas = new HashMap<>();
    }

    /**
     * Devuelve la unica instancia de FiltradoContenido
     * @return FiltradoContenido
     */
    public static FiltradoContenido getMiFiltro(){return miFiltro;}
    
    /**
     * Genera un modelo de pIdUsuario a partir de pValoracionesUsuario, selecciona las peliculas con puntuacion mayor o igual a 3,5 y multiplica la valoracion por el peso de sus etiquetas
     * @param pIdUsuario
     * @param pValoracionesUsuario 
     */
    public void annadirModeloPersona(int pIdUsuario, ArrayList<Entry<Integer,Double>> pValoracionesUsuario ){
        Map<String,Double> modeloPersona = new HashMap<>();
        for(Entry<Integer,Double> parPeliValoracion: pValoracionesUsuario){  
            if(parPeliValoracion.getValue()<3.5)continue;
            //recorre la lista de pesos de etiquetas de cada pelicula y multiplica la valoracion por el peso, si la etiqueta ya existe en el hashmap suma el nuevo valor al original
            for(Entry<String,Double> parEtiquetaPeso: CatalogoPeliculas.getMiCPeli().getPelicula(parPeliValoracion.getKey()).getEtiquetasYPesos()){
                double resultado= parEtiquetaPeso.getValue();
                if(modeloPersona.containsKey(parEtiquetaPeso.getKey())){
                    resultado+=modeloPersona.get(parEtiquetaPeso.getKey());
                }
                modeloPersona.put(parEtiquetaPeso.getKey(),resultado);
            }
        }
        modeloPersonas.put(pIdUsuario, modeloPersona);
    }
    
    /**
     * Devuelve una lista con las 30 peliculas que mas se ajustan a los gustos de pUsuario
     * @param pUsuario
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> seleccionar30MejoresPelisPara(int pUsuario) {
        ArrayList<String> lista= new ArrayList<>();
        for(int idPelicula: CatalogoPeliculas.getMiCPeli().getListaPeliculas()){
            lista.add(calcularNota(pUsuario, idPelicula)+"//"+CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getNombre());
        }
        quickSort(lista,0,lista.size()-1);
        return sublista(lista,lista.size()-30,lista.size());
        
    }
    
    interface Operacion{
        public double norma(int x);
    }
    
    /**
     * Devuelve la nota que le daria pIdsuario a pIdPelicula
     * @param pIdUsuario
     * @param pIdPelicula
     * @return double
     */
    @Override
    public double calcularNota(int pIdUsuario, int pIdPelicula) {
    	return Math.abs(coseno(pIdUsuario,pIdPelicula));
    }
    
    /**
     * Calcula el coseno de los vectores de de los vectores de pesos de etiquetas de pIdUsuario y pIdPelicula
     * @param pIdUsuario
     * @param pIdPelicula
     * @return double
     */
    @Override
    public double coseno(int pIdUsuario, int pIdPelicula){
        Operacion o= (int id)->{
            double modulo=0;
            modulo = modeloPersonas.get(id).entrySet().stream().map((par) -> Math.pow(par.getValue(),2)).reduce(modulo, (accumulator, _item) -> accumulator + _item);
            return Math.sqrt(modulo);
        };
        return productoEscalar(pIdPelicula, pIdUsuario)/(o.norma(pIdUsuario)*CatalogoPeliculas.getMiCPeli().norma(pIdPelicula));
    }
    
    /**
     * Calcula el producto escalar de los vectores de pesos de etiquetas de pIdUsuario y pIdPelicula
     * @param pIdPelicula
     * @param pIdUsuario
     * @return double
     */
    @Override
    public double productoEscalar(int pIdPelicula, int pIdUsuario){
        double dot=0;
        for(Entry<String,Double> par: CatalogoPeliculas.getMiCPeli().getPelicula(pIdPelicula).getEtiquetasYPesos()){
            dot+=(modeloPersonas.get(pIdUsuario).get(par.getKey())!=null)?par.getValue()*modeloPersonas.get(pIdUsuario).get(par.getKey()):0;
        }
        return dot;
        
        
    }
}