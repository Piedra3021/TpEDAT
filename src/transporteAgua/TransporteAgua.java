package transporteAgua;

import conjuntistas.ArbolAVL;
import grafos.Grafo;
import jerarquicas.*;
import lineales.*;

// cargar desde archivo
// Ciudad c1 = new Ciudad("neufuen", "ne3001","12000000");
public class TransporteAgua {
    public static void main() {
        Anio a2025 = new Anio(2025);
        Ciudad c = new Ciudad("neufuen", 100);
        ArbolAVL ciudades = new ArbolAVL();

    }

    public static void genAnio(Anio a) {
        for (int i = 0; i < 12; i++) {
            a.actualizarMes(i + 1, 300);
        }
    }
    // A futuro: recibe arbolAvl por parametro y lo actualiza
    // public static void altaCiudad(Ciudad c){

    // }


}
