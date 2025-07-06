package jerarquicas;

public class NodoArbol {
    Object elemento;
    NodoArbol hijoIzquierdo;
    NodoArbol hijoDerecho;

    public NodoArbol(Object elem){
        this.elemento = elem;
        hijoIzquierdo = null;
        hijoDerecho = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public NodoArbol getHijoDerecho() {
        return hijoDerecho;
    }

    public NodoArbol getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public void setHijoDerecho(NodoArbol hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void setHijoIzquierdo(NodoArbol hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

}
