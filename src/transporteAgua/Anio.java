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

    public int[] getPoblacion() {
        return poblacion;
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

    @Override
    public String toString() {
        return "Anio [anio=" + anio + ", poblacion=" + Arrays.toString(poblacion) + "]";
    }

    public boolean equals(Anio anio2){
        return (this.anio == anio2.getAnio());
    }

}
