package Utiles;

public class IO {

    // 0-2
    // private static final int nivelLog = 2;
//    private static final String[] tab = {"", "\t", "\t\t"};

    //Escribe en pantalla un separador horizontal, con el texto pasado por parámetro en el centro
    //txtSep: texto que será escrito a la mitad del separador
    public static void separador(String txtSep) {
        System.out.printf("|/-/-/-/|\t%-20S   |\\-\\-\\-\\|%n", txtSep);
    }

    //Pausa la ejecución del programa y pide al usuario que confirme su reanudación
    public static void pausador() {
        System.out.printf("Pulse ENTER para continuar");
        TecladoIn.readLine();
    }

    public static void salida(Object txt, boolean haceLog){
        sout(txt);
        // revisar!
//        if (loguearMensaje) {
//            try {
//                PrintWriter escritor = new PrintWriter(
//                        new FileOutputStream("src/tpo/archivos/log.log", true));
//                escritor.print(mensajeSalida);
//                escritor.println();
//                escritor.close();
//            } catch (Exception e) {
//                System.out.println("Problema con la escritura." + e);
//            }
//        }
    }
    //Alias de System.out.println
    public static void sout(Object txt) {
        System.out.println(txt);
    }

    //Alias de System.out.print
    public static void print(Object txt) {
        System.out.print(txt);
    }

    public static void menu() {
        int opc;

        do {
            System.out.println("1. ABM ...");
            System.out.println("2. ABM ...");
//            System.out.println("9. Mostrar sistema");
            System.out.println("0. Salir");

            opc = IO.ingresarRango(0, 9);

            switch (opc) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
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

//    public static void log(int nivel, boolean exito, String metodo, String extra) {
//        log(nivel, exito, metodo + "|" + extra);
//
//    }
//
//    public static void log(int nivel, boolean exito, String metodo) {
//        if (nivel <= nivelLog) {
//            System.out.println(tab[nivel] + exito + "! " + metodo);
//
//        }
//    }
//
//    public static void log(int nivel, String metodo, Object extra) {
//        log(nivel, metodo + "|" + extra);
//    }
//
//    public static void log(int nivel, String metodo) {
//        if (nivel <= nivelLog) {
//            System.out.println(metodo);
//        }
//    }
}
