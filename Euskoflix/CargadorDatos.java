package Euskoflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
public class CargadorDatos {

    private static final String RUTA_F_ETIQUETAS="src//Datos//movie-tags.csv";
    private static final String RUTA_F_PELICULAS="src//Datos//movie-titles.csv";
    private static final String RUTA_F_VALORACIONES="src//Datos//movie-ratings.csv";
    private static final String SEPARADOR1=";";
    private static final String SEPARADOR2=",";
    
    public static void cargarDatos(){
        cargarFicheroPeliculas();
        cargarFicheroEtiquetas();
        cargarFicheroValoraciones();
        cargarFiltroContenido();
    }
    
    /**
     * Recorre el fichero con ruta pRutaFichero y añade todas las lineas a un arrayList
     * @param pRutaFichero
     * @return ArrayList<String> 
     */
    private static ArrayList<String> getLineasFichero(String pRutaFichero){
        BufferedReader br = null;
        ArrayList<String> listaLineas = null;
        try {
            listaLineas= new ArrayList<>();
            br =new BufferedReader(new FileReader(pRutaFichero));
            String line = br.readLine();
            while (null!=line) {
                listaLineas.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Peto durante la lectura");
        } finally {
            try{
                if (null!=br) {
                   br.close();
                }
            }catch(Exception e){
                System.out.println("Peto al cerrarlo");
            }
        }
        return listaLineas;
    } 

    /**
     * Añade al catalogo de peliculas todas las peliculas junto con sus ids
     * a partir del fichero de peliculas
     */
    private static void cargarFicheroPeliculas() {
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        ArrayList<String> listaPeliculas = getLineasFichero(RUTA_F_PELICULAS);
        for(String linea : listaPeliculas){
            String [] fields = linea.split(SEPARADOR1);
            catPeliculas.annadirPelicula((Integer.parseInt(fields[0])),fields[1].substring(1, fields[1].length()-1));
        }
    }
    
    /**
     * Añade al catalogo de etiquetas todas las que se encuentren en el fichero y el numero de apariciones total de la etiqueta
     * y añade a cada pelicula del catalogo las etiquetas que aparecen en ella junto con el numero de apariciones de la etiqueta en la peli
     * una vez terminado el proceso de carga calcula el TF-IDF  de cada etiqueta en el catalogo
     * y por ultimo normaliza los valores de cada una de las etiquetas que tiene cada pelicula
     */
    private static void cargarFicheroEtiquetas(){
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        CatalogoEtiquetas catEtiquetas = CatalogoEtiquetas.getMiCEti();
        ArrayList<String> listaEtiquetas = getLineasFichero(RUTA_F_ETIQUETAS);
        String lAct = listaEtiquetas.get(0);
        listaEtiquetas.remove(0);
        int apariciones=1;
        for(String nextL :listaEtiquetas){
            if(lAct.equals(nextL))apariciones++;
            else{
                String [] fields = lAct.split(SEPARADOR1);
                catPeliculas.annadirEtiqueta(Integer.parseInt(fields[0]), apariciones, fields[1]);
                catEtiquetas.annadirPeliAEtiqueta(fields[1]);
                lAct=nextL;
                apariciones=1;  
            }
        }
        String [] fields = lAct.split(SEPARADOR1);
        catPeliculas.annadirEtiqueta(Integer.parseInt(fields[0]), apariciones, fields[1]);
        catEtiquetas.annadirPeliAEtiqueta(fields[1]);
        calcularPesoEtiquetas();
        normalizarPesos();
    }
    
    /**
     * Añade al catalogo de Usuarios todas sus Valoraciones
     */
    private static void cargarFicheroValoraciones(){
        CatalogoUsuarios catUsuarios = CatalogoUsuarios.getMiCU();
        ArrayList<String> listaValoraciones = getLineasFichero(RUTA_F_VALORACIONES);
        for(String linea: listaValoraciones){
            String [] fields = linea.split(SEPARADOR2);
            int idPeli= Integer.parseInt(fields[1]);
            int idUsuario=Integer.parseInt(fields[0]);
            catUsuarios.annadirValoracionAUsuario(idUsuario, idPeli, Double.parseDouble(fields[2]));
            CatalogoPeliculas.getMiCPeli().annadirUsuario(idPeli, idUsuario);
        }
    }
    
    /**
     * Calcula el TF-IDF de cada etiqueta de cada pelicula y lo sustituye en el HashMap por el nº de apariciones
     */
    private static void calcularPesoEtiquetas(){
        CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
        CatalogoEtiquetas catEtiquetas = CatalogoEtiquetas.getMiCEti();
        for( int idPeli : catPeliculas.getListaPeliculas()){
            for (String etiqueta: catPeliculas.getPelicula(idPeli).getNombresEtiquetas()){
                double TF_IDF= catPeliculas.getPelicula(idPeli).getPesoEtiqueta(etiqueta);
                TF_IDF*=Math.log(catPeliculas.getTotalPelis()/catEtiquetas.getApariciones(etiqueta));
                catPeliculas.annadirEtiqueta(idPeli,TF_IDF, etiqueta);
            }
        }
    }
    
    /**
     * Por cada peli calcula su norma y divide cada uno de los pesos de sus etiquetas por ella
     */
    private static void normalizarPesos() {
    	CatalogoPeliculas catPeliculas = CatalogoPeliculas.getMiCPeli();
    	for(Entry<Integer,Pelicula> peli: catPeliculas.getIdsYPeliculas()) {
            double norma= CatalogoPeliculas.getMiCPeli().norma(peli.getKey());
            for( Entry<String,Double> pesos: peli.getValue().getEtiquetasYPesos() ){
                pesos.setValue( (pesos.getValue()/norma)); 			
            }
    	}
    }
    
    /**
     * Añade cada usuario del catalogo a la estructura de la clase Filtrado Contenido
     */
    private static void cargarFiltroContenido(){
        CatalogoUsuarios.getMiCU().listaEntriesDeUsuarios().stream().forEach((listaEntriesUsuario) -> {
            FiltradoContenido.getMiFiltro().annadirModeloPersona(listaEntriesUsuario.getKey(), listaEntriesUsuario.getValue());
        });
    }
    
   
       
}
