package main.java.conjuntistas;

public class NodoABB {
    
    Object elem;
    NodoABB izquierdo;
    NodoABB derecho;

    public NodoABB(Object elemento, NodoABB hijoIzq, NodoABB hijoDer){
        this.elem = elemento;
        this.izquierdo = hijoIzq;
        this.derecho = hijoDer;
    }

    public NodoABB(Object elemento){
        this.elem = elemento;
        this.derecho = null;
        this.izquierdo = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB hijoIzquierdo) {
        this.izquierdo = hijoIzquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB hijoDerecho) {
        this.derecho = hijoDerecho;
    }

    
}
