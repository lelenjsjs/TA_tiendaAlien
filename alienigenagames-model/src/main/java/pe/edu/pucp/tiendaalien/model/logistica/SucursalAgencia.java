package pe.edu.pucp.tiendaalien.model.logistica;


public class SucursalAgencia {
    private int sucursalId;
    private int agenciaId; // O el objeto AgenciaEnvio si prefieres
    private int ubigeoId;
    private boolean activo;

    // Constructores
    public SucursalAgencia() {}

    // Getters y Setters
    public int getSucursalId() { return sucursalId; }
    public void setSucursalId(int sucursalId) { this.sucursalId = sucursalId; }
    public int getAgenciaId() { return agenciaId; }
    public void setAgenciaId(int agenciaId) { this.agenciaId = agenciaId; }
    public int getUbigeoId() { return ubigeoId; }
    public void setUbigeoId(int ubigeoId) { this.ubigeoId = ubigeoId; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}