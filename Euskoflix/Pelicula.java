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
    
    public void annadirEtiqueta(String pEtiqueta, double pNApariciones){
        if(!this.pesoEtiquetas.containsKey(pEtiqueta)){ 
            this.pesoEtiquetas.put(pEtiqueta,pNApariciones);
        }else{
            this.pesoEtiquetas.put(pEtiqueta,this.pesoEtiquetas.get(pEtiqueta)+ pNApariciones);
        }
    }
    
    public double getPesoEtiqueta(String pEtiqueta){ return (this.pesoEtiquetas.get(pEtiqueta)==null)?0:pesoEtiquetas.get(pEtiqueta);}

    public Set<String> getNombresEtiquetas() { return pesoEtiquetas.keySet(); }
    
    public ArrayList<Entry<String,Double>> getEtiquetasYPesos(){
        ArrayList<Entry<String,Double>> lista = new ArrayList<>();
        for(Entry<String,Double> e: pesoEtiquetas.entrySet()){lista.add(e);}
        return lista;
    }
    
    public void annadirUsuario(int idUsuario) {this.valoradaPorUsuarios.add(idUsuario);}
    
    public String getListaUsuarios(){
    	String usuarios="";
    	for(Integer id: valoradaPorUsuarios) {
    		usuarios+=String.valueOf(id)+"/";
    	}
    	return usuarios.substring(0,usuarios.length()-1);
    }
    
}
