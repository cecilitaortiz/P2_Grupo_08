/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Cecy
 */
public class ArbolBinario<E> {

    Nodo raiz;

    private class Nodo {

        E contenido;
        ArbolBinario<E> izq, der;

        public Nodo(E e) {
            contenido = e;
            izq = der = null;
        }
    }

    public ArbolBinario() {
        clear();
    }

    public ArbolBinario(E contenido) {
        raiz = new Nodo(contenido);
    }

    public void clear() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public boolean esHoja() {
        if (isEmpty()) {
            return false;
        }
        return raiz.der == null && raiz.izq == null;
    }

    public int altura() {
        if (isEmpty()) {
            return 0;
        }
        if (esHoja()) {
            return 1;
        }
        int alturaIzq = (raiz.izq != null) ? raiz.izq.altura() : 0;
        int alturaDer = 0;
        if (raiz.der != null) {
            alturaDer = raiz.der.altura();
        }

        return 1 + Math.max(alturaDer, alturaIzq);
    }

    public boolean addLeft(ArbolBinario ab) {
        if (raiz.izq != null) {
            return false;
        }
        raiz.izq = ab;
        return true;
    }

    public boolean addRight(ArbolBinario ab) {
        if (raiz.der != null) {
            return false;
        }
        raiz.der = ab;
        return true;
    }

    public ArrayList<E> recorridoPreOrden() {
        if (isEmpty()) {
            return null;
        }
        ArrayList<E> recorrido = new ArrayList<>();
        recorrido.add(raiz.contenido);

        if (raiz.izq != null) {
            recorrido.addAll(raiz.izq.recorridoPreOrden());
        }
        if (raiz.der != null) {
            recorrido.addAll(raiz.der.recorridoPreOrden());
        }
        return recorrido;
    }
    
    public static ArbolBinario<ArrayList<Object>> crearArbolBinario(ArrayList<String> preguntas, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas) {
        int altura=preguntas.size();
        if (altura <= 0) {
            return null;
        }

        // Crear la raíz del árbol
        ArrayList<Object> r=new ArrayList<>();
        r.add(true);
        r.add(preguntas.get(0));
        ArbolBinario<ArrayList<Object>> arbol = new ArbolBinario<>(r);

        // Método auxiliar para crear los subárboles recursivamente
        crearSubArbol(arbol, altura - 1, true, preguntas, respuestas, new ArrayList<>());
        return arbol;
    }

