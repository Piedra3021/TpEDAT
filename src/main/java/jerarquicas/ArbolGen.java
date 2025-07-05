package main.java.jerarquicas;

import main.java.lineales.dinamica.*;

public class ArbolGen {
    NodoGen raiz;

    public ArbolGen() {

        this.raiz = null;

    }

    public ArbolGen(NodoGen nodo) {
        this.raiz = nodo;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean resp = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            NodoGen padre = obtenerNodo(this.raiz, elemPadre);

            if (padre != null) {
                NodoGen nuevoNodo = new NodoGen(elemNuevo, null, null);
                NodoGen hijo = padre.getHijoIzquierdo();
                if (hijo == null) {
                    padre.setHijoIzquierdo(nuevoNodo);
                } else {
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(nuevoNodo);
                }
            }
        }

        return resp;
    }

    public NodoGen obtenerNodo(NodoGen nodo, Object elemPadre) {
        NodoGen nodoPadre = null;
        if (nodo != null) {
            if (nodo.getElem() == elemPadre) {
                nodoPadre = nodo;
            } else {
                nodo = nodo.getHijoIzquierdo();
                while (nodo != null && nodoPadre == null) {
                    nodoPadre = obtenerNodo(nodo, elemPadre);
                    nodo = nodo.getHermanoDerecho();
                }

            }

        }

        return nodoPadre;
    }

    public String toString() {

        return toStringAux(this.raiz);

    }

    private String toStringAux(NodoGen nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = cadena + nodo.getElem() + "--->";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena = cadena + hijo.getElem() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena = cadena + "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }

        }

        return cadena;
    }

    public boolean insertatPorPosicion(Object elemento, int pos) {
        boolean res = false;
        if (pos > 0) {
            int[] arreglo = new int[1];
            arreglo[0] = 1;
            res = insertarPorPosicionAux(this.raiz, elemento, arreglo, pos);

        }

        return res;
    }

    public boolean insertarPorPosicionAux(NodoGen nodo, Object elemento, int[] arreglo, int pos) {
        boolean resp = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemento, null, null);
        } else {
            NodoGen padre = obtenerNodoPorPos(this.raiz, pos, arreglo);

            if (padre != null) {
                NodoGen nuevoNodo = new NodoGen(elemento, null, null);
                NodoGen hijo = padre.getHijoIzquierdo();
                if (hijo == null) {
                    padre.setHijoIzquierdo(nuevoNodo);
                } else {
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(nuevoNodo);
                }
            }
        }

        return resp;
    }

    private NodoGen obtenerNodoPorPos(NodoGen nodo, int pos, int[] arreglo) {
        NodoGen nodoRes = null;
        if (nodo != null) {
            if (arreglo[0] == pos) {
                nodoRes = nodo;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && nodoRes == null) {
                    arreglo[0] = arreglo[0] + 1;
                    nodoRes = obtenerNodoPorPos(hijo, pos, arreglo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return nodoRes;
    }

    public boolean pertenece(Object elemento) {

        boolean res = perteneceAux(raiz, elemento);
        return res;
    }

    private boolean perteneceAux(NodoGen nodo, Object elemento) {
        boolean res = false;
        if (nodo != null) {

            if (nodo.getElem().equals(elemento)) {
                res = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();

                while (hijo != null && res == false) {
                    res = perteneceAux(hijo, elemento);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return res;
    }

    public Lista ancestros(Object elemento) {

        Lista resp = new Lista();
        ancestrosAux(this.raiz, elemento, resp);

        return resp;
    }

    private boolean ancestrosAux(NodoGen nodo, Object elemento, Lista ancestros) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem() == elemento) {
                exito = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();

                while (hijo != null && !exito) {
                    exito = ancestrosAux(hijo, elemento, ancestros);
                    hijo = hijo.getHermanoDerecho();
                }
                if (exito == true) {
                    ancestros.insertar(nodo.getElem(), 1);
                }
            }

        }
        return exito;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public int altura() {

        int res = alturaAux(this.raiz);

        return res;
    }

    private int alturaAux(NodoGen nodo) {
        // Variables para comparar las alturas
        int anterior = -1;
        int respuesta = anterior;
        if (nodo != null) {
            // Si no tiene hijo izquierdo, no tiene otros hijos que recorrer
            if (nodo.getHijoIzquierdo() == null) {
                respuesta = anterior + 1;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    // Anterior sera la altura, de los hijos en preorden, se suma a la vuelta
                    anterior = alturaAux(hijo) + 1;
                    // Si la altura del llamado realizado es menor que la anterior guardada, se
                    // cambia
                    if (anterior > respuesta) {
                        respuesta = anterior;
                    }
                    // avanzo
                    hijo = hijo.getHermanoDerecho();

                }
            }

        }
        return respuesta;
    }

    public Object padre(Object elemento) {

        Object retorno = padreAux(elemento, this.raiz);

        return retorno;
    }

    private Object padreAux(Object elemento, NodoGen nodo) {
        Object retorno = null;

        // Verifico que este evaluando en un nodo valido
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            // Desde el nodo padre, reviso los hijos
            if (hijo != null) {
                // Reviso todos los hijos del padre primero.
                while (hijo != null && retorno == null) {
                    if (hijo.getElem().equals(elemento)) {
                        retorno = nodo.getElem();
                    }
                    hijo = hijo.getHermanoDerecho();
                }

                // En caso de no haberlo conseguido, hago recorrido preorden
                if (retorno == null) {
                    hijo = nodo.getHijoIzquierdo();
                    while (hijo != null && retorno == null) {
                        retorno = padreAux(elemento, hijo);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }

        return retorno;
    }

    public int nivel(Object elem) {

        int res = nivelAux(this.raiz, elem, -1);

        return res;
    }

    private int nivelAux(NodoGen nodo, Object el, int alt) {
        int altura = -1;

        if (nodo != null) {
            // Si se consigue el elemento, se suma 1 a la altura.
            if (nodo.getElem().equals(el)) {
                altura = alt + 1;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && altura == -1) {
                    // Se aumenta unicamente una vez la altura, solamente cuando se baja y se llama
                    // recursivamente a todos los
                    // hermanos con el mismo valor.
                    altura = nivelAux(hijo, el, alt + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return altura;
    }

    public Lista listarPreorden() {
        Lista respuesta = new Lista();
        listaPreordenAux(respuesta, raiz);
        return respuesta;
    }

    private void listaPreordenAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {

            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listaPreordenAux(lista, hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarPosorden() {
        Lista respuesta = new Lista();
        listaPosordenAux(respuesta, raiz);
        return respuesta;
    }

    private void listaPosordenAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {

            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listaPosordenAux(lista, hijo);
                hijo = hijo.getHermanoDerecho();
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista respuesta = new Lista();
        listaInordenAux(respuesta, raiz);
        return respuesta;
    }

    private void listaInordenAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {

            NodoGen hijo = nodo.getHijoIzquierdo();
            // recorro los hijos izquierdos
            if (hijo != null) {
                listaInordenAux(lista, hijo);
            }
            // Inserto
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            // Empiezo a recorrer los hermanos derechos
            while (hijo != null) {
                hijo = hijo.getHermanoDerecho();
                listaInordenAux(lista, hijo);
            }
        }
    }

    public Lista listarPorNiveles() {
        Lista respuesta = new Lista();
        if (raiz != null) {
            // Inserto primero la raiz desde el codigo principal.
            respuesta.insertar(raiz.getElem(), respuesta.longitud() + 1);
            listarPorNivelesAux(respuesta, raiz);
        }
        return respuesta;
    }

    private void listarPorNivelesAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                // Desde el padre inserto todos los hijos.
                lista.insertar(hijo.getElem(), lista.longitud() + 1);
                hijo = hijo.getHermanoDerecho();
            }
            // Vuelvo al primero hijo de la izquierda para hacer recorrido inorden de los
            // nodos pero insertar por niveles.
            hijo = nodo.getHijoIzquierdo();

            while (hijo != null) {
                listarPorNivelesAux(lista, hijo);
                hijo = hijo.getHermanoDerecho();
            }

        }
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();

        clon = new ArbolGen(cloneAux(raiz, new NodoGen(raiz.getElem(), null, null)));

        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo, NodoGen nodoClon) {

        if (nodo != null) {

            if (nodo.getHijoIzquierdo() != null) {
                nodoClon.setHijoIzquierdo(new NodoGen(nodo.getHijoIzquierdo().getElem(), null, null));
                cloneAux(nodo.getHijoIzquierdo(), nodoClon.getHijoIzquierdo());
            }

            if (nodo.getHermanoDerecho() != null) {
                nodoClon.setHermanoDerecho(new NodoGen(nodo.getHermanoDerecho().getElem(), null, null));
                cloneAux(nodo.getHermanoDerecho(), nodoClon.getHermanoDerecho());
            }

        }

        return nodoClon;

    }

    public Lista listarPorNiveles2() {
        Lista salida = new Lista();
        Cola cola = new Cola();
        if (this.raiz != null) {
            cola.poner(this.raiz);
            while (!cola.esVacia()) {
                NodoGen nodo = (NodoGen) cola.obtenerFrente();
                cola.sacar();
                salida.insertar(nodo.getElem(), salida.longitud() + 1);
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    cola.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return salida;
    }

    public boolean verificarCamino(Lista lista) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = verificarCaminoAux(raiz, lista, lista.longitud(), 1);
        }
        return exito;
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lista, int longitud, int pos) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(lista.recuperar(pos))) {
                if (pos == longitud) {
                    // Si llego al ultimo elemento de la lista y es igual durante todo el recorrido,
                    // se retorna true.
                    exito = true;
                } else {
                    NodoGen hijo = nodo.getHijoIzquierdo();

                    while (hijo != null && exito == false) {
                        // NO VERIFICAR SOBRE NODOS,SOLO SOBRE HIJOS
                        exito = verificarCaminoAux(hijo, lista, longitud, pos + 1);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return exito;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista retorno = new Lista();
        listarEntreNivelesAux(raiz, retorno, niv1, niv2, 0, retorno.longitud());
        return retorno;
    }

    private int listarEntreNivelesAux(NodoGen nodo, Lista lista, int niv1, int niv2, int nivActual, int longitud) {

        if (nodo != null) {
            if (niv1 <= nivActual && nivActual <= niv2) {
                lista.insertar(nodo.getElem(), longitud + 1);
                longitud = longitud + 1;
            }

            if (nivActual < niv2) {
                NodoGen hijo = nodo.getHijoIzquierdo();

                while (hijo != null) {
                    longitud = listarEntreNivelesAux(hijo, lista, niv1, niv2, nivActual + 1, longitud);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return longitud;
    }

    private NodoGen buscarNodo(NodoGen nodo, Object elem) {
        NodoGen nodoRetorno = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nodoRetorno = nodo;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();

                while (hijo != null && nodoRetorno == null) {
                    nodoRetorno = buscarNodo(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nodoRetorno;
    }

    public boolean eliminar(Object elem) {
        boolean[] arr = new boolean[1];
        arr[0] = false;
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elem)) {
                this.raiz = null;
            } else {
                eliminarAparicionesAux(raiz, elem, arr);
            }
        }
        return arr[0];
    }

    public boolean eliminar2(Object elem) {
        boolean exito = false;
        NodoGen nElimnar = buscarNodo(raiz, elem);
        if (nElimnar != null) {
            exito = true;
            if (this.raiz == nElimnar) {
                this.raiz = null;
            } else {
                eliminarAux(this.raiz, nElimnar);
            }
        }

        return exito;

    }

    private boolean eliminarAux(NodoGen nodo, NodoGen nodoEliminar) {
        boolean exito = false;
        if (nodo != null) {
            System.out.println(nodo.getElem());

            NodoGen hijo = nodo.getHijoIzquierdo();

            if (hijo == nodoEliminar) {
                nodo.setHijoIzquierdo(hijo.getHermanoDerecho());
                exito = true;
            }
            if (hijo != null && exito == false) {
                NodoGen recorredor = hijo.getHermanoDerecho();
                while (recorredor != null) {
                    if (recorredor == nodoEliminar) {
                        hijo.setHermanoDerecho(recorredor.getHermanoDerecho());
                        exito = true;
                    }
                    recorredor = recorredor.getHermanoDerecho();
                    hijo = hijo.getHermanoDerecho();
                }
            }

            hijo = nodo.getHijoIzquierdo();
            while (hijo != null && exito == false) {
                exito = eliminarAux(hijo, nodoEliminar);
                hijo = hijo.getHermanoDerecho();
            }

        }
        return exito;
    }

    private void eliminarAparicionesAux(NodoGen nodo, Object elem, boolean[] exito) {
        if (nodo != null) {
            // me paro en su hijo
            NodoGen hijo = nodo.getHijoIzquierdo();
            if (hijo != null) {
                // Verifico los hijos izquierdos hasta que sean distintos del elem
                while (hijo != null && hijo.getElem().equals(elem)) {
                    nodo.setHijoIzquierdo(hijo.getHermanoDerecho());
                    hijo = nodo.getHijoIzquierdo();
                    exito[0] = true;
                }
                /*
                 * Verifico que hijo no sea nulo (por si se ejecuto el while)
                 */
                if (hijo != null) {
                    NodoGen anterior = hijo;
                    NodoGen recorredor = hijo.getHermanoDerecho();
                    while (recorredor != null) {
                        /*
                         * Si el hermano derecho es el elem a eliminar, al anterior se le asigna el
                         * hermano del recorredor
                         */
                        if (recorredor.getElem().equals(elem)) {
                            anterior.setHermanoDerecho(recorredor.getHermanoDerecho());
                            exito[0] = true;

                        } else {
                            anterior = recorredor;
                        }
                        recorredor = anterior.getHermanoDerecho();
                    }
                }
            }

            while (hijo != null) {

                eliminarAparicionesAux(hijo, elem, exito);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public boolean Jerarquizar(Object elem) {
        boolean exito = false;
        if (this.raiz != null) {
            if (!(this.raiz.equals(elem))) {
                exito = JerarquizarAux(this.raiz,elem,1);
            }
        }

        return exito;
    }

    private boolean JerarquizarAux(NodoGen nodo, Object elem, int nivel) {
        boolean exito = false;
        boolean encontrado = false;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            NodoGen anterior = hijo;

            while (hijo != null && !encontrado) {
                if (hijo.getElem().equals(elem)) {
                    encontrado = true;
                } else {
                    anterior = hijo;
                    hijo = hijo.getHermanoDerecho();
                }
            }
            if (encontrado && nivel > 1) {
                if (nodo.getHijoIzquierdo().getElem().equals(elem)) {
                    nodo.setHijoIzquierdo(hijo.getHermanoDerecho());
                } else {
                    anterior.setHermanoDerecho(hijo.getHermanoDerecho());
                }
                hijo.setHermanoDerecho(nodo.getHermanoDerecho());
                nodo.setHermanoDerecho(hijo);
                exito = true;
            } else {
                hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = JerarquizarAux(hijo, elem, nivel + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return exito;
    }
}
