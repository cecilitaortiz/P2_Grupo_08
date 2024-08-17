/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    // Este método crea un árbol binario donde cada nodo contiene un ArrayList de dos elementos:
    // Índice 0: La respuesta (true/false) que llevó a la pregunta.
    // Índice 1: El contenido de la pregunta (String).
    // Las hojas del árbol contienen en el Índice 0 un ArrayList<Boolean> con el camino de respuestas,
    // y en el Índice 1 el valor correspondiente en el HashMap de respuestas.
    public static ArbolBinario<ArrayList<Object>> arboljuego(ArrayList<String> preguntas, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas) {
        int altura = preguntas.size();
        if (altura <= 0) {
            return null; // Si no hay preguntas, no se puede crear el árbol
        }

        // Crear la raíz del árbol con la primera pregunta
        ArrayList<Object> raizContenido = new ArrayList<>();
        raizContenido.add(true); // Se inicia el árbol con una respuesta positiva
        raizContenido.add(preguntas.get(0));
        ArbolBinario<ArrayList<Object>> arbol = new ArbolBinario<>(raizContenido);

        // Método auxiliar para construir los subárboles recursivamente
        crearSubArbol(arbol, altura - 1, preguntas, respuestas, new ArrayList<>());
        return arbol;
    }

    // Método recursivo para construir los subárboles
    private static void crearSubArbol(ArbolBinario<ArrayList<Object>> arbol, int altura, ArrayList<String> preguntas, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas, ArrayList<Boolean> caminoRespuestas) {
        if (altura == 0) {
            // Si llegamos a una hoja, verificamos el camino de respuestas
            procesarHoja(arbol, respuestas, caminoRespuestas);
        } else {
            // Construcción de subárboles para las respuestas true y false
            construirSubArboles(arbol, altura, preguntas, respuestas, caminoRespuestas);
        }
    }

    // Método para procesar la lógica de una hoja del árbol
    private static void procesarHoja(ArbolBinario<ArrayList<Object>> arbol, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas, ArrayList<Boolean> caminoRespuestas) {
        ArrayList<Boolean> caminoTrue = new ArrayList<>(caminoRespuestas);
        caminoTrue.add(true);
        ArrayList<Boolean> caminoFalse = new ArrayList<>(caminoRespuestas);
        caminoFalse.add(false);

        ArbolBinario<ArrayList<Object>> subArbolIzq = crearHoja(respuestas, caminoTrue, "No se pudo encontrar el animal");
        ArbolBinario<ArrayList<Object>> subArbolDer = crearHoja(respuestas, caminoFalse, "No se pudo encontrar el animal");

        arbol.addLeft(subArbolIzq);
        arbol.addRight(subArbolDer);
    }

    // Método para crear una hoja en el árbol
    private static ArbolBinario<ArrayList<Object>> crearHoja(HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas, ArrayList<Boolean> camino, String mensajePorDefecto) {
        ArrayList<Object> contenido = new ArrayList<>();
        if (isSubArray(respuestas.keySet(), camino)) {
            ArrayList<String> arrayRespuestas=new ArrayList<>();
            for(ArrayList<Boolean> a:arrayCamino(respuestas.keySet(), camino))
                arrayRespuestas.addAll(respuestas.get(a));
            contenido.add(arrayRespuestas);
        } else {
            contenido.add(mensajePorDefecto);
        }
        contenido.add(camino);
        return new ArbolBinario<>(contenido);
    }

    // Método para construir los subárboles recursivamente
    private static void construirSubArboles(ArbolBinario<ArrayList<Object>> arbol, int altura, ArrayList<String> preguntas, HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas, ArrayList<Boolean> caminoRespuestas) {
        // Actualizamos la lista de preguntas eliminando la primera
        ArrayList<String> siguientesPreguntas = new ArrayList<>(preguntas.subList(1, preguntas.size()));

        // Construir subárbol para la respuesta true
        ArrayList<Boolean> caminoTrue = new ArrayList<>(caminoRespuestas);
        caminoTrue.add(true);
        ArrayList<Object> contenidoIzq = new ArrayList<>();
        contenidoIzq.add(true);
        contenidoIzq.add(siguientesPreguntas.get(0));
        ArbolBinario<ArrayList<Object>> subArbolIzq = new ArbolBinario<>(contenidoIzq);
        arbol.addLeft(subArbolIzq);

        // Construir subárbol para la respuesta false
        ArrayList<Boolean> caminoFalse = new ArrayList<>(caminoRespuestas);
        caminoFalse.add(false);
        ArrayList<Object> contenidoDer = new ArrayList<>();
        contenidoDer.add(false);
        contenidoDer.add(siguientesPreguntas.get(0));
        ArbolBinario<ArrayList<Object>> subArbolDer = new ArbolBinario<>(contenidoDer);
        arbol.addRight(subArbolDer);

        // Llamadas recursivas para construir el resto del árbol
        crearSubArbol(subArbolIzq, altura - 1, siguientesPreguntas, respuestas, caminoTrue);
        crearSubArbol(subArbolDer, altura - 1, siguientesPreguntas, respuestas, caminoFalse);
    }

    // Método que valida si array2 es un subarray de algún array en array1
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

    // Método que retorna una lista de arrays en array1 que contienen a array2 como subarray
    public static List<ArrayList<Boolean>> arrayCamino(Set<ArrayList<Boolean>> array1, ArrayList<Boolean> array2) {
        List<ArrayList<Boolean>> resultados = new ArrayList<>();
        for (ArrayList<Boolean> list : array1) {
            int size = array2.size();
            for (int i = 0; i <= list.size() - size; i++) {
                if (list.subList(i, i + size).equals(array2)) {
                    resultados.add(list);
                    break; // No es necesario seguir buscando en la misma lista
                }
            }
        }
        return resultados;
    }

    // Encuentra el animal basado en el camino de respuestas
    // Retorna una cadena con las respuestas separadas por comas
    public String encontrarAnimal(ArrayList<Object> respuestas) {
        if (isEmpty()) {
            return null; // Retorna null si el árbol está vacío
        }

        // Inicializar el StringBuilder para construir la respuesta
        StringBuilder resultado = new StringBuilder();

        // Si es una hoja, se obtiene la respuesta del contenido del nodo
        if (esHoja()) {
            resultado.append(((ArrayList<Object>)raiz.contenido).get(0));
        } else {
            // Crear una lista de respuestas restantes
            ArrayList<Object> respuestasRestantes = new ArrayList<>(respuestas.subList(1, respuestas.size()));

            // Recursivamente encontrar el animal en el subárbol izquierdo o derecho
            if (respuestas.get(0).equals(true)) {
                resultado.append(raiz.izq.encontrarAnimal(respuestasRestantes));
            } else {
                resultado.append(raiz.der.encontrarAnimal(respuestasRestantes));
            }
        }

        // Convertir el resultado a una cadena y eliminar posibles corchetes
        String resultadoStr = resultado.toString();
        if (resultadoStr.startsWith("[") && resultadoStr.endsWith("]")) {
            resultadoStr = resultadoStr.substring(1, resultadoStr.length() - 1);
        }

        // Retornar el resultado final sin corchetes y con elementos separados por comas
        return resultadoStr;
    }

    
}
