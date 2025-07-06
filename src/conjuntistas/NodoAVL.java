package conjuntistas;
class NodoAVL {
    
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    //constructor
    public NodoAVL(Comparable elem, NodoAVL enlaceizq, NodoAVL enlaceder) {
        this.elem = elem;
        this.izquierdo = enlaceizq;
        this.derecho = enlaceder;
        recalcularAltura();
    }
    
    //modificadores
    public void setElem(Comparable elem) {
        this.elem = elem;
    }
    
    public void setIzquierdo(NodoAVL enlaceIzq) {
        this.izquierdo = enlaceIzq;
    }

    public void setDerecho(NodoAVL enlaceDer) {
        this.derecho = enlaceDer;
    }

    public void recalcularAltura() {
        if (!(izquierdo == null || derecho == null)) {
            if (izquierdo.getAltura() > derecho.getAltura()) {
                this.altura = 1 + izquierdo.getAltura();
            } else {
                this.altura = 1 + derecho.getAltura();
            }
        } else if (izquierdo != null) {
            this.altura = 1 + izquierdo.getAltura();
        } else if (derecho != null) {
            this.altura = 1 + derecho.getAltura();
        } else this.altura = 0;
    }
    
    //observadoras
    public Comparable getElem() {
        return elem;
    }

    public int getAltura() {
        return altura;
    }
    
    public NodoAVL getIzquierdo() {
        return izquierdo;
    }
    
    public NodoAVL getDerecho() {
        return derecho;
    }

    public boolean esHoja(){
        return (this.izquierdo==null&&this.derecho==null);
    }

    public void dibujar(String espaciado) {
        if (!this.esHoja()) {
            if(this.getDerecho() != null && this.getIzquierdo() != null) {
                System.out.print(this.elem + " ----> ");
                this.derecho.dibujar(espaciado + " |     ");
                
                System.out.println("");
                System.out.print(espaciado +  " L----> ");
                if (this.getIzquierdo().esHoja()) {
                    this.getIzquierdo().dibujar(espaciado + " |     ");
                } else {
                    this.getIzquierdo().dibujar(espaciado + "       ");
                }
            } else if (this.getDerecho() != null) {
                System.out.print(this.elem + " ----> ");
                this.getDerecho().dibujar(espaciado + "        ");
            } else {
                System.out.println(this.elem);
                System.out.print(espaciado +  " L----> ");
                if (this.getIzquierdo().esHoja()) {
                    this.getIzquierdo().dibujar(espaciado + " |     ");
                } else {
                    this.getIzquierdo().dibujar(espaciado + "        ");
                }
            }
        } else {
            System.out.print(this.elem);
        }
    }
}