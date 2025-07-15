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
        // test1();
        // test2();
        test3();
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
        Lista l = TransporteAgua.ciudadesEnRango(ciudades, c2.getNombre(), c1.getNombre(), 2020, 1, 0, 250);
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
        Lista ciuRangoNom = ciudades.listarRango("NE0000", "PU3013");
        // Lista ciuRangoNom = ciudades.listarRangoValor("NE0000", "NZ9999");
        IO.sout(ciuRangoNom);
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

        DesdeArchivo.cargarCiudades(arbolTest, mapa);
        DesdeArchivo.cargarTuberias(arbolTest, mapa, hmapTuberias);
        DesdeArchivo.cargarPoblacion(arbolTest);

        Lista CAMINO = mapa.obtenerCamino(((Ciudad) arbolTest.obtenerValor("Miracosta")).getNomenclatura(),
                ((Ciudad) arbolTest.obtenerValor("CampoAzul")).getNomenclatura());
        System.out.println(CAMINO.toString());

        Lista etiquetas = mapa.obtenerEtiquetasCamino(CAMINO);
        System.out.println(etiquetas.toString());

        double resultado = mapa.obtenerMenorEtiqueta(etiquetas);
        System.out.println(resultado);

        System.out.println("- 2da parte - ");
        Lista camino = mapa.obtenerPrimerActivo("Miracosta", hmapTuberias);
        System.out.println("CAMINO ACTIVO");
        System.out.println(camino + "\n");
        Lista etiquLista = mapa.obtenerEtiquetasCamino(camino);
        System.out.println("ETIQUETAS");
        System.out.println(etiquLista + "\n");
        double menor = mapa.obtenerMenorEtiqueta(etiquLista);
        System.out.println("MENOR");
        System.out.println(menor + "\n");
        double porHab = ((Ciudad) arbolTest.obtenerValor("Miracosta")).cantidadAguaPorMes(2020, 1);
        System.out.println("aguaPorHab");
        System.out.println(porHab);

        TransporteAgua.mostrarCiudad(arbolTest, mapa, hmapTuberias);

    }
}
