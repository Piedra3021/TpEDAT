import java.util.HashMap;
import java.util.Random;

import Utiles.IO;
import conjuntistas.*;
import transporteAgua.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        //test0();
        test1();
        //test2();
        //test3();
    }

    private static void test0() {

    }

    private static void test1() {
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        ClaveTuberia clave1 = new ClaveTuberia("A", "B");
        ClaveTuberia clave2 = new ClaveTuberia("B", "C");
        ClaveTuberia clave3 = new ClaveTuberia("A", "C");
        DatosTuberia datos1 = new DatosTuberia(100, 200, 50, 'A');
        DatosTuberia datos2 = new DatosTuberia(150, 250, 60, 'D');
        DatosTuberia datos3 = new DatosTuberia(200, 300, 70, 'A');

        hmapTuberias.put(clave1, datos1);
        hmapTuberias.put(clave2, datos2);
        hmapTuberias.put(clave3, datos3);

        //ejemplo de busqueda
        DatosTuberia d1 = DatosTuberia.obtenerDatos(hmapTuberias, "A", "B");
        System.out.println(d1.getCaudalMax());
        System.out.println(d1.getCaudalMin());
        System.out.println(d1.getDiametro());
        System.out.println(d1.toString());
    }

    private static void test2() {
        //TransporteAgua.main();
        ArbolAVL ciudades = new ArbolAVL();
        HashMap<String, Ciudad> hmapCiudades = new HashMap<String, Ciudad>();
        Ciudad c1 = new Ciudad("ciu1");
        // Poblacion anual
        Anio a1 = new Anio(2020);
        genPobRandom(a1);
        c1.setPoblacion(a1);
        IO.sout(a1);
        Ciudad c2 = new Ciudad("ciu2");
        Anio a2 = new Anio(2020);
        genPobRandom(a2);
        IO.sout(a2);
        //c2.setPoblacion(a2);
        //TransporteAgua.altaCiudad(ciudades, hmapCiudades, c1);
        //TransporteAgua.altaCiudad(ciudades, hmapCiudades, c2);
        //TransporteAgua.mostrarSistema(ciudades, hmapCiudades);
    }

    private static void genPobRandom(Anio anio) {
        int valorMes = 100000;
        Random r = new Random();
        double variacion;
        // calcular consumo random por mes
        for (int i = 1; i < 13; i++) {
            anio.actualizarMes(i, valorMes);
            variacion = r.nextInt(10000) ;
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
