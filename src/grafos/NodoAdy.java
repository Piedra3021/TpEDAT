package grafos;

public class NodoAdy {

    //atributos
    NodoVert vertice;
    NodoAdy sigAdyacente;
    int etiqueta;

    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente, int etiq){

        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
        this.etiqueta = etiq;
        
    }

    //getters y setters
    public NodoVert getVertice() {
        return vertice;
    }
    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }
    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }
    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }
    public int getEtiqueta() {
        return etiqueta;
    }
    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }


    
}
