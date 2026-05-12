package pe.edu.pucp.tiendaalien.model.logistica;

public class AgenciaEnvio {

    private int agenciaId;
    private String nombre;
    private String urlTracking; // Nullable
    private boolean esActivo; // Para manejar delete logico

    public AgenciaEnvio() {
    }

    public AgenciaEnvio(String nombre, String urlTracking) {
        this.nombre = nombre;
        this.urlTracking = urlTracking;
        this.esActivo = true;
    }

    public void setAgenciaId(int agenciaId) {
        this.agenciaId = agenciaId;
    }
    public int getAgenciaId() {
        return agenciaId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setUrlTracking(String urlTracking) {
        this.urlTracking = urlTracking;
    }
    public String getUrlTracking() {
        return urlTracking;
    }

    public boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public String toString() {
        return "ID = " + agenciaId + ", NOMBRE = " + nombre + ", URLTracking = " + urlTracking + "\n";
    }
}
