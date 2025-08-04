package transporteAgua;

import java.util.HashMap;

public class DatosTuberia {
    private double caudalMin;
    private double caudalMax;
    private double diametro;
    private char estado;

    public DatosTuberia(double caudalMin, double caudalMax, double diametro, char estado) {
        this.caudalMin = caudalMin;
        this.caudalMax = caudalMax;
        this.diametro = diametro;
        this.estado = estado;
    }

    //metodo para obtener unos datos de una tuberia especifica
    public static DatosTuberia obtenerDatos(HashMap hash, String desde, String hasta) {
        //desde y hasta son las nomenclaturas de las ciudades
        return (DatosTuberia) hash.get(new ClaveTuberia(desde, hasta));
    }

    public double getCaudalMin() {
        return caudalMin;
    }

    public double getCaudalMax() {
        return caudalMax;
    }

    public double getDiametro() {
        return diametro;
    }

    public char getEstado() {
        return estado;
    }

    public void setCaudalMin(double caudalMin) {
        this.caudalMin = caudalMin;
    }

    public void setCaudalMax(double caudalMax) {
        this.caudalMax = caudalMax;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DT [caudalMin=" + caudalMin + ", caudalMax=" + caudalMax
                + ", diametro=" + diametro + ", estado=" + estado + "]";
    }

}