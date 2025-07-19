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

            opc = IO.ingresarRango(0, 9);
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
                    break;
                case 4:
                    param1 = ingresarString("Nomeclatura minima");
                    param2 = ingresarString("Nomeclatura maxima");
                    IO.sout("Ingrese el anio");
                    anio = TecladoIn.readInt();
                    IO.sout("Ingrese el mes");
                    mes = TecladoIn.readInt();
                    IO.sout("Ingrese minVol");
                    n1 = (double) ingresarRango(0, 99999);
                    IO.sout("Ingrese maxVol");
                    n1 = (double) ingresarRango(0, 99999);
                    n2 = (double) ingresarRango(0, 99999);
                    TransporteAgua.ciudadesEnRango(ciudades, param1, param2, anio, mes, n1, n2);
                    break;
                case 5:
                    break;
                case 6:

                    TransporteAgua.caminoMasCorto(ciudades, mapa, null, null, hMapTuberias);
                    break;
                case 7:
                    break;
                case 8:
                    TransporteAgua.mostrarSistema(ciudades, mapa, hMapTuberias);
                    break;
                case 9:
                    break;
                case 0:
                    System.out.println("Fin de programa");
                    break;
                default:
                    System.out.println("Opción indefinida");
                    break;
            }
        } while (opc != 0);

    }

    public static void menuABM(ArbolAVL ciudades, Grafo mapa, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias,
            boolean menuDeCiudades) {
        int opc;
        String tipoMenu = menuDeCiudades ? "ciudadades" : "tuberias";
        Ciudad c;
        do {
            System.out.println("1. Alta " + tipoMenu);
            System.out.println("2. Modificar " + tipoMenu);
            System.out.println("1. Baja " + tipoMenu);
            System.out.println("0. Salir");

            opc = IO.ingresarRango(0, 3);
            c = null;
            String param1, param2;

            switch (opc) {
                case 1:
                    if (menuDeCiudades) {
                        c = ingresarCiudad(ciudades);
                        TransporteAgua.altaCiudad(ciudades, mapa, c);
                    } else {
                        param1 = ingresarString("Desde");
                        param2 = ingresarString("Hasta");
                        // dt = ingresarDatosTuberia()
                        // TransporteAgua.altaTuberia(ciudades, mapa, hMapTuberias, param1, param2,
                        // dt);
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Fin de MenuABM");
                    break;
                default:
                    System.out.println("Opción indefinida");
                    break;
            }
        } while (opc != 0);
    }

    public static Ciudad ingresarCiudad(ArbolAVL ciudades) {
        Ciudad c = null;
        String[] valores = new String[3];
        IO.salida("Ingresar ciudad", false);
        valores[0] = ingresarString("Nombre");
        valores[1] = ingresarString("Superficie en mt.");
        valores[2] = ingresarString("Consumo promedio.");
        // valores[1] = (double) ingresarRango(0, 99999999);
        // valores[2]= (double) ingresarRango(0, 99999999);
        c = DesdeArchivo.genCiudad(valores);
        return c;
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
