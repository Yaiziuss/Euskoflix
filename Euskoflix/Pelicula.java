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
public class Pelicula{
    
    private final String nombre;
    private Map<String,Double> pesoEtiquetas;
    private ArrayList<Integer> valoradaPorUsuarios;

    public Pelicula(String pNombre){
        this.nombre= pNombre;
        this.pesoEtiquetas= new HashMap<>();
        this.valoradaPorUsuarios= new ArrayList<>();
    }

    public String getNombre() {return nombre;}
    
    /**
     * Suma a pEtiqueta pNApariciones y si pEtiqueta no existe lo crea en pesoEtiquetas y annade pEtiqueta
     * @param pEtiqueta 
     * @param pNApariciones 
     */
    public void annadirEtiqueta(String pEtiqueta, double pNApariciones){
        if(!this.pesoEtiquetas.containsKey(pEtiqueta)){ 
            this.pesoEtiquetas.put(pEtiqueta,pNApariciones);
        }else{
            this.pesoEtiquetas.put(pEtiqueta,this.pesoEtiquetas.get(pEtiqueta)+ pNApariciones);
        }
    }
    
    /**
     * Devuelve el peso de pEtiqueta y 0 si pEtiqueta no existe
     * @param pEtiqueta
     * @return 
     */
    public double getPesoEtiqueta(String pEtiqueta){ return (this.pesoEtiquetas.get(pEtiqueta)==null)?0:pesoEtiquetas.get(pEtiqueta);}
    
    /**
     * Devuelve una lista con los nombres de las etiquetas
     * @return 
     */
    public Set<String> getNombresEtiquetas() { return pesoEtiquetas.keySet(); }
    
    /**
     * Devuelve una estructura equivalente al HashMap pero en forma de arrayList
     * @return 
     */
    public ArrayList<Entry<String,Double>> getEtiquetasYPesos(){
        ArrayList<Entry<String,Double>> lista = new ArrayList<>();
        for(Entry<String,Double> e: pesoEtiquetas.entrySet()){lista.add(e);}
        return lista;
    }
    /**
     * Annade a valoradaPorUsuarios el id pIdUsuario
     * @param pIdUsuario 
     */
    public void annadirUsuario(int pIdUsuario) {this.valoradaPorUsuarios.add(pIdUsuario);}
    
    /**
     * Devuelve un String que contiene todos los ids de usuarios de valoradaPorUsuarios concatenados y separados por /
     * @return 
     */
    public String getListaUsuarios(){
    	String usuarios="";
    	for(Integer id: valoradaPorUsuarios) {
    		usuarios+=String.valueOf(id)+"/";
    	}
    	return usuarios.substring(0,usuarios.length()-1);
    }
    
}
