package transporteAgua;

import java.util.HashMap;

import Utiles.IO;
import conjuntistas.ArbolAVL;
import grafos.Grafo;
import jerarquicas.*;
import lineales.*;

// cargar desde archivo
// Ciudad c1 = new Ciudad("neufuen", "ne3001","12000000");
public class TransporteAgua {
    public static void main() {
        Anio a2025 = new Anio(2025);
        Ciudad c1 = new Ciudad("neufuen", 100);
        ArbolAVL ciudades = new ArbolAVL();
        HashMap<String, Ciudad> hmapCiudades = new HashMap<String, Ciudad>();
        hmapCiudades.put("NE3000", c1);
    }

    public static void genAnio(Anio a) {
        for (int i = 0; i < 12; i++) {
            a.actualizarMes(i + 1, 300);
        }
    }

    public static void altaCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad> hmapCiudades, Ciudad nuevaCiudad) {
        IO.salida("ALTA ciudad", true);

        // arbolNombres.insertar(nombreNuevo);
        // String nome = calcNome(nombreNuevo);
        // hmapCiudades.put(nome, nuevaCiudad);

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
    public static void mostrarSistema(ArbolAVL arbolNombres, HashMap<String, Ciudad> hmapCiudades) {
        IO.sout(arbolNombres.toString());
        IO.sout(hmapCiudades.toString());
    }
}
