package transporteAgua;

public class Ciudad {
    String nombre;
    String nomeclatura;
    double metros;
    int cantHabitantes = 1000;
    double consumoPromedio = 0.25;
    // Arreglo de anios?
    Anio consumoProm;

    public Ciudad(String nombreC, double metros) {
        this.nombre = nombreC;
        this.metros = metros;
        this.consumoProm = new Anio(2025);
    }

    public String getNombre() {
        return nombre;
    }

    public String getNomeclatura() {
        return nomeclatura;
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

}
