package transporteAgua;

import java.util.HashMap;

import Utiles.DesdeArchivo;
import Utiles.IO;
import conjuntistas.ArbolAVL;
import grafos.Grafo;
import jerarquicas.*;
// import lineales.*;

// cargar desde archivo
// Ciudad c1 = new Ciudad("neufuen", "ne3001","12000000");
public class TransporteAgua {
    public static void main(String[] args) throws Exception {
        ArbolAVL ciudades = new ArbolAVL();
        DesdeArchivo.cargarCiudades(ciudades);
        System.out.println("A");
        mostrarSistema(ciudades);
    }

    public static void genAnio(Anio a) {
        for (int i = 0; i < 12; i++) {
            a.actualizarMes(i + 1, 300);
        }
    }

    public static void altaCiudad(ArbolAVL arbol, Ciudad nuevaCiudad) {
        IO.salida("ALTA ciudad." + nuevaCiudad.toString(), true);

        arbol.insertar(nuevaCiudad.getNombre(), nuevaCiudad);
    }

    public static void modCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad> hmapCiudades, String nombreNuevo,
            double metros) {
        IO.salida("MOD ciudad", true);

    }

    public static void bajaCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad> hmapCiudades, String nombreNuevo,
            double metros) {
        IO.salida("BAJA ciudad", true);

    }

    // public static void altaTuberia(Grafo grafo) {
    // IO.salida("ALTA tuberia", true);
    // }

    // public static void modTuberia(Grafo grafo) {
    // IO.salida("ALTA tuberia", true);
    // }

    // public static void bajaTuberia(Grafo grafo) {
    // IO.salida("ALTA tuberia", true);
    // }

    // 8.
    public static void mostrarSistema(ArbolAVL arbol) {
        IO.salida("MostrarSistema", false);
        arbol.dibujar();
    }
}
