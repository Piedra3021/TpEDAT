package transporteAgua;

public class ConsumoAnual implements Comparable<ConsumoAnual> {
    private double consumo;
    private Ciudad ciudad;

    public ConsumoAnual(Ciudad ciudad, double consumo) {
        this.ciudad = ciudad;
        this.consumo = consumo;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public boolean equals(ConsumoAnual o) {
        return this.consumo == o.consumo;
    }

    public int compareTo(ConsumoAnual o) {
        return Double.compare(this.consumo, o.consumo);
    }

    @Override
    public String toString() {  
        return "ConsumoAnual{" +
                "consumo=" + consumo +
                ", ciudad=" + ciudad +
                '}';
    }
}
