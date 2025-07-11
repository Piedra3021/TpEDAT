package Utiles;

import transporteAgua.*;

import java.util.HashMap;
import conjuntistas.ArbolAVL;
import grafos.Grafo;
import jerarquicas.*;
// import lineales.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Podemos usar una instancia de la clase Scanner para poder leer datos de forma
 * más comoda que con un Reader "pelado". Recordamos que un reader solo nos
 * provee operaciones para leer o un byte o una linea completa (hasta el
 * siguiente \n o \r ).
 *
 * Scanner nos permite encapsular un objeto Reader, un Stream (como System.in) o
 * un String y obtener a partir de ese objeto secuencialmente datos de tipos
 * primitivos o incluso substrings separados sin que necesitemos controlar
 * manualmente como se reconocen esos datos.
 *
 * https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 *
 * @author jpinero
 */
public class DesdeArchivo {

    // static final int MAX_VALOR = 1000000;
    // static final String NOMBRE_ARCHIVO =
    // "src/main/java/Utiles/ListaDesaprobados.txt";
    static final String DELIMITER = ",";

    public static void cargarCiudades(ArbolAVL arbol) {
        IO.salida("INI cargaCiudades", false);
        String line;
        String PATH = "src/Utiles/ciu_prod.csv";
        Ciudad nuevaCiudad;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(DELIMITER);
                if (valores[0].equals("c")) {
                    nuevaCiudad = genCiudad(valores);
                    TransporteAgua.altaCiudad(arbol, nuevaCiudad);
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    // parsea arreglo de parametros
    private static Ciudad genCiudad(String[] valores) {
        Ciudad c;
        String nombre = valores[1];
        double metros = Double.parseDouble(valores[2]);
        // int poblacion = 1000;
        double consumoPromedio = Double.parseDouble(valores[3]);
        c = new Ciudad(nombre, metros, consumoPromedio);

        return c;
    }

    public static void cargarTuberias(ArbolAVL ciudades, HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("INI cargaTuberias", false);
        String line;
        String PATH = "src/Utiles/tub_dev.csv";
        ClaveTuberia cT;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                Object[] valores = line.split(DELIMITER);
                DatosTuberia dT;
                if (valores[0].equals("t")) {
                    cT = genTuberia(valores, ciudades, hMapTuberias);
                    dT = new DatosTuberia((double) valores[3], (double) valores[4], (double) valores[5],
                            (char) valores[6]);

                    TransporteAgua.altaTuberia(ciudades, hMapTuberias, cT, dT);
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    private static ClaveTuberia genTuberia(Object[] valores, ArbolAVL ciudades,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        ClaveTuberia cT = null;
        Ciudad c1 = (Ciudad) ciudades.obtenerValor((String) valores[1]);
        Ciudad c2 = (Ciudad) ciudades.obtenerValor((String) valores[2]);
        if (c1 != null && c2 != null) {
            cT = new ClaveTuberia(c1.getNomenclatura(), c2.getNomenclatura());
        }

        return cT;

    }

    public static void cargarPoblacion(ArbolAVL arbol) {
        IO.salida("INI cargaPoblacion", false);
        String line;
        String PATH = "src/Utiles/pob_dev.csv";
        Ciudad ciudad;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((line = br.readLine()) != null) {
                Object[] valores = line.split(DELIMITER);
                if (valores[0].equals("p")) {
                    ciudad = (Ciudad) arbol.obtenerValor((Comparable) valores[1]);
                    if (ciudad != null) {
                        ciudad.setPoblacion((int) valores[2], (int) valores[3], (int) valores[4]);
                        // nuevaCiudad = genPoblacion(valores);
                        // TransporteAgua.altaCiudad(arbol, nuevaCiudad);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    public static void main(String[] args) {
        // leerArchivo_1();
        // leerArchivo_2();
        // cargarCiudad();
    }
}
