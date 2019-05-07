/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Euskoflix;

import java.util.ArrayList;

/**
 *
 * @author Elalfred
 */
public abstract class Filtrado {
    public abstract double calcularNota(int a, int b);
    
    public abstract double productoEscalar(int a, int b);
    
    public abstract double coseno(int a, int b);
    
    public abstract ArrayList<String> seleccionar30MejoresPelisPara(int a);
    
    public void quickSort(ArrayList<String> lista, int begin, int end) {
        if (begin < end) {
            int indiceParticion = partition(lista, begin, end);
            quickSort(lista , begin, indiceParticion-1);
            quickSort(lista, indiceParticion+1, end);
        }
    }
    /**
     * 
     * @param lista
     * @param begin
     * @param end
     * @return 
     */
    private int partition(ArrayList<String> lista, int begin, int end) {
        
        double pivote = getParteReal(lista.get(end).split("//"));
        int i = begin-1;

        for (int j = begin; j < end; j++) {
            
            if (getParteReal(lista.get(j).split("//")) <= pivote) {
                i++;

                String aux = lista.get(i);
                lista.set(i,lista.get(j));
                lista.set(j, aux);
            }
        }

        String aux = lista.get(i+1);
        lista.set(i+1, lista.get(end));
        lista.set(end, aux);
        return i+1;
    }
    
    public double getParteReal(String[] s){
        return (s[0].length()>15)?Double.parseDouble(s[0].substring(0,14)):Double.parseDouble(s[0]);
    }
    
    public int getParteEntera(String[] s){
        return Integer.parseInt(s[1]);
    }
    
    public ArrayList<String> sublista(ArrayList<String> lista, int comienzo, int end){
        ArrayList<String> l= new ArrayList<>();
        int act=-1;
        for (String s: lista){
            act++;
            if(act<comienzo) continue;
            else if(act==end) break;
            else l.add(s);
        }
        return l;
    }
}
