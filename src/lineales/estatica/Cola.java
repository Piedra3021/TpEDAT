/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.lineales.estatica;

/**
 *
 * @author pedro.beltran
 */
public class Cola {

    private final int TAMANIO = 20;
    private Object[] arreglo;
    private int frente;
    private int fin;

    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;

    }

    public boolean poner(Object elem) {
        boolean res = false;
        if ((this.fin + 1) % this.TAMANIO != this.frente) {//Verificamos que no este llena.
            //Se asigna el elem al arreglo
            this.arreglo[fin] = elem;
            this.fin = (this.fin + 1) % TAMANIO;
            res = true;
        }
        return res;
    }

    public boolean sacar() {
        boolean res = false;
        if (!esVacia()) {//Verificamos que haya al menos un elemento.
            this.arreglo[frente] = null;//Se elimina el elemento y se cambia el frente.
            this.frente = (this.frente + 1) % TAMANIO;
            res = true;
        }
        return res;
    }

    public Object obtenerFrente() {
        return this.arreglo[frente];
    }

    public boolean esVacia() {
        return this.fin == this.frente;
    }

    public void vaciar() {
        while (this.frente != this.fin) {//Se va vaciando posicion a posicion desde el frente hasta el fin.
            this.arreglo[frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
    }

    public Cola clone() {
        Cola copia = new Cola();
        int i = this.frente;//Empiezo desde el frente
        if (!esVacia()) {//Verifico que no es vacia
            while (this.arreglo[i] != null) {//Mientras la posicion no sea nula, recorro
                copia.arreglo[i] = this.arreglo[i];
                i = (i + 1) % TAMANIO;
            }
            copia.frente = this.frente;
            copia.fin = this.fin;
        }

        return copia;
    }
    
    
    public Cola clone2(){
        
        Cola copia = new Cola();
        copia.arreglo = this.arreglo.clone();
        copia.frente = this.frente;
        copia.fin = this.fin;
        
        return copia;
    }

    public String toString() {
        String cad = "[]";
        int i = this.frente;
        if (!esVacia()) {
            cad = "[";
            while (i != this.fin) {
                cad = cad + this.arreglo[i];
                i = (i + 1) % TAMANIO;
                if(i!=this.fin){
                    cad = cad+",";
                }
            }
            cad = cad+"]";
        }

        return cad;
    }

}
