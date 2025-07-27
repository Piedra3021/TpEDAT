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
        IO.menu(ciudades, grafo, hMapTuberias);
    }

    public static void genAnio(Anio a) {
        for (int i = 0; i < 12; i++) {
            a.actualizarMes(i + 1, 300);
        }
    }

    public static void altaCiudad(ArbolAVL arbol, Grafo grafo, Ciudad nuevaCiudad) {
        IO.salida("ALTA ciudad." + nuevaCiudad.toString(), true);
        // Cortar ejecucion si falla insercion?
        boolean exitoArbol = arbol.insertar(nuevaCiudad.getNombre().toUpperCase(), nuevaCiudad);
        boolean exitoGrafo = grafo.insertarVertice(nuevaCiudad);
        if (!(exitoArbol && exitoGrafo)) {
            IO.salida("\texitoArbol: " + exitoArbol + ". Grafo: " + exitoGrafo, true);
        }
    }

    /*
     * public static void modCiudad(ArbolAVL arbolNombres, HashMap<String, Ciudad>
     * hmapCiudades, String nCiudadNuevo,
     * double metros) {
     * IO.salida("MOD ciudad", true);
     * }
     */
    public static void modCiudad(ArbolAVL ciudades, String nCiudad) {
        Ciudad ciudad = (Ciudad) ciudades.obtenerValor(nCiudad.toUpperCase());
        if (ciudad != null) {
            int opc;
            do {
                IO.salida("\t1. modificar poblacion (en un año y mes).", false);
                IO.salida("\t2. modificar consumoProm (?)", false);
                IO.salida("\t3. modificar metros de la ciudad.", false);
                IO.salida("\t0. Salir menuMOD", false);
                opc = IO.ingresarRango(0, 3);
                switch (opc) {
                    case 1:
                        IO.salida("\tInserte el año", false);
                        int anio = TecladoIn.readInt();
                        IO.salida("\tInserte el mes (1-12)", false);
                        int mes = TecladoIn.readInt();
                        IO.salida("\tInserte la poblacion", false);
                        int poblacion = TecladoIn.readInt();
                        ciudad.setPoblacion(anio, mes, poblacion);
                        IO.salida("\tMOD poblacion: " + nCiudad + ". Exito", true);
                        break;

                    case 2:
                        IO.salida("\tInserte el nuevo consumoPromedio", false);
                        double consumo = TecladoIn.readDouble();
                        ciudad.setConsumoProm(consumo);
                        IO.salida("\tMOD consumo: " + nCiudad + ". Exito", true);
                        break;

                    case 3:
                        IO.salida("\tIngrese la nueva cantidad de metros", false);
                        double metros = TecladoIn.readDouble();
                        ciudad.setMetros(metros);
                        IO.salida("\tMOD superficie: " + nCiudad + ". Exito", true);
                        break;

                    case 0:
                        System.out.println("\tFin de MenuMOD");
                        break;
                    default:
                        System.out.println("\tOpción indefinida");
                        break;
                }

            } while (opc != 0);
        } else {
            IO.salida("No se insertó una ciudad valida.", false);
        }
    }

    public static boolean bajaCiudad(ArbolAVL arbolNombres,
            HashMap<ClaveTuberia, DatosTuberia> hMapTub, String nCiudad,
            Grafo grafo) {
        IO.salida("BAJA ciudad: " + nCiudad, false);
        boolean exito = false;
        Ciudad c = (Ciudad) arbolNombres.obtenerValor(nCiudad);

        if (c != null) {
            // Si encuentra la tuberia, la elimina de las estructuras
            exito = arbolNombres.eliminar(nCiudad);
            if (exito) {
                IO.salida("BAJA ciudad: " + nCiudad + ". Exito", true);
                grafo.eliminarVertice(c);
                // En cada elemento del HashMap, se verifica si la clave
                // contiene la nomenclatura de la ciudad
                hMapTub.entrySet().removeIf(entry -> entry.getKey().getNom1().equals(c.getNomenclatura()) ||
                        entry.getKey().getNom2().equals(c.getNomenclatura()));
            }
        } else {
            IO.salida("\tNo se pudo eliminar la ciudad: no existe " + nCiudad, true);
        }

        return exito;
    }

    public static void altaTuberia(ArbolAVL ciudades, Grafo grafo,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta, DatosTuberia datosTuberia) {
        IO.salida("ALTA tuberia: " + desde + "->" + hasta, false);
        desde = desde.replace(" ", "");
        hasta = hasta.replace(" ", "");
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde.toUpperCase());
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta.toUpperCase());
        // Verifica que las ciudades existan y que no haya un arco entre ellas
        if (c1 != null && c2 != null && !c1.equals(c2) && !grafo.existeArco(c1, c2)) {
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            hMapTuberias.put(clave, datosTuberia);
            grafo.insertarArco(c1, c2, datosTuberia.getCaudalMax());
            IO.salida("ALTA tuberia: " + desde + "->" + hasta + ". Exito", true);
        } else {
            String mensaje = "\tNo se pudo agregar la tubería: ";
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
     * IO.salida("MOD tuberia: ", true);
     * }
     */

    public static void modTuberia(ArbolAVL ciudades, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta) {
        IO.salida("Modificacion tuberia: " + desde + "->" + hasta, false);
        boolean exito = false;
        desde = desde.replace(" ", "");
        hasta = hasta.replace(" ", "");
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde.toUpperCase());
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta.toUpperCase());

        if (c1 != null && c2 != null && mapa.existeArco(c1, c2)) {
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            DatosTuberia datos = hMapTuberias.get(clave);
            int opc;
            do {
                IO.salida("1. Editar caudal minimo", false);
                IO.salida("2. Editar caudal maximo", false);
                IO.salida("3. Editar diametro", false);
                IO.salida("4. Editar estado", false);
                IO.salida("0. Salir", false);
                opc = IO.ingresarRango(0, 4);

                switch (opc) {
                    case 1:
                        IO.salida("Ingrese el nuevo caudal minimo", false);
                        double minimo = TecladoIn.readDouble();
                        datos.setCaudalMin(minimo);
                        break;
                    case 2:
                        IO.salida("Ingrese el nuevo caudal maximo", false);
                        double maximo = TecladoIn.readDouble();
                        datos.setCaudalMax(maximo);
                        mapa.eliminarArco(c1, c2);
                        mapa.insertarArco(c1, c2, maximo);
                        break;
                    case 3:
                        IO.salida("Ingrese el nuevo diametro", false);
                        double diametro = TecladoIn.readDouble();
                        datos.setDiametro(diametro);
                    case 4:
                        IO.salida("Ingrese el estado. \nACTIVO\nREPARACION\nINACTIVO\nDISEÑO", false);
                        char estado = TecladoIn.readLine().toLowerCase().charAt(0);
                        while (estado != 'a' && estado != 'r' && estado != 'i' && estado != 'd') {
                            IO.salida("Ingrese una opcion valida. \n" + //
                                    "ACTIVO\n" + //
                                    "REPARACION\n" + //
                                    "INACTIVO\n" + //
                                    "DISEÑO", false);
                            estado = TecladoIn.readLine().toLowerCase().charAt(0);
                        }
                        datos.setEstado(estado);
                        break;
                    default:
                        IO.salida("Ingrese una opcion valida.", false);
                        break;
                }
                if (opc >= 1 && opc <= 4) {
                    IO.salida("Se modifico con exito!", false);
                }

            } while (opc != 0);
        } else if (c1 == null || c2 == null) {
            IO.salida("Una o ambas ciudades no existe", false);
        } else {
            IO.salida("No existe una tuberia entre las ciudades", false);
        }
    }

    public static boolean bajaTuberia(ArbolAVL ciudades, Grafo grafo, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            String desde, String hasta) {
        IO.salida("BAJA tuberia: " + desde + "->" + hasta, false);
        boolean exito = false;
        desde = desde.replace(" ", "");
        hasta = hasta.replace(" ", "");
        Ciudad c1 = (Ciudad) ciudades.obtenerValor(desde.toUpperCase());
        Ciudad c2 = (Ciudad) ciudades.obtenerValor(hasta.toUpperCase());
        if (c1 != null && c2 != null && grafo.existeArco(c1, c2)) {
            // Si la tuberia existe, se elimina de las estructuras
            ClaveTuberia clave = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
            hMapTuberias.remove(clave);
            grafo.eliminarArco(c1, c2);
            exito = true;
            IO.salida("BAJA tuberia: " + desde + "->" + hasta + ". Exito", true);
        } else {
            String mensaje = "\tNo se pudo eliminar la tubería: ";
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
            HashMap<ClaveTuberia, DatosTuberia> hmapTuberias, String nCiudad, int anio, int mes) {
        nCiudad = nCiudad.replace(" ", "");
        Ciudad ciudad = (Ciudad) ciudades.obtenerValor(nCiudad.toUpperCase());
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
    public static void mostrarCiudad(ArbolAVL arbol, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberia,
            String nCiudad) {
        IO.salida("INI mostrarCiudad :" + nCiudad, false);
        // Ciudad c = leerCiudad(arbol);
        Ciudad c = (Ciudad) arbol.obtenerValor(nCiudad.toUpperCase());
        // validar ciudad?
        if (c != null) {
            IO.salida("Inserte el anio", false);
            int anio = TecladoIn.readInt();
            IO.salida("Inserte el mes", false);
            int mes = TecladoIn.readInt();
            // Leer anio y mes por teclado? usar ultimo elemento del elemento anio?
            // getPoblacionUltimoMes()?
            int pobActual = c.getPoblacion(anio, mes);
            IO.salida("=== Informe de distribución de agua ===", false);
            IO.salida("Ciudad: " + c.toString(), false);
            IO.salida("Mes/Año: " + String.format("%02d", mes) + "/" + anio + "\n", false);
            if (pobActual != -1) {
                IO.salida("Cantidad de habitantes registrados: " + pobActual + "\n", false);
                Lista caminoA = mapa.obtenerPrimerActivo(c, hMapTuberia);
                caminoA = mapa.obtenerEtiquetasCamino(caminoA);
                IO.salida("Volumen de agua calculado por cantidad de habitantes: "
                        + c.cantidadAguaPorMes(anio, mes) + " m³", false);
                IO.salida("Volumen de agua calculado por caudal disponible:     "
                        + mapa.obtenerMenorEtiqueta(caminoA) + " m³\n", false);
                IO.salida(">> Volumen efectivamente aprovisionado: "
                        + TransporteAgua.obtenerAguaAprovisionada(arbol, mapa, hMapTuberia, nCiudad, anio, mes) + " m³",
                        false);

            } else {
                IO.salida("No se tienen datos de poblacion de la ciudad en este anio/mes", false);
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
        } else {
            IO.salida("No se ingreso una ciudad valida.", false);
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

        IO.salida("Las ciudades en el rango son: \n" + resultado, false);
        IO.salida("FIN ciudadesEnRango", false);
        return resultado;
    }

    // Ej. 5-1
    public static Lista caminoCaudalPleno(ArbolAVL arbol, Grafo mapa,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias, String cOrigen, String cDestino) {
        IO.salida("INI caminoCaudalPleno", false);

        double caudalMin = -1;
        double caudalAux;
        int posCamino = -1;
        Lista caminoAct = new Lista();
        Ciudad ciudadO = (Ciudad) arbol.obtenerValor(cOrigen);
        Ciudad ciudadD = (Ciudad) arbol.obtenerValor(cDestino);
        Lista caminos = mapa.obtenerTodosCaminos(ciudadO, ciudadD);

        for (int i = 1; i < caminos.longitud() + 1; i++) {
            caminoAct = (Lista) caminos.recuperar(i);
            caminoAct = mapa.obtenerEtiquetasCamino(caminoAct);
            caudalAux = caudalMin = mapa.obtenerMenorEtiqueta(caminoAct);

            if (i == 1) {
                caudalMin = caudalAux;
                posCamino = i;
            } else {
                if (caudalMin > caudalAux) {
                    caudalMin = caudalAux;
                    posCamino = i;
                }
            }

        }

        if (posCamino != -1) {
            caminoAct = (Lista) caminos.recuperar(posCamino);
            IO.salida("El camino es: \n" + caminoAct, false);
            String estado = definirEstadoCamino(caminoAct, hMapTuberias);
            IO.salida(estado, false);
        } else if (ciudadO == null || ciudadD == null) {
            IO.salida("Una o ambas ciudades no existen.", false);
        } else {
            IO.salida("Las ciudades no estan conectadas.", false);
        }

        // ...
        // leer c1 y c2
        // obtener camino con maximos menores?
        // mostrar estado del camino
        IO.salida("FIN caminoCaudalPleno", false);
        return caminoAct;
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
        } else {
            IO.salida("Una o ambas ciudades no existen.", false);
        }
        IO.salida("FIN caminoMasCorto", false);
    }

    public static String definirEstadoCamino(Lista camino, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
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
        IO.salida("Ordenada por consumo: \n" + resultado, false);
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
