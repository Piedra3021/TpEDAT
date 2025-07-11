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
        Grafo grafo = new Grafo();
        HashMap<ClaveTuberia, DatosTuberia> hMapTuberias = new HashMap<>();
        DesdeArchivo.cargarCiudades(ciudades, grafo);
        DesdeArchivo.cargarTuberias(ciudades, grafo, hMapTuberias);

        // ejemplo de usos de los metodos (Eliminar luego)
        //Ejemplo de eliminar una ciudad
        bajaCiudad(ciudades, hMapTuberias, "Miracosta", grafo);
        //Ejemplo de eliminar una tuberia
        bajaTuberia(ciudades, grafo, hMapTuberias, "Verdemar", "Solferino");
        //Ejemplo de alta ciudad
        Ciudad nuevaCiudad = new Ciudad("NuevaCiudad", 1000, 50);
        altaCiudad(ciudades, grafo, nuevaCiudad);
        //Ejemplo de alta tuberia
        DatosTuberia nuevaTuberia = new DatosTuberia(100, 10, 5, 'a');
        altaTuberia(ciudades, grafo, hMapTuberias, "NuevaCiudad", "Orquin", nuevaTuberia);

        mostrarSistema(ciudades, grafo, hMapTuberias);
    }

    public static void genAnio(Anio a) {
        for (int i = 0; i < 12; i++) {
            a.actualizarMes(i + 1, 300);
        }
    }

    public static void altaCiudad(ArbolAVL arbol, Grafo grafo, Ciudad nuevaCiudad) {
        IO.salida("ALTA ciudad." + nuevaCiudad.toString(), true);
        arbol.insertar(nuevaCiudad.getNombre(), nuevaCiudad);
        grafo.insertarVertice(nuevaCiudad);
    }

    /*public static void modCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad> hmapCiudades, String nombreNuevo,
            double metros) {
        IO.salida("MOD ciudad", true);
    }*/

    public static boolean bajaCiudad(ArbolAVL arbolNombres, 
            HashMap<ClaveTuberia, DatosTuberia> hMapTub, String nombre,
            Grafo grafo) {
        IO.salida("BAJA ciudad", true);
        boolean exito = false;
        Ciudad c = (Ciudad) arbolNombres.obtenerValor(nombre);

        if (c != null) {
            //Si encuentra la tuberia, la elimina de las estructuras
            exito = arbolNombres.eliminar(nombre);
            if (exito) {
                grafo.eliminarVertice(c);
                //En cada elemento del HashMap, se verifica si la clave 
                //contiene la nomenclatura de la ciudad
                hMapTub.entrySet().removeIf(entry -> 
                    entry.getKey().getNom1().equals(c.getNomenclatura()) || 
                    entry.getKey().getNom2().equals(c.getNomenclatura()));
            }
        } else {
            IO.salida("No se pudo eliminar la ciudad: no existe.", true);
        }

        return exito;
    }

    public static void altaTuberia(ArbolAVL ciudades, Grafo grafo,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta, DatosTuberia datosTuberia) {
        IO.salida("ALTA tuberia", true);
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde);
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta);
        // Verifica que las ciudades existan y que no haya un arco entre ellas
        if (c1 != null && c2 != null && !c1.equals(c2) && !grafo.existeArco(c1, c2)) {
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            hMapTuberias.put(clave, datosTuberia);
            grafo.insertarArco(c1, c2, datosTuberia.getCaudalMax());
        } else {
            IO.salida("No se pudo agregar la tubería: una o ambas ciudades no existen.", true);
        }
    }

    /*public static void modTuberia(Grafo grafo) {
        IO.salida("ALTA tuberia", true);
    }*/

    public static boolean bajaTuberia(ArbolAVL ciudades, Grafo grafo, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta) {
        IO.salida("BAJA tuberia", true);
        boolean exito = false;
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde);
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta);
        if (c1 != null && c2 != null && grafo.existeArco(c1, c2)) {
            //Si la tuberia existe, se elimina de las estructuras
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            hMapTuberias.remove(clave);
            grafo.eliminarArco(c1, c2);
            exito = true;
        } else {
            IO.salida("No se pudo eliminar la tubería: no existe.", true);
        }
        return exito;
    }

    // 8.
    public static void mostrarSistema(ArbolAVL arbol, Grafo grafo,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("MostrarSistema", false);
        System.out.println("Arbol de Ciudades:");
        arbol.dibujar();
        System.out.println();
        System.out.println("HashMap de Tuberías:");
        System.out.println(hMapTuberias.toString());
        System.out.println();
        System.out.println("Grafo de Transporte de Agua:");
        System.out.println(grafo.dibujarGrafo());
    }
}
