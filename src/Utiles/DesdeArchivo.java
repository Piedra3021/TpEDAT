package Utiles;

import transporteAgua.*;

import java.util.HashMap;
import java.util.Properties;

import conjuntistas.ArbolAVL;
import grafos.Grafo;
import jerarquicas.*;
// import lineales.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.Normalizer;

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

    public static void cargarCiudades(ArbolAVL arbol, Grafo grafo) {
        IO.salida("INI cargaCiudades", false);
        String sufijo = getProps().getProperty("ciu");
        // IO.sout(sufijo);
        String line;
        String PATH = "src/Utiles/ciu_" + sufijo + ".csv";
        Ciudad nuevaCiudad;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(DELIMITER);
                if (valores[0].equals("c")) {
                    nuevaCiudad = genCiudad(valores);
                    TransporteAgua.altaCiudad(arbol, grafo, nuevaCiudad);
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    public static String quitarAcentos(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return patron.matcher(normalizado).replaceAll("");
    }

    // parsea arreglo de parametros
    public static Ciudad genCiudad(String[] valores) {
        Ciudad c;
        String nombre = quitarAcentos(valores[1].replaceAll("\\s+", ""));
        double metros = Double.parseDouble(valores[2]);
        double consumoPromedio = Double.parseDouble(valores[3]);
        c = new Ciudad(nombre, metros, consumoPromedio);

        return c;
    }

    public static void cargarTuberias(ArbolAVL ciudades, Grafo grafo,
            HashMap<ClaveTuberia, DatosTuberia> hMapTuberias) {
        IO.salida("INI cargaTuberias", false);
        String sufijo = getProps().getProperty("tub");
        // IO.sout(sufijo);
        String line;
        String PATH = "src/Utiles/tub_" + sufijo + ".csv";
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(DELIMITER);
                DatosTuberia dT;
                if (valores[0].equals("t")) {
                    valores[1] = quitarAcentos(valores[1].replaceAll("\\s+", ""));
                    valores[2] = quitarAcentos(valores[2].replaceAll("\\s+", ""));
                    dT = genDatosTuberia(valores);
                    TransporteAgua.altaTuberia(ciudades, grafo, hMapTuberias, valores[1], valores[2], dT);
                }
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del "
                    + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    public static DatosTuberia genDatosTuberia(String[] valores) {
        DatosTuberia dT = null;
        double caudalMin = Double.parseDouble(valores[3]);
        double caudalMax = Double.parseDouble(valores[4]);
        double diametro = Double.parseDouble(valores[5]);
        char estado = valores[6].charAt(0);
        dT = new DatosTuberia(caudalMin, caudalMax, diametro, estado);
        return dT;
    }

    public static void cargarPoblacion(ArbolAVL arbol) {
        IO.salida("INI cargaPoblacion", false);
        String line;
        String sufijo = getProps().getProperty("pob");
        // IO.sout(sufijo);
        String PATH = "src/Utiles/pob_" + sufijo + ".csv";
        Ciudad ciudad;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(DELIMITER);
                if (valores[0].equals("c")) {
                    valores[1] = quitarAcentos(valores[1].replaceAll("\\s+", ""));
                    String clean = valores[1].replace(" ", "");
                    ciudad = (Ciudad) arbol.obtenerValor(clean.toUpperCase());
                    if (ciudad != null) {
                        ciudad.setPoblacion(Integer.parseInt(valores[2]), Integer.parseInt(valores[3]),
                                Integer.parseInt(valores[4]));
                        // nuevaCiudad = genPoblacion(valores);
                        // TransporteAgua.altaCiudad(arbol, nuevaCiudad);
                    } else {
                        System.out.println("Ciudad no encontrada: " + valores[1]);
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

    public static Properties getProps() {
        Properties props = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/Utiles/app.properties");
            props.load(fis);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return props;
    }

    public static void main(String[] args) {
        // leerArchivo_1();
        // leerArchivo_2();
        // cargarCiudad();
    }
}
