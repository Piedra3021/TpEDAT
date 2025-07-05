package main.java.conjuntistas;

import main.java.jerarquicas.NodoArbol;
import main.java.lineales.dinamica.Lista;

public class ArbolABB {
    NodoABB raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elemento);
            exito = true;
        } else {
            exito = insertarAux(raiz, elemento);
        }
        return exito;
    }

    public boolean insertarAux(NodoABB nodo, Comparable elemento) {
        boolean exito = true;
        if ((elemento.compareTo(nodo.getElem())) == 0) {
            exito = false;
        } else if ((elemento.compareTo(nodo.getElem()) < 0)) {
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elemento);
            } else {
                nodo.setIzquierdo(new NodoABB(elemento));
            }
        } else {
            if (nodo.getDerecho() != null) {
                exito = insertarAux(nodo.getDerecho(), elemento);
            } else {
                nodo.setDerecho(new NodoABB(elemento));
            }
        }

        return exito;
    }

    public boolean eliminar(Comparable elemento) {

        boolean exito = false;

        return exito;
    }

    public boolean eliminarAux(Comparable elemento, NodoABB nodo) {
        boolean exito = false;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElem()) == 0) {
                if ((nodo.getIzquierdo() == null) && (nodo.getDerecho() == null)) {

                }
            }
        }
        return exito;
    }

    public boolean pertenece(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(raiz, elemento);
        }
        return exito;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elemento) {
        boolean exito = false;

        if ((elemento.compareTo(nodo.getElem()) == 0)) {
            exito = true;
        } else if ((elemento.compareTo(nodo.getElem())) < 0) {
            if (nodo.getIzquierdo() != null) {
                exito = perteneceAux(nodo.getIzquierdo(), elemento);
            }
        } else {
            if (nodo.getDerecho() != null) {
                exito = perteneceAux(nodo.getDerecho(), elemento);
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Lista listarRango(Comparable minimo, Comparable maximo) {
        Lista res = new Lista();

        listarRangoAux(this.raiz, minimo, maximo, res);

        return res;
    }

    private void listarRangoAux(NodoABB nodo, Comparable minimo, Comparable maximo, Lista lista) {
        if (nodo != null) {
            if ((minimo.compareTo(nodo.getElem()) <= 0) && (maximo.compareTo(nodo.getElem()) >= 0)) {
                listarRangoAux(nodo.getIzquierdo(), minimo, maximo, lista);
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                listarRangoAux(nodo.getDerecho(), minimo, maximo, lista);
            }
            if (minimo.compareTo(nodo.getElem()) > 0) {
                listarRangoAux(nodo.getDerecho(), minimo, maximo, lista);
            } else if (maximo.compareTo(nodo.getElem()) < 0) {
                listarRangoAux(nodo.getIzquierdo(), minimo, maximo, lista);
            }
        }
    }

    public Lista listar() {
        Lista res = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, res);
        }
        return res;
    }

    private void listarAux(NodoABB nodo, Lista lista) {
        if (nodo != null) {

            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }

    }

    public Object minimo() {
        Object resultado = null;

        if (this.raiz != null) {
            resultado = this.raiz.getElem();
            NodoABB recorredor = this.raiz.getIzquierdo();
            while (recorredor != null) {
                resultado = recorredor.getElem();
                recorredor = recorredor.getIzquierdo();
            }
        }
        return resultado;
    }

    public Object maximo() {
        Object resultado = null;

        if (this.raiz != null) {
            resultado = this.raiz.getElem();
            NodoABB recorredor = this.raiz.getDerecho();
            while (recorredor != null) {
                resultado = recorredor.getElem();
                recorredor = recorredor.getDerecho();
            }
        }
        return resultado;
    }

    public String toString() {
        String cad = "";
        if (this.raiz != null) {
            cad = cad + "raiz " + this.raiz.getElem() + "\n";
            cad = toStringAux(this.raiz, cad);
        }
        return cad;
    }

    private String toStringAux(NodoABB nodo, String cad) {

        if (nodo != null) {

            if (nodo.getIzquierdo() != null) {
                cad = cad + nodo.getIzquierdo().getElem() + " HI - Padre: " + nodo.getElem() + "\n";
                cad = toStringAux(nodo.getIzquierdo(), cad);
            }

            if (nodo.getDerecho() != null) {
                cad = cad + nodo.getDerecho().getElem() + " HD - Padre: " + nodo.getElem() + "\n";
                cad = toStringAux(nodo.getDerecho(), cad);
            }
        }

        return cad;
    }

    public boolean eliminarMinimo() {
        boolean exito = false;
        exito = eliminarMinimoAux(this.raiz);
        return exito;
    }

    private boolean eliminarMinimoAux(NodoABB nodo) {
        boolean exito = true;
        if (nodo != null) {
            NodoABB hijo = nodo.getIzquierdo();
            if (nodo == this.raiz && hijo == null) {
                this.raiz = nodo.getDerecho();
            } else {
                if (hijo != null) {

                    NodoABB recorredor = hijo.getIzquierdo();

                    if (recorredor == null) {
                        nodo.setIzquierdo(hijo.getDerecho());
                    } else {
                        while (recorredor != null) {
                            if (recorredor.getIzquierdo() == null) {
                                hijo.setIzquierdo(recorredor.getDerecho());
                            }
                            hijo = recorredor;
                            recorredor = recorredor.getIzquierdo();
                        }
                    }
                }
            }
        }
        return exito;
    }

    private NodoABB buscarNodo(NodoABB nodo, Comparable elem) {
        NodoABB retorno = null;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                retorno = nodo;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                retorno = buscarNodo(nodo.getIzquierdo(), elem);
            } else {
                retorno = buscarNodo(nodo.getDerecho(), elem);
            }
        }

        return retorno;
    }

    public ArbolABB cloneParteInv(Object elem) {
        ArbolABB clonado = new ArbolABB();
        NodoABB parteClon = buscarNodo(this.raiz, (Comparable) elem);
        if (parteClon != null) {
            clonado.raiz = cloneParteInvAux(parteClon, new NodoABB(parteClon.getElem()));
        }
        return clonado;
    }

    private NodoABB cloneParteInvAux(NodoABB nodo, NodoABB clon) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                clon.setDerecho(new NodoABB(nodo.getIzquierdo().getElem()));
                cloneParteInvAux(nodo.getIzquierdo(), clon.getDerecho());
            }
            if (nodo.getDerecho() != null) {
                clon.setIzquierdo(new NodoABB(nodo.getDerecho().getElem()));
                cloneParteInvAux(nodo.getDerecho(), clon.getIzquierdo());
            }
        }
        return clon;
    }

    public Object mejorCandidato(Comparable elem) {
        Object candidato = -1;
        if (this.raiz != null) {

            candidato = mejorCandidatoAux(this.raiz, elem);

        }
        return candidato;
    }

    private Object mejorCandidatoAux(NodoABB nodo, Comparable elem) {
        Object candidato = -1;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                int elemNodo = (int) elem;
                int minimo = 0;
                int maximo = 0;
                NodoABB recorredor;
                NodoABB HDer = nodo.getDerecho();
                NodoABB HIzq = nodo.getIzquierdo();

                if (HIzq != null) {
                    recorredor = HIzq.getDerecho();
                    while (recorredor != null) {
                        HIzq = recorredor;
                        recorredor = recorredor.getDerecho();
                    }
                    minimo = (int) HIzq.getElem();
                }

                if (HDer != null) {
                    recorredor = HDer.getIzquierdo();
                    while (recorredor != null) {
                        HDer = recorredor;
                        recorredor = recorredor.getIzquierdo();
                    }
                    maximo = (int) HDer.getElem();
                }

                minimo =  Math.abs(elemNodo - minimo);
                maximo = Math.abs(maximo - elemNodo);

                if (minimo < maximo) {
                    candidato = HIzq.getElem();
                } else if(HIzq == null && HDer == null) {
                    candidato = -1;
                }else{
                    candidato = HDer.getElem();
                }
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                candidato = mejorCandidatoAux(nodo.getIzquierdo(), elem);
            } else {
                candidato = mejorCandidatoAux(nodo.getDerecho(), elem);
            }
        }
        return candidato;
    }

}
