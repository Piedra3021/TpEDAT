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

        IO.salida("TEST de CaminoMasCorto", false);
        TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "MALBRAN", hmapTuberias);

        // agregamos tuberia de Neufuen a Miracosta
        TransporteAgua.altaTuberia(ciudades, mapa, hmapTuberias,
                "NEUFUEN", "LASCOLINAS", new DatosTuberia(0, 5, 1, 'a'));

        TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "MALBRAN", hmapTuberias);

        // borramos Miracosta y agregamos tuberia de Neufuen a Malbran
        TransporteAgua.altaTuberia(ciudades, mapa, hmapTuberias,
                "NEUFUEN", "MALBRAN", new DatosTuberia(0, 5, 1, 'a'));

        TransporteAgua.caminoMasCorto(ciudades, mapa, "NEUFUEN", "MALBRAN", hmapTuberias);

        IO.salida("TEST ciudadesEnRango", false);

        TransporteAgua.ciudadesEnRango(ciudades, "A", "O", 2020, 1, 0, 5000);
        TransporteAgua.ciudadesEnRango(ciudades, "A", "O", 2020, 1, 0, 50000);
        TransporteAgua.ciudadesEnRango(ciudades, "N", "W", 2020, 1, 0, 50000);
        TransporteAgua.ciudadesEnRango(ciudades, "N", "W", 2020, 1, 0, 7500);
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
