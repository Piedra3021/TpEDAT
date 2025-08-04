package transporteAgua;

public class ClaveTuberia {
    private String nom1;
    private String nom2;

    public ClaveTuberia(String desde, String hasta) {
        this.nom1 = desde;
        this.nom2 = hasta;
    }

    // Usado por Grafo.obtenerCamino
    public ClaveTuberia(Object desde, Object hasta) {
        this.nom1 = ((Ciudad) desde).getNomenclatura();
        this.nom2 = ((Ciudad) hasta).getNomenclatura();
        ;
    }

    public String getNom1() {
        return nom1;
    }

    public String getNom2() {
        return nom2;
    }

    @Override
    public String toString() {
        // return "ClaveTuberia [Desde=" + nom1 + ", Hasta=" + nom2 + "]";
        return "\nCT [" + nom1 + "->" + nom2 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom1 == null) ? 0 : nom1.hashCode());
        result = prime * result + ((nom2 == null) ? 0 : nom2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                ClaveTuberia other = (ClaveTuberia) obj;
                if (nom1.equals(other.nom1) && nom2.equals(other.nom2)) {
                    result = true;
                } else if (nom1.equals(other.nom2) && nom2.equals(other.nom1)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
