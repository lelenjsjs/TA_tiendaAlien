package pe.edu.pucp.tiendaalien.logistica;

public class AgenciaEnvio {

    private int agenciaId;
    private String nombre;
    private String urlTracking; // Nullable

    public AgenciaEnvio() {
    }

    public AgenciaEnvio(int agenciaId, String nombre, String urlTracking) {
        this.agenciaId = agenciaId;
        this.nombre = nombre;
        this.urlTracking = urlTracking;
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

}
