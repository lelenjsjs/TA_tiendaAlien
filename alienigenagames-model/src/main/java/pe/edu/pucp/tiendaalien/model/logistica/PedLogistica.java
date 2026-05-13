package pe.edu.pucp.tiendaalien.model.logistica;

import pe.edu.pucp.tiendaalien.model.usuarios.DireccionUsuario;
import pe.edu.pucp.tiendaalien.model.ventas.Pedido;

public class PedLogistica {
    private int pedLogisticaId;
    private Pedido pedido;
    private DireccionUsuario direccion;
    private EstadoLogistica estadoLogistica;
    private String receptorNombre;
    private String receptorTipoDoc;
    private String receptorNroDoc;
    private String receptorCel;

    public PedLogistica() {
        pedido = new Pedido();
    }

    public int getPedLogisticaId() { return pedLogisticaId; }
    public void setPedLogisticaId(int pedLogisticaId) { this.pedLogisticaId = pedLogisticaId; }

    public EstadoLogistica getEstadoLogistica() { return estadoLogistica; }
    public void setEstadoLogistica(EstadoLogistica estadoLogistica) { this.estadoLogistica = estadoLogistica; }

    public String getReceptorNombre() { return receptorNombre; }
    public void setReceptorNombre(String receptorNombre) { this.receptorNombre = receptorNombre; }

    public String getReceptorTipoDoc() { return receptorTipoDoc; }
    public void setReceptorTipoDoc(String receptorTipoDoc) { this.receptorTipoDoc = receptorTipoDoc; }

    public String getReceptorNroDoc() { return receptorNroDoc; }
    public void setReceptorNroDoc(String receptorNroDoc) { this.receptorNroDoc = receptorNroDoc; }

    public String getReceptorCel() { return receptorCel; }
    public void setReceptorCel(String receptorCel) { this.receptorCel = receptorCel; }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public DireccionUsuario getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionUsuario direccion) {
        this.direccion = direccion;
    }
}
