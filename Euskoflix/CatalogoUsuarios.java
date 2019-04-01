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
    
    public ArrayList<Float> obtenerPuntuacionesPelicula(int pIdPeli){
    	ArrayList<Float> lp=new ArrayList<>();
    	Set<Integer> clU= getListaUsuarios();
    	for (int clave : clU) {
    		Float val=listaUsuarios.get(clave).getValoracion(pIdPeli);
    		if (val!=null) {
    			lp.add(val);
    		}
    	}
    	return lp;
    }
    
    /**
     * Con ente método sacamos la matriz de puntuaciones de todos los usuarios que hayan visto la pelicula a predecir la puntuacion
     * @param pIdUsuario
     * @param pIdPeli
     * @return matrix
     */
    public Float[][] matrizPuntuaciones(int pIdUsuario, int pIdPeli){
    	Usuario us=listaUsuarios.get(pIdUsuario);
    	ArrayList<String> lisP=us.getClaves(); //Lista de peliculas del usuario pIdUsuario
    	Float[][] matrix= new Float [us.getClaves().size()][getListaUsuarios().size()]; //Filas--> Peliculas, Columnas--> Usuarios
    	Set<Integer> clU= getListaUsuarios();
    	int f=0;
    	for (int i=0; i<lisP.size(); i++) {
    		matrix[f][i]=  Float.parseFloat(lisP.get(i));
    		}
    	for (int clave : clU) {
    		Float val=listaUsuarios.get(clave).getValoracion(pIdPeli);
    		if (val!=null) {
    			//obtenemos lista de peliculas de usuario ux
    			Usuario ux=listaUsuarios.get(clave);
    			f++;
    			for (int j=0;j<ux.getClaves().size();j++) { //recorro la lista de peliculas del usuario ux
    				String peliUx=String.valueOf(matrix[0][j]);
    				if (ux.getClaves().contains(peliUx)) {
    					matrix[f][j]=(ux.getValoracion(Integer.parseInt(peliUx))); /*Si el usuario ha visto la peli añadimos su valoracion 
    					en la misma columna que la valoración de la pelicula del usuario pIdUsuario*/
    				}
    			}
    		}
    	}
    	return matrix;
	}
    
    public Float[][] matrizPuntuacionesEtiquetas(){
    	//matriz fila-> Usuario; Columna -> Etiqueta
    	Float[][] matriz= new Float[listaUsuarios.size()][CatalogoEtiquetas.getMiCEti().getListaEtiquetas().size()];
    	for(Usuario u: listaUsuarios.values()) {
    		for
    	}
    	
    	return matriz;
    }
}
