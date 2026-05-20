package pe.edu.pucp.tiendaalien.model.ventas;
import java.util.Date;

public class HistorialEstadoPed {
    private Integer id;
    private Pedido pedido;
    private String estado;
    private Date fecActualizacion;

    // Constructor para facilitar la creación de registros de auditoría
    public HistorialEstadoPed(String estado, Date fecha) {
        this.estado = estado;
        this.fecActualizacion = fecha;
    }

    public HistorialEstadoPed() {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
