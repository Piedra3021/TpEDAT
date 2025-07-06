package lineales.dinamica;
//import main.java.lineales.dinamicas.Nodo;

/**
 * Clase Nodo utilizada en la implementación dinámica de la pila.
 * Contiene un elemento y una referencia al siguiente nodo.
 */


/**
 * Implementación de la Pila de manera dinámica utilizando nodos enlazados.
 */
public class Pila {
    private Nodo tope; // Referencia al nodo en la cima de la pila
    
    /**
     * Constructor de la clase Pila.
     * Inicializa la pila vacía.
     */
    public Pila() {
        this.tope = null; // La pila comienza vacía
    }
    
    /**
     * Método que permite apilar un elemento en la pila.
     * @param nuevoElemento Elemento a agregar en la pila.
     * @return true, ya que la pila dinámica nunca está llena.
     */
    public boolean apilar(Object nuevoElemento) {
        this.tope = new Nodo(nuevoElemento, this.tope); // Se crea un nuevo nodo y se enlaza al tope
        return true; // Siempre es exitoso porque la pila no tiene límite de tamaño
    }
    
    /**
     * Método que permite desapilar un elemento de la pila.
     * @return true si el elemento fue eliminado, false si la pila está vacía.
     */
    public boolean desapilar() {
        boolean exito = false;
        if (!this.esVacia()) { // Verifica si la pila no está vacía
            this.tope = this.tope.getEnlace(); // Se mueve el tope al siguiente nodo
            exito = true;
        }
        return exito;
    }
    
    /**
     * Método que devuelve el elemento en el tope de la pila sin eliminarlo.
     * @return Elemento en el tope o null si la pila está vacía.
     */
    public Object obtenerTope() {
        Object elemento = null;
        if (this.tope != null) { // Verifica si la pila no está vacía
            elemento = this.tope.getElemento(); // Obtiene el elemento del nodo en la cima
        }
        return elemento;
    }
    
    /**
     * Método que verifica si la pila está vacía.
     * @return true si la pila está vacía, false en caso contrario.
     */
    public boolean esVacia() {
        return this.tope == null;
    }
    
    /**
     * Método que vacía completamente la pila.
     */
    public void vaciar() {
        this.tope = null; // Se elimina la referencia a los nodos, liberando memoria automáticamente
    }
    
    /**
     * Método que crea y devuelve una copia exacta de la pila.
     * Se copian los elementos en el mismo orden en una nueva instancia.
     * @return Nueva instancia de Pila con los mismos elementos.
     */
    public Pila clone() {
        Pila nuevaPila = new Pila(); // Se crea una nueva pila vacía
        if (!this.esVacia()) { // Si la pila no está vacía
            Nodo aux = this.tope;
            Nodo nuevaCima = new Nodo(aux.getElemento(), null);
            nuevaPila.tope = nuevaCima;
            Nodo actual = nuevaCima;
            aux = aux.getEnlace();
            
            while (aux != null) {
                actual.setEnlace(new Nodo(aux.getElemento(), null));
                actual = actual.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return nuevaPila;
    }
    
    /**
     * Método que devuelve una representación en String de la pila.
     * @return Cadena de caracteres con los elementos de la pila.
     */
    
     public String toString() {
        String cad = "[]";
        if (this.tope != null) {
            cad = "[";
            Nodo aux = this.tope;
            do {
                cad = cad + aux.getElemento();
                aux = aux.getEnlace();
                if (aux != null) {
                    cad = cad + ",";
                }
            } while (aux != null);

            cad = cad + "]";
        }
        return cad;
    }
}


