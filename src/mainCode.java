import java.util.HashMap;
import java.util.Random;

import Utiles.IO;
import conjuntistas.*;
import transporteAgua.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        test0();
        // test1();
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
        ArbolAVL ciudades = new ArbolAVL();
        HashMap<String, Ciudad> hmapCiudades = new HashMap<String, Ciudad>();
        Ciudad c1 = new Ciudad("ciu1");
        Anio a1 = new Anio(2020);
        genPromRandom(a1);
        c1.setConsumoProm(a1);
        IO.sout(a1);
        Ciudad c2 = new Ciudad("ciu2");
        Anio a2 = new Anio(2020);
        genPromRandom(a2);
        IO.sout(a2);
        c2.setConsumoProm(a2);
        TransporteAgua.altaCiudad(ciudades, hmapCiudades, c1);
        TransporteAgua.altaCiudad(ciudades, hmapCiudades, c2);
        TransporteAgua.mostrarSistema(ciudades, hmapCiudades);
    }

    private static void genPromRandom(Anio anio) {
        double valorMes = 0.30;
        Random r = new Random();
        double variacion;
        // calcular consumo random por mes
        for (int i = 1; i < 13; i++) {
            anio.actualizarMes(i, valorMes);
            variacion = r.nextDouble() / 10;
            // Variar negativamente
            if (i % 2 == 0) {
                variacion = variacion * -1;
            }
            valorMes += variacion;

        }
    }

    private static void test3() {
        //
    }
}
