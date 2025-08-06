import java.util.HashMap;
import java.util.Random;

import Utiles.DesdeArchivo;
import Utiles.IO;
import Utiles.TecladoIn;
import conjuntistas.*;
import transporteAgua.*;
import grafos.*;
import lineales.dinamica.*;

public class mainCode {
    public static void main(String[] args) throws Exception {
        IO.sout(DesdeArchivo.getProps().getProperty("nombre"));
        int nTest = Integer.parseInt(DesdeArchivo.getProps().getProperty("test"));
        IO.sout("Test: " + nTest);
        switch (nTest) {
            case 1:
                test1();
                break;
            case 2:
                test2();
                break;
            case 3:
                test3();
                break;
            default:
                test0();
                break;
        }
        // test1();
        // test2();
        // test3();
        // res.insertar(n.getElem(), res.longitud() + 1);
    }

    private static void test0() throws Exception {
        IO.sout("Ini test 0");
        TransporteAgua.main(null);
    }

    private static void test1() {
        IO.sout("Ini test 1");
        ArbolAVL ciudades = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        DesdeArchivo.cargarCiudades(ciudades, mapa);
        DesdeArchivo.cargarTuberias(ciudades, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(ciudades);

        // Test de eliminacion
        ciudades.dibujar();
        IO.sout("Eliminar Orquin y PuertoYaco (RSI)");
        ciudades.eliminar("ORQUIN");
        ciudades.eliminar("PUERTOYACO");
        ciudades.dibujar();
        IO.sout("Eliminar VerdeMar (RDD)");
        ciudades.eliminar("VERDEMAR");
        ciudades.dibujar();
        IO.sout("Eliminar Valderia, Solferino y Tarnavia (RSD)");
        ciudades.eliminar("VALDERIA");
        ciudades.eliminar("SOLFERINO");
        ciudades.eliminar("TARNAVIA");
        ciudades.dibujar();
        IO.sout("Eliminar Esplendida y FuentesDelSol (RDI)");
        ciudades.eliminar("ESPLENDIDA");
        ciudades.eliminar("FUENTESDELSOL");
        ciudades.dibujar();
        IO.sout("Eliminar SanUrquiza (Un hijo)");
        ciudades.eliminar("SANURQUIZA");
        ciudades.dibujar();
        IO.sout("Eliminar Portenilo (Sin hijos)");
        ciudades.eliminar("PORTENILO");
        ciudades.dibujar();
        IO.sout("Eliminar Neufuen (Raiz, Dos hijos)");
        ciudades.eliminar("NEUFUEN");
        ciudades.dibujar();

        // Test de insercion
        IO.sout("Insertar Miracosto");
        ciudades.insertar("MIRACOSTO", new Ciudad("Miracosto", 150000.0, 0.25));
        ciudades.dibujar();
        IO.sout("Insertar LasAltas");
        ciudades.insertar("LASALTAS", new Ciudad("LasAltas", 100000.0, 0.15));
        ciudades.dibujar();
        IO.sout("Insertar Malbron");
        ciudades.insertar("MALBRON", new Ciudad("Malbron", 50000.0, 0.1));
        ciudades.dibujar();
        IO.sout("Insertar Malbrun");
        ciudades.insertar("MALBRUN", new Ciudad("Malbrun", 200000.0, 0.3));
        ciudades.dibujar();
        IO.sout("Fin test 1");
    }

    private static void test2() {
        IO.sout("Ini test 2");
        // TransporteAgua.main();
        ArbolAVL ciudades = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();
        DesdeArchivo.cargarCiudades(ciudades, mapa);
        DesdeArchivo.cargarTuberias(ciudades, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(ciudades);
        Lista a;
        IO.separador("Ejemplo varios caminos(5-1)");
        TransporteAgua.printTuberias(hmapTuberias);
        IO.salida(mapa.obtenerTodosCaminos("Neufuen", "Portenilo"), false);
        a = TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "Neufuen", "Portenilo");
        IO.separador("Ejemplo BAJA MIRACOSTA(5-1)");
        TransporteAgua.bajaCiudad(ciudades, hmapTuberias, "MIRACOSTA", mapa);
        IO.salida(mapa.obtenerTodosCaminos("Neufuen", "Portenilo"), false);
        TransporteAgua.printTuberias(hmapTuberias);
        a = TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "Neufuen", "Portenilo");
        IO.separador("Ejemplo unico camino(5-1)");
        IO.salida(mapa.obtenerTodosCaminos("Neufuen", "Verdemar"), false);
        a = TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "NEUFUEN", "VERDEMAR");
        IO.separador("Una ciudad no existe(5-1)");
        IO.salida(mapa.obtenerTodosCaminos("Neufuen", "Inexistente"), false);
        a = TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "NEUFUEN", "Inexistente");
        IO.separador("No hay camino entre ambas(5-1)");
        IO.salida(mapa.obtenerTodosCaminos("Portenilo", "Neufuen"), false);
        a = TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "Portenilo", "Neufuen");

        DatosTuberia dT = null;
        double caudalMin = 0;
        double caudalMax = 5;
        double diametro = 1;
        char estado = 'r';
        dT = new DatosTuberia(caudalMin, caudalMax, diametro, estado);
        IO.separador("Ejemplo insercion tuberia (5-1)");
        TransporteAgua.altaTuberia(ciudades, mapa, hmapTuberias, "Solferino", "Portenilo", dT);
        TransporteAgua.printTuberias(hmapTuberias);
        IO.salida(mapa.obtenerTodosCaminos("Neufuen", "Portenilo"), false);
        TransporteAgua.caminoCaudalPleno(ciudades, mapa, hmapTuberias, "Neufuen", "Portenilo");

        // TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "PORTENILO", hmapTuberias);
        // TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "PUERTOYACO", hmapTuberias);
        // TransporteAgua.bajaTuberia(ciudades, mapa, hmapTuberias, "RIOMAYOR", "SOLFERINO");
        // TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "PUERTOYACO", hmapTuberias);
        // TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "Inexistente", hmapTuberias);
        // TransporteAgua.caminoMasCorto(ciudades, mapa, "PORTENILO", "NEUFUEN", hmapTuberias);
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
        IO.sout("Ini test 3");
        ArbolAVL arbolTest = new ArbolAVL();
        Grafo mapa = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hmapTuberias = new HashMap<ClaveTuberia, DatosTuberia>();

        int loop = TecladoIn.readInt();
        DesdeArchivo.cargarCiudades(arbolTest, mapa);
        DesdeArchivo.cargarTuberias(arbolTest, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(arbolTest);

        while (loop != 0) {
            Ciudad cOrigen = (Ciudad) arbolTest.obtenerValor("MIRACOSTA");
            Ciudad cDestino = (Ciudad) arbolTest.obtenerValor("LASCOLINAS");

            Lista CAMINO = mapa.obtenerCamino(((Ciudad) arbolTest.obtenerValor("MIRACOSTA")).getNomenclatura(),
                    ((Ciudad) arbolTest.obtenerValor("CAMPOAZUL")).getNomenclatura());
            System.out.println(CAMINO.toString());

            Lista etiquetas = mapa.obtenerEtiquetasCamino(CAMINO);
            System.out.println(etiquetas.toString());

            double resultado = mapa.obtenerMenorEtiqueta(etiquetas);
            System.out.println(resultado);

            System.out.println("- 2da parte - ");
            /*
             * Lista camino = mapa.obtenerPrimerActivo("Miracosta", hmapTuberias);
             * System.out.println("CAMINO ACTIVO");
             * System.out.println(camino + "\n");
             * Lista etiquLista = mapa.obtenerEtiquetasCamino(camino);
             * System.out.println("ETIQUETAS");
             * System.out.println(etiquLista + "\n");
             * double menor = mapa.obtenerMenorEtiqueta(etiquLista);
             * System.out.println("MENOR");
             * System.out.println(menor + "\n");
             * double porHab = ((Ciudad)
             * arbolTest.obtenerValor("MIRACOSTA")).cantidadAguaPorMes(2020, 1);
             * System.out.println("aguaPorHab");
             * System.out.println(porHab);
             */

            Lista todosCaminos = mapa.obtenerTodosCaminos(cOrigen.getNombre(), cDestino.getNombre());
            for (int i = 1; i < todosCaminos.longitud() + 1; i++) {
                System.out.println("camino " + (i));
                System.out.println(todosCaminos.recuperar(i));
                System.out.println(mapa.obtenerEtiquetasCamino((Lista) todosCaminos.recuperar(i)));
            }

            TransporteAgua.mostrarCiudad(arbolTest, mapa, hmapTuberias, "MIRACOSTA", 0, 0);

            /*
             * Lista caminoCaudalPleno = TransporteAgua.caminoCaudalPleno(arbolTest, mapa,
             * hmapTuberias);
             * System.out.println(caminoCaudalPleno);
             * caminoCaudalPleno = mapa.obtenerEtiquetasCamino(caminoCaudalPleno);
             * System.out.println(caminoCaudalPleno);
             * System.out.println(mapa.obtenerMenorEtiqueta(caminoCaudalPleno));
             * // TransporteAgua.mostrarCiudad(arbolTest, mapa, hmapTuberias);
             * System.out.println(TransporteAgua.listarPorConsumoAnual(arbolTest, 2020));
             */

            String nCiudad = IO.ingresarString("MOD. Ingrese nombre de ciudad");
            TransporteAgua.modCiudad(arbolTest, nCiudad);
            loop = TecladoIn.readInt();
        }
    }
}
