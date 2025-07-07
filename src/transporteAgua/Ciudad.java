package transporteAgua;
import conjuntistas.ArbolAVL;

public class Ciudad implements Comparable<Object> {
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

    public Ciudad(String nombreC) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = 0.0;
        this.consumoProm = new Anio(2025);
        codigoNumerico = (codigoNumerico+1)%4000;
    }

    public static Ciudad buscarEnArbol(ArbolAVL arbol, String nombreC) {
        //Se aclara que se esta buscando en un Arbol que almacena Ciudades
        return (Ciudad) arbol.buscar(nombreC);
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
    // Borrar luego?
    public void setConsumoProm(Anio consumoProm) {
        this.consumoProm = consumoProm;
    }

    public int compareTo(Object otraCiudad) {
        int r = 0;
        if (!(otraCiudad instanceof Ciudad)) {
            //Se compara con un String
            r = this.nombre.compareTo((String) otraCiudad);
        } else {
            //Se compara con una Ciudad
            Ciudad c = (Ciudad) otraCiudad;
            r = this.nombre.compareTo(c.getNombre());
        }
        return r;
    }

    public boolean equals(Object otraCiudad) {
        boolean r;
        if (!(otraCiudad instanceof Ciudad)) {
            //Se compara con un String
            r = this.nombre.equals((String) otraCiudad);
        } else {
            //Se compara con una Ciudad
            Ciudad c = (Ciudad) otraCiudad;
            r = this.nombre.equals(c.getNombre());
        }
        return r;
    }

    public String toString() {
        return nombre;
    }
}