    public static void crearSubArbol(ArbolBinario<ArrayList<Object>> arbol, int altura, boolean esIzquierda, ArrayList<String> preguntas, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas, ArrayList<Boolean> arregloComparar) {
        if (altura == 0) {
            
            
            ArrayList<Boolean> arrbooltrue=new ArrayList<>();
            arrbooltrue.addAll(arregloComparar);
            arrbooltrue.add(true); 
            ArrayList<Boolean> arrboolfalse=new ArrayList<>();
            arrboolfalse.addAll(arregloComparar);
            arrboolfalse.add(false);
            ArrayList<Object> arrayRespuestasV=new ArrayList<>();
            arrayRespuestasV.add("No se pudo encontrar el animal");
            arrayRespuestasV.add(arrbooltrue);
            ArrayList<Object> arrayRespuestasF=new ArrayList<>();
            arrayRespuestasF.add("No se pudo encontrar el animal");
            arrayRespuestasF.add(arrboolfalse);
            ArrayList<Object> answers=new ArrayList<>();

            if(isSubArray(respuestas.keySet(),arrbooltrue) && isSubArray(respuestas.keySet(),arrboolfalse)){ 
                
                ArrayList<Object> answersizq=new ArrayList<>();
                answersizq.add(respuestas.get(subArray(respuestas.keySet(),arrbooltrue)));
                answersizq.add(arrbooltrue);
                ArrayList<Object> answersder=new ArrayList<>();
                answersder.add(respuestas.get(subArray(respuestas.keySet(),arrboolfalse)));
                answersder.add(arrboolfalse);
                ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(answersizq);
                ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(answersder);
                arbol.addLeft(subArbolIzq);
                arbol.addRight(subArbolDer);
            }else if(isSubArray(respuestas.keySet(),arrbooltrue)){
                answers.add(respuestas.get(subArray(respuestas.keySet(),arrbooltrue)));
                answers.add(arrbooltrue);
                ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(answers);
                ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(arrayRespuestasF);
                arbol.addLeft(subArbolIzq);
                arbol.addRight(subArbolDer);
            }else if(isSubArray(respuestas.keySet(),arrboolfalse)){
                answers.add(respuestas.get(subArray(respuestas.keySet(),arrboolfalse)));
                answers.add(arrboolfalse);
                ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(arrayRespuestasV);
                ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(answers);
                arbol.addLeft(subArbolIzq);
                arbol.addRight(subArbolDer);
            }else{
                ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(arrayRespuestasV);
                ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(arrayRespuestasF);
                arbol.addLeft(subArbolIzq);
                arbol.addRight(subArbolDer);
            }
        }else{
            // Crear subárbol a la izquierda con valor true
            ArrayList<String> npreguntas=new ArrayList<>();
            for(int i=1;i<preguntas.size();i++) npreguntas.add(preguntas.get(i));
            ArrayList<Boolean> arrbooltrue=new ArrayList<>();
            arrbooltrue.addAll(arregloComparar);
            arrbooltrue.add(true);
            ArrayList<Object> arrayRespuestasV=new ArrayList<>();
            arrayRespuestasV.add(true);
            arrayRespuestasV.add(npreguntas.get(0));
            ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(arrayRespuestasV);
            arbol.addLeft(subArbolIzq);
            
            // Crear subárbol a la derecha con valor false
            ArrayList<Boolean> arrboolfalse=new ArrayList<>();
            arrboolfalse.addAll(arregloComparar);
            arrboolfalse.add(false);
            ArrayList<Object> arrayRespuestasF=new ArrayList<>();
            arrayRespuestasF.add(false);
            arrayRespuestasF.add(npreguntas.get(0));
            ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(arrayRespuestasF);
            arbol.addRight(subArbolDer);


            // Recursivamente construir el resto del árbol
            crearSubArbol(subArbolIzq, altura - 1, true, npreguntas, respuestas,arrbooltrue);
            crearSubArbol(subArbolDer, altura - 1, false, npreguntas, respuestas,arrboolfalse);
        }
    }
    
    public static boolean isSubArray(Set<ArrayList<Boolean>> array1, ArrayList<Boolean> array2) {
        for (ArrayList<Boolean> list : array1) {
            int size = array2.size();
            for (int i = 0; i <= list.size() - size; i++) {
                if (list.subList(i, i + size).equals(array2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static ArrayList<Boolean> subArray(Set<ArrayList<Boolean>> array1, ArrayList<Boolean> array2){
        for (ArrayList<Boolean> list : array1) {
            int size = array2.size();
            for (int i = 0; i <= list.size() - size; i++) {
                if (list.subList(i, i + size).equals(array2)) {
                    return list;
                }
            }
        }
        return array2;
    }
    
    public String encontrarAnimal(ArrayList<Object> respuestas) {
        if (isEmpty()) {
            return null;
        }
        String res="";
//        System.out.print(esHoja());
//        System.out.print(raiz.contenido);
        if (esHoja()){
            res+=((ArrayList<Object>)raiz.contenido).get(0);
        }
        else{
            ArrayList<Object> arr=new ArrayList<>();
            for(int i=1;i<respuestas.size();i++) arr.add(respuestas.get(i));
            if(respuestas.size()>=1)
                if(respuestas.get(0).equals(true)) res+=raiz.izq.encontrarAnimal(arr);
                else res+=raiz.der.encontrarAnimal(arr);
        }
        return res;
    }
    
}
