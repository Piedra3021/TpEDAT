package transporteAgua;

import conjuntistas.ArbolAVL;
import lineales.dinamica.*;

public class Ciudad implements Comparable {
    private String nombre;
    private String nomenclatura;
    private double metros;
    // private int poblacion = 1000;
    private Lista poblacion;
    private double consumoPromedio = 0.25;
    private double consumoProm;
    private static int numNomenclatura = 3000;

    public Ciudad(String nombreC, double metros, double consumoProm) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = metros;
        this.consumoProm = consumoProm;
        numNomenclatura = (numNomenclatura + 1) % 4000;
    }

    public Ciudad(String nombreC) {
        this.nombre = nombreC;
        this.nomenclatura = obtenerNomenclatura(nombre);
        this.metros = 0.0;
        this.consumoProm = 0.0;
        numNomenclatura = (numNomenclatura + 1) % 4000;
    }

    private String obtenerNomenclatura(String nombre) {
        String cadena = "";
        if (nombre.length() > 2) {
            cadena = nombre.substring(0, 2).toUpperCase() + numNomenclatura;
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

    public int getPoblacion(int anio, int mes) {
        int cant = -1;
        if (poblacion != null) {
            int posAnio = poblacion.localizar(anio);
            Anio anioPoblacion = (Anio) poblacion.recuperar(posAnio);
            if (anioPoblacion != null) {
                cant = anioPoblacion.getPoblacionMes(mes);
            }
        }
        return cant;
    }

    public int getPoblacionAnual(int anio) {
        int cant = -1;
        if (poblacion != null) {
            int posAnio = poblacion.localizar(anio);
            Anio anioPob = (Anio) poblacion.recuperar(posAnio);
            if (anioPob != null) {
                cant = anioPob.getPoblacionAnual();
            }
        }
        return cant;
    }

    // Setter para mes y año en especifico
    public void setPoblacion(int anioPoblacion, int mes, int cantidad) {
        Anio anioModificar;
        // Verifico si la lista existe.
        if (poblacion != null) {
            // Localizo el anio especifico
            int posAnio = poblacion.localizar(anioPoblacion);
            if (posAnio != -1) {
                // Si existe, lo recupero
                anioModificar = (Anio) poblacion.recuperar(posAnio);
            } else {
                // Si no, lo creo.
                anioModificar = new Anio(anioPoblacion);
                poblacion.insertar(anioModificar, poblacion.longitud() + 1);
            }
        } else {
            poblacion = new Lista();
            anioModificar = new Anio(anioPoblacion);
            poblacion.insertar(anioModificar, 1);
        }

        anioModificar.actualizarMes(mes, cantidad);
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

    // Revisar!
    public int compareTo(Object otraCiudad) {
        int resultado = -1;
        if (otraCiudad instanceof Ciudad) {
            resultado = this.nomenclatura.compareTo(((Ciudad) otraCiudad).getNomenclatura());
            if (resultado != 0) {
                resultado = resultado < 0 ? -1 : 1;
            }
        } else if (otraCiudad instanceof String) {
            resultado = this.nomenclatura.compareTo((String) otraCiudad);
            if (resultado != 0) {
                resultado = resultado < 0 ? -1 : 1;
            }

        }
        return resultado;
    }

    public boolean equals(Ciudad otraCiudad) {
        return this.nombre.equals(otraCiudad.getNombre());
    }

    public double cantidadAguaPorMes(int anio, int mes) {
        double res = -1;
        if (poblacion != null) {
            int anioPos = poblacion.localizar(anio);
            Anio anioTrabajar = (Anio) poblacion.recuperar(anioPos);
            if (anioTrabajar != null) {
                int cantDias = anioTrabajar.obtenerDiasDelMes(mes);
                int cantHabitantes = anioTrabajar.getPoblacionMes(mes);
                res = cantHabitantes * consumoPromedio * cantDias;
            }
        }
        return res;
    }

    public double cantidadAguaPorAnio(int anio){
        double res = 0;
        if(poblacion != null){
            int anioPos = poblacion.localizar(anio);
            Anio anioTrabajar = (Anio) poblacion.recuperar(anioPos);
            if(anioTrabajar!=null){
                for (int mes = 1; mes <= 12; mes++) {
                    // Acumulo el consumo de cada mes
                    int cantHabitantes = anioTrabajar.getPoblacionMes(mes);
                    if (cantHabitantes != -1) {
                        res = res + cantHabitantes*consumoPromedio;
                    }
                }
            }
        }
        return res;
    }

    public int getDias(int anio, int mes){
        int cantDias = -1;
        Anio anioC = new Anio(anio);
        
        cantDias = anioC.obtenerDiasDelMes(mes);

        return cantDias;
    }

    // Revisar
    public boolean consumoEnRango(int anio, int mes, double minVol, double maxVol) {
        double cantAguaMes = this.cantidadAguaPorMes(anio, mes);
        return (cantAguaMes >= minVol) && (cantAguaMes <= maxVol);
    }

    public String toString() {
        return nombre;
    }

    public boolean equals(Object ciudad) {
        boolean exito = false;
        if (ciudad instanceof Ciudad) {
            if (this.nombre == ((Ciudad) ciudad).getNombre()) {
                exito = true;
            }

        } else if (ciudad instanceof String) {
            if (nomenclatura.equals(ciudad)) {
                exito = true;
            } else if (nombre.equals(ciudad)) {
                exito = true;
            }
        }
        return exito;
    }
}
