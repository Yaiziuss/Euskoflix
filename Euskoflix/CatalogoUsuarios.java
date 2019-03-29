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
public class CatalogoUsuarios{
    private Map<Integer,Usuario> listaUsuarios;
    private static CatalogoUsuarios miCU = new CatalogoUsuarios();

    private CatalogoUsuarios(){
        this.listaUsuarios= new HashMap<>();
    }
    
    public static CatalogoUsuarios getMiCU(){  return miCU;  }
    
  /*  public ArrayList<String> getParejitasFelices(){
    	ArrayList<String> listaP= new ArrayList<>();
    	for(int clave: getListaPeliculas()) { 
            listaP.add(clave+" "+this.listaPeliculas.get(clave).getNombre());}
    	return (listaP==null)?null:listaP;
    	
    }*/
    
    /**
     * Añade la valoracion de pIdPeli a la lista de valoraciones de pIdUsuario y añade pIdUsuario si no existe
     * @param pIdUsuario
     * @param pIdPeli
     * @param pValoracion 
     */
    public void annadirValoracionAUsuario(int pIdUsuario, int pIdPeli, float pValoracion ){
        if(!this.listaUsuarios.containsKey(pIdUsuario)){
            this.listaUsuarios.put(pIdUsuario, new Usuario());
        }
        this.listaUsuarios.get(pIdUsuario).annadirValoracion(pIdPeli, pValoracion);
    }
    private Iterator<Entry<Integer,Usuario>> getIterador(){return listaUsuarios.entrySet().iterator();}
    
    private Set<Integer>getListaUsuarios() {return listaUsuarios.keySet(); }
    
    public ArrayList<ArrayList<String>> listaValoracionesUsuarios(){
        ArrayList<ArrayList<String>> listaUV= new ArrayList<>();
        for(int clave : listaUsuarios.keySet()){
            ArrayList<String> listaV= new ArrayList<>();
            for(String key: listaUsuarios.get(clave).getClaves()){
                listaV.add(key +" "+listaUsuarios.get(clave).getValoracion(Integer.parseInt(key)));
            }
            listaUV.add(listaV);
        }
        return listaUV;
    }
    
    public ArrayList<Float> obtenerPuntuaciones(int pIdUsuario){
    	Usuario us=listaUsuarios.get(pIdUsuario);
    	ArrayList<String> lc= us.getClaves();
    	ArrayList<Float> lp= new ArrayList<>();
    	for (int i=0; i<lc.size();i++) {
    		int clave=Integer.parseInt(lc.get(i));
    		float punt= us.getValoracion(clave);
    		lp.add(punt);
    	}
    	return lp;
    }
}
