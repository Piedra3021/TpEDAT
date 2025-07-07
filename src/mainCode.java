import conjuntistas.*;
import transporteAgua.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        test0();
        test1();
        test2();
        test3();
    }

    private static void test0() {
    }

    private static void test1() {
        ArbolAVL arbol1 = new ArbolAVL();

        // Insersiones en el Arbol AVL
        arbol1.insertar(new Ciudad("Buenos Aires", 203.0));
        arbol1.insertar(new Ciudad("Mendoza", 199.0));
        arbol1.insertar(new Ciudad("La Plata", 180.0));
        arbol1.insertar(new Ciudad("El Chocon mejor pueblo de la historia", 5.0));

        arbol1.dibujar();

        arbol1.eliminar("Buenos Aires");
        System.out.println();

        arbol1.dibujar();

        // Prueba de metodos
        System.out.println(arbol1.pertenece("Mendoza"));
        System.out.println(arbol1.listar().toString());
        System.out.println(arbol1.listarRango("F", "P").toString());
        System.out.println(arbol1.minimoElem().toString());
        System.out.println(arbol1.maximoElem().toString());

        // Ejemplo de busqueda de una ciudad en el arbol
        // Se obtiene a traves de un metodo estatico por comodidad
        Ciudad ciudad1 = Ciudad.buscarEnArbol(arbol1, "Mendoza");
        System.out.println(ciudad1.getMetros() + ", " + ciudad1.getNombre());

    }

    private static void test2() {
        TransporteAgua.main();
    }

    private static void test3() {
        //
    }
}
