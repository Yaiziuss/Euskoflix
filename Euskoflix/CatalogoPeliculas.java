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
     * 
     * @return 
     */
    public static CatalogoPeliculas getMiCPeli(){ return miCPeli;}
    
    
    public Set<Integer> getListaPeliculas() {return listaPeliculas.keySet(); }
    
    public ArrayList<Entry<Integer,Pelicula>> getIdsYPeliculas(){
    	ArrayList<Entry<Integer,Pelicula>> listaP= new ArrayList<>();
    	for(Entry<Integer,Pelicula> par: listaPeliculas.entrySet()) { 
            listaP.add(par);
        }return (listaP==null)?null:listaP;
    }
    
    public Pelicula getPelicula(int pIdPeli){ return (this.listaPeliculas.get(pIdPeli)==null)?null:listaPeliculas.get(pIdPeli);}
   
    /**
     * Añade una pelicula al catalogo
     * @param pId
     * @param pNombre 
     */
    public void annadirPelicula(int pId, String pNombre){ this.listaPeliculas.put(pId,new Pelicula(pNombre));}
    
    /**
     * Añade a la pelicula con id pIdPeli la etiqueta pEtiqueta y el numero de apariciones
     * @param pIdPeli
     * @param pNAparicionesE
     * @param pEtiqueta 
     * @precondicion la pelicula tiene que existir
     */
    public void annadirEtiqueta(int pIdPeli , double pNAparicionesE ,String pEtiqueta ){ this.listaPeliculas.get(pIdPeli).annadirEtiqueta(pEtiqueta, pNAparicionesE); }
    
    public void annadirUsuario(int idPeli, int idUsuario) { this.listaPeliculas.get(idPeli).annadirUsuario(idUsuario);}
    
    public int getTotalPelis(){ return this.listaPeliculas.size();}
    
    public double norma(int idPelicula){
        double norma=0;
        norma = listaPeliculas.get(idPelicula).getEtiquetasYPesos().stream()
                .map((par) -> Math.pow(par.getValue(),2))
                .reduce(norma, (accumulator, _item) -> accumulator + _item);
        return Math.sqrt(norma);
    }

}