package main.java.jerarquicas;

import main.java.lineales.dinamica.*;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }

    public ArbolBin(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char posHijo) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoArbol(elemNuevo);
        } else {
            NodoArbol nodoPadre = raiz;

            nodoPadre = obtenerNodo(this.raiz, elemPadre);

            if (nodoPadre != null) {
                if (posHijo == 'I' && nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoArbol(elemNuevo));
                } else if (posHijo == 'D' && nodoPadre.getHijoDerecho() == null) {
                    nodoPadre.setHijoDerecho(new NodoArbol(elemNuevo));
                } else {
                    res = false;
                }
            } else {
                res = false;
            }
        }

        return res;
    }

    private NodoArbol obtenerNodo(NodoArbol actual, Object padre) {
        NodoArbol nodoEncontrado = new NodoArbol(null);

        if (actual != null) {
            if (actual.getElemento().equals(padre)) {
                nodoEncontrado = actual;
            } else {
                nodoEncontrado = obtenerNodo(actual.getHijoIzquierdo(), padre);
                if (nodoEncontrado.getElemento() == null) {
                    nodoEncontrado = obtenerNodo(actual.getHijoDerecho(), padre);
                }
            }
        }

        return nodoEncontrado;
    }

    public Lista ListaPreorden() {
        Lista res = new Lista();
        if (this.raiz != null) {
            listaPreordenAux(this.raiz, res);
        }
        return res;
    }

    private void listaPreordenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);

            listaPreordenAux(nodo.getHijoIzquierdo(), lista);
            listaPreordenAux(nodo.getHijoDerecho(), lista);
        }

    }

    public boolean esVacia() {
        return this.raiz == null;
    }

    public Object padre(Object elem) {
        Object res = null;
        if (this.raiz != null) {
            res = padreAux(this.raiz, elem);
        }

        return res;
    }

    private Object padreAux(NodoArbol nodoPadre, Object elem) {
        Object resp = null;
        if (nodoPadre != null) {
            if (nodoPadre.getElemento().equals(elem)) {
                resp = nodoPadre.getElemento();
            } else {

                if (nodoPadre.getHijoIzquierdo() != null) {
                    if (nodoPadre.getHijoIzquierdo().getElemento().equals(elem)) {
                        resp = nodoPadre.getElemento();
                    } else {
                        resp = padreAux(nodoPadre.getHijoIzquierdo(), elem);
                    }
                }

                if (resp == null) {
                    if (nodoPadre.getHijoDerecho() != null) {
                        if (nodoPadre.getHijoDerecho().getElemento().equals(elem)) {
                            resp = nodoPadre.getElemento();
                        } else {
                            resp = padreAux(nodoPadre.getHijoDerecho(), elem);
                        }
                    }
                }
            }
        }
        return resp;
    }

    public int altura() {

        int res = alturaAux(this.raiz);

        return res;
    }

    private int alturaAux(NodoArbol nodo) {
        int alturaI = -1;
        int alturaD = -1;
        if (nodo != null) {
            alturaI = 1 + alturaAux(nodo.getHijoIzquierdo());
            alturaD = 1 + alturaAux(nodo.getHijoDerecho());

            if (alturaD > alturaI) {
                alturaI = alturaD;
            }
        }
        return alturaI;
    }

    public int nivel(Object elem) {

        int res = nivelAux(this.raiz, elem, -1);

        return res;
    }

    private int nivelAux(NodoArbol nodo, Object el, int alt) {
        int altura = -1;
        alt++;

        if (nodo != null) {
            if (nodo.getElemento().equals(el)) {
                altura = alt;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    altura = nivelAux(nodo.getHijoIzquierdo(), el, alt);
                }
                if (altura == -1) {
                    if (nodo.getHijoDerecho() != null) {
                        altura = nivelAux(nodo.getHijoDerecho(), el, alt);
                    }

                }

            }
        }

        return altura;
    }

    public boolean verificarPatron(Lista patron) {

        boolean res = patronAux(this.raiz, patron, 1);

        return res;

    }

    private boolean patronAux(NodoArbol nodo, Lista patron, int pos) {
        boolean res = false;
        // Verifica que el nodo no sea nulo y que la posicion este dentro de la lista
        if (nodo != null && pos < patron.longitud() + 1) {

            // Verifica que el elemento es igual y sea hoja
            if (nodo.getElemento().equals(patron.recuperar(pos)) && nodo.getHijoIzquierdo() == null
                    && nodo.getHijoDerecho() == null) {
                // Verifica que si es hoja es, tambien, el ultimo de la lista
                if (pos == patron.longitud()) {
                    res = true;
                }
                // Si es igual y no es hoja, verifica que siga con el patron
            } else if (nodo.getElemento().equals(patron.recuperar(pos))) {
                if (nodo.getHijoIzquierdo() != null) {
                    res = patronAux(nodo.getHijoIzquierdo(), patron, pos + 1);
                }
                // Si el camino del hijo izquierdo es falso, verifica el derecho
                if (res == false) {
                    if (nodo.getHijoDerecho() != null) {
                        res = patronAux(nodo.getHijoDerecho(), patron, pos + 1);
                    }
                }
            }
        }
        return res;
    }

    public Lista frontera() {
        Lista resultado = new Lista();

        fronteraAux(this.raiz, resultado);

        return resultado;
    }

    private void fronteraAux(NodoArbol nodo, Lista resultadoLista) {

        if (nodo != null) {
            // Verifico si es hoja, si lo es, lo inserto al final de la lista
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                resultadoLista.insertar(nodo.getElemento(), resultadoLista.longitud() + 1);
            } else {
                // Hago recorrido preorden
                fronteraAux(nodo.getHijoIzquierdo(), resultadoLista);
                fronteraAux(nodo.getHijoDerecho(), resultadoLista);
            }
        }

    }

    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin(cloneAux(this.raiz, new NodoArbol(this.raiz.getElemento())));

        return clon;

    }

    private NodoArbol cloneAux(NodoArbol nodo, NodoArbol nodoClon) {

        if (nodo != null) {

            // Verifico los hijos izquierdos
            if (nodo.getHijoIzquierdo() != null) {
                // Le setteo el hijo izquierdo
                nodoClon.setHijoIzquierdo(new NodoArbol(nodo.getHijoIzquierdo().getElemento()));
                // Hago llamada recursiva con el hijo
                cloneAux(nodo.getHijoIzquierdo(), nodoClon.getHijoIzquierdo());
            }
            // Lo mismo pero con el derecho
            if (nodo.getHijoDerecho() != null) {
                nodoClon.setHijoDerecho(new NodoArbol(nodo.getHijoDerecho().getElemento()));
                cloneAux(nodo.getHijoDerecho(), nodoClon.getHijoDerecho());
            }

        }

        return nodoClon;

    }

    public ArbolBin cloneInvertido() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            NodoArbol raizC = new NodoArbol(this.raiz.getElemento());
            clon = new ArbolBin(cloneInvAux(this.raiz, raizC));
        }
        return clon;

    }

    private NodoArbol cloneInvAux(NodoArbol nodo, NodoArbol nodoClon) {

        if (nodo != null) {

            // Verifico los hijos izquierdos
            if (nodo.getHijoIzquierdo() != null) {
                // Le setteo el hijo izquierdo como el derecho
                nodoClon.setHijoDerecho(new NodoArbol(nodo.getHijoIzquierdo().getElemento()));
                // Hago llamada recursiva con el hijo
                cloneInvAux(nodo.getHijoIzquierdo(), nodoClon.getHijoDerecho());
            }
            // Lo mismo pero con el derecho/izquierdo
            if (nodo.getHijoDerecho() != null) {
                nodoClon.setHijoIzquierdo(new NodoArbol(nodo.getHijoDerecho().getElemento()));
                cloneInvAux(nodo.getHijoDerecho(), nodoClon.getHijoIzquierdo());
            }

        }

        return nodoClon;

    }

    public String toString() {
        String cad = "";
        if (this.raiz != null) {
            cad = cad + "raiz " + this.raiz.getElemento() + "\n";
            cad = toStringAux(this.raiz, cad);
        }
        return cad;
    }

    private String toStringAux(NodoArbol nodo, String cad) {

        if (nodo != null) {

            if (nodo.getHijoIzquierdo() != null) {
                cad = cad + nodo.getHijoIzquierdo().getElemento() + " HI - Padre: " + nodo.getElemento() + "\n";
                cad = toStringAux(nodo.getHijoIzquierdo(), cad);
            }

            if (nodo.getHijoDerecho() != null) {
                cad = cad + nodo.getHijoDerecho().getElemento() + " HD - Padre: " + nodo.getElemento() + "\n";
                cad = toStringAux(nodo.getHijoDerecho(), cad);
            }
        }

        return cad;
    }
}
