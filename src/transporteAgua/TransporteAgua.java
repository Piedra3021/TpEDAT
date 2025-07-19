package transporteAgua;

import java.util.HashMap;

import Utiles.DesdeArchivo;
import Utiles.IO;
import Utiles.TecladoIn;
import conjuntistas.ArbolAVL;
import conjuntistas.HeapMin;
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
        DesdeArchivo.cargarPoblacion(ciudades);
        // IO.menu(ciudades, grafo, hMapTuberias);

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
        boolean exitoArbol = arbol.insertar(nuevaCiudad.getNombre(), nuevaCiudad);
        boolean exitoGrafo = grafo.insertarVertice(nuevaCiudad);
        IO.salida("ALTA ciudad." + nuevaCiudad.toString() + ". exitoArbol: " + exitoArbol + ". Grafo: " + exitoGrafo,
                true);
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
            IO.salida("No se pudo eliminar la ciudad: no existe " + nombre, true);
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
            String mensaje = "No se pudo agregar la tubería: ";
            if (c1 == null || c2 == null) {
                mensaje += c1 == null ? desde : hasta;
                mensaje += " no existe";
            } else {
                mensaje += "c1==c2 o no estan conectadas las ciudades";
            }
            IO.salida(mensaje, true);
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
            String mensaje = "No se pudo eliminar la tubería: ";
            if (c1 == null || c2 == null) {
                mensaje += c1 == null ? desde : hasta;
                mensaje += " no existe";
            } else {
                mensaje += "no estan conectadas las ciudades";
            }
            IO.salida(mensaje, true);
        }
        return exito;
    }

    public static double obtenerAguaAprovisionada(ArbolAVL ciudades, Grafo mapa,
            HashMap<ClaveTuberia, DatosTuberia> hmapTuberias, String nombre, int anio, int mes) {
        Ciudad ciudad = (Ciudad) ciudades.obtenerValor(nombre);
        double resultado = -1;

        if (ciudad != null) {
            // Obtiene la cantidad de agua en un año, en un mes
            double aguaPorHab = ciudad.cantidadAguaPorMes(anio, mes);
            double aguaCaudal = -1;

            // Obtiene el primer camino de tuberias activo de principio a fin.
            Lista caminoA = mapa.obtenerPrimerActivo(ciudad, hmapTuberias);
            if (caminoA != null) {
                // Si existe, obtiene las etiquetas (caudales) y luego el minimo de estos
                // Ya que va a ser la capacidad maxima del camino.
                caminoA = mapa.obtenerEtiquetasCamino(caminoA);

                aguaCaudal = mapa.obtenerMenorEtiqueta(caminoA);
            }

            // Si es mayor que 0, es que si existe el anio, mes
            if (aguaPorHab > 0) {
                // Existe algun camino activo en la ciudad
                if (aguaCaudal > 0) {
                    if (aguaPorHab < aguaCaudal) {
                        resultado = aguaPorHab;
                    } else {
                        resultado = aguaCaudal;
                    }
                } else {
                    resultado = aguaPorHab;
                }
            } else {
                resultado = aguaCaudal;
            }

        }
        return resultado;
    }

    // private static Ciudad leerCiudad(ArbolAVL arbol){
    // leer por teclado y obtener desde el arbol
    // validar entrada?
    // }
    // Ej 4-1
    public static void mostrarCiudad(ArbolAVL arbol, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberia) {
        IO.salida("INI mostrarCiudad", true);
        // Ciudad c = leerCiudad(arbol);
        IO.salida("Indique la ciudad", false);
        String nombre = TecladoIn.readLine();
        Ciudad c = (Ciudad) arbol.obtenerValor(nombre);
        // validar ciudad?
        if (c != null) {
            IO.salida("Inserte el anio", false);
            int anio = TecladoIn.readInt();
            IO.salida("Inserte el mes", false);
            int mes = TecladoIn.readInt();
            // Leer anio y mes por teclado? usar ultimo elemento del elemento anio?
            // getPoblacionUltimoMes()?
            int pobActual = c.getPoblacion(anio, mes);
            if (pobActual != -1) {
                Lista caminoA = mapa.obtenerPrimerActivo(c, hMapTuberia);
                caminoA = mapa.obtenerEtiquetasCamino(caminoA);

                IO.salida("=== Informe de distribución de agua ===", false);
                IO.salida("Ciudad: " + c.toString(), false);
                IO.salida("Mes/Año: " + String.format("%02d", mes) + "/" + anio + "\n", false);
                IO.salida("Cantidad de habitantes registrados: " + pobActual + "\n", false);
                IO.salida("Volumen de agua calculado por cantidad de habitantes: "
                        + c.cantidadAguaPorMes(anio, mes) + " m³", false);
                IO.salida("Volumen de agua calculado por caudal disponible:     "
                        + mapa.obtenerMenorEtiqueta(caminoA) + " m³\n", false);
                IO.salida(">> Volumen efectivamente aprovisionado: "
                        + TransporteAgua.obtenerAguaAprovisionada(arbol, mapa, hMapTuberia, nombre, anio, mes) + " m³",
                        false);

            }

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
    public static Lista ciudadesEnRango(ArbolAVL arbol, String nomb1, String nomb2, int anio, int mes, double minVol,
            double maxVol) {
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

    // Ej. 5-1
    public static void caminoCaudalPleno(ArbolAVL arbol, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("INI caminoCaudalPleno", false);

        IO.salida("Ingrese ciudad Origen", false);
        String cOrigen = TecladoIn.readLine();
        Ciudad ciudadO = (Ciudad) arbol.obtenerValor(cOrigen);
        IO.salida("Ingrese ciudad Destino", false);
        String cDestino = TecladoIn.readLine();
        Ciudad ciudadD = (Ciudad) arbol.obtenerValor(cDestino);
        Lista camino = mapa.obtenerCamino(cOrigen, cDestino);
        camino = mapa.obtenerEtiquetasCamino(camino);

        // ...
        // leer c1 y c2
        // obtener camino con maximos menores?
        // mostrar estado del camino
        IO.salida("FIN caminoCaudalPleno", false);
    }

    // Ej 5-2
    public static void caminoMasCorto(ArbolAVL arbol, Grafo mapa, String origen, String destino,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("INI caminoMasCorto", false);
        Ciudad c1 = (Ciudad) arbol.obtenerValor(origen);
        Ciudad c2 = (Ciudad) arbol.obtenerValor(destino);
        if (c1 != null && c2 != null) {
            Lista camino = mapa.caminoMasCorto(c1, c2);
            if (camino != null) {
                IO.salida("Camino más corto de " + c1.getNombre() + " a " + c2.getNombre() + ": " + camino.toString(),
                        true);
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
        while (i + 1 <= camino.longitud() && estadoIndex < 3) {
            ClaveTuberia clave = new ClaveTuberia(((Ciudad) camino.recuperar(i)).getNomenclatura(),
                    ((Ciudad) camino.recuperar(i + 1)).getNomenclatura());
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
        System.out.println("Ciudades ordenadas: " + ciudadesOrdenadas.toString());
        int longi = ciudadesOrdenadas.longitud();
        double consumoAnual;
        ConsumoAnual consumoCiudad;
        HeapMin heap = new HeapMin(longi);
        Ciudad ciudad;
        // Se recorre la lista de ciudades y se calcula el consumo anual
        for (int i = 1; i <= longi; i++) {
            ciudad = (Ciudad) ciudadesOrdenadas.recuperar(i);
            consumoAnual = ciudad.cantidadAguaPorAnio(anio);
            consumoCiudad = new ConsumoAnual(ciudad, consumoAnual);
            heap.insertar(consumoCiudad);
        }
        // Extraigo los elementos del heap y se insertan las ciudades en la lista
        // resultado
        while (!heap.esVacio()) {
            resultado.insertar(((ConsumoAnual) heap.obtenerCima()).getCiudad(), 1);
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
