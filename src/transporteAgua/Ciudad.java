package transporteAgua;

import conjuntistas.ArbolAVL;

public class Ciudad {
    private String nombre;
    private String nomenclatura;
    private double metros;
    // private int poblacion = 1000;
    private Anio poblacion;
    private double consumoPromedio = 0.25;
    private double consumoProm;
    // numNomenclatura?
    private static int codigoNumerico = 3000;

    public Ciudad(String nombreC, double metros, double consumoProm) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = metros;
        this.consumoProm = consumoProm;
        codigoNumerico = (codigoNumerico + 1) % 4000;
    }

    public Ciudad(String nombreC) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = 0.0;
        this.consumoProm = 0.0;
        codigoNumerico = (codigoNumerico + 1) % 4000;
    }

    private String obtenerNomenclatura(String nombre) {
        String cadena = "";
        if (nombre.length() > 2) {
            cadena = nombre.substring(0, 2).toUpperCase() + codigoNumerico;
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

    public Anio getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Anio poblacion) {
        this.poblacion = poblacion;
    }

    public double getConsumoPromedio() {
        return consumoPromedio;
    }

    public double getConsumoProm() {
        return consumoProm;
    }

    // Borrar luego?
    public void setConsumoProm(double consumoProm) {
        this.consumoProm = consumoProm;
    }

    public int compareTo(Ciudad otraCiudad) {
        return this.nombre.compareTo(otraCiudad.getNombre());
    }

    public boolean equals(Ciudad otraCiudad) {
        return this.nombre.equals(otraCiudad.getNombre());
    }

    public String toString() {
        return nombre + "(" + nomenclatura + ")";
    }
}
