import java.util.HashMap;
import java.util.Random;

import Utiles.DesdeArchivo;
import Utiles.IO;
import conjuntistas.*;
import transporteAgua.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        // test0();
        // test1();
        test2();
        // test3();
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

        // ejemplo de busqueda
        DatosTuberia d1 = DatosTuberia.obtenerDatos(hmapTuberias, "A", "B");
        System.out.println(d1.getCaudalMax());
        System.out.println(d1.getCaudalMin());
        System.out.println(d1.getDiametro());
        System.out.println(d1.toString());
    }

    private static void test2() {
        // TransporteAgua.main();
        ArbolAVL ciudades = new ArbolAVL();
        Ciudad c1 = new Ciudad("ciu1");
        // Poblacion anual
        genPobRandom(c1);
        IO.sout(c1);
        IO.sout(c1.getPoblacionAnual(2020));
        Ciudad c2 = new Ciudad("ciu2");
        genPobRandom(c2);
        TransporteAgua.altaCiudad(ciudades, c1);
        TransporteAgua.altaCiudad(ciudades, c2);
        DesdeArchivo.cargarPoblacion(ciudades);
        IO.sout(c1.getPoblacion(2020, 1));
        TransporteAgua.mostrarSistema(ciudades);
    }

    private static void genPobRandom(Ciudad ciudad) {
        int pob = 1000;
        int anio = 2020;
        Random r = new Random();
        double variacion;
        // calcular consumo random por mes
        for (int mes = 1; mes < 13; mes++) {

            ciudad.setPoblacion(anio, mes, pob);
            variacion = r.nextInt(10);
            // Variar negativamente
            if (mes % 2 == 0) {
                variacion = variacion * -1;
            }
            pob += variacion;

        }
    }

    private static void test3() {
        ArbolAVL arbolTest = new ArbolAVL();

        arbolTest.insertar("Buenos Aires", new Ciudad("Buenos Aires", 203.0, 0.25));
        arbolTest.insertar("Mendoza", new Ciudad("Mendoza", 199.0, 0.22));
        arbolTest.insertar("La Plata", new Ciudad("La Plata", 180.0, 0.20));

        Ciudad ciudadTest = (Ciudad) arbolTest.obtenerValor("Buenos Aires");
        ciudadTest.setPoblacion(2020, 1, 100);
        ciudadTest.setPoblacion(2020, 2, 254);
        ciudadTest.setPoblacion(2020, 3, 100);
        ciudadTest.setPoblacion(2020, 4, 100);
        ciudadTest.setPoblacion(2020, 5, 100);
        ciudadTest.setPoblacion(2020, 6, 100);
        ciudadTest.setPoblacion(2020, 7, 100);
        ciudadTest.setPoblacion(2020, 8, 100);

        System.out.println(ciudadTest.getPoblacion(2020, 2));
        System.out.println(ciudadTest.getPoblacionAnual(2020));

    }
}
