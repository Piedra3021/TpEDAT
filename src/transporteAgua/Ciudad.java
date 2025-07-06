package transporteAgua;

public class Ciudad {
    String nombre;
    String nomeclatura;
    double metros;
    int cantHabitantes = 1000;
    double consumoPromedio = 0.25;


    public Ciudad(String nombreC, double metros){
        this.nombre = nombreC;
        this.nomeclatura = obtenerNomeclatura(nombre);
        this.metros = metros;
    }

    private String obtenerNomeclatura(String nombre){
        String cadena = "";
        if(nombre.length() > 2){
            cadena = nombre.substring(0,2).toUpperCase();
        }

        return cadena;
    }
}
