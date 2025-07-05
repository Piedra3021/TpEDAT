/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.lineales.dinamica;

/**
 *
 * @author pedro.beltran
 */

public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object elem) {
        Nodo obj = new Nodo(elem,null);

        //Caso especial, cola vacia. Si tiene un solo elemento el frente y el fin serán el mismo.
        if (this.frente == null && this.fin == null) {
            this.frente = obj;
            this.fin = obj;
        } else {
            this.fin.setEnlace(obj);
            this.fin = obj;
        }

        return true;
    }

    public boolean sacar() {
        boolean exito = false;
        //Verifico que el exista almenos un elemento
        if (this.frente != null) {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }
        return exito;
    }

    public boolean esVacia() {
        return this.frente == null && this.fin == null;
    }

    public Object obtenerFrente() {
        Object elem = null;

        if (!esVacia()) {
            elem = this.frente.getElemento();
        }
        return elem;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Cola copia = new Cola();
        if (!esVacia()) {
            //Actual recorre la cola original, aux el clon
            Nodo actual, aux;
            //El frente no puede ser nulo
            actual = this.frente.getEnlace();
            aux = new Nodo(this.frente.getElemento(), null);
            
            copia.frente = aux;
            //Voy comprobando el nodo que va a enlazar a la copia
            while (actual != null) {
                aux.setEnlace(new Nodo(actual.getElemento(), null));
                aux = aux.getEnlace();
                actual = actual.getEnlace();
            }
            copia.fin = aux;
        }
        return copia;
    }

    public String toString() {
        String cad = "[]";
        if (this.frente != null) {
            cad = "[";
            Nodo aux = this.frente;
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
    