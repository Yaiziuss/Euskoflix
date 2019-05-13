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
    
    
    /**
     * Aplica el quicksort a pLista, con inicio pBegin y final pEnd
     * @param pLista
     * @param pBegin
     * @param pEnd 
     */
    public void quickSort(ArrayList<String> pLista, int pBegin, int pEnd) {
        if (pBegin < pEnd) {
            int indiceParticion = partition(pLista, pBegin, pEnd);
            quickSort(pLista , pBegin, indiceParticion-1);
            quickSort(pLista, indiceParticion+1, pEnd);
        }
    }
    /**
     * Ordena  pLista moviendo a la izquierda todos los elementos menores que el pivote y a la derecha los mayores
     * @param pLista
     * @param pBegin
     * @param pEnd
     * @return 
     */
    private int partition(ArrayList<String> pLista, int pBegin, int pEnd) {
        
        double pivote = getParteReal(pLista.get(pEnd).split("//"));
        int i = pBegin-1;

        for (int j = pBegin; j < pEnd; j++) {
            
            if (getParteReal(pLista.get(j).split("//")) <= pivote) {
                i++;

                String aux = pLista.get(i);
                pLista.set(i,pLista.get(j));
                pLista.set(j, aux);
            }
        }

        String aux = pLista.get(i+1);
        pLista.set(i+1, pLista.get(pEnd));
        pLista.set(pEnd, aux);
        return i+1;
    }
    
    /**
     * Devuelve el primer elemento de pPartes y lo convierte a double, para evitar Excepciones en caso de que sea mas largo de 15 caracteres recorta la cadena a esa longitud
     * @param pPartes
     * @return double
     */
    public double getParteReal(String[] pPartes){
        return (pPartes[0].length()>15)?Double.parseDouble(pPartes[0].substring(0,14)):Double.parseDouble(pPartes[0]);
    }
    
    /**
     * Devuelve el segundo elemento de pPartes y lo convierte a int
     * @param pPartes
     * @return int
     */
    public int getParteEntera(String[] pPartes){
        return Integer.parseInt(pPartes[1]);
    }
    
    /**
     * Genera una sublista de pLista cuyos elementos estan comprendidos entre [pComienzo,pEnd]
     * @param pLista
     * @param pComienzo
     * @param pEnd
     * @return 
     */
    public ArrayList<String> sublista(ArrayList<String> pLista, int pComienzo, int pEnd){
        ArrayList<String> l= new ArrayList<>();
        int act=-1;
        for (String s: pLista){
            act++;
            if(act<pComienzo) continue;
            else if(act==pEnd) break;
            else l.add(s);
        }
        return l;
    }
}
