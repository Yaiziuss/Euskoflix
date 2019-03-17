package Euskoflix;


import java.util.HashMap;
import java.util.Map;
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
    
    public int getApariciones(String pEtiqueta){ return (listaApariciones.get(pEtiqueta)==null)?0:listaApariciones.get(pEtiqueta);}
    
    private Iterator<Entry<String,Integer>> getIterador(){return listaApariciones.entrySet().iterator();}
}