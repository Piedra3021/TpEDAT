import java.util.HashMap;
import java.util.Random;

import Utiles.IO;
import conjuntistas.*;
import transporteAgua.*;
import grafos.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        //test0();
        //test1();
        //test2();
        test3();
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
        //Nuevo constructor de poblacion = setPoblacion(año,mes,cantidadHabitantes)
        //c1.setPoblacion(2020,1-12,cantidad)
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
        ArbolAVL arbolTest = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();

        arbolTest.insertar("Buenos Aires", new Ciudad("Buenos Aires", 203.0, 0.25));
        Ciudad ciudadTest = (Ciudad) arbolTest.obtenerValor("Buenos Aires");
        mapa.insertarVertice(ciudadTest.getNomenclatura());
        arbolTest.insertar("Mendoza", new Ciudad("Mendoza", 199.0, 0.22));
        ciudadTest = (Ciudad) arbolTest.obtenerValor("Mendoza");
        mapa.insertarVertice(ciudadTest.getNomenclatura());
        arbolTest.insertar("La Plata", new Ciudad("La Plata", 180.0, 0.20));
        ciudadTest = (Ciudad) arbolTest.obtenerValor("La Plata");
        mapa.insertarVertice(ciudadTest.getNomenclatura());

        ClaveTuberia t1 = new ClaveTuberia(((Ciudad) arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura());
        DatosTuberia dt1 = new DatosTuberia(10, 20, 5, 'A');
        ClaveTuberia t2 = new ClaveTuberia(((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("La Plata")).getNomenclatura());
        DatosTuberia dt2 = new DatosTuberia(10, 20, 5, 'A');

        hmapTuberias.put(t1,dt1);
        hmapTuberias.put(t2,dt2);

        DatosTuberia prueba = DatosTuberia.obtenerDatos(hmapTuberias, ((Ciudad) arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura());
        DatosTuberia prueba2 = DatosTuberia.obtenerDatos(hmapTuberias, ((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("La Plata")).getNomenclatura());

        mapa.insertarArco(((Ciudad) arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura(), prueba.getCaudalMin());
        mapa.insertarArco(((Ciudad) arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("La Plata")).getNomenclatura(), prueba2.getCaudalMin());

        System.out.println(mapa.toString());
        System.out.println(mapa.dibujarGrafo());

        System.out.println(mapa.obtenerCamino(((Ciudad) arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad) arbolTest.obtenerValor("La Plata")).getNomenclatura(),hmapTuberias));


        
        
        /*ciudadTest.setPoblacion(2020, 1, 100);
        ciudadTest.setPoblacion(2020, 2, 254);
        ciudadTest.setPoblacion(2020, 3, 100);
        ciudadTest.setPoblacion(2020, 4, 100);
        ciudadTest.setPoblacion(2020, 5, 100);
        ciudadTest.setPoblacion(2020, 6, 100);
        ciudadTest.setPoblacion(2020, 7, 100);
        ciudadTest.setPoblacion(2020, 8, 100);

        System.out.println(ciudadTest.getPoblacion(2020, 2));
        System.out.println(ciudadTest.getPoblacionAnual(2020));*/

    }
}
