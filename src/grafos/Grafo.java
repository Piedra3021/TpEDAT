package grafos;

public class Grafo {

    NodoVert inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio, null);
            exito = true;
        }
        return exito;

    }

    public boolean eliminarVertice(Object verticeElim){
        boolean exito = false;
        NodoVert actual = this.inicio;
        NodoVert anterior = null;

    // Buscar el vértice a eliminar
    while (actual != null && !actual.getElem().equals(verticeElim)) {
        anterior = actual;
        actual = actual.getSigVertice();
    }

    if (actual != null) {
        // Eliminar todos los arcos que apuntan al vértice a eliminar
        NodoVert recorredor = this.inicio;
        while (recorredor != null) {
            eliminarArcoAux(recorredor, verticeElim);
            recorredor = recorredor.getSigVertice();
        }

        // Eliminar el vértice de la lista de vértices
        if (anterior == null) {
            this.inicio = actual.getSigVertice();
        } else {
            anterior.setSigVertice(actual.getSigVertice());
        }
        exito = true;
    }

        return exito;
    }

    public boolean existeVertice(Object buscado) {
        boolean existe = false;
        NodoVert vertices = this.inicio;

        while (vertices != null && existe == false) {
            if (vertices.getElem().equals(buscado)) {
                existe = true;
            } else {
                vertices = vertices.getSigVertice();
            }
        }

        return existe;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarArco(Object origen, Object destino, double etiqueta) {
        boolean exito = false;
        if (this.inicio != null) {
            NodoVert nodoOrigen = ubicarVertice(origen);
            NodoVert nodoDestino = ubicarVertice(destino);

            if (nodoOrigen != null & nodoDestino != null) {
                NodoAdy recorredorAd, anteriorAd;
                NodoAdy nuevoArco = new NodoAdy(nodoDestino, null, etiqueta);
                if (nodoOrigen.getPrimerAdy() == null) {
                    nodoOrigen.setPrimerAdy(nuevoArco);
                } else {
                    anteriorAd = nodoOrigen.getPrimerAdy();
                    recorredorAd = anteriorAd.getSigAdyacente();

                    while (recorredorAd != null) {
                        anteriorAd = recorredorAd;
                        recorredorAd = recorredorAd.getSigAdyacente();
                    }

                    anteriorAd.setSigAdyacente(nuevoArco);
                }
                exito = true;
            }

        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {

        boolean exito = false;
        NodoVert nodoOr = ubicarVertice(origen);

        if (nodoOr != null) {
            exito = eliminarArcoAux(nodoOr, destino);
        }

        return exito;
    }

    private boolean eliminarArcoAux(NodoVert origen, Object buscado) {

        boolean exito = false;
        NodoAdy adyacentes = origen.getPrimerAdy();
        NodoAdy recorredor;

        if (adyacentes != null) {
            Object elemento = adyacentes.getVertice().getElem();
            if (elemento.equals(buscado)) {
                origen.setPrimerAdy(adyacentes.getSigAdyacente());
                exito = true;
            } else {
                recorredor = adyacentes.getSigAdyacente();
                while (recorredor != null && !exito) {
                    if (recorredor.getVertice().getElem().equals(buscado)) {
                        adyacentes.setSigAdyacente(recorredor.getSigAdyacente());
                        exito = true;
                    }
                    adyacentes = recorredor;
                    recorredor = recorredor.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            NodoAdy adyacente = nodoOrigen.getPrimerAdy();
            while (adyacente != null) {
                if (adyacente.getVertice().getElem().equals(destino)) {
                    exito = true;
                }
                adyacente = adyacente.getSigAdyacente();
            }
        }
        return exito;
    }

    

    public String toString() {
        String cadena = "";

        if (this.inicio != null) {
            NodoVert recorredor = this.inicio;

            while (recorredor != null) {
                cadena = cadena + recorredor.getElem() + "-->";
                NodoAdy recorredorAd = recorredor.getPrimerAdy();
                while (recorredorAd != null) {
                    cadena = cadena + "(" + recorredorAd.getVertice().getElem() + " " + recorredorAd.getEtiqueta()
                            + ") ";
                    recorredorAd = recorredorAd.getSigAdyacente();
                }
                cadena = cadena + "\n";
                recorredor = recorredor.getSigVertice();
            }
        }
        return cadena;
    }

    public String dibujarGrafo() {
    StringBuilder sb = new StringBuilder();

    NodoVert recorredor = this.inicio;
    while (recorredor != null) {
        sb.append(recorredor.getElem()).append("\n");
        NodoAdy recorredorAd = recorredor.getPrimerAdy();
        if (recorredorAd != null) {
            while (recorredorAd != null) {
                sb.append(" ├── ")
                  .append(recorredorAd.getVertice().getElem())
                  .append(" (")
                  .append(recorredorAd.getEtiqueta())
                  .append(")\n");
                recorredorAd = recorredorAd.getSigAdyacente();
            }
        } else {
            sb.append(" (sin adyacentes)\n");
        }
        recorredor = recorredor.getSigVertice();
    }
    return sb.toString();
}


}
