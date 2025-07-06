package transporteAgua;

public class Ciudad {
    String nombre;
    String nomeclatura;
    double metros;
    int cantHabitantes = 1000;
    double consumoPromedio = 0.25;


    public Ciudad(String nombreC, double metros){
        this.nombre = nombreC;
        this.metros = metros;
    }
}
