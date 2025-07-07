package transporteAgua;

import java.util.Arrays;

public class Anio {

    private int anio;
    private double[] valorMes;

    public Anio(int anio) {
        this.anio = anio;
        this.valorMes = new double[12];
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double[] getValorMes() {
        return valorMes;
    }

    public void setValorMes(double[] valorMes) {
        this.valorMes = valorMes;
    }

    public boolean actualizarMes(int nMes, double valorMes) {
        boolean exito = false;
        if (nMes > 0 && nMes < 13) {
            exito = true;
            this.valorMes[nMes - 1] = valorMes;

        }
        return exito;
    }

    @Override
    public String toString() {
        return "Anio [anio=" + anio + ", valorMes=" + Arrays.toString(valorMes) + "]";
    }


}
