import java.util.HashMap;
import java.util.Random;

import Utiles.DesdeArchivo;
import Utiles.IO;
import conjuntistas.*;
import transporteAgua.*;
import grafos.*;
import lineales.dinamica.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        // test0();
        test1();
        // test2();
        // test3();
        // res.insertar(n.getElem(), res.longitud() + 1);
    }

    private static void test0() {

    }

    private static void test1() {
        ArbolAVL ciudades = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        DesdeArchivo.cargarCiudades(ciudades, mapa);
        DesdeArchivo.cargarTuberias(ciudades, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(ciudades);

        Ciudad c1 = (Ciudad) ciudades.obtenerValor("Neufuen");
        Ciudad c2 = (Ciudad) ciudades.obtenerValor("Brezalia");
        Lista l = TransporteAgua.ciudadesEnRango(ciudades, c2.getNombre(), c1.getNombre(), 2020, 1, 0, 100);
        ciudades.dibujar();
        System.out.println(l.toString());
    }

    private static void test2() {
        // TransporteAgua.main();
        ArbolAVL ciudades = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        DesdeArchivo.cargarCiudades(ciudades, mapa);
        DesdeArchivo.cargarTuberias(ciudades, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(ciudades);

        // TransporteAgua.mostrarSistema(ciudades);

        // Lista a = mapa.obtenerCamino(((Ciudad)
        // ciudades.obtenerValor("Miracosta")).getNombre(), ((Ciudad)
        // ciudades.obtenerValor("Brezalia")).getNombre(), hmapTuberias);
        String nome1 = ((Ciudad) ciudades.obtenerValor("Miracosta")).getNombre();
        String nome2 = ((Ciudad) ciudades.obtenerValor("Brezalia")).getNombre();
        Lista a = mapa.obtenerCamino(nome1, nome2);
        // // Lista b = mapa.obtenerEtiquetasCamino(a);
        IO.sout("Camino Miracosta->Brezalia: " + a);
        nome1 = ((Ciudad) ciudades.obtenerValor("Neufuen")).getNombre();
        nome2 = ((Ciudad) ciudades.obtenerValor("Portenilo")).getNombre();
        a = mapa.obtenerCamino(nome1, nome2);
        IO.sout("Camino Neufuen->Portenilo: " + a);
        nome1 = ((Ciudad) ciudades.obtenerValor("Neufuen")).getNombre();
        nome2 = ((Ciudad) ciudades.obtenerValor("Valderia")).getNombre();
        a = mapa.obtenerCamino(nome1, nome2);
        IO.sout("Camino Neufuen->Valderia: " + a);
        IO.sout("Existe camino " + nome1 + "-> " + nome2 + ": " + mapa.existeCamino(nome1, nome2));
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
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        /*
         * TransporteAgua tAgua = new TransporteAgua();
         * Ciudad ciudadTest = new Ciudad("Buenos Aires", 203.0, 0.25);
         * tAgua.altaCiudad(arbolTest, mapa, ciudadTest);
         * ciudadTest = new Ciudad("Mendoza",199.0,0.22);
         * tAgua.altaCiudad(arbolTest, mapa, ciudadTest);
         * ciudadTest = new Ciudad("La Plata", 180.0, 0.20);
         * tAgua.altaCiudad(arbolTest, mapa, ciudadTest);
         * 
         * /*arbolTest.insertar("Buenos Aires", new Ciudad("Buenos Aires", 203.0,
         * 0.25));
         * Ciudad ciudadTest = (Ciudad) arbolTest.obtenerValor("Buenos Aires");
         * mapa.insertarVertice(ciudadTest.getNomenclatura());
         * arbolTest.insertar("Mendoza", new Ciudad("Mendoza", 199.0, 0.22));
         * ciudadTest = (Ciudad) arbolTest.obtenerValor("Mendoza");
         * mapa.insertarVertice(ciudadTest.getNomenclatura());
         * arbolTest.insertar("La Plata", new Ciudad("La Plata", 180.0, 0.20));
         * ciudadTest = (Ciudad) arbolTest.obtenerValor("La Plata");
         * mapa.insertarVertice(ciudadTest.getNomenclatura());
         * 
         * ClaveTuberia t1 = new ClaveTuberia(((Ciudad)
         * arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura());
         * DatosTuberia dt1 = new DatosTuberia(10, 20, 5, 'A');
         * ClaveTuberia t2 = new ClaveTuberia(((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("La Plata")).getNomenclatura());
         * DatosTuberia dt2 = new DatosTuberia(10, 20, 5, 'A');
         */

        /*
         * hmapTuberias.put(t1,dt1);
         * hmapTuberias.put(t2,dt2);
         * 
         * DatosTuberia prueba = DatosTuberia.obtenerDatos(hmapTuberias, ((Ciudad)
         * arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura());
         * DatosTuberia prueba2 = DatosTuberia.obtenerDatos(hmapTuberias, ((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("La Plata")).getNomenclatura());
         * 
         * mapa.insertarArco(((Ciudad)
         * arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura(), prueba.getCaudalMin());
         * mapa.insertarArco(((Ciudad)
         * arbolTest.obtenerValor("Mendoza")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("La Plata")).getNomenclatura(),
         * prueba2.getCaudalMin());
         * 
         * System.out.println(mapa.toString());
         * System.out.println(mapa.dibujarGrafo());
         * 
         * System.out.println(mapa.obtenerCamino(((Ciudad)
         * arbolTest.obtenerValor("Buenos Aires")).getNomenclatura(), ((Ciudad)
         * arbolTest.obtenerValor("La Plata")).getNomenclatura(),hmapTuberias));
         * 
         * 
         */

        /*
         * ciudadTest.setPoblacion(2020, 1, 100);
         * ciudadTest.setPoblacion(2020, 2, 254);
         * ciudadTest.setPoblacion(2020, 3, 100);
         * ciudadTest.setPoblacion(2020, 4, 100);
         * ciudadTest.setPoblacion(2020, 5, 100);
         * ciudadTest.setPoblacion(2020, 6, 100);
         * ciudadTest.setPoblacion(2020, 7, 100);
         * ciudadTest.setPoblacion(2020, 8, 100);
         * 
         * System.out.println(ciudadTest.getPoblacion(2020, 2));
         * System.out.println(ciudadTest.getPoblacionAnual(2020));
         */

        DesdeArchivo.cargarCiudades(arbolTest, mapa);
        DesdeArchivo.cargarTuberias(arbolTest, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(arbolTest);
        // Lista a = mapa.obtenerCamino(((Ciudad)
        // arbolTest.obtenerValor("Miracosta")).getNombre(),
        // ((Ciudad) arbolTest.obtenerValor("Brezalia")).getNombre(), hmapTuberias);
        // Lista b = mapa.obtenerEtiquetasCamino(a);
        // System.out.println(mapa.obtenerCamino(((Ciudad)
        // arbolTest.obtenerValor("Miracosta")).getNombre(),
        // ((Ciudad) arbolTest.obtenerValor("Brezalia")).getNombre(), hmapTuberias));
        // System.out.println(mapa.obtenerMenorEtiqueta(b));

        // System.out.println(hmapTuberias.values());
        // System.out.println(mapa.dibujarGrafo());
    }
}
