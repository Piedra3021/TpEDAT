package transporteAgua;

public class Ciudad {
    private String nombre;
    private String nomenclatura;
    private double metros;
    private int cantHabitantes = 1000;
    private double consumoPromedio = 0.25;
    // Arreglo de anios?
    private Anio consumoProm;
    //numNomenclatura?
    private static int codigoNumerico = 3000;

    public Ciudad(String nombreC, double metros) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = metros;
        this.consumoProm = new Anio(2025);
        codigoNumerico = (codigoNumerico+1)%4000;
    }

    private String obtenerNomenclatura(String nombre){
        String cadena = "";
        if(nombre.length() > 2){
            cadena = nombre.substring(0,2).toUpperCase() + codigoNumerico;
        }

        return cadena;
    }
    public String getNombre() {
        return nombre;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public double getMetros() {
        return metros;
    }

    public int getCantHabitantes() {
        return cantHabitantes;
    }

    public double getConsumoPromedio() {
        return consumoPromedio;
    }

    public Anio getConsumoProm() {
        return consumoProm;
    }

    @Override
    public String toString() {
        return "Ciudad [nombre=" + nombre + ", nomenclatura=" + nomenclatura + ", metros=" + metros
                + ", cantHabitantes=" + cantHabitantes + ", consumoPromedio=" + consumoPromedio + "]";
    }

}
