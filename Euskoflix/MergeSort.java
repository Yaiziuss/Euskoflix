package Euskoflix;
import java.util.ArrayList;
public class MergeSort {
	    // Constructor
	    public MergeSort() {}
	     
	    //El int corresponde a los identificadores
	    public ArrayList<Integer> mergeSort(ArrayList<Integer> pLista) {
	        ArrayList<Integer> izqda = new ArrayList<Integer>();
	        ArrayList<Integer> drch = new ArrayList<Integer>();
	        int center;
	 
	        if (pLista.size() == 1) {    
	            return pLista;
	        } else {
	            center = pLista.size()/2;
	            // Copia la mitad izquierda de todo en la izquierda.
	            for (int i=0; i<center; i++) {
	                    izqda.add(pLista.get(i));
	            }
	 
	            //Copia la mitad derecha de todo en el nuevo arraylist.
	            for (int i=center; i<pLista.size(); i++) {
	                    drch.add(pLista.get(i));
	            }
	 
	            // Ordenar las mitades izquierda y derecha del arraylist.
	            izqda  = mergeSort(izqda);
	            drch = mergeSort(drch);
	 
	            // Combinar los resultados de nuevo juntos.
	            merge(izqda, drch, pLista);
	        }
	        return pLista;
	    }
	 
	    private void merge(ArrayList<Integer> izqda, ArrayList<Integer> drch, ArrayList<Integer> pLista) {
	        int izqdaIndex = 0;
	        int drchIndex = 0;
	        int pListaIndex = 0;
	 
	        // Mientras no se haya usado el ArrayList izquierdo ni el derecho, 
	        // siga tomando el menor izqda.get (izqdaIndex) o drch.get (drchIndex) 
	        // y añádalo a both.get (bothIndex).
	        while (izqdaIndex < izqda.size() && drchIndex < drch.size()) {
	            if ( (izqda.get(izqdaIndex).compareTo(drch.get(drchIndex))) < 0) {
	                pLista.set(pListaIndex, izqda.get(izqdaIndex));
	                izqdaIndex++;
	            } else {
	                pLista.set(pListaIndex, drch.get(drchIndex));
	                drchIndex++;
	            }
	            pListaIndex++;
	        }
	 
	        ArrayList<Integer> nuevo;
	        int nuevoIndex;
	        if (izqdaIndex >= izqda.size()) {
	            // El ArrayList izquierdo ha sido usado hasta ...
	            nuevo = drch;
	            nuevoIndex = drchIndex;
	        } else {
	            // El ArrayList derecho ha sido usado hasta ...
	            nuevo = izqda;
	            nuevoIndex = izqdaIndex;
	        }
	 
	        // Copia el nuevoo de cualquier ArrayList (izquierda o derecha) que no se haya utilizado.
	        for (int i=nuevoIndex; i<nuevo.size(); i++) {
	            pLista.set(pListaIndex, nuevo.get(i));
	            pListaIndex++;
	        }
	    }
	 
	    public void imprimir(ArrayList<Integer> strList) {
	    	if (strList!=null){
		        for (int i=0; i< strList.size();i++) {
		            System.out.println(strList.get(i)/*.getNombre()*/);
		        }
	    	}
	    }
}
