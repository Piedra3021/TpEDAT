package main.java.lineales.dinamica;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedro.beltran
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        boolean res;
        // Pos tiene que ser mayor que 0 y menor que longitud+1.
        if (pos < 1 || pos > (this.longitud + 1)) {
            res = false;
        } else {
            if (pos == 1) {
                // Inserta en la posicion 1, en caso de haber ya un elem se lo settea como
                // enlace.
                this.cabecera = new Nodo(elem, this.cabecera);
                this.longitud++;
            } else {
                Nodo recorredor = this.cabecera;
                int i = 1;
                // Recorre hasta uno menos a la posicion a insertar.
                while (i < pos - 1) {
                    recorredor = recorredor.getEnlace();
                    i++;
                }

                // Crea un nuevo nodo, en caso de que haya un elemento, se lo settea como
                // enlace.
                recorredor.setEnlace(new Nodo(elem, recorredor.getEnlace()));
                this.longitud++;
            }
            res = true;
        }

        return res;
    }

    public boolean eliminar(int pos) {
        boolean res = false;

        if (pos >= 1 && pos <= this.longitud) {// verifica que la posicion sea valida
            if (pos == 1) {// Pos 1 caso especial. se tiene que settear el enlace del tope.
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
                res = true;
            } else {
                int i = 1;
                Nodo recorredor = this.cabecera;
                while (i < pos - 1) {// recorre hasta uno antes de la posicion a eliminar
                    recorredor = recorredor.getEnlace();
                    i++;
                }
                // Se le pone el enlace del elemento a eliminar.
                recorredor.setEnlace(recorredor.getEnlace().getEnlace());
                this.longitud--;
                res = true;
            }
        }
        return res;
    }

    public int longitud() {
        return this.longitud;
    }

    public Object recuperar(int pos) {
        Object elemento = null;

        if (pos >= 1 && pos <= this.longitud) {// Verifica pos valida.
            Nodo recorredor = this.cabecera;
            int i = 1;
            while (i < pos) {// recorre hasta el elemento
                recorredor = recorredor.getEnlace();
                i++;
            }
            elemento = recorredor.getElemento();
        }
        return elemento;
    }

    public int localizar(Object elem) {
        int pos = -1;
        int contador = 0;
        boolean bandera = false;

        if (this.cabecera != null) {// Verifico que no sea vacia
            Nodo recorredor = this.cabecera;
            // Se hace un while mientras estemos dentro de la lista y no se encuentre el
            // elemento.
            while (recorredor != null && !bandera) {
                contador++;
                if (recorredor.getElemento().equals(elem)) {
                    bandera = true;
                    pos = contador;
                }
                recorredor = recorredor.getEnlace();
            }
        }

        return pos;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public Lista clone() {

        Lista copia = new Lista();
        // Verifico que exista una lista
        if (this.cabecera != null) {
            Nodo recorredor, actual;
            // Nodo que va a recorrer la lista original
            recorredor = this.cabecera.getEnlace();
            // Nodo de la lista clon
            actual = new Nodo(this.cabecera.getElemento());
            // La cabecera de la copia es el ultimo nodo creado, de aqui se engancha todo
            copia.cabecera = actual;
            while (recorredor != null) {
                // Voy setteando los enlaces
                actual.setEnlace(new Nodo(recorredor.getElemento()));
                // se mueven en sus listas
                actual = actual.getEnlace();
                recorredor = recorredor.getEnlace();
            }
            copia.longitud = this.longitud;
        }

        return copia;
    }

    public String toString() {
        String cad = "[]";
        if (this.cabecera != null) {
            cad = "[";
            Nodo aux = this.cabecera;
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

    public Lista obtenerMultiplos(int n) {
        Lista res = new Lista();
        int numActual = 1;

        if (n <= this.longitud) {
            //Nodos con los que recorro, recorredor en la original, actual en la copia
            Nodo recorredor = this.cabecera;
            Nodo actual = null;
            //Trabajo con un while para no recorrer de mas
            while (recorredor != null) {
                //Si la posicion es multipli de n, entonces posicion%n = 0
                if (numActual % n == 0) {
                    //Verificamos si se inserta en cabecera o no
                    if (res.cabecera != null) {
                        //Se settea el enlace en el que estoy, y me muevo al nuevo nodo
                        actual.setEnlace(new Nodo(recorredor.getElemento()));
                        actual = actual.getEnlace();
                    } else {
                        res.cabecera = new Nodo(recorredor.getElemento());
                        actual = res.cabecera;
                    }
                }
                //Me muevo en la posicion
                recorredor = recorredor.getEnlace();
                numActual++;
            }
        }

        return res;
    }

    public void eliminarApariciones(Object x) {
        if (!this.esVacia()) {
            Nodo recorredor = this.cabecera.getEnlace();
            Nodo anterior = this.cabecera;
            boolean avanzo;
            while (recorredor != null) {
                avanzo = true;
                if (this.cabecera.getElemento().equals(x)) {
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    if (recorredor.getElemento().equals(x)) {
                        anterior.setEnlace(recorredor.getEnlace());
                        avanzo = false;
                    }
                }
                recorredor = recorredor.getEnlace();
                if (avanzo) {
                    anterior = anterior.getEnlace();
                }
            }

            if (this.cabecera != null) {
                if (this.cabecera.getElemento().equals(x)) {
                    this.cabecera = null;
                }
            }

        }
    }

}
