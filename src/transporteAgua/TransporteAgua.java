package transporteAgua;

import java.util.HashMap;

import Utiles.DesdeArchivo;
import Utiles.IO;
import conjuntistas.ArbolAVL;
import conjuntistas.HeapMax;
import grafos.Grafo;
import jerarquicas.*;
import lineales.*;
import lineales.dinamica.Lista;

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
        // Ejemplo de eliminar una ciudad
        bajaCiudad(ciudades, hMapTuberias, "Miracosta", grafo);
        // Ejemplo de eliminar una tuberia
        bajaTuberia(ciudades, grafo, hMapTuberias, "Verdemar", "Solferino");
        // Ejemplo de alta ciudad
        Ciudad nuevaCiudad = new Ciudad("NuevaCiudad", 1000, 50);
        altaCiudad(ciudades, grafo, nuevaCiudad);
        // Ejemplo de alta tuberia
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

    /*
     * public static void modCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad>
     * hmapCiudades, String nombreNuevo,
     * double metros) {
     * IO.salida("MOD ciudad", true);
     * }
     */

    public static boolean bajaCiudad(ArbolAVL arbolNombres,
            HashMap<ClaveTuberia, DatosTuberia> hMapTub, String nombre,
            Grafo grafo) {
        IO.salida("BAJA ciudad", true);
        boolean exito = false;
        Ciudad c = (Ciudad) arbolNombres.obtenerValor(nombre);

        if (c != null) {
            // Si encuentra la tuberia, la elimina de las estructuras
            exito = arbolNombres.eliminar(nombre);
            if (exito) {
                grafo.eliminarVertice(c);
                // En cada elemento del HashMap, se verifica si la clave
                // contiene la nomenclatura de la ciudad
                hMapTub.entrySet().removeIf(entry -> entry.getKey().getNom1().equals(c.getNomenclatura()) ||
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

    /*
     * public static void modTuberia(Grafo grafo) {
     * IO.salida("ALTA tuberia", true);
     * }
     */

    public static boolean bajaTuberia(ArbolAVL ciudades, Grafo grafo, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta) {
        IO.salida("BAJA tuberia", true);
        boolean exito = false;
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde);
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta);
        if (c1 != null && c2 != null && grafo.existeArco(c1, c2)) {
            // Si la tuberia existe, se elimina de las estructuras
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            hMapTuberias.remove(clave);
            grafo.eliminarArco(c1, c2);
            exito = true;
        } else {
            IO.salida("No se pudo eliminar la tubería: no existe.", true);
        }
        return exito;
    }

    // private static Ciudad leerCiudad(ArbolAVL arbol){
    // leer por teclado y obtener desde el arbol
    // validar entrada?
    // }
    // Ej 4-1
    public static void mostrarCiudad(ArbolAVL arbol) {
        IO.salida("INI mostrarCiudad", false);
        // Ciudad c = leerCiudad(arbol);
        String nombre = "Neufuen";
        Ciudad c = (Ciudad) arbol.obtenerValor(nombre);
        // validar ciudad?
        if (c != null) {
            // Leer anio y mes por teclado? usar ultimo elemento del elemento anio?
            // getPoblacionUltimoMes()?
            double pobActual = c.getPoblacion(2020, 1);
            IO.salida("Cant habitantes " + nombre + ": " + pobActual, true);

            // leer anio y mes para calc volumen
            // int anioVol = TecladoIn..
            // int mesVol = TecladoIn..
            // double volumen = c.calcVolumen(anio, mes);
            // IO.salida("Volumen distribuido en "+mes+"/"+anio+":
            // "+volumen+"(unidad)",true);
            // double pobMes = c.getPoblacion(anioVol,mesVol);
            // Revisar!
            // IO.salida("Volumen distribuido en "+mesVol+"/"+anioVol+":
            // "+volumen/pobVol+"(unidad)",true);
        }

        IO.salida("FIN mostrarCiudad", false);
    }

    // Ej 4-2
    public static Lista ciudadesEnRango(ArbolAVL arbol, String nomb1, String nomb2, int anio, int mes
                , double minVol, double maxVol) {
        IO.salida("INI ciudadesEnRango", false);
        // Se listan las ciudades segun el rango de nombres
        Lista ciudadesEnRango = arbol.listarRangoValor(nomb1, nomb2), resultado = new Lista();
        Ciudad ciudad;
        if (ciudadesEnRango.longitud() > 0) {
            // Si se encontraron ciudades en el rango
            // se evalua si su comsumo de agua esta dentro del rango
            for (int i = 1; i <= ciudadesEnRango.longitud(); i++) {
                ciudad = (Ciudad) ciudadesEnRango.recuperar(i);
                double volumen = ciudad.cantidadAguaPorMes(anio, mes);
                if (!(volumen < minVol || volumen > maxVol)) {
                    resultado.insertar(ciudad, 1);
                }
            }
        } else {
            IO.salida("No se encontraron ciudades en el rango especificado.", true);
        }
        IO.salida("FIN ciudadesEnRango", false);
        return resultado;
    }

    // Ej 5-1
    public static void caminoCaudalPleno(ArbolAVL arbol) {
        IO.salida("INI caminoCaudalPleno", false);
        // ...
        // leer c1 y c2
        // obtener camino con maximos menores?
        // mostrar estado del camino
        IO.salida("FIN caminoCaudalPleno", false);
    }

    // Ej 5-2
    public static void caminoMasCorto(ArbolAVL arbol, Grafo mapa,  String origen, String destino,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("INI caminoMasCorto", false);
        Ciudad c1 = (Ciudad) arbol.obtenerValor(origen);
        Ciudad c2 = (Ciudad) arbol.obtenerValor(destino);
        if (c1 != null && c2 != null) {
            Lista camino = mapa.caminoMasCorto(c1, c2);
            if (camino != null) {
                IO.salida("Camino más corto de " + c1.getNombre() + " a " + c2.getNombre() + ": " + camino.toString(), true);
                IO.salida("Estado del camino: " + definirEstadoCamino(camino, hMapTuberias), true);
            } else {
                IO.salida("No se encontró un camino entre " + c1.getNombre() + " y " + c2.getNombre(), true);
            }
        }
        IO.salida("FIN caminoMasCorto", false);
    }

    private static String definirEstadoCamino(Lista camino, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        String[] estados = new String[] {
                "Activo",
                "En Reparación",
                "Inactivo",
                "En Diseño"
        };
        int estadoIndex = 0; // En principio, activo
        int i = 1;
        while (i+1 <= camino.longitud() && estadoIndex < 3) {
            ClaveTuberia clave = new ClaveTuberia(((Ciudad) camino.recuperar(i)).getNomenclatura(), 
                ((Ciudad) camino.recuperar(i+1)).getNomenclatura());
            char estado = hMapTuberias.get(clave).getEstado();
            if (estado != 'a') {
                if (estado != 'r') {
                    if (estado != 'i') {
                        estadoIndex = 3; // En Diseño
                    } else {
                        if (estadoIndex < 2)
                            estadoIndex = 2; // Inactivo
                    }
                } else {
                    if (estadoIndex < 1)
                        estadoIndex = 1; // En Reparación
                }
            }
            i++;
        }
        return estados[estadoIndex];
    }

    // Ej 7
    public static Lista listarPorConsumoAnual(ArbolAVL arbol, int anio) {
        IO.salida("INI listarPorConsumoAnual", false);
        // Se listan las ciudades ordenadas por consumo anual
        Lista ciudadesOrdenadas = arbol.listarValor(), resultado = new Lista();
        int longi = ciudadesOrdenadas.longitud();
        double consumoAnual;
        ConsumoAnual consumoCiudad;
        HeapMax heap = new HeapMax(longi);
        Ciudad ciudad;
        // Se recorre la lista de ciudades y se calcula el consumo anual
        for (int i = 1; i <= longi; i++) {
            ciudad = (Ciudad) ciudadesOrdenadas.recuperar(i);
            consumoAnual = ciudad.cantidadAguaPorAnio(anio);
            consumoCiudad = new ConsumoAnual(ciudad, consumoAnual);
            heap.insertar(consumoCiudad);
        }
        // Extraigo los elementos del heap y los inserto en la lista resultado
        while (!heap.esVacio()) {
            resultado.insertar(heap.obtenerCima(), resultado.longitud() + 1);
            heap.eliminarCima();
        }
        IO.salida("FIN listarPorConsumoAnual", false);
        return resultado;
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
