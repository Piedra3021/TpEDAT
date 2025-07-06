package lineales.dinamica;

class Nodo {
    private Object elemento; // Dato almacenado en el nodo
    private Nodo enlace; // Referencia al siguiente nodo en la pila
    
    /**
     * Constructor de la clase Nodo.
     * @param elem Elemento a almacenar en el nodo.
     * @param sig Nodo siguiente en la pila.
     */
    public Nodo(Object elem, Nodo sig) {
        this.elemento = elem;
        this.enlace = sig;
    }
    public Nodo(Object elem) {
        this.elemento = elem;
        this.enlace = null;
    }
    
    /**
     * Método para obtener el elemento almacenado en el nodo.
     * @return Elemento almacenado.
     */
    public Object getElemento() {
        return this.elemento;
    }
    
    /**
     * Método para obtener el siguiente nodo en la pila.
     * @return Nodo siguiente.
     */
    public Nodo getEnlace() {
        return this.enlace;
    }
    
    /**
     * Método para establecer el enlace al siguiente nodo.
     * @param nodo Nuevo nodo siguiente.
     */
    public void setEnlace(Nodo nodo) {
        this.enlace = nodo;
    }
}
