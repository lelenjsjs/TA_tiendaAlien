package pe.edu.pucp.tiendaalien.model.ventas;
import java.util.Date;

public class HistorialEstadoPed {
    private Integer historialId;
    private String estado;
    private Date fecActualizacion;

    // Constructor para facilitar la creación de registros de auditoría
    public HistorialEstadoPed(String estado, Date fecha) {
        this.estado = estado;
        this.fecActualizacion = fecha;
    }

    public Integer getHistorialId() {
        return historialId;
    }
    public void setHistorialId(Integer historialId) {
        this.historialId = historialId;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }
    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }
}
