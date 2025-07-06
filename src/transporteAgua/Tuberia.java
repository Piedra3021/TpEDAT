package transporteAgua;

public class Tuberia {
    private String nomenclatura;
    private double caudalMin;
    private double caudalMax;
    private double diametro;
    private char estado;

    public Tuberia(String nomenclaruta, double caudalMin, double caudalMax, double diametro, char estado) {
        this.nomenclatura = nomenclatura;
        this.caudalMin = caudalMin;
        this.caudalMax = caudalMax;
        this.diametro = diametro;
        this.estado = estado;
    }

    public Tuberia(String nomenclatura) {
        this.nomenclatura = nomenclatura;
        this.caudalMin = 0.0;
        this.caudalMax = 0.0;
        this.diametro = 0.0;
        this.estado = 'A'; // Estado por defecto 'A' (Activo)
    }

    public String getNomenclatura() {
        return nomenclatura;
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

    public String toString() {
        return "Tuberia{" +
                "nomenclaruta='" + nomenclaruta + '\'' +
                ", caudalMin=" + caudalMin +
                ", caudalMax=" + caudalMax +
                ", diametro=" + diametro +
                ", estado=" + estado +
                '}';
    }
}