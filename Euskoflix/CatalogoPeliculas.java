package Euskoflix;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry; 


/**
 *
 * @author Elalfred
 */
public class CatalogoPeliculas {  
    private Map<Integer,Pelicula> listaPeliculas;
    private static CatalogoPeliculas miCPeli = new CatalogoPeliculas();
    
    private CatalogoPeliculas(){  this.listaPeliculas=new HashMap<>(); }
    
    /**
     * Devuelve la unica instancia del catalogo de peliculas
     * @return CatalogoPeliculas
     */
    public static CatalogoPeliculas getMiCPeli(){ return miCPeli;}
    
    /**
     * Devuelve la lista de ids de peliculas
     * @return Set<Integer> 
     */
    public Set<Integer> getListaPeliculas() {return listaPeliculas.keySet(); }
    
    /**
     * Devuelve una lista de pares idPelicula/Pelicula asociada
     * @return ArrayList<Entry<Integer,Pelicula>>
     */
    public ArrayList<Entry<Integer,Pelicula>> getIdsYPeliculas(){
    	ArrayList<Entry<Integer,Pelicula>> listaP= new ArrayList<>();
    	for(Entry<Integer,Pelicula> par: listaPeliculas.entrySet()) { 
            listaP.add(par);
        }return (listaP==null)?null:listaP;
    }
    
    /**
     * Devuelve la pelicula con pIdPeli o null si no existe
     * @param pIdPeli
     * @return 
     */
    public Pelicula getPelicula(int pIdPeli){ return (this.listaPeliculas.get(pIdPeli)==null)?null:listaPeliculas.get(pIdPeli);}
   
    /**
     * Aniade una pelicula al catalogo
     * @param pId
     * @param pNombre 
     */
    public void annadirPelicula(int pId, String pNombre){ this.listaPeliculas.put(pId,new Pelicula(pNombre));}
    
    /**
     * Aniade a la pelicula con id pIdPeli la etiqueta pEtiqueta y el numero de apariciones
     * @param pIdPeli
     * @param pNAparicionesE
     * @param pEtiqueta 
     * @precondicion la pelicula tiene que existir
     */
    public void annadirEtiqueta(int pIdPeli , double pNAparicionesE ,String pEtiqueta ){ this.listaPeliculas.get(pIdPeli).annadirEtiqueta(pEtiqueta, pNAparicionesE); }
    
    /**
     * Aniadea a la peli con pIdPeli pIdusuario
     * @param pIdPeli
     * @param pIdUsuario 
     */
    public void annadirUsuario(int pIdPeli, int pIdUsuario) { this.listaPeliculas.get(pIdPeli).annadirUsuario(pIdUsuario);}
    
    /**
     * Devuelve el total de peliculas almacenadas
     * @return int
     */
    public int getTotalPelis(){ return this.listaPeliculas.size();}
    
    /**
     * Calcula la norma de pIdPelicula sumando y elevando al cuadrado todos los pesos de pIdPelicula y devolviendo la raiz de su suma
     * @param pIdPelicula
     * @return double
     */
    public double norma(int pIdPelicula){
        double norma=0;
        norma = listaPeliculas.get(pIdPelicula).getEtiquetasYPesos().stream()
                .map((par) -> Math.pow(par.getValue(),2))
                .reduce(norma, (accumulator, _item) -> accumulator + _item);
        return Math.sqrt(norma);
    }

}