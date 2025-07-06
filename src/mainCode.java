import conjuntistas.*;
import transporteAgua.TransporteAgua;

public class mainCode {
    public static void main(String[] args) throws Exception {
        ArbolAVL arbol1 = new ArbolAVL();
        arbol1.insertar(10);
        arbol1.insertar(20);
        arbol1.insertar(15);
        arbol1.insertar(11);
        arbol1.insertar(21);
        arbol1.insertar(12);
        arbol1.insertar(13);
        arbol1.insertar(24);
        arbol1.insertar(16);

        arbol1.dibujar();

        arbol1.eliminar(21);

        arbol1.dibujar();
        TransporteAgua.main();
    }
}
