/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Euskoflix;

import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Elalfred
 */
public class FiltradoPersona extends Filtrado {
    private static FiltradoPersona miFPer= new FiltradoPersona();
    
    private FiltradoPersona() {}
    
    public static FiltradoPersona getMiFPer(){return miFPer;} 
    
    interface Operacion{
        public double norma(int id);
    }
    
    /**
     * calcula el coseno de pUsuario1 y pUsuario2 a partir de los vectores de puntuaciones de ambos
     * @param pUsuario1
     * @param pUsuario2
     * @return double
     */
    @Override
    public double coseno(int pUsuario1, int pUsuario2 ){
        Operacion n=(int id) ->{
            double modulo=0;
            modulo = CatalogoUsuarios.getMiCU().getUsuario(id).getPeliculaYValoracion().stream()
                    .map((par) -> Math.pow(par.getValue(),2))
                    .reduce(modulo, (accumulator, _item) -> accumulator + _item);
            return Math.sqrt(modulo);
        };
        return productoEscalar( pUsuario1,  pUsuario2 )/(n.norma(pUsuario1)*n.norma(pUsuario2));
    }
    
    /**
     * calcula el producto esclar de pUsuario1 y pUsuario2 a partir de los vectores de puntuaciones de ambos
     * @param pUsuario1
     * @param pUsuario2
     * @return 
     */
    @Override
    public double productoEscalar(int pUsuario1, int pUsuario2 ){
        double dot=0;
        if(pUsuario1!=pUsuario2){
            for( Map.Entry<Integer,Double> parUsuario1 : CatalogoUsuarios.getMiCU().getUsuario(pUsuario1).getPeliculaYValoracion()){
                int peliculaAEvaluar=parUsuario1.getKey();
                dot+= parUsuario1.getValue()*CatalogoUsuarios.getMiCU().getUsuario(pUsuario2).getValoracion(peliculaAEvaluar);
            }
        }
        return dot;
    } 
    
    /**
     * devuelve el par similitud (pUsuario,usuarioCandidato) y idUsarioCandidato mas similares a pUsuario que han valorado pPelicula, a partir de calcular el coseno de los vectores de valoraciones de los pUsuarios y los usuarios candidatos
     * @param pUsuario
     * @param pPelicula
     * @return ArrayList<String>
     */
    private ArrayList<String> seleccionar30UsuariosSimilaresConValoracion(int pUsuario, int pPelicula){ 
        ArrayList<Integer> usuarios = obtenerListaUsuarios(pPelicula);
        ArrayList<String> cosenoYUsuarios= new ArrayList<>();
        for(int usuarioCandidato : usuarios){
            double cos=coseno(pUsuario,usuarioCandidato);
            String s="";
            s+=cos+"//"+usuarioCandidato;
            cosenoYUsuarios.add(s);
        }
        quickSort(cosenoYUsuarios,0,cosenoYUsuarios.size()-1);
        return sublista(cosenoYUsuarios,cosenoYUsuarios.size()-30,cosenoYUsuarios.size());
    }
    
    /**
     * Selecciona la lista con los 30 usuarios mas similares a pUsuario que han valorado pPelicula y calcula la nota a partir de dividendo donde 
     * almacena la suma de productos de similitudes del usuario seleccionado con pUsuario por la valoracion a pPelicula del usuario seleccionado 
     * y el divisor donde suma las similitudes
     * @param pUsuario
     * @param pPelicula
     * @return 
     */
    @Override
    public double calcularNota(int pUsuario, int pPelicula){
        ArrayList<String> usuarios = seleccionar30UsuariosSimilaresConValoracion(pUsuario, pPelicula);
        double dividendo=0;
        double divisor=0;
        for(String cosenoYUsuario : usuarios){
            String partes[]=cosenoYUsuario.split("//");
            double cos=getParteReal(partes);
            int idUsuario=getParteEntera(partes);
            dividendo+= cos*CatalogoUsuarios.getMiCU().getUsuario(idUsuario).getValoracion(pPelicula);
            divisor+= cos;
        }
        return dividendo/divisor;
    }
    
    /**
     * Calcula la puntuacion que le daria pUsuario a todas las peliculas y selecciona las 30 con la nota mas alta 
     * @param pUsuario
     * @return 
     */
    @Override
    public ArrayList<String> seleccionar30MejoresPelisPara(int pUsuario){
        ArrayList<String> listaPelis= new ArrayList<>();
        for(int idPelicula : CatalogoPeliculas.getMiCPeli().getListaPeliculas()){
            listaPelis.add(String.valueOf(calcularNota(pUsuario, idPelicula)+"//"+CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getNombre()));
        }
        quickSort(listaPelis,0,listaPelis.size()-1);
        return sublista(listaPelis,listaPelis.size()-30,listaPelis.size());
    }
    
    /**
     * Devuelve la lista de ids de usuario que han valorado a pPelicula
     * @param pPelicula
     * @return 
     */
    private ArrayList<Integer> obtenerListaUsuarios( int pPelicula){
        String[] usuariosQueHanValorado= CatalogoPeliculas.getMiCPeli().getPelicula(pPelicula).getListaUsuarios().split("/");
        ArrayList<Integer> usuarios = new ArrayList<>();
        for(String usuarioConValoracion :usuariosQueHanValorado ){
            usuarios.add(Integer.parseInt(usuarioConValoracion));
        }
        return usuarios;
    }

}
