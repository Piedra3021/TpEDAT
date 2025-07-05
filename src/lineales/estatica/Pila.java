package main.java.lineales.estatica;

/*AUTORES: 
Alan Cavieres FAI-3810  
Pedro Beltran FAI-4180
Antonella Maldonado FAI-4820 
Jazmin Valenzuela FAI-4322 
*/

public class Pila {
    
    private Object[] arreglo; // Arreglo que almacena los elementos de la pila
    private int tope; // Indice del ultimo elemento agregado en la pila
    private static final int TAMANIO = 10; // Capacidad máxima de la pila
    
    /*
    Constructor de la clase Pila.
    Inicializa el arreglo con el tamaño máximo y establece el tope en -1 (pila vacía).
     */
    public Pila() {
        this.arreglo = new Object[TAMANIO]; // Se crea el arreglo con tamaño fijo
        this.tope = -1; // Se inicializa la pila como vacía
    }
    
    /*Modulo que permite apilar un elemento en la pila.*/
    public boolean apilar(Object nuevoElemento) {
        boolean exito; 
        if (this.tope + 1 >= TAMANIO) { // Verifica si la pila está llena
            exito = false; // No se puede apilar más elementos
        } else {
            this.tope++; 
            this.arreglo[tope] = nuevoElemento; // Se almacena el nuevo elemento
            exito = true; 
        }
        return exito; 
    }
    
    /*Modulo que permite desapilar un elemento de la pila*/
    public boolean desapilar() {
        boolean exito = false;
        if (!this.esVacia()) { // Verifica si la pila no está vacía
            this.arreglo[this.tope]=null;
            this.tope--; // Se decrementa el indice del tope
            exito = true;
        }
        return exito;
    }
    
    /*Método que devuelve el elemento en el tope de la pila sin eliminarlo*/
    public Object obtenerTope() {
        Object elemento = null;
        if (this.tope >= 0) { // Verifica si la pila no está vacía
            elemento = this.arreglo[this.tope]; // Se obtiene el elemento del tope
        }
        return elemento;
    }
    
    /* Método que verifica si la pila está vacía*/
    public boolean esVacia() {
        return this.tope == -1;
    }
    

    public void vaciar() {
        while (!(this.tope == -1)) {
            this.arreglo[this.tope] = null;
            this.tope--;
        }
    }
    
    /*Modulo que crea y devuelve una copia exacta de la pila*/
    public Pila clone() {
        Pila nueva = new Pila(); // Se crea una nueva instancia de Pila
        nueva.tope = this.tope; // Se copia el indice del tope actual
    
        // Se copian los elementos manualmente usando un bucle
        for (int i = 0; i <= this.tope; i++) {
            nueva.arreglo[i] = this.arreglo[i];
        }
        nueva.tope=this.tope;
    
        return nueva;
    }

    public Pila clone2(){
        Pila copia = new Pila();

        copia.arreglo = this.arreglo.clone();
        copia.tope = this.tope;

        return copia;
    }

    
    public String toString() {
        String cad = "[]";
        if (!esVacia()) {
            cad = "[";
            for (int i = tope; i >= 0; i--) {
                cad = cad + this.arreglo[i];
                if (i != 0) {
                    cad = cad + ",";
                }
            }
            cad = cad + "]";
        }
        return cad;
    }
}