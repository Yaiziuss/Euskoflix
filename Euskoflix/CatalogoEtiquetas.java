package Euskoflix;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elalfred
 */
public class CatalogoEtiquetas {
    private Map<String,Integer> listaApariciones;
    private static CatalogoEtiquetas miCEti = new CatalogoEtiquetas();

    private CatalogoEtiquetas(){ this.listaApariciones=new HashMap<>();}
    
    public static CatalogoEtiquetas getMiCEti(){ return miCEti;}   
    
    /**
     * Añade una peli a la etiqueta
     * @param pNombre
     */
    public void annadirPeliAEtiqueta(String pNombre){
        if(!this.listaApariciones.containsKey(pNombre)) this.listaApariciones.put(pNombre,1);
        else this.listaApariciones.put(pNombre, getApariciones(pNombre)+1);
    }
    
    /**
     * Devuelve el par Etiqueta-num Apariciones
     * @return listaE
     */
    public ArrayList<Entry<String,Integer>> obtenerEtiquetasYApariciones(){
    	ArrayList<Entry<String,Integer>> listaE= new ArrayList<>();
    	for(Entry<String,Integer> par: listaApariciones.entrySet()) { 
            listaE.add(par);}
    	return (listaE==null)?null:listaE;
    	
    }
    /**
     * Devuelve el numero de apariciones de pEtiqueta o cero si no existe en el catalogo
     * @param pEtiqueta
     * @return num Aparicioneos 
     */
    public int getApariciones(String pEtiqueta){ return (listaApariciones.get(pEtiqueta)==null)?0:listaApariciones.get(pEtiqueta);}
}
