package Utiles;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import conjuntistas.ArbolAVL;
import grafos.Grafo;
import transporteAgua.Ciudad;
import transporteAgua.ClaveTuberia;
import transporteAgua.DatosTuberia;
import transporteAgua.TransporteAgua;

public class IO {

    // 0-2
    // private static final int nivelLog = 2;
    // private static final String[] tab = {"", "\t", "\t\t"};

    // Escribe en pantalla un separador horizontal, con el texto pasado por
    // parámetro en el centro
    // txtSep: texto que será escrito a la mitad del separador
    public static void separador(String txtSep) {
        System.out.printf("|/-/-/-/|\t%-20S   |\\-\\-\\-\\|%n", txtSep);
    }

    // Pausa la ejecución del programa y pide al usuario que confirme su reanudación
    public static void pausador() {
        System.out.printf("Pulse ENTER para continuar");
        TecladoIn.readLine();
    }

    public static void salida(Object txt, boolean haceLog) {
        sout(txt);
        boolean opcionLog = DesdeArchivo.getProps().getProperty("logArchivo").equals("true");
        // revisar!
        if (haceLog && opcionLog) {
            try {
                PrintWriter escritor = new PrintWriter(
                        new FileOutputStream("src/Utiles/log.log", true));
                escritor.print(txt);
                escritor.println();
                escritor.close();
            } catch (Exception e) {
                System.out.println("Problema con la escritura." + e);
            }
        }
    }

    // Alias de System.out.println
    public static void sout(Object txt) {
        System.out.println(txt);
    }

    // Alias de System.out.print
    public static void print(Object txt) {
        System.out.print(txt);
    }

    public static void menu(ArbolAVL ciudades, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        int opc;

        do {
            System.out.println("1. ABM ciudades (Ej 1)");
            System.out.println("2. ABM tuberias (Ej 2)");
            System.out.println("3. Consultas ciudades (Ej 4-1)");
            System.out.println("4. Ciudades en rango (Ej 4-2)");
            System.out.println("5. Camino caudal pleno (Ej 5-1)");
            System.out.println("6. Camino mas corto (Ej 5-2)");
            System.out.println("7. Listar por consumo anual (Ej 7)");
            System.out.println("8. Mostrar sistema");
            System.out.println("0. Salir");

            opc = IO.ingresarRango("una opcion", 0, 8);
            String param1, param2;
            double n1, n2;
            int anio, mes;

            switch (opc) {
                case 1:
                    menuABM(ciudades, mapa, hMapTuberias, true);
                    break;
                case 2:
                    menuABM(ciudades, mapa, hMapTuberias, false);
                    break;
                case 3:
                    TransporteAgua.mostrarCiudad(ciudades, mapa, hMapTuberias);
                    break;
                case 4:
                    param1 = ingresarString("Nomeclatura minima");
                    param1 = param1.replace(" ", "");
                    param1 = param1.toUpperCase();
                    param2 = ingresarString("Nomeclatura maxima");
                    param2 = param2.replace(" ", "");
                    param2 = param2.toUpperCase();
                    IO.sout("Ingrese el anio");
                    anio = TecladoIn.readInt();
                    mes = ingresarRango("el mes", 1, 12);
                    n1 = (double) ingresarRango("minVol", 0, 99999);
                    n2 = (double) ingresarRango("maxVol", 0, 99999);
                    TransporteAgua.ciudadesEnRango(ciudades, param1, param2, anio, mes, n1, n2);
                    break;
                case 5:
                    TransporteAgua.caminoCaudalPleno(ciudades, mapa, hMapTuberias);
                    break;
                case 6:
                    param1 = ingresarString("desde");
                    param1 = param1.replace(" ", "");
                    param1 = param1.toUpperCase();
                    param2 = ingresarString("hasta");
                    param2 = param2.replace(" ", "");
                    param2 = param2.toUpperCase();
                    TransporteAgua.caminoMasCorto(ciudades, mapa, param1, param2, hMapTuberias);
                    break;
                case 7:
                    anio = ingresarRango("anio", 1900, 2100);
                    TransporteAgua.listarPorConsumoAnual(ciudades, anio);
                    break;
                case 8:
                    TransporteAgua.mostrarSistema(ciudades, mapa, hMapTuberias);
                    break;
                case 0:
                    System.out.println("Fin de programa");
                    break;
                default:
                    System.out.println("Opción indefinida");
                    break;
            }
            pausador();
        } while (opc != 0);

    }

