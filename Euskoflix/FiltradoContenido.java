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


    public static FiltradoContenido getMiFiltro(){return miFiltro;}
    
    public void annadirModeloPersona(int idUsuario, ArrayList<Entry<Integer,Double>> valoracionesUsuario ){
        Map<String,Double> modeloPersona = new HashMap<>();
        for(Entry<Integer,Double> parPeliValoracion: valoracionesUsuario){  
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
        modeloPersonas.put(idUsuario, modeloPersona);
    }
    
    @Override
    public ArrayList<String> seleccionar30MejoresPelisPara(int usuario) {
        ArrayList<String> lista= new ArrayList<>();
        for(int idPelicula: CatalogoPeliculas.getMiCPeli().getListaPeliculas()){
            lista.add(calcularNota(usuario, idPelicula)+"//"+CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getNombre());
        }
        quickSort(lista,0,lista.size()-1);
        return sublista(lista,lista.size()-30,lista.size());
        
    }
    
    interface Operacion{
        public double norma(int x);
    }
    
    @Override
    public double calcularNota(int idUsuario, int idPelicula) {
    	return Math.abs(coseno(idUsuario,idPelicula));
    }
    
    @Override
    public double coseno(int idUsuario, int idPelicula){
        Operacion o= (int id)->{
            double modulo=0;
            modulo = modeloPersonas.get(id).entrySet().stream().map((par) -> Math.pow(par.getValue(),2)).reduce(modulo, (accumulator, _item) -> accumulator + _item);
            return Math.sqrt(modulo);
        };
        return productoEscalar(idPelicula, idUsuario)/(o.norma(idUsuario)*CatalogoPeliculas.getMiCPeli().norma(idPelicula));
    }

    @Override
    public double productoEscalar(int idPelicula, int idUsuario){
        double dot=0;
        for(Entry<String,Double> par: CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getEtiquetasYPesos()){
            dot+=(modeloPersonas.get(idUsuario).get(par.getKey())!=null)?par.getValue()*modeloPersonas.get(idUsuario).get(par.getKey()):0;
        }
        return dot;
    }
}