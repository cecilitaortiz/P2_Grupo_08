/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;


import java.util.*;

/**
 *
 * @author Cecy
 */
public class ABB<E extends Comparable<E>> extends ArbolBinario<E> {

    private Nodo raiz;

    public ABB(E contenido) {
        super(contenido);
    }

    public boolean add(E contenido) {
        if (isEmpty()) {
            raiz = new Nodo(contenido);
            return true;
        }
        return add(raiz, contenido);
    }

    private boolean add(Nodo nodo, E contenido) {
        int cmp = contenido.compareTo(nodo.contenido);
        if (cmp < 0) {
            if (nodo.izq == null) {
                nodo.izq = new Nodo(contenido);
                return true;
            } else {
                return add(nodo.izq, contenido);
            }
        } else if (cmp > 0) {
            if (nodo.der == null) {
                nodo.der = new Nodo(contenido);
                return true;
            } else {
                return add(nodo.der, contenido);
            }
        } else {
            // contenido ya existe en el árbol
            return false;
        }
    }

    private class Nodo {

        E contenido;
        Nodo izq;
        Nodo der;

        Nodo(E contenido) {
            this.contenido = contenido;
            izq = null;
            der = null;
        }
    }

    @Override
    public void clear() {
        raiz = null;
    }

    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    @Override
    public boolean esHoja() {
        if (isEmpty()) {
            return false;
        }
        return raiz.der == null && raiz.izq == null;
    }

    @Override
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    
 
}