    public static void menuABM(ArbolAVL ciudades, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            boolean menuDeCiudades) {
        int opc;
        String tipoMenu = menuDeCiudades ? "ciudad" : "tuberia";
        Ciudad c;
        do {
            System.out.println("  1. Alta " + tipoMenu);
            System.out.println("  2. Modificar " + tipoMenu);
            System.out.println("  3. Baja " + tipoMenu);
            System.out.println("  0. Salir");

            opc = IO.ingresarRango("una opcion", 0, 3);
            c = null;
            String param1;

            switch (opc) {
                case 1:
                    if (menuDeCiudades) {
                        c = ingresarCiudad(ciudades);
                        TransporteAgua.altaCiudad(ciudades, mapa, c);
                    } else {
                        String[] valores = new String[7];
                        valores[1] = ingresarString("desde");
                        valores[1] = valores[1].replace(" ", "");
                        valores[1] = valores[1].toUpperCase();
                        valores[2] = ingresarString("hasta");
                        valores[2] = valores[2].replace(" ", "");
                        valores[2] = valores[2].toUpperCase();
                        DatosTuberia dt = ingresarDatosTuberia(valores);
                        TransporteAgua.altaTuberia(ciudades, mapa, hMapTuberias, valores[1], valores[2], dt);
                    }
                    break;
                case 2:
                    if (menuDeCiudades) {
                        param1 = ingresarString("nombre de ciudad. (MOD)");
                        param1 = param1.replace(" ", "");
                        param1 = param1.toUpperCase();
                        TransporteAgua.modCiudad(ciudades, param1);
                    } else {
                        String[] valores = new String[7];
                        valores[1] = ingresarString("desde");
                        valores[1] = valores[1].replace(" ", "");
                        valores[1] = valores[1].toUpperCase();
                        valores[2] = ingresarString("hasta");
                        valores[2] = valores[2].replace(" ", "");
                        valores[2] = valores[2].toUpperCase();
                        TransporteAgua.modTuberia(ciudades, mapa, hMapTuberias, valores[1],valores[2]);
                    }
                    break;
                case 3:
                    if (menuDeCiudades) {
                        param1 = ingresarString("nombre de ciudad. (BAJA)");
                        param1 = param1.replace(" ", "");
                        param1 = param1.toUpperCase();
                        TransporteAgua.bajaCiudad(ciudades, hMapTuberias, param1, mapa);
                    } else {
                        String[] valores = new String[7];
                        valores[1] = ingresarString("desde");
                        valores[1] = valores[1].replace(" ", "");
                        valores[1] = valores[1].toUpperCase();
                        valores[2] = ingresarString("hasta");
                        valores[2] = valores[2].replace(" ", "");
                        valores[2] = valores[2].toUpperCase();
                        TransporteAgua.bajaTuberia(ciudades, mapa, hMapTuberias, valores[1], valores[2]);
                    }
                    break;
                case 0:
                    System.out.println("  Fin de MenuABM");
                    break;
                default:
                    System.out.println("  Opción indefinida");
                    break;
            }
        } while (opc != 0);
    }

    public static Ciudad ingresarCiudad(ArbolAVL ciudades) {
        Ciudad c = null;
        String[] valores = new String[4];
        IO.salida("Ingresar ciudad. (ALTA)", false);
        valores[1] = ingresarString("Nombre");
        valores[2] = ingresarString("Superficie en mt.");
        valores[3] = ingresarString("Consumo promedio.");
        c = DesdeArchivo.genCiudad(valores);
        return c;
    }

    public static DatosTuberia ingresarDatosTuberia(String[] valores) {
        DatosTuberia dt;
        IO.salida("Ingresar DatosTuberia. (ALTA)", false);
        valores[3] = ingresarString("caudalMin");
        valores[4] = ingresarString("caudalMax");
        valores[5] = ingresarString("diametro");
        do {
        valores[6] = ingresarString("estado  \n" + //
                        "ACTIVO\n" + //
                        "REPARACION\n" + //
                        "INACTIVO\n" + //
                        "DISEÑO");
        valores[6] = valores[6].toLowerCase();
}while(valores[6].charAt(0) != 'a' && valores[6].charAt(0) !='r' && valores[6].charAt(0) != 'i' && valores[6].charAt(0) != 'd');
        dt = DesdeArchivo.genDatosTuberia(valores);
        return dt;
    }

    public static int ingresarRango(String nombreDato, int min, int max) {
        int entrada;

        do {
            sout("Ingrese " + nombreDato + "("
                    + min + "-" + max + ")\n");
            entrada = TecladoIn.readInt();
        } while ((entrada < min) || (entrada > max));

        return entrada;
    }

    public static int ingresarRango(int min, int max) {
        int entrada;

        do {
            sout("Ingrese un valor en el rango("
                    + min + "-" + max + ")\n");
            entrada = TecladoIn.readLineShort();
        } while ((entrada < min) || (entrada > max));

        return entrada;
    }

    public static String ingresarString(String nombreDato) {
        String s;
        do {
            sout("Ingrese " + nombreDato + "\n");
            s = TecladoIn.readLine();
        } while (s.length() < 1);

        return s.trim();
    }

    // public static void log(int nivel, boolean exito, String metodo, String extra)
    // {
    // log(nivel, exito, metodo + "|" + extra);
    //
    // }
    //
    // public static void log(int nivel, boolean exito, String metodo) {
    // if (nivel <= nivelLog) {
    // System.out.println(tab[nivel] + exito + "! " + metodo);
    //
    // }
    // }
    //
    // public static void log(int nivel, String metodo, Object extra) {
    // log(nivel, metodo + "|" + extra);
    // }
    //
    // public static void log(int nivel, String metodo) {
    // if (nivel <= nivelLog) {
    // System.out.println(metodo);
    // }
    // }
}
