package Euskoflix;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry; 

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
    /**
     * Devuelve la unica instancia del catalogo
     * @return CatalogoUsuarios
     */
    public static CatalogoUsuarios getMiCU(){  return miCU;  }
    
    /**
     * Devuelve el usuario pIDUsu
     * @param pIDUsu
     * @return Usuario
     */
    public Usuario getUsuario(int pIDUsu){return listaUsuarios.get(pIDUsu);}
    
    /**
     * Aniade la valoracion de pIdPeli a la lista de valoraciones de pIdUsuario y añade pIdUsuario si no existe
     * @param pIdUsuario
     * @param pIdPeli
     * @param pValoracion 
     */
    public void annadirValoracionAUsuario(int pIdUsuario, int pIdPeli, double pValoracion ){
        if(!this.listaUsuarios.containsKey(pIdUsuario)){
            this.listaUsuarios.put(pIdUsuario, new Usuario());
        }
        this.listaUsuarios.get(pIdUsuario).annadirValoracion(pIdPeli, pValoracion);
    }
   
    /**
     * Devuelve una estructura equivalente al HashMap listaUsuario, pero en forma de ArrayList para facilitar su iteracion
     * @return ArrayList<Entry<Integer,ArrayList<Entry<Integer,Double>>>>
     */
    public ArrayList<Entry<Integer,ArrayList<Entry<Integer,Double>>>> listaEntriesDeUsuarios(){
        ArrayList<Entry<Integer,ArrayList<Entry<Integer,Double>>>> listaVU= new ArrayList<>();
        Map<Integer,ArrayList<Entry<Integer,Double>>> aux = new HashMap();
        for(int clave : listaUsuarios.keySet()){
            aux.put(clave,listaUsuarios.get(clave).getPeliculaYValoracion());
        }
        for(Entry<Integer, ArrayList<Entry<Integer, Double>>> c: aux.entrySet()){
            listaVU.add(c);
        }
        return listaVU;
    }
    
   
}