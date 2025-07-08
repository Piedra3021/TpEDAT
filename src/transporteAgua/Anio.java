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

    public int[] getValorMes() {
        return poblacion;
    }

    public void setValorMes(int[] poblacion) {
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

    @Override
    public String toString() {
        return "Anio [anio=" + anio + ", poblacion=" + Arrays.toString(poblacion) + "]";
    }

}
