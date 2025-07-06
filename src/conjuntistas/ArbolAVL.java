package conjuntistas;

import lineales.dinamica.*;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        boolean resultado = true;
        if (obtenerNodo(raiz, elemento) == null) resultado = false;
        return resultado;
    }

    private NodoAVL obtenerNodo(NodoAVL nodo, Comparable elemento) {
        NodoAVL nodoBuscado = null;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElem()) < 0) {
                nodoBuscado = obtenerNodo(nodo.getIzquierdo(), elemento);
            } else if (elemento.compareTo(nodo.getElem()) > 0) {
                nodoBuscado = obtenerNodo(nodo.getDerecho(), elemento);
            } else {
                nodoBuscado = nodo;
            }
        }
        return nodoBuscado;
    }


    public boolean insertar(Comparable elemento) {
        boolean[] resultado = new boolean[1];
        resultado[0] = false;
        if (raiz != null) {
            raiz = insertarAux(raiz, elemento, resultado);
        } else {
            raiz = new NodoAVL(elemento, null, null);
            raiz.recalcularAltura();
            resultado[0] = true;
        }
        return resultado[0];
    }

    private NodoAVL insertarAux(NodoAVL nodo, Comparable elem, boolean[] resultado) {
        NodoAVL aux = nodo; 
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) < 0) {
                nodo.setIzquierdo(insertarAux(nodo.getIzquierdo(), elem, resultado));
            } else if (elem.compareTo(nodo.getElem()) > 0) {
                nodo.setDerecho(insertarAux(nodo.getDerecho(), elem, resultado));
            }

            if (resultado[0]) {
                aux = balancear(nodo);
                aux.recalcularAltura();
            }
        } else {
            //Caso en el que el nodo es null, se crea un nuevo nodo
            aux = new NodoAVL(elem, null, null);
            aux.recalcularAltura();
            resultado[0] = true;
        }
        return aux;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        int balance;
        balance = calcularBalance(nodo);
        if (balance < -1) {
            if (calcularBalance(nodo.getDerecho()) <= 0) {
                //Rotacion simple a izquierda
                nodo = rotarIzquierda(nodo);
            } else {
                //Rotacion doble a izquierda
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                nodo.getDerecho().recalcularAltura();
                nodo = rotarIzquierda(nodo);
            }
        } else if (balance > 1) {
            if (calcularBalance(nodo.getIzquierdo()) >= 0) {
                //Rotacion simple a derecha
                nodo = rotarDerecha(nodo);
            } else {
                //Rotacion doble a derecha
                nodo.setIzquierdo(nodo.getIzquierdo());
                nodo.getIzquierdo().recalcularAltura();
                nodo = rotarDerecha(nodo);
            }
        }
        return nodo;
    }

    private int calcularBalance(NodoAVL nodo) {
        int altIzq = -1, altDer = -1;
        if (nodo.getIzquierdo() != null) altIzq = nodo.getIzquierdo().getAltura();
        if (nodo.getDerecho() != null) altDer = nodo.getDerecho().getAltura();
        return altIzq - altDer;
    }

    private NodoAVL rotarIzquierda(NodoAVL pivot) {
        NodoAVL y = pivot.getDerecho();
        pivot.setDerecho(y.getIzquierdo());
        y.setIzquierdo(pivot);
        pivot.recalcularAltura();
        y.recalcularAltura();
        return y;
    }

    private NodoAVL rotarDerecha(NodoAVL pivot) {
        NodoAVL y = pivot.getIzquierdo();
        pivot.setIzquierdo(y.getDerecho());
        y.setDerecho(pivot);
        pivot.recalcularAltura();
        y.recalcularAltura();
        return y;
    }


    public boolean eliminar(Comparable elemento) {
        boolean[] eliminado = new boolean[1]; // Flag para saber si se eliminó
        raiz = eliminarAux(raiz, elemento, eliminado);
        return eliminado[0];
    }

    // Devuelve el nuevo subárbol raíz tras la eliminación
    private NodoAVL eliminarAux(NodoAVL nodo, Comparable elem, boolean[] eliminado) {
        NodoAVL aux = null;
        if (nodo == null) {
            eliminado[0] = false;
        } else {
            int cmp = elem.compareTo(nodo.getElem());
            if (cmp < 0) {
                nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elem, eliminado));
                nodo.recalcularAltura();
                nodo = balancear(nodo);
                aux = nodo;
            } else if (cmp > 0) {
                nodo.setDerecho(eliminarAux(nodo.getDerecho(), elem, eliminado));
                nodo.recalcularAltura();
                nodo = balancear(nodo);
                aux = nodo;
            } else {
                // Nodo encontrado
                eliminado[0] = true;
                // Caso 1: solo un hijo o ninguno
                if (nodo.getIzquierdo() == null) {
                    aux = nodo.getDerecho();
                } else if (nodo.getDerecho() == null) {
                    aux = nodo.getIzquierdo();
                } else {
                    // Caso 2: dos hijos
                    // Buscar el menor del subárbol derecho
                    NodoAVL sucesor = nodo.getDerecho();
                    while (sucesor.getIzquierdo() != null) {
                        sucesor = sucesor.getIzquierdo();
                    }
                    // Reemplazar valor y eliminar sucesor recursivamente
                    nodo.setElem(sucesor.getElem());
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), sucesor.getElem(), eliminado));
                    nodo.recalcularAltura();
                    nodo = balancear(nodo);
                    aux = nodo;
                }
            }
        }
        return aux;
    }


    public Lista listar() {
        //Lista en inorden
        Lista salida = new Lista();
        listarInorden(raiz, salida);
        return salida;
    }

    private void listarInorden(NodoAVL nodo, Lista salida) {
        if (nodo != null) {
            //Se visita invertido para no obtener la longitud de la lista para cada elemento.
            listarInorden(nodo.getDerecho(), salida);

            salida.insertar(nodo.getElem(), 1);

            listarInorden(nodo.getIzquierdo(), salida);
        }
    }


    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        Lista lista = new Lista();
        if (elemMaximo.compareTo(elemMinimo) >= 0) {
            listarRangoAux(elemMinimo, elemMaximo, raiz, lista);
        }
        return lista;
    }

    private void listarRangoAux(Comparable min, Comparable max, NodoAVL nodo, Lista lista) {
        Comparable elem;
        boolean mayorQueMin, menorQueMax;
        if (nodo != null) {
            elem = nodo.getElem();
            mayorQueMin = elem.compareTo(min) >= 0;
            menorQueMax = elem.compareTo(max) <= 0;
            if (mayorQueMin && menorQueMax) {
                //Esta en rango y lo listamos
                //Si el elemento del nodo es igual a alguno de los limites del rango,
                //no es necesario visitar todos sus hijos.
                if (!elem.equals(max)) listarRangoAux(min, max, nodo.getDerecho(), lista);
                lista.insertar(elem, 1);
                if (!elem.equals(min)) listarRangoAux(min, max, nodo.getIzquierdo(), lista);
            } else if (mayorQueMin) {
                listarRangoAux(min, max, nodo.getIzquierdo(), lista);
            } else {
                listarRangoAux(min, max, nodo.getDerecho(), lista);
            }
        }
    }


    public Comparable minimoElem() {
        NodoAVL nodo;
        Comparable elem = null;
        if (raiz != null) {
            nodo = raiz;
            while (nodo.getIzquierdo() != null) nodo = nodo.getIzquierdo();
            elem = nodo.getElem();
        }
        return elem;
    }

    public Comparable maximoElem() {
        NodoAVL nodo;
        Comparable elem = null;
        if (raiz != null) {
            nodo = raiz;
            while (nodo.getDerecho() != null) nodo = nodo.getDerecho();
            elem = nodo.getElem();
        }
        return elem;
    }

    public ArbolAVL clone() {
        ArbolAVL arbolClon = new ArbolAVL();
        if (!this.esVacio()){
            arbolClon.raiz = new NodoAVL(raiz.getElem(), null, null);
            cloneAux(raiz, arbolClon.raiz);
        }
        
        return arbolClon;
    }
        
    private void cloneAux(NodoAVL nodoOr, NodoAVL nodoCl) {
        NodoAVL nodo1 = null, nodo2 = null;
        if (nodoOr != null) {
            if (nodoOr.getDerecho() != null) {
                nodo2 = new NodoAVL(nodoOr.getDerecho().getElem(), null, null);
                nodoCl.setDerecho(nodo2);
                cloneAux(nodoOr.getDerecho(), nodo2);
            }
            if (nodoOr.getIzquierdo() != null) {
                nodo1 = new NodoAVL(nodoOr.getIzquierdo().getElem(), null, null);
                nodoCl.setIzquierdo(nodo1);
                cloneAux(nodoOr.getIzquierdo(), nodo1);
            }
        }
    }


    public boolean esVacio() {
        return raiz == null;
    }

    public void vaciar() {
        raiz = null;
    }

    public String toString() {
        //in-orden
        String[] arr = {""};
        return obtenerElems(arr, raiz);
    }

    private String obtenerElems(String[] text, NodoAVL nodo) {
        if (nodo != null) {
            obtenerElems(text, nodo.getIzquierdo());
            text[0] = text[0] + " " + nodo.getElem();
            obtenerElems(text, nodo.getDerecho());
        }
        return text[0];
    }


    public void dibujar() {
        if (raiz != null) raiz.dibujar("");
        System.out.println();
    }
}
