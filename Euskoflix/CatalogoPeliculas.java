package Euskoflix;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry; 
import java.util.Iterator;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elalfred
 */
public class CatalogoPeliculas{  
    private Map<Integer,Pelicula> listaPeliculas;
    private static CatalogoPeliculas miCPeli = new CatalogoPeliculas();
    
    private CatalogoPeliculas(){  this.listaPeliculas=new HashMap<>(); }
    
    /**
     * 
     * @return 
     */
    public static CatalogoPeliculas getMiCPeli(){ return miCPeli;}
    
    
    //encapsular
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
    public void annadirEtiqueta(int pIdPeli , float pNAparicionesE ,String pEtiqueta ){ this.listaPeliculas.get(pIdPeli).annadirEtiqueta(pEtiqueta, pNAparicionesE); }
    
    public int getTotalPelis(){ return this.listaPeliculas.size();}
    
    private Iterator<Entry<Integer,Pelicula>> getIterador(){return listaPeliculas.entrySet().iterator();}

    
    

}