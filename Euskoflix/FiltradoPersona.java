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
    @Override
    public double coseno(int usuario1, int usuario2 ){
        Operacion n=(int id) ->{
            double modulo=0;
            modulo = CatalogoUsuarios.getMiCU().getUsuario(id).getPeliculaYValoracion().stream()
                    .map((par) -> Math.pow(par.getValue(),2))
                    .reduce(modulo, (accumulator, _item) -> accumulator + _item);
            return Math.sqrt(modulo);
        };
        return productoEscalar( usuario1,  usuario2 )/(n.norma(usuario1)*n.norma(usuario2));
    }

    @Override
    public double productoEscalar(int usuario1, int usuario2 ){
        double dot=0;
        if(usuario1!=usuario2){
            for( Map.Entry<Integer,Double> parUsuario1 : CatalogoUsuarios.getMiCU().getUsuario(usuario1).getPeliculaYValoracion()){
                int peliculaAEvaluar=parUsuario1.getKey();
                dot+= parUsuario1.getValue()*CatalogoUsuarios.getMiCU().getUsuario(usuario2).getValoracion(peliculaAEvaluar);
            }
        }
        return dot;
    } 
    
    private ArrayList<String> seleccionar30UsuariosSimilaresConValoracion(int usuario, int pelicula){ 
        ArrayList<Integer> usuarios = obtenerListaUsuarios(pelicula);
        ArrayList<String> cosenoYUsuarios= new ArrayList<>();
        for(int idUsuario : usuarios){
            double cos=coseno(usuario,idUsuario);
            String s="";
            s+=cos+"//"+idUsuario;
            cosenoYUsuarios.add(s);
        }
        quickSort(cosenoYUsuarios,0,cosenoYUsuarios.size()-1);
        return sublista(cosenoYUsuarios,cosenoYUsuarios.size()-30,cosenoYUsuarios.size());
    }
    
    @Override
    public double calcularNota(int usuario, int pelicula){
        ArrayList<String> usuarios = seleccionar30UsuariosSimilaresConValoracion(usuario, pelicula);
        double dividendo=0;
        double divisor=0;
        for(String cosenoYUsuario : usuarios){
            String partes[]=cosenoYUsuario.split("//");
            double cos=getParteReal(partes);
            int idUsuario=getParteEntera(partes);
            dividendo+= cos*CatalogoUsuarios.getMiCU().getUsuario(idUsuario).getValoracion(pelicula);
            divisor+= cos;
        }
        return dividendo/divisor;
    }
    
    @Override
    public ArrayList<String> seleccionar30MejoresPelisPara(int usuario){
        ArrayList<String> listaPelis= new ArrayList<>();
        for(int idPelicula : CatalogoPeliculas.getMiCPeli().getListaPeliculas()){
            listaPelis.add(String.valueOf(calcularNota(usuario, idPelicula)+"//"+CatalogoPeliculas.getMiCPeli().getPelicula(idPelicula).getNombre()));
        }
        quickSort(listaPelis,0,listaPelis.size()-1);
        return sublista(listaPelis,listaPelis.size()-30,listaPelis.size());
    }
    
    private ArrayList<Integer> obtenerListaUsuarios( int pelicula){
        String[] usuariosQueHanValorado= CatalogoPeliculas.getMiCPeli().getPelicula(pelicula).getListaUsuarios().split("/");
        ArrayList<Integer> usuarios = new ArrayList<>();
        for(String usuarioConValoracion :usuariosQueHanValorado ){
            usuarios.add(Integer.parseInt(usuarioConValoracion));
        }
        return usuarios;
    }

}
