package transporteAgua;

public class Anio {

    private int anio;
    private int[] valorMes;

    public Anio(int anio) {
        this.anio = anio;
        this.valorMes = new int[12];
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int[] getValorMes() {
        return valorMes;
    }

    public void setValorMes(int[] valorMes) {
        this.valorMes = valorMes;
    }

    public boolean actualizarMes(int nMes, int valorMes) {
        boolean exito = false;
        if (nMes > 0 && nMes < 13) {
            exito = true;
            this.valorMes[nMes - 1] = valorMes;

        }
        return exito;
    }

}
