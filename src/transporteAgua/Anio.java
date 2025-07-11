package transporteAgua;

import java.util.Arrays;

public class Anio {

    private int anio;
    private int[] poblacion;

    public Anio(int anio) {
        this.anio = anio;
        this.poblacion = new int[12];
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPoblacionAnual() {
        int cant = 0;
        for(int i=0; i<12;i++){
            cant = cant +  this.poblacion[i];
        }
        return cant;
    }

    public void setPoblacion(int[] poblacion) {
        this.poblacion = poblacion;
    }

    public boolean actualizarMes(int nMes, int poblacion) {
        boolean exito = false;
        if (nMes > 0 && nMes < 13) {
            exito = true;
            this.poblacion[nMes - 1] = poblacion;

        }
        return exito;
    }

    public int getPoblacionMes(int mes){
        int cantidad = -1;
        if(mes > 0 && mes < 13){
            cantidad = this.poblacion[mes-1];
        }
        return cantidad;
    }

    public int obtenerDiasDelMes(int mes) {
        int cant;
    switch (mes) {
        case 1:  // Enero
        case 3:  // Marzo
        case 5:  // Mayo
        case 7:  // Julio
        case 8:  // Agosto
        case 10: // Octubre
        case 12: // Diciembre
            cant = 31;
        case 4:  // Abril
        case 6:  // Junio
        case 9:  // Septiembre
        case 11: // Noviembre
            cant = 30;
        case 2:  // Febrero
            if(esBisiesto(this.anio)){
                cant = 29;
            }else{
                cant = 28;
            }
        default:
            cant = -1;
    }
    return cant;
}

    public boolean esBisiesto(int anio) {
    return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
}

    @Override
    public String toString() {
        return "Anio [anio=" + anio + ", poblacion=" + Arrays.toString(poblacion) + "]";
    }

    @Override
    public boolean equals(Object anio2){
        return (this.anio == ((int) anio2));
    }

}
